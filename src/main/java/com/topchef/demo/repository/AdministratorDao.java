package com.topchef.demo.repository;

import com.topchef.demo.dto.handlesEntity.DeleteCommentDto;
import com.topchef.demo.dto.handlesEntity.RegisterDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;

import java.util.List;

public interface AdministratorDao {
    //create user
    //delete user
    //see all user
    public void create(RegisterDto registerDto);
    public void deleteUser(String userId);
    public List<UserDto> getAllUser();

    //delete recipe
    public void deleteRecipe(String recipeId);
    //see all recipe
    public List<RecipeDto> getAllRecipe();
    //delete comment
    public void deleteComment(DeleteCommentDto deleteComment);
}
