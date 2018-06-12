import { Component, Input } from '@angular/core';

import { CompanyNews } from '../../common/model/company-news';

@Component({
  selector: 'app-opinion-event-item',
  templateUrl: './opinion-event-item.component.html',
  styleUrls: ['../company-risk.component.css']
})
export class OpinionEventItemComponent {
  @Input() newsTrack: any;

  constructor() { }

  // 查看新闻详情
  newsDetail(news: CompanyNews) {
    window.open(`#/lfs/region/news/${news.companyId}/${news.newsCode}`);
  }

  //点击公司名
  clickCompanyNm($event,companyId){
    $event.stopPropagation();
    window.open(`#/lfs/company/id/${companyId}`);
  }

}
