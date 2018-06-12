import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { PublicApiService } from '../../common/api/public-api.service';
import { User } from '../../common/model/user';
import { CommonUtil } from '../../common/utill/common-util';
import { Router } from '@angular/router';
import { Md5 } from 'ts-md5';
import {SettingApiService} from "../../common/api/setting-api.service";
import {BsModalService, BsModalRef} from 'ngx-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../public.component.css']
})
export class RegisterComponent implements OnInit, AfterViewInit {
  user: User = new User();
  message: string;

  modalRef: BsModalRef;

  constructor(
    private modalService: BsModalService,
    private router: Router,
    private changeDetectorRef: ChangeDetectorRef,
    private api: PublicApiService,
    private settingApiService: SettingApiService,
    private util: CommonUtil
  ) {
    this.message = '';
  }

  ngOnInit() {
    this.user.accountNm = '';
    this.user.accountPw = '';
    this.user.reAccountPw = '';
  }

  ngAfterViewInit() {
    this.user.accountNm = '';
    this.user.accountPw = '';
    this.user.reAccountPw = '';
    this.changeDetectorRef.markForCheck();
    this.changeDetectorRef.detectChanges();
  }

  //验证用户名存在
  checkUserNm(userNm){
    if(this.util.isSingleEmpty(userNm.trim())){
      this.message = '用户名不能为空';
      return;
    }
    if(this.util.rexSrc2(this.user.accountNm)){
      this.message = '';
    }else{
      this.message = '用户名必须以4-20位字母、数字';
      return;
    }
    this.settingApiService.findAccountName(userNm.trim()).subscribe(data=>{
      if(data.code == '0'){
        // let nameRex =  /^[a-zA-Z][a-zA-Z0-9_]{3,19}?$/;
        /*if (!nameRex.test(this.user.accountNm)) {
          this.message = '用户名必须以4-20位字母、数字或“_”';
          return;
        } else {*/

        // }
      }else if(data.code == '1'){
        this.message = '用户名已被占用';
        return;
      }else{
        this.message = '系统错误，请稍后重试！';
        return;
      }
      this.message = '';
    })
  }

  checkPassword(password) {
    if (this.util.isSingleEmpty(password.trim())) {
      this.message = '密码不能为空';
      return;
    }
    let rex = /^[a-zA-Z][a-zA-Z0-9_]{7,15}?$/;
    if (!rex.test(password)) {
      this.message = '密码必须以8-16位字母、数字或“_”，字母开头';
      return;
    }else{
      if(this.util.rexSrc(password,8,16)){
        this.message = '';
      }else{
        this.message = '密码必须以8-16位字母、数字或“_”，字母开头';
        return;
      }
    }
    this.message = '';
  }

  checkRePassword(password) {
    if (this.util.isSingleEmpty(password.trim())) {
      this.message = '确认密码不能为空';
      return;
    }
    if (this.user.accountPw !== password) {
      this.message = '确认密码有误';
      return;
    }
    this.message = '';
  }

  register(templeEle?) {
    if(this.util.isSingleEmpty(this.user.accountNm.trim())){
      this.message = '用户名不能为空';
      return;
    }
    if (this.util.isSingleEmpty(this.user.accountPw.trim())) {
      this.message = '密码不能为空';
      return;
    }
    if (this.util.isSingleEmpty(this.user.reAccountPw.trim())) {
      this.message = '确认密码不能为空';
      return;
    }

    this.message = '';
    if(this.util.rexSrc(this.user.accountNm,4,20,true)){
      this.message = '';
    }else{
      this.message = '用户名必须以4-20位字母、数字';
      return;
    }
    if(this.util.rexSrc(this.user.accountPw,8,16)){
      this.message = '';
    }else{
      this.message = '密码必须以8-16位字母、数字或“_”，字母开头';
      return;
    }
    if (this.user.accountPw !== this.user.reAccountPw) {
      this.message = '确认密码有误';
      return;
    }
    this.api.register(this.user)
      .subscribe(data => {
        if (data.code === '0') {
          this.modalRef = this.modalService.show(templeEle, {ignoreBackdropClick: true});
        } else if (data.code === '1') {
          this.message = data.message;
        } else {
          this.message = '系统错误，未注册成功！';
        }
      });
  }

  offModal(){
    this.modalRef.hide();
    this.router.navigate(['home/login']);
  }
}
