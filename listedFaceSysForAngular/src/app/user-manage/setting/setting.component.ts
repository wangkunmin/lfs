import {Component, OnInit} from '@angular/core';
import {User} from "../../common/model/user";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {SettingApiService} from "../../common/api/setting-api.service";
import {Md5} from "ts-md5";
import {Subject} from "rxjs/Subject";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ParamsService} from "../../common/service/params.service";
import {BaseApiResponseModel} from "../../common/model/base-api-response.model";

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {


  userInfo:any = {};//获取用户线信息

  modalRef: BsModalRef;

  userId; //用户Id

  selectSetting = 0; //设置 关注
  settingList = [
    {
      key:0,
      value:'基本信息'
    },{
      key:1,
      value:'重置密码'
    },
    {
      key:2,
      value:'重点关注公司'
    }
  ];


  constructor(
    private lg:LocalStorageService,
    private modalService: BsModalService,
    private paramsService: ParamsService,
    private settingApiService:SettingApiService
  ) {
    this.userId = this.lg.get('userInfo').userId;
  }

  ngOnInit() {
      this.paramsService
        .getFocusCompany()
        .subscribe(value => {
          if(value == 0){
            this.getUserInfo(this.userId)
          }
        });
  }




  //设置 关注板块切换
  setModel(type:number){
    this.selectSetting = type;
  }


  //获取用户信息
  getInfo(outInfo){
    if(outInfo.type == 0){
      this.userInfo =outInfo.userInfo ; //用户信息
    }else{
      this.userInfo =outInfo.userInfo ;  //获取用户信息失败
    }
  }

  //添加公司
  addCompany(template){
    this.modalRef = this.modalService.show(template, {class: 'modal-lg', ignoreBackdropClick: true});
  }


  // 确定，关闭模态框
  addFollow($event) {
    if ($event.type === 0) {
      this.modalRef.hide();
      if ($event.value.length != 0) {
        this.addFocusCompany($event.value);
        return;
      }
    } else if ($event.type === 1) {
      this.modalRef.hide();
    }
  }

  //添加关注公司
  private addFocusCompany(companyList){
    let focusCompanyList = {
      userId:this.userId,
      list:companyList
    };
    this.settingApiService.batchAdd(focusCompanyList).subscribe(data => {
      if(data.code == '0'){
        this.paramsService.sendFleshFC(0);
      }else if(data.code == '1'){
      }else{
      }
      this.getUserInfo(this.userId)
    })
  }

  //获取用户基本信息
  private getUserInfo(userId){
    if(userId == ''|| userId == false){
      return '';
    }
    this.settingApiService.findUserInfo(userId).subscribe(data=>{
      let outInfo:any = {
        type:0,
        userInfo:{},
      };
      if(data.code == '0'){
        this.userInfo = data.data.userInfo;
        this.userInfo.count = data.count;
      }else{
      }
    })
  }
}
