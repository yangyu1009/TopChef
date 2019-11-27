package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "practice")
@IdClass(TopChefPractice.RecipePractice.class)
@Entity
public class TopChefPractice {
    @Id
    @Column(name = "r_id")
    private String recipeId;

    @Id
    @Column(name = "indexn")
    private int index;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    public class RecipePractice implements Serializable{
        private String recipeId;
        private int index;

        public String getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(String recipeId) {
            this.recipeId = recipeId;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
