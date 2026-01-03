package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.UserProfileDto;
import io.jsonwebtoken.io.IOException;
import org.springframework.web.multipart.MultipartFile;


public interface UserProfileService {
    String updateUserProfile(String email, UserProfileDto dto);
    UserProfileDto getMyProfile(String email);

    String uploadImage(String email, MultipartFile file) throws IOException;
}
