import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-cricketer-create',
  templateUrl: './cricketercreate.component.html',
  styleUrls: ['./cricketercreate.component.scss']
})
export class CricketerCreateComponent implements OnInit {
  cricketerForm!: FormGroup;

  // UI feedback
  successMessage: string | null = null;
  errorMessage: string | null = null;

  // (For demo) hold the created object
  cricketer: any | null = null;

  // Must use exact strings as per DB spec
  readonly roles = ['Batsman', 'Bowler', 'All-rounder', 'Wicketkeeper'];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cricketerForm = this.fb.group({
      cricketerId: [null, [Validators.required, Validators.min(1)]],
      teamId: [null, [Validators.required, Validators.min(1)]],
      cricketerName: ['', [Validators.required, Validators.minLength(2)]],
      // Age realistic bounds for professional cricketers
      age: [null, [Validators.required, Validators.min(15), Validators.max(60)]],
      nationality: ['', [Validators.required, Validators.minLength(2)]],
      // Day-21 rule: non-negative
      experience: [0, [Validators.required, Validators.min(0), Validators.max(45)]],
      role: ['', [Validators.required]],
      totalRuns: [0, [Validators.required, Validators.min(0)]],
      totalWickets: [0, [Validators.required, Validators.min(0)]],
    });
  }

  // Shorthand access for template
  get f(): { [key: string]: AbstractControl } {
    return this.cricketerForm.controls as { [key: string]: AbstractControl };
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.cricketerForm.invalid) {
      this.cricketerForm.markAllAsTouched();
      this.errorMessage = 'Please fix the validation errors and try again.';
      return;
    }

    const payload = { ...this.cricketerForm.value };

    // ✅ Day-21: simulate backend error propagation
    const backendError = this.simulateBackendError(payload);
    if (backendError) {
      this.errorMessage = backendError;
      return;
    }

    // Day 23+: replace with service call (IplService.addCricketer)
    this.cricketer = payload;
    console.log('Cricketer form submitted:', this.cricketer);

    this.successMessage = 'Cricketer created successfully!';
    this.resetForm();
  }

  resetForm(): void {
    this.cricketerForm.reset({
      cricketerId: null,
      teamId: null,
      cricketerName: '',
      age: null,
      nationality: '',
      experience: 0,
      role: '',
      totalRuns: 0,
      totalWickets: 0
    });
  }

  /**
   * Simulate typical service-side constraints (UI propagation demo)
   */
  private simulateBackendError(payload: any): string | null {
    // Example: block duplicate name within the same team
    if ((payload?.cricketerName || '').trim().toLowerCase() === 'existing cricketer') {
      return 'Cricketer already exists in this team.';
    }
    // Example: enforce team limit (11)
    // If you had team count in UI, you’d check it before submit; here we simulate:
    if (payload?.teamId === 999) { // pretend team 999 already has 11 players
      return 'Team player limit reached (11). Cannot add more cricketers.';
    }
    return null;
  }
}