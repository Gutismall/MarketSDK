package com.ariguter.marketdemosdk.viewModel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariguter.marketdemosdk.App;
import com.ariguter.marketsdk.DTO.CategoryDTO;
import com.ariguter.marketsdk.DTO.MarketDTO;
import com.ariguter.marketsdk.DTO.PostDTO;
import com.ariguter.marketsdk.MarketSDK;
import com.ariguter.marketsdk.callbacks.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarketViewModel extends ViewModel {
    private final MutableLiveData<List<PostDTO>> posts = new MutableLiveData<>();
    private final MarketSDK marketSDK= MarketSDK.getInstance();
    private final SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences("MarketPreferences", Context.MODE_PRIVATE);;
    private final String marketId = sharedPreferences.getString("selectedMarket", null);
    private final MutableLiveData<Map<String, String>> categoryNames = new MutableLiveData<>();


    public MarketViewModel() {

    }
    public LiveData<Map<String,String>> getAllCategoriesNames(){

        if (marketId != null) {
            marketSDK.getCategoryService().getCategoryByName(marketId, new Callback<Map<String,String>>() {

                @Override
                public void onSuccess(Map<String, String> data) {
                    categoryNames.postValue(data);
                }

                @Override
                public void onError(String error) {

                }
            });
        }
        return categoryNames;
    }

    public LiveData<List<PostDTO>> getAllMarketPosts() {
        String savedMarketId = sharedPreferences.getString("selectedMarket", null);
        if (savedMarketId != null) {
            marketSDK.getPostService().getAllPosts(savedMarketId, new Callback<List<PostDTO>>() {
                @Override
                public void onSuccess(List<PostDTO> postDTOs) {
                    posts.postValue(postDTOs);
                }

                @Override
                public void onError(String error) {
                    posts.postValue(new ArrayList<>());
                }
            });
        }
        return posts;
    }

    public LiveData<List<PostDTO>> getPostsByCategory(String selectedCategoryName) {
        String categoryId = null;
        if (categoryNames.getValue() != null) {
            for (Map.Entry<String, String> entry : categoryNames.getValue().entrySet()) {
                if (entry.getValue().equals(selectedCategoryName)) {
                    categoryId = entry.getKey();
                }
            }
        }
        if (categoryId != null) {
            String savedMarketId = sharedPreferences.getString("selectedMarket", null);
            if (savedMarketId != null) {
                marketSDK.getCategoryService().getCategoryPosts(savedMarketId, categoryId, new Callback<List<PostDTO>>() {
                    @Override
                    public void onSuccess(List<PostDTO> postDTOs) {
                        posts.postValue(postDTOs);
                    }

                    @Override
                    public void onError(String error) {
                        posts.postValue(new ArrayList<>());
                    }
                });
            }
        } else {
            posts.postValue(new ArrayList<>());
        }
        return posts;
    }
}