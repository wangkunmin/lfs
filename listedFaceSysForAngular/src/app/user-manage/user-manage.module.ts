import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationModule, ModalModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';
import { ClipboardModule } from 'ngx-clipboard';
import {PublicModule} from "../public/public.module";
import {UserManageRoutingModule} from "./user-manage-routing.module";
import {SettingComponent} from "./setting/setting.component";
import {ManageComponent} from "./manage/manage.component";
import { AccountComponent } from './manage/account.component';
import { WatchComponent } from './manage/watch.component';
import {ManageApiService} from "../common/api/manage-api.service";
import { AccountEditComponent } from './manage/account-edit.component';
import {SettingApiService} from "../common/api/setting-api.service";
import {UserInfoComponent} from "./setting/user-info.component";
import {FocusCompanyComponent} from "./setting/focus-company.component";
import { WatchEditComponent } from './manage/watch-edit.component';
import {AddCompanyComponent} from "./setting/add-company.component";
import {MessageCompanyComponent} from "./setting/message-company.component";
import {LevelTypeComponent} from "./manage/companyMaintain/level-type.component";
import {RiskMaintainComponent} from "./manage/companyMaintain/risk-maintain.component";
import {LevelEditComponent} from "./manage/companyMaintain/level-edit/level-edit.component";
import {RiskEditComponent} from "./manage/companyMaintain/risk-edit/risk-edit.component";
import {CompanyBaseInfoComponent} from "./manage/companyBaseInfo/company-base-info.component";
import {CompanyBaseInfoEditComponent} from "./manage/companyBaseInfo/company-base-info-edit/company-base-info-edit.component";


@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    PublicModule,
    UserManageRoutingModule,
    ClipboardModule,
    PaginationModule.forRoot(),
    ModalModule.forRoot()
  ],
  declarations: [
    SettingComponent,
    ManageComponent,
    AccountComponent,
    WatchComponent,
    LevelTypeComponent, //评级维护 一览
    LevelEditComponent, //评级维护 详情
    RiskMaintainComponent, //风险维护 一览
    RiskEditComponent, //风险维护 详情
    CompanyBaseInfoComponent, //企业信息维护
    CompanyBaseInfoEditComponent, //企业信息维护 编辑
    UserInfoComponent,
    FocusCompanyComponent,
    AccountEditComponent,
    AddCompanyComponent,
    WatchEditComponent,
    MessageCompanyComponent
  ],
  providers:[
    ManageApiService,
    SettingApiService
  ]
})
export class UserManageModule { }
