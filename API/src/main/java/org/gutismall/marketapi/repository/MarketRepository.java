package org.gutismall.marketapi.repository;

import org.gutismall.marketapi.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
    
    List<MarketEntity> getAllByAppId(String appId);
    
    MarketEntity getMarketEntityByMarketIdAndAppId(String marketId, String appId);
    
    void deleteMarketEntityByMarketIdAndAppId(String marketId, String appId);
    
    boolean findMarketEntityByAppIdAndMarketId(String appId, String marketId);
    
    MarketEntity getByMarketId(String marketId);
    
    boolean existsMarketEntitiesByAppIdAndMarketId(String appId, String marketId);
}
