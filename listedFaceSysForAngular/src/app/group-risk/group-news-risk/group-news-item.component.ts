import { Component, Input } from '@angular/core';

import { CompanyNews } from '../../common/model/company-news';
import {GroupTrackMoreComponent} from "./group-track-more.component";

@Component({
  selector: 'app-group-news-item',
  templateUrl: './group-news-item.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class GroupNewsItemComponent {
  @Input() newsTrack: CompanyNews;

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
