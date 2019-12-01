package com.topchef.demo.dto.tableEntity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RecipeDto {
    private String recipeId;
    private String recipeName;
    private String description;
    private String image;
    private int sNumber;
    private int vNumber;
    private String publishTime;
    private String userId;

}



