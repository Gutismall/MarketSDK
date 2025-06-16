package org.gutismall.marketapi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "market")
public class MarketEntity {
    @Id
    private String  marketId;
    private String name;
    private String appId;
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<CategoryEntity> categories;
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
