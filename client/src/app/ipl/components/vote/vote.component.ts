
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
// If you created types on Day 16, import them; otherwise, you can keep it typed as `any`
import { Vote } from '../../types/Vote';

@Component({
  selector: 'app-vote',
  templateUrl: './vote.component.html',
  styleUrls: ['./vote.component.scss'],
})
export class VoteComponent implements OnInit {
  voteForm!: FormGroup;
  vote: Vote | null = null;

  successMessage: string | null = null;
  errorMessage: string | null = null;

  // Use exact strings as required by spec
  readonly categories = ['Team', 'Batsman', 'Bowler', 'All-rounder', 'Wicketkeeper'] as const;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.voteForm = this.fb.group({
      voteId: [null, [Validators.required, Validators.min(1)]],
      email: ['', [Validators.required, Validators.email]],
      category: ['', [Validators.required]],
      cricketerId: [null, [Validators.min(1)]],
      teamId: [null, [Validators.min(1)]],
    });

    // Optional: dynamic validation — if category is 'Team', require teamId; otherwise require cricketerId
    this.voteForm.get('category')?.valueChanges.subscribe((cat) => {
      const cricketerIdCtrl = this.voteForm.get('cricketerId');
      const teamIdCtrl = this.voteForm.get('teamId');
      if (!cricketerIdCtrl || !teamIdCtrl) return;

      // reset validators
      cricketerIdCtrl.clearValidators();
      teamIdCtrl.clearValidators();

      if (cat === 'Team') {
        teamIdCtrl.setValidators([Validators.required, Validators.min(1)]);
        cricketerIdCtrl.setValidators([Validators.min(1)]);
      } else {
        cricketerIdCtrl.setValidators([Validators.required, Validators.min(1)]);
        teamIdCtrl.setValidators([Validators.min(1)]);
      }

      cricketerIdCtrl.updateValueAndValidity({ emitEvent: false });
      teamIdCtrl.updateValueAndValidity({ emitEvent: false });
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.voteForm.controls;
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.voteForm.invalid) {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.voteForm.markAllAsTouched();
      return;
    }

    const payload: Vote = {
        voteId: this.f['voteId'].value,
        email: this.f['email'].value,
        category: this.f['category'].value,
        cricketerId: this.f['cricketerId'].value,
        teamId: this.f['teamId'].value,
        displayInfo: function (): void {
            throw new Error('Function not implemented.');
        }
    };

    // TODO (Day 23+): integrate with backend via service
    console.log('Vote submitted:', payload);

    this.vote = payload;
    this.successMessage = 'Vote submitted successfully!';
    this.voteForm.reset();
  }

  resetForm(): void {
    this.voteForm.reset();
    this.successMessage = null;
    this.errorMessage = null;
    this.vote = null;
  }
}
