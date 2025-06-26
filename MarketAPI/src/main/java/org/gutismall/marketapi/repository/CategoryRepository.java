package org.gutismall.marketapi.repository;

import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
    
    List<CategoryEntity> getAllByMarketMarketId(String marketMarketId);
    
    List<CategoryEntity> getAllByCategoryIdIn(List<String> categoryIds);
    
    CategoryEntity getByCategoryId(String categoryId);
    
    void deleteByCategoryId(String categoryId);
    
    boolean existsCategoryEntityByCategoryId(String categoryId);
    
    List<CategoryEntity> getCategoryEntitiesByMarket_AppIdAndMarket_MarketId(String marketAppId, String marketMarketId);
}
