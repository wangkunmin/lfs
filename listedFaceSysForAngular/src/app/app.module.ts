import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PublicModule } from './public/public.module';
import { PublicTwoModule } from './public/public-two.module';

import { AppRoutingModule } from './app-routing.module';

import { CommonUtil } from './common/utill/common-util';
import { LocalStorageService } from './common/service/localStorage.service';
import { LoginService } from './common/service/login.service';
import { HeaderService } from './common/service/header.service';
import { ParamsService } from './common/service/params.service';
import { NoopInterceptor } from './noop-interceptor';


@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    PublicModule,
    PublicTwoModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NoopInterceptor,
      multi: true
    },
    CommonUtil,
    LoginService,
    HeaderService,
    ParamsService,
    LocalStorageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
