package com.ariguter.marketsdk;

import java.util.List;

import lombok.*;

@Data
public class MarketDTO {
    private String marketId;
    private String appId;
    private String name;
    private List<String> categoriesIds;
    private List<String> postIds;
}
