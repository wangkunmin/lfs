import {
  Component, EventEmitter, Input, OnInit, Output, Renderer2, TemplateRef
} from '@angular/core';
import {UserManageInfo} from "../../common/model/user-manage-info";
import {ManageApiService} from "../../common/api/manage-api.service";
import {UserManageIn} from "../../common/model/user-manage-in";
import {BaseApiResponseModel} from "../../common/model/base-api-response.model";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {CommonUtil} from "../../common/utill/common-util";
import {Md5} from "ts-md5";
import {SettingApiService} from "../../common/api/setting-api.service";
import {User} from "../../common/model/user";
import {LoginService} from "../../common/service/login.service";


@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./manage.component.css']
})
export class AccountEditComponent implements OnInit {
  @Input() user: UserManageInfo;
  @Input() title: string;
  @Input() aType: any;
  @Output() type = new EventEmitter<number>();

  accountInit: boolean;
  disFlag: boolean;
  message: string;
  success: string;
  clipText: string;
  userInfo:any; //用户基本信息
  pawIsCanUse = 0;

  constructor(
    private renderer2: Renderer2,
    private api: ManageApiService,
    private ls: LocalStorageService,
    private util: CommonUtil,
    private settingApiService: SettingApiService
  ) {
    this.accountInit = true;
  }

  ngOnInit() {
    if (!this.user.accountNm) {
      this.user.accountNm = '';
    }
    this.disFlag = this.user.accountNm ? true : false;
  }
  //验证用户名存在
  checkUserNm(){
    this.pawIsCanUse = 0;
    let accountNm = this.user.accountNm;
    if(this.util.isSingleEmpty(accountNm.trim())){
      this.message = '用户名不能为空';
      return;
    }
    if(!this.util.rexSrc2(this.user.accountNm)){
      this.message = '用户名必须以4-20位字母、数字';
      return;
    }
    if(this.aType !=1){
      this.settingApiService.findAccountName(accountNm.trim()).subscribe(data=>{
        if(data.code == '0'){
          this.pawIsCanUse =1;
        }else if(data.code == '1'){
          this.pawIsCanUse =0;
          this.message = '用户名已被占用';
          return ;
        }else{
          this.message = '系统错误，请稍后重试！';
          return ;
        }
      })
    }
    this.message = '';
    this.save();

  }

  save() {
    if (this.aType === 0) {
      this.success = '新用户创建成功';
    } else {
      this.success = '密码重置成功'
    }

    /*if (this.util.isSingleEmpty(this.user.accountNm.trim())) {
      this.message = '用户名不能为空';
      return;
    }*/
    let accountName= this.user.accountNm;
    /*if (this.util.getStringBytesLength(accountName)>30) {
      this.message = '用户名不能超过30个字符';
      return;
    }*/
    this.message ='';
      this.user.accountPw = this.util.randomPassword(8);
    let password = this.user.accountPw;
    let userManageIn: UserManageIn = {
      userId: this.ls.get("userInfo").userId,
      accountNm: this.user.accountNm,
      roleId: this.user.roleId,
      accountPw: Md5.hashStr(this.user.accountPw),
      type: this.aType
    };
    this.api.editUserMaintain(userManageIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            this.clipText = `用户名：${this.user.accountNm} 密码：${password}`;
            this.accountInit = false;
            return;
          }
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  clickCopy($event) {
    // if ($event.originalEvent.clipboardData) {
    //   ($event.originalEvent || $event).clipboardData.setData('text/plain', document.getElementById('account').textContent);
    // } else if (window.clipboardData) {
    //   window.clipboardData.getData('Text');
    // }
    // document.execCommand('copy');
    if ($event.isSuccess) {
      this.type.emit(3);
    }
  }

  cancel() {
    this.type.emit(1);
  }

//验证用户名存在
  checkUserNm2(){
    this.pawIsCanUse = 0;
    let accountNm = this.user.accountNm;
    if(this.util.isSingleEmpty(accountNm.trim())){
      this.message = '用户名不能为空';
      return;
    }
    if(!this.util.rexSrc2(this.user.accountNm)){
      this.message = '用户名必须以4-20位字母、数字';
      return;
    }
    this.settingApiService.findAccountName(accountNm.trim()).subscribe(data=>{
      if(data.code == '0'){
        this.pawIsCanUse =1;

      }else if(data.code == '1'){
        this.pawIsCanUse =0;
        this.message = '用户名已被占用';
        return ;
      }else{
        this.message = '系统错误，请稍后重试！';
        return ;
      }
      this.message = '';

    })
  }

}
