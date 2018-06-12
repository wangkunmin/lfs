import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {User} from "../../common/model/user";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {SettingApiService} from "../../common/api/setting-api.service";
import {Subject} from "rxjs/Subject";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {LoginService} from "../../common/service/login.service";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./setting.component.css']
})
export class UserInfoComponent implements OnInit {
  @ViewChild('messageModel') messageModel;

  messageObj = {
    ele:``,
    message:''
  };//消息模态框

  @Input() selectInfoPaw; //资料 管理

  @Output() infoOut = new EventEmitter<any>(); //用户基本信息

  oldUserNm=''; // 初始化加载时用户名

  userNm=''; // 修改的用户名
  message = '';
  isCanUse = 0; //是否能使用 0 初始化状态  1 能成功使用  2 不能使用


  paw = ''; //原密码
  paw0 = ''; //重置密码
  paw1 = ''; //确认重置密码


  // eye  1 显示密码   0 隐藏密码      isCanUse: 是否能使用 0 初始化状态  1 能成功使用  2 不能使用
  //原密码校验
  pawMessage = '';
  pawIsCanUse = 0;
  eye = 1;
  //新密码验证
  pawMessage_0 = '';
  pawIsCanUse_0 = 0;
  eye_0 = 1;
  //确认密码
  pawMessage_1 = '';
  pawIsCanUse_1 = 0;
  eye_1 = 1;

  userId; //用户Id
  userInfo:any; //用户基本信息

  constructor(
    private lg:LocalStorageService,
    private settingApiService:SettingApiService,
    private modalService: BsModalService,
    private util: CommonUtil,
    private loginService: LoginService
  ) {
    this.userInfo = new User();
  }

  ngOnInit() {
    this.userId = this.lg.get('userInfo').userId;
    this.oldUserNm = this.lg.get('userInfo').userNm;
    this.getUserInfo(this.userId);
  }


  //个人信息 密码设置
  setInfoPaw(type:number){
    this.selectInfoPaw =type;
    this.message = '';
  }


  //保存用户信息
  saveInfo(userNm){
    if(userNm.trim() == ''){
      this.isCanUse = 2;
      this.message = '显示名不能为空!';
      return ;
    }
    if(this.isCanUse != 1){
      return;
    }
    let body:any = {
      userId:this.userId,
      userNm: userNm
    };
    this.settingApiService.updateUserInfo(body).subscribe(data=>{
      if(data.code == '0'){
        this.message = '信息更新成功';
        this.openMessage({
          ele:`<div class="fa fa-check fa-4x message-check-class "></div>`,
          message:'信息重置完成！'
        });
        this.setTimeHideMessage(5000);
        this.getUserInfo(this.userId,0);
      }else{
        this.message = '信息更新失败';
        this.openMessage({
          ele:`<div class="message-error-class"></div>`,
          message:'信息重置失败！'
        });
        this.setTimeHideMessage(5000);
      }
    })
  }


  //重置密码
  updatePaw(paw,paw0,paw1){

    if(this.paw.trim() == ''){
      this.pawMessage = '原密码不能为空！';
      this.pawIsCanUse = 2;
      return ;
    }
    if(this.paw0.trim() == ''){
      this.pawMessage_0 = '新密码不能为空';
      this.pawIsCanUse_0 = 2;
      return ;
    }
    if(this.paw1.trim() == ''){
      this.pawMessage_1 = '确认密码不能为空';
      this.pawIsCanUse_1 = 2;
      return ;
    }
    if(paw0 !=paw1 ){
      this.pawMessage_1 = '确认密码有误';
      this.pawIsCanUse_1 = 2;
      return;
    }
    this.surePaw(paw,this.userInfo,paw0);
  }

