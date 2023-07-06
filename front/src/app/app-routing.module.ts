import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ArticlesComponent} from "./pages/articles/articles.component";
import {ThemesComponent} from "./pages/themes/themes.component";
import {NewArticleComponent} from "./pages/new-article/new-article.component";
import {ShowArticleComponent} from "./pages/show-article/show-article.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {canActivate} from "./auth.guard";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: 'articles',
    component: ArticlesComponent,
    canActivate: [canActivate]
  },
  {
    path: 'article',
    component: NewArticleComponent,
    canActivate: [canActivate]
  },
  {
    path: 'articles/:id',
    component: ShowArticleComponent,
    canActivate: [canActivate]
  },
  {
    path: 'themes',
    component: ThemesComponent,
    canActivate: [canActivate]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [canActivate]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
