package com.topchef.demo.service;


import com.topchef.demo.dto.handlesEntity.CreateRecipeDto;
import com.topchef.demo.dto.handlesEntity.RegisterDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.SubscribeDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.dto.tableEntity.UserFollowDto;
import com.topchef.demo.repository.TopChefUserDao;
import com.topchef.demo.utils.CreateTimeUtils;
import com.topchef.demo.utils.IDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public UserHandlesService(TableSearchService tableSearchService) {
        this.tableSearchService = tableSearchService;
    }


    @Override
    public void createRecipe(CreateRecipeDto createRecipe) {
        createRecipe.setRecipeId(IDUtils.genRecipeId());
        String sql ="insert into recipe (r_id, r_name, description, image, S_number, V_number, Pub_time, u_id) values (?,?,?,?,?,?,?,?)";
        jdbcTemplateObject.update(sql, IDUtils.genRecipeId(), createRecipe.getRecipeName(), createRecipe.getDescription(), createRecipe.getImage(), 1, 1, CreateTimeUtils.genCreateTime(), "gao");
        for(int i=0; i <createRecipe.getPractice().size(); i++){
            sql ="insert into practice(r_id, index, description, image) values (?,?,?,?)";
            jdbcTemplateObject.update(sql, createRecipe.getRecipeId(), (i+1), createRecipe.getPracticeDescription().get(i), createRecipe.getPracticeImage().get(i));
        }
        for(int i=0; i < createRecipe.getIngredientName().size(); i++){
            sql ="insert into ingredient(r_id, name, amount) values (?,?,?)";
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
    public void updateRecipe(String recipeId) {

    }

    @Override
    public void subscribeRecipe(String recipeId) {
        String sql ="insert into subscribe (u_id, r_id) values (?,?)";
        jdbcTemplateObject.update(sql,0, recipeId);
    }

    @Override
    public boolean followOrNot(String userId) {
        String sql = "select * from user_follow where p_id=? and f_id=?";
        List row = jdbcTemplateObject.queryForList(sql, 0, userId);
        if(row.size() != 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean subscribeOrNot(String recipeId) {
        String sql = "select * from subscribe where u_id=? and r_id=?";
        List row = jdbcTemplateObject.queryForList(sql, 0, recipeId);
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
    public void register(RegisterDto registerDto) {
        String sql = "insert into user(u_id, u_name, email, create_date, password) values(?,?,?,?,?)";
        jdbcTemplateObject.update(sql, registerDto.getUerId(), registerDto.getUserName(), registerDto.getEmail(), registerDto.getCreateTime(), registerDto.getPassword());
    }

    public boolean isSuccess() {
        return true;
    }


}
