import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Users,UserLogin } from './data-type';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8088/otp'; // Spring Boot backend URL

  constructor(private http: HttpClient) {}

  register(user: Users): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  login(user: UserLogin): Observable<any> {
    return this.http.post(`${this.apiUrl}/signIn`, user);
  }
}
