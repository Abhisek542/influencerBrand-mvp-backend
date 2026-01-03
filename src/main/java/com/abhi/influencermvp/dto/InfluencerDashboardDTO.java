package com.abhi.influencermvp.dto;


import lombok.Data;

@Data
public class InfluencerDashboardDTO {

    private long totalApplications;
    private long pendingApplications;
    private long rejectedApplications;
    private long acceptedApplications;
}
