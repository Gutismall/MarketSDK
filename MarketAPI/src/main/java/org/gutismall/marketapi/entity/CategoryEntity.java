package org.gutismall.marketapi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    private String categoryId;
    private String name;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> posts = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "marketId", nullable = false)
    private MarketEntity market;
}
