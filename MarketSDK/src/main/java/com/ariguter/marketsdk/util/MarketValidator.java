package com.ariguter.marketsdk.util;

import com.ariguter.marketsdk.DTO.MarketDTO;
import java.util.List;

public class MarketValidator {
    public static void validate(MarketDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("MarketDTO cannot be null");
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Market name cannot be null or empty");
        }
        if (dto.getAppId() == null || dto.getAppId().trim().isEmpty()) {
            throw new IllegalArgumentException("App ID cannot be null or empty");
        }
    }
}