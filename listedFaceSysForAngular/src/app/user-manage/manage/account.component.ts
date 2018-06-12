import {Component, OnInit, TemplateRef} from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';

import { BaseApiResponseModel } from "../../common/model/base-api-response.model";
import { ManageApiService } from "../../common/api/manage-api.service";
import { UserManageIn } from "../../common/model/user-manage-in";
import { UserManageInfo } from "../../common/model/user-manage-info";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./manage.component.css']
})
export class AccountComponent implements OnInit {
  userInfo: UserManageInfo[];
  totalItems: number;
  itemsPerPage: number;
  currentPage: number;
  name: string;
  userData: UserManageInfo;
  title: string;
  type: number;
  message: string;
  private deleteUser: UserManageInfo;

  modalRef: BsModalRef;


  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private router: Router,
    private lg:LocalStorageService
  ) {
    this.itemsPerPage = 10;
    this.currentPage = 1;
    this.name = '';
  }

  ngOnInit() {
    this.getAccount();
  }

  search() {
    this.getAccount(null, this.name);
  }

  deleteAccount(user: UserManageInfo) {
    this.api.deleteUser(user.accountId)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            if((+this.lg.get('userInfo').accountId) == (+user.accountId)){
              this.lg.clear(); //清理用户信息
              this.router.navigate(['home/login']);
            }
            this.currentPage = 1;
            let ev = {
              page : 1
            }
            this.getAccount(ev,this.name);
            return;
          }
          this.userInfo = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  add(template: TemplateRef<any>) {
    this.userData = new UserManageInfo();
    this.userData.roleId = '2050';
    this.title = '创建新用户';
    this.type = 0;
    this.modalRef = this.modalService.show(template, {ignoreBackdropClick: true});
  }

  getAccount($event?, accountName?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let userManageIn: UserManageIn = {
      accountNm: accountName,
      page: this.currentPage,
      pageSize: this.itemsPerPage
    };
    this.api.getUserMaintain(userManageIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          this.totalItems = data.count;
          if (data.code === '0') {
            this.userInfo = data.data['UserManagementList'];
            return;
          }
          this.userInfo = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  editUser($event) {
    if ($event === 0) {
      let ev = {
        page : 1
      };
      this.getAccount(ev,this.name);
      this.modalRef.hide();
    } else if ($event === 3) {
      this.currentPage = 1;
      let ev = {
        page : 1
      };
      this.getAccount(ev,this.name);
      this.modalRef.hide();
    } else {
      this.modalRef.hide();
    }
  }

  edit(template: TemplateRef<any>, data) {
    this.userData = data;
    this.title = '重置密码';
    this.type = 1;
    this.modalRef = this.modalService.show(template, {ignoreBackdropClick: true});
  }

  openModal(user: UserManageInfo, confirmTemplate: TemplateRef<any>) {
    this.deleteUser = user;
    this.message = '是否删除！';
    this.modalRef = this.modalService.show(confirmTemplate,  {class: 'modal-sm', ignoreBackdropClick: true});
  }

  confirm($event) {
    if ($event === 0) {
      this.deleteAccount(this.deleteUser);
      this.modalRef.hide();
    } else if ($event === 1) {
      this.modalRef.hide();
    }
  }
}
