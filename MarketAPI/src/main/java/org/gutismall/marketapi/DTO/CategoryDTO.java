package org.gutismall.marketapi.DTO;


import lombok.*;

import java.util.*;

@Data
public class CategoryDTO {
    private String categoryId;
    private String name;
    private List<String> postsIds;
    private String marketId;
}
