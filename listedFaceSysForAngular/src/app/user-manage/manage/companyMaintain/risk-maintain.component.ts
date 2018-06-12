import {Component, OnInit, TemplateRef} from '@angular/core';

import {CommonUtil} from "../../../common/utill/common-util";
import {BaseApiResponseModel} from "../../../common/model/base-api-response.model";
import {ManageApiService} from "../../../common/api/manage-api.service";
import {CompanySuperviseInfo} from "../../../common/model/company-supervise-info";
import {UserAttentionIn} from "../../../common/model/user-attention-in";
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {CompanyRiskIn} from "../../../common/model/company-aintain";
import {Base} from "../../../common/constant/api-base.const";

@Component({
  selector: 'app-risk-maintain',
  templateUrl: './risk-maintain.component.html',
  styleUrls: ['../manage.component.css']
})
export class RiskMaintainComponent implements OnInit {
  useModel = 0; //1新增 2,编辑  3删除

  watchInfo: CompanyRiskIn[]; //公司信息列表
  totalItems: number;  //记录条总数
  itemsPerPage: number;  //每页记录条数
  currentPage: number;  //页码

  company: string;
  watchData: CompanyRiskIn; //单个公司信息

  modalRef: BsModalRef;

  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private utils:CommonUtil,
  ) {
    this.itemsPerPage = 10;
    this.currentPage = 1;
  }

  ngOnInit() {
    this.getWatch();
  }


  //获取公司列表
  getWatch($event?, companyName?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    if(companyName==undefined || companyName == null || companyName ==''){
      companyName = -1;
    }
    if(companyName !=-1 && this.utils.isSpecialStr(companyName)){
      this.watchInfo=null;
      this.currentPage = 1;
      this.totalItems = 0;
      return false;
    }
    this.api.findAllRiskInfo( this.currentPage,this.itemsPerPage,companyName)
      .subscribe(
        (data: BaseApiResponseModel) => {
          this.totalItems = data.count;
          if (data.code === '0') {
            this.setWatchInfo(data.data['content']);
            return;
          }
          this.watchInfo = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  //公司集合信息空数据处理
  setWatchInfo(data: CompanyRiskIn[]) {
    for (let i = 0; i < data.length; i++) {
      let riskType = data[i].riskType;
      if([1,2,3,4].indexOf(riskType)<0){
        data[i].riskTypeSrc = '';
      }
      for(let ratingTemp of Base.riskTypeList){
        if(riskType == ratingTemp.key){
          data[i].riskTypeSrc = ratingTemp.value;
        }
      }
    }
    this.watchInfo = data;
  }

  //条件查询
  search() {
    let ev = {page:1};
    this.getWatch(ev, this.company);
  }

  //打开模态框
  edit(template: TemplateRef<any>, data) {
    this.useModel = 1;
    this.watchData = data;
    this.modalRef = this.modalService.show(template, {ignoreBackdropClick: true});
  }


  //关闭模态框
  editWatch($event) {
      let tempEv = {
        page:this.currentPage
      };
      this.getWatch(tempEv,this.company);
      this.modalRef.hide();
  }


  //新增
  add(template: TemplateRef<any>) {
    this.useModel = 0;
    this.modalRef = this.modalService.show(template, {ignoreBackdropClick: true});
  }
}


