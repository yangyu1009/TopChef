package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@IdClass(TopChefPractice.RecipePractice.class)
@Entity
public class TopChefComment {
    @Id
    @Column
    private String userId;

    @Id
    @Column
    private String recipeId;

    @Column
    private String description;

    public class Comment implements Serializable{
        private String userId;
        private String recipeId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(String recipeId) {
            this.recipeId = recipeId;
        }
    }
}
