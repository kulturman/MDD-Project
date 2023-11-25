import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {ShowArticle} from "../../models/showArticle";
import {CommentService} from "../../services/comment.service";

@Component({
  selector: 'app-show-article',
  templateUrl: './show-article.component.html',
  styleUrls: ['./show-article.component.css']
})
export class ShowArticleComponent implements OnInit {
  isLoading = false;
  articleId!: number;
  article!: ShowArticle;
  newComment: string = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private articleService: ArticleService,
    private commentService: CommentService
  ) {
  }

  ngOnInit(): void {
    this.isLoading = true;

    this.activatedRoute.params.subscribe(params => {
      this.articleId = +params['id'];
    });

    this.articleService.getById(this.articleId).subscribe({
      next: article => {
        this.article = article;
      },
      complete: () => {
        this.isLoading = false;
      }
    });

  }

  submitComment() {
    if (!this.newComment.trim()) {
      alert('Veuillez entrer un commentaire');
      return;
    }

    this.commentService.saveComment(this.articleId, this.newComment.trim()).subscribe({
      next: result => {
        this.article = {
          ...this.article,
          comments: [
            ...this.article.comments,
            {
              id: result.id,
              content: this.newComment.trim(),
              author: {
                id: result.userId,
                username: result.username
              }
            }
          ]
        }
        this.newComment = '';
      }
    });
  }
}
