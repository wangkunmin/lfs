import { Component, Input } from '@angular/core';

import {EventRiskInfo} from "../../common/model/event-risk-info";
import {GroupRiskApiService} from "../../common/api/group-risk-api.service";

@Component({
  selector: 'app-event-risk-item',
  templateUrl: './event-risk-item.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class EventRiskItemComponent {
  @Input() newsTrack: EventRiskInfo[];
  @Input() rpflag: boolean;

  constructor(
    private groupRiskApiService: GroupRiskApiService,
  ) { }
  // 查看新闻详情
  newsDetail(news: EventRiskInfo) {
      this.groupRiskApiService.getEventRiskDetail(news.infoCd)
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
    window.open(`#/lfs/company/id/${companyId}`);
  }

  //查看全部公告
  clickAllNotice($event,companyId){
    $event.stopPropagation();
    window.open(`#/lfs/group/eventRCA/${companyId}`);
  }

  //点击标题
  /*clickNoticeTitle($event,url){
    $event.stopPropagation();
    window.open(url);
  }*/
}
