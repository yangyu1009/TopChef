package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@IdClass(TopChefPractice.RecipePractice.class)
@Entity
public class TopChefPractice {
    @Id
    @Column
    private String recipeId;

    @Id
    @Column
    private int index;

    @Column
    private String description;

    @Column
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
