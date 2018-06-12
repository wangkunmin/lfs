import {Component, DoCheck, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {NgxEchartsDirective, NgxEchartsService} from 'ngx-echarts';
import { CommonUtil } from '../../common/utill/common-util';
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import {LocalStorageService} from "../../common/service/localStorage.service";
import {GroupRiskApiService} from "../../common/api/group-risk-api.service";

@Component({
  selector: 'app-group-news-event',
  templateUrl: './group-news-event.component.html',
  styleUrls: ['./group-news-event.component.css']
})
export class GroupNewsEventComponent implements OnInit,DoCheck {
  startAndEndTime = [
    new Date().getFullYear()-7>2016? new Date().getFullYear()-7:2016,  //开始年
    new Date().getFullYear()    //结束年
  ];
  startAndEndLineTime:any = [
    new Date().getFullYear()-7>2016? new Date().getFullYear()-7:2016,  //开始年
    new Date().getFullYear()    //结束年
  ];
  @ViewChild("groupNewsEcharts")groupNewsEcharts:ElementRef;
  oldWidth = 0;//宽度比较
  rshowdate = '';//记录点击时间
  userId;
  options:any = {};
  getTimeNewsData:any={ //{postDt:string, newCount:number,negativeNewsCount:number,ratio:string }
    postDt:'2018',
    newCount:0,  //新闻总数
    negativeNewsCount:0, //负面新闻
    ratio:'0' //负面/总新闻占比
  };

  //eChart图数据值
  dataMap:any = {
    currentIndex:0
  };
  timeList =[];
  timeListApi;
  getTimeInit(ev){
    this.timeListApi = ev;
  }
  constructor(
    private ls:LocalStorageService,
    private groupRiskApiService : GroupRiskApiService,
    private es: NgxEchartsService,private commonUtil:CommonUtil) { }

  //初始化时间x时间轴
  initTimeList(){
    let d = new Date();
    let m = d.getMonth()+1;
    let days = new Date(d.getFullYear(),m,0);
    this.timeList = [];
    for(let i=1;i<= days.getDate();i++){
      this.timeList.push(i<10?('0'+i):i+'');
    }
  }
  ngOnInit() {
    this.oldWidth = document.getElementById("groupNewsEcharts").clientWidth;
    this.userId = this.ls.get("userInfo").userId;
    this.initTimeList();
    this.showChart();

  }

  echartsIntance;
  //初始化获取echartsIntance操作对象
  onChartInit(ec) {
    this.echartsIntance = ec;
  }

  //刷新echart视图窗体
  resizeChart() {
    if (this.echartsIntance) {
      this.echartsIntance.resize();
    }
  }

  ngDoCheck(){
    let newWidth = document.getElementById("groupNewsEcharts").clientWidth;
    if(this.oldWidth != newWidth){//避免多次刷新
      this.oldWidth = newWidth;//避免多次刷新
      this.resizeChart()
    }
  }

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
      rshowdate=time;
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
    let mE = new Date().getMonth()+1; //时间轴初始化当前月
    let dE = new Date().getDate();
    let startTime = yS+'-01-01';
    let endTime =yE+'-'+(mE<10?'0'+mE:mE)+'-'+(dE<10?'0'+dE:dE);
    let initTime = yE+''+(mE<10?'0'+mE:mE);
    let body = {
      startDate:startTime,
      endDate:endTime,
      userId:this.userId,
    };
    this.groupRiskApiService.getNewsCharts(body).subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.dataMap = this.getTrueData(geoJson.data);

        //根据实际数据的情况设定时间轴的时间间隔
        let dateYm = new Date();
        let y = dateYm.getFullYear();
        let m = dateYm.getMonth()+1;  //时间轴初始化当前月
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
          data: ['新闻总数', '负面新闻'],
          top:5,
          left:25,
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
            axisTick:false,//显示刻度轴
            axisLine:{
              lineStyle:{
                color:'#ccc', //轴线颜色
              }
            },
            boundaryGap : true,//从最左侧开始
            data:dataMap.timeList,
            // splitLine: {show: false},
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


  timeObj = '';//点击事件参数
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
    if (oneDataMap){
      oneDataMap.subtextSrc = this.showDateData(timeSrc);
    }

    this.getChartsData(oneDataMap);
  }

  private getTimeData(time,dataMap){
    return dataMap[time];
  }
  //  days = 30;//月时间段
  private getTrueData(dataJson){ //传入后台json
    let conents = dataJson.conent;
    if(conents!=null){

      let mapData:any = {};
      let mListData:any = new newEventChart(); //年份数据

      let isY = ''; //判断是否下一年
      for(let conent of conents){
        let mTime = (conent.countDate+'').split('-');
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
          }
          mapData[mTime[0]+''+mTime[mTime.length-1]] = dListData;  //单个个月数据
          // mapData[warningRiskOutData.date] = mListData;  //一年12个月数据
        }

      }
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

/*
export class GroupNewsEventComponent implements OnInit,DoCheck {
  startAndEndTime = [
    new Date().getFullYear()-7,  //开始年
    new Date().getFullYear()    //结束年
  ];

  @ViewChild("groupNewsEcharts")groupNewsEcharts:ElementRef;
  oldWidth = 0;//宽度比较

  userId;
  options:any = {};
  getTimeNewsData:any={ //{postDt:string, newCount:number,negativeNewsCount:number,ratio:string }
    postDt:this.commonUtil.dateFormat(new Date(),'yyyy-MM-dd'),
    newCount:0,  //新闻总数
    negativeNewsCount:0, //负面新闻
    ratio:'0' //负面/总新闻占比
  };

  //eChart图数据值
  dataMap:any = {
    currentIndex:0
  };
  timeList =[];

  constructor(
    private ls:LocalStorageService,
    private groupRiskApiService : GroupRiskApiService,
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
    this.oldWidth = document.getElementById("groupNewsEcharts").clientWidth;
    this.userId = this.ls.get("userInfo").userId;
    this.initTimeList();
    this.showChart();
    this.showDateData()
  }

  echartsIntance;
  //初始化获取echartsIntance操作对象
  onChartInit(ec) {
    this.echartsIntance = ec;
  }

  //刷新echart视图窗体
  resizeChart() {
    if (this.echartsIntance) {
      this.echartsIntance.resize();
    }
  }

  ngDoCheck(){
    let newWidth = document.getElementById("groupNewsEcharts").clientWidth;
    if(this.oldWidth != newWidth){//避免多次刷新
      this.oldWidth = newWidth;//避免多次刷新
      this.resizeChart()
    }
  }

  //获取当天数据
  showDateData(){
    let body = {
      pos_Dt:this.commonUtil.dateFormat(new Date(),'yyyy-MM-dd'),
      userId:this.userId,
    };
    this.groupRiskApiService.getNewsChartByDate(body).subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.getTimeNewsData = geoJson.data;
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


  //加载数据展开图
  showChart(){
    let body = {
      userId:this.userId,
    };
    this.groupRiskApiService.getNewsCharts(body).subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.dataMap = this.getTrueData(geoJson, this.timeList);
        this.getChartsData(this.dataMap);

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

  //获取图形数据值
  getChartsData(dataMap){
    if(dataMap.currentIndex ==undefined){
      dataMap.currentIndex = 6;
    }
    this.options = {
      baseOption: {
        timeline: {
          // y: 0,
          axisType: 'category',
          // realtime: false,
          // loop: false,
          autoPlay: false,//自动播放
          // currentIndex: 2,
          playInterval: 1000,
          // controlStyle: { //播放按钮
          //     position: 'left'
          // },
          checkpointStyle:{ //当前点样式
            color : 'auto',
            borderColor:'auto',
          },
          data: dataMap.startEndList,
          currentIndex:dataMap.currentIndex, //默认选中值
          label: {
            formatter : function(s) {
              return s;
            }
          },
          controlStyle:{
            show:false
          }
        },
        title: {
          show:false
        },
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            let ret = '';
            for(let t of params){
              ret+=t.marker+' '+ t.seriesName+' : '+ (t.value==null?0:t.value==''?0:t.value)+'</br>';
            }
            return ret;
          }
        },
        legend: {
          top:'25',
          right: '90',
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
            boundaryGap : false,//从最左侧开始
            data:dataMap.timeList,
            splitLine: {show: false},
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'line',        // 默认为直线，可选为：'line' | 'shadow'
              label: {
                show: true,
                // formatter: function (params) {
                //   let ret = params.value.replace('\n', '');
                //   return ret;
                // }
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
            }
          }
        ],
        series: [
          {name: '新闻总数', type: 'line'},
          {name: '负面新闻', type: 'line'},
          {name: '负面/总新闻占比', type: 'line'},
        ]
      },
      options: dataMap.optionSeries
    };
  }
  //获取时间轴点击事件
  getClickTimeLine(e?){
    if(e.componentType =='timeline'){ //点击时间轴方法
      let d = new Date(e.name);
      let days = new Date(d.getFullYear(),d.getMonth()+1,0);
      this.timeList = [];
      for(let i=1;i<= days.getDate();i++){
        this.timeList.push(i<10?('0'+i):i+'');
      }
      this.dataMap.timeList = this.timeList;
      this.dataMap.currentIndex = e.dataIndex;
      this.getChartsData(this.dataMap);
    }

  }
  //  days = 30;//月时间段
  private getTrueData(JsonData,timeList){ //传入后台json
    let dataMap:any = {};
    let startEndList = [];//时间轴
    let optionSeries:{  //直角坐标系点坐标
      series: [
        {
          data: [
            {
              name: string,
              value: string
            }
            ]
        }
        ]
    }[] = [];

    if(JsonData.data.conent!=undefined){
      for(let oneConent of JsonData.data.conent){
        startEndList.push(oneConent.countDate);
        let dataList1:any = [];  //添加新闻总数
        let dataList2:any = [];  //负面新闻数
        let dataList3:any = [];  //负面/新闻总数占比
        if(oneConent.singleNews != null){
          for(let oneSingleNews of oneConent.singleNews){ //添加新闻总数 ， 负面新闻数
            dataList1.push({
              name: this.commonUtil.dateFormat(new Date(oneSingleNews.postDt),'dd'),
              value: oneSingleNews.newCount,
              postDt:oneSingleNews.postDt,
              ratio:oneSingleNews.ratio
            });
            dataList2.push({
              name: this.commonUtil.dateFormat(new Date(oneSingleNews.postDt),'dd'),
              value: oneSingleNews.negativeNewsCount,
              postDt:oneSingleNews.postDt,
              ratio:oneSingleNews.ratio
            });
            dataList3.push({
              name: this.commonUtil.dateFormat(new Date(oneSingleNews.postDt),'dd'),
              value: oneSingleNews.ratio,
              postDt:oneSingleNews.postDt,
              ratio:oneSingleNews.ratio
            })
          }
        }
        optionSeries.push({  //添加所有时间轴数据
          series:[
            {data:dataList1},
            {data:dataList2},
            {data:dataList3}
          ]
        })
      }
    }else {
      let mes = JSON.stringify(JsonData);
      throw new Error('验证后台数据： '+mes);
    }
    dataMap.startEndList = startEndList;//时间轴
    dataMap.timeList = timeList; //直角坐标系x轴
    dataMap.optionSeries = optionSeries;
    return dataMap;
  }
}*/
