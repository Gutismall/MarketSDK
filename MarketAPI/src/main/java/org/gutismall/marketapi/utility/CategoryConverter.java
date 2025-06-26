package org.gutismall.marketapi.utility;

import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.springframework.stereotype.*;

@Component
public class CategoryConverter {
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    
    public CategoryConverter(MarketRepository marketRepository, CategoryRepository categoryRepository, PostRepository postRepository) {
        this.marketRepository = marketRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
    }
    
    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        if (categoryEntity.getName() != null){
            categoryDTO.setName(categoryEntity.getName());
        }
        if (categoryEntity.getMarket() != null){
            categoryDTO.setMarketId(categoryEntity.getMarket().getMarketId());
        }
        if (categoryEntity.getCategoryId() != null){
            categoryDTO.setCategoryId(categoryEntity.getCategoryId());
        }
        if (categoryEntity.getPosts() != null){
            categoryDTO.setPostsIds(categoryEntity.getPosts()
                    .stream()
                    .map(PostEntity::getPostId)
                    .toList());
        }
        return categoryDTO;
    }
    public CategoryEntity toEntity (CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = new CategoryEntity();
        if (categoryDTO.getCategoryId() != null){
            categoryEntity.setCategoryId(categoryDTO.getCategoryId());
        }
        if (categoryDTO.getMarketId() != null){
            categoryEntity.setMarket(marketRepository.getByMarketId(categoryDTO.getMarketId()));
        }
        if (categoryDTO.getName() != null){
            categoryEntity.setName(categoryDTO.getName());
        }
        if (categoryDTO.getPostsIds() != null){
            categoryEntity.setPosts(postRepository.getAllByPostIdIn(categoryDTO.getPostsIds()));
        }
        return categoryEntity;
    }
}
