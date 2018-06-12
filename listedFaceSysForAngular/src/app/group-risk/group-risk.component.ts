import { Component, OnInit, AfterContentChecked } from '@angular/core';

import { UserFollowIn } from '../common/model/user-follow-in';
import { UserFollow } from '../common/model/user-follow';
import { LocalStorageService } from '../common/service/localStorage.service';
import { GroupRiskApiService } from '../common/api/group-risk-api.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-group-risk',
  templateUrl: './group-risk.component.html',
  styleUrls: ['./group-risk.component.css']
})
export class GroupRiskComponent implements OnInit, AfterContentChecked {
  flag: any;
  getCss: any;
  userId: number;
  userFollow: UserFollow[];

  constructor(
    private ls: LocalStorageService,
    private router: Router,
    private groupRiskApiService: GroupRiskApiService,
    private activatedRoute: ActivatedRoute,
  ) {
    this.userId = this.ls.get('userInfo').userId;
    this.flag = {
      followFlag: true,
      rightFlag: false
    };
  }

  ngOnInit() {
    document.getElementById('followContent').style.width = '100%';
    // Observable.fromEvent(window, 'resize')
    //   .subscribe((event) => {
    //     this.setCss();
    //   });
    this.findFollowedCompany();
  }

  ngAfterContentChecked() {
    this.setCss();
  }

  // 展示关注公司列表
  showFollow() {
    // this.setTrans();
    this.flag.followFlag = false;
    let mMenu:any = document.getElementsByClassName('m-menu')[0];
    //右侧定位设置
    // let uNavGroup:any = document.getElementsByClassName('u-nav-group')[0];
    // uNavGroup.style.top = 80+ mMenu.scrollTop + 'px';
    mMenu.style.overflowY = 'hidden';
    this.findFollowedCompany();
    this.router.navigate(['lfs/group'],{
      fragment:'followContent'
    });
  }

  // 查询用户已关注的公司列表
  findFollowedCompany() {
    let userFollowIn: UserFollowIn = {
      userId: this.userId
    };
    this.groupRiskApiService.findFollowedCompany(userFollowIn)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.userFollow = data.data['content'];
            return;
          }
          this.userFollow = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  //关闭添加公司模态框
  offRiskMenu(ev){
    if(ev == 0){ //确认

    }else if(ev == 1){// 取消

    }
    this.findFollowedCompany();
    this.router.navigate(['lfs/group'],{
      fragment:'followContent'
    });
  }

  // 设置css效果
  setCss( ) {
    const groupClass = document.getElementsByClassName('container');
    this.getCss = {
      cWidth: groupClass[0].clientWidth,
      cHeight: groupClass[0].clientHeight,
      oWidth: document.documentElement.offsetWidth || document.body.offsetWidth,
      oHeight: document.documentElement.offsetHeight || document.body.offsetHeight
    };
    if (this.getCss.cHeight > this.getCss.oHeight + 50) {
      this.flag.rightFlag = true;
    }
    // this.getCss.width = 345 - (this.getCss.oWidth - this.getCss.cWidth)/2;
    // if (!this.flag.followFlag) {
    //   const followContent = document.getElementById('followContent');
    //   followContent.style.width = `${this.getCss.cWidth - this.getCss.width}px`;
    // }
  }

  // 显示动画效果
  // setTrans() {
  //   if (this.getCss.width > 0) {
  //     const followContent = document.getElementById('followContent');
  //     followContent.style.width = `${this.getCss.cWidth - this.getCss.width}px`;
  //     let style = document.createElement('style');
  //     style.type = 'text/css';
  //     let keyFrames = `@keyframes uTransLeft {
  //       0%   {left: ${this.getCss.width}px;}
  //       100% {left: 0;}
  //     }`;
  //     style.innerHTML = keyFrames;
  //     followContent.appendChild(style);
  //   }
  // }

  // 关闭右边栏效果
  // setRightTrans($event) {
  //   if ($event === 1 && this.getCss.width > 0) {
  //     this.flag.followFlag = true;
  //     const followContent = document.getElementById('followContent');
  //     followContent.style.width = `${this.getCss.cWidth}px`;
  //     let style = document.createElement('style');
  //     style.type = 'text/css';
  //     let keyFrames = `@keyframes uTransLeft {
  //       0%   {left: 0;}
  //       100% {left: ${this.getCss.width}px;}
  //     }`;
  //     style.innerHTML = keyFrames;
  //     followContent.appendChild(style);
  //   } else if ($event === 0) {
  //     this.findFollowedCompany();
  //   }
  // }
}
