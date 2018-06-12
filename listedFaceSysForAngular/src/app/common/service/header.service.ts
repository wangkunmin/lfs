import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import {HeaderParams} from "../model/headerParams";


@Injectable()
export class HeaderService {
  private headerSubject = new Subject<HeaderParams>();

  constructor() { }

  sendData(headerParams: HeaderParams) {  //触发方法
    this.headerSubject.next(headerParams);
  }

  getData(): Observable<HeaderParams> {  //监听值变化
    return this.headerSubject.asObservable();
  }
}
