package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "subscribe")
@IdClass(TopChefSubscribe.UserRecipe.class)
@Entity(name = "smartkitchen")
public class TopChefSubscribe {
    @Id
    @Column(name = "u_id")
    private String userId;

    @Id
    @Column(name = "r_id")
    private String recipeId;

    public class UserRecipe implements Serializable{
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
