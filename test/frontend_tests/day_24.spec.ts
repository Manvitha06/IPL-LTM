import { TestBed, ComponentFixture } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { of } from 'rxjs';
import { DashboardComponent } from '../app/ipl/components/dashboard/dashboard.component';
import { TicketBookingComponent } from '../app/ipl/components/ticketbooking/ticketbooking.component';
import { VoteComponent } from '../app/ipl/components/vote/vote.component';
import { IplService } from '../app/ipl/services/ipl.service';
import { SharedModule } from '../app/shared/shared.module';
import { ActivatedRoute } from '@angular/router';

describe('TicketBookingComponent', () => {
  let ticketBookingFixture: ComponentFixture<TicketBookingComponent>;
  let ticketBookingComponent: TicketBookingComponent;
  let iplService: jasmine.SpyObj<IplService>;

  beforeEach(async () => {
    const spyIplService = jasmine.createSpyObj('IplService', ['createBooking', 'getAllMatches']);
    spyIplService.createBooking.and.returnValue(of({bookingId:1, email: 'test@TestBed.com', numberOfTickets: 2}));
    spyIplService.getAllMatches.and.returnValue(of([{matchId: 1, firstTeam: {teamId:1}, secondTeam: {teamId:2}, matchDate: new Date(), venue: 'Mumbai'}]));
    
    await TestBed.configureTestingModule({
      declarations: [TicketBookingComponent],
      providers: [
        { provide: IplService, useValue: spyIplService }
      ],
      imports: [ReactiveFormsModule, HttpClientTestingModule]
    }).compileComponents();

    ticketBookingFixture = TestBed.createComponent(TicketBookingComponent);
    ticketBookingComponent = ticketBookingFixture.componentInstance;
    iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;

    ticketBookingFixture.detectChanges();
  });

  it('should create the ticket booking component', () => {
    expect(ticketBookingComponent).toBeTruthy();
  });

  it('should load matches', () => {
    ticketBookingComponent.loadMatches();
    ticketBookingFixture.detectChanges();

    expect(ticketBookingComponent.matches.length).toBe(1);
    expect(ticketBookingComponent.matches[0].venue).toBe('Mumbai');
  });

  it('should book a ticket on valid form submission', () => {
    ticketBookingComponent.ticketBookingForm.controls['email'].setValue('test@test.com');
    ticketBookingComponent.ticketBookingForm.controls['match'].setValue({ matchId: 1, venue: 'Stadium A' });
    ticketBookingComponent.ticketBookingForm.controls['numberOfTickets'].setValue(2);

    ticketBookingComponent.onSubmit();
    ticketBookingFixture.detectChanges();

    expect(iplService.createBooking).toHaveBeenCalled();
    expect(ticketBookingComponent.successMessage).toBe('Ticket booked successfully!');
  });

  it('should display error on invalid form submission', () => {
    ticketBookingComponent.onSubmit();
    expect(ticketBookingComponent.errorMessage).toBe('Please fill out all required fields correctly.');
  });

});

