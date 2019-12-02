package com.topchef.demo.repository;

import com.topchef.demo.dto.handlesEntity.CreateCommentDto;
import com.topchef.demo.dto.tableEntity.*;

import java.util.List;

public interface TableSearchDao {

    //User Table
    public List<UserDto> getAllUsers();
    public UserDto getUserById(String userId);
    public boolean emailUsed(String email);
    public int getTotalUserNumber();

    //UserFollow Table
    public List<UserFollowDto> getAllPublishers(String userId);
    public List<UserFollowDto> getAllFollowers(String userId);

    //Subscribe Table
    public List<SubscribeDto> getAllSubscribeRecipes(String userId);

    //recipe Table
    public List<RecipeDto> getAllRecipes();
    public RecipeDto getRecipeByRecipeId(String recipeId);
    public List<RecipeDto> getAllRecipesByUserId(String userId);

    //Ingredient Table
    public List<IngredientDto> getAllIngredientsByRecipeId(String recipeId);

    //Practice Table
    public List<PracticeDto> getAllPracticesByRecipeId(String recipeId);

    //Comment Table
    public boolean commentValidation(CreateCommentDto createComment);

}
