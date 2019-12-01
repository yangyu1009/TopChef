import { Component, OnInit } from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  images = [1, 2, 3].map(() => `https://picsum.photos/900/500?random&t=${Math.random()}`);
  loginSuccess = false;

  constructor() { }


  async ngOnInit() {
    try {
      this.loginSuccess = await axios.get("http://localhost:5005/user/login").then(function(response) {
        return response.data;
      });
    } catch (e) {
      // TODO handle get data fail later
      //console.table(`Error connecting with server: ${e}`);
      console.log('error');
    }

  }
  async onClick() {
    var islogin;


   }
   isSuccess() {
    return this.loginSuccess;
   }

}