describe('DashboardComponent', () => {

  let dashboardFixture: ComponentFixture<DashboardComponent>;
  let dashboardComponent: DashboardComponent;
  let iplService: jasmine.SpyObj<IplService>;

  beforeEach(async () => {
    const spyIplService = jasmine.createSpyObj('IplService', ['getBookingsByUserEmail', 'getAllTeams', 'getAllCricketers', 'getAllMatches', 'getAllVotes']);
    spyIplService.getBookingsByUserEmail.and.returnValue(of([{
      bookingId: 1,
      email: 'test@TestBed.com',
      numberOfTickets: 2,
      match: {
        firstTeam: { teamName: 'Team A' },
        secondTeam: { teamName: 'Team B' },
        matchDate: new Date(),
      }
    }]));
    spyIplService.getAllTeams.and.returnValue(of([{teamId: 1, teamName: 'CSK', location: 'mumbai', ownerName: 'Ambani'}]));
    spyIplService.getAllCricketers.and.returnValue(of([{cricketerId: 1, cricketerName: 'Dhoni', age: 30, team: {teamName: 'CSK'}}]));
    spyIplService.getAllMatches.and.returnValue(of([{matchId: 1, matchDate: new Date(), venue: 'Mumbai'}]));
    spyIplService.getAllVotes.and.returnValue(of([{voteId: 1, category: 'team', team: {teamName: 'CSK'}, cricketer: null }]));

    await TestBed.configureTestingModule({
      declarations: [DashboardComponent],
      providers: [
        { provide: ActivatedRoute, useValue: { params: of({}) } },
        { provide: IplService, useValue: spyIplService }
      ],
      imports: [ReactiveFormsModule, HttpClientTestingModule, SharedModule]
    }).compileComponents();

    dashboardFixture = TestBed.createComponent(DashboardComponent);
    dashboardComponent = dashboardFixture.componentInstance;
    iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;

    dashboardFixture.detectChanges();
  });

  it('should create the dashboard component', () => {
    expect(dashboardComponent).toBeTruthy();
  });

  it('should fetch tickets booked for an email', () => {
    dashboardComponent.emailForm.controls['email'].setValue('test@test.com');
    dashboardComponent.onSubmitEmail();
    dashboardFixture.detectChanges();

    expect(dashboardComponent.ticketsBooked.length).toBe(1);
    expect(dashboardComponent.ticketsBooked[0].numberOfTickets).toBe(2);
  });
});

describe('VoteComponent', () => {
  let voteFixture: ComponentFixture<VoteComponent>;
  let voteComponent: VoteComponent;
  let iplService: jasmine.SpyObj<IplService>;

  beforeEach(async () => {
    const spyIplService = jasmine.createSpyObj('IplService', ['getAllTeams', 'getAllCricketers', 'createVote']);
    spyIplService.getAllTeams.and.returnValue(of([{teamId:1, teamName: 'CSK'}]));
    spyIplService.getAllCricketers.and.returnValue(of([{cricketerId:1, cricketerName: 'Dhoni'}]));
    spyIplService.createVote.and.returnValue(of({voteId:1, email: 'test@test.com', category: 'team', team: {teamId:1, teamName: 'CSK'}}));

    await TestBed.configureTestingModule({
      declarations: [VoteComponent],
      providers: [
        { provide: IplService, useValue: spyIplService }
      ],
      imports: [ReactiveFormsModule, HttpClientTestingModule]
    }).compileComponents();

    voteFixture = TestBed.createComponent(VoteComponent);
    voteComponent = voteFixture.componentInstance;
    iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;

    voteFixture.detectChanges();
  });

  it('should create the vote component', () => {
    expect(voteComponent).toBeTruthy();
  });

  it('should load teams and cricketers', () => {
    voteComponent.loadTeams();
    voteComponent.loadCricketers();
    voteFixture.detectChanges();

    expect(voteComponent.teams.length).toBe(1);
    expect(voteComponent.cricketers.length).toBe(1);
    expect(voteComponent.teams[0].teamName).toBe('CSK');
    expect(voteComponent.cricketers[0].cricketerName).toBe('Dhoni');
  });

  it('should cast a vote on valid form submission', () => {
    voteComponent.voteForm.controls['email'].setValue('test@test.com');
    voteComponent.voteForm.controls['category'].setValue('Cricketer');
    voteComponent.voteForm.controls['cricketer'].setValue({ cricketerId: 1, cricketerName: 'Player A' });

    voteComponent.onSubmit();
    voteFixture.detectChanges();

    expect(iplService.createVote).toHaveBeenCalled();
    expect(voteComponent.successMessage).toBe('Vote casted successfully!');
  });

  it('should display error on invalid form submission', () => {
    voteComponent.onSubmit();
    expect(voteComponent.errorMessage).toBe('Please fill out all required fields correctly.');
  });
});