package com.ariguter.marketdemosdk;

import android.app.Application;
import android.content.Context;

import com.ariguter.marketsdk.MarketSDK;

public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MarketSDK.initialize(getPackageName());

    }
    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

}
