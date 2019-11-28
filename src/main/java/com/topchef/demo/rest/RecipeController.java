package com.topchef.demo.rest;

import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.repository.TopChefRecipeDao;
import com.topchef.demo.service.RecipeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @CrossOrigin
    @RestController
    @RequestMapping(path = "/recipe")
    public class RecipeController {
        private RecipeService recipeService;

        public RecipeController(RecipeService recipeService) {
            this.recipeService = recipeService;
        }

        @GetMapping(path = "/all")
        public List<RecipeDto> findAllRecipes() {
        return recipeService.findAllRceipes();
    }
    @GetMapping(path = "/{recipeId}")
        public RecipeDto getRecipeById(@PathVariable("recipeId") String recipeId){
            return recipeService.getRecipeById(recipeId);
    }
    }

