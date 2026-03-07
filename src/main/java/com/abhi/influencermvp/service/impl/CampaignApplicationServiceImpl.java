package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.enums.ApplicationStatus;
import com.abhi.influencermvp.exception.ResourceNotFoundException;
import com.abhi.influencermvp.repository.CampaignApplicationRepository;
import com.abhi.influencermvp.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignApplicationServiceImpl implements CampaignApplicationService {

    @Autowired
    private CampaignApplicationRepository campaignApplicationRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    //This method for Influencer->apply to campaign
    @Override
    public String applyToCampaign(String email, int campaignId, String message,String influencerName,String niche,String platform) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(()->new ResourceNotFoundException("Campaign not found"));

        //check already applied or not

        boolean alreadyApplied =
                campaignApplicationRepository
                        .existsByInfluencerEmailAndCampaignId(email, campaignId);

        if(alreadyApplied){
            return "Your campaign has been already applied";
        }

        CampaignApplication app = new CampaignApplication();
        app.setCampaignId(campaignId);
        app.setInfluencerName(influencerName);
        app.setNiche(niche);
        app.setPlatform(platform);
        app.setMessage(message);
        app.setInfluencerEmail(email);
        app.setStatus(ApplicationStatus.PENDING);

        campaignApplicationRepository.save(app);

        return "Application has been applied";

    }

    //This method for influencer->to get influencer applications
    @Override
    public List<CampaignApplication> getMyApplications(String email) {
        return  campaignApplicationRepository.findByInfluencerEmail(email);
    }

    //This method for brand->get applications for a specific campaign
    @Override
    public List<CampaignApplication> getApplicationsForCampaign(String brandEmail, int campaignId) {

        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(()->new ResourceNotFoundException("Campaign not found"));

        if(!campaign.getCreatedBy().equals(brandEmail)){
            throw new ResourceNotFoundException("Campaign not found");
        }
        return campaignApplicationRepository.findByCampaignId(campaignId);

    }

    //This method for brand-> to update their campaigns
    @Override
    public String UpdateCampaignApplicationsStatus(String brandEmail, int applicationId, ApplicationStatus status){

       CampaignApplication app = campaignApplicationRepository.findById(applicationId).orElseThrow(()->new RuntimeException("Application not found"));


;

        Campaign campaign = campaignRepository.findById(app.getCampaignId()).orElseThrow(()->new ResourceNotFoundException("Campaign not found"));

        if(!campaign.getCreatedBy().equals(brandEmail)){
            return "Not authorizes";
        }
        app.setStatus(status);
        campaignApplicationRepository.save(app);
        return "Appication"+ " "+status +" "+"sucessfully";

    }

    //This method for brand-> to get accepted influencers
    @Override
    public List<CampaignApplication> getAcceptedInfluencers(String brandEmail, int campaignId) {

        Campaign campaign =  campaignRepository.findById(campaignId).orElseThrow(()->new ResourceNotFoundException("Campaign not found"));
        if(!campaign.getCreatedBy().equals(brandEmail)){
            throw new RuntimeException("Not authorizes");
        }

        return campaignApplicationRepository.findByCampaignIdAndStatus(campaignId,ApplicationStatus.ACCEPTED);
    }

    @Override
    public String deleteCampaignApplication(String brandEmail, int applicationId) {

        CampaignApplication campaignApplication = campaignApplicationRepository.findById(applicationId).orElseThrow(()->new RuntimeException("Application not found"));

        Campaign campaign =  campaignRepository.findById(campaignApplication.getCampaignId()).orElseThrow(()->new ResourceNotFoundException("Campaign not found"));
        if(!campaign.getCreatedBy().equals(brandEmail)){
            throw new RuntimeException("Not authorizes");
        }
        campaignApplicationRepository.delete(campaignApplication);
        return "Application has been deleted";
    }

}

