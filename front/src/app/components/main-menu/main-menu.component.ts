import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  isAuthenticated: boolean = false;
  showDropdown = false;

  constructor(public authService: AuthService) {
  }

  ngOnInit(): void {
    this.isAuthenticated = this.authService.getAuthToken() !== null;
  }

  toggleDropdown(isVisible: boolean) {
    this.showDropdown = isVisible;
  }

  logout() {
    this.authService.logout();
  }
}
