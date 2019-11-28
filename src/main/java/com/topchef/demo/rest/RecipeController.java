package com.topchef.demo.rest;

import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.service.TopChefRecipeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @CrossOrigin
    @RestController
    @RequestMapping(path = "/recipe")
    public class RecipeController {
        private TopChefRecipeService topChefRecipeService;

        public RecipeController(TopChefRecipeService topChefRecipeService) {
            this.topChefRecipeService = topChefRecipeService;
        }
        @GetMapping(path = "/all")
        public List<RecipeDto> getAllRecipes() {
        return topChefRecipeService.getAllRecipes();
    }
        @GetMapping(path = "/{recipeId}")
        public RecipeDto getOneItem(@PathVariable("recipeId") String recipeId) {
            return topChefRecipeService.getRecipe(recipeId);
        }
    }

