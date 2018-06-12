import {AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../common/service/login.service';
import {Observable} from "rxjs/Rx";
import {withIdentifier} from "codelyzer/util/astQuery";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  @ViewChild('imgList') imgList: ElementRef;
  @ViewChild('imgBody') imgBody: ElementRef;
  @ViewChild('img') img: ElementRef;

  uSwipe: object;         // 整个滑块
  uSwipeContent: object;  // 右侧滑块
  uSwipeFlag: boolean;    // 控制滑块滑动
  uSLFlag: boolean;       // 是否显示左侧滑块
  signFlag: boolean;      // 显示登录或注册

  constructor(
    private renderer2: Renderer2,
    private router: Router,
    private loginService: LoginService) {
    this.uSwipeFlag = this.uSLFlag = this.signFlag = true;
  }

  ngOnInit() {
    this.loginService.sendLogin({loginFlag:2});
  }

  ngAfterViewInit() {
    document.body.style.paddingBottom = '0';
    this.setImgWH();
  }

  setImgWH() {
    let width = this.imgList.nativeElement.clientWidth;
    let height = this.imgList.nativeElement.clientHeight - 68;
    let imgWidth = this.img.nativeElement.naturalWidth;
    let imgHeight = this.img.nativeElement.naturalHeight;
    this.resetImgSizeWH(width, height, imgWidth, imgHeight);
    // let padding = (height / width) * 100;
    // this.renderer2.setStyle(this.imgBody.nativeElement, 'padding-bottom', `${padding}%`);
    // if (imgWidth > imgHeight) {
    //   this.renderer2.addClass(this.img.nativeElement, 'm-img-h');
    // } else if (imgHeight > imgWidth) {
    //   this.renderer2.addClass(this.img.nativeElement, 'm-img-w');
    // } else {
    //   this.renderer2.addClass(this.img.nativeElement, 'm-img');
    // }
  }

  resetImgSizeWH(width, height, iWidth, iHeight) {
    let iWH = iWidth / iHeight;
    let wH = width / height;
    if (iWH > wH) {
      this.renderer2.setStyle(this.img.nativeElement, 'height', `${height}px`);
      let imgWidth = iWH*height;
      this.renderer2.setStyle(this.img.nativeElement, 'width', `${parseInt(imgWidth + '')}px`);
      let left = parseInt((imgWidth - width) / 2 + '') * -1;
      // this.renderer2.setStyle(this.img.nativeElement, 'margin-left', `${left}px`);
    } else if (iWH < wH) {
      this.renderer2.setStyle(this.img.nativeElement, 'width', `${width}px`);
      let imgHeight = 1 / iWH * width;
      this.renderer2.setStyle(this.img.nativeElement, 'height', `${parseInt(imgHeight + '')}px`);
      let top = parseInt((imgHeight - height) / 2 + '') * -1;
      this.renderer2.setStyle(this.img.nativeElement, 'margin-top', `${top}px`)
    } else {
      this.renderer2.setStyle(this.img.nativeElement, 'height', `${height}px`);
      this.renderer2.setStyle(this.img.nativeElement, 'width', `${width}px`);
    }
  }

  // 是否显示登录注册画面
  showSign() {
    this.router.navigate(['home/login']);
    this.uSLFlag = this.uSwipeFlag ? false : true;
    this.changeCss(this.uSLFlag);
  }

  // 登录注册动画效果
  changeCss(flag: boolean) {
    this.uSwipeFlag = !flag;
    this.uSwipe = {
      'uSwipeLeft': !flag,
      'uSwipeRight': flag
    };
    this.uSwipeContent = {
      'uSConLeft': !flag,
    }
  }
}
