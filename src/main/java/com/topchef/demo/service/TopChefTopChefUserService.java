package com.topchef.demo.service;


import com.topchef.demo.dto.CreateRecipeDto;
import com.topchef.demo.dto.ShowFollowerDto;
import com.topchef.demo.mapper.UserFollowMapper;
import com.topchef.demo.mapper.UserFollowedMapper;
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

    @Override
    public List<ShowFollowerDto> getFollowerList(String userId) {
        String sql ="select f_id, u_name from user_follow, user where p_id=? and f_id = u_id";
        List<ShowFollowerDto> followers = jdbcTemplateObject.query(sql, new Object[]{userId}, new UserFollowMapper());
        return followers.stream()
                .map(follower -> {
                    ShowFollowerDto dto = new ShowFollowerDto();
                    BeanUtils.copyProperties(follower, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowFollowerDto> getPublisherList(String userId) {
        String sql ="select p_id, u_name from user_follow, user where f_id=? and p_id = u_id";
        List<ShowFollowerDto> publishers = jdbcTemplateObject.query(sql, new Object[]{userId}, new UserFollowedMapper());
        return publishers.stream()
                .map(publisher -> {
                    ShowFollowerDto dto = new ShowFollowerDto();
                    BeanUtils.copyProperties(publisher, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRecipe(String recipeId) {
        String sql ="delete from recipe where r_id = ?)";
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

}
