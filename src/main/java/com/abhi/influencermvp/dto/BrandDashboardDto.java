package com.abhi.influencermvp.dto;

import lombok.Data;

@Data
public class BrandDashboardDto {

    private long totalCampaigns;
    private long activeCampaigns;
    private long expiredCampaigns;
    private double totalBudget;

}
