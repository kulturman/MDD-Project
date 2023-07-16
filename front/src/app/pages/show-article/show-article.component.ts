import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {ShowArticle} from "../../models/showArticle";

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

  constructor(private activatedRoute: ActivatedRoute, private articleService: ArticleService) {
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
  }
}
