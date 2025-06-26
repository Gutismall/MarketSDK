package com.ariguter.marketdemosdk.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ariguter.marketsdk.DTO.CategoryDTO;
import com.ariguter.marketsdk.DTO.PostDTO;
import com.ariguter.marketsdk.MarketSDK;
import com.ariguter.marketsdk.callbacks.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateNewPostViewModel extends AndroidViewModel {

    private final MarketSDK marketSDK = MarketSDK.getInstance();
    private final MutableLiveData<Map<String, String>> categoryNames = new MutableLiveData<>(new HashMap<>());
    private final MutableLiveData<Boolean> postCreated = new MutableLiveData<>();
    private String marketId;

    public CreateNewPostViewModel(@NonNull Application application) {
        super(application);
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public LiveData<Boolean> getPostCreated() {
        return postCreated;
    }

    public void loadCategories() {
        marketSDK.getCategoryService().getAllCategories(marketId, new Callback<List<CategoryDTO>>() {
            @Override
            public void onSuccess(List<CategoryDTO> data) {
                Map<String, String> map = new HashMap<>();
                for (CategoryDTO category : data) {
                    map.put(category.getCategoryId(), category.getName());
                }
                categoryNames.postValue(map);
            }

            @Override
            public void onError(String error) {
                categoryNames.postValue(new HashMap<>());
            }
        });
    }

    public void createPost(String title, String description, double price, String selectedCategoryId, String userId, String country, String city) {
        Log.e("CreateNewPostDialogFragment", "Selected Category: " + selectedCategoryId +
                ", Title: " + title +
                ", Description: " + description +
                ", Price: " + price);
        marketSDK.getPostService().createPost(marketId, new PostDTO(
                "",
                title,
                description,
                price,
                new ArrayList<>(),
                userId,
                selectedCategoryId,
                marketId,
                country,
                city,
                null
        ), new Callback<PostDTO>() {
            @Override
            public void onSuccess(PostDTO data) {
                postCreated.postValue(true);
            }

            @Override
            public void onError(String error) {
                postCreated.postValue(false);
            }
        });
    }
    public LiveData<Map<String,String>> getAllCategoriesNames() {

        if (marketId != null) {
            marketSDK.getCategoryService().getCategoryByName(marketId, new Callback<Map<String, String>>() {

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
}