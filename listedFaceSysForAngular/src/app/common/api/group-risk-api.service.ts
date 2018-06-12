import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { NegativeNewsIn } from '../model/negative-news-in';
import { UserFollowIn } from '../model/user-follow-in';
import {RiskInData} from "../model/risk-in-data";
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class GroupRiskApiService {

  constructor(
    private http: FlsHttpService
  ) { }

  //获取当天舆情新闻信息
  getNewsChartByDate(body:any): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_newsChartByDate}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }

  //舆情新闻折线图
  getNewsCharts(body:any): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_newsCharts}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }

  //获取重点关注公司-风险TOP10
  getFollowTop(body:any): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_followCompany}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }

  //获取重点关注公司-风险TOP10
  getFollowTop2(body:any): Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_getShortCutWarningTop}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }

  // 舆情新闻跟踪
  getNewsTrack(negativeNewsIn: NegativeNewsIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_newsConsensus}`;
    return this.http.post<BaseApiResponseModel>(url, negativeNewsIn, HttpOptions);
  }

  // 查询用户已关注的公司信息
  findFollowedCompany(userFollowIn: UserFollowIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userAttention_findAllFollowedCompyInfo}`;
    return this.http.post<BaseApiResponseModel>(url, userFollowIn, HttpOptions);
  }

  // 查询用户未关注的公司信息
  findNotFollowedCompany(userFollowIn: UserFollowIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userAttention_findNotFollowCompyInfo}`;
    return this.http.post<BaseApiResponseModel>(url, userFollowIn, HttpOptions);
  }

  // 事件公告风险
  getEventRisk(negativeNewsIn: NegativeNewsIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_eventRisk}`;
    return this.http.post<BaseApiResponseModel>(url, negativeNewsIn, HttpOptions);
  }

  // 单个公司事件公告风险
  getSingleEventRisk(riskInData:RiskInData):Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_singleEventRisk}`;
    return this.http.post<BaseApiResponseModel>(url, riskInData, HttpOptions);
  }
  //公告风险详情链接
  getEventRiskDetail(assign_infoCd):Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_eventRiskDetail}`+'/'+assign_infoCd;
    return  this.http.get<BaseApiResponseModel>(url);
  }

}
