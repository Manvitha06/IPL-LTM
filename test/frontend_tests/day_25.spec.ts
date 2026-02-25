import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Component } from '@angular/core';
import { SharedModule } from '../app/shared/shared.module';
import { TeamEditComponent } from '../app/ipl/components/teamedit/teamedit.component';
import { IplService } from '../app/ipl/services/ipl.service';
import { Team } from '../app/ipl/types/Team';
import { DashboardComponent } from '../app/ipl/components/dashboard/dashboard.component';


@Component({ template: 'ipl' })
class MockDashboardComponent {}

describe('TeamEditComponent', () => {
  let component: TeamEditComponent;
  let fixture: ComponentFixture<TeamEditComponent>;
  let iplService: jasmine.SpyObj<IplService>;

  beforeEach(async () => {
    const spyIplService = jasmine.createSpyObj('iplService', ['getTeamById', 'updateTeam']);
    spyIplService.updateTeam.and.returnValue(of({}));
    spyIplService.getTeamById.and.returnValue(of({
        teamId: 1, teamName: 'CSK',
        displayInfo: () => {}
      }));

    await TestBed.configureTestingModule({
      declarations: [TeamEditComponent],
      imports: [
        ReactiveFormsModule, 
        HttpClientTestingModule, 
        SharedModule,
        RouterTestingModule.withRoutes([
            {path: 'ipl', component: MockDashboardComponent}
        ])
      ],
      providers: [
        FormBuilder,
        { provide: IplService, useValue: spyIplService }, 
        {
          provide: ActivatedRoute, 
          useValue: {
            params: of({ teamId: 1 }) // Mock params observable
          }
        },
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(TeamEditComponent);
    component = fixture.componentInstance;
    iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;
    fixture.detectChanges(); // Triggers ngOnInit
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with required controls', () => {
    expect(component.teamForm.contains('teamName')).toBeTrue();
    expect(component.teamForm.contains('location')).toBeTrue();
    expect(component.teamForm.contains('ownerName')).toBeTrue();
    expect(component.teamForm.contains('establishmentYear')).toBeTrue();
  });

  it('should load team details on init', () => {
    const mockTeam: Team = {
      teamId: 1,
      teamName: 'CSK', ownerName: 'Ambani', establishmentYear: 2012,
      location: 'Chennai',
      displayInfo: function () {}
    };

    iplService.getTeamById.and.returnValue(of(mockTeam));

    component.loadTeamDetails(1);
    
    expect(iplService.getTeamById).toHaveBeenCalledWith(1);
    expect(component.teamForm.value.teamName).toEqual(mockTeam.teamName);
    expect(component.teamForm.value.ownerName).toEqual(mockTeam.ownerName);
  });


  it('should update team when form is valid and submitted', () => {

    component.teamForm.patchValue({
        teamName: 'CSK', ownerName: 'Mukesh Ambani', establishmentYear: 2012,
        location: 'Chennai'
    });
 
    component.onSubmit();

    expect(iplService.updateTeam).toHaveBeenCalledWith(
        jasmine.objectContaining({
            teamName: 'CSK', ownerName: 'Mukesh Ambani', establishmentYear: 2012,
            location: 'Chennai'
          })
        );
    });
 

  it('should not update team if the form is invalid', () => {
    component.teamForm.patchValue({
        teamName: '', ownerName: '', establishmentYear: 2042,
        location: ''
    });

    component.onSubmit();

    expect(iplService.updateTeam).not.toHaveBeenCalled();
  });
  
});

describe("DeleteFunctionaility", () => {
    let component: DashboardComponent;
    let fixture: ComponentFixture<DashboardComponent>;
    let iplService: jasmine.SpyObj<IplService>;
   
    beforeEach(async () => {
        const spyIplService = jasmine.createSpyObj('IplService', ['deleteTeam', 'deleteCricketer']);
        spyIplService.deleteTeam.and.returnValue(of({}));
        spyIplService.deleteCricketer.and.returnValue(of({}));

        await TestBed.configureTestingModule({
          declarations: [DashboardComponent],
          providers: [
            { provide: IplService, useValue: spyIplService }
          ],
          imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule, SharedModule]
        }).compileComponents();
      
        fixture = TestBed.createComponent(DashboardComponent);
        component = fixture.componentInstance;
        iplService = TestBed.inject(IplService) as jasmine.SpyObj<IplService>;
    });
  
    it("should create the component", () => {
      expect(component).toBeTruthy();
    });
  
    it('should call delete Teams when Delete button is clicked', () => {
        spyOn(window, 'confirm').and.returnValue(true);
        
        spyOn(component, 'loadAdminData');

        iplService.deleteTeam.and.returnValue(of({}));

        component.deleteTeam(1);

        expect(iplService.deleteTeam).toHaveBeenCalledWith(1);
    });
         
});
