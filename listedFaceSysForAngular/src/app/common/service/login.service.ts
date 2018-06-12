import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import {HeaderState} from "../model/headerState";


@Injectable()
export class LoginService {
  private loginSubject = new Subject<HeaderState>();

  constructor() { }

  sendLogin(headerState: HeaderState) {
    this.loginSubject.next(headerState);
  }

  getLogin(): Observable<HeaderState> {
    return this.loginSubject.asObservable();
  }
}
