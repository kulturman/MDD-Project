import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private readonly router: Router) {
  }
  async navigate(link: string) {
    await this.router.navigate([link]);
  }
}
