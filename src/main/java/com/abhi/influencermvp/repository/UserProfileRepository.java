package com.abhi.influencermvp.repository;


import com.abhi.influencermvp.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByUserId(Integer userId);
}
