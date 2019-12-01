package com.topchef.demo.dto.tableEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeTagDto {
    private String recipeId;
    private String tagId;
}
