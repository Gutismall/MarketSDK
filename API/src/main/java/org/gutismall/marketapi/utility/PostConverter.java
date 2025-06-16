package org.gutismall.marketapi.utility;

import org.gutismall.marketapi.DTO.PostDTO;
import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    
    public PostConverter(MarketRepository marketRepository, CategoryRepository categoryRepository, PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.marketRepository = marketRepository;
        this.postRepository = postRepository;
    }
    
    public PostDTO toDTO(PostEntity entity) {
        PostDTO dto = new PostDTO();
        
        dto.setPostId(entity.getPostId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setImages(entity.getImages());
        dto.setUserID(entity.getUserID());
        
        dto.setMarketId(entity.getMarket().getMarketId());
        
        dto.setCategoryId(entity.getCategory().getCategoryId());
        return dto;
    }
    
    public PostEntity toEntity(PostDTO dto) {
        PostEntity entity = new PostEntity();
        
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setDescription(dto.getDescription());
        entity.setPostId(dto.getPostId());
        entity.setPrice(dto.getPrice());
        entity.setImages(dto.getImages());
        entity.setTitle(dto.getTitle());
        entity.setUserID(dto.getUserID());
        
        if (!dto.getCategoryId().isEmpty()){
            entity.setCategory(categoryRepository.getByCategoryId(dto.getCategoryId()));
        }
        if (!dto.getCategoryId().isEmpty()){
            entity.setMarket(marketRepository.getByMarketId(dto.getMarketId()));
        }
        
        
        return entity;
    }
}