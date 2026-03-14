import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { IplRoutingModule } from "./ipl-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { TeamCreateComponent } from "./components/teamcreate/teamcreate.component";
import { CricketerArrayComponent } from "./components/cricketerarray/cricketerarray.component";
import { CricketerCreateComponent } from "./components/cricketercreate/cricketercreate.component";
import { MatchCreateComponent } from "./components/matchcreate/matchcreate.component";

import { VoteComponent } from './components/vote/vote.component';
import { TicketBookingComponent } from './components/ticketbooking/ticketbooking.component';

@NgModule({
 declarations: [
    TeamCreateComponent,
    CricketerArrayComponent,

 VoteComponent,
    TicketBookingComponent,

 CricketerCreateComponent,
    MatchCreateComponent

  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],



  exports: [
      TeamCreateComponent,
    CricketerArrayComponent,
     CricketerCreateComponent,
      VoteComponent,
    TicketBookingComponent,
    MatchCreateComponent
    
  ]
})
export class IplModule {}
