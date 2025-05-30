package com.ariguter.marketsdk.service;

import static com.ariguter.marketsdk.util.CategoryValidator.validate;

import com.ariguter.marketsdk.DTO.CategoryDTO;
import com.ariguter.marketsdk.callbacks.Callback;
import com.ariguter.marketsdk.repositories.CategoryRepository;
import com.ariguter.marketsdk.util.CategoryValidator;

import java.util.List;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories(String marketId, Callback<List<CategoryDTO>> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return null;
        }
        return categoryRepository.getAllCategories(marketId, callback);
    }

    public CategoryDTO getCategory(String marketId, String categoryId, Callback<CategoryDTO> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return null;
        }
        if (categoryId == null || categoryId.trim().isEmpty()) {
            callback.onError("Category ID cannot be null or empty");
            return null;
        }
        return categoryRepository.getCategory(marketId, categoryId, callback);
    }

    public void createCategory(String marketId, CategoryDTO dto, Callback<CategoryDTO> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        try {
            validate(dto);
        } catch (IllegalArgumentException e) {
            callback.onError(e.getMessage());
            return;
        }
        categoryRepository.createCategory(marketId, dto, callback);
    }

    public void updateCategory(String marketId, String categoryId, CategoryDTO dto, Callback<CategoryDTO> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        if (categoryId == null || categoryId.trim().isEmpty()) {
            callback.onError("Category ID cannot be null or empty");
            return;
        }
        try {
            validate(dto);
        } catch (IllegalArgumentException e) {
            callback.onError(e.getMessage());
            return;
        }
        categoryRepository.updateCategory(marketId, categoryId, dto, callback);
    }

    public void deleteCategory(String marketId, String categoryId, Callback<Void> callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        if (marketId == null || marketId.trim().isEmpty()) {
            callback.onError("Market ID cannot be null or empty");
            return;
        }
        if (categoryId == null || categoryId.trim().isEmpty()) {
            callback.onError("Category ID cannot be null or empty");
            return;
        }
        categoryRepository.deleteCategory(marketId, categoryId, callback);
    }
}