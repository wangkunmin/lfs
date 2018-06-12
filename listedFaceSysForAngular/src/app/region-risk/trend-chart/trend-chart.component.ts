import { Component, OnInit } from '@angular/core';

import { NgxEchartsService } from 'ngx-echarts';
import { CommonUtil } from '../../common/utill/common-util';
import { ParamsService } from '../../common/service/params.service';
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';

@Component({
  selector: 'app-trend-chart',
  templateUrl: './trend-chart.component.html',
  styleUrls: ['./trend-chart.component.css']
})
export class TrendChartComponent implements OnInit {
  startAndEndTime = [
    new Date().getFullYear()-7>2016? new Date().getFullYear()-7:2016,  //开始年
    new Date().getFullYear()    //结束年
  ];
  startAndEndLineTime:any = [
    new Date().getFullYear()-7>2016? new Date().getFullYear()-7:2016,  //开始年
    new Date().getFullYear()    //结束年
  ];
  dataIsNull = false; //无数据情况
  options = {};
  rshowdate = '';//记录点击时间
  //eChart图数据值
  dataMap:any = {};
  //echar实例
  echartsIntance :any ={};
  timeObj = '';
  trendChartData = {
    dataMonth: CommonUtil.prototype.dateFormat(new Date(),'yyyy-MM'),  //日期时间
    risk1: 0,  //治理风险
    risk2: 0,  //财务风险
    risk3: 0,  //经营风险
    risk4: 0,  //市场风险
    risk5: 0,   //法律法规风险
  };
  timeList =['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
  timeListApi;
  getTimeInit(ev){
    this.timeListApi = ev;
  }
  html: string = `
    <ul>
        <li>财务风险：公司财务指标负增长、或者财务结构不合理，融资不当，导致投资者预期收益率下降或者企业生产经营出现状况，或者财务数据不可信，存在财务造假的可能性。</li>
        <li>治理风险：公司股权，控制权发生异动，管理人员或其他重要人员离职，或公司员工大量离职给公司生产经营造成不利影响；公司制度不合理，不健全，给公司持续经营带来不利影响。</li>
        <li>经营风险：公司研发，采购，生产，销售出现问题，可能会给公司带来经济损失的情况。</li>
        <li>市场风险：股票、债券等证券价格出现异动，或者存在交易异常等情况。</li>
        <li>法律法规风险：公司因触犯法律法相关条款，被监管部门实施处罚措施，可能造成企业经济损失或者经营受限的情况。</li>
    </ul>
  `;
  constructor(
    private es: NgxEchartsService,
    private paramsService : ParamsService,
    private regionRiskApiService : RegionRiskApiService,
    private commonUtil:CommonUtil
    ) { }


  ngOnInit() {

    this.showChart();
  }
  onChartInit(ec) {
    this.echartsIntance = ec;
  }
  //初始化当前年月数据
  initNewsData(y,mE){
    let inx = mE - 1;
    let yData = this.getTimeData(y,this.dataMap);
    if(yData){
      this.trendChartData.risk1 = yData.risk1[inx];
      this.trendChartData.risk2 = yData.risk2[inx];
      this.trendChartData.risk3 = yData.risk3[inx];
      this.trendChartData.risk4 = yData.risk4[inx];
      this.trendChartData.risk5 = yData.risk5[inx];
      this.trendChartData.dataMonth = y +'-'+(mE<10?'0'+mE:mE);  //日期时间
    }
  }

  //加载数据展开图
  showChart(){
    this.dataIsNull = false;
    let yS = this.startAndEndTime[0];
    let yE = this.startAndEndTime[1];
    let mE = new Date().getMonth()+1;
    let dE = new Date().getDate();
    let startTime = yS+'0101';
    let endTime =yE+''+(mE<10?'0'+mE:mE)+''+(dE<10?'0'+dE:dE);
    let initTime = yE+''+(mE<10?'0'+mE:mE);
    this.regionRiskApiService.getTrendChart(startTime,endTime).subscribe(geoJson=>{
        if(geoJson.code == '0'){
          this.dataMap = this.getDataFun(geoJson.data);
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
              this.timeListApi.initTimeLineValue()
              this.timeListApi.initDefaultYear(y,this.timeListApi.timeObj,m);
            }


          });
          this.timeObj = y+''+(m<10?'0'+m:m);
          this.showDateData(y+''+(m<10?'0'+m:m));
          this.getChartsData(this.getTimeData(y+''+(m<10?'0'+m:m),this.dataMap));
          {
           /* this.trendChartData.risk1 = this.yDatMap[yE].risk1Count;
            this.trendChartData.risk2 = this.yDatMap[yE].risk2Count;
            this.trendChartData.risk3 = this.yDatMap[yE].risk3Count;
            this.trendChartData.risk4 = this.yDatMap[yE].risk4Count;
            this.trendChartData.risk5 = this.yDatMap[yE].risk5Count;
            this.trendChartData.dataMonth = this.yDatMap[yE].date;  //日期时间*/
            this.initNewsData(y,m);
          }
        }else if(geoJson.code == '1' ){
          this.dataIsNull = true;
        }else if(geoJson.code == '-1' ){
          this.dataIsNull = true;
        }
      });
  }

  //获取月的天数
  getYMDays(y,m){
    return new Date(y,m,0).getDate()
  }

  //获取图形数据值
  getChartsData(dataMap){
        if(dataMap == undefined){
          dataMap = {};
        }
        let date = new Date();
        let m = date.getMonth()+1;//时间轴初始化当前月
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
            color:['#303F9F','#4CAF50','#E64A19','#9B89EF','#FF9F00'], //颜色设置
            icon:'circle',
            title: {
              show:true,
              subtext:subtextSrc,
              right:100
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
              top:'25',
              x: '90',
              data: [ '财务风险','治理风险', '经营风险', '市场风险', '法律法规风险'],
              selected: {
                '市场风险': true, '法律法规风险': true
              }
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
                    formatter: function (params) {
                      return params.value.replace('\n', '');
                    }
                  }
                }
              }
            },
            xAxis: [
              {
                'type':'category',
                axisTick:false,//显示刻度轴
                axisLine:{
                  lineStyle:{
                    color:'#ccc', //轴线颜色
                  }
                },
                boundaryGap : true,//从最左侧开始
                // data:dataMap.timeList,
                splitLine: {show: false},
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                  type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
                },
                data:dataMap.timeList
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
                name: '财务风险',
                type: 'line',
                data:dataMap.risk2
              },
              {
                name: '治理风险',
                type: 'line',
                data:dataMap.risk1
              },
              {
                name: '经营风险',
                type: 'line',
                data:dataMap.risk3
              },
              {
                name: '市场风险',
                type: 'line',
                data:dataMap.risk4
              },
              {
                name: '法律法规风险',
                type: 'line',
                data:dataMap.risk5
              },
            ]
          }
        };
  }


  getClickChart(e?){
    let time = this.timeObj + (+e.name<10?'0'+e.name:e.name);
    //显示右上时间
    this.echartsIntance.setOption({
      baseOption:{
        title:{
          subtext:this.showDateData(time)
        }
      }});

  }

