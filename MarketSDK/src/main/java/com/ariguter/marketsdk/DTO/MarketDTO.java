package com.ariguter.marketsdk.DTO;

import androidx.annotation.NonNull;

import java.util.List;

public class MarketDTO {
    private String marketId;
    private String appId;
    private String name;
    private List<String> categoriesIds;

    @NonNull
    @Override
    public String toString() {
        return "MarketDTO{" +
                "marketId='" + marketId + '\'' +
                ", appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", categoriesIds=" + categoriesIds +
                ", postIds=" + postIds +
                '}';
    }

    public MarketDTO(String marketId, String appId, String name, List<String> categoriesIds, List<String> postIds) {
        this.marketId = marketId;
        this.appId = appId;
        this.name = name;
        this.categoriesIds = categoriesIds;
        this.postIds = postIds;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategoriesIds() {
        return categoriesIds;
    }

    public void setCategoriesIds(List<String> categoriesIds) {
        this.categoriesIds = categoriesIds;
    }

    public List<String> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<String> postIds) {
        this.postIds = postIds;
    }

    private List<String> postIds;
}
