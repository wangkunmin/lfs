import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { EarlyWarning } from '../../common/model/early-warning';
import { WarningRiskIn } from '../../common/model/warning-risk-in';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import { BaseApiResponseModel } from "../../common/model/base-api-response.model";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-warning-more',
  templateUrl: './warning-more.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class WarningMoreComponent implements OnInit {
  earlyWarningList: EarlyWarning[];
  userId: number;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;
  getYear: string;
  num: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private regionRiskApiService: RegionRiskApiService,
    private commonUtil:CommonUtil
  ) {
    this.userId = null;
    this.currentPage = 1;
    this.itemsPerPage = 15;
  }

  ngOnInit() {
    this.getWarningTop();
  }

  getWarningTop($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    const year = this.activatedRoute.snapshot.paramMap.get('year');
    let timeObj:any = this.commonUtil.getStartEndTime(year);
    if(timeObj != -1){
      let warningRiskIn: any = {
        userId: this.userId,
        dateStart:timeObj.startTimeSrc,
        dateEnd:timeObj.endTimeSrc,
        pageSize: this.itemsPerPage,
        page: this.currentPage
      };
      this.regionRiskApiService.getWarningTop(warningRiskIn)
        .subscribe(
          (data: BaseApiResponseModel) => {
            this.totalItems = data.count;
            this.getYear = year.toString();
            if (data.code === '0') {
              this.num = (this.currentPage - 1) * this.itemsPerPage + 1;
              this.earlyWarningList = data.data['warningDataList'];
              return;
            }
            this.earlyWarningList = null;
          },
          (error: any[]) => console.log('Error: ' + error),
        );
    }else{
      console.error("时间入参错误："+year)
    }

  }
}
