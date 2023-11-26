import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Theme} from "../models/theme";

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  constructor(private readonly http: HttpClient) { }

  getAll(): Observable<Theme[]> {
    return this.http.get<Theme[]>('/api/themes');
  }

  subscribe(themeId: number) {
    return this.http.post(`/api/themes/${themeId}/subscribe`, {});
  }

  unsubscribe(themeId: number) {
    return this.http.post(`/api/themes/${themeId}/unsubscribe`, {});
  }
}
