import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../services/theme.service";
import {Theme} from "../../models/theme";

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.css']
})
export class ThemesComponent implements OnInit {
  themes: Theme[] = [];
  isLoading!: boolean;

  constructor(private readonly themeService: ThemeService) {
  }

  ngOnInit(): void {
    this.isLoading = true;

    this.themeService.getAll().subscribe({
      next: themes => {
        this.isLoading = false;
        this.themes = themes;
      }
    })
  }
}
