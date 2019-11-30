package com.topchef.demo.mapper.TableMapper;

import com.topchef.demo.dto.handlesEntity.PublisherAndFollowerDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherAndFollowerMapper implements RowMapper<PublisherAndFollowerDto> {
    @Override
    public PublisherAndFollowerDto mapRow(ResultSet resultSet, int i) throws SQLException {
        PublisherAndFollowerDto userinfo = new PublisherAndFollowerDto();
        userinfo.setPublisherId(resultSet.getString("p_id"));
        userinfo.setFollowerId(resultSet.getString("f_id"));
        userinfo.setFollowerName(resultSet.getString("u_name"));
        return userinfo;
    }
}
