package com.topchef.demo.repository;

import com.topchef.demo.dto.RecipeDto;

import java.util.List;

public interface TopChefRecipeDao {
    public List<RecipeDto> findAllRceipes();
    public RecipeDto getRecipeById(String recipeId);
}