//获取当天数据
  showDateData(time){
    let datedata: any={};
    let rshowdate='';
    //点击年的处理
    if ((time+'').length ==4){
      rshowdate = time;

    }
    //点击月的处理
    if ((time+'').length ==6){
      rshowdate =this.commonUtil.timeToTimeSrc(time);

    }
    //点击日的处理
    if ((time+'').length ==8){
      rshowdate = this.commonUtil.timeToTimeSrc(time);

    }

    this.rshowdate = rshowdate;
    return '截止时间：'+rshowdate;
  }

  //获取时间轴点击事件
  getClickTimeLine(e?){
    let timeSrc = '';
    let y ;
    if(e.nodeType == 0){
      timeSrc = e.timeObj0.value;
      y = e.timeObj0.value;
      {
        this.trendChartData.risk1 = this.yDatMap[y].risk1Count;
        this.trendChartData.risk2 = this.yDatMap[y].risk2Count;
        this.trendChartData.risk3 = this.yDatMap[y].risk3Count;
        this.trendChartData.risk4 = this.yDatMap[y].risk4Count;
        this.trendChartData.risk5 = this.yDatMap[y].risk5Count;
        this.trendChartData.dataMonth = this.yDatMap[y].date;  //日期时间
      }
    }else if(e.nodeType == 1){
      timeSrc = e.preTimeObj0.value;
      y = e.preTimeObj0.value;
      timeSrc = timeSrc+''+(e.timeObj0.value<10?'0'+e.timeObj0.value:e.timeObj0.value);
      this.initNewsData(y,e.timeObj0.value);
    }

    this.timeObj = timeSrc;

    let oneDataMap = this.getTimeData(timeSrc,this.dataMap);
    if (oneDataMap){
      oneDataMap.subtextSrc = this.showDateData(timeSrc);
    }

    this.getChartsData(oneDataMap);

    this.paramsService.sendEarlyWarning(timeSrc);//监测预警新闻 传年触发

  }

  private getTimeData(time,dataMap){
    return dataMap[time];
  }

  yDatMap:any = {
    2018:{
    date:2018,
    risk1Count :1,
    risk2Count :0,
    risk3Count :0,
    risk4Count :0,
    risk5Count :0,
    }
  };
  private getDataFun(dataJson){
    let warningRiskOutDataList = dataJson.warningRiskOutDataList;
    if(warningRiskOutDataList!=null){
      let mapData:any = {};
      for(let warningRiskOutData of warningRiskOutDataList){

        this.yDatMap[warningRiskOutData.date] = {
          date:warningRiskOutData.date,
          risk1Count :warningRiskOutData.risk1Count,
          risk2Count :warningRiskOutData.risk2Count,
          risk3Count :warningRiskOutData.risk3Count,
          risk4Count :warningRiskOutData.risk4Count,
          risk5Count :warningRiskOutData.risk5Count,
        };
        let mListData:any = {
          risk1:[],
          risk2:[],
          risk3:[],
          risk4:[],
          risk5:[],
          dataMonth : [],
          timeList :[],
        }; //年份数据
        let warningRiskInfoDataList = warningRiskOutData.warningRiskInfoDataList;
        if(warningRiskInfoDataList!=null){
          for(let warningRiskInfoData of warningRiskInfoDataList){  //各个月数据
            mListData.risk1.push(warningRiskInfoData.risk1);
            mListData.risk2.push(warningRiskInfoData.risk2);
            mListData.risk3.push(warningRiskInfoData.risk3);
            mListData.risk4.push(warningRiskInfoData.risk4);
            mListData.risk5.push(warningRiskInfoData.risk5);
            mListData.dataMonth.push(warningRiskInfoData.dataMonth);
            mListData.timeList.push(warningRiskInfoData.dataMonth);

            let dListData:any = {
              risk1:[],
              risk2:[],
              risk3:[],
              risk4:[],
              risk5:[],
              dataMonth : [],
              timeList:[]
            }; //年份数据
            let dateList = warningRiskInfoData.dayList;
            if(dateList!=null){
              for(let dateOne of dateList){  //每天数据
                dListData.risk1.push(dateOne.risk1);
                dListData.risk2.push(dateOne.risk2);
                dListData.risk3.push(dateOne.risk3);
                dListData.risk4.push(dateOne.risk4);
                dListData.risk5.push(dateOne.risk5);
                dListData.dataMonth.push(dateOne.dataMonth);
                dListData.timeList.push(dateOne.dataMonth);
              }
              mapData[warningRiskOutData.date+''+(warningRiskInfoData.dataMonth<10?'0'+warningRiskInfoData.dataMonth:warningRiskInfoData.dataMonth)] = dListData;  //单个个月数据
            }
          }
          mapData[warningRiskOutData.date] = mListData;  //一年12个月数据
        }

      }
      return mapData;
    }
  }
}







