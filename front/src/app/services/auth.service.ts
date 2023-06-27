import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl: string = '/api/auth';

  constructor(private readonly http: HttpClient) { }

  register(registerDto: RegisterDto) {
    return this.http.post(`${this.baseUrl}/register`, registerDto);
  }

  login(loginDto: LoginDto) {
    return this.http.post<LoginDto>(`${this.baseUrl}/login`, loginDto);
  }
}

export interface RegisterDto {
  username: string;
  email: string;
  password: string;
}

export interface LoginDto {
  email: string;
  password: string;
}
