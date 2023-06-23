import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import {NgOptimizedImage} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import { NavigationArrowComponent } from './components/navigation-arrow/navigation-arrow.component';
import { MainMenuComponent } from './components/main-menu/main-menu.component';
import {ReactiveFormsModule} from "@angular/forms";
import { FormControlErrorComponent } from './components/form-control-error/form-control-error.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleComponent } from './components/article/article.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ThemeComponent } from './components/theme/theme.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    NavigationArrowComponent,
    MainMenuComponent,
    FormControlErrorComponent,
    ArticlesComponent,
    ArticleComponent,
    ThemesComponent,
    ThemeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    NgOptimizedImage,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
