import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Article} from "../models/article";
import {Observable} from "rxjs";
import {CreateArticle} from "../models/createArticle";
import {ShowArticle} from "../models/showArticle";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  constructor(private readonly http: HttpClient) { }

  getAll(sortOrder: SortOrder): Observable<Article[]> {
    return this.http.get<Article[]>(`/api/articles?sort=${sortOrder}`);
  }

  getById(id: number): Observable<ShowArticle> {
    return this.http.get<ShowArticle>(`/api/articles/${id}`);
  }

  save(articleToCreate: CreateArticle) {
    return this.http.post('/api/articles', articleToCreate);
  }
}

export type SortOrder = 'DESC' | 'ASC';
