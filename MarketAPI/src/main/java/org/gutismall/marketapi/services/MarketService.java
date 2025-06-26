package org.gutismall.marketapi.services;

import lombok.*;
import org.apache.commons.logging.*;
import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.gutismall.marketapi.utility.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
@AllArgsConstructor
public class MarketService {
    
    private final MarketRepository marketRepository;
    private final Log logger = LogFactory.getLog(MarketService.class);
    private final MarketConverter marketConverter;
    private final Validator validator;
    
    @Transactional(readOnly = true)
    public List<MarketDTO> getAllMarkets(String appId) {
        List<MarketEntity> allMarkets = marketRepository.getAllByAppId(appId);
        if(allMarkets.isEmpty()) {
            return Collections.emptyList();
        }
        return allMarkets
                .stream()
                .map(marketConverter::toDTO)
                .toList();
    }
    
    @Transactional(readOnly = true)
    public MarketDTO getMarketById(String appId,String marketId) {
        validator.requireExistingMarket(appId,marketId);
        MarketEntity requestedMarket = marketRepository.getMarketEntityByMarketIdAndAppId(marketId, appId);
        return marketConverter.toDTO(requestedMarket);
    }
    
    @Transactional(readOnly = false)
    public MarketDTO createMarket(String appId,MarketDTO market) {
        market.setMarketId(UUID.randomUUID().toString());
        market.setAppId(appId);
        market.setPostIds(new ArrayList<>());
        market.setCategoriesIds(new ArrayList<>());
        
        MarketEntity marketEntity = marketConverter.toEntity(market);
        marketEntity = marketRepository.save(marketEntity);
        return marketConverter.toDTO(marketEntity);
    }
    
    @Transactional(readOnly = false)
    public void updateMarket(String appId,String marketId,MarketDTO updatedMarket) {
        MarketEntity requestedMarket = marketRepository.getMarketEntityByMarketIdAndAppId(marketId,appId);
        if (requestedMarket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Market not found for appId: " + appId + " and marketId: " + marketId);
        }
        if(requestedMarket.getName() != null){
            requestedMarket.setName(updatedMarket.getName());
        }
        
        marketRepository.save(requestedMarket);
        
    }
    
    @Transactional(readOnly = false)
    public void deleteMarket(String appId,String marketId) {
        marketRepository.deleteMarketEntityByMarketIdAndAppId(marketId, appId);
    }
    
    
}
