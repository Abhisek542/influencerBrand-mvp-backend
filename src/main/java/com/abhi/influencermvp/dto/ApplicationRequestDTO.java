package com.abhi.influencermvp.dto;

public record ApplicationRequestDTO(
        String message,
        String influencerName,
        String niche,
        String platform
) {}