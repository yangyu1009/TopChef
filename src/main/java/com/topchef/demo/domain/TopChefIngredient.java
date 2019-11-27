package com.topchef.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "ingredient")
@IdClass(TopChefIngredient.RecipeIngredient.class)
@Entity(name = "smartkitchen")
public class TopChefIngredient {

    @Id
    @Column(name = "r_id")
    private String recipeId;

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "amount")
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
