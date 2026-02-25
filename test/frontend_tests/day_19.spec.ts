import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CricketerCreateComponent } from '../app/ipl/components/cricketercreate/cricketercreate.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatchCreateComponent } from '../app/ipl/components/matchcreate/matchcreate.component';

describe('CricketerCreateComponent', () => {
  let component: CricketerCreateComponent;
  let fixture: ComponentFixture<CricketerCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CricketerCreateComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CricketerCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should create cricketer on valid form submission', () => {
    component.cricketerForm.setValue({
      cricketerId: 1,
      teamId: 101,
      cricketerName: 'Sachin Tendulkar',
      age: 40,
      nationality: 'Indian',
      experience: 20,
      role: 'Batsman',
      totalRuns: 15000,
      totalWickets: 200
    });
    component.onSubmit();
    expect(component.cricketer?.cricketerName).toBe('Sachin Tendulkar');
    expect(component.successMessage).toBe('Cricketer created successfully!');
  });

  it('should reset the form', () => {
    component.cricketerForm.setValue({
      cricketerId: 1,
      teamId: 101,
      cricketerName: 'Sachin Tendulkar',
      age: 40,
      nationality: 'Indian',
      experience: 20,
      role: 'Batsman',
      totalRuns: 15000,
      totalWickets: 200
    });
    component.resetForm();
    expect(component.cricketerForm.value.cricketerId).toBeNull();
  });
});

describe('MatchCreateComponent', () => {
    let component: MatchCreateComponent;
    let fixture: ComponentFixture<MatchCreateComponent>;
  
    beforeEach(async () => {
      await TestBed.configureTestingModule({
        declarations: [MatchCreateComponent],
        imports: [ReactiveFormsModule]
      }).compileComponents();
    });
  
    beforeEach(() => {
      fixture = TestBed.createComponent(MatchCreateComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create the component', () => {
      expect(component).toBeTruthy();
    });
  
    it('should create match on valid form submission', () => {
      component.matchForm.setValue({
        matchId: 1,
        firstTeamId: 101,
        secondTeamId: 102,
        matchDate: '2022-10-05',
        venue: 'Mumbai',
        result: 'Team 1 won',
        status: 'Finished',
        winnerTeamId: 101
      });
      component.onSubmit();
      expect(component.match?.venue).toBe('Mumbai');
      expect(component.successMessage).toBe('Match created successfully!');
    });
  
    it('should reset the form', () => {
      component.matchForm.setValue({
        matchId: 1,
        firstTeamId: 101,
        secondTeamId: 102,
        matchDate: '2022-10-05',
        venue: 'Mumbai',
        result: 'Team 1 won',
        status: 'Finished',
        winnerTeamId: 101
      });
      component.resetForm();
      expect(component.matchForm.value.matchId).toBeNull();
    });
  });