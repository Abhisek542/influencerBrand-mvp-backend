package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.InfluencerDashboardDTO;
import com.abhi.influencermvp.dto.MyApplicationDto;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.enums.ApplicationStatus;
import com.abhi.influencermvp.exception.ResourceNotFoundException;
import com.abhi.influencermvp.repository.CampaignApplicationRepository;
import com.abhi.influencermvp.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class InfluencerDashboardServiceImpl implements InfluencerDashboardService {

    @Autowired
    private CampaignApplicationRepository campaignApplicationRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public InfluencerDashboardDTO getInfluencerDashboard(String email){

        List<CampaignApplication> applications = campaignApplicationRepository.findByInfluencerEmail(email);

        InfluencerDashboardDTO dto = new InfluencerDashboardDTO();
        long total = applications.size();

        dto.setTotalApplications(total);
        dto.setPendingApplications(applications.stream().filter(a->a.getStatus()==ApplicationStatus.PENDING).count());
        dto.setAcceptedApplications(applications.stream().filter(a->a.getStatus()==ApplicationStatus.ACCEPTED).count());
        dto.setRejectedApplications(applications.stream().filter(a->a.getStatus()==ApplicationStatus.REJECTED).count());
        return dto;
    }

    @Override
    public List<Campaign> getAcceptedCampaigns(String email) {
        List<CampaignApplication> applications = campaignApplicationRepository.findByInfluencerEmail(email);
        return applications.stream().
                filter(app->app.getStatus()==
                        ApplicationStatus.ACCEPTED).map(app->campaignRepository.findById(app.getCampaignId()).orElse(null)).filter(Objects::nonNull).toList();
    }

    @Override
    public List<MyApplicationDto> getMyApplications(String email) {

        List<CampaignApplication> applications = campaignApplicationRepository.findByInfluencerEmail(email);

        return applications.stream().map(
                app->{
                    Campaign campaign = campaignRepository.findById(app.getCampaignId()).orElse(null);

                    if(campaign!=null){
                        String deadline = String.valueOf(LocalDate.parse(campaign.getDeadline()));
                        MyApplicationDto dto = new MyApplicationDto();
                        dto.setCampaignId(campaign.getId());
                        dto.setBrandName(campaign.getBrandName());
                        dto.setStatus(app.getStatus());
                        dto.setMessage(app.getMessage());
                        dto.setDeadline(deadline);
                        return dto;
                    }
                    else{
                        return null;
                    }
                }

        ).filter(Objects::nonNull).toList();
    }
}
