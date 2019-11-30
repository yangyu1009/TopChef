package com.topchef.demo.service;

import com.topchef.demo.dto.handlesEntity.RecipeDetailDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.handlesEntity.SubscribeAndViewNumberDto;
import com.topchef.demo.repository.TopChefRecipeDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RecipeService implements TopChefRecipeDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    private TableSearchService tableSearchService;

    public RecipeService(TableSearchService tableSearchService) {
        this.tableSearchService = tableSearchService;
    }

    @Override
    public RecipeDetailDto getRecipeDetail(String recipeId) {

        return null;
    }

}