  //获取用户基本信息
  private getUserInfo(userId,flag?){
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
        this.lg.set('userInfo',data.data.userInfo);
        if(flag == 0){
          this.loginService.sendLogin({ loginFlag: 0 });
        }
        outInfo.userInfo = this.userInfo;
        outInfo.userInfo.count = data.count;
      }else{
        outInfo.type = -1;
      }
      this.infoOut.emit(outInfo);
    })
  }



  //新密码校验
  keyUpFun1(ev,paw0){
    if((paw0+'').trim() == ''){ //不输入关键字 //
      return ;
    }
    let re = /^\w{8,16}$/;
    if(re.test(paw0)){  //true 则符合规则
      // let re1 = /[a-zA-Z]/;
      // let tempSrc = (paw0.trim()).substr(0,1);
      // if(re1.test(tempSrc)){
      if (this.util.rexSrc(paw0,8,16)) {
        this.pawIsCanUse_0 = 1;
      }else{
        this.pawMessage_0 = '首字符不为字母！';
        this.pawIsCanUse_0 = 2;
      }
    }else{
      this.pawMessage_0 = '密码不适！';
      this.pawIsCanUse_0 = 2;
    }
  }

  keyUpFun0(ev,paw){
    this.surePaw(paw,this.userInfo);
  }

  //确认密码校验
  keyUpFun2(ev,paw1){
    if(this.paw0 == this.paw1){
      this.pawIsCanUse_1 = 1;
    }else{
      this.pawMessage_1 = '确认密码有误';
      this.pawIsCanUse_1 = 2;
    }
  }
  //显示名验证
  keyUpFun(ev,userNm){
    if((userNm+'').trim() == ''){ //不输入关键字 //
      this.isCanUse = 0;
      return ;
    }

    if(userNm.length>15){
      this.isCanUse = 2;
      this.message = '不能超过15个字！';
      // let len = 20- (this.util.getStringBytesLength(userNm) - userNm.length);
      this.userNm = userNm.substr(0,20);
      return ;
    }
    let rex = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
    if(!rex.test(this.userNm)){
      this.isCanUse = 2;
      this.message = '不能包含特殊字符！';
      return ;
    }

      this.checkUserNm(userNm);


  }
  //验证用户名存在
  checkUserNm(userNm){
    if(userNm.trim() == ''){
      return;
    }
    let userNmIn = (userNm+'').replace(/ /g,'%20');
    this.settingApiService.findUserName(userNmIn).subscribe(data=>{
      if(data.code == '0'){
        this.message = '';
        this.isCanUse = 1;
      }else if(data.code == '1'){
        this.message = '显示名已存在，请修改！';
        this.isCanUse = 2;
      }else{
        this.message = '系统错误，请稍后重试！';
        this.isCanUse = 2;
      }
    })
  }


  // 修改密码
  private upDatePaw(paw0,loginInfo) {
    let re = /^\w{8,16}$/;
    if(re.test(paw0)){  //true 则符合规则
      // let re1 = /[a-zA-Z]/;
      // let tempSrc = (paw0.trim()).substr(0,1);
      // if(re1.test(tempSrc)){
      if (this.util.rexSrc(paw0,8,16)) {
        this.pawIsCanUse_0 = 1;
      }else{
        this.pawMessage_0 = '首字符不为字母！';
        this.pawIsCanUse_0 = 2;
        return
      }
    }else{
      this.pawMessage_0 = '密码不适！';
      this.pawIsCanUse_0 = 2;
      return
    }

    let inPaw = {
      accountNm:loginInfo.accountNm,
      accountPw :(''+paw0), //修改密码
      userId:this.userId,
    };
    this.settingApiService.updatePassword(inPaw).subscribe(data=>{
      if(data.code == '0'){
        this.message = '密码修改成功';
        this.openMessage({
          ele:`<div class="fa fa-check fa-4x message-check-class "></div>`,
          message:'密码重置完成！'
        });
        this.setTimeHideMessage(5000);
      }else{
        this.message = '密码修改失败';
        this.openMessage({
          ele:`<div class="message-error-class"></div>`,
          message:'密码修改失败！'
        });
        this.setTimeHideMessage(5000);
      }
    });
  }

  /* 验证密码正误*/ //paw 原密码 userInfo 用户信息
  private surePaw(paw,userInfo,paw0?){
    let loginInfo = Object.create(userInfo);
    loginInfo.accountPw = (""+paw); //登录密码

    let bodyIn:any = {
      accountNm:loginInfo.accountNm,
      accountPw :loginInfo.accountPw,
    };
    //用户密码加密
    this.settingApiService.login(bodyIn).subscribe(data=>{
      if(data.code == "0"){//登录成功
        this.pawIsCanUse = 1;
        if(paw0 != undefined){
          this.upDatePaw(paw0,loginInfo);
        }
        return ;
      }else if(data.code == "1"){

      }else if(data.code == "-1"){

      }
      this.pawMessage = '密码错误！';
      this.pawIsCanUse = 2;
    },error=>{

    });

  }


  modalRef: BsModalRef;
  //open message模态框
  openMessage(messageObj){
    this.messageObj = messageObj;
    this.modalRef = this.modalService.show(this.messageModel, {class: 'modal-lg', ignoreBackdropClick: true});
  }
  //关闭模态框
  setTimeHideMessage(time?){
    if(time == undefined){
      this.modalRef.hide();
    }else{
      let subject = new Subject<number>(); //创建观察者
      let tempTime = setTimeout(function(){
        subject.next(0)  //发送读取文件结束后内容
      },time);
      subject.asObservable().subscribe( resultList=> {
        this.modalRef.hide();
      });
    }
    this.delSrc();
  }

  delSrc(){
    this.paw = '';
    this.paw0 = '';
    this.paw1 = '';
    this.userNm = '';
    this.isCanUse = 0;

    this.pawMessage = '';
    this.pawIsCanUse = 0;
    this.eye = 1;
    this.pawMessage_0 = '';
    this.pawIsCanUse_0 = 0;
    this.eye_0 = 1;
    this.pawMessage_1 = '';
    this.pawIsCanUse_1 = 0;
    this.eye_1 = 1;
  }

}
