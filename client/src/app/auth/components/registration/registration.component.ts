import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  private usernamePattern = /^[a-zA-Z0-9]+$/;
  private strongPassword = /^(?=.*[A-Z])(?=.*\d).{8,}$/;

  constructor(private fb: FormBuilder) {
    this.registrationForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      username: ['', [Validators.required, Validators.pattern(this.usernamePattern)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(this.strongPassword)]],
    });
  }

  get fullName(): AbstractControl | null { return this.registrationForm.get('fullName'); }
  get username(): AbstractControl | null { return this.registrationForm.get('username'); }
  get email(): AbstractControl | null { return this.registrationForm.get('email'); }
  get password(): AbstractControl | null { return this.registrationForm.get('password'); }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.registrationForm.invalid) {
      // 🔴 REQUIRED BY TEST: set this exact string on invalid entries (e.g., bad email)
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.registrationForm.markAllAsTouched();
      return;
    }

    // if service exists, call it; for Day 21 demo we can just succeed
    this.successMessage = 'Registration successful!';
  }
}