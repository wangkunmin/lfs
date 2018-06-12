import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GroupRiskComponent } from './group-risk.component';
import { RiskContentComponent } from './risk-content/risk-content.component';
import { GroupTrackMoreComponent } from './group-news-risk/group-track-more.component';
import { EventRiskMoreComponent } from './event-risk/event-risk-more.component';
import { EventRiskCompanyAllComponent } from './event-risk/event-risk-company-all.component';

const routes: Routes = [
  {
    path: '',
    component: GroupRiskComponent,   // 分组风险总览
    children: [
      {
        path: '',
        component: RiskContentComponent
      },
      {
        path: 'detail/:id/:companyId',
        component: RiskContentComponent
      }
    ]
  },
  {
    path: 'moreGroupNews',
    component: GroupTrackMoreComponent   // 更多舆情新闻
  },
  {
    path: 'moreGroupNews/:startDate/:endDate',
    component: GroupTrackMoreComponent   // 更多舆情新闻
  },
  /*{
    path: 'moreGroupNews/:companyId/:newsCode',
    component: GroupTrackMoreComponent   // 更多舆情新闻
  },*/
  {
    path: 'eventRiskMore/:riskType',
    component: EventRiskMoreComponent   // 更多事件公告风险
  },
  {
    path: 'eventRiskMore/:startDate/:endDate/:riskType/:negativeType/:importanceType',
    component: EventRiskMoreComponent   // 更多事件公告风险
  },
  {
    path: 'eventRCA/:companyId',
    component: EventRiskCompanyAllComponent   // 更多事件公告风险
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GroupRiskRoutingModule { }
