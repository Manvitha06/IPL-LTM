import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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

  // Just to hold created object for demo (service integration will come later)
  cricketer: any | null = null;

  readonly roles = ['Batsman', 'Bowler', 'All-rounder', 'Wicketkeeper'];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cricketerForm = this.fb.group({
      cricketerId: [null, [Validators.required, Validators.min(1)]],
      teamId: [null, [Validators.required, Validators.min(1)]],
      cricketerName: ['', [Validators.required, Validators.minLength(2)]],
      age: [null, [Validators.required, Validators.min(0)]],
      nationality: ['', [Validators.required, Validators.minLength(2)]],
      experience: [0, [Validators.required, Validators.min(0)]],
      role: ['', [Validators.required]],
      totalRuns: [0, [Validators.required, Validators.min(0)]],
      totalWickets: [0, [Validators.required, Validators.min(0)]],
    });
  }

  get f() { return this.cricketerForm.controls; }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.cricketerForm.invalid) {
      this.cricketerForm.markAllAsTouched();
      this.errorMessage = 'Please fix the validation errors and try again.';
      return;
    }

    // For Day 19 demo; on Day 23 you’ll call the backend via service
    this.cricketer = { ...this.cricketerForm.value };
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
}