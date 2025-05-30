package com.ariguter.marketsdk.service;

import static com.ariguter.marketsdk.util.MarketValidator.validate;

import com.ariguter.marketsdk.DTO.MarketDTO;
import com.ariguter.marketsdk.callbacks.Callback;
import com.ariguter.marketsdk.repositories.MarketRepository;
import java.util.List;

public class MarketService {

    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public void getAllMarkets(Callback<List<MarketDTO>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        marketRepository.getAllMarkets(callback);
    }

    public void getMarket(String marketId, Callback<MarketDTO> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        marketRepository.getMarket(marketId, callback);
    }

    public void createMarket(MarketDTO dto, Callback<MarketDTO> callback) {
        validate(dto);
        marketRepository.createMarket(dto, callback);
    }

    public void updateMarket(String marketId, MarketDTO dto, Callback<MarketDTO> callback) {
        validate(dto);
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        marketRepository.updateMarket(marketId, dto, callback);
    }

    public void deleteMarket(String marketId, Callback<Void> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        marketRepository.deleteMarket(marketId, callback);
    }
}