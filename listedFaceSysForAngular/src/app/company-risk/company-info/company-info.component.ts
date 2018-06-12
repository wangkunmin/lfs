import {Component, OnInit, ViewChild, Input, Renderer2} from '@angular/core';
import {TabsetComponent} from "ngx-bootstrap";
import {CompanyRiskApiService} from "../../common/api/company-risk-api.service";
import {CompanyRiskInfo} from "../../common/model/company-risk-info";
import {ControllerInfo} from "../../common/model/controller-info";
import {BaseData} from "../../common/api/base-data";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {UserApiService} from "../../common/api/user-api.service";
import {BaseApiResponseModel} from "../../common/model/base-api-response.model";
import {UserAttentionIn} from "../../common/model/user-attention-in";

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']
})
export class CompanyInfoComponent implements OnInit {
  selectType = 1;
  companyInfoList = [
    { type:1, value:'基本信息', activeFlag:false},
    { type:2, value:'管理层', activeFlag:false},
  ];

  followedCompanyFlag = false;
  @Input() companyInfo: CompanyRiskInfo;
  @ViewChild("infoTabSet")infoTabSet:TabsetComponent;
  canvasObj = {
    width : "",
    height : "",
  };
  userId:string='';
  @Input() companyId;
  controllerInfos:ControllerInfo[] = [];
  baseData ; //国家code代码基础数据
  constructor(
    private render2: Renderer2,
    private ls:LocalStorageService,
    private userApiService: UserApiService,
    private companyRiskApiService: CompanyRiskApiService
  ) {
    this.baseData = new BaseData().countryCode;
  }

  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.initController();
    this.initFollowed();

  }

  // 添加关注
  addFocus() {
    let userAttentionIn: UserAttentionIn = {
      userId: Number(this.userId),
      companyId: Number(this.companyId),
      companyNm: this.companyInfo.companyNm,
      focusType: 1
    };
    this.userApiService.addAttention(userAttentionIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            this.followedCompanyFlag = true;
            return;
          }
          this.followedCompanyFlag = false;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  // 取消关注
  deleteFocus() {
    let userAttentionIn:UserAttentionIn = {
      userId: Number(this.userId),
      companyId: Number(this.companyId),
      companyNm: this.companyInfo.companyNm,
      focusType: 1
    };
    this.userApiService.deleteAttention(userAttentionIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            this.followedCompanyFlag = false;
            return;
          }
          this.followedCompanyFlag = true;
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }

  //查询用户是否已关注某公司接口
  initFollowed(){
    let userId = this.userId;
    let companyId = this.companyId;
    this.companyRiskApiService.findFollowedCompyInfoById(userId,companyId)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.followedCompanyFlag = data.data['content'];
            return;
          }
        },
        error => {
          console.log(error);
        }
      );
  }
  //初始化管理层信息
  initController(){
    let companyId = this.companyId;
    this.companyRiskApiService.getControllerInfo(companyId)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.controllerInfos = data.data['content'];
            for(let data of this.controllerInfos){
              this.baseData.forEach((value => {
                if(value.PARAMCODE == data.countryCd){
                  data.countryCdNM = value.PARAMCHNAME
                }
              }))
            }
            return;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

  //公司网址跳转
  jumpToCompanyWeb(url){
    window.open("http://"+url);
  }

  getSelectType(type){
    this.selectType = type;
  }

  //打开公司图谱
   openCompanyChart(){
    if(this.companyId){ //有点击公司 查询数据
      window.open(`#/Atlas/chart/${this.companyId}/1`)
    }
  }
}
