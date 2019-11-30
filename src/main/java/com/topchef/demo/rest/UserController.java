package com.topchef.demo.rest;

import com.topchef.demo.dto.CreateRecipeDto;
import com.topchef.demo.dto.PublisherAndFollowerDto;
import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.dto.SubscribeDto;
import com.topchef.demo.service.TopChefTopChefUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private TopChefTopChefUserService topChefUserService;

    public UserController(TopChefTopChefUserService topChefUserService) {
        this.topChefUserService = topChefUserService;
    }

    @PostMapping(value = "/createRecipe")
    public void createRecipe(CreateRecipeDto createRecipe){
        topChefUserService.createRecipe(createRecipe);
    }

    @GetMapping(path = "/followerList/{userId}")
    public List<PublisherAndFollowerDto> getFollowerList(@PathVariable("userId") String userId){
        return topChefUserService.getFollowerList(userId);
    }

    @GetMapping(path = "/publisherList/{userId}")
    public List<PublisherAndFollowerDto> getPublisherList(@PathVariable("userId") String userId){
        return topChefUserService.getPublisherList(userId);
    }

    @GetMapping(path = "/deleteRecipe/{recipeId}")
    public void deleteRecipe(@PathVariable("recipeId") String recipeId){
        topChefUserService.deleteRecipe(recipeId);
    }

    @GetMapping(path = "/recipeList/{userId}")
    public List<RecipeDto> getAllRecipesByUserId(@PathVariable("userId") String userId){
        return  topChefUserService.getAllRecipesByUserId(userId);
    }

    @GetMapping(path = "/subscribeList/{userId}")
    public  List<SubscribeDto> getAllSubscribeRecipes(@PathVariable("userId") String userId){
        return  topChefUserService.getAllSubscribeRecipes(userId);
    }
}
