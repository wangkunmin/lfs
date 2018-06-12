import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import {User} from "../model/user";
import {UserFollowIn} from "../model/user-follow-in";
import {UserInfo} from "../model/entity";
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class SettingApiService {

  constructor(
    private http: FlsHttpService
  ) { }

  //更新用户基本信息
  updateUserInfo(user: User): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_updateUserInfo}`;
    return this.http.post<BaseApiResponseModel>(url, user, HttpOptions);
  }

  //更新密码
  updatePassword(user): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_updatePassword}`;
    return this.http.post<BaseApiResponseModel>(url, user, HttpOptions);
  }

  //分页查询已关注公司
  findFollowedCompanyInfo(user): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_findFollowedCompanyInfo}`;
    return this.http.post<BaseApiResponseModel>(url, user, HttpOptions);
  }

  //查询用户基本信息
  findUserInfo(userId): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_findUserInfo}${userId}`;
    return this.http.get<any>(url);
  }

  //验证昵称
  findUserName(uesrNm): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_findUserName}${uesrNm}`;
    return this.http.get<any>(url);
  }
  //验证用户
  findAccountName(accountNm): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_findAccountName}${accountNm}`;
    return this.http.get<any>(url);
  }
  //查询公司信息
  queryCompanyInfo(keyWord): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_queryCompanyInfo}${keyWord}`;
    return this.http.get<any>(url);
  }
  //查询用户未关注公司列表
  queryNotAttentCompyInfo(userId,keyWord): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_queryNotAttentCompyInfo}${userId}/${keyWord}`;
    return this.http.get<any>(url);
  }

  // 查询用户未关注的公司信息
  findNotFollowedCompany(userFollowIn: UserFollowIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.userAttention_findNotFollowCompyInfo}`;
    return this.http.post<BaseApiResponseModel>(url, userFollowIn, HttpOptions);
  }
  // 批量关注公司
  batchAdd(focusCompanyList: FocusCompanyList): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.setting_batchAdd}`;
    return this.http.post<BaseApiResponseModel>(url, focusCompanyList, HttpOptions);
  }

  login(userInfo:UserInfo): Observable<BaseApiResponseModel> { //登录接口
    const url = `${ApiUrl.api_uri}${ApiUrl.user_login}`;
    return this.http.post<BaseApiResponseModel>(url, userInfo, HttpOptions);
  }
}


class FocusCompanyList{
  userId:string;
  list:{
    companyId:string;
    companyNm:string;
  }[];
}
