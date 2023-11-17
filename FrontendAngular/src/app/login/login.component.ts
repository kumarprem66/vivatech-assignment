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
      this.http.get<Users[]>('http://localhost:3000/user').subscribe(
        (users: Users[]) => {
          const user = users.find(
            (u) => u.username === formData.username && u.password === formData.password
          );
          if (user) {
            // console.log('Authentication successful');
            alert('Authentication successful')
            // Implement session management or token handling here
            this.loginForm.reset();
            localStorage.setItem('currentUser', JSON.stringify(user));
            
            this.router.navigate(['/dashboard']);
          } else {
            alert('Authentication failed')
            // console.log('Authentication failed');
          }
        }
      );
    }
  }
}
