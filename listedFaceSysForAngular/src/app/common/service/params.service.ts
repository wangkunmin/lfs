import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';


@Injectable()
export class ParamsService {
  private earlyWarningSubject = new Subject<string>();
  private focusCompanySubject = new Subject<any>();
  private tokenSubject = new Subject<any>();

  private fleshFCSubject = new Subject<any>();

  constructor() { }

  sendEarlyWarning(year:string) {
    this.earlyWarningSubject.next(year);
  }

  getEarlyWarning(): Observable<string> {
    return this.earlyWarningSubject.asObservable();
  }


  sendFocusCompany(value:any) {
    this.focusCompanySubject.next(value);
  }

  getFocusCompany(): Observable<any> {
    return this.focusCompanySubject.asObservable();
  }

  sendToken(value: any) {
    this.tokenSubject.next(value);
  }

  getToken(): Observable<any> {
    return this.tokenSubject.asObservable();
  }


  private headerSubject = new Subject<any>();
  sendHeaderSubject(value: any) {
    this.headerSubject.next(value);
  }

  getHeaderSubject(): Observable<any> {
    return this.headerSubject.asObservable();
  }

  private findCompanySubject = new Subject<any>();
  sendFindCompanySubject(value: any) {
    this.findCompanySubject.next(value);
  }

  getFindCompanySubject():Observable<any> {
    return this.findCompanySubject.asObservable();
  }


  sendFleshFC(value:any) {
    this.fleshFCSubject.next(value);
  }

  getFleshFC(): Observable<any> {
    return this.fleshFCSubject.asObservable();
  }
}
