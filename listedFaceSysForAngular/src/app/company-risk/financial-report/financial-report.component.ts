import {
  Component, EventEmitter, ElementRef, Input, OnInit, Output, AfterViewInit,
  QueryList, ViewChildren, Renderer2, TemplateRef
} from '@angular/core';

import {BsModalRef, BsModalService} from 'ngx-bootstrap';

import { CompanyReportIn } from '../../common/model/company-report-in';

import { CompanyRiskApiService } from '../../common/api/company-risk-api.service';
import { CommonUtil } from '../../common/utill/common-util';
import { CompanyList, DataForm, MarketType, NotShowColumn, ReportDate, ReportType } from '../../common/constant/finance.const';

@Component({
  selector: 'app-financial-report',
  templateUrl: './financial-report.component.html',
  styleUrls: ['./financial-report.component.css']
})
export class FinancialReportComponent implements OnInit, AfterViewInit {
  @Input() companyId: number;
  @Input() companyName: string;
  @Output() financeReportType = new EventEmitter<any>();
  @ViewChildren('row') row: QueryList<ElementRef>;
  @ViewChildren('rightColumn') rightColumn: QueryList<ElementRef>;
  @ViewChildren('leftColumn') leftColumn: QueryList<ElementRef>;
  @ViewChildren('topScroll') topScroll: QueryList<ElementRef>;

  modalRef: BsModalRef;
  chartData: Object;
  companyReportIn: CompanyReportIn;
  columnDefs: Array<any>;
  rowData: Array<any>;
  attrFlag: boolean;
  tabs: Object;
  searchCode: Object;
  tabsId : string ='0';
  private columnNameAll: Array<any>;
  private columnNameList: Array<string>;  // 前面固定列名称
  private index: number;
  private rptTimeTypeCd: string;
  private width: number;

  constructor(
    private renderer2: Renderer2,
    private modalService: BsModalService,
    private util: CommonUtil,
    private companyRiskApiService: CompanyRiskApiService
  ) {
    this.companyReportIn = {
      companyId: this.companyId,
      subjectType: 1,
      rptTimetypeCd: '0',
      rptDt: '最新'
    };
    this.attrFlag = true;
    this.tabs = {
      heading: ['资产负债表(合计)', '利润表(合计)', '现金流量表(合计)', '利润表(单季)', '现金流量表(单季)'],
      id: ['1,0', '2,0', '3,0', '2,1', '3,1'],
      customClass: 'fr-tab'
    };
    this.searchCode = {
      rptTimetypeCd: [
        { id: '0', flag: true, type: '全部'},
        { id: '1', flag: false, type: '年报'},
        { id: '2', flag: false, type: '中报'},
        { id: '3', flag: false, type: '一季报'},
        { id: '4', flag: false, type: '三季报'}
      ],
      rptDt: [
        { id: '最新', flag: true, type: '最新'},
        { id: '3', flag: false, type: '3年'},
        { id: '5', flag: false, type: '5年'},
        { id: '10', flag: false, type: '10年'},
        { id: '上市以来', flag: false, type: '上市以来'},
      ]
    };
    this.columnNameList = [
      'lkpFinansubjectdispSid', // 财务科目显示标识符
      'subjectCd',              // 科目代码
      'subjectNm',              // 科目中文名
      'parentSubjectCd',        // 上级科目代码
      'subjectLevel',           // 科目层次
      'isLabel',                // 是否是label
      'isBold',                 // 是否显示粗体
      'isFormular'              // 是否计算项目
    ];
  }

  ngOnInit() {
    this.changeRptTimeTypeCd('0');
    this.getFinanceReport();
  }

  ngAfterViewInit() {
    this.changeColor();
  }

  // 滚动栏
  onScroll($event, iTab) {
    this.leftColumn.forEach((el, i) => {
      if (iTab === i) {
        this.renderer2.setProperty(el.nativeElement, 'scrollTop', $event.srcElement.scrollTop);
      }
    });
    this.topScroll.forEach((el, i) => {
      if (iTab === i) {
        this.renderer2.setStyle(el.nativeElement, 'margin-left', `-${$event.srcElement.scrollLeft}px`);
        // this.renderer2.setStyle(el.nativeElement, 'scrollLeft', $event.srcElement.scrollLeft);
      }
    });
  }

  // 改变颜色
  changeColor() {
    this.row.changes
      .subscribe(
        (list: QueryList<ElementRef>) => {
          if (list.length > 0) {
            let flag = true;
            list.forEach(el => {
              if (flag) {
                this.renderer2.setStyle(el.nativeElement, 'background-color', `#fdfdfd`);
              } else {
                this.renderer2.setStyle(el.nativeElement, 'background-color', `#f3f3f3`);
              }
              flag = !flag;
            });
          }
        }
      );
  }

