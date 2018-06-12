import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { CommonUtil } from '../../common/utill/common-util';
import {SettingApiService} from "../../common/api/setting-api.service";
import {Subject} from "rxjs/Subject";
import {ParamsService} from "../../common/service/params.service";
import {LocalStorageService} from "../../common/service/localStorage.service";

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {
  searchSrc = '';  //搜索字段
  oldSearchSrc = '';

  selectState = 0;  //搜索状态
  findDataList = [] ; //公司集

  addCompanyList = [];

  @Input() userId: number;
  @Output() addType = new EventEmitter<any>();

  constructor(
    private settingApiService: SettingApiService,
    private commonUtil: CommonUtil,
    private paramsService:ParamsService,
    private ls: LocalStorageService,
  ) {
  }

  ngOnInit() {
    this.userId = this.ls.get('userInfo').userId;
    this.documentClick();
  }

  // 监听页面回车
  documentClick(){
    let subject = new Subject<string>(); //创建观察者
    document.onclick=(ev => { //点击回车事件
      let target:any = ev.target;
      if(target.id == 'form-control-0'){
        subject.next('1'); //发送读取文件结束后内容
        return;
      }else{
        subject.next('0'); //发送读取文件结束后内容
      }

    });
    subject.asObservable().subscribe( resultList=> {
       this.selectState = +resultList;
    });
  }

  //监听键盘输入事件
  keyUpFun(ev,searchSrc){
    if((searchSrc+'').trim() == ''){ //不输入关键字 //
      this.selectState = 0;
      this.findDataList = [];
      return ;
    }
    // if( ev.keyCode == 32){  // 点击空格键开始查询
        this.findFollowedCompany(searchSrc);
    // }

  }

  isShowFindData(type:number){
    this.selectState = type;
  }

  //删除搜索字段
  delClick(){
    this.searchSrc = '';
    this.findDataList = [];
  }

  //添加公司
  clickCompany(companyInfo){
    let addFlag = true ;
    for(let i = 0; i<this.addCompanyList.length;i++){
      if(companyInfo.companyId == this.addCompanyList[i].companyId){
        addFlag = false;
        break;
      }
    }
    if(addFlag){
      this.addCompanyList.push(companyInfo);
    }
    this.searchSrc = ''; //添加结束清空关键字
    this.findDataList = []; //清空搜索结果
  }

//删除选中关系公司
  delCompany(selectCompany){
    for(let i = 0; i<this.addCompanyList.length;i++){
      if(selectCompany.companyId == this.addCompanyList[i].companyId){
        this.addCompanyList.splice(i,1);//删除指定位置元素
      }
    }
  }

  // 查询用户未关注的公司列表
  findFollowedCompany(companyName) {
    if (companyName == '') {
      this.searchSrc = '';
      return ;
    }
    if(this.commonUtil.isSpecialStr(companyName.trim())){
      return false;
    }
    this.searchSrc = companyName;
    this.settingApiService.queryNotAttentCompyInfo(this.userId,companyName.trim())
      .subscribe(
        data => {
          if (data.code == '0') {
            this.findDataList = data.data['content'];
          }else if(data.code == '1'){
            this.findDataList =[];
          }else{
            this.findDataList = null;
          }
          this.selectState = 1;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 确定
  confirm() {
    this.addType.emit({
      type:0,
      value:this.addCompanyList,
    });

  }

  cancel() {
    this.addType.emit({
      type:1
    });
  }

}
