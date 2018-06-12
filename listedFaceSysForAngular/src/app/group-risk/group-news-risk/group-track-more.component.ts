import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';
import { CommonUtil } from '../../common/utill/common-util';
import { GroupRiskApiService } from "../../common/api/group-risk-api.service";
import {LocalStorageService} from "../../common/service/localStorage.service";

@Component({
  selector: 'app-track-more',
  templateUrl: './group-track-more.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class GroupTrackMoreComponent implements OnInit {
  userId;
  newsTrack: CompanyNews;
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
    private groupRiskApiService: GroupRiskApiService
  ) {
    this.currentPage = 1;
    this.itemsPerPage = 15;
  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
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
    let body = Object.assign(news,{userId:this.userId});
    this.groupRiskApiService.getNewsTrack(body)
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
