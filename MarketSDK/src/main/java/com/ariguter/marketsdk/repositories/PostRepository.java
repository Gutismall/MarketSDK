package com.ariguter.marketsdk.repositories;

import androidx.annotation.NonNull;
import com.ariguter.marketsdk.MarketAPI;
import com.ariguter.marketsdk.DTO.PostDTO;
import com.ariguter.marketsdk.callbacks.Callback;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class PostRepository {

    private final MarketAPI api;
    private final String appId;

    public PostRepository(MarketAPI api, String appId) {
        this.api = api;
        this.appId = appId;
    }

    public List<PostDTO> getAllPosts(String marketId, Callback<List<PostDTO>> callback) {
        api.getAllPosts(appId, marketId).enqueue(new retrofit2.Callback<List<PostDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostDTO>> call, @NonNull Response<List<PostDTO>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<PostDTO>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
        return null;
    }

    public PostDTO getPost(String marketId, String postId, Callback<PostDTO> callback) {
        api.getPost(appId, marketId, postId).enqueue(new retrofit2.Callback<PostDTO>() {
            @Override
            public void onResponse(@NonNull Call<PostDTO> call, @NonNull Response<PostDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<PostDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
        return null;
    }

    public void createPost(String marketId, PostDTO dto, Callback<PostDTO> callback) {
        api.createPost(appId, marketId, dto).enqueue(new retrofit2.Callback<PostDTO>() {
            @Override
            public void onResponse(@NonNull Call<PostDTO> call, @NonNull Response<PostDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<PostDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public Response deletePost(String marketId, String postId, Callback<String> callback) {
        api.deletePost(appId, marketId, postId).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess("Post deleted successfully");
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
        return null;
    }

    public void updatePost(String marketId, String postId, PostDTO dto, Callback<PostDTO> callback) {
        api.updatePost(appId, marketId, postId, dto).enqueue(new retrofit2.Callback<PostDTO>() {
            @Override
            public void onResponse(@NonNull Call<PostDTO> call, @NonNull Response<PostDTO> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<PostDTO> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void addPostToCategories(String marketId, String postId, List<String> categoryIds, Callback<Void> callback) {
        api.addPostToCategories(appId, marketId, postId, categoryIds).enqueue(new retrofit2.Callback<Void>() {
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