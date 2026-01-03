package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.UserProfileDto;
import com.abhi.influencermvp.service.impl.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/profile/update")
    @PreAuthorize("hasRole('INFLUENCER')")
    public String updateProfile(@RequestBody UserProfileDto dto) {

        String email= Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return userProfileService.updateUserProfile(email, dto);
    }

    @GetMapping("/profile/me")
    @PreAuthorize("hasRole('INFLUENCER')")
   public UserProfileDto getMyProfile() {
        String email= Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();
        return userProfileService.getMyProfile(email);
   }

   @PostMapping("/profile/upload-Image")
   @PreAuthorize("hasRole('INFLUENCER')")
   public String uploadImage(@RequestParam("image")   MultipartFile file) {

        String email= Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).toString();

        return userProfileService.uploadImage(email,file);
   }

}
