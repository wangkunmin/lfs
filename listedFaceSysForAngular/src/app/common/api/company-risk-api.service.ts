import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { NegativeNewsIn } from '../model/negative-news-in';
import { UserFollowIn } from '../model/user-follow-in';
import {CompanyReportIn} from "../model/company-report-in";
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class CompanyRiskApiService {

  constructor(
    private http: FlsHttpService
  ) { }


  // 舆情新闻跟踪
  getNewsTrack(negativeNewsIn: NegativeNewsIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_newsConsensus}`;
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
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_companyAnnounce}`;
    return this.http.post<BaseApiResponseModel>(url, negativeNewsIn, HttpOptions);
  }

  // 主要财务数据
  getFinanceInfo(companyId: number): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.financeInfo_finance}${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // 详细财务报表
  getFinanceReport(companyReportIn: CompanyReportIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.financeInfo_cashflow}`;
    return this.http.post<BaseApiResponseModel>(url, companyReportIn, HttpOptions);
  }

  // 公司基本信息
  getCompanyInfo(companyId: string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyBaseInfo_company}${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // 管理层信息
  getControllerInfo(companyId: string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_controller}${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // 词云信息
  getWordCloud(companyId: string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_wordCloud}${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  //  新闻事件趋势图
  getCompanyRiskNewsEvent(body:any): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_newsEvent}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }

  //  新闻事件单天数据
  getCompanyRiskNewsEventByDate(body:any): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_newsEventByDate}`;
    return this.http.post<BaseApiResponseModel>(url, body, HttpOptions);
  }


  // 股东及投资信息
  getShareholder(companyId: string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_shareholder}${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // 查询用户是否已关注某公司接口
  findFollowedCompyInfoById(userId,companyId: string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.findFollowedCompyInfoById}${userId}/${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // get风险预警信息
  getRiskWarning(companyId: string,type:string,dateStart :string,dateEnd :string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_singleCompanyWR}${companyId}/${type}/${dateStart}/${dateEnd}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // get风险预警信息count数
  getRiskWarningCount(companyId: string,dateStart :string,dateEnd :string): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.companyRisk_singleCompanyWRCount}${companyId}/${dateStart}/${dateEnd}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // 缩略图
  getThumbnail(type: string): Observable<any> {
    const url = `${ApiUrl.api_uri}${ApiUrl.chart_thumbnail}${type}`;
    return this.http.get<any>(url);
  }

  //公告风险详情链接
  getEventRiskDetail(assign_infoCd):Observable<BaseApiResponseModel>{
    const url = `${ApiUrl.api_uri}${ApiUrl.groupRisk_eventRiskDetail}`+'/'+assign_infoCd;
    return  this.http.get<BaseApiResponseModel>(url);
  }
}
