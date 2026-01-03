package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.BrandDashboardDto;
import com.abhi.influencermvp.service.impl.BrandDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/dashboard")
public class BrandDashboardController {
    @Autowired
    BrandDashboardService brandDashboardService;

    @GetMapping("/brand")
    @PreAuthorize("hasRole('BRAND')")
    public BrandDashboardDto getBrandDashboardDto() {
        String  email = Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return brandDashboardService.getBrandDashboard(email);
    }
}
