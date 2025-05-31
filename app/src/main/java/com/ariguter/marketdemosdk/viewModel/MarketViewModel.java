package com.ariguter.marketdemosdk.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariguter.marketsdk.DTO.PostDTO;
import com.ariguter.marketsdk.MarketSDK;

import java.util.List;

public class MarketViewModel extends ViewModel {
    private final MutableLiveData<List<PostDTO>> posts = new MutableLiveData<>();

    public LiveData<List<PostDTO>> getPosts() {
        return posts;
    }

    public void loadPosts() {
        // Example: synchronous fetch, replace with async if needed
        List<PostDTO> data = repository.getPosts();
        posts.setValue(data);
    }
}