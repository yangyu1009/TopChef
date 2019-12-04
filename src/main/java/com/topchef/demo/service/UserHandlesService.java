package com.topchef.demo.service;


import com.topchef.demo.dto.handlesEntity.*;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.SubscribeDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.dto.tableEntity.UserFollowDto;
import com.topchef.demo.mapper.UserMapper;
import com.topchef.demo.repository.TopChefUserDao;
import com.topchef.demo.utils.CreateTimeUtils;
import com.topchef.demo.utils.CurrentUser;
import com.topchef.demo.utils.IDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserHandlesService implements TopChefUserDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    private TableSearchService tableSearchService;
    private UserFollowService userFollowService;

    public UserHandlesService(TableSearchService tableSearchService, UserFollowService userFollowService) {

        this.tableSearchService = tableSearchService;
        this.userFollowService = userFollowService;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void createRecipe(CreateRecipeDto createRecipe) {
        createRecipe.setRecipeId(IDUtils.genRecipeId());
        insertRecipe(createRecipe);
        insertIngredient(createRecipe);
        insertPractice(createRecipe);
    }

    @Override
    public void insertRecipe(CreateRecipeDto createRecipe) {
        String sql ="insert into recipe (r_id, r_name, description, S_number, V_number, Pub_time, u_id) values (?,?,?,?,?,?,?)";
        jdbcTemplateObject.update(sql,createRecipe.getRecipeId(), createRecipe.getRecipeName(), createRecipe.getDescription(), 0, 0, CreateTimeUtils.genCreateTime(), createRecipe.getUserId());
    }

    @Override
    public void insertIngredient(CreateRecipeDto createRecipe) {
        for(int i=0; i <createRecipe.getPracticeDescription().size(); i++){
            String sql ="insert into practice(r_id, indexn, description) values (?,?,?)";
            jdbcTemplateObject.update(sql, createRecipe.getRecipeId(), i, createRecipe.getPracticeDescription().get(i));
        }
    }

    @Override
    public void insertPractice(CreateRecipeDto createRecipe) {
        for(int i=0; i <createRecipe.getIngredientName().size(); i++){
            String sql ="insert into ingredient(r_id, name, amount) values (?,?,?)";
            jdbcTemplateObject.update(sql, createRecipe.getRecipeId(),createRecipe.getIngredientName().get(i), createRecipe.getIngredientNumber().get(i));
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------
    //get publisherList or FollowerList
    @Override
    public List<UserDto> getFollowerList(String userId) {
        Set<String> followId =  tableSearchService.getAllFollowers(userId)
                .stream()
                .map(UserFollowDto::getFollowerId)
                .collect(Collectors.toSet());
        return getUserDtos(followId);
    }

    @Override
    public List<UserDto> getPublisherList(String userId) {
        Set<String> publisherId =  tableSearchService.getAllPublishers(userId)
                .stream()
                .map(UserFollowDto::getPublisherId)
                .collect(Collectors.toSet());
        return getUserDtos(publisherId);
    }

    private List<UserDto> getUserDtos(Set<String> userId) {
        List<UserDto> users = tableSearchService.getAllUsers();
        return users.stream()
                .filter(item -> userId.contains(item.getUserId()))
                .map(followerUser -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(followerUser, dto);
                    return dto;
                }).collect(Collectors.toList());
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void deleteRecipe(String recipeId) {
        String sql ="delete from recipe where r_id = ?";
        jdbcTemplateObject.update(sql, recipeId);
    }

    @Override
    public void subscribeRecipe(String recipeId) {
        String sql ="insert into subscribe (u_id, r_id) values (?,?)";
        jdbcTemplateObject.update(sql,CurrentUser.CurrentUserId.get(0), recipeId);

    }

    @Override
    public boolean followOrNot(String userId) {
        String sql = "select * from user_follow where p_id=? and f_id=?";
        List row = jdbcTemplateObject.queryForList(sql, CurrentUser.CurrentUserId.get(0), userId);
        if(row.size() != 0){
            return true;
        }
        return false;
    }

    @Override
    public void unfollowed(String userId) {
        String sql = "delete from user_follow where p_id= ? and f_id=?";
        jdbcTemplateObject.update(sql, userId, CurrentUser.CurrentUserId.get(0));
    }

    @Override
    public boolean subscribeOrNot(String recipeId) {
        String sql = "select * from subscribe where u_id=? and r_id=?";
        List row = jdbcTemplateObject.queryForList(sql, CurrentUser.CurrentUserId.get(0), recipeId);
        if(row.size() != 0){
            return true;
        }
        return false;
    }

    @Override
    public List<RecipeDto> getAllSubscribeRecipes(String userId) {
        Set<String> recipesId = tableSearchService.getAllSubscribeRecipes(userId)
                .stream()
                .map(SubscribeDto::getRecipeId)
                .collect(Collectors.toSet());
        List<RecipeDto> recipes = tableSearchService.getAllRecipes();
        return recipes.stream()
                .filter(item -> recipesId.contains(item.getRecipeId()))
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void unsubscribe(String recipeId) {
        String sql = "delete from subscribe where u_id=? and r_id=?";
        jdbcTemplateObject.update(sql, CurrentUser.CurrentUserId.get(0), recipeId);
    }

    @Override
    public void register(RegisterDto registerDto) {
        String sql = "insert into user(u_id, u_name, email, create_date, password) values(?,?,?,?,?)";
        jdbcTemplateObject.update(sql, registerDto.getUerId(), registerDto.getUserName(), registerDto.getEmail(), registerDto.getCreateTime(), registerDto.getPassword());
    }
    @Override
    public RecipeDetailDto updateRecipe(CreateRecipeDto createRecipe) {
       // RecipeDetailDto recipeDetail =
        return null;
    }

    @Override
    public void addComment(CreateCommentDto createComment) {
        String sql = "insert into comment(r_id, u_id, description) values(?,?,?)";
        jdbcTemplateObject.update(sql, createComment.getRecipeId(), CurrentUser.CurrentUserId.get(0), createComment.getDescription());
    }

    @Override
    public void resetPwd(String password) {
        String sql = "update user set password = ? where u_id = ?";
        jdbcTemplateObject.update(sql, password, CurrentUser.CurrentUserId.get(0));
    }

    @Override
    public void changeUserName(String name) {
        String sql = "update user set u_name = ? where u_id = ?";
        jdbcTemplateObject.update(sql, name, CurrentUser.CurrentUserId.get(0));
    }

    @Override
    public void signOut() {
        CurrentUser.CurrentUserId = new ArrayList<>();
    }

    @Override
    public void followPublisher(String publisherId) {
        userFollowService.insert(publisherId);
    }

    @Override
    public Boolean Logincheck(LoginTryDto loginTryDto) {

        String sql = "select * from user where email=?";
        UserDto userInfo = jdbcTemplateObject.queryForObject(sql,new Object[]{loginTryDto.getEmail()}, new UserMapper());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean result = encoder.matches(loginTryDto.getPassword(), userInfo.getPassword());

        if(result == true){
            CurrentUser.CurrentUserId.add(userInfo.getUserId());
            CurrentUser.CurrentUserId.add(userInfo.getName());
            CurrentUser.CurrentUserId.add(userInfo.getEmail());
            return true;
        }
        return false;
    }
    public boolean isSuccess() {
        return true;
    }
}
