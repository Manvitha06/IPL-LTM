

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-match-create',
  templateUrl: './matchcreate.component.html',
  styleUrls: ['./matchcreate.component.scss']
})
export class MatchCreateComponent implements OnInit {
  matchForm!: FormGroup;

  // UI feedback
  successMessage: string | null = null;
  errorMessage: string | null = null;

  // Just to hold created object for demo (service integration will come later)
  match: any | null = null;

  readonly statuses = ['Pending', 'Scheduled', 'Completed'];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.matchForm = this.fb.group({
      matchId: [null, [Validators.required, Validators.min(1)]],
      firstTeamId: [null, [Validators.required, Validators.min(1)]],
      secondTeamId: [null, [Validators.required, Validators.min(1)]],
      matchDate: ['', [Validators.required]],
      venue: ['', [Validators.required, Validators.minLength(2)]],
      result: [''],
      status: ['', [Validators.required]],
      winnerTeamId: [null, [Validators.min(1)]],
    });
  }

  get f() { return this.matchForm.controls; }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.matchForm.invalid) {
      this.matchForm.markAllAsTouched();
      this.errorMessage = 'Please fix the validation errors and try again.';
      return;
    }

    // For Day 19 demo; on Day 23 you’ll call the backend via service
    this.match = { ...this.matchForm.value };
    console.log('Match form submitted:', this.match);

    this.successMessage = 'Match created successfully!';
    this.resetForm();
  }

  resetForm(): void {
    this.matchForm.reset({
      matchId: null,
      firstTeamId: null,
      secondTeamId: null,
      matchDate: '',
      venue: '',
      result: '',
      status: '',
      winnerTeamId: null
    });
  }
}