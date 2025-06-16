package org.gutismall.marketapi.utility;


import lombok.*;
import org.gutismall.marketapi.repository.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;
import java.util.logging.*;

@Component
@AllArgsConstructor
public class Validator {
    private final MarketRepository marketRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final Logger logger = Logger.getLogger(Validator.class.getName());
    
    public void requireExistingMarket(String appId, String marketId) {
        if (!marketRepository.existsMarketEntitiesByAppIdAndMarketId(appId, marketId)){
            logger.warning("Market not found for appId: " + appId + " and marketId: " + marketId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Market not found for appId: " + appId + " and marketId: " + marketId);
        }
    }
    public void requireExistingCategories(String categoryId){
        if (!categoryRepository.existsCategoryEntityByCategoryId(categoryId)){
            logger.warning("Category not found for IDs: " + categoryId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Categories not found ");
        }
    }
    public void requireExistingPost(List<String> PostIds){
        if (!postRepository.existsAllByPostIdIn(PostIds)){
            logger.warning("Posts not found for IDs: " + PostIds);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Categories not found ");
        }
    }
}
