package com.ariguter.marketdemosdk.viewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ariguter.marketdemosdk.App;
import com.ariguter.marketsdk.DTO.PostDTO;
import com.ariguter.marketsdk.MarketSDK;
import com.ariguter.marketsdk.callbacks.Callback;

import java.util.List;

public class ProfileViewModel {

    private String email;
    private String username;
    private String phoneNumber;

    private final MutableLiveData<List<PostDTO>> userPosts = new MutableLiveData<>();
    private final MarketSDK api = MarketSDK.getInstance();
    private String marketId = App.getAppContext().getSharedPreferences("MarketPreferences", Context.MODE_PRIVATE).getString("selectedMarket", "");

    public ProfileViewModel() {
        SharedPreferences prefs = App.getAppContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        this.username = prefs.getString("userId", "");
        this.email = prefs.getString("userEmail", "");
        this.phoneNumber = prefs.getString("userPhoneNumber", "");
        Log.d("ProfileViewModel", "Username: " + username + ", Email: " + email + ", Phone: " + phoneNumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LiveData<List<PostDTO>> getUserPosts() {
       api.getPostService().getPostByUser(this.marketId, this.username, new Callback<List<PostDTO>>() {
           @Override
           public void onSuccess(List<PostDTO> data) {
               userPosts.postValue(data);
           }

           @Override
           public void onError(String error) {

           }
       });
       return userPosts;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
