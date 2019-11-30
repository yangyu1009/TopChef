package com.topchef.demo.dto.tableEntity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PracticeDto {
    private String recipeId;
    private int index;
    private String description;
    private String image;
}
