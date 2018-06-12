import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfo } from '../../common/model/entity';

import { LoginService } from '../../common/service/login.service';
import { PublicApiService } from '../../common/api/public-api.service';
import { LocalStorageService } from '../../common/service/localStorage.service';
import {Md5} from "ts-md5";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../public.component.css']
})
export class LoginComponent implements OnInit{
  showPaw=false; //密码显隐控制
  savePassword: boolean;
  userInfo: UserInfo = new UserInfo(); //用户信息
  message:any='';//登录返回消息

  constructor(
    private router: Router,
    private loginService: LoginService,
    private publicApiService:PublicApiService,
    private lg:LocalStorageService) {
    let remember = this.lg.get('remember');
    if (remember) {
      this.userInfo = this.lg.get('remember');
      this.savePassword = true;
    } else {
      this.savePassword = false;
    }
  }

  ngOnInit(): void {
    // this.loginService.sendLogin({loginFlag:2});
  }

  save() {
    this.savePassword = !this.savePassword;
  }

  // 登录
  login(info?: UserInfo) {
    let loginInfo = Object.create(this.userInfo);

    if(undefined == this.userInfo.accountName || null == this.userInfo.accountName){
      this.userInfo.accountName = '';
    }
    if(undefined == this.userInfo.password || null == this.userInfo.password){
      this.userInfo.password = '';
    }
    loginInfo.accountName = (this.userInfo.accountName+'');

    if(loginInfo.accountName== ''|| undefined == this.userInfo.accountName ){
      this.message = "用户名不能为空";
      return ;
    }
    if(loginInfo.password == ''|| undefined == this.userInfo.accountName ){
      this.message = "密码不能为空";
      return ;
    }
    loginInfo.accountNm = loginInfo.accountName; // 登录名
    loginInfo.accountPw = loginInfo.password; //登录密码
    //用户密码加密
    this.publicApiService.login(loginInfo).subscribe(data=>{
      this.lg.set("userInfo", null);
      if(data.code == "0"){//登录成功
        if (this.savePassword) {
          let u: UserInfo = new UserInfo();
          u.accountName = loginInfo.accountNm;
          u.password = loginInfo.accountPw;
          this.lg.set('remember', u);
        } else {
          this.lg.set("remember", null);
        }
        this.lg.set("userInfo",data.data["content"]);//设置用户登录成功后返回信息
        this.lg.set("token",data.data["content"].token); //设置token信息
        this.router.navigate(['lfs/region']);

      }else if(data.code == "1"){
        this.message = data.message;
      }else if(data.code == "-1"){
        this.message = data.message;
      }
    },error=>{

    });

  }
}



