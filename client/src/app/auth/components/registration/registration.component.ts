import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.email]],
      role: ["", [Validators.required]],
    });
  }

onSubmit(): void {
  if (this.registrationForm.valid) {
    this.successMessage = null;
    this.errorMessage = null;

    const payload = this.registrationForm.value;

    this.authService.createUser(payload).subscribe({
      next: (response) => {
        this.successMessage =
          (response && (response['message'] || response['msg'])) ||
          'Registration successful!';
        this.errorMessage = null;
        this.registrationForm.reset();
      },
      error: (err: HttpErrorResponse | any) => {
        const serverMessage =
          err?.error?.message ||
          err?.error?.error ||
          (typeof err?.error === 'string' ? err.error : null);

        this.errorMessage =
          serverMessage ||
          err?.message ||
          'Something went wrong. Please try again.';
        this.successMessage = null;

        console.error('Create user error', err); // optional
      },
    });
  } else {
    this.registrationForm.markAllAsTouched();
    this.errorMessage = 'Please fill out the form correctly.';
    this.successMessage = null;
  }
}
}