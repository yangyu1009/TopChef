package com.topchef.demo.repository;

import com.topchef.demo.dto.handlesEntity.CreateCommentDto;
import com.topchef.demo.dto.handlesEntity.CreateRecipeDto;
import com.topchef.demo.dto.handlesEntity.RecipeDetailDto;
import com.topchef.demo.dto.handlesEntity.RegisterDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;

import java.util.List;

public interface TopChefUserDao {
    // create a recipe
    public void createRecipe(CreateRecipeDto createRecipe);
    public void insertRecipe(CreateRecipeDto createRecipe);
    public void insertIngredient(CreateRecipeDto createRecipe);
    public void insertPractice(CreateRecipeDto createRecipe);

    //get follower list Done
    public List<UserDto> getFollowerList(String userId);

    //get follower list Done
    public List<UserDto> getPublisherList(String userId);

    // delete recipe Done
    public void deleteRecipe(String recipeId);

    // subscribeRecipe Done
    public void subscribeRecipe(String recipeId);

    // judge whether this publisher is followed or not
    public boolean followOrNot(String userId);

    // judge whether this recipe is followed or not
    public boolean subscribeOrNot(String recipeId);

    //get all subscribe recipe Done
    public List<RecipeDto> getAllSubscribeRecipes(String userId);

    //register
    public void register(RegisterDto registerDto);

    //update Recipe
    public RecipeDetailDto updateRecipe(CreateRecipeDto createRecipe);

    //write comment
    public void addComment(CreateCommentDto createComment);

    //Reset password
    public void resetPwd(String password);

    //Change userName
    public void changeUserName(String name);

    //Sign out
    public void signOut();
}
