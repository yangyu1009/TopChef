package com.topchef.demo.service;

import com.topchef.demo.repository.IngredientDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class IngredientService implements IngredientDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void deleteIngredient(String recipeId, String ingredientName) {
        String sql = "delete from ingredient where r_id = ? and name = ?";
        jdbcTemplateObject.update(sql, recipeId, ingredientName);

    }
}
