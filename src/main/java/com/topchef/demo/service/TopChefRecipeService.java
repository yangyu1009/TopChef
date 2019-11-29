package com.topchef.demo.service;

import com.topchef.demo.domain.TopChefRecipe;
import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.repository.TopChefRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;



@Service
@Transactional
public class TopChefRecipeService {
    @Autowired
    private TopChefRecipeRepository topChefRecipeRepository;

        public List<RecipeDto> getAllRecipes() {
        List<TopChefRecipe> allRecipes = topChefRecipeRepository.getAllRecipeInfo();

        return allRecipes.stream()
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public RecipeDto getRecipe(String id) {

        TopChefRecipe recipe = topChefRecipeRepository.getRecipe(id);
        RecipeDto dto = new RecipeDto();
        BeanUtils.copyProperties(recipe, dto);
        return dto;
    }

}
