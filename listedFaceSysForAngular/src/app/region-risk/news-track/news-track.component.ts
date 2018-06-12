import {Component, OnInit, DoCheck } from '@angular/core';

import { CommonUtil } from '../../common/utill/common-util';

import { BsLocaleService } from "ngx-bootstrap/datepicker";
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';

import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';

@Component({
  selector: 'app-news-track',
  templateUrl: './news-track.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsTrackComponent implements OnInit {
  newsTrack: CompanyNews[];
  copyNewsTrack: CompanyNews[];
  startDate: Date =  new Date( new Date().getTime()-30*24*60*60*1000);
  endDate: Date = new Date();

  constructor(
    private commonUtil: CommonUtil,
    private bsLocaleService: BsLocaleService,
    private regionRiskApiService: RegionRiskApiService) { }

  // //初始化时间为空  第一次点击获取上一年数据
  // clickInit = 0;
  // clickInput(){
  //   if(this.clickInit == 0){
  //
  //     //初始化时间
  //     let date = new Date();
  //     let y = date.getFullYear();
  //     let m = date.getMonth()+1;
  //     let d = date.getDate();
  //     let preDate;
  //     if(m == 1){
  //       let src = (y-1)+'/12/'+(d<10?'0'+d:d);
  //       preDate = src
  //     }else{
  //       m = m -1;
  //       let src = y+'/'+(m<10?'0'+m:m)+'/'+(d<10?'0'+d:d);
  //       preDate = src
  //     }
  //     this.range = [new Date(preDate),date];
  //     this.clickInit = 1;
  //   }else{
  //     return ;
  //   }
  // }

  ngOnInit() {
    this.bsLocaleService.use('zh-cn');
    /*this.getNewsTrack(1, 10,
      this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd'),
        this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd'));*/
  }


  changeDate($event) {
    this.startDate = $event.startDate;
    this.endDate = $event.endDate;
    this.getNewsTrack(1, 10,
      this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd'));
  }

  // 监听时间
  // ngDoCheck() {
  //   if (this.range !== this.oldRange) {
  //     this.startDate = this.range[0];
  //     this.endDate = this.range[1];
  //     this.getNewsTrack(1, 10,
  //       this.commonUtil.dateFormat(this.range[0], 'yyyy-MM-dd'),
  //       this.commonUtil.dateFormat(this.range[1], 'yyyy-MM-dd'));
  //     this.oldRange = this.range;
  //   }
  // }

  loadingHidden = false; //加载状态控制 false隐藏
  // 获取负面新闻.
  getNewsTrack(page: number, pageSize: number, startDate?: string, endDate?: string) {
    if (startDate=='NaN-aN-aN' || endDate =='NaN-aN-aN' ||
      startDate=='NaNaNaN' || endDate =='NaNaNaN'){
      this.newsTrack = null;
      this.copyNewsTrack = null;
      return;
    }


    let news: NegativeNewsIn;
    news = {
      page: page,
      pageSize: pageSize,
      dateStart: startDate,
      dateEnd: endDate
    };
    this.loadingHidden = true; //开始加载
    this.regionRiskApiService.getNewsTrack(news)
      .subscribe(
        data => {
          this.loadingHidden = false; //加载结束
          if (data.code === '0') {
            let list: CompanyNews[] = data.data['content'];
            if (list.length <= 5) {
              this.newsTrack = list;
              return;
            }
            this.newsTrack = list.slice(0, 5);
            this.copyNewsTrack = list.slice(5, list.length);

            return;
          }
          this.newsTrack = null;
          this.copyNewsTrack = null;
        },
        error => {
          this.loadingHidden = false; //加载结束
          console.log(error);
        }
      );
  }

  // 查看更多负面新闻
  moreTracks() {
    if (this.startDate && this.endDate) {
      window.open(`#/lfs/region/tracks/${this.startDate.getTime()}/${this.endDate.getTime()}`);
      return;
    }
    window.open(`#/lfs/region/tracks`);
  }

}
