package com.topchef.demo.rest;

import com.topchef.demo.dto.handlesEntity.CreateRecipeDto;
import com.topchef.demo.dto.handlesEntity.RegisterDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.dto.tableEntity.UserFollowDto;
import com.topchef.demo.service.TableSearchService;
import com.topchef.demo.service.UserHandlesService;
import com.topchef.demo.utils.CreateTimeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserHandlesService userHandlesService;
    private TableSearchService tableSearchService;

    public UserController(UserHandlesService userHandlesService, TableSearchService tableSearchService) {
        this.userHandlesService = userHandlesService;
        this.tableSearchService = tableSearchService;
    }

    @PostMapping(value = "/createRecipe")
    public void createRecipe(CreateRecipeDto createRecipe){
        userHandlesService.createRecipe(createRecipe);
    }

    @GetMapping(path = "/followerList/{userId}")
    public List<UserDto> getFollowerList(@PathVariable("userId") String userId){
        return userHandlesService.getFollowerList(userId);
    }

    @GetMapping(path = "/publisherList/{userId}")
    public List<UserDto> getPublisherList(@PathVariable("userId") String userId){
        return userHandlesService.getPublisherList(userId);
    }

    @GetMapping(path = "/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") String recipeId){
        userHandlesService.deleteRecipe(recipeId);
        return "Delete Succeed!";
    }

    @GetMapping(path = "/recipeList/{userId}")
    public List<RecipeDto> getAllRecipesByUserId(@PathVariable("userId") String userId){
        return  tableSearchService.getAllRecipesByUserId(userId);
    }

    @GetMapping(path = "/subscribeList/{userId}")
    public  List<RecipeDto> getAllSubscribeRecipes(@PathVariable("userId") String userId){
        return  userHandlesService.getAllSubscribeRecipes(userId);
    }

    @GetMapping(path = "/follower/{userId}")
    public List<UserFollowDto> getFollower(@PathVariable("userId") String userId){
        return tableSearchService.getAllFollowers(userId);
    }

    @GetMapping(path = "/follower/allUser")
    public List<UserDto> getAllUer(){
        return tableSearchService.getAllUsers();
    }

    @PostMapping()
    public String subscribeRecipe(String recipeid){
        userHandlesService.subscribeRecipe(recipeid);
        return "Subscribed!";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RegisterDto registerDto){
        if(tableSearchService.emailUsed(registerDto.getEmail())){
            return "Sorry, email is used";
        }else{
            registerDto.setUerId(String.valueOf(tableSearchService.getTotalUserNumber()+1));
            registerDto.setCreateTime(CreateTimeUtils.genCreateTime());
            userHandlesService.register(registerDto);
        }
        return "Register succeed!";
    }
//    @GetMapping(path= "/login")
//    public boolean loginSuccess() { return topChefUserService.isSuccess(); }

}
