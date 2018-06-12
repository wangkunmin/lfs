import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AtlasMapComponent } from './atlas-map.component';
import { ChartComponent } from './chart/chart.component';
import { FindMoreCompanyComponent } from './find-company-input/find-more-company.component';
import { ShapesComponent } from './shapes/shapes.component';

const routes: Routes = [
  { path: '', component: AtlasMapComponent },
  {
    path: 'findMoreCompany/:searchSrc',
    component: FindMoreCompanyComponent     // 关联图谱查询界面
  },
  { path: 'chart', component: ChartComponent },
  {
    path: 'chart/:id/:type',
    component: ChartComponent
  },
  {
    path: 'ship/:key/:name',
    component: ShapesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtlasMapRoutingModule { }
