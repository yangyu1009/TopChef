package com.topchef.demo.service;

import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.mapper.TopChefRecipeMapper;
import com.topchef.demo.repository.TopChefRecipeDao;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeService implements TopChefRecipeDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;


    @Override
    public List<RecipeDto> findAllRceipes() {
        String sql = "select * from recipe";
        List<RecipeDto> recipes = jdbcTemplateObject.query(sql, new TopChefRecipeMapper());
        return recipes.stream()
                .map(recipe -> {
                    RecipeDto dto = new RecipeDto();
                    BeanUtils.copyProperties(recipe, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDto getRecipeById(String recipeId) {
        String sql ="select * from recipe where r_id=?";
        RecipeDto recipe = jdbcTemplateObject.queryForObject(sql,new Object[]{recipeId}, new TopChefRecipeMapper());
        sql = "update recipe set V_number= V_number+1 where r_id=?";
        jdbcTemplateObject.update(sql, recipeId);
        return recipe;
    }
}
