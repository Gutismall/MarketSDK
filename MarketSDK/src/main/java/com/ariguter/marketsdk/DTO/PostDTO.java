package com.ariguter.marketsdk.DTO;


import java.util.*;


public class PostDTO {

    private String postId;
    private String title;
    private String description;
    private double price;
    private List<String> images;
    private String userID;
    private List<String> categoryIds;
    private String marketId;
    private String country;
    private String city;
    private Date createdAt;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PostDTO(String postId, String title, String description, double price, List<String> images, String userID, List<String> categoryIds, String marketId, String country, String city, Date createdAt) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.images = images;
        this.userID = userID;
        this.categoryIds = categoryIds;
        this.marketId = marketId;
        this.country = country;
        this.city = city;
        this.createdAt = createdAt;
    }
}
