import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, TemplateRef} from '@angular/core';
import {Router} from '@angular/router';

import {UserFollow} from '../../common/model/user-follow';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';

@Component({
  selector: 'app-risk-menu',
  templateUrl: './risk-menu.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class RiskMenuComponent implements OnChanges {
  //设定关注画面高度
  h:number;

  headerh = 68;
  booterh = 310;
  getHeight = {
    height:window.screen.height - this.headerh - this.booterh  +'px',  //获取显示屏分辨率
  };

  modalRef: BsModalRef;

  @Input() flag: any;
  @Input() userId: number;
  @Input() userFollow: UserFollow[];
  @Output() followFlag = new EventEmitter<number>();

  constructor(
    private router: Router,
    private modalService: BsModalService,
  ) { }

  onResize(ev){
    if(window.screen.height >=800){
      this.getHeight = {
        height:800  - this.headerh - this.booterh   +'px',
      };

    }else{
      this.getHeight = {
        height:window.screen.height - this.headerh - this.booterh  +'px',
      }
    }

  }

  ngOnChanges(changes: SimpleChanges) {
    this.userFollow = this.userFollow;
  }

  // 取消关注
  cancelFollow($event, i: number, companyId: string) {
    if ($event.type === 1) {
      if (this.userFollow[i].companyId === companyId) {
        this.userFollow.splice(i, 1);
        // return;
      }
      this.router.navigate([`lfs/group/detail/1/${$event.companyId}`]);
    }
  }

  // 打开添加公司的模态框
  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-lg', ignoreBackdropClick: true});
  }

  // 确定，关闭模态框
  addFollow($event) {
    if ($event.type === 0) {
      if ($event.companyId === '') {
        $event.companyId = 99999;
        this.modalRef.hide();
      }
      this.followFlag.emit(0);
      this.modalRef.hide();
    } else if ($event.type === 1) {
      this.followFlag.emit(0);
      this.modalRef.hide();
    }
    this.router.navigate([`lfs/group/detail/0/${$event.companyId}`]);

  }

  close() {
    this.flag.followFlag = true;
    this.followFlag.emit(1);
    let mMenu:any = document.getElementsByClassName('m-menu')[0];
    mMenu.style.overflowY = '';
  }

  //点击公司名
  clickCompanyNm($event,companyId){
    $event.stopPropagation();
    window.open(`#/lfs/company/id/${companyId}`);
  }
}
