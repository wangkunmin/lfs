import {Component, EventEmitter, Input, Output} from '@angular/core';
import { EarlyWarning } from "../../common/model/early-warning";

@Component({
  selector: 'app-warning-item',
  templateUrl: './warning-item.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class WarningItemComponent {
  @Input() earlyWarningList: EarlyWarning[];
  @Input() num: number;

  @Output() openType = new EventEmitter<any>();

  constructor() { }

  //打开公司展台
  openCompanyDetail(news,ev){
    ev.stopPropagation();//防止事件广播
    window.open(`#/lfs/company/id/${news.companyId}`);
  }

  open($event) {
    this.openType.emit($event);
  }
}
