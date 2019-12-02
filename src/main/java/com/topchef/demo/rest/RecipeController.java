package com.topchef.demo.rest;

import com.topchef.demo.aop.Log;
import com.topchef.demo.dto.handlesEntity.RecipeDetailDto;
import com.topchef.demo.dto.tableEntity.IngredientDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.service.RecipeService;
import com.topchef.demo.service.TableSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

    private RecipeService recipeService;
    private TableSearchService tableSearchService;

    public RecipeController(RecipeService recipeService, TableSearchService tableSearchService) {
        this.recipeService = recipeService;
        this.tableSearchService = tableSearchService;
    }

    @Log("get all recipe")
    @GetMapping(path = "/all")
        public List<RecipeDto> findAllRecipes() {
        return tableSearchService.getAllRecipes();
    }
    @Log("get recipeDetail")
    @GetMapping(path = "/recipeDetail/{recipeId}")
        public RecipeDetailDto getRecipeById(@PathVariable("recipeId") String recipeId){
            return recipeService.getRecipeDetail(recipeId);
    }
    @Log("get Ingredient")
    @GetMapping(path = "/ingredient/{recipeId}")
    public List<IngredientDto> getIngredientById(@PathVariable("recipeId") String recipeId){
        return tableSearchService.getAllIngredientsByRecipeId(recipeId);
    }

    @Log("search recipe")
    @GetMapping(path = "/search/{keyword}")
    public List<RecipeDto> searchRecipe(@PathVariable("keyword") String keyword){
        return recipeService.searchRecipe(keyword);
    }
}

