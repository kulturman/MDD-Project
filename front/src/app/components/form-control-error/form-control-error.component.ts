import {Component, Input} from '@angular/core';
import {FormGroup, ValidationErrors} from "@angular/forms";

@Component({
  selector: 'app-form-control-error',
  templateUrl: './form-control-error.component.html',
  styleUrls: ['./form-control-error.component.css']
})
export class FormControlErrorComponent {
  @Input() form!: FormGroup;
  @Input() name!: string;
  @Input() customErrors: CustomError[] = [];

  private messages = {
    'required' : 'Ce champ est obligatoire',
    'email' : 'L\'adresse email est invalide',
    'pattern': 'Le champ est invalide',
    'UniqueField': 'Cette valeur est déjà utilisée'
  };

  getMessage(errors: ValidationErrors | null | undefined): string {
    for (const error in errors) {
      if (error in this.messages) {
        return this.getMessageText(error as keyof typeof this.messages);
      }
    }
    throw new Error(`Unknown error`);
  }

  private getMessageText(key: keyof typeof this.messages): string {
    return this.getCustomError(key) || this.messages[key];
  }

  private getCustomError(errorType: string): string | null {
    let message = null;

    if (!(errorType in this.messages)) {
      throw new Error('Unknown key');
    }

    this.customErrors.forEach(customError => {
      if (customError.errorType === errorType) {
        message = customError.message;
      }
    });

    return message;
  }
}

export interface CustomError {
  errorType: string;
  message: string;
}
