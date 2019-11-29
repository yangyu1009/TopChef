package com.topchef.demo.rest;

import com.topchef.demo.dto.CreateRecipeDto;
import com.topchef.demo.service.TopChefUserService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private TopChefUserService topChefUserService;

    public UserController(TopChefUserService topChefUserService) {
        this.topChefUserService = topChefUserService;
    }

    @PostMapping(value = "/createRecipe")
    public void createRecipe(CreateRecipeDto createRecipe){
        topChefUserService.createCecipe(createRecipe);
    }
}
