import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';


@Component({
  selector: 'app-time-line',
  templateUrl: './timeLine.component.html',
  styleUrls: ['./timeLine.component.css']
})
export class TimeLineComponent implements OnInit {
  @Output() clickChild = new EventEmitter<TimeClickObj>(); //点击月
  @Output() clickCur = new EventEmitter<TimeClickObj>(); //点击年
  @Output() clickNode = new EventEmitter<TimeClickObj>(); //点击时间
  @Input() startAndEndTime ;
  @Input() defaultT0Data = new Date().getFullYear(); // 默认展开年
  @Input() defaultFlag = true; // 是否默认展开

  @Input() childLength = 12;

  @Output() initApi = new EventEmitter<any>(); //点击时间

  yearList = [];
  timeObj:TimeObj0[] = [];
  monthList = [];
  yLiStyle ={ };

  constructor(

  ) {
    if(this.startAndEndTime ==null){
      this.startAndEndTime=[
        new Date().getFullYear()-7,  //开始年
        new Date().getFullYear()    //结束年
      ]
    }


  }

  ngOnInit() {
    this.initTimeLineValue();
    if(this.defaultFlag){
      this.initDefaultData(this.defaultT0Data,this.timeObj)
    }

    let temp = this;
    this.initApi.emit(temp);
  }

  //初始化时间轴值
  initTimeLineValue(){
    this.yearList = [];
    this.monthList = [];
    this.timeObj = [];

    for (let i = this.startAndEndTime[0];i<=this.startAndEndTime[1];i++){
      this.yearList.push(i);
    }
    for (let i = 1;i<13;i++){
      this.monthList.push(i);
    }
    let yLen = this.yearList.length;
    let mLen = this.childLength;

    let yLiMarginLRNum = 20;
    let mLiMarginLRNum = 5;


    let widthSrc = 'calc('+(100/yLen)+'% - '+yLiMarginLRNum*2+'px)';
    let tS = { width: widthSrc ,margin: '0px '+ yLiMarginLRNum+'px'};
    this.yLiStyle = tS;
    for (let i = 0;i<yLen;i++){
      let t:TimeObj0= new TimeObj0();
      let mS = { width: 'calc('+(100/mLen)+'% - '+mLiMarginLRNum*2+'px)' ,margin: '0px '+ mLiMarginLRNum+'px'};
      t.value = this.yearList[i];
      t.tStyle = tS;
      t.child = [];
      this.timeObj.push(t);
    }
  }

  //初始化默认值
  initDefaultData(defaultValue,obj){
    this.initDefaultYearAndMonth(defaultValue,obj);
  }

  //wkm 时间轴，初始化选中当前日期年 初始月
  initDefaultYear(yValue,obj,mValue?){
    let yLen = obj.length;
    for (let i = 0;i<yLen;i++){
      if(obj[i].value == yValue){
        this.clickY(i,obj[i],obj,1);
        if(mValue != undefined ){
          for (let j = 0;j<obj[i].child.length;j++){
            if(obj[i].child[j].value == mValue){
              this.clickM(mValue-1,obj[i].child[j],obj[i],obj);
              return;
            }
          }
        }else{
          return ;
        }
      }
    }
  }




  //时间轴，初始化选中当前日期年月
  initDefaultYearAndMonth(defaultValue,obj){
    let yLen = obj.length;
    for (let i = 0;i<yLen;i++){
      if(obj[i].value == defaultValue){
        this.clickY(i,obj[i],obj,1);
        for (let j = 0;j<obj[i].child.length;j++){
          if(j ==  new Date().getMonth()){
            this.clickM(new Date().getMonth(),obj[i].child[j],obj[i],obj,undefined,1);
          }
        }
      }
    }
  }


  //点击年
  clickY(key:number,value:TimeObj0,obj:TimeObj0[],isClick?:number){ //isClick == 0 点击事件
    if(value.value == new Date().getFullYear()){
      this.setChildLength(new Date().getMonth()+1);
    }else{
      this.setChildLength(this.monthList.length);
    }


    let yLen = obj.length;
    let mLen = this.childLength;

    let sumNum = obj.length+mLen; //20 单点轴上元素
    let yLiMarginLRNum = 5;
    let widthSrc = 'calc('+(100/sumNum)+'% - '+yLiMarginLRNum*2+'px)';

    let tS = { width: widthSrc ,margin: '0px '+ yLiMarginLRNum+'px'};
    for (let i = 0;i<yLen;i++){
      let mS = { width: 'calc('+(100/mLen)+'% - '+yLiMarginLRNum*2+'px)' ,margin: '0px '+ yLiMarginLRNum+'px'};
      obj[i].tStyle = tS;
      obj[i].tClass = {'active':false};
      obj[i].child = [];
      if((i+'')==(''+key)){
        for (let y = 0;y<mLen;y++){
          let t2:TimeObj0= new TimeObj0();
          t2.value = this.monthList[y];
          t2.tStyle = mS;
          obj[key].child.push(t2);
        }
      }
    }
    let click_num = (1 + mLen)*(100/sumNum); //选中展开比例
    let click_widthSrc = 'calc('+click_num+'% - '+yLiMarginLRNum*2+'px)';
    let clickStyle =  { width: click_widthSrc ,margin: '0px '+ yLiMarginLRNum+'px'};
    obj[key].tStyle = clickStyle;
    obj[key].tClass = {'active':true};

    let timeClickObj = new TimeClickObj();
    timeClickObj.nodeType = 0;
    timeClickObj.index = key;
    timeClickObj.timeObj0 = value;
    timeClickObj.timeObj0List = obj;

    if(isClick == 0){
      this.clickCur.emit(timeClickObj);

      this.clickNode.emit(timeClickObj);
    }
  }

  //点击月
  clickM(key,value,preValue,obj,ev?,isClick?:number){
    if(ev !=undefined){
      ev.stopPropagation();
    }
    let yLen = obj.length;

    for (let i = 0;i<yLen;i++){
      obj[i].tClass = {'active':false};
      for(let preChild of preValue.child){
        if(preChild.value == value.value){
          preChild.tClass =  {'active':true};
        }else{
          preChild.tClass =  {'active':false};
        }
      }
    }
    preValue.tClass = {'active':true};

    let timeClickObj = new TimeClickObj();
    timeClickObj.nodeType = 1;
    timeClickObj.index = key;
    timeClickObj.timeObj0 = value;
    timeClickObj.timeObj0List = obj;
    timeClickObj.preTimeObj0 = preValue;

    if(isClick == 0){
      this.clickChild.emit(timeClickObj);
      this.clickNode.emit(timeClickObj);
    }

  }

  setChildLength(childLength){
    this.childLength = childLength;
  }
}

class TimeObj0{
  value:any;
  tStyle:any;
  child:any;
  tClass:any;
}

class TimeClickObj{
  nodeType:number; //点击节点类型 0外层节点  1子节点
  timeObj0:TimeObj0; //点击节点
  preTimeObj0?:TimeObj0; //月的年节点
  index:number;
  timeObj0List:TimeObj0[];
}
