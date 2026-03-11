
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Cricketer } from '../../types/Cricketer';

@Component({
  selector: 'app-cricketersample',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cricketersample.component.html',
  styleUrls: ['./cricketersample.component.scss']
})
export class CricketerSampleComponent {
  /**
   * Public property REQUIRED by the test:
   * The spec does: componentCricketer.cricketer = cricketer;
   * So this must exist and be assignable.
   */
  cricketer: Cricketer = new Cricketer(
    101,     // cricketerId
    1,       // teamId
    'Virat', // cricketerName
    35,      // age
    'India', // nationality
    15,      // experience
    'Batsman', // role
    8000,    // totalRuns
    4        // totalWickets
  );
}