import { Component, EventEmitter, Input, Output, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';

import { Follow } from './follow';

import { LocalStorageService } from '../../common/service/localStorage.service';
import { UserAttentionIn } from '../../common/model/user-attention-in';
import { BaseApiResponseModel } from '../../common/model/base-api-response.model';
import { UserApiService } from '../../common/api/user-api.service';

@Component({
  selector: 'app-follow',
  templateUrl: './follow.component.html',
  styleUrls: ['./follow.component.css']
})
export class FollowComponent {
  @Input() follow: Follow;
  @Output() followType = new EventEmitter<any>();
  @Input() nonFleshbtn: Boolean;
  modalRef: BsModalRef;
  userId: number;
  message: string;

  constructor(
    private modalService: BsModalService,
    private ls: LocalStorageService,
    private userApiService: UserApiService
  ) {
    this.userId = this.ls.get('userInfo').userId;
  }

  openModal($event, confirmTemplate: TemplateRef<any>) {
    $event.stopPropagation();

    if (this.follow.isFocused) {
      this.message = '是否取消关注！';
    } else {
      this.message = '是否重点关注！';
    }
    this.modalRef = this.modalService.show(confirmTemplate,  {class: 'modal-sm', ignoreBackdropClick: true});
  }

  confirm($event) {
    if ($event === 0) {
      if (this.follow.isFocused) {
        this.deleteFocus();
      } else {
        this.addFocus();
      }
      this.modalRef.hide();
    } else if ($event === 1) {
      this.modalRef.hide();
    }
  }

  // 添加关注
  addFocus() {
    let userAttentionIn: UserAttentionIn = {
      userId: this.userId,
      companyId: Number(this.follow.companyId),
      companyNm: this.follow.companyNm,
      focusType: 1
    };
    this.userApiService.addAttention(userAttentionIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            if (!this.nonFleshbtn){
              this.follow.isFocused = true;
            }
            this.followType.emit({
              type: 0,
              companyId: this.follow.companyId
            });
            return;
          }
          this.follow.isFocused = false;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  // 取消关注
  deleteFocus() {
    let userAttentionIn: UserAttentionIn = {
      userId: this.userId,
      companyId: Number(this.follow.companyId),
      companyNm: this.follow.companyNm,
      focusType: 1
    };
    this.userApiService.deleteAttention(userAttentionIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            if (!this.nonFleshbtn) {
              this.follow.isFocused = false;
            }
            this.followType.emit({
              type: 1,
              companyId: this.follow.companyId
            });
            return;
          }
          this.follow.isFocused = true;
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }

}
