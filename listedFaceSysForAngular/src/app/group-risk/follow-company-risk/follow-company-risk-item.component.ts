import { Component, Input,Output, EventEmitter } from '@angular/core';
import { EarlyWarning } from "../../common/model/early-warning";
import { LocalStorageService } from "../../common/service/localStorage.service";

@Component({
  selector: 'app-follow-company-risk-item',
  templateUrl: './follow-company-risk-item.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class FollowCompanyRiskItemComponent {
  @Input() followTopList: EarlyWarning[];
  @Input() num: number;

  @Output() getFollowTopList = new EventEmitter<any>();

  userId: number;

  constructor(
    private ls: LocalStorageService,
  ) {
    this.userId = this.ls.get('userInfo').userId;
  }
  getValue(value){
    let ret = '';
    for(let src of value){
      ret = ret+ src+'\n';
    }
    return ret;
  }

  //点击公司名
  clickCompanyNm($event,companyId){
    $event.stopPropagation();
    window.open(`#/lfs/company/id/${companyId}`);
  }

  followTypeClick(ev){
    if(ev.type == 0){

    }else{

    }
    this.getFollowTopList.emit(ev)
  }
}
