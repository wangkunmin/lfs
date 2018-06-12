import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PublicModule } from './public.module';
import { PublicRoutingModule } from './public-routing.module';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MenuComponent } from './menu/menu.component';
import { SettingApiService } from '../common/api/setting-api.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PublicModule,
    PublicRoutingModule
  ],
  declarations: [
    HomeComponent,    // 首页
    LoginComponent,   // 登录
    RegisterComponent,// 注册
    MenuComponent     // 菜单
  ],
  providers: [
    SettingApiService
  ]
})
export class PublicTwoModule { }
