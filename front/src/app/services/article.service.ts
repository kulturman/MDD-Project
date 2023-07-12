import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Article} from "../models/article";
import {Observable} from "rxjs";
import {CreateArticle} from "../models/createArticle";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  constructor(private readonly http: HttpClient) { }

  getAll(sortOrder: SortOrder): Observable<Article[]> {
    return this.http.get<Article[]>(`/api/articles?sort=${sortOrder}`);
  }

  getById(id: number): Observable<Article> {
    return new Observable<Article>(subscriber => {
      subscriber.next({
        title: "The title",
        createdAt: "2023-06-29T01:00:00",
        content: "A simple content",
        id: 1,
        author: "An author",
        theme: "Theme bidon"
      });
    })
    //return this.http.get<Article>(`/api/articles/${id}`);
  }

  save(articleToCreate: CreateArticle) {
    return this.http.post('/api/articles', articleToCreate);
  }
}

export type SortOrder = 'DESC' | 'ASC';
