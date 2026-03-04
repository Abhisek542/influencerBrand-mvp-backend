package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.InfluencerDashboardDTO;
import com.abhi.influencermvp.dto.MyApplicationDto;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.service.impl.InfluencerDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/influencers")
public class InfluencerDashboardController {
    @Autowired
    private InfluencerDashboardService influencerDashboardService;

    @GetMapping("/mydashboard")
    @PreAuthorize("hasRole('INFLUENCER')")
    public InfluencerDashboardDTO getDashboard() {
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return influencerDashboardService.getInfluencerDashboard(email);
    }
    @GetMapping("/accepted")
    @PreAuthorize("hasRole('INFLUENCER')")
    public List<Campaign> getAcceptedCampaigns() {
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return influencerDashboardService.getAcceptedCampaigns(email);
    }
    @GetMapping("/my-applications")
    @PreAuthorize("hasRole('INFLUENCER')")
    public List<MyApplicationDto> getMyApplications() {

        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return influencerDashboardService.getMyApplications(email);
    }
}
