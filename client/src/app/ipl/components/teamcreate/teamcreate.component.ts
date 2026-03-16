import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Team } from '../../types/Team';

@Component({
  selector: 'app-teamcreate',
  templateUrl: './teamcreate.component.html',
  styleUrls: ['./teamcreate.component.scss']
})
export class TeamCreateComponent implements OnInit {
  teamForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  currentYear = new Date().getFullYear();
  // Alphanumeric + space only (no special characters)
  private readonly teamNamePattern = /^[a-zA-Z0-9 ]+$/;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.teamForm = this.formBuilder.group({
      teamId: [null, [Validators.required, Validators.min(1)]],
      teamName: ['', [Validators.required, Validators.minLength(2), Validators.pattern(this.teamNamePattern)]],
      location: ['', [Validators.required]],
      ownerName: ['', [Validators.required, Validators.minLength(2)]],
      establishmentYear: [
        this.currentYear,
        [Validators.required, Validators.min(1900), Validators.max(this.currentYear)]
      ]
    });
  }

  // ----- Getters for template cleanliness -----
  get teamId(): AbstractControl | null { return this.teamForm.get('teamId'); }
  get teamName(): AbstractControl | null { return this.teamForm.get('teamName'); }
  get location(): AbstractControl | null { return this.teamForm.get('location'); }
  get ownerName(): AbstractControl | null { return this.teamForm.get('ownerName'); }
  get establishmentYear(): AbstractControl | null { return this.teamForm.get('establishmentYear'); }

  // Handle form submission and set messages accordingly
  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.teamForm.invalid) {
      this.teamForm.markAllAsTouched();
      this.errorMessage = 'Please fill out all required fields correctly.';
      return;
    }

    const payload: Team = this.teamForm.value;

    // ✅ Simulate backend error propagation (e.g., TeamAlreadyExistsException)
    const backendError = this.simulateBackendConflict(payload.teamName);
    if (backendError) {
      this.errorMessage = backendError;
      return;
    }

    // TODO: Replace with real call:
    // this.iplService.addTeam(payload).subscribe({
    //   next: (team) => { this.successMessage = 'Team created successfully!'; this.resetForm(); },
    //   error: (err: HttpErrorResponse) => { this.errorMessage = err.error?.message ?? 'Failed to create team'; }
    // });

    this.successMessage = 'Team has been successfully created!';
    console.log('Team Created: ', payload);
    this.resetForm();
  }

  // Reset form data
  resetForm(): void {
    this.teamForm.reset({
      teamId: null,
      teamName: '',
      location: '',
      ownerName: '',
      establishmentYear: this.currentYear
    });
    // Don’t clear messages on reset unless you want to:
    // this.successMessage = null;
    // this.errorMessage = null;
  }

  // Fake server rule for UI demo
  private simulateBackendConflict(teamName: string): string | null {
    if (teamName?.trim().toLowerCase() === 'existing team') {
      return 'Team already exists. Please choose a different name.';
    }
    return null;
  }
}