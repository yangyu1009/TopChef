package com.topchef.demo.repository;

import com.topchef.demo.dto.CreateRecipeDto;

public interface UserDao {
    public void createCecipe(CreateRecipeDto createRecipe);
}
