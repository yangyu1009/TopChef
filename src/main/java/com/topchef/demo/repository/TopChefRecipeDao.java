package com.topchef.demo.repository;

import com.topchef.demo.dto.handlesEntity.RecipeDetailDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.handlesEntity.SubscribeAndViewNumberDto;

import java.util.List;

public interface TopChefRecipeDao {
    public RecipeDetailDto getRecipeDetail(String recipeId);

}
