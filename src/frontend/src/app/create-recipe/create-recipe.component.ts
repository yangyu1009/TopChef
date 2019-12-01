import { Component, OnInit } from '@angular/core';
//import * as $ from 'jquery';
import {creatRecipeService} from '../create-recipe/createRecipeService.service';
import {RecipeInfo} from "./model/recipeInfo";

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent implements OnInit {
  number1 = 0;
  number2 = 0;
  myRecipeList: RecipeInfo[];

  constructor(private service: creatRecipeService) { }

  ngOnInit() {

  }
  // var number = 0; //计数
   add1(){
    var newchild = document.createElement("p");
    var group = 'ingredient'+this.number1+'：<input type="text" name="ingredient['+this.number1+']" />&nbsp;&nbsp;weight'+this.number1+'：<input type="text" name="weight['+this.number1+']" /><br/>';
    newchild.innerHTML = group;
    var groups = document.getElementById("groups1");
    groups.appendChild(newchild);
    this.number1++;
  }
  add2(){
    var newchild = document.createElement("p");
    var group = 'step'+this.number2+'：<input type="text" name="username['+this.number2+']" />&nbsp;&nbsp;description'+this.number2+'：<input type="text" name="password['+this.number2+']" /><br/>';
    newchild.innerHTML = group;
    var groups = document.getElementById("groups2");
    groups.appendChild(newchild);
    this.number2++;
  }

  add(recipeName: string): void {
    recipeName = recipeName.trim();
    if (!recipeName) {
      return;
    }
    this.service.addRecipe({recipeName} as RecipeInfo).subscribe(recipe => {
      this.myRecipeList.push(recipe);
    })
  }

}
