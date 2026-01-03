package com.abhi.influencermvp.service.impl;

import com.abhi.influencermvp.dto.UserDto;

public interface UserService {

    String registerUser(UserDto dto);
    String loginUser(UserDto dto);


}
