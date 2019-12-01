package com.topchef.demo.service;
;
import com.topchef.demo.repository.PracticeDao;
import org.hibernate.annotations.Source;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public class PracticeService implements PracticeDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void deletePractice(String recipeId, String index) {
        String sql = "delete from practice where r_id = ? and indexn = ?";
        jdbcTemplateObject.update(sql, recipeId, index);
    }
}
