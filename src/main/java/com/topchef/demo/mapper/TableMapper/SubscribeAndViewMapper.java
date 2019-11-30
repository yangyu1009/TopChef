package com.topchef.demo.mapper.TableMapper;

import com.topchef.demo.dto.handlesEntity.SubscribeAndViewNumberDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscribeAndViewMapper implements RowMapper<SubscribeAndViewNumberDto> {
    @Override
    public SubscribeAndViewNumberDto mapRow(ResultSet resultSet, int i) throws SQLException {
        SubscribeAndViewNumberDto svs = new SubscribeAndViewNumberDto();
        svs.setSubscribeNumber(resultSet.getInt("S_number"));
        svs.setViewNumber(resultSet.getInt("V_number"));
        return svs;
    }
}
