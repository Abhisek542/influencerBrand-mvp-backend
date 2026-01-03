package com.abhi.influencermvp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "Userprofile")
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bio;
    private String  SocialLinks;
    private String Location;
    private String profilePicture;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


}
