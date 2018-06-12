import {Component, OnInit, TemplateRef} from '@angular/core';

import {BaseApiResponseModel} from "../../../common/model/base-api-response.model";
import {ManageApiService} from "../../../common/api/manage-api.service";
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {CompanyBaseInfo} from "../../../common/model/company-aintain";
import { CommonUtil } from '../../../common/utill/common-util';

@Component({
  selector: 'app-company-base-info',
  templateUrl: './company-base-info.component.html',
  styleUrls: ['../manage.component.css']
})
export class CompanyBaseInfoComponent implements OnInit {
  useModel = 0; //1新增 2,编辑  3删除

  watchInfo: CompanyBaseInfo[]; //公司信息列表
  totalItems: number;  //记录条总数
  itemsPerPage: number;  //每页记录条数
  currentPage: number;  //页码

  company: string;
  watchData: CompanyBaseInfo; //单个公司信息

  modalRef: BsModalRef;

  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private utils : CommonUtil,
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
    if(companyName==undefined || companyName == null || this.company == ''){
      companyName = -1; //查询所有
    }
    if(companyName !=-1 && this.utils.isSpecialStr(companyName)){
      this.watchInfo = null;
      this.currentPage = 1;
      this.totalItems = 0;
      return false;
    }
    this.api.findAllCompanyBaseInfo( this.currentPage,this.itemsPerPage,companyName)
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
  setWatchInfo(data: CompanyBaseInfo[]) {
    this.watchInfo = data;
  }

  //条件查询
  search() {
    this.getWatch(null, this.company);
  }

  //打开模态框
  edit(template: TemplateRef<any>, data) {
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
}


