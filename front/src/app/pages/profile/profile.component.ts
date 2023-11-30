import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {GetUserProfile} from "../../models/get-user-profile";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SwalComponent} from "@sweetalert2/ngx-sweetalert2";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile!: GetUserProfile;
  formGroup!: FormGroup;
  @ViewChild('swalDialog') swalDialog!: SwalComponent;

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

  unsubscribe(themeId: number) {
    this.profile = {
      ...this.profile,
      subscriptions: this.profile.subscriptions.filter(s => s.id !== themeId)
    }
  }

  updateProfile() {
    this.authService.updateProfile(this.formGroup.value).subscribe({
      next: async () => {
        this.swalDialog.swalOptions.text = "Profil modifié avec succès";
        await this.swalDialog.fire();
      },
      error: ({error}) => {
        if (error.errors) {
          for (const errorElement of error.errors) {
            this.formGroup.get(errorElement.field)?.setErrors({[errorElement.error]: ""});
          }
        }
      }
    })
  }
}
