import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ArticleService} from "../../services/article.service";
import {Article} from "../../models/article";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {
  isLoading = true;
  articles: Article[] = [];

  constructor(private readonly router: Router, private readonly articleService: ArticleService) {
  }
  async navigate() {
    await this.router.navigate(['/article']);
  }

  ngOnInit(): void {
    this.articleService.getAll().subscribe({
      next: articles => {
        this.isLoading = false;
        this.articles = articles
      }
    })
  }
}
