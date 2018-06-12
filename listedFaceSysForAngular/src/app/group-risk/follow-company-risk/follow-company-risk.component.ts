import { Component, ElementRef, OnInit, QueryList, Renderer2, ViewChildren} from '@angular/core';

import { EarlyWarning } from '../../common/model/early-warning';

import { BaseApiResponseModel } from '../../common/model/base-api-response.model';
import { LocalStorageService } from '../../common/service/localStorage.service';
import {GroupRiskApiService} from "../../common/api/group-risk-api.service";
import {CommonUtil} from "../../common/utill/common-util";
import {Router} from '@angular/router';

@Component({
  selector: 'app-follow-company-risk',
  templateUrl: './follow-company-risk.component.html',
  styleUrls: ['../group-risk.component.css'],
})
export class FollowCompanyRiskComponent implements OnInit  {
  @ViewChildren('newDate') newDateR: QueryList<ElementRef>;

  startDate: Date =  new Date( new Date().getTime()-30*24*60*60*1000);
  endDate: Date = new Date();

  followTopList: EarlyWarning[];
  copyFollowTopList: EarlyWarning[];
  userId: number;
  getYear: string;
  newDate: any;

  constructor(
    private commonUtil: CommonUtil,
    private renderer2: Renderer2,
    private router: Router,
    private ls:LocalStorageService,
    private groupRiskApiService: GroupRiskApiService,
  ) {
    this.userId = this.ls.get('userInfo').userId;
  }

  //初始化时间为空  第一次点击获取上一年数据
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

  // 监听时间
  // ngDoCheck() {
  //   if (this.range !== this.oldRange) {
  //     this.startDate = this.range[0];
  //     this.endDate = this.range[1];
  //     this.getFollowTop( this.commonUtil.dateFormat(this.range[0], 'yyyyMMdd'),
  //       this.commonUtil.dateFormat(this.range[1], 'yyyyMMdd'));
  //     this.oldRange = this.range;
  //   }
  // }

  isCheckDate = true;
  changeNewDate(num) {
    this.newDateR.forEach((el, i) => {
      if (num === i+1) {
        this.renderer2.addClass(el.nativeElement, 'color-7219');
      } else {
        this.renderer2.removeClass(el.nativeElement, 'color-7219');
      }
    });
    this.isCheckDate = false;
    this.dateObj.startDate = null;
    this.dateObj.endDate = null;

    this.getFollowTop( null,null, num);
  }

  changeDate($event) {
    if(!this.isCheckDate){
      this.isCheckDate =true;
      return;
    }
    if(this.newDateR != undefined){
      this.newDateR.forEach((el, i) => {
        if (0 === i+1) {
          this.renderer2.addClass(el.nativeElement, 'color-7219');
        } else {
          this.renderer2.removeClass(el.nativeElement, 'color-7219');
        }
      });
    }

    this.startDate = $event.startDate;
    this.endDate = $event.endDate;
    this.getFollowTop( this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
            this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'));
  }

  ngOnInit() {
    /*this.getFollowTop( this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'));*/
  }

  dateObj ; //时间对象
  initDate(dateObj){
    this.dateObj = dateObj ;
  }
  // 获取重点关注公司-风险TOP10
  getFollowTop(startDate?: string, endDate?:string, timeType?: number) {
    if (startDate=='NaN-aN-aN' || endDate =='NaN-aN-aN' ||
      startDate=='NaNaNaN' || endDate =='NaNaNaN'){
      this.followTopList = null;
      this.copyFollowTopList = null;
      return;
    }
    let warningRiskIn = {
      userId: this.userId,
      dateStart: startDate,
      dateEnd: endDate,
      page : 1,
      pageSize : 10,
      timeType: timeType
    };
    if (timeType) {
      this.groupRiskApiService.getFollowTop2(warningRiskIn)
        .subscribe(
          (data: BaseApiResponseModel) => {
            this.setData(data);
          },
          (error: any[]) => console.log('Error: ' + error),
        );
    } else {
      this.groupRiskApiService.getFollowTop(warningRiskIn)
        .subscribe(
          (data: BaseApiResponseModel) => {
            this.getYear = this.commonUtil.dateFormat(this.startDate, 'yyyy-MM-dd')+ "至" + this.commonUtil.dateFormat(this.endDate, 'yyyy-MM-dd');
            this.setData(data);
          },
          (error: any[]) => console.log('Error: ' + error),
        );
    }
  }

  setData(data) {
    if (data.code === '0') {
      let list: EarlyWarning[];
      list = data.data['warningDataList'];

      if (list.length <= 5) {
        this.followTopList = list;
        return;
      }
      this.followTopList = data.data['warningDataList'].slice(0, 5);
      this.copyFollowTopList = data.data['warningDataList'].slice(5, list.length);
      return;
    }
    this.followTopList = null;
    this.copyFollowTopList = null;
  }


  setTimeYM(){
    this.getFollowTop( this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'));
  }

  indexNum = 0
  //取消关注 增加关注刷新数据
  getFollowTopList(ev){
    if(ev.type == 0){

    }else{

    }
    this.getFollowTop( this.commonUtil.dateFormat(this.startDate, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.endDate, 'yyyyMMdd'));

    let urlPathName = window.location.hash;
    if (urlPathName.indexOf('detail') > 0) {
      this.router.navigate(['lfs/group'],{
        fragment:'11'+this.indexNum++
      });
    }else{
      this.router.navigate([`lfs/group/detail/1/111`]);
    }
  }
}
