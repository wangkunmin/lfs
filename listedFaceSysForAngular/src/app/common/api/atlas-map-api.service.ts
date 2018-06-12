import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import {ApiUrl, HttpOptions} from '../constant/api-url.const';
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class AtlasMapApiService {

  constructor(
    private http: FlsHttpService
  ) { }

  // 图谱公司
  sendAtlas(param: string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.chart_company}${param}`;
    return this.http.get<any>(url);
  }
  //根据关键字，查询公司基本信息
  findCompanyData(keyword: string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.atlas_companySearch}${keyword}`;
    return this.http.get<any>(url);
  }

  // 图谱公司
  iscompany(id: number): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.chart_iscompany}${id}`;
    return this.http.get<any>(url);
  }

  //查找关系
  getRelationship(keyword: string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.atlas_relationship}${keyword}`;
    return this.http.get<any>(url);
  }

  //根据批量导入的关键字字符串，查询公司基本信息
  batchCompanySearch(keyword: string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.atlas_batchCompanySearch}${keyword}`;
    return this.http.get<any>(url);
  }

  //根据关键字，查询公司详细信息
  findCompanyDelData(userId,page: string,keyword:string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.atlas_warningSearch}${userId}/${keyword}/${page}`;
    return this.http.get<any>(url);
  }

  // 关联关系
  searchShip(param: string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.chart_ship}${param}`;
    return this.http.get<any>(url);
  }
}
