import { Component, OnInit } from '@angular/core';
//import * as $ from 'jquery';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    // $( "#myform" ).on( "submit", function( event ) {
    //   event.preventDefault();
    //   let data = $( this ).serialize();
    //   //make our request here
    //   $.post( "/login", function( data ) {
    //     console.log(data);
    //   });
    // });
  }

}
