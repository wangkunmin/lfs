import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../common/service/login.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  getWidth = {
    width:window.screen.width +'px',  //获取显示屏分辨率
  };

  menu: boolean;
  wMenu: boolean;
  flag: boolean;


  constructor(
    private loginService: LoginService) {
    this.menu = false;
    this.wMenu = false;
    this.flag = true;
  }

  ngOnInit() {
    this.loginService.sendLogin({loginFlag:0});
  }

  over() {
    this.wMenu = true;
    setTimeout(() => {
      this.menu = true;
    }, 100)
  }

  out() {
    this.wMenu = false;
    setTimeout(() => {
      this.menu = false;
    }, 100)
  }

  onResize(ev){
    if(window.screen.width >=1280){
      this.getWidth = {
        width : window.screen.width +'px'
      }
    }else{
      this.getWidth = {
        width : 1280 +'px'
      }
    }

  }

  //关注界面右侧回缩问题
  onClickMenu(){
    let mMenu:any = document.getElementsByClassName('m-menu')[0];
    mMenu.style.overflowY = '';
  }
}


