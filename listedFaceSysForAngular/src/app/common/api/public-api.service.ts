import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { UserInfo } from '../model/entity';
import {User} from "../model/user";
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class PublicApiService {

  constructor(
    private http: HttpClient,
    private flsHttp: FlsHttpService
  ) { }

  login(userInfo:UserInfo): Observable<BaseApiResponseModel> { //登录接口
    const url = `${ApiUrl.api_uri}${ApiUrl.user_login}`;
    return this.http.post<BaseApiResponseModel>(url, userInfo, HttpOptions);
  }

  //根据关键字，查询公司基本信息
  findCompanyData(keyword: string): Observable<any>{
    const url = `${ApiUrl.api_uri}${ApiUrl.atlas_companySearch}${keyword}`;
    return this.flsHttp.get<any>(url);
  }
  register(user: User): Observable<BaseApiResponseModel> { //登录接口
    const url = `${ApiUrl.api_uri}${ApiUrl.user_register}`;
    return this.http.post<BaseApiResponseModel>(url, user, HttpOptions);
  }

}
