package org.gutismall.marketapi.controllers;

import org.gutismall.marketapi.DTO.*;
import org.gutismall.marketapi.entity.CategoryEntity;
import org.gutismall.marketapi.services.CategoryService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/{appId}/{marketId}/category")
public class CategoryController {
    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
            (produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> getAllCategories(
            @PathVariable String appId,
            @PathVariable String marketId)
    {
        return categoryService.getAllCategories(appId,marketId);
    }
    
    @GetMapping(path = "/{categoryId}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO getCategoryById(
            @PathVariable String categoryId,
            @PathVariable String appId,
            @PathVariable String marketId)
    {
        return categoryService.getCategoryById(appId,marketId,categoryId);
    }
    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CategoryDTO createCategory(
            @PathVariable String appId,
            @PathVariable String marketId,
            @RequestBody CategoryDTO categoryDTO)
    {
        return categoryService.createCategory(appId,marketId,categoryDTO);
    }
    
    @PutMapping(
            path = "/{categoryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CategoryDTO updateCategory(
            @PathVariable String appId,
            @PathVariable String marketId,
            @PathVariable String categoryId,
            @RequestBody CategoryDTO updatedCategory)
    {
        return categoryService.updateCategory(appId,marketId,categoryId,updatedCategory);
    }
    
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(
            @PathVariable String appId,
            @PathVariable String marketId,
            @PathVariable String categoryId
    )
    {
        categoryService.deleteCategory(appId,marketId,categoryId);
    }
    @GetMapping(path = "/names",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> getCategoryNames(
            @PathVariable String appId,
            @PathVariable String marketId)
    {
        return categoryService.getCategoryNames(appId,marketId);
    }

    @GetMapping(path = "/{categoryId}/posts",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getCategoryPosts(@PathVariable String appId,
                                          @PathVariable String marketId,
                                          @PathVariable String categoryId){
        return categoryService.getCategoryPosts(appId,marketId,categoryId);
    }
}