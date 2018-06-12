import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { GroupRiskApiService } from '../../common/api/group-risk-api.service';
import { UserFollowIn } from '../../common/model/user-follow-in';
import { UserFollow } from '../../common/model/user-follow';
import { CommonUtil } from '../../common/utill/common-util';

@Component({
  selector: 'app-add-follow-company',
  templateUrl: './add-follow-company.component.html',
  styleUrls: ['./add-follow-company.component.css']
})
export class AddFollowCompanyComponent implements OnInit {
  @Input() userId: number;
  @Output() addType = new EventEmitter<any>();
  userFollowInfo: UserFollow;
  currentPage: number;
  itemsPerPage: number;
  totalItems: number;
  num: number;
  companyIdArrays: string = '';
  companyName: string;

  constructor(
    private groupRiskApiService: GroupRiskApiService,
    private commonUtil: CommonUtil
  ) {
    this.currentPage = 1;
    this.itemsPerPage = 10;
  }

  ngOnInit() {
    this.findFollowedCompany();
  }

  keyDown($event) {
    if (this.commonUtil.keyDown($event)) {
      this.currentPage = 1;
      this.findFollowedCompany(null, this.companyName);
    }
  }

  addFollow($event) {
    if ($event.type === 0) {
      if (this.companyIdArrays === '') {
        this.companyIdArrays = $event.companyId;
      } else {
        this.companyIdArrays = `${this.companyIdArrays},${$event.companyId}`;
      }
    }
    let ev = {
    page:1
    };
    this.findFollowedCompany(ev,this.companyName);
  }

  // 查询用户未关注的公司列表
  findFollowedCompany($event?, companyName?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let userFollowIn: UserFollowIn = {
      userId: this.userId,
      page: this.currentPage,
      pageSize: this.itemsPerPage,
      companyNm: companyName
    };
    this.groupRiskApiService.findNotFollowedCompany(userFollowIn)
      .subscribe(
        data => {
          this.totalItems = data.count;
          if (data.code === '0') {
            this.num = (this.currentPage - 1) * this.itemsPerPage + 1;
            this.userFollowInfo = data.data['content'];
            return;
          }
          this.userFollowInfo = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 确定
  confirm() {
    this.addType.emit({
      type: 0,
      companyId: this.companyIdArrays
    });
  }

  cancel() {
    this.addType.emit({
      type: 1
    });
  }

}
