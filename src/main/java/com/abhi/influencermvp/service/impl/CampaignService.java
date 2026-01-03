package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.CampaignResponseDto;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.repository.CampaignApplicationRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CampaignService {
    String createCampaign(String brandEmail, Campaign campaign);
    Page<CampaignResponseDto> getAllCampaigns(int page,int size);
    CampaignResponseDto getCampaignById(int id);
    Page<Campaign> getMyCampaigns(String email, int page, int size);

    String deleteCampaign(String email, int id);

    String updateCampaign(int id, String email, Campaign updatedCampaign);
    Page<CampaignResponseDto> filterCampaigns(String title,String niche,String brandName,Double minBudget,Double maxBudget,int page,int size);



}
