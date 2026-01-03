package com.abhi.influencermvp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CampaignResponseDto {

    private int id;
    private String title;
    private String description;
    private String brandName;
    private String budget;
    private String niche;
    private String deadline;


}
