import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Subscription} from "../../models/get-user-profile";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent {
  @Input() subscription!: Subscription;
  @Output()
  onUnsubscription: EventEmitter<number>;

  constructor(
    private snackbar: MatSnackBar,
    private themeService: ThemeService
  ) {
    this.onUnsubscription = new EventEmitter<number>();
  }

  unsubscribe(themeId: number) {
    this.themeService.unsubscribe(themeId).subscribe({
      next: () => {
        this.snackbar.open('Vous avez été désabonné', '', {duration: 2000});
        this.onUnsubscription.emit(themeId);
      }
    })
  }
}
