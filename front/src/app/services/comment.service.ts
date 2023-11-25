import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NewCommentResponse} from "../models/newCommentResponse";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(private http: HttpClient) { }

  saveComment(postId: number, comment: string) {
    return this.http.post<NewCommentResponse>(`/api/articles/${postId}/comment`, {
      content: comment
    });
  }
}
