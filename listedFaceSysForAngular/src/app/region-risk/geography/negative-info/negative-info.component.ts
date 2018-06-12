import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Base} from "../../../common/constant/api-base.const";
import {RegionRiskApiService} from "../../../common/api/region-risk-api.service";
import { NgxEchartsService } from 'ngx-echarts';
import {LocalStorageService} from "../../../common/service/localStorage.service";

@Component({
  selector: 'app-negative-info',
  templateUrl: './negative-info.component.html',
  styleUrls: ['../geography.component.css']
})
export class NegativeInfoComponent implements OnInit {
  isDataNull = 0; // 1 数据为空  -1 数据异常

  selectMapSize = 0; //0 深圳市  1 非深圳市
  options = {};
  constructor(
    private regionRiskApiService : RegionRiskApiService,
    private es: NgxEchartsService,
    private lg:LocalStorageService
  ) { }
  eventDate = '';//入参时间
  ngOnInit() {
    this.getRiskDistribute();
  }
  geogMapMap = {}; //地图map数据
  allBaseMap = {};  //所有负面坐标 数据

  cityNegative = {
    regionName:'深圳市',
    negativeCount:0,  //负面数
    waringCompanyCount:0, //预警公司数
  };  //深圳市预警数 && 负面数

  cityCompanyList = []; //各区公司负面信息
  //获取深圳市 或其 各区地图数据
  getGeogMapMap(key){
    return this.geogMapMap[key];
  }
  //获取区的所有公司baseMap
  getAllBaseMap(key){
    return this.allBaseMap[key];
  }
  //获取区的所有公司信息
  getCityCompanyList(key,isFilter?){
    let resList:any = {};
    if(isFilter == undefined){
      let data = this.getAllBaseMap(key);
      if(data != undefined){
        resList = this.filterNegativeCompanyList(data.negativeCompanyList.data);
      }
    }else{
      resList = this.getAllBaseMap(key).negativeCompanyList.data;
    }
    return resList;
  }
  //过滤负面事件为 0
  private filterNegativeCompanyList(list){
    let resList = [];
    for(let temp of list){
      if(+temp.negativeCount>0){
        resList.push(temp)
      }else{

      }
    }
    return resList;
  }

  //获取深圳市预警 负面数据
  getSZNegativeData(key){
    let temp = this.getAllBaseMap(key);
    let res = {
      regionName:key,
      negativeCount:0, //负面数
      waringCompanyCount:0,  //预警公司数
    };
    if(temp!= undefined){
      res = {
        regionName:key,
        negativeCount:temp.negativeCount, //负面数
        waringCompanyCount:temp.waringCompanyCount,  //预警公司数
      };
    }
    return res;
  }

