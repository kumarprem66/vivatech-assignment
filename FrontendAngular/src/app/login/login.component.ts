import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Users } from '../data-type';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

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
    private router:Router,
    private userService:UserService
    
    ){
    this.loginForm = this.fb.group({
      email: ['',Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    
  }

 

  onSubmit() {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;

    
      
      // this.http.post<any>('http://localhost:8088/otp/signIn', formData)
      this.userService.login(formData)
      .subscribe(
        (response) => {
          alert('Login successful: ' + response);
          // Redirect to another page upon successful login
          // this.router.navigate(['/dashboard']);
        },
        (error) => {
          alert('Login failed. Please check your username and password.');
          console.error('Login failed', error);
          // Handle error, e.g., display a message to the user
        }
      );
    }
  }
}
