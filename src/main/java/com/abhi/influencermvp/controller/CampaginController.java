package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.CampaignResponseDto;
import com.abhi.influencermvp.dto.PageResponse;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.service.impl.CampaignApplicationService;
import com.abhi.influencermvp.service.impl.CampaignService;
import com.abhi.influencermvp.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/campaign")
public class CampaginController {

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private UserService userService;
    @Autowired
    private CampaignApplicationService campaignApplicationService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('BRAND')")
    public String createCampaign(@Valid @RequestBody Campaign campaign) {

        System.out.println("CAMPAIGN RECEIVED → " + campaign);
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return campaignService.createCampaign(email, campaign);
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('INFLUENCER')")
    public Page<CampaignResponseDto> getAllCampaigns(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size) {
        return campaignService.getAllCampaigns(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('BRAND')")
    public CampaignResponseDto getCampaignById(@PathVariable int id) {
        return campaignService.getCampaignById(id);
    }

    @GetMapping("/my-campaigns")
    @PreAuthorize("hasRole('BRAND')")
    public Page<Campaign> getMyCampaigns(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size) {
        String email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return campaignService.getMyCampaigns(email,page,size);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('BRAND')")
    public String deleteCampaign(@PathVariable int id) {

        String  email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return campaignService.deleteCampaign(email,id);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('BRAND')")
    public String updateCampaign(@PathVariable int id, @RequestBody Campaign updatedCampaign) {
        String  email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return campaignService.updateCampaign(id,email,updatedCampaign);
    }
    @GetMapping("/filtr")
    public PageResponse<CampaignResponseDto> filterCampaigns(@RequestParam(required = false)String title, @RequestParam(required = false)String niche, @RequestParam(required = false)String  brandName, @RequestParam(required = false)Double minBudget, @RequestParam(required = false)Double maxBudget, @RequestParam(required = false) CampaignApplication status, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "50")int size) {
        return campaignService.filterCampaigns(title,niche,brandName, minBudget, maxBudget, page, size);
    }




}

