import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpErrorResponse,HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {ParamsService} from "./common/service/params.service";
import "rxjs/add/operator/catch";
import {LocalStorageService} from "./common/service/localStorage.service";
import {Router} from '@angular/router';
import {CompanyRiskInfo} from "./common/model/company-risk-info";
import "rxjs/add/operator/do";

@Injectable()
export class NoopInterceptor implements HttpInterceptor {

  constructor(
    private paramsService: ParamsService,
    private lg:LocalStorageService,
    private router: Router,
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


    let headerOptions={'Token': this.lg.get('token')?this.lg.get('token'):"",
                        'TokenUserid':this.lg.get("userInfo")?this.lg.get("userInfo").userId:""};
    let reqOptions = {
      headers: new HttpHeaders(headerOptions)
    };
    const authReq = request.clone(reqOptions)
    console.log("request********" , authReq);

    return next.handle(authReq).
      do((data:HttpEvent<any>)=> {
        if (data instanceof HttpResponse){
      //登录、注册以及本地json取值的不重置token，其他调接口则重置token，以操作画面了则重新计算token时间
      if (data.url.indexOf("/ifcUser/login")<0 && data.url.indexOf("/ifcUser/register")<0
        && data.url.indexOf("assets/")<0
        && data.url.indexOf("ifcUser/findAccountName")<0) {
        let new_token = data.headers.get('new_token');
        console.log("next token********" ,  this.lg.get('token'));
        console.log("next new_token********" ,  new_token);
        if(this.lg.get('token')!=null && new_token!=null){
          this.lg.set('token',new_token);
        }
      }
    }},(err:any)=>{
      if (err instanceof HttpErrorResponse){
        this.lg.set('token',null);
        this.lg.set('userInfo',null);
        this.router.navigate(['home/login']);
        console.log("err********" , err);
        return Observable.throw(err);
      }
    })
    /*console.log("request********" + authReq);
    const observer = next.handle(authReq);
    const sub = {
      next:data=>{
        if (data instanceof HttpResponse){
          console.log("next********" + data);

          //登录、注册、注册用户名校验以及本地json取值的不重置token，其他调接口则重置token，以操作画面了则重新计算token时间
          if (data.url.indexOf("/ifcUser/login")<0 && data.url.indexOf("/ifcUser/register")<0
          && data.url.indexOf("assets/")<0
          && data.url.indexOf("ifcUser/findAccountName")<0
          ) {
            let new_token = data.headers.get('new_token');
            console.log("next token********" +  this.lg.get('token'));
            console.log("next new_token********" +  new_token);
            this.lg.set('token',new_token);
          }
        }},
      error:err=>{
        if (err instanceof HttpErrorResponse){
          this.lg.set('token',null);
          this.lg.set('userInfo',null);
          this.router.navigate(['home/login']);
          console.log("err********" + err);
      }},
      complete:()=>{
        console.log("complete********" + 2222222);
      }
    }
    observer.subscribe(sub);

    return observer;*/

  }
}
