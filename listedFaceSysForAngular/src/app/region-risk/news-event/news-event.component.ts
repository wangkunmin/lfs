import { Component, OnInit,ViewChild } from '@angular/core';
import { NgxEchartsService } from 'ngx-echarts';
import { CommonUtil } from '../../common/utill/common-util';
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import {LocalStorageService} from "../../common/service/localStorage.service";

@Component({
  selector: 'app-news-event',
  templateUrl: './news-event.component.html',
  styleUrls: ['./news-event.component.css']
})
export class NewsEventComponent implements OnInit {
  timeObj = '';//右边数据集key值

  startAndEndTime = [
    new Date().getFullYear()-7>2016? new Date().getFullYear()-7:2016,  //开始年
    new Date().getFullYear()    //结束年
  ];

  startAndEndLineTime:any = [
    new Date().getFullYear()-7>2016? new Date().getFullYear()-7:2016,  //开始年
    new Date().getFullYear()    //结束年
  ];

  userId; //用户Id
  options:any = {};
  getTimeNewsData:any={ //{postDt:string, newCount:number,negativeNewsCount:number,ratio:string }  右边对象
    postDt:this.commonUtil.dateFormat(new Date(),'yyyy-MM-dd'),
    newCount:0,  //新闻总数
    negativeNewsCount:0, //负面新闻
    ratio:'0' //负面/总新闻占比
  };

  //echar实例
  echartsIntance :any ={};

  //eChart图数据值
  dataMap:any = {
    2018:{
      newCountList:[],
      negativeNewsCountList:[],
      ratioList:[],
    }
  };
  timeList =[];

  timeListApi;
  getTimeInit(ev){
    this.timeListApi = ev;
  }

  constructor(
    private ls:LocalStorageService,
    private regionRiskApiService : RegionRiskApiService,
    private es: NgxEchartsService,private commonUtil:CommonUtil) { }

    //初始化时间x时间轴
    initTimeList(){
      let d = new Date();
      let days = new Date(d.getFullYear(),d.getMonth()+1,0);
      this.timeList = [];
      for(let i=1;i<= days.getDate();i++){
        this.timeList.push(i<10?('0'+i):i+'');
      }
    }
  ngOnInit() {
    this.userId = this.ls.get("userInfo").userId;
    this.initTimeList();
    this.showChart();

  }

  onChartInit(ec) {
    this.echartsIntance = ec;
  }


  rshowdate = '';//记录点击时间
    //获取当天数据
    showDateData(time){
      let datedata: any={};
      let newsCount =0;
      let negativeNewsCount =0;
      let ratio =0;
      let retratio ='';
      let rshowdate='';
      //点击年的处理
      if ((time+'').length ==4){
        rshowdate = time;
        datedata= this.dataMap[time];
        for(let i=0;i< datedata.newCountList.length;i++){

          newsCount +=  datedata.newCountList[i];
          negativeNewsCount +=  datedata.negativeNewsCountList[i];
        }
        if (newsCount == 0){
          ratio = 0;
        }else{
          ratio = negativeNewsCount/newsCount;
        }
        retratio = Math.round(ratio * 10000)/100 +'';

      }
      //点击月的处理
      if ((time+'').length ==6){
        let tmp = time.substr(0,4);
        datedata= this.dataMap[tmp];
        for(let i=0;i< datedata.newCountList.length;i++){
          if (time == datedata.countDateList[i].replace(/-/g,'')){
            rshowdate=datedata.countDateList[i];
            newsCount =  datedata.newCountList[i];
            negativeNewsCount =  datedata.negativeNewsCountList[i];
            ratio =  datedata.ratioList[i];
            retratio = ratio+'';
            retratio = retratio.replace(/%/g,'');
            break;
          }

        }


      }
      //点击日的处理
      if ((time+'').length ==8){
        let tmp = time.substr(0,6);
        datedata= this.dataMap[tmp];
        for(let i=0;i< datedata.newCountList.length;i++){
          if (time == datedata.countDateList[i].replace(/-/g,'')){
            rshowdate=datedata.countDateList[i];
            newsCount =  datedata.newCountList[i];
            negativeNewsCount =  datedata.negativeNewsCountList[i];
            ratio =  datedata.ratioList[i];
            retratio = ratio+'';
            retratio = retratio.replace(/%/g,'');
            break;
          }

        }

      }
    //retratio = Math.round(ratio * 10000)/100 +'';
    this.getTimeNewsData = {
      postDt:this.commonUtil.timeToTimeSrc(time),
      newCount:newsCount,  //新闻总数
      negativeNewsCount:negativeNewsCount, //负面新闻
      ratio:retratio //负面/总新闻占比
    };

    this.rshowdate = rshowdate;
    return '截止时间：'+rshowdate;
  }


