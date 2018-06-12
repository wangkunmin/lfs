import {Component, EventEmitter, Input, OnInit, Output, TemplateRef} from '@angular/core';
import {ManageApiService} from "../../../../common/api/manage-api.service";
import {CompanyRiskIn, RiskRateIn} from "../../../../common/model/company-aintain";
import {Subject} from "rxjs/Subject";
import {Base} from "../../../../common/constant/api-base.const";
import {BsModalService} from 'ngx-bootstrap';
import {BaseApiResponseModel} from "../../../../common/model/base-api-response.model";
import {CommonUtil} from "../../../../common/utill/common-util";

@Component({
  selector: 'app-risk-edit',
  templateUrl: './risk-edit.component.html',
  styleUrls: ['../../manage.component.css']
})
export class RiskEditComponent implements OnInit {
  riskLevelList = [{key:1,value:'一级风险'},{key:2,value:'二级风险'},{key:3,value:'三级风险'},{key:4,value:'四级风险'}];
  modalRef;
  @Input() watch: CompanyRiskIn;
  @Input() useModel: number;
  @Output() type = new EventEmitter<number>();
  searchSrc = ''; //搜索关键字
  oldSearchSrc = ''; //保存搜索关键字
  selectState = 0;//检索状态
  findDataList = []; //查询公司所有集

  riskTypeList = [];
  watchDel:CompanyRiskIn = new CompanyRiskIn();

  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private util : CommonUtil
  ) { }

  ngOnInit() {
    this.documentClick();
    if(this.useModel == 0){
      this.watch = new CompanyRiskIn();
    }else{
      this.api.findRiskRateInfoById(this.watch.companyRiskId)
        .subscribe(
          (data: any) => {
            if (data.code === '0') {
              this.watchDel = data.data['content'];
              if(data.data.content.risksTypes!=null){
                this.watchDel.risksTypes = data.data['content'].risksTypes.trim();
              }
              return;
            }
          },
          (error: any[]) => console.log('Error: ' + error),
        );
      /*
        .subscribe((data)=>{
        if(+data.code == 0){
          this.watch = data.data.content;
          if(this.watch.riskTypes!=null){
            this.watch.riskTypes = this.watch.riskTypes.trim();
          }
        }
      })*/
    }
    for(let temp of Base.riskTypeList){
      if(temp.key !=0){
        this.riskTypeList.push(temp);
      }
    }
  }

  incheck(){

    if(this.watchDel.companyNm==''|| this.watchDel.companyNm == undefined){
      this.message2='请选择公司！';
      return false;
    }
    if(this.watchDel.riskType==0 || this.watchDel.riskType == undefined){
      this.message2='请选择风险类型！';
      return false;
    }
    if(this.watchDel.risksTypes==''|| this.watchDel.risksTypes == undefined){
      this.message2='请选择风险类别！';
      return false;
    }
    if(this.util.getStringBytesLength(this.watchDel.risksDetail)>600){
      this.message2 = '风险详情不能超过300个字！';
      return false;
    }

    return true;
  }

  //监听键盘输入事件
  keyUpFun(ev,searchSrc){
    if((searchSrc+'').trim() == ''){ //不输入关键字 //
      this.selectState = 0;
      this.findDataList = [];
      return ;
    }
    if(this.oldSearchSrc != searchSrc.trim()){
      this.findCompanyData(searchSrc);
    }
    this.oldSearchSrc = searchSrc.trim();

  }

  //点击公司 打开公司展台
  clickCompany(companyInfo){
    this.selectState  = 0;
    this.watchDel.companyId = companyInfo.companyId;
    this.watchDel.companyNm = companyInfo.companyNm;
  }


  private findCompanyData(keyWord){
    if((keyWord+'').trim() == ''|| keyWord == undefined || keyWord  == null){
      this.findDataList = [];
      this.selectState = 0;
      return ;
    }
    if(this.util.isSpecialStr((keyWord+'').trim())){
      return false;
    }
    this.selectState = 0;
    this.api.queryCompanyInfo((keyWord+'').trim())
      .subscribe(
        (data: any) => {
          this.selectState = 1;
          if(data.code == 0){
            this.findDataList =  data.data['content'];
          }else if(data.code == 1){
            this.findDataList = [];
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }


  isShowFindData(type:number){
    this.selectState = type;
  }

  // 监听页面回车
  documentClick(){
    let subject = new Subject<string>(); //创建观察者
    document.onclick=(ev => { //点击回车事件
      let target:any = ev.target;
      if(target ==null){
        subject.next('0'); //发送读取文件结束后内容
        return;
      }
      if(target.offsetParent ==null){
        subject.next('0'); //发送读取文件结束后内容
        return;
      }
      if(target.offsetParent.id ==null){
        subject.next('0'); //发送读取文件结束后内容
        return;
      }
      if(target.offsetParent.id == 'riskEditSearchInput' ){
        subject.next('1')  //发送读取文件结束后内容
      }else{
        subject.next('0')  //发送读取文件结束后内容
      }
    });
    subject.asObservable().subscribe( resultList=> {
      this.isShowFindData(+resultList);
    });
  }

  hideType(){
    this.type.emit(0);
  }

  cancel(type) {
    let companyRiskIn:any = this.watchDel;
    companyRiskIn.riskTypes = this.watchDel.risksTypes;
    if(type == 0){ //新增
      this.api.addRiskInfo(companyRiskIn).subscribe((data)=>{
        if(+data.code == 0){

        }
        this.type.emit(1);
      })
    }
    if(type == 1){ //编辑
      this.api.editRiskInfo(companyRiskIn).subscribe((data)=>{
        if(+data.code == 0){

        }
        this.type.emit(1);
      })
    }

  }

  //删除
  del(){
    this.api.removeRiskInfo(this.watchDel.companyRiskId).subscribe((data)=>{
      if(+data.code == 0){
      }
      this.type.emit(1);
    })
  }

  confirm($event){
    if ($event === 0) {
     if(this.message ==  '是否删除！'){
       this.del();
     }else if(this.message ==  '是否新增！'){
       this.cancel(0);
     }else if(this.message ==  '是否保存！'){
       this.cancel(1);
     }
    } else if ($event === 1) {

    }
    this.modalRef.hide();
  }

  //打开删除模态框
  message = '';
  message2 ='';
  openModal(type,confirmTemplate: TemplateRef<any>) {
    if(type == -1) {
      this.message = '是否删除！';
    }else if(type == 0){
       if(this.incheck()){
         this.message = '是否新增！';
       }else {
         return;
       }
    }else if(type == 1){
      if( this.incheck()){
        this.message = '是否保存！';
      }else{
        return;
      }


    }
    this.message2 ='';
    this.modalRef = this.modalService.show(confirmTemplate,  {class: 'modal-sm', ignoreBackdropClick: true});
  }
}
