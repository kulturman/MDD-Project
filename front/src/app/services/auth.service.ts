import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterDto} from "../models/register.dto";
import {LoginDto} from "../models/login.dto";
import {LoginResponse} from "../models/login.response";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl: string = '/api/auth';
  private TOKEN_KEY = 'token';
  private authState: AuthState = {
    isAuthenticated: false,
    token: null
  };

  constructor(private readonly http: HttpClient) { }

  register(registerDto: RegisterDto) {
    return this.http.post(`${this.baseUrl}/register`, registerDto);
  }

  login(loginDto: LoginDto) {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, loginDto);
  }

  registerAuthState(token: string) {
    this.authState.isAuthenticated = true;
    this.authState.token = token;
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getAuthToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }
}

interface AuthState {
  isAuthenticated: boolean,
  token: string | null
}
