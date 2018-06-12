import { Component, OnInit, OnDestroy } from '@angular/core';

import { Subscription } from 'rxjs/Subscription';

import { LoginService } from '../../common/service/login.service';
import {LocalStorageService} from "../../common/service/localStorage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HeaderService} from "../../common/service/header.service";
import {HeaderParams} from "../../common/model/headerParams";
import {HeaderState} from "../../common/model/headerState";
import {SearchType} from "../../common/constant/header.const";
import {PublicApiService} from "../../common/api/public-api.service";
import {Subject} from "rxjs/Subject";
import {ParamsService} from "../../common/service/params.service";
import { CommonUtil } from '../../common/utill/common-util';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  searchSrc = ''; //搜索关键字
  oldSearchSrc = ''; //保存搜索关键字

  selectState = 0;//检索状态
  findDataList = []; //查询公司所有集

  userInfo:any = {};
  loginFlag: boolean=false;
  loginSub: Subscription;
  headerSub: Subscription;
  manageFlag: boolean;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private lg:LocalStorageService,
    private headerService :HeaderService,
    private publicApiService :PublicApiService,
    private paramsService:ParamsService,
    private utils:CommonUtil,
    private loginService: LoginService) {
    this.manageFlag = false;
  }

  ngOnInit() {

    this.lg.set("isHeadrInputEntr",false);

    document.body.style.paddingBottom  = "0";
    if(this.lg.get("userInfo")==false){
      this.router.navigate(['home']);
    }
    this.loginSub = this.loginService
      .getLogin()
      .subscribe(headerState => {
        if(this.lg.get('userInfo') != undefined ){
          if (this.lg.get("userInfo").roleId === '1') {
            this.manageFlag = true;
          } else {
            this.manageFlag = false;
          }
        }
        this.headerHidden(headerState);
      });

    this.headerSub = this.headerService
      .getData()
      .subscribe( headerParams=> {
        let hp: HeaderParams = headerParams;
        this.searchSrc = hp.searchSrc; //关键字赋值
        if(hp.searchType == SearchType.atlasForHeader){
          this.openFindCompanyData(hp);
        }
      });
    this.getUrlPathName();
    this.documentClick();
  }

  //打开关系图谱查询公司信息
  openFindCompanyData(headerParams:HeaderParams){
    if(this.utils.isSpecialStr(headerParams.searchSrc)){
      return;
    }

    window.open('#/lfs/atlas/findMoreCompany/'+headerParams.searchSrc);
  }

  headerState = 0; //头部部分内容显隐控制
  //头部信息显隐控制
  headerHidden(headerState:HeaderState){
    this.headerState = 0;

    if(headerState.loginFlag == 0){
      this.loginFlag = true;
    }else if(headerState.loginFlag == 1){
      this.loginFlag = false;
    }else if(headerState.loginFlag == 2){
      this.loginFlag = true;
      this.headerState = 2;
      this.searchSrc = '';
    }
    if(this.loginFlag){
      this.userInfo = this.lg.get("userInfo");
      if(this.userInfo==false){
        this.router.navigate(['home/login']);
      }
      document.body.style.paddingBottom  = "50px";
    }else{
      document.body.style.paddingBottom  = "0";
    }
  }

  ngOnDestroy() {
    this.loginSub.unsubscribe();
    this.headerSub.unsubscribe();
  }

  searchType:number ; //0 关系图谱回车查询

  getUrlPathName(){
    let urlPathName = window.location.hash;  //  #/lfs/atlas

    if(urlPathName.indexOf('lfs/atlas')>=0){ //#/lfs/atlas 关系图谱
      this.searchType = SearchType.headerAtlasData;

    }else if(urlPathName.indexOf('lfs/atlas/findMoreCompany/')>=0){ //atlas 关系图谱公司详情
      let src = urlPathName.replace('#/lfs/atlas/findMoreCompany/','');

      this.searchType = SearchType.headerAtlasDelData;
      this.searchSrc = decodeURIComponent(src);// url解码
    }

  }
  //退出登录
  exitLogin(){
    this.searchSrc = '';
    let remember = this.lg.get('remember');
    this.lg.clear(); //清理用户信息
    this.lg.set('remember', remember);
    this.router.navigate(['home']);
  }

  //监听键盘输入事件
  keyUpFun(ev,searchSrc){
    this.lg.set("isHeadrInputEntr",true);
    if((searchSrc+'').trim() == ''){ //不输入关键字 //
      this.selectState = 0;
      this.findDataList = [];
      return ;
    }
    if(ev.keyCode == 13 ){  //点击回车键
      let headerParams:HeaderParams = new HeaderParams();
      if( this.searchType == SearchType.headerAtlasDelData){
        headerParams.searchType = this.searchType;
        headerParams.searchSrc = searchSrc.trim();
        this.headerService.sendData(headerParams);
      }else{
        this.searchType = 0;
        headerParams.searchType = this.searchType;
        headerParams.searchSrc = searchSrc;
        this.openFindCompanyData(headerParams);
      }
      this.selectState = 0;
    } else {
      if(this.oldSearchSrc != searchSrc.trim()){
        this.findCompanyData(searchSrc);
      }
      this.oldSearchSrc = searchSrc.trim();
    }

  }

  //点击公司 打开公司展台
  clickCompany(companyInfo){
    this.selectState  = 0;
    if(companyInfo.companyId!=undefined){ //有点击公司 查询数据
      window.open(`#/lfs/company/id/${companyInfo.companyId}`);
    }
  }

  jumpSetting() {
    this.router.navigate(['lfs/user/setting']);
  }

  jumpManage() {
    this.router.navigate(['lfs/user/manage/account']);
  }


  private findCompanyData(keyWord){
    if((keyWord+'').trim() == ''|| keyWord == undefined || keyWord  == null){
      this.findDataList = [];
      this.selectState = 0;
      return ;
    }
    if(this.utils.isSpecialStr((keyWord+'').trim())){
      return;
    }
    this.selectState = 0;
    this.publicApiService.findCompanyData((keyWord+'').trim())
      .subscribe(
        (data: any) => {
          this.selectState = 1;
          if(data.code == 0){
            this.findDataList =  data.data['searchOutObj'].list;
          }else if(data.code == 1){
            this.findDataList = [];
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }


  isShowFindData(type:number){
    this.selectState = type;
  }

  // 监听页面回车
  documentClick(){
    document.onclick=(ev => { //点击回车事件
      let target:any = ev.target;
      if(target ==null){
        this.paramsService.sendHeaderSubject('0');
        this.paramsService.sendFindCompanySubject('0');
        return;
      }
      if(target.offsetParent ==null){
        this.paramsService.sendHeaderSubject('0');
        this.paramsService.sendFindCompanySubject('0');
        return;
      }
      if(target.offsetParent.id ==null){
        this.paramsService.sendHeaderSubject('0');
        this.paramsService.sendFindCompanySubject('0');
        return;
      }
      if(target.offsetParent.id == 'searchMapInput' ){
        this.paramsService.sendFindCompanySubject('1');
      }else{
        this.paramsService.sendFindCompanySubject(0);
      }
      if(target.offsetParent.id == 'headerSearchInput' ){
        this.paramsService.sendHeaderSubject('1');
      }else{
        this.paramsService.sendHeaderSubject('0');
      }
    });
    this.paramsService.getFindCompanySubject().subscribe(resultList => {
      this.isShowFindData(+resultList);
    });
  }

}
