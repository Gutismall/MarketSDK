package org.gutismall.marketapi.controllers;

import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.services.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/{appId}/markets/{marketId}/posts")
public class PostController {
    private final PostService postService;
    static final Logger logger = LoggerFactory.getLogger(PostController.class);
    
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getPosts(
            @PathVariable String appId,
            @PathVariable String marketId,
            @RequestParam(required = false) String userId
    ) {
        if (userId != null) {
            return postService.getPostsByUserId(appId, marketId, userId);
        }
        return postService.getAllPosts(appId, marketId);
    }
    
    @GetMapping("/{postId}")
    public PostDTO getPostById(
            @PathVariable String appId,
            @PathVariable String marketId,
            @PathVariable String postId) {
        return postService.getPostById(appId, marketId, postId);
    }
    
    @PostMapping
    public PostDTO createPost(
            @PathVariable String appId,
            @PathVariable String marketId,
            @RequestBody PostDTO dto) {
        return postService.createPost(appId, marketId, dto);
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(
            @PathVariable String appId,
            @PathVariable String marketId,
            @PathVariable String postId) {
        postService.deletePost(appId, marketId, postId);
    }
    
    @PostMapping("/{postId}")
    public PostDTO updatePost(
            @PathVariable String appId,
            @PathVariable String marketId,
            @PathVariable String postId,
            @RequestBody PostDTO dto) {
        return postService.updatePost(appId, marketId, postId, dto);
    }
    

    
    @GetMapping(path = "/count")
    public int getPostCount(
            @PathVariable String appId,
            @PathVariable String marketId
    ) {
        return postService.getPostCount(appId, marketId);
        
    }
    @GetMapping(path = "/{categoryId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getPostsByCategoryId(
            @PathVariable String appId,
            @PathVariable String marketId,
            @PathVariable String categoryId) {
        return postService.getPostsByCategoryId(appId, marketId, categoryId);
    }
    @GetMapping(path = "/price")
    public int getTotalPrice(
            @PathVariable String appId,
            @PathVariable String marketId) {
        return postService.getTotalPrice(appId, marketId);
    }
}