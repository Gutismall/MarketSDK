package org.gutismall.marketapi.DTO;

import lombok.*;

import java.util.*;

@Data
public class MarketDTO {
    private String marketId;
    private String appId;
    private String name;
    private List<String> categoriesIds;
    private List<String> postIds;
}
