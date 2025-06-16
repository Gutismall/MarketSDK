package org.gutismall.marketapi.DTO;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    
    private String postId;
    private String title;
    private String description;
    private double price;
    private List<String> images;
    private String userID;
    private String categoryId;
    private String marketId;
    private String country;
    private String city;
    private Date createdAt;
    
}
