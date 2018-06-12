import {
  AfterViewInit,
  Component, DoCheck, ElementRef, EventEmitter, Input, Output, QueryList, Renderer2,
  ViewChildren
} from '@angular/core';
import {AtlasMapApiService} from "../../common/api/atlas-map-api.service";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-left-bar',
  templateUrl: './left-bar.component.html',
  styleUrls: ['./left-bar.component.css', '../atlas-map.component.css']
})
export class LeftBarComponent implements DoCheck, AfterViewInit {
  @Input() sels: any;
  @Input() sel: any;
  @Input() relations: Array<any>;
  @Input() riskSelF: any;
  @Input() reskSels: any;
  @Input() toggle: Array<boolean>;
  @Input() leftBarDataSel: any = {};
  @Output() riskSelType = new EventEmitter<any>();
  @ViewChildren('leftBarHead') leftBarHead: QueryList<ElementRef>;
  @ViewChildren('leftBarContent') leftBarContent: QueryList<ElementRef>;
  @ViewChildren('checkSquare') checkSquare: QueryList<ElementRef>;

  leftFlag: Array<any>;
  leftSrc0: string;
  leftSrc1: string;
  leftType: any;
  leftData: any;
  private toggleFlag: boolean;

  constructor(
    private renderer2: Renderer2,
    private atlasMapApiService: AtlasMapApiService,
    private util: CommonUtil
  ) {
    this.leftFlag = [];
    this.leftFlag[0] = false;
    this.leftFlag[1] = false;
    this.toggleFlag = true;
    this.leftSrc0 = 'assets/images/svg/left_on.svg';
    this.leftSrc1 = 'assets/images/svg/left_on.svg';
    this.leftType = {
      1:['t1','1','t2','5','t3','t4'],
      2:['1','2'],
      3:['1','t11','t6']
    };
    this.leftData = {
      1:['对外投资','rgba(137, 195, 235, 1)','持股比例'],
      t1:['股东','rgba(137, 195, 235, 1)','持股比例'],
      2:['对外任职','rgba(151,204,133, 1)','职位'],
      t2:['高管','rgba(151,204,133, 1)','职位'],
      3:['客户','rgba(50, 161, 175, 1)','关系'],
      t3:['客户','rgba(50, 161, 175, 1)','关系'],
      t4:['供应商','rgba(51, 102, 204, 1)','关系'],
      5:['担保','rgba(249,191,71, 1)','担保'],
      t5:['担保','rgba(137, 195, 235, 1)','担保'],
      6:['基金管理','rgba(66, 6, 169, 1)','关系'],
      t6:['基金管理','rgba(66, 6, 169, 1)','关系'],
      7:['亲戚','rgba(66, 6, 169, 1)','关系'],
      8:['股权控制','rgba(66, 6, 169, 1)','发行主体'],
      9:['产品发行','rgba(66, 6, 169, 1)','发行主体'],
      10:['有效担保','rgba(66, 6, 169, 1)','担保'],
      11:['托管机构','rgba(51, 102, 204, 1)','托管方式'],
      t11:['托管机构','rgba(51, 102, 204, 1)','托管方式'],
      12:['法人委派','rgba(66, 6, 169, 1)','法人']
    };
  }

  ngDoCheck() {
    if (this.toggleFlag && this.sels[this.sel]) {
      this.toggleFlag = false;
      let type = this.leftType[this.sels[this.sel]['label']];
      for (let i = 0; i < type.length; i++) {
        this.riskSelType.emit({
          'i': type[i],
          'type': 3,
          'index': i
        });
      }
    }
  }

  ngAfterViewInit() {
    this.checkSquare.changes
      .subscribe(
        (list: QueryList<ElementRef>) => {
          if (list.length > 0) {
            list.forEach(el => {
              this.renderer2.addClass(el.nativeElement, 'fa-check-square');
            });
          }
        }
      );
  }

