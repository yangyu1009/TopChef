package com.topchef.demo.service;

import com.topchef.demo.dto.handlesEntity.DeleteCommentDto;
import com.topchef.demo.dto.handlesEntity.RegisterDto;
import com.topchef.demo.dto.tableEntity.RecipeDto;
import com.topchef.demo.dto.tableEntity.UserDto;
import com.topchef.demo.repository.AdministratorDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AdministratorService implements AdministratorDao {
    @Resource
    private JdbcTemplate jdbcTemplateObject;

    private UserHandlesService userHandlesService;
    private TableSearchService tableSearchService;

    public AdministratorService(UserHandlesService userHandlesService, TableSearchService tableSearchService) {
        this.userHandlesService = userHandlesService;
        this.tableSearchService = tableSearchService;
    }


    @Override
    public void create(RegisterDto registerDto) {
        userHandlesService.register(registerDto);
    }

    @Override
    public void deleteUser(String userId) {
        String sql = "delete from user where u_id=?";
        jdbcTemplateObject.update(sql, userId);
    }

    @Override
    public List<UserDto> getAllUser() {
        return tableSearchService.getAllUsers();
    }

    @Override
    public void deleteRecipe(String recipeId) {
        userHandlesService.deleteRecipe(recipeId);
    }

    @Override
    public List<RecipeDto> getAllRecipe() {
        return tableSearchService.getAllRecipes();
    }

    @Override
    public void deleteComment(DeleteCommentDto deleteComment) {
        String sql = "delete from adnimistrator where u_id=? and r_id=?";
        jdbcTemplateObject.update(sql, deleteComment.getUserId(), deleteComment.getRecipeId());
    }
}
