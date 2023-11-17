import { Component,OnInit } from '@angular/core';
import { Users } from '../data-type';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  currentUser: Users; // Define a property to hold the user data

  constructor() {
    // Retrieve user data from localStorage
    const user = localStorage.getItem('currentUser');
    this.currentUser = user ? JSON.parse(user) : null;
  }

  ngOnInit(): void {}
}
