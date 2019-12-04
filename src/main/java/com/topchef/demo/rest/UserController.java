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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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


    @Log("get all user")
    @GetMapping(path = "/allUser")
    public List<UserDto> getAllUer(){
        return tableSearchService.getAllUsers();
    }


    //User service
    //------------------------------------------------------------------------------------------------------------------
    //1. register
    //2. login
    // sign out

//    public ModelAndView registerRecdirect(String path){
//        return  new ModelAndView(new RedirectView(path));
//    }
    //register
    @Log("register user")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(RegisterDto registerDto){
        if(tableSearchService.emailUsed(registerDto.getEmail())){
            return new ModelAndView(new RedirectView("http://localhost:4200/register"));
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String result = encoder.encode(registerDto.getPassword());
            System.out.println(result);
            registerDto.setPassword(result);
            registerDto.setUerId(String.valueOf(tableSearchService.getTotalUserNumber()+1));
            registerDto.setCreateTime(CreateTimeUtils.genCreateTime());
            userHandlesService.register(registerDto);
        }
        return new ModelAndView(new RedirectView("http://localhost:4200/"));
    }

    //login in
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

    //Sign out
    @Log("sign out")
    @RequestMapping(path = "/signOut")
    public void signOut(){
        userHandlesService.signOut();
    }
    //------------------------------------------------------------------------------------------------------------------
    //3. create recipe
    //4. see myself published recipe
    //5. delete my recipe

    //create recipe
    @Log("create recipe")
    @RequestMapping(value = "/createRecipe", method = RequestMethod.POST)
    public String createRecipe(CreateRecipeDto createRecipe){
        createRecipe.setUserId(CurrentUser.CurrentUserId.get(0));
        System.out.println("go");
        userHandlesService.createRecipe(createRecipe);
        return  "done";
    }

    //see myself published recipe
    @Log("get user recipe list")
    @GetMapping(path = "/recipeList/{userId}")
    public List<RecipeDto> getAllRecipesByUserId(@PathVariable("userId") String userId){
        return  tableSearchService.getAllRecipesByUserId(userId);
    }

    // delete recipe Done
    @Log("delete recipe")
    @GetMapping(path = "/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") String recipeId){
        userHandlesService.deleteRecipe(recipeId);
        return "Delete Succeed!";
    }
    //------------------------------------------------------------------------------------------------------------------
    //6. see my follower

    //get follower list
    @Log("get follower list")
    @GetMapping(path = "/followerList/{userId}")
    public List<UserDto> getFollowerList(@PathVariable("userId") String userId){
        return userHandlesService.getFollowerList(userId);
    }
    //------------------------------------------------------------------------------------------------------------------
    //7. follow publisher
    //8. see my followed publisher
    //9. check this publisher is follow or not
    //10. unfollow this publisher

    // follow publisher

    //get followed publisher list
    @Log("get publisher list")
    @GetMapping(path = "/publisherList/{userId}")
    public List<UserDto> getPublisherList(@PathVariable("userId") String userId){
        return userHandlesService.getPublisherList(userId);
    }

    // check this publisher is follow or not

    // unfollow this publisher

    //------------------------------------------------------------------------------------------------------------------
    //11. subscribe recipe
    //12. see my subscribe
    //13. unsubscribe recipe
    //14. check this recipe is subscribe or not

    // subscribeRecipe
    @Log("sbuscribe")
    @GetMapping(path = "/subscribe/{recipeId}")
    public String subscribeRecipe(@PathVariable("recipeId") String recipeId){
        userHandlesService.subscribeRecipe(recipeId);
        return "Subscribed!";
    }

    //get all subscribe recipe Done
    @Log("get subscribelist")
    @GetMapping(path = "/subscribeList/{userId}")
    public  List<RecipeDto> getAllSubscribeRecipes(@PathVariable("userId") String userId){
        return  userHandlesService.getAllSubscribeRecipes(userId);
    }

    //unsubscribe recipe

    // check this recipe is subscribe or not

    //------------------------------------------------------------------------------------------------------------------
    //15. change userName
    //16. reset pasword

    //Change userName
    @Log("change User Name")
    @RequestMapping(value = "/changeUserName", method = RequestMethod.POST)
    public String changeUserName(String newName){
        userHandlesService.changeUserName(newName);
        return "Change Name Succeed!";
    }
    //Reset password
    @Log("reset password")
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public String resetPwd(String newPwd){
        userHandlesService.resetPwd(newPwd);
        return "Reset Succeed!";
    }
    //------------------------------------------------------------------------------------------------------------------
    //17. write comment

    //write comment
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

}
