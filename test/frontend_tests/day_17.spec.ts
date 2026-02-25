
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CricketerSampleComponent } from '../app/ipl/components/cricketersample/cricketersample.component';
import { TeamSampleComponent } from '../app/ipl/components/teamsample/teamsample.component';
import { Cricketer } from '../app/ipl/types/Cricketer';
import { Team } from '../app/ipl/types/Team';

describe('CricketerSampleComponent', () => {
  let componentCricketer: CricketerSampleComponent;
  let fixtureCricketer: ComponentFixture<CricketerSampleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
        imports:[CricketerSampleComponent]
    });

    fixtureCricketer = TestBed.createComponent(CricketerSampleComponent);
    componentCricketer = fixtureCricketer.componentInstance;
  });

  it('should display cricketer information', () => {
    const cricketer: Cricketer = new Cricketer(1, 1, "Virat", 32, "Indian", 14, "Batsman", 580, 50);

    componentCricketer.cricketer = cricketer;
    fixtureCricketer.detectChanges();

    const compiled = fixtureCricketer.nativeElement;

    expect(compiled.textContent).toContain('Cricketer ID: 1');
    expect(compiled.textContent).toContain('Team ID: 1');
    expect(compiled.textContent).toContain('Cricketer Name: Virat');
   
  });
});

describe('TeamSampleComponent', () => {
    let componentTeam: TeamSampleComponent;
    let fixtureTeam: ComponentFixture<TeamSampleComponent>;
  
    beforeEach(() => {
      TestBed.configureTestingModule({
          imports:[TeamSampleComponent]
      });
  
      fixtureTeam = TestBed.createComponent(TeamSampleComponent);
      componentTeam = fixtureTeam.componentInstance;
    });
  
    it('should display team details correctly', () => {
        const team: Team = new Team(1, "CSK", "Chennai", "Dhoni", 2015);

        componentTeam.team = team;
        fixtureTeam.detectChanges();

        const compiled = fixtureTeam.nativeElement;
    
        expect(compiled.textContent).toContain('Team ID: 1');
        expect(compiled.textContent).toContain('Team Name: CSK');
        expect(compiled.textContent).toContain('Location: Chennai');
    });
});