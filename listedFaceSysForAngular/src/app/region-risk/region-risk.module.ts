import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { NgxEchartsModule } from 'ngx-echarts';
import {
  AccordionModule, CarouselModule,
  PaginationModule, PopoverModule,
  RatingModule, ButtonsModule, AlertModule } from 'ngx-bootstrap';

import { PublicModule } from '../public/public.module';
import { RegionRiskRoutingModule } from './region-risk-routing.module';

import { RegionRiskComponent } from './region-risk.component';
import { GeographyComponent } from './geography/geography.component';
import { TrendChartComponent } from './trend-chart/trend-chart.component';
import { NewsEventComponent } from './news-event/news-event.component';
import { EarlyWarningComponent } from './early-warning/early-warning.component';
import { WarningItemComponent } from './early-warning/warning-item.component';
import { WarningMoreComponent } from './early-warning/warning-more.component';
import { NewsTrackComponent } from './news-track/news-track.component';
import { TrackMoreComponent } from './news-track/track-more.component';
import { NewsDetailComponent } from './news-track/news-detail.component';
import { NewsItemComponent } from './news-track/news-item.component';

import { RegionRiskApiService } from '../common/api/region-risk-api.service';
import { ParamsService } from '../common/service/params.service';
import { UserApiService } from '../common/api/user-api.service';
import {NegativeInfoComponent} from "./geography/negative-info/negative-info.component";
import {RiskDimensionComponent} from "./geography/risk-dimension/risk-dimension.component";


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxEchartsModule,
    PublicModule,
    RegionRiskRoutingModule,
    AccordionModule.forRoot(),
    CarouselModule.forRoot(),
    PaginationModule.forRoot(),
    RatingModule.forRoot(),
    ButtonsModule.forRoot(),
    AlertModule.forRoot(),
    PopoverModule.forRoot()
  ],
  declarations: [
    RegionRiskComponent,
    GeographyComponent,     // 上市公司地理分布一览
    NegativeInfoComponent,
    RiskDimensionComponent,
    TrendChartComponent,    // 监测预警趋势图
    EarlyWarningComponent,  // 监测预警新闻
    WarningItemComponent,   // 预警信息
    NewsEventComponent,     // 热点新闻
    NewsTrackComponent,     // 负面新闻跟踪
    WarningMoreComponent,   // 更多监测预警
    TrackMoreComponent,     // 更多负面跟踪
    NewsDetailComponent,    // 新闻详情
    NewsItemComponent,      // 新闻信息
  ],
  providers: [
    RegionRiskApiService,
    ParamsService,
    UserApiService
  ]
})
export class RegionRiskModule { }
