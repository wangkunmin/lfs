import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { PublicModule } from "../public/public.module";
import { PaginationModule } from "ngx-bootstrap";
import { EssenceNg2PrintModule } from 'essence-ng2-print';

import { AtlasMapComponent } from './atlas-map.component';

import { AtlasMapRoutingModule } from './atlas-map-routing.module';
import { ChartComponent } from './chart/chart.component';
import { AtlasMapApiService } from '../common/api/atlas-map-api.service';
import { ChartUtil } from '../common/utill/chart-util';
import { LeftBarComponent } from './left-bar/left-bar.component';
import { RightBarComponent } from './right-bar/right-bar.component';
import { ShapesComponent } from './shapes/shapes.component';
import { FindCompanyInputComponent } from "./find-company-input/find-company-input.component";
import { HeaderService } from "../common/service/header.service";
import { FindMoreCompanyComponent } from "./find-company-input/find-more-company.component";


@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    AtlasMapRoutingModule,
    EssenceNg2PrintModule,
    PaginationModule.forRoot(),
    PublicModule
  ],
  declarations: [
    FindCompanyInputComponent,
    FindMoreCompanyComponent, //查询公司详情
    AtlasMapComponent,
    ChartComponent,
    LeftBarComponent,
    RightBarComponent,
    ShapesComponent
  ],
  providers:[
    HeaderService,
    AtlasMapApiService,
    ChartUtil
  ]
})
export class AtlasMapModule { }