  //加载数据展开图
  showChart(){
    let yS = this.startAndEndTime[0];
    let yE = this.startAndEndTime[1];
    let mE = new Date().getMonth()+1;  //时间轴初始化当前月
    let dE = new Date().getDate();
    let startTime = yS+'-01-01';
    let endTime =yE+'-'+(mE<10?'0'+mE:mE)+'-'+(dE<10?'0'+dE:dE);
    let initTime = yE+''+(mE<10?'0'+mE:mE);
    this.timeObj = yE+'';
    let body = {
      startDate:startTime,
      endDate:endTime,
    };
    this.regionRiskApiService.getNewsCharts(body).subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.dataMap = this.getTrueData(geoJson.data);

        let dateYm = new Date();
        let y = dateYm.getFullYear();
        let m = dateYm.getMonth()+1;  //时间轴初始化当前月
        //根据实际数据的情况设定时间轴的时间间隔
        let flag = false;
        this.commonUtil.each(this.dataMap,(key, value) => {
          if(!flag) {
            this.startAndEndLineTime[0] =key;
            let temp = new Object(this.startAndEndLineTime);
            this.startAndEndLineTime = temp;
            flag = true;
            this.timeListApi.initTimeLineValue();
            this.timeListApi.initDefaultYear(y,this.timeListApi.timeObj,m);
          }
        });
        this.timeObj = y+''+(m<10?'0'+m:m);
        this.showDateData(y+''+(m<10?'0'+m:m));
        this.getChartsData(this.getTimeData(y+''+(m<10?'0'+m:m),this.dataMap));
      }else if(geoJson.code == '1'){
        this.getTimeNewsData = {
          postDt:this.commonUtil.dateFormat(new Date(),'yyyy-MM-dd'),
          newCount:0,  //新闻总数
          negativeNewsCount:0, //负面新闻
          ratio:'0' //负面/总新闻占比
        };
      }else if(geoJson.code == '-1'){

      }
    });

  }

  //点击EChart图
  clickChart(e){
    let time = this.timeObj + (+e.name<10?'0'+e.name:e.name);
    //显示右上时间
    this.echartsIntance.setOption({
      baseOption:{
        title:{
          subtext:this.showDateData(time)
        }
      }});

  }

  //获取图形数据值
  getChartsData(dataMap){
    if(dataMap == undefined){
      dataMap = {};
    }
    let date = new Date();
    let m = date.getMonth()+1;  //时间轴初始化当前月
    let subtextSrc = '截止时间：'+date.getFullYear()+ '-'+(m<10?'0'+m:m);
    if(dataMap.subtextSrc != undefined){
      subtextSrc = dataMap.subtextSrc;
    }
    let rshowdate  = '';
    if(this.rshowdate == ''){
      rshowdate = date.getFullYear()+ '-'+(m<10?'0'+m:m);
    }else{
      rshowdate  = this.rshowdate ;
    }
    this.options = {
      baseOption: {
        color:['#4CAF50','#E64A19','#38B4EE'], //颜色设置
        icon:'circle',//圆形
        title: {
          show:true,
          subtext:subtextSrc,
          right:73,
          top:15
        },
        tooltip: {
          trigger: 'item',
          position:'top',
          backgroundColor: 'rgba(72,84,101,0.9)',
          borderWidth:0,
          padding:0,
          enterable:true,
          formatter: function (params) {
            let dateValue = params[0].name;
            dateValue = dateValue<10?'0'+dateValue:dateValue;
            let ret = `<div class="col-lg-12 padding-left-right_0 padding-5"> <div class="col-lg-12 padding-5 padding-left_30"> ${rshowdate +'-'+ dateValue }  </div><div class="m-line col-lg-12 padding-left-right_0"></div></div>`;
            for(let t of params){
              /*  ret+=t.marker+' '+ t.seriesName+' : '+ (t.value==null?0:t.value==''?0:t.value)+'</br>';*/
              ret += '<div class="col-lg-12 padding-left-right_0 padding-5"><span class="col-lg-6 padding-left_30">' + t.seriesName+ ' </span><span class="col-lg-6 align-center">' + (t.value==null?0:t.value==''?0:t.value) + '</span></div>';
            }
             ret = `<div class="news-event-tooltip"> <div class="">${ret}</div></div>`;
            return ret;
          }
        },
        legend: {
          top:5,
          left:55,
          data: ['新闻总数', '负面新闻'],
        },
        calculable : true,
        grid: {
          top: 80,
          bottom: 100,
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow',
              label: {
                show: true,
              }
            }
          }
        },
        xAxis: [
          {
            type:'category',
            axisLabel:{'interval':0},
            boundaryGap : true,//从最左侧开始
            axisTick:false,//显示刻度轴
            axisLine:{
              lineStyle:{
                color:'#ccc', //轴线颜色
              }
            },
            data:dataMap.timeList,
            splitLine: {show: false},
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'line',        // 默认为直线，可选为：'line' | 'shadow'
              label: {
                show: true,

              }
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            // name: '市场风险（亿元）',
            splitArea: {//块颜色分割
              show: false
            },
            axisTick:false,//显示刻度轴
            axisLine: {
              show: false
            }
          }
        ],
        series: [
          {
            name: '新闻总数',
            type: 'line',
            data:dataMap.newCountList,
          },
          {
            name: '负面新闻',
            type: 'line',
            data:dataMap.negativeNewsCountList,
          },
          {
            name: '负面/总新闻占比',
            type: 'line',
            data:dataMap.ratioList,
          },
        ]
      },
    };
  }

  //获取时间轴点击事件
  getClickTimeLine(e?){
    let timeSrc = '';
    let y ;
    if(e.nodeType == 0){
      timeSrc = e.timeObj0.value;
      y = e.timeObj0.value;
    }else if(e.nodeType == 1){
      timeSrc = e.preTimeObj0.value;
      y = e.preTimeObj0.value;
      timeSrc = timeSrc+''+(e.timeObj0.value<10?'0'+e.timeObj0.value:e.timeObj0.value);
    }
    this.timeObj = timeSrc;

    let oneDataMap = this.getTimeData(timeSrc,this.dataMap);
    if (oneDataMap) {
      oneDataMap.subtextSrc = this.showDateData(timeSrc);
    }
    this.getChartsData(oneDataMap);

  }

  private getTimeData(time,dataMap){
    return dataMap[time];
  }
          //  days = 30;//月时间段
  private getTrueData(dataJson){ //传入后台json
    let timeMapYMDObj:any = {}; //右边数据集
    let conents = dataJson.conent;
    if(conents!=null){
      let mapData:any = {};
      let mListData:any = new newEventChart(); //年份数据
      let isY = ''; //判断是否下一年
      for(let conent of conents){
        let mTime = (conent.countDate+'').split('-');

        let oneTemp:any = new Object(conent);

        oneTemp.newCount = conent.totalCount; //新闻总数
        oneTemp.negativeNewsCount = conent.negativeTotalCount; //负面新闻
        oneTemp.ratio = conent.totalRatio;  //负面/总新闻占比
        oneTemp.postDt = conent.countDate;

        timeMapYMDObj[(conent.countDate+'').replace(/-/g,'')] = oneTemp;

        if(isY == mTime[0]){
          mListData.newCountList.push(conent.totalCount);
          mListData.negativeNewsCountList.push(conent.negativeTotalCount);
          mListData.ratioList.push(conent.totalRatio);
          mListData.countDateList.push(conent.countDate);
          mListData.timeList.push(+mTime[mTime.length-1]);
          mapData[mTime[0]] = mListData;
        }else{
          mListData = new newEventChart();  //新一年数据
          mListData.newCountList.push(conent.totalCount);
          mListData.negativeNewsCountList.push(conent.negativeTotalCount);
          mListData.ratioList.push(conent.totalRatio);
          mListData.countDateList.push(conent.countDate);
          mListData.timeList.push(+mTime[mTime.length-1]);
          mapData[mTime[0]] = mListData;
          isY = mTime[0];
        }
        let singleNews = conent.singleNews;
        if(singleNews!=null){
          let dListData:any =  new newEventChart(); //月份数据
          for(let singleNew of singleNews){  //各个月数据
              dListData.newCountList.push(singleNew.newCount);
              dListData.negativeNewsCountList.push(singleNew.negativeNewsCount);
              dListData.ratioList.push(singleNew.ratio);
              dListData.countDateList.push(singleNew.postDt);
              let dTime = (singleNew.postDt+'').split('-');
              dListData.timeList.push(+dTime[dTime.length-1]);

            let oneTemp2:any = new Object(singleNew);
            timeMapYMDObj[(oneTemp2.postDt+'').replace(/-/g,'')] = oneTemp2;
          }
          mapData[mTime[0]+''+mTime[mTime.length-1]] = dListData;  //单个个月数据
          // mapData[warningRiskOutData.date] = mListData;  //一年12个月数据
        }

      }
      // this.timeMapYMDObj = timeMapYMDObj;
      return mapData;
    }
  }
}

class newEventChart{
  newCountList = [];  //新闻数集合
  negativeNewsCountList= [];   //负面新闻数集合
  ratioList= [];  //负面新闻占比集合
  countDateList = [];  //时间集合
  timeList= []; //时间集合
}

