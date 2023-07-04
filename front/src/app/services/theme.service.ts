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
    return new Observable<Theme[]>(subscriber => {
      subscriber.next([
        {id: 1, label: 'Theme 1', content: 'Le Lorem Ipsum est'},
        {id: 2, label: 'Theme 2', content: 'Le Lorem Ipsum est'},
        {id: 3, label: 'Theme 3', content: 'Le Lorem Ipsum est'},
        {id: 4, label: 'Theme 4', content: 'Le Lorem Ipsum est'},
      ])
    })
  }
}
