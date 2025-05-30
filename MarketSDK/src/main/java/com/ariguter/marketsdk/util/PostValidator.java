package com.ariguter.marketsdk.util;

import com.ariguter.marketsdk.DTO.PostDTO;

public class PostValidator {
    public static void validate(PostDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("PostDTO cannot be null");
        }
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (dto.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (dto.getUserID() == null || dto.getUserID().trim().isEmpty()) {
            throw new IllegalArgumentException("UserID is required");
        }
        if (dto.getMarketId() == null || dto.getMarketId().trim().isEmpty()) {
            throw new IllegalArgumentException("MarketId is required");
        }
        if (dto.getCountry() == null || dto.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }
        if (dto.getCity() == null || dto.getCity().trim().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
    }
}