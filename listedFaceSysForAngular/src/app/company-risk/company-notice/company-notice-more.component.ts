import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';
import { CommonUtil } from '../../common/utill/common-util';
import {LocalStorageService} from "../../common/service/localStorage.service";
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";

@Component({
  selector: 'app-company-notice-more',
  templateUrl: './company-notice-more.component.html',
  styleUrls: ['../company-risk.component.css']
})
export class CompanyNoticeMoreComponent implements OnInit {
  companyId;
  userId;
  newsTrack: any;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  startDate: string;
  endDate: string;

  constructor(
    private ls:LocalStorageService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private commonUtil: CommonUtil,
    private companyRiskApiService: CompanyRiskApiService
  ) {
    this.currentPage = 1;
    this.itemsPerPage = 15;
  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.companyId = +this.activatedRoute.snapshot.paramMap.get('companyId');

    const start = +this.activatedRoute.snapshot.paramMap.get('dateStart');
    const end = +this.activatedRoute.snapshot.paramMap.get('dateEnd');
    if (start === 0 || end === 0) {
      this.startDate = null;
      this.endDate = null;
    } else {
      this.startDate = this.commonUtil.dateFormat(new Date(start), 'yyyyMMdd');
      this.endDate = this.commonUtil.dateFormat(new Date(end), 'yyyyMMdd');
    }

    this.getNewsTrack();
  }

  // 获取负面新闻
  getNewsTrack($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }



    let news: NegativeNewsIn;
    news = {
      page: this.currentPage,
      pageSize: this.itemsPerPage,
      dateStart: this.startDate,
      dateEnd: this.endDate,
    };
    let body = Object.assign(news,{userId:this.userId,companyId:this.companyId});
    this.companyRiskApiService.getEventRisk(body)
      .subscribe(
        data => {
          this.totalItems = data.count;
          if (data.code === '0') {
            this.newsTrack = data.data['AnncounceEventDataList'];
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
