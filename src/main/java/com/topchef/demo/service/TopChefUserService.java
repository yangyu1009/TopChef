package com.topchef.demo.service;


import com.topchef.demo.dto.CreateRecipeDto;
import com.topchef.demo.dto.RecipeDto;
import com.topchef.demo.repository.TopChefRecipeDao;
import com.topchef.demo.repository.UserDao;
import com.topchef.demo.utils.CreateTimeUtils;
import com.topchef.demo.utils.IDUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TopChefUserService implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void createCecipe(CreateRecipeDto createRecipe) {
        createRecipe.setRecipeId(IDUtils.genRecipeId());
        String sql ="insert into recipe (r_id, r_name, description, image, S_number, V_number, Pub_time, u_id) values (?,?,?,?,?,?,?,?)";
        jdbcTemplateObject.update(sql, IDUtils.genRecipeId(), createRecipe.getRecipeName(), createRecipe.getDescription(), createRecipe.getImage(), 1, 1, CreateTimeUtils.genCreateTime(), "gao");
        for(int i=0; i <createRecipe.getPractice().size(); i++){
            sql ="insert into practice(r_id, index, description, image) values (?,?,?,?)";
            jdbcTemplateObject.update(sql, createRecipe.getRecipeId(), (i+1), createRecipe.getPracticeDescription().get(i), createRecipe.getPracticeImage().get(i));
        }
        for(int i=0; i < createRecipe.getIngredientName().size(); i++){
            sql ="insert into ingredient(r_id, name, amount) values (?,?,?)";
            jdbcTemplateObject.update(sql, createRecipe.getRecipeId(),createRecipe.getIngredientName().get(i), createRecipe.getIngredientNumber().get(i));
        }
    }

}
