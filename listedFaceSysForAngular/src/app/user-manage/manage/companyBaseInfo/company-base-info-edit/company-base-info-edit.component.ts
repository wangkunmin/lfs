import {Component, EventEmitter, Input, OnInit, Output, TemplateRef} from '@angular/core';
import {ManageApiService} from "../../../../common/api/manage-api.service";
import {Base} from "../../../../common/constant/api-base.const";
import {CompanyBaseInfo, RiskRateIn} from "../../../../common/model/company-aintain";
import {BsModalService} from 'ngx-bootstrap';
import {CommonUtil} from "../../../../common/utill/common-util";

@Component({
  selector: 'app-company-base-info-edit',
  templateUrl: './company-base-info-edit.component.html',
  styleUrls: ['../../manage.component.css']
})
export class CompanyBaseInfoEditComponent implements OnInit {
  @Input() watch: CompanyBaseInfo;
  @Output() type = new EventEmitter<number>();

  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private util: CommonUtil
  ) { }

  ngOnInit() {
    this.api.findCompanyBaseInfoById(this.watch.companyId).subscribe((data)=>{
      if(+data.code == 0){
        this.watch = data.data['content'];
      }
    })
  }



  cancel() {
    this.api.editCompanyBaseInfo(this.watch).subscribe((data)=>{
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
  message2='';
  openModal(confirmTemplate: TemplateRef<any>) {
    if(this.util.getStringBytesLength(this.watch.regAddr)>150){
      this.message2='注册地址不能超过150字！';
      return;
    }
    let reg =/^[-\+]?\d+(\.\d+)$/;
    if(!this.watch.positionX.match(reg)){
      this.message2='X轴坐标不能输入非数字！';
      return;
    }
    if(!this.watch.positionY.match(reg)){
      this.message2='Y轴坐标不能输入非数字！';
      return;
    }
    this.message2='';
    this.message = '是否保存！';
    this.modalRef = this.modalService.show(confirmTemplate,  {class: 'modal-sm', ignoreBackdropClick: true});
  }
}
