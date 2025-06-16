package org.gutismall.marketapi.repository;

import org.gutismall.marketapi.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> getAllByMarket_MarketId(String marketMarketId);
    
    List<PostEntity> getAllByMarket_MarketIdAndMarket_AppId(String marketMarketId, String marketAppId);
    
    PostEntity getPostEntityByPostIdAndMarket_MarketIdAndMarket_AppId(String postId, String marketId, String appId);
    
    void deleteByMarket_MarketIdAndMarket_AppIdAndPostId(String marketMarketId, String marketAppId, String postId);
    
    List<PostEntity> getAllByPostIdIn(Collection<String> postIds);
    
    boolean existsAllByPostIdIn(Collection<String> postIds);

    List<PostEntity> getAllByMarket_AppIdAndMarket_MarketIdAndUserID(String marketAppId, String marketMarketId, String userID);
    
    List<PostEntity> getPostEntitiesByMarket_AppIdAndMarket_MarketIdAndCategory_CategoryId(String marketAppId, String marketMarketId, String categoryCategoryId);
}
