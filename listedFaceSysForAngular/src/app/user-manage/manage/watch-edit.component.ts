import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BaseApiResponseModel} from "../../common/model/base-api-response.model";
import {CompanySuperviseInfo, CompySuperviseInfo} from "../../common/model/company-supervise-info";
import {CompanySuperviseIn} from "../../common/model/company-supervise-in";
import {LocalStorageService} from "../../common/service/localStorage.service";
import {ManageApiService} from "../../common/api/manage-api.service";
import {UserManageIn} from "../../common/model/user-manage-in";
import {UserManageInfo} from "../../common/model/user-manage-info";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-watch-edit',
  templateUrl: './watch-edit.component.html',
  styleUrls: ['./manage.component.css']
})
export class WatchEditComponent implements OnInit {
  @Input() watch: CompanySuperviseInfo;
  @Output() type = new EventEmitter<number>();

  userInfo: UserManageInfo[];
  name: string;
  companySupervise: CompySuperviseInfo[];
  message: string;

  constructor(
    private ls: LocalStorageService,
    private api: ManageApiService,
    private util: CommonUtil
  ) { }

  ngOnInit() {
    this.companySupervise = this.changeSuper(this.watch.compySuperviseInfoList);
    this.getAccount();
  }

  changeSuper(list) {
    let data: CompySuperviseInfo[] = [];
    for (let i = 0; i < list.length; i++) {
      if (!this.util.isEmpty(list[i])) {
        data.push(list[i]);
      }
    }
    return data;
  }

  editWatch(supervise: CompySuperviseInfo, type: string, index?: number) {
    let companySupervise: CompanySuperviseIn = {
      compyId: Number(this.watch.companyId),
      updtBy: this.ls.get("userInfo").userId,
      userIds: supervise.userId.toString(),
      operationType: type
    };
    this.api.operationSupervise(companySupervise)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            if (type === '1') {
              this.companySupervise.push(supervise);
            } else if (type === '2') {
              this.companySupervise.splice(index, 1)
            }
            return;
          }
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  getAccount(accountName?) {
    let userManageIn: UserManageIn = {
      accountNm: accountName
    };
    this.api.getUserMaintain(userManageIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            this.userInfo = data.data['UserManagementList'];
            return;
          }
          this.userInfo = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  add(user: UserManageInfo, type: string) {
    for (let i = 0; i < this.companySupervise.length; i++) {
      if (this.companySupervise[i].userId === user.userId) {
        this.message = '添加失败，已存在该监管人！';
        return false;
      }
    }
    let supervise: CompySuperviseInfo = {
      userId: user.userId,
      userNm: user.userNm
    };
    this.editWatch(supervise, type);
    return true;
  }

  deleteWatch(supervise: CompySuperviseInfo, type: string) {
    for (let i = 0; i < this.companySupervise.length; i++) {
      if (this.companySupervise[i].userId === supervise.userId) {
        this.editWatch(supervise, type, i);
      }
    }
  }

  search() {
    this.getAccount(this.name);
  }

  cancel() {
    this.type.emit(1);
  }
}
