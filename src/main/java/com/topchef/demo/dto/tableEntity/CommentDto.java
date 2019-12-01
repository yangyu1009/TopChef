package com.topchef.demo.dto.tableEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private String userId;
    private String recipeId;
    private String description;
}
