import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation-arrow',
  templateUrl: './navigation-arrow.component.html',
  styleUrls: ['./navigation-arrow.component.css']
})
export class NavigationArrowComponent {
  @Input() redirectTo: string = '';
  constructor(private readonly router: Router) {
  }

  async navigate() {
    await this.router.navigate([this.redirectTo]);
  }
}
