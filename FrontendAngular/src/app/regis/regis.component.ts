import { Component,OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http'



@Component({
  selector: 'app-regis',
  templateUrl: './regis.component.html',
  styleUrls: ['./regis.component.css']
})
export class RegisComponent implements OnInit{

  registrationForm: FormGroup;

  constructor(private fb:FormBuilder,private http: HttpClient){
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

      this.http.post('http://localhost:8088/otp/register',formData).subscribe((response)=>{
        alert("Registration complted"+response)
        // console.log("Registration complted",response)
        this.registrationForm.reset();
      })
    }
  }

  

}
