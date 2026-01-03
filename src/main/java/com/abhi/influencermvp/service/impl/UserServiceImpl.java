package com.abhi.influencermvp.service.impl;
import com.abhi.influencermvp.config.JwtUtil;
import com.abhi.influencermvp.entity.Campaign;
import com.abhi.influencermvp.entity.User;
import com.abhi.influencermvp.dto.UserDto;

import com.abhi.influencermvp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    public  String registerUser(UserDto dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            return "Email Already Exists";
        }
      else {

          User user = new User();
          user.setName(dto.getName());
          user.setEmail(dto.getEmail());

        /*  String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());*/
          user.setPassword(dto.getPassword());
          user.setRole(dto.getRole());

          userRepository.save(user);
          return "User Registered Successfully";
        }
    }

    @Override
    public  String loginUser(UserDto dto){

        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if(user.isPresent()){
            if(dto.getPassword().equals(user.get().getPassword())){

                String token = jwtUtil.generateToken(dto.getEmail(),user.get().getRole());
                System.out.println(token);
                return "User Logged Successfully. Token: "+ token;
            }
            else {
                return "Wrong Password";
            }
        }
        return "Email does not exist";

    }


}
