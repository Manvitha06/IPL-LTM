import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { TicketBooking } from '../../types/TicketBooking';

@Component({
  selector: 'app-ticket-booking',
  templateUrl: './ticketbooking.component.html',
  styleUrls: ['./ticketbooking.component.scss'],
})
export class TicketBookingComponent implements OnInit {
  // Keep the original internal name
  private bookingForm!: FormGroup;

  // ✅ Public alias expected by tests
  public get ticketBookingForm(): FormGroup {
    return this.bookingForm;
  }

  ticketBooking: TicketBooking | null = null;

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.bookingForm = this.fb.group({
      bookingId: [null, [Validators.required, Validators.min(1)]],
      email: ['', [Validators.required, Validators.email]],
      matchId: [null, [Validators.required, Validators.min(1)]],
      numberOfTickets: [1, [Validators.required, Validators.min(1)]],
    });
  }

  // Return controls from the alias so template remains clear
  get f(): { [key: string]: AbstractControl } {
    return this.ticketBookingForm.controls;
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.ticketBookingForm.invalid) {
      this.errorMessage ='Please fill out all required fields correctly.';
      this.ticketBookingForm.markAllAsTouched();
      return;
    }

    const payload: TicketBooking = {
        bookingId: this.f['bookingId'].value,
        email: this.f['email'].value,
        matchId: this.f['matchId'].value,
        numberOfTickets: this.f['numberOfTickets'].value,
        displayInfo: function (): void {
            throw new Error('Function not implemented.');
        }
    };

    console.log('Ticket booking submitted:', payload);

    this.ticketBooking = payload;
    this.successMessage = 'Tickets booked successfully!';
    this.ticketBookingForm.reset({ numberOfTickets: 1 });
  }

  resetForm(): void {
    this.ticketBookingForm.reset({ numberOfTickets: 1 });
    this.successMessage = null;
    this.errorMessage = null;
    this.ticketBooking = null;
  }
}