  // 报告期，时间范围
  search(type, id) {
    if (type === 0) {
      if (id === 0) {
        this.searchCode['rptTimetypeCd'][0].flag = true;
        for (let i = 1; i < this.searchCode['rptTimetypeCd'].length; i++) {
          this.searchCode['rptTimetypeCd'][i].flag = false;
        }
      } else {
        this.searchCode['rptTimetypeCd'][0].flag = false;
        this.searchCode['rptTimetypeCd'][id].flag = !this.searchCode['rptTimetypeCd'][id].flag;
      }
      this.companyReportIn.rptTimetypeCd = this.searchIn(this.searchCode['rptTimetypeCd']);
    }
    if (type === 1) {
      this.companyReportIn.rptDt = this.searchCode['rptDt'][id].id;
      for (let i = 0; i < this.searchCode['rptDt'].length; i++) {
        if (i === id) {
          this.searchCode['rptDt'][i].flag = true;
        } else {
          this.searchCode['rptDt'][i].flag = false;
        }
      }
    }
    this.changeRptTimeTypeCd(this.tabsId);
    this.getFinanceReport();
  }

  // 改变报表时间类型
  changeRptTimeTypeCd(id) {
    if (id === '0') {
      if (this.companyReportIn.rptTimetypeCd === '0') {
        this.rptTimeTypeCd = '1,2,3,4';
      } else {
        this.rptTimeTypeCd = this.companyReportIn.rptTimetypeCd;
      }
    }
    if (id === '1') {
      let reportType = {'0': '3,6,7,8,9', '1': '7', '2': '8', '3': '3', '4': '6,9'};
      this.rptTimeTypeCd = '';
      if (this.companyReportIn.rptTimetypeCd === '0') {
        this.rptTimeTypeCd = reportType['0'];
      } else {
        let attr = this.companyReportIn.rptTimetypeCd.split(',');
        attr.forEach(str => {
          if (this.rptTimeTypeCd === '') {
            this.rptTimeTypeCd = reportType[str];
          } else {
            this.rptTimeTypeCd += `,${reportType[str]}`;
          }
        });
      }
    }
  }

  // tab栏切换
  tabSearch(id) {
    let i = '';
    i = id.split(',');
    this.companyReportIn.subjectType = Number(i[0]);
    this.tabsId = i[1];
    this.changeRptTimeTypeCd(i[1]);
    this.getFinanceReport();
  }

  // 获取财务数据
  getFinanceReport() {
    let companyReportIn: CompanyReportIn = {
      companyId: this.companyId,
      subjectType: this.companyReportIn.subjectType,
      rptTimetypeCd: this.rptTimeTypeCd,
      rptDt: this.companyReportIn.rptDt
    };
    this.companyRiskApiService.getFinanceReport(companyReportIn)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.rowData = this.changData(data.data['content']);
            return;
          }
          this.rowData = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 改变数据
  changData(data: Array<any>): Array<any> {
    let newData = [];

    // {'lkpFinansubjectdispSid': 1208, 'subjectCd': '00', 'subjectNm': '报表日期', 'parentSubjectCd': 1,
    // 'subjectLevel': 0, 'isLabel': 0, 'isBold': 0, 'isFormular': 0,
    // '20161231': 20161231, '20170630': 20170630, '20170331': 20170331, '20160930': 20160930}
    this.columnNameAll = this.util.compact(this.columnNameList);
    for (let i = this.columnNameAll.length; i < data[0].length; i++) {
      this.columnNameAll.push(data[0][i]);
    }
    for (let i = 0; i < data.length; i++) {
      let obj = {};
      for (let j = 0; j < data[i].length; j++) {
        obj[this.columnNameAll[j]] = data[i][j];
      }
      newData.push(obj);
    }

    this.columnDefs = this.createColumnDefs(newData[0]);
    newData.splice(0, 1);
    this.util.typeFilter(newData, '报告期', ReportDate);
    this.util.typeFilter(newData, '上市前/上市后', MarketType);
    this.util.typeFilter(newData, '公司类型', CompanyList);
    this.util.typeFilter(newData, '报表类型', ReportType);
    this.util.typeFilter(newData, '数据来源', DataForm);
    newData = this.deleteData(newData);
    return this.gradingData(newData);
  }

  // 创建表头
  createColumnDefs(obj: Object): Array<any> {
    let array = [];
    let keys = Object.keys(obj);
    this.width = 0;
    for(let i = 0; i < keys.length; i++) {
      if (!this.util.container(NotShowColumn, keys[i])) {
        if (keys[i] === 'subjectNm') {
          array.push({
            headerName: obj[keys[i]],
            field: keys[i],
            width: 255
          });
          this.width += 255;
        } else {
          array.push({
            headerName: this.util.getRptDtTitle(obj[keys[i]] + ''),
            field: obj[keys[i]] + '',   // 必须为字符串格式
            width: 135
          });
          this.width += 135;
        }
      }
    }
    return array.reverse();
  }

