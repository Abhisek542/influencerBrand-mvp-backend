package com.abhi.influencermvp.controller;

import com.abhi.influencermvp.dto.ApiResponseDTO;
import com.abhi.influencermvp.dto.UserDto;
import com.abhi.influencermvp.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> registerUser(@Valid @RequestBody UserDto dto) {
        return userService.registerUser(dto);
    }
}
