package com.topchef.demo.mapper;

import com.topchef.demo.dto.tableEntity.CommentDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<CommentDto> {
    @Override
    public CommentDto mapRow(ResultSet resultSet, int i) throws SQLException {
        CommentDto comment = new CommentDto();
        comment.setUserId(resultSet.getString("u_id"));
        comment.setRecipeId(resultSet.getString("r_id"));
        comment.setDescription(resultSet.getString("description"));
        return comment;
    }
}
