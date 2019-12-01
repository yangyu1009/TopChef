export default class Recipe {
  recipeName: string;
  recipeId: string;
  description: string;
  image: string;

  constructor(recipeNamd: string, recipeId: string, description: string, image: string) {
    this.recipeName = recipeNamd;
    this.recipeId = recipeId;
    this.description = description;
    this.image = image;
  }
}


