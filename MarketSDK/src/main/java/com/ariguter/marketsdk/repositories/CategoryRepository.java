package com.ariguter.marketsdk.repositories;

import androidx.annotation.NonNull;
import com.ariguter.marketsdk.MarketAPI;
import com.ariguter.marketsdk.DTO.CategoryDTO;
import com.ariguter.marketsdk.callbacks.Callback;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class CategoryRepository {
    private final MarketAPI api;
    private final String appId;

    public CategoryRepository(MarketAPI api, String appId) {
        this.api = api;
        this.appId = appId;
    }

    public List<CategoryDTO> getAllCategories(String marketId, Callback<List<CategoryDTO>> callback) {
        api.getAllCategories(appId, marketId).enqueue(new retrofit2.Callback<List<CategoryDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<CategoryDTO>> call, @NonNull Response<List<CategoryDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<CategoryDTO>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
        return null;
    }

    public CategoryDTO getCategory(String marketId, String categoryId, Callback<CategoryDTO> callback) {
        api.getCategory(appId, marketId, categoryId).enqueue(new retrofit2.Callback<CategoryDTO>() {
            @Override
            public void onResponse(@NonNull Call<CategoryDTO> call, @NonNull Response<CategoryDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<CategoryDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
        return null;
    }

    public void createCategory(String marketId, CategoryDTO dto, Callback<CategoryDTO> callback) {
        api.createCategory(appId, marketId, dto).enqueue(new retrofit2.Callback<CategoryDTO>() {
            @Override
            public void onResponse(@NonNull Call<CategoryDTO> call, @NonNull Response<CategoryDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<CategoryDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void updateCategory(String marketId, String categoryId, CategoryDTO dto, Callback<CategoryDTO> callback) {
        api.updateCategory(appId, marketId, categoryId, dto).enqueue(new retrofit2.Callback<CategoryDTO>() {
            @Override
            public void onResponse(@NonNull Call<CategoryDTO> call, @NonNull Response<CategoryDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<CategoryDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void deleteCategory(String marketId, String categoryId, Callback<Void> callback) {
        api.deleteCategory(appId, marketId, categoryId).enqueue(new retrofit2.Callback<Void>() {
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