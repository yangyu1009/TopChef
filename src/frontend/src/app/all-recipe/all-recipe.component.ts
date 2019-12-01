import { Component, OnInit } from '@angular/core';
import axios from 'axios';
import {Router} from '@angular/router';

@Component({
  selector: 'app-all-recipe',
  templateUrl: './all-recipe.component.html',
  styleUrls: ['./all-recipe.component.css']
})
export class AllRecipeComponent implements OnInit {
recipeChunks
  constructor(private router: Router) { }

  async ngOnInit() {
    try {
      const data = await axios.get('http://localhost:5005/recipe/all');
      this.recipeChunks = this.chunks(data.data, 4);
      //console.table(this.bookChunks);
    } catch (e) {
      // TODO handle get data fail later
      //console.table(`Error connecting with server: ${e}`);
    }

  }
  onSelect(recipe) {
    this.router.navigate(['/item', recipe.recipeId]);
  }
  chunks = (array, size) => {
    if (array === undefined) {
      return array;
    }
    const results = [];
    while (array.length) {
      results.push(array.splice(0, size));
    }
    return results;
  };

}
