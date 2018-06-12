import {Component, OnInit, TemplateRef} from '@angular/core';

import { NgxEchartsService } from 'ngx-echarts';
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import {Base} from "../../common/constant/api-base.const";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-geography',
  templateUrl: './geography.component.html',
  styleUrls: ['./geography.component.css']
})
export class GeographyComponent implements OnInit {
  selectMapSize = 0; //0 深圳市  1 非深圳市

  tabList = [
    {key:0,value:'风险分类'},
    {key:2,value:'风险维度'},
    {key:1,value:'负面事件'},
  ];
  selectTab = 0;

  cityRiskLevel:any={  //深沪各类公司评级数据
    regionName:'深圳市',
    highRiskCount:0, //高风险
    mediumRiskCount:0,  //次高风险
    focusCount:0,   //关注
    lowRiskCount:0,   //低风险
  };


  options = {};

  constructor(
    private regionRiskApiService : RegionRiskApiService,
    private es: NgxEchartsService,
    private commonUtil : CommonUtil,
  ) { }

  subtextSrc ='';
  ngOnInit() {
    this.getRiskDistribute();
    let date = new Date();
    let m = date.getMonth()+1;
    let d = date.getDate();
    this.subtextSrc = date.getFullYear()+ '-'+(m<10?'0'+m:m);
    // this.subtextSrc = "2018-04-27";
  }

  echartsIntance;
  onChartInit(ec) {
    this.echartsIntance = ec;
  }

  geogMapMap = {};

  //获取深沪各区公司评级数据
  getCityRiskLevel(key){
    let riskLevel = new RiskLevel();
    riskLevel = this.getAllBaseMap(key);
    riskLevel.regionName =key;
    return riskLevel;
  }

  //点击tab切换
  clickTab(type,selectTab){
    this.selectTab =type;
    this.getInitMapData();

    let date = new Date();
    let m = date.getMonth()+1;
    let d = date.getDate();
    if(type == 1){
      this.subtextSrc = date.getFullYear()+ '-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d);
      // this.subtextSrc = "2018-04-27";
    }else{
      this.subtextSrc = date.getFullYear()+ '-'+(m<10?'0'+m:m);
    }
  }

