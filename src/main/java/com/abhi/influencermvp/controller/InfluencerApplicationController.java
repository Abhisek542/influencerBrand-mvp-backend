package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.ApplicationRequestDTO;
import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.service.impl.CampaignApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/influencer")
public class InfluencerApplicationController {

    @Autowired
    private CampaignApplicationService campaignApplicationService;

    @PostMapping("/apply/{id}")
    @PreAuthorize("hasRole('INFLUENCER')")
    public ResponseEntity<Map<String, String>> applyCampaign(
            @PathVariable int id,
            @RequestBody ApplicationRequestDTO applicationRequest
    ) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        String message = campaignApplicationService.applyToCampaign(
                email,
                id,
                applicationRequest.message(),
                applicationRequest.influencerName(),
                applicationRequest.niche(),
                applicationRequest.platform()
        );

        return ResponseEntity.ok(Map.of("message", message));
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('INFLUENCER')")
    public List<CampaignApplication> getApplications(){
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return campaignApplicationService.getMyApplications(email);
    }
}
