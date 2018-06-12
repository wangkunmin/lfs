import {Component, Input, OnInit} from '@angular/core';
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";
import {ShareholderInfo} from "../../common/model/shareholder-info";

@Component({
  selector: 'app-share-inves-info',
  templateUrl: './share-inves-info.component.html',
  styleUrls: ['./share-inves-info.component.css']
})
export class ShareInvesInfoComponent implements OnInit {
  shareholderInfos:ShareholderInfo[];
  @Input() companyId;

  constructor(
    private companyRiskApiService: CompanyRiskApiService,
  ) { }

  ngOnInit() {
    this.initShareInfo();
  }

  //初始化股东及投资信息
  initShareInfo(){
    let companyId = this.companyId;
    this.companyRiskApiService.getShareholder(companyId)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.shareholderInfos = data.data['content'].companyShareholderList;
            return;
          }else{
            this.shareholderInfos = [];
          }
        },
        error => {
          console.log(error);
        }
      );
  }

}
