package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.UserFollowDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFollowMapper implements RowMapper<UserFollowDto> {
    @Override
    public UserFollowDto mapRow(ResultSet resultSet, int i) throws SQLException {
        UserFollowDto userFollow = new UserFollowDto();
        userFollow.setPublisherId(resultSet.getString("p_id"));
        userFollow.setFollowerId(resultSet.getString("f_id"));
        return userFollow;
    }
}
