package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.IngredientDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper<IngredientDto> {
    @Override
    public IngredientDto mapRow(ResultSet resultSet, int i) throws SQLException {
        IngredientDto ingredent = new IngredientDto();
        ingredent.setRecipeId(resultSet.getString("r_id"));
        ingredent.setIngredientName(resultSet.getString("name"));
        ingredent.setIngredientAmount(resultSet.getString("amount"));
        return ingredent;
    }
}
