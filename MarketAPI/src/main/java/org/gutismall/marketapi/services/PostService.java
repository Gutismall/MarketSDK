package org.gutismall.marketapi.services;

import lombok.*;
import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.gutismall.marketapi.utility.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final CategoryRepository categoryRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MarketRepository marketRepository;
    private final Validator validator;
    
    @Transactional(readOnly = true)
    public List<PostDTO> getAllPosts(String appId,String marketId) {
        validator.requireExistingMarket(appId,marketId);
        List<PostEntity> allPosts = postRepository.getAllByMarket_MarketIdAndMarket_AppId(marketId, appId);
        logger.debug("getAllPosts: allPosts: {}", allPosts);
        if (allPosts.isEmpty()) {
            logger.warn("getAllPosts: No posts found for marketId: {}", marketId);
            return Collections.emptyList();
        }
        return allPosts
                .stream()
                .map(postConverter::toDTO)
                .toList();
    }
    
    @Transactional(readOnly = true)
    public PostDTO getPostById(String appId,String marketId,String postId) {
        validator.requireExistingMarket(appId,marketId);
        PostEntity postEntity = postRepository.getPostEntityByPostIdAndMarket_MarketIdAndMarket_AppId(postId, marketId, appId);
        if (postEntity == null){
            logger.warn("getPostById: postId {} not found in marketId {}", postId, marketId);
            return null;
        }
        return postConverter.toDTO(postEntity);
    }
    
    @Transactional(readOnly = false)
    public PostDTO createPost(String appId, String marketId, PostDTO postDTO) {
        logger.error("createPost: postDTO: {}", postDTO);
        validator.requireExistingMarket(appId,marketId);
        validator.requireExistingCategories(postDTO.getCategoryId());

        logger.info("Creating new post {}", postDTO);
        postDTO.setPostId(UUID.randomUUID().toString());
        postDTO.setCreatedAt(new Date());
        postDTO.setMarketId(marketId);
        
        PostEntity postEntity = postConverter.toEntity(postDTO);
        postRepository.save(postEntity);
        return postConverter.toDTO(postEntity);
    }
    
    @Transactional(readOnly = false)
    public void deletePost(String appId, String marketId, String postId) {
        validator.requireExistingMarket(appId,marketId);
        validator.requireExistingPost(Collections.singletonList(postId));
        postRepository.deleteByMarket_MarketIdAndMarket_AppIdAndPostId(marketId, appId, postId);
    }
    
    @Transactional(readOnly = false)
    public PostDTO updatePost(String appId, String marketId, String postId, PostDTO updatedPost) {
        validator.requireExistingPost(Collections.singletonList(postId));
        PostEntity fetchedPost = postRepository.getPostEntityByPostIdAndMarket_MarketIdAndMarket_AppId(postId, marketId, appId);
        if (fetchedPost == null) {
            logger.warn("updatePost: postId {} not found in marketId {}", postId, marketId);
            return null;
        }
        if (fetchedPost.getCity() != null) {
            fetchedPost.setCity(updatedPost.getCity());
        }
        if (fetchedPost.getCountry() != null) {
            fetchedPost.setCountry(updatedPost.getCountry());
        }
        if (fetchedPost.getDescription() != null) {
            fetchedPost.setDescription(updatedPost.getDescription());
        }
        if (fetchedPost.getImages() != null) {
            fetchedPost.setImages(updatedPost.getImages());
        }
        if (fetchedPost.getTitle() != null) {
            fetchedPost.setTitle(updatedPost.getTitle());
        }
        if (fetchedPost.getPrice() != 0) {
            fetchedPost.setPrice(updatedPost.getPrice());
        }
        postRepository.save(fetchedPost);
        return postConverter.toDTO(fetchedPost);
    }

    public List<PostDTO> getPostsByUserId(String appId, String marketId, String userIds) {
        validator.requireExistingMarket(appId,marketId);
        logger.debug("getPostsByUserId: userIds: {}", userIds);
        List<PostEntity> listOfUserPosts = postRepository.getAllByMarket_AppIdAndMarket_MarketIdAndUserID(appId, marketId, userIds);
        if (listOfUserPosts == null) {
            return Collections.emptyList();
        }
        List<PostDTO> postDTOs = new ArrayList<>();
        for (PostEntity postEntity : listOfUserPosts) {
            postDTOs.add(postConverter.toDTO(postEntity));

        }
        return postDTOs;
    }
    
    public int getPostCount(String appId, String marketId) {
        validator.requireExistingMarket(appId, marketId);
        logger.info("Fetching post count for marketId: {}", marketId);
        
        List<PostEntity> posts = postRepository.getAllByMarket_MarketIdAndMarket_AppId(marketId, appId);
        if (posts == null) {
            return 0;
        }
        return posts.size();
    }
    
    public List<PostDTO> getPostsByCategoryId(String appId, String marketId, String categoryId) {
        validator.requireExistingMarket(appId, marketId);
        validator.requireExistingCategories(categoryId);
        
        logger.info("Fetching posts for categoryId: {} in marketId: {}", categoryId, marketId);
        List<PostEntity> posts = postRepository.getPostEntitiesByMarket_AppIdAndMarket_MarketIdAndCategory_CategoryId(appId, marketId, categoryId);
        logger.warn("getPostsByCategoryId: posts: {}", posts);
        return posts.stream()
                .map(postConverter::toDTO)
                .toList();
    }
    
    public int getTotalPrice(String appId, String marketId) {
        validator.requireExistingMarket(appId, marketId);
        logger.info("Calculating total price for all posts in marketId: {}", marketId);
        
        List<PostEntity> posts = postRepository.getAllByMarket_MarketIdAndMarket_AppId(marketId, appId);
        if (posts == null || posts.isEmpty()) {
            return 0;
        }
        
        int totalPrice = posts.stream()
                .mapToInt(post -> (int) post.getPrice())
                .sum();
        
        logger.info("Total price for all posts in marketId {}: {}", marketId, totalPrice);
        return totalPrice;
        
    }
}
