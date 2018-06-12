import {Component, OnInit } from '@angular/core';

import { CommonUtil } from '../../common/utill/common-util';

import { BsLocaleService } from 'ngx-bootstrap/datepicker';

import { CompanyNews } from '../../common/model/company-news';
import {GroupRiskApiService} from "../../common/api/group-risk-api.service";
import {LocalStorageService} from "../../common/service/localStorage.service";

@Component({
  selector: 'app-group-news-track',
  templateUrl: './group-news-track.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class GroupNewsTrackComponent implements OnInit {
  userId='';
  newsTrack: any;
  startDate: Date =  new Date( new Date().getTime()-30*24*60*60*1000);
  endDate: Date = new Date();

  constructor(
    private ls:LocalStorageService,
    private commonUtil: CommonUtil,
    private bsLocaleService: BsLocaleService,
    private groupRiskApiService: GroupRiskApiService) {

  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.bsLocaleService.use('zh-cn');
    /*this.getNewsTrack(1, 5,
      this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd'));*/

  }

  changeDate($event) {
    this.startDate = $event.startDate;
    this.endDate = $event.endDate;
    this.getNewsTrack(1, 5,
      this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd'));
  }

  // 获取负面新闻
  getNewsTrack(page: number, pageSize: number, startDate?: string, endDate?: string) {
    if (startDate=='NaN-aN-aN' || endDate =='NaN-aN-aN' ||
      startDate=='NaNaNaN' || endDate =='NaNaNaN'){
      this.newsTrack = null;
      return;
    }
    let news  = {
      page: page,
      pageSize: pageSize,
      dateStart: startDate,
      dateEnd: endDate
    };
    let body = Object.assign(news,{userId:this.userId});
    this.groupRiskApiService.getNewsTrack(body)
      .subscribe(
        data => {
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

  // 查看更多负面新闻
  moreTracks() {
    if (this.startDate && this.endDate) {
      window.open(`#/lfs/group/moreGroupNews/${this.startDate.getTime()}/${this.endDate.getTime()}`);
      return;
    }
    window.open(`#/lfs/group/moreGroupNews`);
  }

}
