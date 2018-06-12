import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import {
  BsDropdownModule, BsDatepickerModule, ModalModule
} from 'ngx-bootstrap';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { zhCnLocale } from 'ngx-bootstrap/locale';
defineLocale('zh-cn', zhCnLocale);

import { ErrorComponent } from './error/error.component';
import { HeaderComponent } from './header/header.component';
import { FollowComponent } from './follow/follow.component';
import { ConfirmComponent } from './confirm/confirm.component';
import { MapTransPipe } from '../common/pipe/map-trans.pipe';
import { TimeLineComponent } from "../region-risk/time-line/timeLine.component";
import { DatepickerComponent } from './datepicker/datepicker.component';

import { PublicApiService } from '../common/api/public-api.service';
import { UserApiService } from '../common/api/user-api.service';
import {FlsHttpService} from "../common/api/http/fls-http.service";
import {LfsDatePipe} from "../common/pipe/lfs-date.pipe";
import {LfsTypesListPipe} from "../common/pipe/lfs-types-list.pipe";
import {ConfirmMessageComponent} from "./confirm-message/confirm-message.component";
import {LoadingComponent} from "./loading/loading.component";


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    BsDatepickerModule.forRoot(),
    ModalModule.forRoot()
  ],
  declarations: [
    ErrorComponent,   // 报错
    HeaderComponent,  // 头部
    FollowComponent,
    ConfirmComponent,
    ConfirmMessageComponent,
    TimeLineComponent,//  时间轴
    DatepickerComponent,
    LoadingComponent, //loading加载
    MapTransPipe,
    LfsDatePipe,
    LfsTypesListPipe
  ],
  providers: [
    FlsHttpService,
    PublicApiService,
    UserApiService
  ],
  exports: [
    MapTransPipe,
    LfsDatePipe,
    LfsTypesListPipe,
    HeaderComponent,
    ErrorComponent,
    FollowComponent,
    ConfirmComponent,
    ConfirmMessageComponent,
    TimeLineComponent,    //  时间轴
    DatepickerComponent,
    LoadingComponent //loading加载
  ],
  entryComponents: [
    ConfirmComponent,
    ConfirmMessageComponent
  ]
})
export class PublicModule { }
