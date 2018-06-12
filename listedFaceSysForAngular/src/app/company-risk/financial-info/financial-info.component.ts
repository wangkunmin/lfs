import { Component, Input, OnInit, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { ActivatedRoute } from '@angular/router';

import { CompanyRiskApiService } from '../../common/api/company-risk-api.service';
import { Financial } from '../../common/model/financial';

import { CommonUtil } from '../../common/utill/common-util';

@Component({
  selector: 'app-financial-info',
  templateUrl: './financial-info.component.html',
  styleUrls: ['./financial-info.component.css']
})
export class FinancialInfoComponent implements OnInit {
  @Input() companyId: number;
  @Input() companyName: string;
  modalRef: BsModalRef;
  financialData: Map<string, Array<string>>;
  notFinanceFlag: Array<boolean>;
  financialFlag: boolean;

  constructor(
    private activatedRoute: ActivatedRoute,
    private util: CommonUtil,
    private modalService: BsModalService,
    private companyRiskApiService: CompanyRiskApiService
  ) {
    this.financialFlag = false;
  }

  ngOnInit() {
    this.getFinanceInfo();
  }

  // 获取主要财务数据
  getFinanceInfo() {
    this.companyRiskApiService.getFinanceInfo(this.companyId)
      .subscribe(
        data => {
          if (data.code === '0') {
            let financeInfo = this.setFinanceData(data.data['content']);

            this.notFinanceFlag = this.setFinanceFlag(financeInfo);
            this.financialFlag = this.setFinancialFlag(this.notFinanceFlag);
            this.financialData = financeInfo;
            return;
          }
          this.financialData = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 整合财务数据数据
  setFinanceData(finance: Financial[]): Map<string, Array<string>> {
    let map = new Map();
    let rptDtBak: Array<string> = [],
      sumAsset: Array<string> = [],
      sumLiab: Array<string> = [],
      sumLliab: Array<string> = [],
      operateReve: Array<string> = [],
      operateExp: Array<string> = [],
      operateProfit: Array<string> = [],
      sumProfit: Array<string> = [],
      netProfit: Array<string> = [],
      netOperateCashflow: Array<string> = [],
      netInvCashflow: Array<string> = [],
      netFinaCashflow: Array<string> = [],
      nicashEqui: Array<string> = [],
      rptTimetypeCd: Array<string> = [];
    for (let i = 0; i < finance.length; i++) {
      if (finance[i]) {
        rptTimetypeCd.push(this.util.getRptTimeType(finance[i].rptTimetypeCd + '') + '合并');
        rptDtBak.push(this.util.getRptDtBak(finance[i].rptDtBak + ''));
        sumAsset.push(this.util.financeMoney(finance[i].sumAsset/10000, 2));
        sumLiab.push(this.util.financeMoney(finance[i].sumLiab/10000, 2));
        sumLliab.push(this.util.financeMoney(finance[i].sumLliab/10000, 2));
        operateReve.push(this.util.financeMoney(finance[i].operateReve/10000, 2));
        operateExp.push(this.util.financeMoney(finance[i].operateExp/10000, 2));
        operateProfit.push(this.util.financeMoney(finance[i].operateProfit/10000, 2));
        sumProfit.push(this.util.financeMoney(finance[i].sumProfit/10000, 2));
        netProfit.push(this.util.financeMoney(finance[i].netProfit/10000, 2));
        netOperateCashflow.push(this.util.financeMoney(finance[i].netOperateCashflow/10000, 2));
        netInvCashflow.push(this.util.financeMoney(finance[i].netInvCashflow/10000, 2));
        netFinaCashflow.push(this.util.financeMoney(finance[i].netFinaCashflow/10000, 2));
        nicashEqui.push(this.util.financeMoney(finance[i].nicashEqui/10000, 2));
      }
    }
    map.set('报告类型', rptTimetypeCd);
    map.set('内容', rptDtBak);
    map.set('资产总计', sumAsset);
    map.set('负债合计', sumLiab);
    map.set('流动负债合计', sumLliab);
    map.set('营业收入', operateReve);
    map.set('营业成本', operateExp);
    map.set('营业利润', operateProfit);
    map.set('利润总额', sumProfit);
    map.set('净利润', netProfit);
    map.set('经营活动产生的现金流量净额', netOperateCashflow);
    map.set('投资活动产生的现金流量净额', netInvCashflow);
    map.set('筹资活动产生的现金流量净额', netFinaCashflow);
    map.set('现金及现金等价物净增加额', nicashEqui);
    return map;
  }

  // 设置单行显示
  setFinanceFlag(map): Array<boolean> {
    let flagArr = [];
    let flag = false;
    map.forEach((value, key) => {
      if (key !== '报告类型' && key !== '内容') {
        if (value.length < 4) {
          flag = true;
        } else {
          for(let i = 0; i < value.length; i++) {
            if (this.util.isSingleEmpty(value[i]) || '0' === value[i]) {
              flag = true;
            }
          }
        }
        flagArr.push(flag);
      } else {
        flagArr.push(flag);
      }
    });
    return flagArr;
  }

  // 设置所有财务数据显示
  setFinancialFlag(data): boolean {
    for (let i = 2; i < data.length; i++) {
      if (!data[i]) {
        return true;
      }
    }
    return false;
  }

  // 打开财务报表
  openFinancialReport(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-lg-report', ignoreBackdropClick: true});
  }

  // 取消关注
  closeFinancialReport($event) {
    if ($event.type === 1) {
      this.modalRef.hide();
    }
  }

}
