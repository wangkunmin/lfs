import {Component, OnInit, DoCheck, Input } from '@angular/core';

import { CommonUtil } from '../../common/utill/common-util';

import { BsLocaleService } from 'ngx-bootstrap/datepicker';

import { NegativeNewsIn } from '../../common/model/negative-news-in';
import {LocalStorageService} from "../../common/service/localStorage.service";
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";

@Component({
  selector: 'app-company-notice-track',
  templateUrl: './company-notice-track.component.html',
  styleUrls: ['../company-risk.component.css']
})
export class CompanyNoticeTrackComponent implements OnInit, DoCheck {
  userId ='';//用户id
  @Input() companyId; //公司Id
  newsTrack: any;
  range: any;
  oldRange: (Date|Date[]);
  dateStart: Date =  new Date( new Date().getTime()-30*24*60*60*1000);
  dateEnd: Date = new Date();

  constructor(
    private ls:LocalStorageService,
    private commonUtil: CommonUtil,
    private bsLocaleService: BsLocaleService,
    private companyRiskApiService: CompanyRiskApiService) {

    this.range = ['',''];
  }

  //初始化时间为空  第一次点击获取上一年数据
 /* clickInit = 0;
  clickInput(){
    if(this.clickInit == 0){

      //初始化时间
      let date = new Date();
      let y = date.getFullYear();
      let m = date.getMonth()+1;
      let d = date.getDate();
      let preDate;
      if(m == 1){
        let src = (y-1)+'/12/'+(d<10?'0'+d:d);
        preDate = src
      }else{
        m = m -1;
        let src = y+'/'+(m<10?'0'+m:m)+'/'+(d<10?'0'+d:d);
        preDate = src
      }
      this.range = [new Date(preDate),date];
      this.clickInit = 1;
    }else{
      return ;
    }
  }*/


  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.bsLocaleService.use('zh-cn');
    /*this.getEventRisk(1, 5,
      this.commonUtil.dateFormat(this.dateStart, 'yyyy-MM-dd'),
      this.commonUtil.dateFormat(this.dateEnd, 'yyyy-MM-dd'));*/
  }

  // 监听时间
  ngDoCheck() {
    /*if (this.range !== this.oldRange) {
      this.dateStart = this.range[0];
      this.dateEnd = this.range[1];
      this.getEventRisk(1, 5,
        this.commonUtil.dateFormat(this.range[0], 'yyyy-MM-dd'),
        this.commonUtil.dateFormat(this.range[1], 'yyyy-MM-dd'));
      this.oldRange = this.range;
    }*/
  }

  // 获取事件公告风险
  getEventRisk(page: number, pageSize: number, startDate?: string, endDate?: string) {
    if (startDate=='NaN-aN-aN' || endDate =='NaN-aN-aN' ||
      startDate=='NaNaNaN' || endDate =='NaNaNaN'){
      this.newsTrack = null;
      return;
    }
    if(startDate != null ){
      startDate = startDate.replace(/-/g,'');
    }
    if(endDate != null ){
      endDate = endDate.replace(/-/g,'');
    }

    let news: NegativeNewsIn;
    news = {
      page: page,
      pageSize: pageSize,
      dateStart: startDate,
      dateEnd: endDate,
    };
    let body = Object.assign(news,{userId:null,companyId:this.companyId});
    this.companyRiskApiService.getEventRisk(body)
      .subscribe(
        data => {
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

  // 查看更多事件公告风险
  moreTracks() {
    if (this.dateStart && this.dateEnd) {
      window.open(`#/lfs/company/companyEventRisk/${this.dateStart.getTime()}/${this.dateEnd.getTime()}/${this.companyId}`);
      return;
    }
    window.open(`#/lfs/company/companyEventRisk/${this.companyId}`);
  }

  changeDate($event) {
    this.dateStart = $event.startDate;
    this.dateEnd = $event.endDate;
    this.getEventRisk(1, 5,
      this.commonUtil.dateFormat(this.dateStart, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.dateEnd, 'yyyyMMdd'));
  }
}
