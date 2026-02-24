package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.BrandDashboardDto;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.enums.ApplicationStatus;
import com.abhi.influencermvp.repository.BranDashboardRepo;
import com.abhi.influencermvp.repository.CampaignApplicationRepository;
import com.abhi.influencermvp.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BrandDashboardServiceImpl implements BrandDashboardService {

    @Autowired
    CampaignRepository campaignRepository;
    @Autowired
    BranDashboardRepo brandDashboardRepo;
    @Autowired
    CampaignApplicationRepository campaignApplicationRepository;

    public BrandDashboardDto getBrandDashboard(String email) {

        BrandDashboardDto dto = new BrandDashboardDto();
        List<Campaign> campaigns = brandDashboardRepo.findByCreatedBy(email);

        dto.setTotalCampaigns(campaigns.size());
        long active = 0;
        long expired = 0;
        double totalBudget = 0;
        for (Campaign c : campaigns) {

            String deadline = String.valueOf(LocalDate.parse(c.getDeadline()));
            if(deadline != null && !deadline.isEmpty()) {

                LocalDate deadlineDate = LocalDate.parse(deadline);
                if(deadlineDate.isAfter(LocalDate.now())) {
                    active++;
                }
                else{
                    expired++;
                }
            }
            else{
                expired++;
            }
            try {
                totalBudget += Double.parseDouble(c.getBudget());
            }
            catch (Exception ignored) {}
        }
       dto.setTotalBudget(totalBudget);
        dto.setActiveCampaigns(active);
        dto.setExpiredCampaigns(expired);

     /*   dto.setTotalApplications(campaignApplicationRepository.countByCampaignCreatedBy(email));
        dto.setPendingApplications(campaignApplicationRepository.countByCreatedByAndStatus(email, ApplicationStatus.PENDING));*/

        return dto;
    }
}
