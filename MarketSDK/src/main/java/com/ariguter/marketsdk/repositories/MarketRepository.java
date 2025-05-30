package com.ariguter.marketsdk.repositories;

import androidx.annotation.NonNull;
import com.ariguter.marketsdk.MarketAPI;
import com.ariguter.marketsdk.DTO.MarketDTO;
import com.ariguter.marketsdk.callbacks.Callback;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class MarketRepository {
    private final MarketAPI api;
    private final String appId;

    public MarketRepository(MarketAPI api, String appId) {
        this.api = api;
        this.appId = appId;
    }

    public void getAllMarkets(Callback<List<MarketDTO>> callback) {
        api.getAllMarkets(appId).enqueue(new retrofit2.Callback<List<MarketDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<MarketDTO>> call, @NonNull Response<List<MarketDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<MarketDTO>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getMarket(String marketId, Callback<MarketDTO> callback) {
        api.getMarket(appId, marketId).enqueue(new retrofit2.Callback<MarketDTO>() {
            @Override
            public void onResponse(@NonNull Call<MarketDTO> call, @NonNull Response<MarketDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MarketDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void createMarket(MarketDTO dto, Callback<MarketDTO> callback) {
        api.createMarket(appId, dto).enqueue(new retrofit2.Callback<MarketDTO>() {
            @Override
            public void onResponse(@NonNull Call<MarketDTO> call, @NonNull Response<MarketDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MarketDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void updateMarket(String marketId, MarketDTO dto, Callback<MarketDTO> callback) {
        api.updateMarket(appId, marketId, dto).enqueue(new retrofit2.Callback<MarketDTO>() {
            @Override
            public void onResponse(@NonNull Call<MarketDTO> call, @NonNull Response<MarketDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MarketDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void deleteMarket(String marketId, Callback<Void> callback) {
        api.deleteMarket(appId, marketId).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}