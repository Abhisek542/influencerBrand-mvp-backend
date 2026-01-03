package com.abhi.influencermvp.dto;

import com.abhi.influencermvp.enums.ApplicationStatus;
import lombok.Data;

@Data
public class MyApplicationDto {

    private int campaignId;
    private String campaignTitle;
    private String brandName;
    private ApplicationStatus status;
    private String message;
    private String deadline;


}
