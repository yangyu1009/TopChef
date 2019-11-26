package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "comment")
@IdClass(TopChefPractice.RecipePractice.class)
@Entity(name = "smartkitchen")
public class TopChefComment {
    @Id
    @Column(name = "u_id")
    private String userId;

    @Id
    @Column(name = "r_id")
    private String recipeId;

    @Column(name = "description")
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
