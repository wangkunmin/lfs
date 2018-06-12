import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { CompanyNews } from '../../common/model/company-news';
import { CommonUtil } from '../../common/utill/common-util';
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";

@Component({
  selector: 'app-opinion-event-more',
  templateUrl: './opinion-event-more.component.html',
  styleUrls: ['../company-risk.component.css']
})
export class OpinionEventMoreComponent implements OnInit {
  keyword = ''; //关键字
  companyId = ''; //公司id
  newsTrack: any;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  startDate: string;
  endDate: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private commonUtil: CommonUtil,
    private companyRiskApiService: CompanyRiskApiService
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
    this.companyId = this.activatedRoute.snapshot.paramMap.get('companyId');
    this.getNewsTrack();
  }

  // 获取负面新闻
  getNewsTrack($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let body = {
      page: this.currentPage,
      pageSize: this.itemsPerPage,
      keyword :this.keyword,
      companyId :this.companyId,
      compyId:this.companyId,
      dateStart :this.startDate,
      dateEnd :this.endDate
    };
    this.companyRiskApiService.getNewsTrack(body)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.newsTrack = [];
            this.totalItems = data.count;
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
