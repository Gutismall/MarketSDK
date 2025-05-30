package com.ariguter.marketsdk.util;

import com.ariguter.marketsdk.DTO.CategoryDTO;

public class CategoryValidator {
    public static void validate(CategoryDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CategoryDTO cannot be null");
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (dto.getMarketId() == null || dto.getMarketId().trim().isEmpty()) {
            throw new IllegalArgumentException("Market ID cannot be null or empty");
        }

    }
}