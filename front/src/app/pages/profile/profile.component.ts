import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {GetUserProfile} from "../../models/get-user-profile";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile!: GetUserProfile;
  formGroup!: FormGroup;

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
    });

    this.authService.getProfile().subscribe({
      next: profile => {
        this.profile = profile;
        this.formGroup.get('username')?.setValue(profile.username);
        this.formGroup.get('email')?.setValue(profile.email);
      }
    })
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['']);
  }
}
