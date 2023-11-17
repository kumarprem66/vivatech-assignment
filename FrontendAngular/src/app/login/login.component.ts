import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Users } from '../data-type';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http:HttpClient,
    private router:Router
    
    ){
    this.loginForm = this.fb.group({
      username: ['',Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    
  }

 

  onSubmit() {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;

      // Send a GET request to the JSON Server to fetch user data
      
    }
  }
}
