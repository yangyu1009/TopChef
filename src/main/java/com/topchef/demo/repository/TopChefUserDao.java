package com.topchef.demo.repository;

import com.topchef.demo.dto.handlesEntity.*;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;

import java.util.List;

public interface TopChefUserDao {

    //update Recipe
    public RecipeDetailDto updateRecipe(CreateRecipeDto createRecipe);


    //User service
    //------------------------------------------------------------------------------------------------------------------
    //1. register
    //2. login
    // sign out

    //register
    public void register(RegisterDto registerDto);
    //login in
    public Boolean Logincheck(LoginTryDto loginTryDto);
    //Sign out
    public void signOut();
    //------------------------------------------------------------------------------------------------------------------
    //3. create recipe
    //4. see myself published recipe
    //5. delete my recipe

    //create recipe
    public void createRecipe(CreateRecipeDto createRecipe);
    public void insertRecipe(CreateRecipeDto createRecipe);
    public void insertIngredient(CreateRecipeDto createRecipe);
    public void insertPractice(CreateRecipeDto createRecipe);
    //table search service
    // delete recipe Done
    public void deleteRecipe(String recipeId);
    //------------------------------------------------------------------------------------------------------------------
    //6. see my follower

    //get follower list Done
    public List<UserDto> getFollowerList(String userId);
    //------------------------------------------------------------------------------------------------------------------
    //7. follow publisher
    //8. see my followed publisher
    //9. check this publisher is follow or not
    //10. unfollow this publisher

    // follow publisher
    public void followPublisher(String publisherId);
    //get followed publisher list
    public List<UserDto> getPublisherList(String userId);
    // check this publisher is follow or not
    public boolean followOrNot(String userId);
    // unfollow this publisher
    public void unfollowed(String userId);
    //------------------------------------------------------------------------------------------------------------------
    //11. subscribe recipe
    //12. see my subscribe
    //13. unsubscribe recipe
    //14. check this recipe is subscribe or not

    // subscribeRecipe
    public void subscribeRecipe(String recipeId);
    //get all subscribe recipe Done
    public List<RecipeDto> getAllSubscribeRecipes(String userId);
    //unsubscribe recipe
    public void unsubscribe(String recipeId);
    // check this recipe is subscribe or not
    public boolean subscribeOrNot(String recipeId);
    //------------------------------------------------------------------------------------------------------------------
    //15. change userName
    //16. reset pasword

    //Change userName
    public void changeUserName(String name);
    //Reset password
    public void resetPwd(String password);
    //------------------------------------------------------------------------------------------------------------------
    //17. write comment

    //write comment
    public void addComment(CreateCommentDto createComment);

    // update my recipe


}
