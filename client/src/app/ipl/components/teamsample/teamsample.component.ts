
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Team } from '../../types/Team';

@Component({
  selector: 'app-teamsample',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './teamsample.component.html',
  styleUrls: ['./teamsample.component.scss']
})
export class TeamSampleComponent {

  // Sample Team object as required for Day 17
  team: Team = new Team(
    1,
    'Chennai Super Kings',
    'Chennai',
    'India Cements',
    2008
  );

} 
