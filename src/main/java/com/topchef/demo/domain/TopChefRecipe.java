package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class TopChefRecipe {

    @Id
    @Column
    private String recipeId;

    Column
    private String recipeName;

    Column
    private String description;

    Column
    private String image;

    Column
    private int sNumber;

    Column
    private int vNumber;

    Column
    private String publishTime;
}
