import { Component } from '@angular/core';
import { Team } from '../../types/Team';
// Use the shared Team model used by tests instead of redefining a local class.
// Path from: src/app/ipl/components/teamsample --> src/app/types

@Component({
  selector: 'app-teamsample',
  standalone: true,
  templateUrl: './teamsample.component.html',
  // ⬅️ fix: your file is .scss (per screenshot), not .css
  styleUrls: ['./teamsample.component.scss'],
})
export class TeamSampleComponent {
 
 team: Team = new Team(
    1,          // teamId
    'CSK',      // teamName
    'Chennai',  // location
    'Dhoni',    // ownerName
    2015        // establishmentYear
  );

}