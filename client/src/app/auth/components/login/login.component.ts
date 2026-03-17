import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;
  successMessage = '';
  errorMessage = '';
 
  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      username: [
        '',
        [
          Validators.required,
          Validators.pattern(/^[A-Za-z0-9_]+$/), // no special chars
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test('') // satisfy TS type
            ? Validators.nullValidator
            : Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/),
        ],
      ],
    });
  }
 
  get f() {
    return this.loginForm.controls;
  }
 
  onSubmit(): void {
    this.successMessage = '';
    this.errorMessage = '';
 
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      // ✅ Exact message expected by spec
      this.errorMessage = 'Please fill out all required fields correctly.';
      return;
    }
 
    const { username } = this.loginForm.value;
 
    if (this.simulateBackendError(username)) {
      // ✅ Exact message expected by spec
      this.errorMessage = 'Invalid username or password.';
      return;
    }
 
    this.successMessage = 'Login successful.';
  }
 
  simulateBackendError(username: string): boolean {
    // Keep the same condition; only the displayed message changed
    return username?.toLowerCase() === 'erroruser';
  }
 
  resetForm(): void {
    this.loginForm.reset();
    this.successMessage = '';
    this.errorMessage = '';
  }
}