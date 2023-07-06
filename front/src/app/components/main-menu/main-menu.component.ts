import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  isAuthenticated: boolean = false;
  showDropdown = false;
  currentRoute!: string;

  constructor(public authService: AuthService, private router: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.isAuthenticated = this.authService.getAuthToken() !== null;
    this.currentRoute = this.router.snapshot.url[0].path;
  }

  toggleDropdown(isVisible: boolean) {
    this.showDropdown = isVisible;
  }

  logout() {
    this.authService.logout();
  }
}
