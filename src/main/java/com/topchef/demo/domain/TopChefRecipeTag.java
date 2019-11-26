package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@IdClass(TopChefRecipeTag.RecipeTag.class)
@Entity
public class TopChefRecipeTag {
    @Id
    @Column
    private String recipeId;

    @Id
    @Column
    private String tagId;

    public class RecipeTag implements Serializable{
        private String recipeId;
        private String tagId;

        public String getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(String recipeId) {
            this.recipeId = recipeId;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }
    }
}
