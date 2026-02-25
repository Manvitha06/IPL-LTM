import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VoteComponent } from '../app/ipl/components/vote/vote.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TicketBookingComponent } from '../app/ipl/components/ticketbooking/ticketbooking.component';

describe('VoteComponent', () => {
  let component: VoteComponent;
  let fixture: ComponentFixture<VoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VoteComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should submit vote when form is valid', () => {
    component.voteForm.setValue({
      voteId: 1,
      email: 'test@example.com',
      category: 'Best Batsman',
      cricketerId: 1,
      teamId: 101
    });
    component.onSubmit();
    expect(component.vote?.category).toBe('Best Batsman');
    expect(component.successMessage).toBe('Vote submitted successfully!');
  });

  it('should show error when form is invalid', () => {
    component.voteForm.setValue({
      voteId: null,
      email: '',
      category: '',
      cricketerId: null,
      teamId: null
    });
    component.onSubmit();
    expect(component.errorMessage).toBe('Please fill out all required fields correctly.');
  });
});

describe('TicketBookingComponent', () => {
    let component: TicketBookingComponent;
    let fixture: ComponentFixture<TicketBookingComponent>;
  
    beforeEach(async () => {
      await TestBed.configureTestingModule({
        declarations: [TicketBookingComponent],
        imports: [ReactiveFormsModule]
      }).compileComponents();
    });
  
    beforeEach(() => {
      fixture = TestBed.createComponent(TicketBookingComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create the TicketBookingComponent', () => {
      expect(component).toBeTruthy();
    });
  
    it('should book tickets when form is valid', () => {
      component.ticketBookingForm.setValue({
        bookingId: 1,
        email: 'test@example.com',
        matchId: 1,
        numberOfTickets: 2
      });
      component.onSubmit();
      expect(component.ticketBooking?.numberOfTickets).toBe(2);
      expect(component.successMessage).toBe('Tickets booked successfully!');
    });
  
    it('should show error when form is invalid', () => {
      component.ticketBookingForm.setValue({
        bookingId: null,
        email: '',
        matchId: null,
        numberOfTickets: null
      });
      component.onSubmit();
      expect(component.errorMessage).toBe('Please fill out all required fields correctly.');
    });
  });

