package com.topchef.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SubscribeService {
    @Resource
    JdbcTemplate jdbcTemplate;

    public int getSubscirbeNumber(String userId){
        String sql = "select count(r_id) from subscribe where u_id = ?";
        int num = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return num;
    }
}
