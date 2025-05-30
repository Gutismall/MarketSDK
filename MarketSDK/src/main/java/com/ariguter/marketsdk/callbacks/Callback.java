package com.ariguter.marketsdk.callbacks;


public interface Callback<T> {
    void onSuccess(T data);
    void onError(String error);
}
