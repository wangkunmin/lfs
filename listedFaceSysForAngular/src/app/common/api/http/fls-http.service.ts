import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import {LocalStorageService} from "../../service/localStorage.service";
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class FlsHttpService {

  constructor(
    private http: HttpClient,
    private lg:LocalStorageService
  ) {

  }

  get<T>(url: string): Observable<any>{
    /*const option = {
      headers:{'Token':this.getToken()}
    };*/
    /*let headers = new  HttpHeaders();
    headers.append('Token',this.getToken());*/
    return this.http.get<T>(url);
  }
  post<T>(url: string, body: any,httpOptions:any): Observable<any>{
    //httpOptions.headers = httpOptions.headers.append('Token', this.getToken() );
    return this.http.post<T>(url, body,httpOptions);
  }

  private getToken(){
    let token = this.lg.get('token');
    return token;
  }
}
