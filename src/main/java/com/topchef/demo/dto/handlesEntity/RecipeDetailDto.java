package com.topchef.demo.dto.handlesEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class RecipeDetailDto {
    // recipe simple info
    private String recipeId;
    private String recipeName;
    private String image;
    private String description;
    private String publishTime;
    private int v_number;
    private int s_number;

    // publisher Info
    private String userId;
    private String userName;

    //ingredient
    private HashMap<String, String> ingredient = new HashMap<>();


    //practice
    private List<List<String>> practice = new ArrayList<>();
}
