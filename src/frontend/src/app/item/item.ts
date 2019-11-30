export default class ItemDetail {
  recipeId : string;
  recipeName: string;
  description: string;
  image: string;
  publishTime: string;
  userId: string;


  constructor(recipeId : string, recipeName: string, description: string, image: string, publishTime: string, userId: string) {
    this.recipeId = recipeId;
    this.recipeName = recipeName;
    this.description = description;
    this.image = image;
    this.publishTime = publishTime;
    this.userId = userId;
  }
}
