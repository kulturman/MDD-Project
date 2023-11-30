import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterDto} from "../models/register.dto";
import {LoginDto} from "../models/login.dto";
import {LoginResponse} from "../models/login.response";
import {Router} from "@angular/router";
import {GetUserProfile} from "../models/get-user-profile";
import {Observable} from "rxjs";
import {UpdateUserProfile} from "../models/update-user-profile";

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

  constructor(private readonly http: HttpClient, private router: Router) { }

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

  updateProfile(updateUserProfile: UpdateUserProfile) {
    return this.http.post(`${this.baseUrl}/me/update`, updateUserProfile)
  }

  getAuthToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  logout() {
    this.authState.isAuthenticated = false;
    this.authState.token = null;
    localStorage.removeItem(this.TOKEN_KEY);
  }

  getProfile(): Observable<GetUserProfile> {
    return this.http.get<GetUserProfile>('/api/auth/me');
  }

  canActivate(): boolean {
    if (!this.authState.isAuthenticated) {
      this.router.navigate(['/login']);
    }
    return true;
  }
}

interface AuthState {
  isAuthenticated: boolean,
  token: string | null
}
