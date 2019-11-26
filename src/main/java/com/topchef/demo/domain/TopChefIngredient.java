package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@IdClass(TopChefIngredient.RecipeIngredient.class)
@Entity
public class TopChefIngredient {

    @Id
    @Column
    private String recipeId;

    @Id
    @Column
    private String name;

    @Column
    private String amount;

   public class RecipeIngredient implements Serializable{
       private String recipeId;
       private String name;

       public String getRecipeId() {
           return recipeId;
       }

       public void setRecipeId(String recipeId) {
           this.recipeId = recipeId;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }
}
