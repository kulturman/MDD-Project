import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ThemeService} from "../../services/theme.service";
import {Theme} from "../../models/theme";

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.css']
})
export class NewArticleComponent implements OnInit {
  formGroup!: FormGroup;
  themes: Theme[] = [];

  constructor(private readonly formBuilder: FormBuilder, private readonly themeService: ThemeService) {
  }
  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      theme: ['', Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required],
    });

    this.themeService.getAll().subscribe({
      next: themes => this.themes = themes
    });
  }

  onSubmit() {
  }
}
