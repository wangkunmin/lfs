import { Component, Input } from '@angular/core';

import {EventRiskInfo} from "../../common/model/event-risk-info";
import {GroupRiskApiService} from "../../common/api/group-risk-api.service";
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";

@Component({
  selector: 'app-company-notice-item',
  templateUrl: './company-notice-item.component.html',
  styleUrls: ['../company-risk.component.css']
})
export class CompanyNoticeItemComponent {
  @Input() newsTrack: EventRiskInfo[];

  constructor(
    private companyRiskApiService: CompanyRiskApiService,
  ) { }
  // 查看新闻详情
  newsDetail(news: EventRiskInfo) {
    this.companyRiskApiService.getEventRiskDetail(news.infoCd)
      .subscribe(
        data => {
          if (data.code === '0') {
            window.open(data.data['url']);
            return;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

  //点击公司名
  clickCompanyNm($event,companyId){
    $event.stopPropagation();
  }

  //查看全部公告
  clickAllNotice($event,companyId){
    $event.stopPropagation();
    window.open(`#/lfs/group/eventRCA/${companyId}`);
  }
}
