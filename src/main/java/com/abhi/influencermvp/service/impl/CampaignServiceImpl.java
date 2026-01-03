package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.CampaignResponseDto;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.entity.CampaignApplication;
import com.abhi.influencermvp.exception.ResourceNotFoundException;
import com.abhi.influencermvp.repository.CampaignRepository;
import com.abhi.influencermvp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String createCampaign(String brandEmail, Campaign campaign) {
        campaign.setCreatedBy(brandEmail);
        campaignRepository.save(campaign);
        return "Campaign created successfully!";
    }

    @Override
    public Page<CampaignResponseDto> getAllCampaigns(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Campaign> campaigns= campaignRepository.findAll(pageable);
        return campaigns.map(campaign -> {

            CampaignResponseDto campaignResponseDto = new CampaignResponseDto();
            campaignResponseDto.setId(campaign.getId());
            campaignResponseDto.setDeadline(campaign.getDeadline());
            campaignResponseDto.setTitle(campaign.getTitle());
            campaignResponseDto.setDescription(campaign.getDescription());
            campaignResponseDto.setBrandName(campaign.getBrandName());
            campaignResponseDto.setNiche(campaign.getNiche());
            campaignResponseDto.setBudget(campaign.getBudget());
            return campaignResponseDto;
        });
    }

    @Override
    public CampaignResponseDto getCampaignById(int id) {

       Campaign campaign = campaignRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Campaign not found!"));

       CampaignResponseDto dto = new CampaignResponseDto();
       dto.setId(campaign.getId());
       dto.setNiche(campaign.getNiche());
       dto.setBudget(campaign.getBudget());
       dto.setTitle(campaign.getTitle());
       dto.setDescription(campaign.getDescription());
       dto.setDeadline(campaign.getDeadline());
       dto.setBrandName(campaign.getBrandName());

       return dto;

    }

    @Override
    public Page<Campaign> getMyCampaigns(String  email, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return campaignRepository.findByCreatedBy(email, pageable);

    }

    @Override
    public String deleteCampaign(String email, int id) {

        Campaign existing = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not exists"));

        if(!existing.getCreatedBy().equals(email)){
            return "You are not allowed to delete this campaign!";
        }
        campaignRepository.delete(existing);
        return "Campaign deleted successfully!";

    }

    @Override
    public String updateCampaign(int id, String email, Campaign updatedCampaign) {

        Campaign existing = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not exists"));

        if(!existing.getCreatedBy().equals(email)){
            return "You are not allowed to delete this campaign!";
        }

        existing.setTitle(updatedCampaign.getTitle());
        existing.setDescription(updatedCampaign.getDescription());
        existing.setBrandName(updatedCampaign.getBrandName());
        existing.setDeadline(updatedCampaign.getDeadline());
        existing.setNiche(updatedCampaign.getNiche());
        existing.setBudget(updatedCampaign.getBudget());

        campaignRepository.save(existing);
        return "Campaign updated successfully!";


    }

    @Override
    public Page<CampaignResponseDto> filterCampaigns(String title,String niche,String brandName,Double minBudget,Double maxBudget,int page,int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Campaign> campaigns = campaignRepository.findAll(pageable);

        List<CampaignResponseDto> filtered = campaigns.getContent()
                .stream()
                .filter(c -> title == null || c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(c -> brandName == null || c.getBrandName().equalsIgnoreCase(brandName))
                .filter(c -> niche == null || c.getNiche().equalsIgnoreCase(niche))
                .filter(c -> {
                    if (minBudget == null && maxBudget == null) return true;
                    double budget = Double.parseDouble(c.getBudget());
                    if (minBudget != null && budget < minBudget) return false;
                    if (maxBudget != null && budget > maxBudget) return false;
                    return true;
                })
                .map(c -> {
                    CampaignResponseDto dto = new CampaignResponseDto();
                    dto.setId(c.getId());
                    dto.setTitle(c.getTitle());
                    dto.setDescription(c.getDescription());
                    dto.setBrandName(c.getBrandName());
                    dto.setBudget(c.getBudget());
                    dto.setNiche(c.getNiche());
                    dto.setDeadline(c.getDeadline());
                    return dto;
                })
                .toList();

        return new PageImpl<>(filtered, pageable, campaigns.getTotalElements());
    }


    }




