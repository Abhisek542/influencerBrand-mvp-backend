package com.abhi.influencermvp.repository;

import com.abhi.influencermvp.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository  extends JpaRepository<Campaign,Integer> {

    Page<Campaign> findByCreatedBy(String email, Pageable pageable);

}
