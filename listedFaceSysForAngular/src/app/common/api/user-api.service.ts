import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { User } from '../model/user';
import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { UserAttentionIn } from '../model/user-attention-in';
import {FlsHttpService} from "./http/fls-http.service";

@Injectable()
export class UserApiService {

  constructor(
    private http: FlsHttpService
  ) { }

  addAttention(userAttentionIn: UserAttentionIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.ifcUser_addAttention}`;
    return this.http.post<BaseApiResponseModel>(url, userAttentionIn, HttpOptions);
  }

  deleteAttention(userAttentionIn: UserAttentionIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.ifcUser_deleteAttention}`;
    return this.http.post<BaseApiResponseModel>(url, userAttentionIn, HttpOptions);
  }
}
