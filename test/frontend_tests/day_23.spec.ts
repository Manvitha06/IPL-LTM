import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { ActivatedRoute } from '@angular/router';
import { DashboardComponent } from '../app/ipl/components/dashboard/dashboard.component';
import { TeamCreateComponent } from '../app/ipl/components/teamcreate/teamcreate.component';
import { IplService } from '../app/ipl/services/ipl.service';
import { Cricketer } from '../app/ipl/types/Cricketer';
import { Match } from '../app/ipl/types/Match';
import { Team } from '../app/ipl/types/Team';
import { SharedModule } from '../app/shared/shared.module';

const mockTeams: Team[] = [
  new Team(1, 'Team A', 'Location A', 'Owner A', 2001),
  new Team(2, 'Team B', 'Location B', 'Owner B', 2002)
];

const mockCricketers: Cricketer[] = [
  new Cricketer(1, 'Cricketer A', 30, 'Indian', 10, 'Batsman', 5000, 50, mockTeams[0]),
  new Cricketer(2, 'Cricketer B', 28, 'Australian', 8, 'Bowler', 1000, 100, mockTeams[1])
];

const mockMatches: Match[] = [
  new Match(1, mockTeams[0], mockTeams[1], new Date(), 'Stadium A', 'Won', 'Completed', mockTeams[0]),
  new Match(2, mockTeams[1], mockTeams[0], new Date(), 'Stadium B', 'Lost', 'Completed', mockTeams[1])
];

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let iplService: jasmine.SpyObj<IplService>;

  beforeEach(async () => {
    const iplSpy = jasmine.createSpyObj('IplService', ['getAllTeams', 'getAllCricketers', 'getAllMatches']);

    await TestBed.configureTestingModule({
      declarations: [DashboardComponent],
      providers: [
        { provide: ActivatedRoute, useValue: { params: of({}) } },  // Mocking ActivatedRoute
        { provide: IplService, useValue: iplSpy }
      ],
      imports: [HttpClientTestingModule, SharedModule]
    }).compileComponents();

    iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
  });

  it('should load teams, cricketers, and matches on initialization', () => {
    iplService.getAllTeams.and.returnValue(of(mockTeams));
    iplService.getAllCricketers.and.returnValue(of(mockCricketers));
    iplService.getAllMatches.and.returnValue(of(mockMatches));

    component.ngOnInit();

    expect(iplService.getAllTeams).toHaveBeenCalled();
    expect(iplService.getAllCricketers).toHaveBeenCalled();
    expect(iplService.getAllMatches).toHaveBeenCalled();

    expect(component.teams.length).toBe(2);
    expect(component.cricketers.length).toBe(2);
    expect(component.matches.length).toBe(2);
  });

  it('should handle empty lists for teams, cricketers, and matches', () => {
    iplService.getAllTeams.and.returnValue(of([]));
    iplService.getAllCricketers.and.returnValue(of([]));
    iplService.getAllMatches.and.returnValue(of([]));

    component.ngOnInit();

    expect(component.teams.length).toBe(0);
    expect(component.cricketers.length).toBe(0);
    expect(component.matches.length).toBe(0);
  });
});

describe('TeamCreateComponent', () => {
  let component: TeamCreateComponent;
  let fixture: ComponentFixture<TeamCreateComponent>;
  let iplService: jasmine.SpyObj<IplService>;

  beforeEach(async () => {
    const iplSpy = jasmine.createSpyObj('IplService', ['addTeam']);

    await TestBed.configureTestingModule({
      declarations: [TeamCreateComponent],
      providers: [{ provide: IplService, useValue: iplSpy }],
      imports: [ReactiveFormsModule, HttpClientTestingModule]
    }).compileComponents();

    iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;
    fixture = TestBed.createComponent(TeamCreateComponent);
    component = fixture.componentInstance;
  });

  it('should initialize form with validation rules', () => {
    fixture.detectChanges();
    const form = component.teamForm;

    expect(form).toBeDefined();
    expect(form.get('teamName')?.valid).toBeFalsy(); // Initially invalid
    expect(form.get('location')?.valid).toBeFalsy();
    expect(form.get('ownerName')?.valid).toBeFalsy();
    expect(form.get('establishmentYear')?.valid).toBeFalsy();
  });

  it('should display success message on valid form submission', () => {
    fixture.detectChanges();
  
    const mockTeam: Team = new Team(1, 'Team A', 'Location A', 'Owner A', 2001);
    iplService.addTeam.and.returnValue(of(mockTeam));
  
    component.teamForm.setValue({
      teamName: 'Team A',   
      location: 'Location A',    
      ownerName: 'Owner A',   
      establishmentYear: 2001 
    });
  
    component.onSubmit();
    
    expect(component.successMessage).toBe('Team created successfully!');
    expect(component.errorMessage).toBeNull();
  });

  it('should display error message on invalid form submission', () => {
    fixture.detectChanges();

    component.teamForm.setValue({
      teamName: '', // Invalid (required)
      location: 'Location A',
      ownerName: 'Owner A',
      establishmentYear: 2001
    });

    component.onSubmit();

    expect(component.successMessage).toBeNull();
    expect(component.errorMessage).toBe('Please fill out all required fields correctly.');
  });

});
