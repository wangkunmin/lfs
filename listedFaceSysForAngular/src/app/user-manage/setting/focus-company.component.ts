import {Component, DoCheck, Input, OnInit} from '@angular/core';
import {User} from "../../common/model/user";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {SettingApiService} from "../../common/api/setting-api.service";
import {Md5} from "ts-md5";
import {Subject} from "rxjs/Subject";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ParamsService} from "../../common/service/params.service";

@Component({
  selector: 'app-focus-company',
  templateUrl: './focus-company.component.html',
  styleUrls: ['./setting.component.css']
})
export class FocusCompanyComponent implements OnInit, DoCheck {
  modalRef: BsModalRef;

  searchCompany = ''; //搜索字段
  userId; //用户Id


  totalItems: number ;  //记录条总数
  pageSize: number = 10;  //单页记录条数
  currentPage: number = 1;  //页码


  focusCompanyList:any = [];

  constructor(
    private lg:LocalStorageService,
    private modalService: BsModalService,
    private paramsService: ParamsService,
    private settingApiService:SettingApiService
  ) {

  }

  ngOnInit() {
    this.userId = this.lg.get('userInfo').userId;
    this.addListCheckValue(this.focusCompanyList);
    this.findFollowedCompanyInfo(this.currentPage,this.pageSize,this.userId);

    this.paramsService.getFleshFC().subscribe(resultList => {
      this.currentPage = 1;
      this.findFollowedCompanyInfo(this.currentPage,this.pageSize,this.userId);
    });
  }

  ngDoCheck() {

  }



  //为关注公司扩展 checkValue字段
  addListCheckValue(focusCompanyList){
    for(let info of focusCompanyList){
      info.checkValue = false;
    }
  }

  //批量删除已关注公司
  delFocusCompany(focusCompanyList){
    let delCompanyList = [];
    for(let info of focusCompanyList){
      if(info.checkValue == true){
        delCompanyList.push(info);
      }
    }
    console.log(delCompanyList)
  }


  //改变checkbox
  changeCheck(ev,company){
    company.checkValue = !company.checkValue;
  }





  //公司搜索
  searchSrc(ev,src){
    if(ev.keyCode == 8){ //点删除键
      if((src+'').trim() == ''){
        this.currentPage = 1;
        this.findFollowedCompanyInfo(this.currentPage,this.pageSize,this.userId,src.trim());
        return ;
      }
    }
    if(ev.keyCode == 32) { //点击空格键 开始查询
      this.currentPage = 1;
      this.findFollowedCompanyInfo(this.currentPage,this.pageSize,this.userId,src.trim());
      return ;
    }
  }


  getAccount(ev){
    this.findFollowedCompanyInfo(ev.page,ev.itemsPerPage,this.userId,this.searchCompany.trim());
  }

  //分页查询已关注公司
  private findFollowedCompanyInfo(page,pageSize,userId,searchCompany?){
    if(searchCompany == undefined){
      searchCompany = '';
    }
    let body  = {
      page:page,
      pageSize:pageSize,
      userId:userId,
      companyNm:searchCompany
    };
    this.settingApiService.findFollowedCompanyInfo(body).subscribe(data=>{
      this.totalItems = data.count;
      if(data.code == '0'){
        this.focusCompanyList = data.data['content'];
      }else{
        this.focusCompanyList = [];
      }
    })
  }

  //关注和取消关注
  clickFollow(){
    this.paramsService.sendFocusCompany(0);//重新获取关注公司数
    this.currentPage = 1;
    this.findFollowedCompanyInfo(this.currentPage,this.pageSize,this.userId,this.searchCompany);
  }

}
