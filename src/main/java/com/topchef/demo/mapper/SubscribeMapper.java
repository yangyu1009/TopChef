package com.topchef.demo.mapper;

import com.topchef.demo.dto.SubscribeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscribeMapper implements RowMapper<SubscribeDto> {
    @Override
    public SubscribeDto mapRow(ResultSet resultSet, int i) throws SQLException {
        SubscribeDto subscribe = new SubscribeDto();
        subscribe.setUserId(resultSet.getString("u_id"));
        subscribe.setRecipeId(resultSet.getString("r_id"));
        return subscribe;
    }
}
