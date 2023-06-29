import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Article} from "../models/article";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  constructor(private readonly http: HttpClient) { }

  getAll(): Observable<Article[]> {
    return this.http.get<Article[]>('/api/articles');
  }
}
