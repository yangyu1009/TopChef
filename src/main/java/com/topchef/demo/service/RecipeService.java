package com.topchef.demo.service;

import com.topchef.demo.dto.handlesEntity.RecipeDetailDto;
import com.topchef.demo.dto.tableEntity.IngredientDto;
import com.topchef.demo.dto.tableEntity.PracticeDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.mapper.RecipeMapper;
import com.topchef.demo.repository.TopChefRecipeDao;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<RecipeDto> searchRecipe(String keyword) {
        keyword = keyword.replaceAll("[\\pP\\pS\\pC\\pM]", "");
        String[] words = keyword.split("\\s+");
        List<RecipeDto> recipes = new ArrayList<>();
        for(String word: words){
            String sql = "select * from recipe where r_name like ?";
            List<RecipeDto> recipe = jdbcTemplateObject.query(sql,new String[]{"%"+word+"%"}, new RecipeMapper());
            recipes.addAll(recipe);
        }
        return recipes.stream()
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
