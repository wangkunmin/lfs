import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { NegativeNewsIn } from '../model/negative-news-in';
import { WarningRiskIn } from '../model/warning-risk-in';
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class RegionRiskApiService {

  constructor(
    private http: FlsHttpService,
    private angularHttp: HttpClient
  ) { }

  // 监测预警风险TOP
  getWarningTop(warningRiskIn: WarningRiskIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_warningTop}`;
    return this.http.post<BaseApiResponseModel>(url, warningRiskIn, HttpOptions);
  }

  // 负面新闻跟踪
  getNewsTrack(negativeNewsIn: NegativeNewsIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_lastingBondViolation}`;
    return this.http.post<BaseApiResponseModel>(url, negativeNewsIn, HttpOptions);
  }

  // 负面新闻跟踪详情
  getNewsDetail(companyId: number, newsCode: string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_newsContent}${companyId}/${newsCode}`;
    return this.http.get<BaseApiResponseModel>(url);
  }


  //监测预警趋势图
  getTrendChart(startTime,endTime): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_trendWarningChart}`+'/'+startTime+'/'+endTime;
    return  this.http.get<BaseApiResponseModel>(url);
  }

  //监测预警趋势一个月信息
  getTrendWarningSingle(): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_trendWarningChartSingle}`;
    return  this.http.get<BaseApiResponseModel>(url);
  }

  //获取当天新闻信息
  getNewsChartByDate(body:any): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_newsChartByDate}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }

  //新闻折线图
  getNewsCharts(body:any): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_newsCharts}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }



  //深圳市数据
  getGeographySZCityData(): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_geographySZCityData}`;
    return  this.http.get<BaseApiResponseModel>(url);
  }


  //深圳市各个区数据
  getGeographyAllCompanyData(): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_geographyAllCompanyData}`;
    return  this.http.get<BaseApiResponseModel>(url);
  }

  //深圳市十个区地图Json
  getGeographyChartsMap(): Observable<BaseApiResponseModel>{
    const url = ApiUrl.regionRisk_geographyChartsMap;
    return  this.angularHttp.get<BaseApiResponseModel>(url);
  }



  //深圳市评级数据
  getRiskDistributeMap(): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_riskDistributeMap}`;
    // const url = '/assets/dataJson/map1.json';
    return  this.http.get<BaseApiResponseModel>(url);
  }
  //深圳市负面信息数据
  getNegativeDistributeMap(userId,eventDate): Observable<BaseApiResponseModel>{
    // const url = '/assets/dataJson/map2.json';
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_negativeDistributeMap}`+'/'+userId+'/'+eventDate;
    return  this.http.get<BaseApiResponseModel>(url);
  }
  //深圳市负面信息数据
  negativeDistributeRecord(negativeInfoRecordInData:NegativeInfoRecordInData): Observable<BaseApiResponseModel>{
    // const url = '/assets/dataJson/map2.json';
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_negativeDistributeRecord}`;
    return this.http.post<BaseApiResponseModel>(url, negativeInfoRecordInData, HttpOptions);
  }

  //深圳市风险维度
  getDimensionDistributeMap(): Observable<BaseApiResponseModel>{
    // const url = '/assets/dataJson/map3.json';
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_riskDimensionMap}`;
    return  this.http.get<BaseApiResponseModel>(url);
  }

  //公司风险维度
  getCompanyDimensionDistributeMap(companyId): Observable<BaseApiResponseModel>{
    // const url = '/assets/dataJson/map3.json';
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_riskCompanyInfo}`+'/'+companyId;
    return  this.http.get<BaseApiResponseModel>(url);
  }

  //公告风险详情链接
  getEventRiskDetail(assign_infoCd):Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_eventRiskDetail}`+'/'+assign_infoCd;
    return  this.http.get<BaseApiResponseModel>(url);
  }
}

class NegativeInfoRecordInData{
  companyId:string;
  userId:string;
  announceCds:string;
  newsCds:string;
  eventDate:string;
}
