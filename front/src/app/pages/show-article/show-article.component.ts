import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {Article} from "../../models/article";
import {Comment} from "../../models/comment";

@Component({
  selector: 'app-show-article',
  templateUrl: './show-article.component.html',
  styleUrls: ['./show-article.component.css']
})
export class ShowArticleComponent implements OnInit {
  articleId!: number;
  article!: Article;
  comments: Comment[] = [
    {
      username: "kulturman",
      comment: "A comment"
    },
    {
      username: "kulturman",
      comment: "A comment"
    }
  ];
  newComment: string = '';

  constructor(private activatedRoute: ActivatedRoute, private articleService: ArticleService) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.articleId = +params['id'];
    });

    this.articleService.getById(this.articleId).subscribe({
      next: article => this.article = article
    });

  }

  submitComment() {
    if (!this.newComment.trim()) {
      alert('Veuillez entrer un commentaire');
    }
  }
}
