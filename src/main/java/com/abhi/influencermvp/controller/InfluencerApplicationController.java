package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.service.impl.CampaignApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/influencer")
public class InfluencerApplicationController {

    @Autowired
    private CampaignApplicationService campaignApplicationService;

    @PostMapping("/apply/{id}")
    @PreAuthorize("hasRole('INFLUENCER')")
    public String applyCampaign(@PathVariable int id, @RequestParam(required = false) String message) {

        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return campaignApplicationService.applyToCampaign(email,id,message);
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('INFLUENCER')")
    public List<CampaignApplication> getApplications(){
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return campaignApplicationService.getMyApplications(email);
    }
}
