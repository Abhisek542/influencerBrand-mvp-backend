package com.abhi.influencermvp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "campaign")
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Brand Name is required")
    private String brandName;
    @NotBlank(message = "Budget is required")
    private String budget;
    @NotBlank(message = "Niche is required")
    private String niche;
    @NotBlank(message = "Deadline is required")
    private String deadline;


    @Column(name="created_by")
    private String createdBy;

}
