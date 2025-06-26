package org.gutismall.marketapi;

import org.gutismall.marketapi.repository.*;
import org.gutismall.marketapi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private MarketRepository marketRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) {
        List<MarketEntity> markets = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            MarketEntity market = new MarketEntity();
            market.setMarketId(UUID.randomUUID().toString());
            market.setName("Demo Market " + (i + 1));
            market.setAppId("com.ariguter.marketdemosdk");
            marketRepository.save(market);
            markets.add(market);
        }
        
        List<CategoryEntity> categories = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            CategoryEntity category = new CategoryEntity();
            category.setCategoryId(UUID.randomUUID().toString());
            if (i < 2) {
                category.setMarket(markets.get(0));
            } else {
                category.setMarket(markets.get(1));
            }
            category.setName("Demo Category " + (i + 1));
            categoryRepository.save(category);
            categories.add(category);
            
            // Generate posts for the category
            for (int j = 0; j < 3; j++) { // Create 3 posts per category
                PostEntity post = new PostEntity();
                post.setPostId(UUID.randomUUID().toString());
                post.setTitle("Post for " + category.getName() + " - " + (j + 1));
                post.setDescription("This is a post for category " + category.getName() + ".");
                post.setPrice(10.0 * (j + 1));
                post.setUserID("testUser" + random.nextInt(2)); // Assign random test user
                post.setMarket(category.getMarket());
                post.setCountry("Demo Country");
                post.setCity("Demo City");
                post.setCategory(category);
                postRepository.save(post);
            }
        }
        
        // Create User
        UserEntity user = new UserEntity();
        user.setEmail("ari.guter@gmail.com");
        user.setAppId("com.ariguter.marketdemosdk");
        userRepository.save(user);
    }
}