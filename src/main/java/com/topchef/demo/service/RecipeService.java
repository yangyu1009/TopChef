package com.topchef.demo.service;

import com.topchef.demo.dto.handlesEntity.RecipeDetailDto;
import com.topchef.demo.dto.tableEntity.IngredientDto;
import com.topchef.demo.dto.tableEntity.PracticeDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.handlesEntity.SubscribeAndViewNumberDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.repository.TopChefRecipeDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RecipeService implements TopChefRecipeDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    private TableSearchService tableSearchService;

    public RecipeService(TableSearchService tableSearchService) {
        this.tableSearchService = tableSearchService;
    }

    @Override
    public RecipeDetailDto getRecipeDetail(String recipeId) {
        //recipe info
        RecipeDetailDto recipeDetail = new RecipeDetailDto();
        RecipeDto recipe = tableSearchService.getRecipeByRecipeId(recipeId);
        recipeDetail.setRecipeId(recipe.getRecipeId());
        recipeDetail.setRecipeName(recipe.getRecipeName());
        recipeDetail.setDescription(recipe.getDescription());
        recipeDetail.setImage(recipe.getImage());
        recipeDetail.setS_number(recipe.getSNumber());
        recipeDetail.setV_number(recipe.getVNumber());
        recipeDetail.setPublishTime(recipe.getPublishTime());
        recipeDetail.setUserId(recipe.getUserId());

        //user info
        UserDto user = tableSearchService.getUserById(recipe.getUserId());
        recipeDetail.setUserName(user.getName());

        //ingredient
        List<IngredientDto> ingredientList = tableSearchService.getAllIngredientsByRecipeId(recipeId);
        for(IngredientDto ingredient: ingredientList){
            Map<String, String> map = recipeDetail.getIngredient();
            map.put(ingredient.getIngredientName(), ingredient.getIngredientAmount());
            //recipeDetail.setIngredient(recipeDetail.getIngredient());
        }

        //Practice
        List<PracticeDto> practiceList = tableSearchService.getAllPracticesByRecipeId(recipeId);
        for(PracticeDto practice: practiceList){
            ArrayList<String> info = new ArrayList<>();
            info.add(practice.getDescription());
            info.add(practice.getImage());
            recipeDetail.getPractice().add(info);
        }
        return recipeDetail;
    }

}
