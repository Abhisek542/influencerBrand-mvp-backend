package com.abhi.influencermvp.service.impl;
import com.abhi.influencermvp.config.JwtUtil;
import com.abhi.influencermvp.dto.ApiResponseDTO;
import com.abhi.influencermvp.dto.LoginResponseDto;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.entity.User;
import com.abhi.influencermvp.dto.UserDto;

import com.abhi.influencermvp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<ApiResponseDTO> registerUser(UserDto dto){

        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)           // 409
                    .body(new ApiResponseDTO("Email already exists", false));
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());           // ⚠️ hash this before saving
        user.setRole(dto.getRole());
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)               // 201
                .body(new ApiResponseDTO("User registered successfully", true));
    }

    @Override
    public LoginResponseDto loginUser(UserDto dto){

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new RuntimeException("Email does not exist"));
        if(!dto.getPassword().equals(user.getPassword())){
            throw  new RuntimeException("Wrong password");
        }

        String token = jwtUtil.generateToken(user.getEmail(),user.getRole());

        loginResponseDto.setToken(token);
        loginResponseDto.setRole(user.getRole().toString());
        return loginResponseDto;

    }


}
