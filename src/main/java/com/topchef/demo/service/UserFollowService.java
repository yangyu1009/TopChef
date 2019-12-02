package com.topchef.demo.service;

import com.topchef.demo.utils.CurrentUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserFollowService {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    public void insert(String publisherId){
        String sql = "insert into user_follow(p_id, f_id) values(?,?)";
        jdbcTemplateObject.update(sql, publisherId, CurrentUser.CurrentUserId.get(0));
    }

    public int getFollowerNumber(String publisherId){
        String sql = "select count(f_id) from user_follow where p_id = ?";
        int num = jdbcTemplateObject.queryForObject(sql, new Object[]{publisherId}, Integer.class);
        return num;
    }

    public int getFollowedNumber(String followerId){
        String sql = "select count(p_id) from user_follow where f_id = ?";
        int num = jdbcTemplateObject.queryForObject(sql, new Object[]{followerId}, Integer.class);
        return num;
    }
}
