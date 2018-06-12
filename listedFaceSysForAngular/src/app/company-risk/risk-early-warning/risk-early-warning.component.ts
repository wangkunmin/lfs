import {Component, Input, OnInit} from '@angular/core';
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-risk-early-warning',
  templateUrl: './risk-early-warning.component.html',
  styleUrls: ['./risk-early-warning.component.css']
})
export class RiskEarlyWarningComponent implements OnInit {
  selectType = 1;
  riskDataBaseList = [
    { type:1, value:'财务风险', activeFlag:false,count:0},
    { type:2, value:'治理风险', activeFlag:false,count:0},
    { type:3, value:'经营风险', activeFlag:false,count:0},
    { type:4, value:'市场风险', activeFlag:false,count:0},
    { type:5, value:'法律法规风险', activeFlag:false,count:0},
  ];
  @Input() companyId;
  riskWarningList:any;
  riskWarningCount:object[];
  dateStart: Date =  new Date( new Date().getTime()-365*24*60*60*1000);
  dateEnd: Date = new Date();
  constructor(
    private companyRiskApiService: CompanyRiskApiService,
    private commonUtil: CommonUtil
  ) {
  }

  ngOnInit() {
    this.getRiskWarning(this.companyId,1);
    this.getRiskWarningCount(this.companyId,
      this.commonUtil.dateFormat(this.dateStart, 'yyyyMMdd'),
      this.commonUtil.dateFormat(this.dateEnd, 'yyyyMMdd'));
  }

  //get风险预警信息
  getRiskWarning(companyId,type){
    this.riskWarningList = [];
    this.selectType = type;
    let dateStart =  this.commonUtil.dateFormat(this.dateStart, 'yyyyMMdd');
    let dateEnd = this.commonUtil.dateFormat(this.dateEnd, 'yyyyMMdd')
    this.companyRiskApiService.getRiskWarning(companyId,type,dateStart,dateEnd)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.riskWarningList = data.data['warningInfoList'];
            return;
          }else if(data.code === '1') {
            this.riskWarningList = [];
            return;
          }else{
            this.riskWarningList = [];
            return;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

  //get风险预警信息count数
  getRiskWarningCount(companyId,dateStart,dateEnd){
    this.companyRiskApiService.getRiskWarningCount(companyId,dateStart,dateEnd)
      .subscribe(
        data => {
          if (data.code === '0') {

            this.riskDataBaseList[0].count =data.data['caiWu'];
            this.riskDataBaseList[1].count =data.data['zhiLi'];
            this.riskDataBaseList[2].count =data.data['jingYin'];
            this.riskDataBaseList[3].count =data.data['shiChang'];
            this.riskDataBaseList[4].count =data.data['faLv'];
            return;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

}
