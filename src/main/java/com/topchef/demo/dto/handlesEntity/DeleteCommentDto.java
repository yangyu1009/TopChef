package com.topchef.demo.dto.handlesEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteCommentDto {
    private String userId;
    private String recipeId;
}
