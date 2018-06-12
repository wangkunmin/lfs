import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompanyNews} from "../../common/model/company-news";

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css'],
})
export class LoadingComponent implements OnInit{
  @Output() initApi = new EventEmitter<any>();

  @Input() loadingHidden; // loading 显隐控制 false隐藏  true显示
  @Input() loadingSize = 20+ 'px'; //加载文字大小
  @Input() loadingHeight = 320+ 'px'; //加载状态高度
  loadingStyle={
    'font-size':this.loadingSize ,
    'line-height':this.loadingHeight,
    'height':this.loadingHeight,
  };

  constructor(){

  }

  ngOnInit(): void {
    this.initLoadingStyle();
    this.initApi.emit(this)
  }

  //设置Loading状态样式
 setLoadingStyle(loadingStyle){
    this.loadingStyle = loadingStyle;
 }
 //设置Loading高度
 setLoadingHeight(loadingHeight:string){
    this.loadingHeight = loadingHeight;
    this.initLoadingStyle();
 }

  //设置Loading文案大小
 setLoadingSize(loadingSize:string){
    this.loadingSize = loadingSize;
    this.initLoadingStyle();
 }

 initLoadingStyle(){
   this.loadingStyle ={
     'font-size':this.loadingSize ,
     'line-height':this.loadingHeight,
     'height':this.loadingHeight,
   };
 }
}



