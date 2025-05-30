package com.ariguter.marketsdk;

import com.ariguter.marketsdk.DTO.CategoryDTO;
import com.ariguter.marketsdk.DTO.MarketDTO;
import com.ariguter.marketsdk.DTO.PostDTO;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface MarketAPI {

    // MarketController Endpoints
    @GET("/api/{appId}")
    Call<List<MarketDTO>> getAllMarkets(@Path("appId") String appId);

    @GET("/api/{appId}/{marketId}")
    Call<MarketDTO> getMarket(@Path("appId") String appId, @Path("marketId") String marketId);

    @POST("/api/{appId}")
    Call<MarketDTO> createMarket(@Path("appId") String appId, @Body MarketDTO market);

    @PUT("/api/{appId}/{marketId}")
    Call<MarketDTO> updateMarket(@Path("appId") String appId, @Path("marketId") String marketId, @Body MarketDTO market);

    @DELETE("/api/{appId}/{marketId}")
    Call<Void> deleteMarket(@Path("appId") String appId, @Path("marketId") String marketId);

    // CategoryController Endpoints
    @GET("/api/{appId}/{marketId}/category")
    Call<List<CategoryDTO>> getAllCategories(@Path("appId") String appId, @Path("marketId") String marketId);

    @GET("/api/{appId}/{marketId}/category/{categoryId}")
    Call<CategoryDTO> getCategory(@Path("appId") String appId, @Path("marketId") String marketId, @Path("categoryId") String categoryId);

    @POST("/api/{appId}/{marketId}/category")
    Call<CategoryDTO> createCategory(@Path("appId") String appId, @Path("marketId") String marketId, @Body CategoryDTO category);

    @PUT("/api/{appId}/{marketId}/category/{categoryId}")
    Call<CategoryDTO> updateCategory(@Path("appId") String appId, @Path("marketId") String marketId, @Path("categoryId") String categoryId, @Body CategoryDTO category);

    @DELETE("/api/{appId}/{marketId}/category/{categoryId}")
    Call<Void> deleteCategory(@Path("appId") String appId, @Path("marketId") String marketId, @Path("categoryId") String categoryId);

    // PostController Endpoints
    @GET("/api/{appId}/markets/{marketId}/posts")
    Call<List<PostDTO>> getAllPosts(@Path("appId") String appId, @Path("marketId") String marketId);

    @GET("/api/{appId}/markets/{marketId}/posts/{postId}")
    Call<PostDTO> getPost(@Path("appId") String appId, @Path("marketId") String marketId, @Path("postId") String postId);

    @POST("/api/{appId}/markets/{marketId}/posts")
    Call<PostDTO> createPost(@Path("appId") String appId, @Path("marketId") String marketId, @Body PostDTO post);

    @DELETE("/api/{appId}/markets/{marketId}/posts/{postId}")
    Call<Void> deletePost(@Path("appId") String appId, @Path("marketId") String marketId, @Path("postId") String postId);

    @POST("/api/{appId}/markets/{marketId}/posts/{postId}")
    Call<PostDTO> updatePost(@Path("appId") String appId, @Path("marketId") String marketId, @Path("postId") String postId, @Body PostDTO post);

    @POST("/api/{appId}/markets/{marketId}/posts/{postId}/categories")
    Call<Void> addPostToCategories(@Path("appId") String appId, @Path("marketId") String marketId, @Path("postId") String postId, @Body List<String> categoryIds);
}
