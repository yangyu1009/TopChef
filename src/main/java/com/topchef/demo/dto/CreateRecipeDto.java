package com.topchef.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateRecipeDto {
    private String recipeId;
    private String recipeName;
    private String userId;
    private String image;
    private String description;
    private List<String> ingredientName;
    private List<String> ingredientNumber;
    private List<String> practiceImage;
    private List<String> practiceDescription;
    private List<String> practice;
    private String tag;
}
