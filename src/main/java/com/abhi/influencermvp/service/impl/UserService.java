package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.ApiResponseDTO;
import com.abhi.influencermvp.dto.LoginResponseDto;
import com.abhi.influencermvp.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ApiResponseDTO> registerUser(UserDto dto);
    LoginResponseDto loginUser(UserDto dto);


}
