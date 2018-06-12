import {Component, OnInit, DoCheck, Input} from '@angular/core';

import { CommonUtil } from '../../common/utill/common-util';

import { BsLocaleService } from 'ngx-bootstrap/datepicker';

import {LocalStorageService} from "../../common/service/localStorage.service";
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";
import {NgxEchartsService} from "ngx-echarts";

@Component({
  selector: 'app-opinion-event-track',
  templateUrl: './opinion-event-track.component.html',
  styleUrls: ['../company-risk.component.css']
})
export class OpinionEventTrackComponent implements OnInit, DoCheck {
  @Input() companyId;
  userId='';
  newsTrack: any[];
  startDate: Date =  new Date( new Date().getTime()-30*24*60*60*1000);
  endDate: Date = new Date();

  constructor(
    private ls:LocalStorageService,
    private commonUtil: CommonUtil,
    private bsLocaleService: BsLocaleService,
    private es: NgxEchartsService,
    private companyRiskApiService: CompanyRiskApiService) {

  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.bsLocaleService.use('zh-cn');
    /*this.getNewsTrack(1, 5,this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd'));*/
    // this.getWordCloud(this.companyId);
  }

  // 监听时间
  ngDoCheck() {

  }

  changeDate($event) {
    this.startDate = $event.startDate;
    this.endDate = $event.endDate;
    this.getNewsTrack(1, 5,
      this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd'));
  }

  countNnm:number=0; //记录条数
  // 获取负面新闻
  getNewsTrack(page: number, pageSize: number, startDate?: string, endDate?: string) {
    if (startDate=='NaN-aN-aN' || endDate =='NaN-aN-aN' ||
      startDate=='NaNaNaN' || endDate =='NaNaNaN'){
      this.newsTrack = null;
      return;
    }
    let body = {
      page:page,
      pageSize:pageSize,
      keyword :this.keyword.trim(),
      companyId :this.companyId,
      compyId:this.companyId,
      dateStart :startDate,
      dateEnd :endDate
    };
    this.companyRiskApiService.getNewsTrack(body)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.countNnm = data.count;
            this.newsTrack = data.data['content'];
            return;
          }else if (data.code != '0'){
            this.countNnm = 0;
          }
          this.newsTrack = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  upDateTime = '';
  //获取词云数据
  getWordCloud(companyId) {
    this.upDateTime = this.commonUtil.dateFormat(new Date(),'yy-MM-dd hh:mm');
    this.companyRiskApiService.getWordCloud(companyId)
      .subscribe(
        data => {
          //初始化词云图
          this.getChartsData(data);
          return;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 查看更多负面新闻
  moreTracks() {
    if (this.startDate && this.endDate) {
      window.open(`#/lfs/company/companyEventMore/${this.companyId}/${this.startDate.getTime()}/${this.endDate.getTime()}`);
      return;
    }
    window.open(`#/lfs/company/companyEventMore/${this.companyId}`);
  }

  options:any = {};//echar词云配置

  //获取图形数据值
  getChartsData(cloudData){
    var maskImage = new Image();
    maskImage.src = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABQAAAACgCAIAAADcolbpAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAPKSURBVHhe7dcxAcAwDMCwrPw5Z09R1NJjDP52dwAAAOB15xYAAACeZoABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQIIBBgAAIMEAAwAAkGCAAQAASDDAAAAAJBhgAAAAEgwwAAAACQYYAACABAMMAABAggEGAAAgwQADAACQYIABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQIIBBgAAIMEAAwAAkGCAAQAASDDAAAAAJBhgAAAAEgwwAAAACQYYAACABAMMAABAggEGAAAgwQADAACQYIABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQIIBBgAAIMEAAwAAkGCAAQAASDDAAAAAJBhgAAAAEgwwAAAACQYYAACABAMMAABAggEGAAAgwQADAACQYIABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQIIBBgAAIMEAAwAAkGCAAQAASDDAAAAAJBhgAAAAEgwwAAAACQYYAACABAMMAABAggEGAAAgwQADAACQYIABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQIIBBgAAIMEAAwAAkGCAAQAASDDAAAAAJBhgAAAAEgwwAAAACQYYAACABAMMAABAggEGAAAgwQADAACQYIABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQIIBBgAAIMEAAwAAkGCAAQAASDDAAAAAJBhgAAAAEgwwAAAACQYYAACABAMMAABAggEGAAAgwQADAACQYIABAABIMMAAAAAkGGAAAAASDDAAAAAJBhgAAIAEAwwAAECCAQYAACDBAAMAAJBggAEAAEgwwAAAACQYYAAAABIMMAAAAAkGGAAAgAQDDAAAQMDMD3mpBD3/RLG9AAAAAElFTkSuQmCC';
    this.options = {
      series: [{
        type: "wordCloud",
        gridSize: 18,
        sizeRange: [20, 60],
        rotationRange: [0, 0],
        rotationStep: 90,
        maskImage: maskImage,
        width: '100%',
        height: '100%',
        textStyle: {
          normal: {
            color: function () {
              switch (Math.floor(Math.random() * 5)) {
                case 0:
                  return "#E97982";
                case 1:
                  return "#8DA1C4";
                case 2:
                  return "#FCD218";
                case 3:
                  return "#CEC3B0";
                case 4:
                  return "#CFDBD6";
              }
            }
          },
          emphasis: {
            shadowColor: "#999",
            shadowBlur: 2,
            shadowOffsetY: 2
          }
        },
        data: cloudData
      }]
    };
  }

  keyword = '';//词云关键字
  //获取词云点击事件
  getClickChart(e?){
    if(this.keyword == ''){
     this.keyword =  (e.data.name).trim();
    }else{
      //this.keyword = (this.keyword +','+ e.data.name).trim();
      this.keyword = ( e.data.name).trim();
    }
    this.keyword = this.keyword.replace(/,,/g,',');
    this.getNewsTrack(1, 5);
  }

  //获取关键字
  changeInput(){
    this.getNewsTrack(1, 5);
  }
}
