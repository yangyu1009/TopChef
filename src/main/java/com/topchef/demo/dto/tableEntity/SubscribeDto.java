package com.topchef.demo.dto.tableEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscribeDto {
    private String userId;
    private String recipeId;
}
