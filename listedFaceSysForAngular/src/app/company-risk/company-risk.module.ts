import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { NgxEchartsModule } from 'ngx-echarts';
import { BsDatepickerModule, PaginationModule, ModalModule, TabsModule, AccordionModule } from 'ngx-bootstrap';

import { CompanyRiskComponent } from './company-risk.component';
import { CompanyRiskRoutingModule } from './company-risk-routing.module';

import { PublicModule } from '../public/public.module';

import { CompanyRiskTrendMapComponent } from './company-risk-trend-map/company-risk-trend-map.component';
import { CompanyNoticeTrackComponent } from './company-notice/company-notice-track.component';
import { CompanyNoticeItemComponent } from './company-notice/company-notice-item.component';
import { CompanyNoticeMoreComponent } from './company-notice/company-notice-more.component';
import { OpinionEventTrackComponent } from './opinion-event/opinion-event-track.component';
import { OpinionEventItemComponent } from './opinion-event/opinion-event-item.component';
import { OpinionEventMoreComponent } from './opinion-event/opinion-event-more.component';
import { FinancialInfoComponent } from './financial-info/financial-info.component';
import { RiskEarlyWarningComponent } from './risk-early-warning/risk-early-warning.component';
import { ShareInvesInfoComponent } from './share-inves-info/share-inves-info.component';
import { CompanyInfoComponent } from './company-info/company-info.component';
import { FinancialReportComponent } from './financial-report/financial-report.component';
import { FinancialChartComponent } from './financial-report/financial-chart.component';

import { CompanyRiskApiService } from '../common/api/company-risk-api.service';
import { CompanyChartComponent } from './company-chart/company-chart.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxEchartsModule,
    PublicModule,
    CompanyRiskRoutingModule,
    AccordionModule.forRoot(),
    BsDatepickerModule.forRoot(),
    PaginationModule.forRoot(),
    ModalModule.forRoot(),
    TabsModule.forRoot()
  ],
  declarations: [
    CompanyRiskComponent,
    CompanyInfoComponent,         // 公司信息
    CompanyRiskTrendMapComponent, // 新闻事件趋势图
    CompanyNoticeTrackComponent,  // 公司公告
    CompanyNoticeItemComponent,
    CompanyNoticeMoreComponent,
    OpinionEventTrackComponent,   // 舆情事件
    OpinionEventItemComponent,
    OpinionEventMoreComponent,
    FinancialInfoComponent,       // 财务信息
    FinancialReportComponent,     // 财务报表
    RiskEarlyWarningComponent,    // 风险预警
    ShareInvesInfoComponent,       // 股东及投资信息
    FinancialChartComponent,
    CompanyChartComponent
  ],
  providers:[
    CompanyRiskApiService
  ],
  entryComponents: [
    FinancialReportComponent
  ]
})
export class CompanyRiskModule { }
