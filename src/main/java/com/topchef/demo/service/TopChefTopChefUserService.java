package com.topchef.demo.service;


import com.topchef.demo.dto.CreateRecipeDto;
import com.topchef.demo.dto.PublisherAndFollowerDto;
import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.dto.SubscribeDto;
import com.topchef.demo.mapper.PublisherAndFollowerMapper;
import com.topchef.demo.mapper.SubscribeMapper;
import com.topchef.demo.mapper.TopChefRecipeMapper;
import com.topchef.demo.repository.TopChefUserDao;
import com.topchef.demo.utils.CreateTimeUtils;
import com.topchef.demo.utils.IDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopChefTopChefUserService implements TopChefUserDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

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
    public List<PublisherAndFollowerDto> getFollowerList(String userId) {
        String sql ="select f_id, p_id, u_name from user_follow, user where p_id=? and f_id = u_id";
        return getPublisherAndFollowerDtos(userId, sql);
    }

    @Override
    public List<PublisherAndFollowerDto> getPublisherList(String userId) {
        String sql ="select p_id, f_id, u_name from user_follow, user where f_id=? and p_id = u_id";
        return getPublisherAndFollowerDtos(userId, sql);
    }

    private List<PublisherAndFollowerDto> getPublisherAndFollowerDtos(String userId, String sql) {
        List<PublisherAndFollowerDto> publishers = jdbcTemplateObject.query(sql, new Object[]{userId}, new PublisherAndFollowerMapper());
        return publishers.stream()
                .map(publisher -> {
                    PublisherAndFollowerDto dto = new PublisherAndFollowerDto();
                    BeanUtils.copyProperties(publisher, dto);
                    return dto;
                })
                .collect(Collectors.toList());
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
    public List<RecipeDto> getAllRecipesByUserId(String userId) {
        String sql ="select * from recipe where u_id=?";
        List<RecipeDto> recipes = jdbcTemplateObject.query(sql, new String[]{userId},new TopChefRecipeMapper());
        return recipes.stream()
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SubscribeDto> getAllSubscribeRecipes(String userId) {
        String sql="select s.u_id, s.r_id, r.r_name, r.image from recipe r, subscribe s where s.u_id = ? and s.r_id = r.r_id";
        List<SubscribeDto> recipes = jdbcTemplateObject.query(sql, new String[]{userId},new SubscribeMapper());
        return recipes.stream()
                .map(recipe -> {
                    SubscribeDto dto = new SubscribeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
