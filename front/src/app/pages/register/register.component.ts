import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {SwalComponent} from "@sweetalert2/ngx-sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  formGroup!: FormGroup;

  @ViewChild('successDialog')
  public readonly successDialog!: SwalComponent;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private readonly router: Router) {}

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [
        Validators.required, Validators.pattern(
        '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&}{+=]).{8,}$'
      )]],
    })
  }

  onSubmit() {
    this.authService.register(this.formGroup.value).subscribe(() => {
      this.successDialog.fire();
      this.router.navigate(['/login']);
    });
  }

}
