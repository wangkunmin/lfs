import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CompanyRiskApiService} from "../common/api/company-risk-api.service";
import {CompanyRiskInfo} from "../common/model/company-risk-info";
import {CompanyInfoComponent} from "./company-info/company-info.component";

@Component({
  selector: 'app-company-risk',
  templateUrl: './company-risk.component.html',
  styleUrls: ['./company-risk.component.css']
})
export class CompanyRiskComponent implements OnInit {
  @ViewChild("appCompanyInfo") appCompanyInfo: CompanyInfoComponent;

  companyInfo: CompanyRiskInfo = new CompanyRiskInfo();
  companyId: any;
  companyNm: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private companyRiskApiService: CompanyRiskApiService
  ) {

  }

  ngOnInit() {
    this.companyId = +this.activatedRoute.snapshot.paramMap.get('companyId');
    this.initCompanyInfo();
  }

  //初始化公司基本信息
  initCompanyInfo(){
    this.companyRiskApiService.getCompanyInfo(this.companyId)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.companyInfo = data.data['content'];
            this.companyNm = this.companyInfo.companyNm;
            return;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

}
