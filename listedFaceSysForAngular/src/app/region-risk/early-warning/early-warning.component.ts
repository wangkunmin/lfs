import {Component, OnDestroy, OnInit} from '@angular/core';

import { EarlyWarning } from '../../common/model/early-warning';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import { BaseApiResponseModel } from '../../common/model/base-api-response.model';
import { ParamsService } from '../../common/service/params.service';
import { Subscription } from 'rxjs/Subscription';
import { LocalStorageService } from '../../common/service/localStorage.service';
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-early-warning',
  templateUrl: './early-warning.component.html',
  styleUrls: ['../region-risk.component.css'],
})
export class EarlyWarningComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  earlyWarningList: EarlyWarning[];
  copyEarlyWarningList: EarlyWarning[];
  userId: number; //用户Id
  getYear: string;

  interval: number;

  constructor(
    private ls:LocalStorageService,
    private regionRiskApiService: RegionRiskApiService,
    private paramsService: ParamsService,
    private commonUtil:CommonUtil
  ) {
    this.interval = 5000;
    this.userId = this.ls.get('userInfo').userId;
  }

  ngOnInit() {
    let dateYm = new Date();
    let y = dateYm.getFullYear();
    let m = dateYm.getMonth()+1;  //时间轴初始化当前月
    let initTime = y+''+(m<10?'0'+m:m);
    let timeObj0 = this.commonUtil.getStartEndTime(initTime);
    if(timeObj0 != -1){
      this.getWarningTop(timeObj0);
    }else{
      console.error("时间入参错误："+new Date().getFullYear().toString())
    }

    this.subscription =
      this.paramsService
      .getEarlyWarning()
      .subscribe(year => {
        let timeObj = this.commonUtil.getStartEndTime(year);
        if(timeObj != -1){
          this.getWarningTop(timeObj);
        }else{
          console.error("时间入参错误："+year)
        }

      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  // 获取监测预警信息
  getWarningTop(timeObj) {
    let warningRiskIn: any = {
      userId: this.userId,
      dateStart:timeObj.startTimeSrc,
      dateEnd:timeObj.endTimeSrc,
    };
    this.regionRiskApiService.getWarningTop(warningRiskIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          this.getYear = timeObj.inputTime;
          if (data.code === '0') {
            let list: EarlyWarning[];
            list = data.data['warningDataList'];
            if (list.length <= 5) {
              this.earlyWarningList = list;
              this.copyEarlyWarningList = null;
              return;
            }
            this.earlyWarningList = data.data['warningDataList'].slice(0, 5);
            this.copyEarlyWarningList = data.data['warningDataList'].slice(5, list.length);
            return;
          }
          this.earlyWarningList = null;
          this.copyEarlyWarningList = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  // 查看更多监测预警
  moreWarning() {
    window.open(`#/lfs/region/warnings/${this.getYear}`);
  }

  intervalCarousel($event) {
    if ($event) {
      this.interval = 0;
    } else {
      this.interval = 5000;
    }
  }

}
