package com.abhi.influencermvp.repository;

import com.abhi.influencermvp.dto.BrandDashboardDto;
import com.abhi.influencermvp.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranDashboardRepo extends JpaRepository<Campaign,Integer> {
    List<Campaign> findByCreatedBy(String email);
}
