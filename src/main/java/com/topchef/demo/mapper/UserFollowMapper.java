package com.topchef.demo.mapper;

import com.topchef.demo.dto.FollowerDto;
import com.topchef.demo.dto.ShowFollowerDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFollowMapper implements RowMapper<ShowFollowerDto> {
    @Override
    public ShowFollowerDto mapRow(ResultSet resultSet, int i) throws SQLException {
        ShowFollowerDto follower = new ShowFollowerDto();
        follower.setFollowerId(resultSet.getString("f_id"));
        follower.setFollowerName(resultSet.getString("u_name"));
        return follower;
    }
}
