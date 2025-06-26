package org.gutismall.marketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    private String postId;
    private String title;
    private String description;
    @Min(value = 0,message = "Price can be lower then 0") @Max(value = Integer.MAX_VALUE,message = "Max price can go over")
    private double price;
    
    private List<String> images;
    
    @NotBlank(message = "User ID cannot be empty")
    private String userID;
    
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private CategoryEntity category;
    
    @ManyToOne
    @JoinColumn(name = "marketId", nullable = false)
    private MarketEntity market;
    
    private String country;
    private String city;
    
    private Date createdAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
