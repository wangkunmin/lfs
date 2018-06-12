import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegionRiskComponent } from './region-risk.component';
import { TrackMoreComponent } from './news-track/track-more.component';
import { WarningMoreComponent } from './early-warning/warning-more.component';
import { NewsDetailComponent } from './news-track/news-detail.component';

const routes: Routes = [
  { path: '', component: RegionRiskComponent },
  {
    path: 'tracks',
    component: TrackMoreComponent   // 更多负面新闻
  },
  {
    path: 'tracks/:startDate/:endDate',
    component: TrackMoreComponent   // 更多负面新闻
  },
  {
    path: 'warnings/:year',
    component: WarningMoreComponent  // 更多预警信息
  },
  {
    path: 'news/:companyId/:newsCode',
    component: NewsDetailComponent   // 更多预警信息
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegionRiskRoutingModule { }
