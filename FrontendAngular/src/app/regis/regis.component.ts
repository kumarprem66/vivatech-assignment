import { Component,OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http'
import { Router } from '@angular/router';
import { Users,UserLogin } from '../data-type';
import { UserService } from '../user.service';


@Component({
  selector: 'app-regis',
  templateUrl: './regis.component.html',
  styleUrls: ['./regis.component.css']
})
export class RegisComponent implements OnInit{

  registrationForm: FormGroup;
  

  constructor(private fb:FormBuilder,private http: HttpClient,private router:Router,private userservice:UserService){
    this.registrationForm = this.fb.group({
      username:['',[Validators.required]],
      password:['',[Validators.required]],
      email:['',[Validators.required,Validators.email]],
    })
  }

  ngOnInit(): void {
    
  }

  onSubmit(){
    if(this.registrationForm.valid){
      const formData = this.registrationForm.value;
      this.userservice.register(formData).
      // this.http.post('http://localhost:8088/otp/register',formData)
      subscribe(
        (response) => {
        
          alert('Registration success.');
          // Redirect to login page upon successful registration
          // this.router.navigate(['/login']);
        },
        (error) => {
          alert('Registration failed. Please try again.');
          console.error('Registration failed', error);
          // Handle error, e.g., display a message to the user
        }
      )
    }
  }

  

}
