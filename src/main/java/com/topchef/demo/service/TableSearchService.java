package com.topchef.demo.service;

import com.topchef.demo.dto.tableEntity.*;
import com.topchef.demo.mapper.*;
import com.topchef.demo.repository.TableSearchDao;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TableSearchService implements TableSearchDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;


    @Override
    public List<UserDto> getAllUsers() {
        String sql = "select * from user";
        List<UserDto> users = jdbcTemplateObject.query(sql, new UserMapper());
        return users.stream()
                .map(user -> {
                    UserDto dto = new UserDto();
                    BeanUtils.copyProperties(user, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String userId) {
        String sql ="select * from user where u_id=?";
        UserDto user = jdbcTemplateObject.queryForObject(sql,new Object[]{userId}, new UserMapper());
        return user;
    }

    @Override
    public boolean emailUsed(String email) {
        String sql = "select * from user where email = ?";
        List row = jdbcTemplateObject.queryForList(sql, email);
        if(row.size() != 0){
            return true;
        }
        return false;
    }

    @Override
    public int getTotalUserNumber() {
        String sql = "select count(*) from user";
        return jdbcTemplateObject.queryForObject(sql, Integer.class);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<UserFollowDto> getAllPublishers(String userId) {
        String sql = "select * from user_follow where f_id=?";
        return getUserFollowDtos(userId, sql);
    }

    @Override
    public List<UserFollowDto> getAllFollowers(String userId) {
        String sql = "select * from user_follow where p_id=?";
        return getUserFollowDtos(userId, sql);
    }

    private List<UserFollowDto> getUserFollowDtos(String userId, String sql) {
        List<UserFollowDto> userList = jdbcTemplateObject.query(sql, new Object[]{userId}, new UserFollowMapper());
        return userList.stream()
                .map(user -> {
                    UserFollowDto dto = new UserFollowDto();
                    BeanUtils.copyProperties(user, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<SubscribeDto> getAllSubscribeRecipes(String userId) {
        String sql = "select * from subscribe where u_id=?";
        List<SubscribeDto> subscribes = jdbcTemplateObject.query(sql,new Object[]{userId} ,new SubscribeMapper());
        return subscribes.stream()
                .map(subscribe -> {
                    SubscribeDto dto = new SubscribeDto();
                    BeanUtils.copyProperties(subscribe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        String sql = "select * from recipe";
        List<RecipeDto> recipes = jdbcTemplateObject.query(sql, new RecipeMapper());
        return recipes.stream()
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDto getRecipeByRecipeId(String recipeId) {
        String sql ="select * from recipe where r_id=?";
        RecipeDto recipe = jdbcTemplateObject.queryForObject(sql,new Object[]{recipeId}, new RecipeMapper());
        return recipe;
    }

    @Override
    public List<RecipeDto> getAllRecipesByUserId(String userId) {
        String sql = "select * from recipe where u_id=?";
        List<RecipeDto> recipes = jdbcTemplateObject.query(sql,new Object[]{userId} ,new RecipeMapper());
        return recipes.stream()
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientDto> getAllIngredientsByRecipeId(String recipeId) {
        String sql = "select * from ingredient where r_id=?";
        List<IngredientDto> ingredients = jdbcTemplateObject.query(sql,new Object[]{recipeId} ,new IngredientMapper());
        return ingredients.stream()
                .map(ingredient -> {
                    IngredientDto dto = new IngredientDto();
                    BeanUtils.copyProperties(ingredient, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PracticeDto> getAllPracticesByRecipeId(String recipeId) {
        String sql = "select * from practice where r_id=? order by indexn";
        List<PracticeDto> practices = jdbcTemplateObject.query(sql,new Object[]{recipeId} ,new PracticeMapper());
        return practices.stream()
                .map(practice -> {
                    PracticeDto dto = new PracticeDto();
                    BeanUtils.copyProperties(practice, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
