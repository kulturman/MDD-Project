import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  isAuthenticated: boolean = false;
  showDropdown = false;
  currentRoute!: string;
  showBackdrop = false;

  constructor(
    public authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.isAuthenticated = this.authService.getAuthToken() !== null;
    this.currentRoute = this.activatedRoute.snapshot.url[0].path;
  }

  toggleDropdown(isVisible: boolean) {
    this.showDropdown = isVisible;
  }

  logout() {
    this.authService.logout();
  }

  navigate(url: string) {
    this.router.navigate([url]);
  }

  onToggleButtonClick() {
    this.showBackdrop = true;
  }

  closeBackdrop() {
    this.showBackdrop = false;
  }
}