  //获取 深圳市 所有区公司负面事件数据
  getRiskDistribute(){
    //查询当天负面事件数据
    let date = new Date();
    let y = date.getFullYear();
    let m = date.getMonth()+1;
    let d = date.getDate();
    let userId = this.lg.get('userInfo').userId;
    this.eventDate = y+ ''+(m>=10?m:('0'+m))+ ''+(d>=10?d:('0'+d));
    // this.eventDate = 20180427+'';
    this.regionRiskApiService.getNegativeDistributeMap(userId,this.eventDate).subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.negativeData(geoJson.data['content']);
        this.isDataNull = 0;
      }else if(geoJson.code == '1'){
        this.isDataNull = 1;
        this.getGeogMap2(true);
      }else if(geoJson.code == '-1'){
        this.isDataNull = -1;
      }
    } )
  }


  //series 风险级数据处理
  private convertData(riskLevel){
    let res = [];
    for(let i = 0;i<riskLevel.data.length;i++){
      let geoCoord = riskLevel.map[riskLevel.data[i].name];
      if(geoCoord){
        res.push({
          name:riskLevel.data[i].name,
          value:geoCoord.concat(riskLevel.data[i])
        })
      }
    }
    return res;
  }
  //true负面和 false非负面数据处理
  private convertDataList(companyList,isNegative:boolean){
    let companyObj = {
      data:[],
      map:companyList.map,
    };
    if(companyList == null ){
      return companyObj;
    }
    if(companyList.data == null){
      return companyObj;
    }
      let res_1 = [];
      let res_2 = [];
      let i = 0;
      let j = 0;
      for(let companyData of companyList.data){
        if(companyData.twinkle == true){ //负面数据
          res_1[i] = companyData;
          i++;
        }else{ //非负面数据
          res_2[j] = companyData;
          j++;
        }
      }
      if(isNegative){ //负面数据
        companyObj.data = res_1;
      }else{ //非负面数据
        companyObj.data = res_2;
      }
    return companyObj;
  }

  //设置eChart加载地图
  setChartRegisterMap(geoJson){
    this.es.registerMap('SZ', geoJson);
  }

  //负面事件数据处理
  negativeData(jsonData){
    if(jsonData == null){
      return null;
    }
    /*jsonData = [
      {
        regionName:'光明新区',
        companyInfos:[
          {companyId:'83802',companyNm:'深圳公司',companySnm:'佳创视讯',postion:[113.95296,22.743902],regionName:'光明新区',rating:1}
        ]
      }
    ];*/
    let szMapData = {  //深圳市
      negativeCompanyList: { map:[], data:[] },  //负面数据
      negativeCount:jsonData.negativeCount,  //负面数
      waringCompanyCount:jsonData.waringCompanyCount, //预警公司数
    };
    for(let aData of jsonData.regionList){
      let tMapData = {
        negativeCompanyList: { map:{}, data:[] },  //负面
        negativeCount:aData.negativeCount,  //负面数
        waringCompanyCount:aData.waringCompanyCount, //预警公司数
      };
      if(aData.companyInfos !=null ) {
        for (let companyInfo of aData.companyInfos) {
          let tRiskData: any = {};
          tRiskData = companyInfo;
          tRiskData.name = companyInfo.companySnm.trim();

          tMapData.negativeCompanyList.map[companyInfo.companySnm.trim()] = companyInfo.postion;
          tMapData.negativeCompanyList.data.push({});
          tMapData.negativeCompanyList.data[tMapData.negativeCompanyList.data.length - 1] = tRiskData;

          szMapData.negativeCompanyList.map[companyInfo.companySnm.trim()] = companyInfo.postion;
          szMapData.negativeCompanyList.data.push({});
          szMapData.negativeCompanyList.data[szMapData.negativeCompanyList.data.length - 1] = tRiskData;

        }
      }
      this.allBaseMap[aData.regionName] = tMapData;
    }
    this.allBaseMap['深圳市'] = szMapData;
    this.getGeogMap2();
  }

  //获取地图资源
  getGeogMap2(nullData?){
    this.regionRiskApiService.getGeographyChartsMap().subscribe(geoJson => {
      this.geogMapMap['深圳市'] = geoJson;
      this.geogMapMap['深圳市'].cityMapValue = Base.cityUrlList.SZ.value;
      let mapDataList = geoJson['features'];
      for(let mapData of mapDataList){
        this.geogMapMap[mapData.properties.name.trim()] = {
          "type": "FeatureCollection",
          "features": [mapData]
        }
      }
      let regionName = '深圳市';
      if(nullData == true){
        let mapData = {
            negativeCompanyList:{
              data:null,
              map:null
            }
          }
        this.initChart(mapData,this.getGeogMapMap(regionName),regionName);
        return;
      }
      this.initChart(this.getAllBaseMap(regionName),this.getGeogMapMap(regionName),regionName);
      this.cityCompanyList = this.getCityCompanyList(regionName);
      this.cityNegative = this.getSZNegativeData(regionName);
    });
  }
  //初始化Chart图
  initChart(mapData,geogMapMap,regionName,symbolSize?){
    this.setChartRegisterMap(geogMapMap);
    if(this.isDataNull != 0){
       mapData = {
        negativeCompanyList:{
          data:null,
          map:null
        }
      }
    }
    if(mapData == undefined || mapData == null){
       mapData = {
        negativeCompanyList:{
          data:null,
          map:null
        }
      }
    }
    let extraCssText ='box-shadow: 0 12px 22px 0 rgba(0,0,0,0.24);padding:0;padding-top:4px;margin-top: 5px;'; //tooltipdiv样式
    if(symbolSize == undefined){
      symbolSize = 12;
    }
    let emphasisAreaColor = '';
    if(regionName == '深圳市'){
      emphasisAreaColor = '#D6E4F6';
    }else{
      emphasisAreaColor = 'rgba(51,56,105,0.1)';
    }
    let date = new Date();
    let m = date.getMonth()+1;
    let subtextSrc = '截止时间：'+date.getFullYear()+ '-'+(m<10?'0'+m:m);
    this.options = {
      title: {
        // subtext: subtextSrc,
        right:25
      },
      tooltip: {
        trigger: 'item',
        position:'bottom',
        backgroundColor: '#ffffff',
        borderWidth:0,
        padding:0,
        extraCssText:extraCssText,
        enterable:true, //可进入tooltip交互
        formatter: function (params,ticket,callback) {
          let res = '';
           if(params.seriesName != Base.cityName_SZ){
             let value = params.value[2];
             let rating =  +value.rating;
             let ratingSrc = '';
             if(rating == 1){ //高风险
               ratingSrc = '高风险';
             }else if(rating == 2){ //次高风险
               ratingSrc = '次高风险';
             }else if(rating == 3){ //关注
               ratingSrc = '关注';
             }else if(rating == 4){ //正常
               ratingSrc = '正常';
             }else {
               ratingSrc = '';
             }
             res =
               `<div class="col-lg-12 padding-left-right_0">
                    <span class="col-lg-5 cursor-pointer padding-left-right_0" id="companyClick" onclick="window.open('#/lfs/company/id/${value.companyId}')" title="${value.companySnm} 「${value.securityCd}」" ><a >${params.marker} ${value.companySnm} 「${value.securityCd}」</a></span>
                    <span class="col-lg-2">${ratingSrc}</span>`;
             res = res+`<span class="col-lg-5 u-leaveOut" title="负面事件数 ： ${value.negativeCount}">负面事件数 ：${value.negativeCount}</span>`;
             res = `<div class="m-message-bottom-box geography-tooltip" value="${JSON.stringify(value).replace(/"/g,"'")}"> <div class="">${res}</div></div>`;
           }
          return res;
        },
      },
      toolbox: {
        show: false,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
          dataView: { readOnly: false },
          restore: {},
          saveAsImage: {}
        }
      },
      geo:{
        map:'SZ',
        label:{
          normal:{ //区域名
            show:true,
            textStyle:{
              color:'rgba(0,0,0,0.4)'
            }
          },
        },
        itemStyle: {
          normal: {
            borderColor:'#fff',
            label: { show: true },
            // borderWidth:.5,//区域边宽度
            // borderColor:"#fff",//区域边框颜色
            areaColor:"rgba(51,56,105,0.1)",//区域颜色
          },
          emphasis: {
            label: { show: true },
            areaColor:emphasisAreaColor,//区域颜色
          }
        },
        // roam:true,  //放大缩小，鼠标拖动效果
        aspectScale:1.2,
      },
      series: [
        {
          name:Base.comRiskLevelList.risk1.text,
          type:'scatter',  //effectScatter
          coordinateSystem:'geo',
          data:this.convertData(this.convertDataList(mapData.negativeCompanyList,false)),
          symbolSize:symbolSize, // 点的大小
          rippleEffect:{
            brushType:'stroke',
          },
          label:{
            narmal:{
              formatter:'{b}',
              position:'right',
              show:true
            }
          },
          itemStyle:{
            normal:{
              color:function (params) {
                let rating =  +params.value[2].rating;
                if(rating == 1){ //高风险
                  return '#ee2f24';
                }else if(rating == 2){ //高风险
                  return  '#ffac17';
                }else if(rating == 3){ //高风险
                  return '#fffb0f';
                }else if(rating == 4){ //低风险
                  return '#06ff0b';
                }

              },
            }
          }
        },
        {
          name:Base.comRiskLevelList.risk1.text,
          type:'effectScatter',  //effectScatter
          coordinateSystem:'geo',
          data:this.convertData(this.convertDataList(mapData.negativeCompanyList,true)),
          symbolSize:symbolSize, // 点的大小
          rippleEffect:{
            brushType:'stroke',  //波动类型 fill
            scale:4, //波纹圈大小
            period:4  //波动时间
          },
          label:{
            narmal:{
              formatter:'{b}',
              position:'right',
              show:true
            }
          },
          itemStyle:{
            normal:{
              color:function (params) {
                let rating =  +params.value[2].rating;
                if(rating == 1){ //高风险
                  return '#ee2f24';
                }else if(rating == 2){ //高风险
                  return  '#ffac17';
                }else if(rating == 3){ //高风险
                  return '#fffb0f';
                }else if(rating == 4){ //低风险
                  return '#06ff0b';
                }

              },
           /*   shadowBlur:10,
              shadowColor:'#333333'*/
            }
          }
        },
        {
          // name: '深圳市各区',
          name: Base.cityName_SZ,
          type: 'map',
          // mapType: 'SZ', // map type should be registered
          geoIndex:0,
          aspectScale:1.2, //图片长宽比
          data: Base.cityListForSZ,
        }
      ]
    };
  }

  //自定义map点点击事件
  anClick(ev){
    if(null == ev.target ){
      return ;
    }else{
      if("m-message-bottom-box geography-tooltip" == ev.target.className){
        let toElement = ev.target;
        let value = JSON.parse((toElement.getAttribute('value')+'').replace(/'/g,'"'));
        this.forAnClick(value,ev);
      }else{
        return ;
      }
    }
  }

  //点击EChart图
  clickChart(e){
    let tempKey = '';
    if(this.cityNegative.regionName == e.name){
      tempKey = '深圳市';
      return;
    }else{
      if(e.seriesName == Base.cityName_SZ) {
        tempKey = e.name;
      }
    }
    if(tempKey != ''){
      this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey),tempKey);
      this.cityCompanyList = this.getCityCompanyList(tempKey);
      this.cityNegative = this.getSZNegativeData(tempKey);
    }
  }

  //选中公司
  selectCompany = {
    companySnm:'',
    securityCd:'',
    typeMap:"", //负面事件列表
  };
  //自定义map点击事件处理
  private forAnClick(data,ev?){
    this.selectMapSize = 1;
    this.selectCompany = data;
    let name =  this.cityNegative.regionName;//获取当前区名
    if(name ==  '深圳'){
      name =  '深圳市';
    }
    if(data.twinkle == true){
      this.upDataAllBaseMap(data);
    }
    this.cityNegative = this.getSZNegativeData(name);
    this.initChart(this.getAllBaseMap(name),this.getGeogMapMap(name),name);
  }
  //点击后更新点状态数据
  upDataAllBaseMap(data){
    this.forUpData(data.regionName,data.companyId);
    this.forUpData('深圳市',data.companyId);
    let negativeInfoRecordInData = {
      companyId:data.companyId,
      userId:this.lg.get('userInfo').userId,
      announceCds:data.announceCds,
      newsCds:data.newsCds,
      eventDate:this.eventDate
    };
    this.regionRiskApiService.negativeDistributeRecord(negativeInfoRecordInData).subscribe(geoJson => {
      if(geoJson.code == '0'){

      }else if(geoJson.code == '1'){

      }else if(geoJson.code == '-1'){

      }
    } )
  }
  private forUpData(regionName,companyId){
    let negativeCompanyList = this.allBaseMap[regionName].negativeCompanyList;
    for(let tempData of negativeCompanyList.data){
      if(tempData.companyId == companyId){
        tempData.twinkle = false;  //标注无负面事件
      }
    }
  }
  //初始化深圳
  getInitMapData(){
    this.selectMapSize = 0;
    let tempKey = '深圳市';
    this.cityNegative = this.getSZNegativeData(tempKey);
    this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey),tempKey);
  }

  //点击负面事件
  clickNewTitle(cityCompany){
    if(cityCompany.type == 1){//公告
      // 查看新闻详情 外部
      this.regionRiskApiService.getEventRiskDetail(cityCompany.infoCd)
        .subscribe(
          data => {
            if (data.code === '0') {
              window.open(data.data['url']);
              return;
            }
          },
          error => {
            console.log(error);
          }
        );
    }else if(cityCompany.type == 2){ //新闻
      // 查看新闻详情
      window.open(`#/lfs/region/news/${cityCompany.companyId}/${cityCompany.infoCd}`);
    }

  }

  //跳转公司展台
  goCompanyInfo(companyId,$event?){
    if($event!=undefined){
      $event.stopPropagation();
    }
    window.open(`#/lfs/company/id/${companyId}`);
  }
  //刷新当前区的echart图
  refreshChart(){
    let tempKey = this.cityNegative.regionName;
    this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey),tempKey);
  }

}
