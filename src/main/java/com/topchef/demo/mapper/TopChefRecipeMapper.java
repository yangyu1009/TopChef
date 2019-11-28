package com.topchef.demo.mapper;

import com.topchef.demo.dto.RecipeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TopChefRecipeMapper implements RowMapper<RecipeDto> {
    @Override
    public RecipeDto mapRow(ResultSet resultSet, int i) throws SQLException {
        RecipeDto topChefRecipe = new RecipeDto();
        topChefRecipe.setRecipeId(resultSet.getString("r_id"));
        topChefRecipe.setRecipeName(resultSet.getString("r_name"));
        topChefRecipe.setDescription(resultSet.getString("description"));
        topChefRecipe.setImage(resultSet.getString("image"));
        topChefRecipe.setSNumber(resultSet.getInt("S_number"));
        topChefRecipe.setVNumber(resultSet.getInt("V_number"));
        topChefRecipe.setPublishTime(resultSet.getString("Pub_time"));
        topChefRecipe.setUserId(resultSet.getString("u_id"));
        return topChefRecipe;
    }
}
