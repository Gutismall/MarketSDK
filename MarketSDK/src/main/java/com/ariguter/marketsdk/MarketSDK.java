package com.ariguter.marketsdk;

import com.ariguter.marketsdk.repositories.CategoryRepository;
import com.ariguter.marketsdk.repositories.MarketRepository;
import com.ariguter.marketsdk.repositories.PostRepository;
import com.ariguter.marketsdk.service.CategoryService;
import com.ariguter.marketsdk.service.MarketService;
import com.ariguter.marketsdk.service.PostService;

public class MarketSDK {
    private static MarketSDK instance;

    private final PostService postService;
    private final MarketService marketService;
    private final CategoryService categoryService;

    public MarketSDK(String appId) {
        String baseUrl = "https://api.ariguter.com/market/" + appId + "/";
        RetrofitClient retrofitClient = new RetrofitClient(baseUrl);
        MarketAPI api = retrofitClient.getMarketAPI();

        this.postService = new PostService(new PostRepository(api, appId));
        this.marketService = new MarketService(new MarketRepository(api, appId));
        this.categoryService = new CategoryService(new CategoryRepository(api, appId));
    }
    public static synchronized void initialize(String appId) {
        if (instance == null) {
            instance = new MarketSDK(appId);
        }
    }
    public static MarketSDK getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MarketSDK is not initialized. Call initialize() first.");
        }
        return instance;
    }

    public PostService getPostService() {
        return postService;
    }

    public MarketService getMarketService() {
        return marketService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }
}