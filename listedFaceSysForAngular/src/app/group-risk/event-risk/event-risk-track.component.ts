import {Component, OnInit, DoCheck } from '@angular/core';

import { CommonUtil } from '../../common/utill/common-util';

import { BsLocaleService } from 'ngx-bootstrap/datepicker';

import { NegativeNewsIn } from '../../common/model/negative-news-in';
import {GroupRiskApiService} from "../../common/api/group-risk-api.service";
import {LocalStorageService} from "../../common/service/localStorage.service";

@Component({
  selector: 'app-event-risk-track',
  templateUrl: './event-risk-track.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class EventRiskTrackComponent implements OnInit, DoCheck {
  userId='';
  newsTrack: any;
  startDate: Date =  new Date( new Date().getTime()-30*24*60*60*1000);
  endDate: Date = new Date();
  riskType: number = 0;
  negativeType: number =0;
  importanceType: number=0;
  private riskType2: number = 0;
  riskTypes: Array<any>;

  checkBox1 = false;//负面
  checkBox2 = false;//重要性
  //点击checkBox
  setCheckBox(checkBox,type){
    if(type == 1){
      this.checkBox1 = !checkBox;
    }else if(type == 2){
      this.checkBox2 = !checkBox;
    }

    if(this.checkBox1){
     this.negativeType = 1
    }else{
      this.negativeType = 0
    }
    if(this.checkBox2){
      this.importanceType = 1
    }else{
      this.importanceType = 0
    }
    this.getEventRisk(1, 5, this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'),
      this.riskType,this.negativeType,this.importanceType);
  }

  constructor(
    private ls:LocalStorageService,
    private commonUtil: CommonUtil,
    private bsLocaleService: BsLocaleService,
    private groupRiskApiService: GroupRiskApiService) {
    this.riskTypes = [
      {
        id: 0,
        type: '全部风险'
      },
      {
        id: 1,
        type: '财务风险'
      },
      {
        id: 2,
        type: '治理风险'
      },
      {
        id: 3,
        type: '经营风险'
      },
      {
        id: 4,
        type: '市场风险'
      },
      {
        id: 5,
        type: '法律法规风险'
      },
      {
        id: 6,
        type: '其他'
      }
    ];
  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.bsLocaleService.use('zh-cn');
    /*this.getEventRisk(1, 5,this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'),0,0,0);
  */}

  ngDoCheck() {
    if (this.riskType !== this.riskType2) {
      this.riskType2 = this.riskType;
      let negativeType = 0, importanceType = 0;
      if(this.checkBox1){
        negativeType = 1
      }
      if(this.checkBox2){
        importanceType = 1
      }
      this.getEventRisk(1, 5, this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
        this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'),
        this.riskType,negativeType,importanceType);
    }
  }

  changeDate($event) {
    this.startDate = $event.startDate;
    this.endDate = $event.endDate;
    let negativeType = 0, importanceType = 0;
    if(this.checkBox1){
      negativeType = 1
    }
    if(this.checkBox2){
      importanceType = 1
    }
    this.getEventRisk(1, 5,
      this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'),this.riskType,negativeType,importanceType);
  }


  // 获取事件公告风险
  getEventRisk(page: number, pageSize: number, startDate: string, endDate: string, riskEventType: number, negativeType: number, importanceType: number) {

    if (startDate=='NaN-aN-aN' || endDate =='NaN-aN-aN' ||
      startDate=='NaNaNaN' || endDate =='NaNaNaN'){
      this.newsTrack = null;
      return;
    }
    let news: NegativeNewsIn;
    news = {
      page: page,
      pageSize: pageSize,
      dateStart: startDate,
      dateEnd: endDate,
      riskEventType: riskEventType, //0 1 2
      negativeType: negativeType,  //负面类型 0：否，1：是
      importanceType: importanceType, //重要类型 0：否，1：是
    };
    let body = Object.assign(news,{userId:this.userId});
    this.groupRiskApiService.getEventRisk(body)
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
    if (this.startDate && this.endDate) {
      window.open(`#/lfs/group/eventRiskMore/${this.startDate.getTime()}/${this.endDate.getTime()}/${this.riskType}/${this.negativeType}/${this.importanceType}`);
      return;
    }
    window.open(`#/lfs/group/eventRiskMore/${this.riskType}`);
  }

}
