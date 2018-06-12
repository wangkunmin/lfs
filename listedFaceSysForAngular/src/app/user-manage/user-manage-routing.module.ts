import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './manage/account.component';
import { SettingComponent } from './setting/setting.component';
import { ManageComponent } from './manage/manage.component';
import { WatchComponent } from './manage/watch.component';
import {LevelTypeComponent} from "./manage/companyMaintain/level-type.component";
import {RiskMaintainComponent} from "./manage/companyMaintain/risk-maintain.component";
import {CompanyBaseInfoComponent} from "./manage/companyBaseInfo/company-base-info.component";
import {AuthGuard} from "../auth.guard";


const routes: Routes = [
  {
    path: 'setting',
    component: SettingComponent
  },
  {
    path: 'manage',
    component: ManageComponent,
    canActivate:[AuthGuard],
    children: [
      {
        path: 'account',
        component: AccountComponent
      },
      {
        path: 'watch',
        component: WatchComponent
      },
      {
        path: 'levelMaintain',
        component: LevelTypeComponent
      },
      {
        path: 'riskMaintain',
        component: RiskMaintainComponent
      },
      {
        path: 'companyBaseInfo',
        component: CompanyBaseInfoComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserManageRoutingModule { }
