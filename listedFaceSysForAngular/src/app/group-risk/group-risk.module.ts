import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { NgxEchartsModule } from 'ngx-echarts';
import { AccordionModule, CarouselModule, PaginationModule, RatingModule, ModalModule } from 'ngx-bootstrap';

import { PublicModule } from '../public/public.module';
import { GroupRiskRoutingModule } from './group-risk-routing.module';

import { GroupRiskComponent } from './group-risk.component';
import { GroupNewsEventComponent } from './group-news-event/group-news-event.component';
import { GroupNewsItemComponent } from './group-news-risk/group-news-item.component';
import { GroupNewsTrackComponent } from './group-news-risk/group-news-track.component';
import { GroupTrackMoreComponent } from './group-news-risk/group-track-more.component';

import { GroupRiskApiService } from '../common/api/group-risk-api.service';
import { FollowCompanyRiskComponent } from './follow-company-risk/follow-company-risk.component';
import { FollowCompanyRiskItemComponent } from './follow-company-risk/follow-company-risk-item.component';
import { EventRiskItemComponent } from './event-risk/event-risk-item.component';
import { EventRiskMoreComponent } from './event-risk/event-risk-more.component';
import { EventRiskTrackComponent } from './event-risk/event-risk-track.component';
import { AddFollowCompanyComponent } from './add-follow-company/add-follow-company.component';
import { RiskContentComponent } from './risk-content/risk-content.component';
import { RiskMenuComponent } from './risk-menu/risk-menu.component';
import { EventRiskCompanyAllComponent } from './event-risk/event-risk-company-all.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    GroupRiskRoutingModule,
    NgxEchartsModule,
    PublicModule,
    PaginationModule.forRoot(),
    RatingModule.forRoot(),
    CarouselModule.forRoot(),
    AccordionModule.forRoot(),
    ModalModule.forRoot()
  ],
  declarations: [
    GroupRiskComponent,
    GroupNewsEventComponent,//舆情风险趋势图

    FollowCompanyRiskComponent, //重点关注公司-风险TOP5
    FollowCompanyRiskItemComponent, //重点关注公司-风险TOP5详情

    GroupNewsItemComponent,//新闻舆情风险
    GroupNewsTrackComponent,//新闻舆情风险跟踪
    GroupTrackMoreComponent,//更多新闻舆情风险

    EventRiskItemComponent, //事件公告风险
    EventRiskMoreComponent, //更多事件公告风险
    EventRiskTrackComponent, //事件公告风险跟踪
    EventRiskCompanyAllComponent,//单个公司事件公告

    AddFollowCompanyComponent,  // 添加关注公司
    RiskContentComponent,
    RiskMenuComponent,

  ],
  providers: [
    GroupRiskApiService
  ],
  entryComponents: [
    AddFollowCompanyComponent
  ]
})
export class GroupRiskModule { }
