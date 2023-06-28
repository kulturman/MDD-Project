import {Component, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {SwalComponent} from "@sweetalert2/ngx-sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @ViewChild("errorDialog")
  private errorDialog!: SwalComponent;

  formGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.formGroup = formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    })
  }

  onSubmit() {
    this.authService.login(this.formGroup.value).subscribe({
      next: (data) => {
        this.authService.registerAuthState(data.token);
        this.router.navigate(["/articles"]);
      },
      error: () => this.errorDialog.fire().then()
    })
  }
}
