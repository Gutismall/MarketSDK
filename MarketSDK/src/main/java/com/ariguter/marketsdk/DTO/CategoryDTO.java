package com.ariguter.marketsdk.DTO;


import androidx.annotation.NonNull;

import java.util.*;


public class CategoryDTO {
    private String categoryId;
    private String name;
    private List<String> postsIds;
    private String marketId;

    @NonNull
    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", postsIds=" + postsIds +
                ", marketId='" + marketId + '\'' +
                '}';
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPostsIds() {
        return postsIds;
    }

    public void setPostsIds(List<String> postsIds) {
        this.postsIds = postsIds;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }



    public CategoryDTO(String categoryId, String name, List<String> postsIds, String marketId) {
        this.categoryId = categoryId;
        this.name = name;
        this.postsIds = postsIds;
        this.marketId = marketId;
    }


}
