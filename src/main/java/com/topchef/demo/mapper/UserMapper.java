package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet resultSet, int i) throws SQLException {
        UserDto user = new UserDto();
        user.setUserId(resultSet.getString("u_id"));
        user.setName(resultSet.getString("u_name"));
        user.setEmail(resultSet.getString("email"));
        user.setCreateDate(resultSet.getString("create_date"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
