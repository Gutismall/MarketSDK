package com.ariguter.marketdemosdk.viewModel;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;

public class MainViewModel {

    private String username;

    public MainViewModel(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(Context context, String username) {
        this.username = username;
        SharedPreferences prefs = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        prefs.edit().putString("userId", username).apply();
        prefs.edit().putString("userEmail", "ari.guter@gmail.com").apply();
        prefs.edit().putString("userPhoneNumber", "0527566301").apply();
    }


}