  // 数据分级
  gradingData(data: Array<any>): Array<any> {
    let newData = [];
    for (let i = 0; i < data.length; i++) {
      if (data[i].parentSubjectCd === '00') {
        data[i].parentSubjectCd = '0';
      }
      if (data[i].parentSubjectCd === '0') {
        data[i].children = [];
        for (let j = 0; j < data.length; j++) {
          if (data[j].parentSubjectCd === data[i].subjectCd) {
            data[j].subjectNm = `--${data[j].subjectNm}`;
            data[i].children.push(data[j]);
          }
        }
        data[i].showChildren = true;
        newData.push(data[i]);
      }
    }
    newData.sort(this.util.sortBy('subjectCd'));
    return newData;
  }

  // 删除对象中金额为null和0的对象
  deleteData(data): Array<any> {
    let newData = [];
    for (this.index = 0; this.index < data.length; this.index++) {
      if (data[this.index].isLabel === 0) {
        let keys = Object.keys(data[this.index]);
        newData = this.changMillion(data, this.index, keys);
      }
    }
    return newData;
  }

  // 元改为万元
  changMillion(data: Array<any>, i: number, keys: Array<any>): Array<any> {
    if (data[i].subjectNm === '(一)基本每股收益(元)' || data[i].subjectNm === '(二)稀释每股收益(元)') {
      data = this.changMoney(data, i, keys, false);
    } else {
      data = this.changMoney(data, i, keys, true);
    }
    return data;
  }

  // 删除对象中金额为null和0的对象
  changMoney(data: Array<any>, i: number, keys: Array<any>, type: boolean): Array<any> {
    let flagArray = [];     // 存放对象中各个数据是否有值
    let deleteFlag = false; // 是否删除
    for (let j = 0; j < keys.length; j++) {
      if (!this.util.container(this.columnNameList, keys[j])) {
        let money = data[i][keys[j]];
        let flag = false;

        if (this.util.isSingleEmpty(money) || 0 === money) {
          money = null;
          flag = true;
        }
        if (typeof data[i][keys[j]] === 'number') {
          let money = data[i][keys[j]];
          if (type) {
            money = money/10000;
          }
          money = this.util.financeMoney(money, 2);
          if (this.util.isSingleEmpty(money) || '0.00' === money) {
            money = null;
            flag = true;
          }
          data[i][keys[j]] = money;
        }
        flagArray.push(flag);
      }
    }
    for (let k = 0; k < flagArray.length; k++) {
      if (!flagArray[k]) {  // 该对象中其中一条数据有值，则不删除
        deleteFlag = true;
        continue;
      }
    }
    if (deleteFlag) {
      if (type) {
        data[i].subjectNm = data[i].subjectNm.replace(/((元))/g, '万元');
      }
    } else {  // 删除指定数组位置，并且下标减1
      data.splice(i, 1);
      this.index--;
    }
    return data;
  }

  // 是否示子节点
  showChildren(type) {
    if(type === 0) {
      this.attrFlag = !this.attrFlag;
      this.forShowChildren(this.attrFlag);
      return;
    } else {
      this.rowData[type].showChildren = !this.rowData[type].showChildren;
    }
    this.changeColor();
  }

  // 控制所有子节点
  forShowChildren(flag) {
    for (let i = 0; i < this.rowData.length; i++) {
      this.rowData[i].showChildren = flag;
    }
  }

  // 拼接字符串
  searchIn(attr) {
    let data = '';
    if (attr[0].flag) {
      return '0';
    }
    for (let i = 1; i < attr.length; i++) {
      if (attr[i].flag) {
        if (data === '') {
          data = attr[i].id;
        } else {
          data += `,${attr[i].id}`;
        }
      }
    }
    return data;
  }

  // 导出excel
  exportData() {
    let data = this.rowData;
    let ex = [];
    let reg = /^[0-9]+.?[0-9]*$/;
    for (let i = 0; i < data.length; i++) {
      ex.push(this.excelData(data, reg, i));
      if (data[i].children.length > 0) {
        for (let j = 0; j < data[i].children.length; j++) {
          ex.push(this.excelData(data[i].children, reg, j));
        }
      }
    }
    try {
      this.util.JSONToExcelConvertor(ex, new Date().getTime());
    } catch (e) { }
  }

  // 整理excel数据
  excelData(data, reg, i) {
    let obj = [];
    for (let j in data[i]) {
      if (j === 'subjectNm') {
        obj.splice(0, 0, {value: data[i][j]});
      } else if (reg.test(j)) {
        obj.push({value: data[i][j] || ''});
      }
    }
    return obj;
  }

  // 打开财务报表
  openFinancialChart(template: TemplateRef<any>, data) {
    this.chartData = data;
    this.modalRef = this.modalService.show(template, {ignoreBackdropClick: true});
  }

  // 关闭财务图表
  closeFinanceChart($event) {
    if ($event.type === 0) {
      this.modalRef.hide();
    }
  }

  // 关闭模态框
  cancel() {
    this.financeReportType.emit({type: 1});
  }

}
