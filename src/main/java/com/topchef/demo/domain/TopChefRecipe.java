package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "recipe")
@Entity
public class TopChefRecipe {

    @Id
    @Column(name = "r_id")
    private String recipeId;

    @Column(name = "r_name")
    private String recipeName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "S_number")
    private int sNumber;

    @Column(name = "V_number")
    private int vNumber;

    @Column(name = "Pub_time")
    private String publishTime;

    @Column(name = "u_id")
    private String userId;
}
