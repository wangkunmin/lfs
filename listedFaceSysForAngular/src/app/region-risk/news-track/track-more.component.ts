import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';

import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';
import { CommonUtil } from '../../common/utill/common-util';

@Component({
  selector: 'app-track-more',
  templateUrl: './track-more.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class TrackMoreComponent implements OnInit {
  newsTrack: CompanyNews;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  startDate: string;
  endDate: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private commonUtil: CommonUtil,
    private regionRiskApiService: RegionRiskApiService
  ) {
    this.currentPage = 1;
    this.itemsPerPage = 15;
  }

  ngOnInit() {
    const start = +this.activatedRoute.snapshot.paramMap.get('startDate');
    const end = +this.activatedRoute.snapshot.paramMap.get('endDate');
    if (start === 0 || end === 0) {
      this.startDate = null;
      this.endDate = null;
    } else {
      this.startDate = this.commonUtil.dateFormat(new Date(start), 'yyyy-MM-dd');
      this.endDate = this.commonUtil.dateFormat(new Date(end), 'yyyy-MM-dd');
    }

    this.getNewsTrack();
  }

  // 获取负面新闻
  getNewsTrack($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let news: NegativeNewsIn = {
      page: this.currentPage,
      pageSize: this.itemsPerPage,
      dateStart: this.startDate,
      dateEnd: this.endDate
    };
    this.regionRiskApiService.getNewsTrack(news)
      .subscribe(
        data => {
          this.totalItems = data.count;
          if (data.code === '0') {
            this.newsTrack = data.data['content'];
            return;
          }
          this.newsTrack = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 查看新闻详情
  newsDetail(news: CompanyNews) {
    this.router.navigate(['lfs/region/news', news.companyId, news.newsCode]);
  }

}