// export class TrendChartComponent implements OnInit {
//   dataIsNull = false; //无数据情况
//   options = {};
//   //eChart图数据值
//   dataMap:any = {
//     currentIndex:6
//   };
//
//   trendChartData = {
//     dataMonth: CommonUtil.prototype.dateFormat(new Date(),'yyyy-MM'),  //日期时间
//     risk1: 0,  //治理风险
//     risk2: 0,  //财务风险
//     risk3: 0,  //经营风险
//     risk4: 0,  //市场风险
//     risk5: 0,   //法律法规风险
//   };
//   timeList =['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
//
//   constructor(
//     private es: NgxEchartsService,
//     private paramsService : ParamsService,
//     private regionRiskApiService : RegionRiskApiService
//     ) { }
//
//
//   ngOnInit() {
//     // this.paramsService.sendEarlyWarning(new Date().getFullYear());
//     this.initNewsData();
//     this.showChart();
//   }
//
//   //初始化当前年月数据
//   initNewsData(){
//     this.regionRiskApiService.getTrendWarningSingle().subscribe(geoJson=>{
//       if(geoJson.code == '0'){
//           this.trendChartData = geoJson.data['monthData'];
//         }else if(geoJson.code == '1'){
//
//         }else if(geoJson.code == '-1'){
//
//       }
//       });
//
//   }
//
//   //加载数据展开图
//   showChart(){
//     this.dataIsNull = false;
//     this.regionRiskApiService.getTrendChart().subscribe(geoJson=>{
//         if(geoJson.code == '0'){
//           this.dataMap = this.getTrueData(geoJson, this.timeList);
//           this.getChartsData(this.dataMap);
//         }else if(geoJson.code == '1' ){
//           this.dataIsNull = true;
//         }else if(geoJson.code == '-1' ){
//           this.dataIsNull = true;
//         }
//       });
//   }
//
//
//   //获取图形数据值
//   getChartsData(dataMap){
//         this.options = {
//           baseOption: {
//             timeline: {
//               // y: 0,
//               axisType: 'category',
//               // realtime: false,
//               // loop: false,
//               autoPlay: false,//自动播放
//               // currentIndex: 2,
//               playInterval: 1000,
//               // controlStyle: { //播放按钮
//               //     position: 'left'
//               // },
//               checkpointStyle:{ //当前点样式
//                 color : 'auto',
//                 borderColor:'auto',
//               },
//               data: dataMap.startEndList,
//               currentIndex:this.dataMap.currentIndex,
//               label: {
//                 formatter : function(s) {
//                   return s;
//                 }
//               },
//               controlStyle:{
//                 show:false
//               }
//             },
//             title: {
//               show:false
//             },
//             tooltip: {
//               trigger: 'axis',
//
//             },
//             legend: {
//               top:'25',
//               x: '90',
//               data: ['治理风险', '财务风险', '经营风险', '市场风险', '法律法规风险'],
//               selected: {
//                 '市场风险': true, '法律法规风险': true
//               }
//             },
//             calculable : true,
//             grid: {
//               top: 80,
//               bottom: 100,
//               tooltip: {
//                 trigger: 'axis',
//                 axisPointer: {
//                   type: 'shadow',
//                   label: {
//                     show: true,
//                     formatter: function (params) {
//                       return params.value.replace('\n', '');
//                     }
//                   }
//                 }
//               }
//             },
//             xAxis: [
//               {
//                 'type':'category',
//                 'axisLabel':{'interval':0},
//                 boundaryGap : false,//从最左侧开始
//                 data:dataMap.timeList,
//                 splitLine: {show: false},
//                 axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//                   type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
//                 }
//               }
//             ],
//             yAxis: [
//               {
//                 type: 'value',
//                 // name: '市场风险（亿元）',
//                 splitArea: {//块颜色分割
//                   show: true
//                 }
//               }
//             ],
//             series: [
//               {name: '治理风险', type: 'line'},
//               {name: '财务风险', type: 'line'},
//               {name: '经营风险', type: 'line'},
//               {name: '市场风险', type: 'line'},
//               {name: '法律法规风险', type: 'line'},
//             ]
//           },
//           options: dataMap.optionSeries
//         };
//   }
//
//
//
//   private getTrueData(JsonData,timeList){ //传入后台json
//     let dataMap:any = {};
//     let startEndList = [];//时间轴
//     let optionSeries:{  //直角坐标系点坐标
//       series: [
//         {
//           data: [
//             {
//               name: string,
//               value: string
//             }
//             ]
//         }
//         ]
//     }[] = [];
//     if(JsonData.data.warningRiskOutDataList!=undefined && JsonData.data.warningRiskOutDataList!= null){
//       for(let oneConent of JsonData.data.warningRiskOutDataList){
//         startEndList.push(oneConent.date);
//          // risk1://治理风险   risk2://财务风险        risk3://经营风险       risk4:  //市场风险      risk5://法律法规风险
//         let dataList1:any = [];  //治理风险
//         let dataList2:any = [];  //财务风险
//         let dataList3:any = [];  //经营风险
//         let dataList4:any = [];  //市场风险
//         let dataList5:any = [];  //法律法规风险
//         if(oneConent.warningRiskInfoDataList!=null){
//           for(let oneSingleNews of oneConent.warningRiskInfoDataList){ //添加新闻总数 ， 负面新闻数
//             dataList1.push({
//               name: timeList[oneSingleNews.dataMonth],
//               value: oneSingleNews.risk1,
//             });
//             dataList2.push({
//               name: timeList[oneSingleNews.dataMonth],
//               value: oneSingleNews.risk2,
//             });
//             dataList3.push({
//               name: timeList[oneSingleNews.dataMonth],
//               value: oneSingleNews.risk3,
//             });
//             dataList4.push({
//               name: timeList[oneSingleNews.dataMonth],
//               value: oneSingleNews.risk5,
//             });
//             dataList5.push({
//               name: timeList[oneSingleNews.dataMonth],
//               value: oneSingleNews.risk4,
//             })
//           }
//         }
//         optionSeries.push({  //添加所有时间轴数据
//           series:[
//             {data:dataList1},
//             {data:dataList2},
//             {data:dataList3},
//             {data:dataList4},
//             {data:dataList5},
//           ]
//         })
//       }
//     }else {
//       let mes = JSON.stringify(JsonData);
//       throw new Error('验证后台数据： '+mes);
//     }
//     dataMap.startEndList = startEndList;//时间轴
//     dataMap.timeList = timeList; //直角坐标系x轴
//     dataMap.optionSeries = optionSeries;
//     dataMap.currentIndex = 6;
//     this.dataMap = dataMap;
//     this.dataMap.currentIndex = 6;
//     return dataMap;
//   }
//
//
//   //获取时间轴点击事件
//   getClickTimeLine(e?){
//     if(e.componentType =='timeline'){ //点击时间轴方法
//       this.dataMap.timeList = this.timeList;
//       this.dataMap.currentIndex = e.dataIndex;
//       this.paramsService.sendEarlyWarning(e.name);
//       this.getChartsData(this.dataMap);
//     }
//
//   }
// }
