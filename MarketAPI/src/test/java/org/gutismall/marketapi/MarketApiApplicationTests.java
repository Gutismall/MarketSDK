package org.gutismall.marketapi;

import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MarketApiApplicationTests {
    
    @Autowired
    private MarketRepository marketRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Test
    public void testCreateDemoData() {
        
        // Create Market
       List<MarketEntity> markets = new ArrayList<>(2);
       for (int i = 0; i < 2; i++) {
           MarketEntity market = new MarketEntity();
           market.setMarketId(UUID.randomUUID().toString());
           market.setName("Demo Market " + (i + 1));
           market.setAppId("com.ariguter.marketsdk");
           marketRepository.save(market);
           markets.add(market);
       }
        // Create Post
        for (int i = 0;i < 10;i++){
            PostEntity post = new PostEntity();
            post.setPostId(UUID.randomUUID().toString());
            post.setTitle("Demo Post");
            post.setDescription("This is a demo post.");
            post.setPrice(10 * i);
            post.setUserID("testUser");
            if (i < 5){
                post.setMarket(markets.getFirst());
            } else {
                post.setMarket(markets.get(1));
            }
            post.setCountry("Demo Country");
            post.setCity("Demo City");
            postRepository.save(post);
        }
        
        // Save to database
        
        // Verify data
        List<PostEntity> posts = postRepository.findAll();
        assertThat(posts).hasSize(10);
        assertThat(posts.get(0).getTitle()).isEqualTo("Demo Post");
    }
}