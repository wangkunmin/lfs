import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MenuComponent } from './menu/menu.component';

import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: '',
        component: LoginComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      }
    ]
  },
  {
    path: 'lfs',
    canActivate: [AuthGuard],
    component: MenuComponent,
    children: [
      {
        path: 'region',   // 区域风险总览
        loadChildren: 'app/region-risk/region-risk.module#RegionRiskModule'
      },
      {
        path: 'group',    // 分组风险总览
        loadChildren: 'app/group-risk/group-risk.module#GroupRiskModule'
      },
      {
        path: 'company',
        loadChildren: 'app/company-risk/company-risk.module#CompanyRiskModule'
      },
      {
        path: 'atlas',    // 关联图谱
        loadChildren: 'app/atlas-map/atlas-map.module#AtlasMapModule'
      },
      {
        path: 'user',    // 关联图谱
        loadChildren: 'app/user-manage/user-manage.module#UserManageModule'
      }
    ]
  },
  {
    path: 'Atlas',    // 关联图谱
    loadChildren: 'app/atlas-map/atlas-map.module#AtlasMapModule'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    AuthGuard
  ]
})
export class PublicRoutingModule { }
