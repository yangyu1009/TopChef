package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.TagDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<TagDto> {
    @Override
    public TagDto mapRow(ResultSet resultSet, int i) throws SQLException {
        TagDto tag = new TagDto();
        tag.setTagId(resultSet.getString("id"));
        tag.setTagName(resultSet.getString("name"));
        return tag;
    }
}
