package com.ariguter.marketdemosdk.viewModel;

import android.content.SharedPreferences;
import android.util.Log;

import com.ariguter.marketdemosdk.App;
import com.ariguter.marketsdk.DTO.MarketDTO;
import com.ariguter.marketsdk.MarketSDK;
import com.ariguter.marketsdk.callbacks.Callback;

import java.util.ArrayList;
import java.util.List;

public class LandingPageViewModel {
    private MarketSDK marketSDK = MarketSDK.getInstance();

    public LandingPageViewModel() {
        marketSDK.getMarketService().getAllMarkets(new Callback<List<MarketDTO>>() {
            @Override
            public void onSuccess(List<MarketDTO> data) {
                App.getAppContext()
                    .getSharedPreferences("MarketPreferences", android.content.Context.MODE_PRIVATE)
                    .edit()
                    .putString("selectedMarket", data.get(0).getMarketId())
                    .apply();
                Log.d("LandingPageViewModel", "Markets fetched successfully: " + data.get(0));
            }
            @Override
            public void onError(String error) {
                throw new RuntimeException("Error fetching markets: " + error);
            }
        });

    }
}