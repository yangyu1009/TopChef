package com.topchef.demo.rest;

import com.topchef.demo.domain.TopChefUser;
import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.dto.UserDto;
import com.topchef.demo.service.TopChefUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private TopChefUserService topChefUserService;

    public UserController(TopChefUserService topChefUserService) {
        this.topChefUserService = topChefUserService;
    }
    @GetMapping(path = "/all")
    public List<UserDto> getAllItems() {
        return topChefUserService.getAllUserInfo();
    }
}
