package com.abhi.influencermvp.repository;

import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignApplicationRepository  extends CrudRepository<CampaignApplication,Integer> {

    List<CampaignApplication> findByInfluencerEmail(String email);
    List<CampaignApplication> findByCampaignId(int campaignId);
    List<CampaignApplication> findByCampaignIdAndStatus(int campaignId, ApplicationStatus status);



}
