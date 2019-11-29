package com.topchef.demo.repository;

import com.topchef.demo.domain.TopChefRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopChefRecipeRepository extends JpaRepository<TopChefRecipe, String>{
    @Query(value = "select * from recipe", nativeQuery = true)
    List<TopChefRecipe> getAllRecipeInfo();
    @Query(value = "select * from recipe where r_id =:recipeId", nativeQuery = true)
    TopChefRecipe getRecipe(@Param("recipeId") String recipeId);
}
