import {Component, EventEmitter, Input, OnInit, Output, TemplateRef} from '@angular/core';
import {ManageApiService} from "../../../../common/api/manage-api.service";
import {Base} from "../../../../common/constant/api-base.const";
import {RiskRateIn} from "../../../../common/model/company-aintain";
import {BsModalService} from 'ngx-bootstrap';
import {CommonUtil} from "../../../../common/utill/common-util";
@Component({
  selector: 'app-level-edit',
  templateUrl: './level-edit.component.html',
  styleUrls: ['../../manage.component.css']
})
export class LevelEditComponent implements OnInit {
  @Input() watch: RiskRateIn;
  @Output() type = new EventEmitter<number>();
  comRiskLevels = Base.comRiskLevels; //评级内容
  newLevel=4; //新评级
  newRiskContent= ''; //新评级内容

  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private util : CommonUtil
  ) { }

  ngOnInit() {
    this.api.findRiskRateInfo(this.watch.companyId).subscribe((data)=>{
      if(+data.code == 0){
        this.watch = data.data['content'];
      }
    })
  }



  cancel() {
    let riskRate = { //编辑风险评级信息入参
      companyId: this.watch.companyId,
      companyNm: this.watch.companyNm,
      pkCompanyId: this.watch.pkCompanyId,  //风险表主键
      rating: this.newLevel,  //风险类型  1高 2次 3关注 4正常
      riskContent: this.newRiskContent, //风险描述
      companySnm: this.watch.companySnm,
      securityCds: this.watch.securityCds
    };
    this.api.editRiskRateInfo(riskRate).subscribe((data)=>{
      if(+data.code == 0){

      }
      this.type.emit(1);
    })
  }
  confirm($event){
    if ($event === 0) {
      this.cancel();
    } else if ($event === 1) {

    }
    this.modalRef.hide();
  }

  //关闭编辑画面
  hideType(){
    this.type.emit(0);
  }

  //打开删除模态框
  modalRef;
  message = '';
  message2 = '';
  openModal(confirmTemplate: TemplateRef<any>) {
    let inputStrLength = this.util.getStringBytesLength(this.newRiskContent);
    if(inputStrLength>600){
      this.message2 = '风险详情不能超过300个字！';
      return;
    }
    this.message2 ='';
      this.message = '是否保存！';
    this.modalRef = this.modalService.show(confirmTemplate,  {class: 'modal-sm', ignoreBackdropClick: true});
  }
}
