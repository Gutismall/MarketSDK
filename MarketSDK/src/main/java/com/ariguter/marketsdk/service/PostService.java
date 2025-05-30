package com.ariguter.marketsdk.service;

import static com.ariguter.marketsdk.util.PostValidator.validate;

import com.ariguter.marketsdk.DTO.PostDTO;
import com.ariguter.marketsdk.callbacks.Callback;
import com.ariguter.marketsdk.repositories.PostRepository;
import com.ariguter.marketsdk.util.PostValidator;

import java.util.List;

public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, PostValidator postValidator) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getAllPosts(String marketId, Callback<List<PostDTO>> callback) {
        if (marketId == null || marketId.isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return null;
        }
        return postRepository.getAllPosts(marketId, callback);
    }

    public PostDTO getPost(String marketId, String postId, Callback<PostDTO> callback) {
        if (marketId == null || marketId.isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return null;
        }
        if (postId == null || postId.isEmpty()) {
            callback.onError("Post ID cannot be null or empty");
            return null;
        }
        return postRepository.getPost(marketId, postId, callback);
    }

    public void createPost(String marketId, PostDTO dto, Callback<PostDTO> callback) {
        try {
            validate(dto);
            postRepository.createPost(marketId, dto, callback);
        } catch (IllegalArgumentException e) {
            callback.onError(e.getMessage());
        }
    }

    public void deletePost(String marketId, String postId, Callback<String> callback) {
        if (marketId == null || marketId.isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        if (postId == null || postId.isEmpty()) {
            callback.onError("Post ID cannot be null or empty");
            return;
        }
        postRepository.deletePost(marketId, postId, callback);
    }

    public void updatePost(String marketId, String postId, PostDTO dto, Callback<PostDTO> callback) {
        try {
            validate(dto);
            postRepository.updatePost(marketId, postId, dto, callback);
        } catch (IllegalArgumentException e) {
            callback.onError(e.getMessage());
        }
    }

    public void addPostToCategories(String marketId, String postId, List<String> categoryIds, Callback<Void> callback) {
        if (categoryIds.isEmpty()){
            callback.onError("Category IDs cannot be empty");
            return;
        }
        if( marketId == null || marketId.isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        if( postId == null || postId.isEmpty()) {
            callback.onError("Post ID cannot be null or empty");
            return;
        }
        postRepository.addPostToCategories(marketId, postId, categoryIds, callback);
    }
}