import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Base} from "../../../common/constant/api-base.const";
import {RegionRiskApiService} from "../../../common/api/region-risk-api.service";
import { NgxEchartsService } from 'ngx-echarts';
import {CommonUtil} from "../../../common/utill/common-util";

@Component({
  selector: 'app-risk-dimension',
  templateUrl: './risk-dimension.component.html',
  styleUrls: ['../geography.component.css']
})
export class RiskDimensionComponent implements OnInit {

  selectMapSize = 0; //0 深圳市  1 非深圳市
  options = {};
  constructor(
    private regionRiskApiService : RegionRiskApiService,
    private commonUtil: CommonUtil,
    private es: NgxEchartsService
  ) { }

  ngOnInit() {
    this.getRiskDistribute();
  }
  geogMapMap = {}; //地图map数据
  allBaseMap = {};  //所有负面坐标 数据



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
      resList = this.filterNegativeCompanyList(this.getAllBaseMap(key).negativeCompanyList.data);
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
  getSZNegativeData(key,riskType){
    let res = this.getAllBaseMap(key);
     res.riskType = riskType;
     res.regionName = key;
      return res;
  }

  //获取 深圳市 所有区公司负面事件数据
  getRiskDistribute(){
    this.regionRiskApiService.getDimensionDistributeMap().subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.negativeData(geoJson.data['content']);
      }else if(geoJson.code == '1'){

      }else if(geoJson.code == '-1'){

      }
    } )
  }
  //点击EChart图
  clickChart(e){
    let tempKey = '';
    let riskType = 0;
    if(this.cityNegative.regionName == e.name){
     /* tempKey = '深圳市';
      riskType = -1;*/
      return ;
    }else{
      if(e.seriesName == Base.cityName_SZ) {
        tempKey = e.name;
        riskType = 0;
      }
    }
    if(tempKey != ''){
      this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey),tempKey,riskType);
      this.cityCompanyList = this.getCityCompanyList(tempKey);
      this.cityNegative = this.getSZNegativeData(tempKey,riskType);
      this.selectRiskClass = riskType;
    }
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
        if(companyData.negativeCount>0){ //负面数据
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

  //风险事件类型  riskType
  private convertRiskTypeDataList(companyList,riskType:number){
    if(+riskType == 0){
      return companyList;
    }

    let companyObj = {
      data: [],
      map: companyList.map,
    };
    if(+riskType == -1){
      return companyObj;
    }
    if(companyList == null ){
      return companyObj;
    }
    if(companyList.data == null){
      return companyObj;
    }
    let res = [];
    let i = 0;
  /*  { key:1,value:'治理风险'},
    { key:2,value:'信息披露'},
    { key:3,value:'财务会计'},
    { key:4,value:'其他'},*/
    for (let companyData of companyList.data) {
      switch (riskType){
        case 1:
          if(+companyData.riskZhiLiCount>0){
            res[i] = companyData;
            i++;
          }
          break;
        case 2:
          if(+companyData.riskXinXiCount>0){
            res[i] = companyData;
            i++;
          }
          break;
        case 3:
          if(+companyData.riskCaiWuCount>0){
            res[i] = companyData;
            i++;
          }
          break;
        case 4:
          if(+companyData.riskQiTaCount>0){
            res[i] = companyData;
            i++;
          }
          break;

      }

    }
    companyObj.data = res;
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
    let szMapData = {  //深圳市
      negativeCompanyList: { map:[], data:[] },  //数据
      riskCaiWuCount:jsonData.riskCaiWuCount,
      riskZhiLiCount:jsonData.riskZhiLiCount,
      riskXinXiCount:jsonData.riskXinXiCount,
      riskQiTaCount:jsonData.riskQiTaCount,
    };
    for(let aData of jsonData.regionList){
      let tMapData = {
        negativeCompanyList: { map:{}, data:[] },  //负面
        riskCaiWuCount:aData.riskCaiWuCount,
        riskZhiLiCount:aData.riskZhiLiCount,
        riskXinXiCount:aData.riskXinXiCount,
        riskQiTaCount:aData.riskQiTaCount,
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
  getGeogMap2(){
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
      let tempKey = '深圳市';
      let riskType = -1;
      this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey),tempKey,-1);
      this.cityCompanyList = this.getCityCompanyList(tempKey);
      this.cityNegative = this.getSZNegativeData(tempKey,riskType);
    });
  }
  //初始化Chart图
  initChart(mapData,geogMapMap,regionName,riskType:number,symbolSize?){
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
    this.setChartRegisterMap(geogMapMap);
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
            /*
            { key:1,value:'治理风险'},
    { key:2,value:'信息披露'},
    { key:3,value:'财务会计'},
    { key:4,value:'其他'},*/
            let ratingSrc = [];
            if((+value.riskZhiLiCount )>0){
              ratingSrc.push(Base.riskTypeList[1].value);
            }
            if((+value.riskXinXiCount)>0){
              ratingSrc.push(Base.riskTypeList[2].value);
            }
            if((+value.riskCaiWuCount)>0){
              ratingSrc.push(Base.riskTypeList[3].value);
            }
            if((+value.riskQiTaCount)>0){
              ratingSrc.push(Base.riskTypeList[4].value);
            }
            res =
              `<div class="col-lg-12 padding-left-right_0">
                <span class="col-lg-12 cursor-pointer padding-left-right_0" id="companyClick" > <a onclick="window.open('#/lfs/company/id/${value.companyId}')" title="${value.companySnm} 「${value.securityCd}」" >${params.marker} ${value.companySnm} 「${value.securityCd}」 </a>
                ${ratingSrc}</span>
             </div>`;
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
          data:this.convertData(this.convertRiskTypeDataList(this.convertDataList(mapData.negativeCompanyList,false),riskType)),
          // symbolSize:symbolSize, // 点的大小
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
              color:'#303F9F',
            }
          }
        },
        {
          name:Base.comRiskLevelList.risk1.text,
          type:'effectScatter',  //effectScatter
          coordinateSystem:'geo',
          data:this.convertData(this.convertRiskTypeDataList(this.convertDataList(mapData.negativeCompanyList,true),riskType)),
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
            emphasis:{
              borderColor:'rgba(255,255,255,0.1)',
              borderWidth:20,
            },
            normal:{
              color:'#303F9F',
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



  selectRiskClass = 0; //1 财务风险  //2 治理风险    //3 市场风险 //4 经营风险  //5 法律法规
  cityNegative = {
    riskType:-1,
    regionName:'深圳市',
    riskCaiWuCount: 0,
    riskZhiLiCount: 0,
    riskXinXiCount: 0,
    riskQiTaCount: 0,
  };  //深圳市预警数 && 负面数

  //选择风险类型
  setSelectRisk(type){
    this.selectRiskClass = type;
    let name =  this.cityNegative.regionName;//获取当前区名
    if(name ==  '深圳'){
      name =  '深圳市';
    }
    this.initChart(this.getAllBaseMap(name),this.getGeogMapMap(name),name,type);
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


  //选中公司
  selectCompany = {
    companySnm:'',
    securityCd:'',
    title:"", //风险概述
  };
  companyDistribute = {
    riskType:[],
    title:[],
  };//公司维度信息
  //自定义map点击事件处理
  private forAnClick(data,ev?){
    this.selectMapSize = 1;
    this.selectCompany = data;
    let riskType = this.selectRiskClass;
    let name =  this.cityNegative.regionName;//获取当前区名
    if(name ==  '深圳'){
      name =  '深圳市';
      riskType = -1;
    }
    this.cityNegative = this.getSZNegativeData(name,riskType);
    this.initChart(this.getAllBaseMap(name),this.getGeogMapMap(name),name,riskType);

    this.regionRiskApiService.getCompanyDimensionDistributeMap(data.companyId).subscribe(geoJson => {
      if(geoJson.code == '0'){
        this.companyDistribute = geoJson.data['content'];

      }else if(geoJson.code == '1'){

      }else if(geoJson.code == '-1'){

      }
    } )
  }


  //初始化深圳
  getInitMapData(){
    let riskType = -1;
    this.selectRiskClass = riskType;
    this.selectMapSize = 0;
    let tempKey = '深圳市';
    this.cityNegative = this.getSZNegativeData(tempKey,riskType);
    this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey),tempKey,riskType);
  }

  //跳转公司展台
  goCompanyInfo(companyId,$event?){
    if($event!=undefined){
      $event.stopPropagation();
    }
    window.open(`#/lfs/company/id/${companyId}`);
  }

}
