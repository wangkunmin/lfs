import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { UserManageIn } from '../model/user-manage-in';
import {CompanySuperviseIn} from "../model/company-supervise-in";
import {UserAttentionIn} from "../model/user-attention-in";
import {FlsHttpService} from "./http/fls-http.service";
import {CompanyBaseInfo, CompanyRiskIn, RiskRateIn} from "../model/company-aintain";

@Injectable()
export class ManageApiService {

  constructor(
    private http: FlsHttpService
  ) { }

  getUserMaintain(userManageIn: UserManageIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userManagement_userMaintain}`;
    return this.http.post<BaseApiResponseModel>(url, userManageIn, HttpOptions);
  }

  editUserMaintain(userManageIn: UserManageIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userManagement_editUserMaintain}`;
    return this.http.post<BaseApiResponseModel>(url, userManageIn, HttpOptions);
  }

  deleteUser(id: number): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userManagement_deleteUser}${id}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  indCompySuperviseInfo(userAttention?: UserAttentionIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userSupervise_findCompySuperviseInfo}`;
    return this.http.post<BaseApiResponseModel>(url, userAttention, HttpOptions);
  }

  operationSupervise(companySupervise: CompanySuperviseIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userSupervise_operationSupervise}`;
    return this.http.post<BaseApiResponseModel>(url, companySupervise, HttpOptions);
  }


  //查询深圳所有上市公司风险评级信息
  findAllRiskRateInfo(page: number,pageSize:number,companyNm): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_findAllRiskRateInfo}/${page}/${pageSize}/${companyNm}`;
    return this.http.get<BaseApiResponseModel>(url);
  }
  //查询单个公司风险评级信息 /findRiskRateInfo/{companyId}
  findRiskRateInfo(companyId): Observable<any> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_findRiskRateInfo}/${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }
  //编辑风险评级信息
  editRiskRateInfo(riskRate: RiskRateIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_editRiskRateInfo}`;
    return this.http.post<BaseApiResponseModel>(url, riskRate, HttpOptions);
  }
  //查询深圳所有上市公司风险类型信息
  findAllRiskInfo(page: number,pageSize:number,companyNm): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_findAllRiskInfo}/${page}/${pageSize}/${companyNm}`;
    return this.http.get<BaseApiResponseModel>(url);
  }
  //查询单个公司风险类型信息 /findRiskRateInfoById/{companyRiskId}
  findRiskRateInfoById(companyRiskId): Observable<any> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_findRiskRateInfoById}/${companyRiskId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }
  //编辑风险类型信息
  editRiskInfo(companyRiskIn?: CompanyRiskIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_editRiskInfo}`;
    return this.http.post<BaseApiResponseModel>(url, companyRiskIn, HttpOptions);
  }
  //添加风险类型信息
  addRiskInfo(companyRiskIn: CompanyRiskIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_addRiskInfo}`;
    return this.http.post<BaseApiResponseModel>(url, companyRiskIn, HttpOptions);
  }
  //删除风险类型信息
  removeRiskInfo(companyRiskId): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_removeRiskInfo}/${companyRiskId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  //查询公司信息
  queryCompanyInfo(keyWord): Observable<any> {
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_queryCompanyInfo}${keyWord}`;
    return this.http.get<any>(url);
  }


  //查询深圳所有上市公司基本信息
  findAllCompanyBaseInfo(page: number,pageSize:number,companyNm): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_findAllCompanyBaseInfo}/${page}/${pageSize}/${companyNm}`;
    return this.http.get<BaseApiResponseModel>(url);
  }
  //查询单个公司基本信息 /findRiskRateInfo/{companyId}
  findCompanyBaseInfoById(companyId): Observable<any> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_findCompanyBaseInfoById}/${companyId}`;
    return this.http.get<BaseApiResponseModel>(url);
  }
  //编辑公司基本信息
  editCompanyBaseInfo(riskRate: CompanyBaseInfo): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.manage_editCompanyBaseInfo}`;
    return this.http.post<BaseApiResponseModel>(url, riskRate, HttpOptions);
  }
}
