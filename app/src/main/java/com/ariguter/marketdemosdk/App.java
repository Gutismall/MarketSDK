package com.ariguter.marketdemosdk;

import android.app.Application;

import com.ariguter.marketsdk.MarketSDK;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String appId = getPackageName();
//        MarketSDK.initialize(appId);
    }

}
