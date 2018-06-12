import { Component, Input } from '@angular/core';

import { CompanyNews } from '../../common/model/company-news';

@Component({
  selector: 'app-news-item',
  templateUrl: './news-item.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsItemComponent {
  @Input() newsTrack: CompanyNews;

  constructor() { }

  // 查看新闻详情
  newsDetail(news: CompanyNews) {
    window.open(`#/lfs/region/news/${news.companyId}/${news.newsCode}`);
  }

  //打开公司展台
  openCompanyDetail(news: CompanyNews,ev){
    ev.stopPropagation();//防止事件广播
    window.open(`#/lfs/company/id/${news.companyId}`);
  }
}