  //获取 深圳市 所有区公司评级数据
  getRiskDistribute(){
    this.regionRiskApiService.getRiskDistributeMap().subscribe(geoJson => {
        if(geoJson.code == '0'){
          this.ratingData(geoJson.data['content']);
        }else if(geoJson.code == '1'){

        }else if(geoJson.code == '-1'){

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

  //所有评级坐标 数据
  allBaseMap = {};
  //获取深圳市 或其 各区地图数据
  getGeogMapMap(key){
    return this.geogMapMap[key];
  }
  //设置eChart加载地图
  setChartRegisterMap(geoJson){
    this.es.registerMap('SZ', geoJson);
  }
  //获取区的公司评级信息
  getAllBaseMap(key){
    return this.allBaseMap[key];
  }
  //评级数据处理
  ratingData(jsonData){
    if(jsonData == null){
      return null;
    }
    let szMapData = {  //深圳市
      riskLevel: { map:[], data:[] },  //负面数据
      highRiskCount:jsonData.highRiskCount, //高风险
      mediumRiskCount:jsonData.mediumRiskCount,  //次高风险
      focusCount:jsonData.focusCount,   //关注
      lowRiskCount:jsonData.lowRiskCount,   //低风险
    };
    for(let aData of jsonData.regionList){
      let tMapData = {
        riskLevel: { map:{}, data:[] },  //负面
        highRiskCount:aData.highRiskCount, //高风险
        mediumRiskCount:aData.mediumRiskCount,  //次高风险
        focusCount:aData.focusCount,   //关注
        lowRiskCount:aData.lowRiskCount,   //低风险
      };
      if(aData.companyInfos !=null ){
        for(let companyInfo of aData.companyInfos){
          let tRiskData:any = {};
          tRiskData = companyInfo;
          tRiskData.name = companyInfo.companySnm.trim();

          tMapData.riskLevel.map[companyInfo.companySnm.trim()] = companyInfo.postion;
          tMapData.riskLevel.data.push({});
          tMapData.riskLevel.data[tMapData.riskLevel.data.length-1] = tRiskData;

          szMapData.riskLevel.map[companyInfo.companySnm.trim()] = companyInfo.postion;
          szMapData.riskLevel.data.push({});
          szMapData.riskLevel.data[szMapData.riskLevel.data.length-1] = tRiskData;

        }
        this.allBaseMap[aData.regionName] = tMapData;
      }
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
      this.cityRiskLevel = this.getCityRiskLevel(tempKey);
      this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey));
    });
  }
  //初始化Chart图
  initChart(mapData,geogMapMap,symbolSize?){
    let extraCssText ='box-shadow: 0 12px 22px 0 rgba(0,0,0,0.24);padding:0;padding-top:4px;margin-top: 5px;';
    let colorList = ['rgba(255, 255, 255)','#E64A19','#FF9F00','#FFD300','#4CAF50'];
    if(symbolSize == undefined){
      symbolSize = 12;
    }
    this.setChartRegisterMap(geogMapMap);

    let emphasisAreaColor = '';
    if(this.cityRiskLevel.regionName == '深圳市'){
      emphasisAreaColor = '#D6E4F6';
    }else{
      emphasisAreaColor = 'rgba(51,56,105,0.1)';
    }

    this.options = {
      title: {
        // subtext: subtextSrc,
        right:25
      },
      tooltip: {
        trigger: 'item',
        position:'bottom',
        extraCssText:extraCssText,
        enterable:true, //可进入tooltip交互
        backgroundColor: '#ffffff',
        borderWidth:0,
        formatter: function (params,ticket,callback) {
          let value = params.value[2];
          let res = '';
          if(params.seriesName == Base.cityName_SZ){
            //使用for可以将需要的数据全部加到res
            //注意下边的<br/>
          }else{
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
              `<div class="col-lg-12 padding-left-right_0" style="min-width: 230px">
                <span class="col-lg-12  cursor-pointer padding-left-right_0 " id="companyClick" onclick="window.open('#/lfs/company/id/${value.companyId}')" title="${value.companySnm} 「${value.securityCd}」" ><a >${params.marker} ${value.companySnm} 「${value.securityCd}」</a>
                ${ratingSrc}
                </span>
             </div>`;
            res = `<div class="m-message-bottom-box geography-tooltip" value="${JSON.stringify(value).replace(/"/g,"'")}"> <div class="">${res}</div></div>`;
          }

          return res;
        },
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
          // geoIndex:0,
          data:this.convertData(mapData.riskLevel),
          symbolSize:symbolSize, // 点的大小
         /* label:{
            narmal:{
              formatter:'{b}',
              position:'right',
              show:true
            }
          },*/
          itemStyle:{
            normal:{
              color:function (params) {
                let rating =  +params.value[2].rating;
                if(rating == 1){ //高风险
                  return colorList[rating];
                }else if(rating == 2){ //次高风险
                  return colorList[rating];
                }else if(rating == 3){ //关注
                  return colorList[rating];
                }else if(rating == 4){ //正常
                  return colorList[rating];
                }else {
                  return  colorList[0];
                }

              },
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




  //点击EChart图
  clickChart(e,p?){
    let tempKey = '';
    if(e.name == this.cityRiskLevel.regionName){
      tempKey = '';
    }else{
      if(e.seriesName == Base.cityName_SZ) {
        tempKey = e.name;
      }else if(e.szCity == 0){

      }
    }
    if(tempKey != ''){
      this.cityRiskLevel = this.getCityRiskLevel(tempKey);
      this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey));
    }
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
    riskCaiWuCount: 0,
    riskZhiLiCount: 0,
    riskXinXiCount: 0,
    riskQiTaCount: 0,
  };
  //自定义map点击事件处理
  private forAnClick(data,ev?){
    this.selectMapSize = 1;
    this.selectCompany = data;
    let name =  this.cityRiskLevel.regionName;//获取当前区名
    if(name ==  '深圳'){
      name =  '深圳市';
    }
    this.initChart(this.getAllBaseMap(name),this.getGeogMapMap(name));
  }

  //初始化深圳
  getInitMapData(){
    this.selectMapSize = 0;
    let tempKey = '深圳市';
    this.cityRiskLevel = this.getCityRiskLevel(tempKey);
    this.initChart(this.getAllBaseMap(tempKey),this.getGeogMapMap(tempKey));
  }

  //跳转公司展台
  goCompanyInfo(companyId,$event?){
    if($event!=undefined){
      $event.stopPropagation();
    }
    window.open(`#/lfs/company/id/${companyId}`);
  }

}


class RiskLevel{
  regionName:string;
  highRiskCount:number; //高风险
  mediumRiskCount:number;  //次高风险
  focusCount:number;   //关注
  lowRiskCount:number;   //低风险
}
