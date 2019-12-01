import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ItemComponent} from "./item/item.component";
import {HomeComponent} from "./home/home.component";
import {CreateRecipeComponent} from "./create-recipe/create-recipe.component";
import {RegisterComponent} from "./register/register.component";
import {AllRecipeComponent} from "./all-recipe/all-recipe.component";
import {SearchComponent} from "./search/search.component";


const routes: Routes = [
  {path: 'item', component: ItemComponent},
  {path: '', component: HomeComponent},
  {path: 'create', component: CreateRecipeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'all', component: AllRecipeComponent},
  {path: 'search/:searchKey', component: SearchComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
