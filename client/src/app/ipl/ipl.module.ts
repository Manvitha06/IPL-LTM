import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { IplRoutingModule } from "./ipl-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { TeamCreateComponent } from "./components/teamcreate/teamcreate.component";
import { CricketerArrayComponent } from "./components/cricketerarray/cricketerarray.component";

@NgModule({
 declarations: [
    TeamCreateComponent,
    CricketerArrayComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],



  exports: [
      TeamCreateComponent,
    CricketerArrayComponent
    
  ]
})
export class IplModule {}
