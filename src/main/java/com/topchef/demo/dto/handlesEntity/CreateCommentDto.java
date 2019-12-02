package com.topchef.demo.dto.handlesEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCommentDto {
    private String recipeId;
    private String description;
}
