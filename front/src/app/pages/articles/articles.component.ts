import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent {
  @Input() title!: string;
  @Input() date!: string;
  @Input() author!: string;

  constructor(private readonly router: Router) {
  }
  async navigate() {
    await this.router.navigate(['/article']);
  }
}
