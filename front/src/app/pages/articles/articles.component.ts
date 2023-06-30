import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ArticleService, SortOrder} from "../../services/article.service";
import {Article} from "../../models/article";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {
  isLoading = false;
  articles: Article[] = [];
  sortOrder: SortOrder = 'DESC';

  constructor(private readonly router: Router, private readonly articleService: ArticleService) {
  }

  async navigate() {
    await this.router.navigate(['/article']);
  }

  ngOnInit(): void {
    this.fetchArticles();
  }

  private fetchArticles() {
    this.isLoading = true;

    this.articleService.getAll(this.sortOrder).subscribe({
      next: articles => {
        this.isLoading = false;
        this.articles = articles
      }
    });
  }

  sort() {
    this.sortOrder = this.sortOrder == 'DESC' ? 'ASC': 'DESC';
    this.fetchArticles();
  }
}
