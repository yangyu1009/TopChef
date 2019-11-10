export default class ItemDetail {
  price: string;
  title: string;
  imgUrl: string;
  id: string;


  constructor(price: string, title: string, imgUrl: string, id: string) {
    this.price = price;
    this.title = title;
    this.imgUrl = imgUrl;
    this.id = id;
  }
}
