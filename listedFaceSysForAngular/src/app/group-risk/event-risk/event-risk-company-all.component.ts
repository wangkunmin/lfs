import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';
import { CommonUtil } from '../../common/utill/common-util';
import { GroupRiskApiService } from "../../common/api/group-risk-api.service";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {RiskInData} from "../../common/model/risk-in-data";

@Component({
  selector: 'app-event-risk-company-all',
  templateUrl: './event-risk-company-all.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class EventRiskCompanyAllComponent implements OnInit {
  userId;
  newsTrack: any;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  companyId: string;

  constructor(
    private ls:LocalStorageService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private commonUtil: CommonUtil,
    private groupRiskApiService: GroupRiskApiService
  ) {
    this.currentPage = 1;
    this.itemsPerPage = 15;
  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    const companyId = this.activatedRoute.snapshot.paramMap.get('companyId');

    if (companyId === '' &&  companyId === null) {
      this.companyId = '';

    } else {
      this.companyId = companyId;
    }

    this.getNewsTrack();
  }

  // 获取负面新闻
  getNewsTrack($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let body :RiskInData = {
      userId:this.userId,
      page: this.currentPage,
      pageSize: this.itemsPerPage,
      companyId:this.companyId,
    };
    this.groupRiskApiService.getSingleEventRisk(body)
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
