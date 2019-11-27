package com.topchef.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReciptDto {
    private String recipeId;
    private String recipeName;
    private String description;
    private String image;
    private int sNumber;
    private int vNumber;
    private String publishTime;
    private String userId;
}



