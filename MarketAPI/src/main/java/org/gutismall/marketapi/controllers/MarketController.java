package org.gutismall.marketapi.controllers;

import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.MarketEntity;
import org.gutismall.marketapi.services.MarketService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{appId}")
public class MarketController {
    private final MarketService marketService;
    
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping(path = "/hello")
    public String getHello(@PathVariable String appId) {
        return "Hello World";
    }

    @GetMapping(path = "/markets",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MarketDTO> getAllMarkets(
            @PathVariable String appId)
    {
        return marketService.getAllMarkets(appId);
    }
    
    @GetMapping(
            path = "/{marketId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MarketDTO getMarketById(
            @PathVariable String marketId,
            @PathVariable String appId)
    {
        return marketService.getMarketById(appId,marketId);
    }
    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MarketDTO createMarket(
            @RequestBody MarketDTO marketDTO,
            @PathVariable String appId)
    {
        return marketService.createMarket(appId,marketDTO);
    }
    
    @PutMapping("/{marketId}")
    public void updateMarket(
            @PathVariable String marketId,
            @RequestBody MarketDTO updatedMarket,
            @PathVariable String appId)
    {
       marketService.updateMarket(appId,marketId,updatedMarket);
    }
    
    @DeleteMapping("/{marketId}")
    public void deleteMarket(
            @PathVariable String marketId,
            @PathVariable String appId)
    {
        marketService.deleteMarket(appId,marketId);
    }
}