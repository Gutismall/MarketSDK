package com.ariguter.myapplication;

import android.app.Application;

import com.ariguter.marketsdk.MarketSDK;

public class app extends Application {
    public app(){
        super();
        MarketSDK.initialize(getPackageName());
    }

}
