package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.UserProfileDto;
import com.abhi.influencermvp.service.impl.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/influencer")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/profile/update")
    @PreAuthorize("hasRole('INFLUENCER')")
    public Map<String, String> updateProfile(@RequestBody UserProfileDto dto) {

        String email= Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
         userProfileService.updateUserProfile(email, dto);
        return Map.of("message", "Profile updated successfully");
    }

    @GetMapping("/profile/me")
    @PreAuthorize("hasRole('INFLUENCER')")
   public UserProfileDto getMyProfile() {
        String email= Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return userProfileService.getMyProfile(email);
   }

   @PostMapping("/profile/upload-image")
   @PreAuthorize("hasRole('INFLUENCER')")
   public Map<String, String> uploadImage(@RequestParam("file")   MultipartFile file) {

        String email= Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

         userProfileService.uploadImage(email,file);

       return Map.of("message", "Profile Image Uploaded Successfully");
   }


}
