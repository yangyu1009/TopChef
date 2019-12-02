package com.topchef.demo.rest;

import com.topchef.demo.aop.Log;
import com.topchef.demo.dto.handlesEntity.CreateCommentDto;
import com.topchef.demo.dto.handlesEntity.CreateRecipeDto;
import com.topchef.demo.dto.handlesEntity.LoginTryDto;
import com.topchef.demo.dto.handlesEntity.RegisterDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.service.TableSearchService;
import com.topchef.demo.service.UserHandlesService;
import com.topchef.demo.utils.CreateTimeUtils;
import com.topchef.demo.utils.CurrentUser;
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

    // user to userTable------------------------------------------------------------------------------------------------
    @Log("login in")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean LoginResult(LoginTryDto loginTryDto){
        System.out.println("Test");
        if(userHandlesService.Logincheck(loginTryDto)==true){
            System.out.println("Succeed");
            return true;
        }
        System.out.println("Fail");
        return false;
    }
    @Log("register user")
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

    @Log("reset password")
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public String resetPwd(String newPwd){
        userHandlesService.resetPwd(newPwd);
        return "Reset Succeed!";
    }

    @Log("change User Name")
    @RequestMapping(value = "/changeUserName", method = RequestMethod.POST)
    public String changeUserName(String newName){
        userHandlesService.changeUserName(newName);
        return "Change Name Succeed!";
    }

    @Log("sign out")
    @RequestMapping(path = "/signOut")
    public void signOut(){
        userHandlesService.signOut();
    }
    //user to recipeTable-----------------------------------------------------------------------------------------------

    @Log("create recipe")
    @RequestMapping(value = "/createRecipe", method = RequestMethod.POST)
    public String createRecipe(CreateRecipeDto createRecipe){
        createRecipe.setUserId(CurrentUser.CurrentUserId.get(0));
        System.out.println("go");
        userHandlesService.createRecipe(createRecipe);
        return  "done";
    }

    @Log("get follower list")
    @GetMapping(path = "/followerList/{userId}")
    public List<UserDto> getFollowerList(@PathVariable("userId") String userId){
        return userHandlesService.getFollowerList(userId);
    }

    @Log("get publisher list")
    @GetMapping(path = "/publisherList/{userId}")
    public List<UserDto> getPublisherList(@PathVariable("userId") String userId){
        return userHandlesService.getPublisherList(userId);
    }

    @Log("delete recipe")
    @GetMapping(path = "/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") String recipeId){
        userHandlesService.deleteRecipe(recipeId);
        return "Delete Succeed!";
    }

    @Log("get user recipe list")
    @GetMapping(path = "/recipeList/{userId}")
    public List<RecipeDto> getAllRecipesByUserId(@PathVariable("userId") String userId){
        return  tableSearchService.getAllRecipesByUserId(userId);
    }

    @Log("get subscribelist")
    @GetMapping(path = "/subscribeList/{userId}")
    public  List<RecipeDto> getAllSubscribeRecipes(@PathVariable("userId") String userId){
        return  userHandlesService.getAllSubscribeRecipes(userId);
    }

    @Log("get all user")
    @GetMapping(path = "/allUser")
    public List<UserDto> getAllUer(){
        return tableSearchService.getAllUsers();
    }


    //user to subscribeTable--------------------------------------------------------------------------------------------
    @Log("sbuscribe")
    @GetMapping(path = "/subscribe/{recipeId}")
    public String subscribeRecipe(@PathVariable("recipeId") String recipeId){
        userHandlesService.subscribeRecipe(recipeId);
        return "Subscribed!";
    }

    //user to CommentTable----------------------------------------------------------------------------------------------
    @Log("add comment")
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(CreateCommentDto createComment){
        if(tableSearchService.commentValidation(createComment)){
            return "Sorry, Comment Duplicated!";
        }else{
            userHandlesService.addComment(createComment);
        }
        return "Add comment succeed!";
    }


//    @GetMapping(path= "/login")
//    public boolean loginSuccess() { return topChefUserService.isSuccess(); }

}
