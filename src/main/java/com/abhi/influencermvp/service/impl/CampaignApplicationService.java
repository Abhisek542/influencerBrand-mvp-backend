package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.enums.ApplicationStatus;
import com.abhi.influencermvp.repository.CampaignApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignApplicationService {
    String applyToCampaign(String email, int campaignId, String message);
    List<CampaignApplication> getMyApplications(String email);
    List<CampaignApplication> getApplicationsForCampaign(String brandEmail, int campaignId);
    String UpdateCampaignApplicationsStatus(String brandEmail, int campaignId, ApplicationStatus status);
    List<CampaignApplication> getAcceptedInfluencers(String brandEmail, int campaignId);


}
