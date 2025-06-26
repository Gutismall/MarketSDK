package org.gutismall.marketapi.utility;

import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.springframework.stereotype.*;

@Component
public class MarketConverter {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    
    public MarketConverter(PostRepository postRepository, CategoryRepository categoryRepository) {
        
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }
    
    public MarketDTO toDTO(MarketEntity marketEntity) {
        MarketDTO marketDTO = new MarketDTO();
        marketDTO.setName(marketEntity.getName());
        marketDTO.setMarketId(marketEntity.getMarketId());
        marketDTO.setAppId(marketEntity.getAppId());
        
        if (marketEntity.getPosts() != null){
            marketDTO.setPostIds(marketEntity.getPosts()
                    .stream()
                    .map(PostEntity::getPostId)
                    .toList());
        }
        if (marketEntity.getCategories() != null){
            marketDTO.setCategoriesIds(marketEntity.getCategories()
                    .stream()
                    .map(CategoryEntity::getCategoryId)
                    .toList());
        }
        return marketDTO;
    }
    public MarketEntity toEntity(MarketDTO marketDTO) {
        
        MarketEntity marketEntity = new MarketEntity();
        marketEntity.setName(marketDTO.getName());
        marketEntity.setMarketId(marketDTO.getMarketId());
        
        if (!marketDTO.getPostIds().isEmpty()){
            marketEntity.setPosts(postRepository.getAllByMarket_MarketId(marketDTO.getMarketId()));
        }
        if (!marketDTO.getCategoriesIds().isEmpty()){
            marketEntity.setCategories(categoryRepository.getAllByMarketMarketId(marketDTO.getMarketId()));
        }
        
        marketEntity.setAppId(marketDTO.getAppId());
        return marketEntity;
    }
}
