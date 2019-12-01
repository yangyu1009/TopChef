package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.PracticeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracticeMapper implements RowMapper<PracticeDto>{
    @Override
    public PracticeDto mapRow(ResultSet resultSet, int i) throws SQLException {
        PracticeDto practice = new PracticeDto();
        practice.setRecipeId(resultSet.getString("r_id"));
        practice.setIndex(resultSet.getInt("indexn"));
        practice.setDescription(resultSet.getString("description"));
        practice.setImage(resultSet.getString("image"));
        return practice;
    }
}
