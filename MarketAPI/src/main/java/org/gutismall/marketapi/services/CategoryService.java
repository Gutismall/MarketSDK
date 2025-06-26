package org.gutismall.marketapi.services;

import lombok.*;
import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.gutismall.marketapi.utility.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.logging.*;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final Validator validator;
    private final Logger logger = Logger.getLogger(CategoryService.class.getName());
    private final PostConverter postConverter;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories(String appId, String marketId) {
        validator.requireExistingMarket(appId,marketId);
        logger.info("Fetching all categories for marketId: " + marketId);
        return categoryRepository.getAllByMarketMarketId(marketId)
                .stream()
                .map(categoryConverter::toDTO)
                .toList();
    }
    
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(String appId, String marketId,String categoryId) {
        validator.requireExistingMarket(appId,marketId);
        validator.requireExistingCategories(categoryId);
        
        logger.info("Fetching category with ID: " + categoryId + " for marketId: " + marketId);
        return categoryConverter.toDTO(categoryRepository.getByCategoryId(categoryId));
    }
    @Transactional(readOnly = false)
    public CategoryDTO createCategory(String appId, String marketId, CategoryDTO categoryDTO) {
        validator.requireExistingMarket(appId,marketId);
        
        logger.info("Creating new category for marketId: " + marketId);
        categoryDTO.setCategoryId(UUID.randomUUID().toString());
        categoryDTO.setMarketId(marketId);
        categoryDTO.setPostsIds(new ArrayList<>());
        CategoryEntity categoryEntity = categoryConverter.toEntity(categoryDTO);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryConverter.toDTO(categoryEntity);
    }
    
    @Transactional(readOnly = false)
    public CategoryDTO updateCategory(String appId, String marketId, String categoryId, CategoryDTO updatedCategory) {
        validator.requireExistingMarket(appId,marketId);
        validator.requireExistingCategories(categoryId);
        
        logger.info("Updating category with ID: " + categoryId + " for marketId: " + marketId);
        CategoryEntity existingCategory = categoryRepository.getByCategoryId(categoryId);
        existingCategory.setName(updatedCategory.getName());
        categoryRepository.save(existingCategory);
        return categoryConverter.toDTO(existingCategory);
        
    }
    @Transactional(readOnly = false)
    public void deleteCategory(String appId, String marketId, String categoryId) {
        validator.requireExistingMarket(appId,marketId);
        validator.requireExistingCategories(categoryId);
        
        logger.info("Deleting category with ID: " + categoryId + " for marketId: " + marketId);
        categoryRepository.deleteByCategoryId(categoryId);
        
    }
    
    public Map<String, String> getCategoryNames(String appId, String marketId) {
        validator.requireExistingMarket(appId, marketId);
        logger.info("Fetching category names for marketId: " + marketId);
        
        List<CategoryEntity> categories = categoryRepository.getCategoryEntitiesByMarket_AppIdAndMarket_MarketId(appId,marketId);
        Map<String, String> categoryNames = new HashMap<>();
        
        for (CategoryEntity category : categories) {
            categoryNames.put(category.getCategoryId(), category.getName());
        }
        return categoryNames;
    }

    public List<PostDTO> getCategoryPosts(String appId, String marketId, String categoryId) {
        validator.requireExistingMarket(appId,marketId);
        validator.requireExistingCategories(categoryId);
        logger.info("Fetching category :" + categoryId +" posts");

        CategoryEntity categoryEntity = categoryRepository.getByCategoryId(categoryId);
        List<PostEntity> posts = categoryEntity.getPosts();
        return posts.stream().map(postConverter::toDTO).toList();

    }
}
