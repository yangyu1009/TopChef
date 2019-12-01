package com.topchef.demo.dto.handlesEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateRecipeDto {
    private String recipeId;
    private String recipeName;
    private String userId;
    private String image;
    private String description;
    private List<String> ingredientName = new ArrayList<>();
    private List<String> ingredientNumber = new ArrayList<>();
    private List<String> practiceImage = new ArrayList<>();
    private List<String> practiceDescription = new ArrayList<>();
    private String tag;
}
