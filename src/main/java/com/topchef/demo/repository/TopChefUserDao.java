package com.topchef.demo.repository;

import com.topchef.demo.dto.CreateRecipeDto;
import com.topchef.demo.dto.PublisherAndFollowerDto;

import java.util.List;

public interface TopChefUserDao {
    // create a recipe
    public void createRecipe(CreateRecipeDto createRecipe);

    //get follower list
    public List<PublisherAndFollowerDto> getFollowerList(String userId);

    //get follower list
    public List<PublisherAndFollowerDto> getPublisherList(String userId);

    // delete recipe
    public void deleteRecipe(String recipeId);

    // update recipe
    public void updateRecipe(String recipeId);

    // subscribeRecipe
    public void subscribeRecipe(String recipeId);

    // judge whether this publisher is followed or not
    public boolean followOrNot(String userId);

    // judge whether this recipe is followed or not
    public boolean subscribeOrNot(String recipeId);
}
