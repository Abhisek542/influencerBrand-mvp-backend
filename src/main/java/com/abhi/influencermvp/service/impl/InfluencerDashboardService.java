package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.InfluencerDashboardDTO;
import com.abhi.influencermvp.dto.MyApplicationDto;
import com.abhi.influencermvp.entity.Campaign;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InfluencerDashboardService {
    InfluencerDashboardDTO getInfluencerDashboard(String email);
    List<Campaign> getAcceptedCampaigns(String email);
    List<MyApplicationDto> getMyApplications(String email);

}
