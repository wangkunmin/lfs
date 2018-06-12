import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ManageApiService} from "../../common/api/manage-api.service";
import {BaseApiResponseModel} from "../../common/model/base-api-response.model";
import {CompanySuperviseInfo} from "../../common/model/company-supervise-info";
import {CommonUtil} from "../../common/utill/common-util";
import {UserAttentionIn} from "../../common/model/user-attention-in";

@Component({
  selector: 'app-watch',
  templateUrl: './watch.component.html',
  styleUrls: ['./manage.component.css']
})
export class WatchComponent implements OnInit {
  watchInfo: CompanySuperviseInfo[];
  totalItems: number;
  itemsPerPage: number;
  currentPage: number;
  company: string;
  watchData: CompanySuperviseInfo;

  modalRef: BsModalRef;

  constructor(
    private modalService: BsModalService,
    private api: ManageApiService,
    private util: CommonUtil
  ) {
    this.itemsPerPage = 10;
    this.currentPage = 1;
  }

  ngOnInit() {
    this.getWatch();
  }

  getWatch($event?, companyName?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let userAttention: UserAttentionIn = {
      companyNm: companyName,
      page: this.currentPage,
      pageSize: this.itemsPerPage
    };
    this.api.indCompySuperviseInfo(userAttention)
      .subscribe(
        (data: BaseApiResponseModel) => {
          this.totalItems = data.count;
          if (data.code === '0') {
            this.setWatchInfo(data.data['content']);
            return;
          }
          this.watchInfo = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  setWatchInfo(data: CompanySuperviseInfo[]) {
    for (let i = 0; i < data.length; i++) {
      let list = data[i].compySuperviseInfoList;
      data[i].nameStrs = '';
      for (let j = 0; j < list.length; j++) {
        if (this.util.isEmpty(list[j])) {
          data[i].nameStrs = '--';
        } else {
          data[i].nameStrs += data[i].nameStrs ? `，${list[j].userNm}` : `${list[j].userNm}`;
        }
      }
    }
    this.watchInfo = data;
  }

  search() {
    this.getWatch(null, this.company);
  }

  edit(template: TemplateRef<any>, data) {
    this.watchData = data;
    this.modalRef = this.modalService.show(template, {ignoreBackdropClick: true});
  }

  editWatch($event) {
    if ($event === 1) {
      this.getWatch();
      this.modalRef.hide();
    }
  }
}
