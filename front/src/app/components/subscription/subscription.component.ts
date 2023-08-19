import {Component, Input} from '@angular/core';
import {Subscription} from "../../models/get-user-profile";

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent {
  @Input() subscription!: Subscription;

  unsubscribe() {
    alert(this.subscription.id);
  }
}
