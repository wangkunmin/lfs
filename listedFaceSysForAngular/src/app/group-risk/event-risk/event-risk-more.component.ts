import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';
import { CommonUtil } from '../../common/utill/common-util';
import { GroupRiskApiService } from "../../common/api/group-risk-api.service";
import {LocalStorageService} from "../../common/service/localStorage.service";

@Component({
  selector: 'app-event-risk-more',
  templateUrl: './event-risk-more.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class EventRiskMoreComponent implements OnInit {
  userId;
  newsTrack: any;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  startDate: string;
  endDate: string;
  riskType: number;
  negativeType: number;
  importanceType: number;

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
    const start = +this.activatedRoute.snapshot.paramMap.get('startDate');
    const end = +this.activatedRoute.snapshot.paramMap.get('endDate');
    const riskType = +this.activatedRoute.snapshot.paramMap.get("riskType");
    const negativeType = +this.activatedRoute.snapshot.paramMap.get("negativeType");
    const importanceType = +this.activatedRoute.snapshot.paramMap.get("importanceType");
    this.riskType = riskType;
    this.negativeType = negativeType;
    this.importanceType = importanceType;
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
    let news: NegativeNewsIn = {
      page: this.currentPage,
      pageSize: this.itemsPerPage,
      dateStart: this.startDate,
      dateEnd: this.endDate,
      riskEventType :this.riskType,
      negativeType : this.negativeType,
      importanceType : this.importanceType
    };
    let body = Object.assign(news,{userId:this.userId});
    this.groupRiskApiService.getEventRisk(body)
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
