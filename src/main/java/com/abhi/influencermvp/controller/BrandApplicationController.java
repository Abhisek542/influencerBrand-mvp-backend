package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.enums.ApplicationStatus;
import com.abhi.influencermvp.service.impl.CampaignApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/brand/applications")
public class BrandApplicationController {

    @Autowired
    private CampaignApplicationService campaignApplicationService;
//
    @GetMapping("/{campaignId}")
    @PreAuthorize("hasRole('BRAND')")
    public List<CampaignApplication> getApplicationsForCampaign(@PathVariable int campaignId) {

        String email = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        return campaignApplicationService.getApplicationsForCampaign(email, campaignId);
    }
//
    @PostMapping("/{applicationId}/status")
    @PreAuthorize("hasRole('BRAND')")
    public String UpdateApplicationStatus(@PathVariable int applicationId, @RequestParam ApplicationStatus status) {

        String email = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        return campaignApplicationService.UpdateCampaignApplicationsStatus(email, applicationId, status);
    }
//
    @GetMapping("/{campaignId}/accepted")
    @PreAuthorize("hasRole('BRAND')")
    public List<CampaignApplication> getAcceptedCampaigns(@PathVariable int campaignId) {
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return campaignApplicationService.getAcceptedInfluencers(email,campaignId);
    }
    @DeleteMapping("/{applicationId}/deleted")
    @PreAuthorize("hasRole('BRAND')")
    public String deleteCampaign(@PathVariable int applicationId) {
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return campaignApplicationService.deleteCampaignApplication(email,applicationId);
    }
    //
}
