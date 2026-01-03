package com.abhi.influencermvp.service.impl;


import com.abhi.influencermvp.dto.UserProfileDto;
import com.abhi.influencermvp.entity.User;
import com.abhi.influencermvp.entity.UserProfile;
import com.abhi.influencermvp.repository.UserProfileRepository;
import com.abhi.influencermvp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

 @Autowired
 UserProfileRepository profileRepository;
 @Autowired
 UserRepository userRepository;

 @Override
 public UserProfileDto getMyProfile(String email){

     User  user = userRepository.findByEmail(email).orElseThrow();
     UserProfile profile = profileRepository.findByUserId(user.getId());

     UserProfileDto dto = new UserProfileDto();

     if(profile!=null){
         dto.setBio(profile.getBio());
         dto.setLocation(profile.getLocation());
         dto.setSocialLinks(profile.getSocialLinks());
         dto.setProfilePicture(profile.getProfilePicture());
     }
     return dto;

    }
    @Override
    public String updateUserProfile(String email, UserProfileDto dto) {

     User user = userRepository.findByEmail(email).orElseThrow();
     int id=user.getId();
     UserProfile profile = profileRepository.findByUserId(id);

    if(profile==null){
        profile = new UserProfile();
        profile.setUser(user);
    }
    profile.setBio(dto.getBio());
    profile.setLocation(dto.getLocation());
    profile.setSocialLinks(dto.getSocialLinks());
    profileRepository.save(profile);
    return "User Profile Updated Successfully";
    }

    @Override
    public String uploadImage(String email, MultipartFile file){
        User user = userRepository.findByEmail(email).orElseThrow();
        UserProfile profile = profileRepository.findByUserId(user.getId());
        if(profile==null){
            profile = new UserProfile();
            profile.setUser(user);
        }

        String folder ="uploads/";
        String fileName= System.currentTimeMillis()+"_"+ file.getOriginalFilename();

        Path path = FileSystems.getDefault().getPath(folder,fileName);

        try {
            Files.write(path,file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        profile.setProfilePicture(fileName);
        profileRepository.save(profile);
        return "Profile Image Uploaded Successfully";

    }
}
