package com.abhi.influencermvp.service.impl;


import com.abhi.influencermvp.dto.BrandDashboardDto;
import org.springframework.data.domain.Pageable;

public interface BrandDashboardService {
    BrandDashboardDto getBrandDashboard(String email);
}
