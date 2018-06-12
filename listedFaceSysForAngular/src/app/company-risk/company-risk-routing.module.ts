import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompanyRiskComponent } from './company-risk.component';
import { CompanyNoticeMoreComponent } from './company-notice/company-notice-more.component';
import { OpinionEventMoreComponent } from './opinion-event/opinion-event-more.component';

const routes: Routes = [
  { path: 'id/:companyId', component: CompanyRiskComponent },
  {
    path: 'companyEventRisk/:companyId',
    component: CompanyNoticeMoreComponent   //  单个公司更多事件公告风险
  },
  {
    path: 'companyEventRisk/:dateStart/:dateEnd/:companyId',
    component: CompanyNoticeMoreComponent   //  单个公司更多事件公告风险
  },
  {
    path: 'companyEventMore/:companyId',
    component: OpinionEventMoreComponent   //  公司展台更多舆情事件
  },
  {
    path: 'companyEventMore/:companyId/:startDate/:endDate',
    component: OpinionEventMoreComponent   //  公司展台更多舆情事件
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyRiskRoutingModule { }
