import {Component, Input} from '@angular/core';
import {Theme} from "../../models/theme";

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.css']
})
export class ThemeComponent {
  @Input() theme!: Theme;
}