  checkToggle(e, el, id, i, sel) {
    e.stopPropagation();
    let index = i;
    this.leftBarDataSel[sel][index][id] = !this.leftBarDataSel[sel][index][id];
    if (el.className.indexOf('fa-check-square') > 0) {
      this.renderer2.addClass(el, 'fa-square-o');
      this.renderer2.removeClass(el, 'fa-check-square');
    } else {
      this.renderer2.removeClass(el, 'fa-square-o');
      this.renderer2.addClass(el, 'fa-check-square');
    }
    this.riskSelType.emit({
      'id': id,
      'i': i,
      'flag': this.leftBarDataSel[sel][index][id],
      'type': 4
    });
  }

  //wkm 20180514 样式bug维护
  retClassFun( el, id, i, sel) {
    let index = i;
    let retClass = {
      'fa-square-o': !this.leftBarDataSel[sel][index][id],
      'fa-check-square': this.leftBarDataSel[sel][index][id],
    };
    return retClass;
  }

  riskSel() {
    this.riskSelType.emit({'type': 1});
  }

  clickToggle($event, i, index) {
    this.riskSelType.emit({
      'e': $event,
      'i': i,
      'type': 2,
      'index': index
    });
  }

  changeSel() {
    this.sels[this.sel].label == 1 ? this.toComBase(this.sels[this.sel]['link_id']) : ''
  }

  // 跳转展台
  toComBase(id) {
    this.atlasMapApiService.iscompany(id)
      .subscribe(
        (data: any) => {
          if(data.code === '0') {
            window.open(`#/lfs/company/id/${id}`);
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  };

  changeLeftFlag0() {
    this.leftFlag[0] = !this.leftFlag[0];
    if (this.leftFlag[0]) {
      this.leftSrc0 = 'assets/images/svg/left_off.svg';
    } else {
      this.leftSrc0 = 'assets/images/svg/left_on.svg';
    }
  }
  changeLeftFlag1() {
    this.leftFlag[1] = !this.leftFlag[1];
    if (this.leftFlag[1]) {
      this.leftSrc1 = 'assets/images/svg/left_off.svg';
    } else {
      this.leftSrc1 = 'assets/images/svg/left_on.svg';
    }
  }

  changeHoverColor(type, iBar, color?) {
    if (type === 0) {
      this.leftBarHead.forEach((el, i) => {
        if (iBar === i) {
          this.renderer2.setStyle(el.nativeElement, 'background', '#fff');
        }
      });
    }
    if (type === 1) {
      this.leftBarHead.forEach((el, i) => {
        if (iBar === i) {
          this.renderer2.setStyle(el.nativeElement, 'background', color.replace('1)', '0.5)'));
        }
      });
    }
  }

  showContent(iBar) {
    this.leftBarHead.forEach((el, i) => {
      if (iBar === i) {
        if (el.nativeElement.className.indexOf('active') > 0) {
          this.leftBarContent.forEach((el2, j) => {
            if (iBar === j) {
              if (el2.nativeElement.style.display === 'block') {
                this.renderer2.setStyle(el2.nativeElement, 'display', 'none');
              } else {
                this.renderer2.setStyle(el2.nativeElement, 'display', 'block');
              }
            } else {
              this.renderer2.setStyle(el2.nativeElement, 'display', 'none');
            }
          });
        }
      }
    });
  }

  //关系线连接控制 开关显示
  getToggleFlag(i,index,relationsIndex){
    if (relationsIndex != undefined && relationsIndex!=null) {
      if(relationsIndex.length == 0){
        this.toggle[index] = false;
      }
      if(this.leftBarDataSel != undefined){
        if (this.leftBarDataSel[this.sel]!= undefined) {
          this.toggle[index] = false;
          for (let i_t in this.leftBarDataSel[this.sel][i]) {
            if (this.leftBarDataSel[this.sel][i][i_t]) {
              this.toggle[index] = true;
              break;
            }
          }
        }
      }

    }else{
      this.toggle[index] = false;
    }
    let retClass = {
      'fa-toggle-on': this.toggle[index],
      'fa-toggle-off': !this.toggle[index]
    };
    return retClass;
  }
}
