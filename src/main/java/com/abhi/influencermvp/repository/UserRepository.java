package com.abhi.influencermvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.influencermvp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


 boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
