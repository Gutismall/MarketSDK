package com.ariguter.marketsdk;

import com.ariguter.marketsdk.repositories.CategoryRepository;
import com.ariguter.marketsdk.repositories.MarketRepository;
import com.ariguter.marketsdk.repositories.PostRepository;

public class MarketSDK {

    private final PostRepository postRepository;
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;

    public MarketSDK(String appId) {
        String baseUrl = "https://api.ariguter.com/market/" + appId + "/";
        RetrofitClient retrofitClient = new RetrofitClient(baseUrl);
        MarketAPI api = retrofitClient.getMarketAPI();

        this.postRepository = new PostRepository(api,appId);
        this.marketRepository = new MarketRepository(api, appId);
        this.categoryRepository = new CategoryRepository(api, appId);
    }

    public MarketRepository getMarketRepository() {
        return marketRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }
}