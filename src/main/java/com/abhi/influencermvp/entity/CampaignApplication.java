package com.abhi.influencermvp.entity;

import com.abhi.influencermvp.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Campaign-Application")
@Data
public class CampaignApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int campaignId;

    private String influencerEmail;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status=ApplicationStatus.PENDING;

    private LocalDateTime appliedAt = LocalDateTime.now();

}
