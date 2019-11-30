package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.RecipeTagDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeTagMapper implements RowMapper<RecipeTagDto> {
    @Override
    public RecipeTagDto mapRow(ResultSet resultSet, int i) throws SQLException {
        RecipeTagDto recipeTag = new RecipeTagDto();
        recipeTag.setRecipeId(resultSet.getString("r_id"));
        recipeTag.setTagId(resultSet.getString("t_id"));
        return recipeTag;
    }
}
