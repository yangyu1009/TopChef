import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  searchKey: string;


  constructor(private router: Router) { }

  ngOnInit() {
  }
  searchRequest(searchKey) {
    this.router.navigate(['./search', this.searchKey]);
  }

}
