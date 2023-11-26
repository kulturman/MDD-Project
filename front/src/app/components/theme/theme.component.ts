import {Component, Input} from '@angular/core';
import {Theme} from "../../models/theme";
import {ThemeService} from "../../services/theme.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.css'],
})
export class ThemeComponent {
  constructor(
    private themeService: ThemeService,
    private snackbar: MatSnackBar
  ) {}

  @Input() theme!: Theme;

  subscribe(themeId: number) {
    this.themeService.subscribe(themeId).subscribe({
      next: () => {
        this.snackbar.open('Abonnement réussi', '', {duration: 2000});
      },
      error: (err) => {
        const message = err.status === 400 ? 'Vous êtes déjà abonné à ce thème':
          'Une erreur est survenue';
        this.snackbar.open(message, '', {duration: 2000});
      }
     })
  }
}
