import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { AtlasMapApiService } from '../../common/api/atlas-map-api.service';
import { ChartUtil } from '../../common/utill/chart-util';
import * as cytoscape from 'cytoscape'
import { CyStyle } from './cy-style';
import { LoginService } from '../../common/service/login.service';
import { CommonUtil } from '../../common/utill/common-util';
import {Observable} from "rxjs/Rx";
import {EssenceNg2PrintComponent} from "essence-ng2-print";
import {BsModalService} from 'ngx-bootstrap';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  @ViewChild("messageTemplate") messageTemplate: ElementRef;//消息弹出框
  modalRef;

  @ViewChild("chart") chart: ElementRef;
  @ViewChild("print") printComponent: EssenceNg2PrintComponent;
  noInfo: boolean;

  private getNodeDataURL: string; // 接口URL
  private chartData: any;         // 图谱数据

  cy: any;
  private allEdges: any = null;
  private leftMark: any = {};          // 左侧栏
  selEdges: any = {};          // 选择线
  private ueSwitch: boolean = false;
  sel: any = {};
  sels: any = {};
  riskSelF: any = false;
  reskSels: any;
  toggle: Array<boolean>;
  leftBarDataSel: any = {};
  private bottomBarDataSel: Array<any> = [];
  relations: any;
  private banDbClick: any = undefined;
  rClientWidth: number;
  printSrc: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private el: ElementRef,
    private renderer2: Renderer2,
    private atlasMapApiService: AtlasMapApiService,
    private loginService: LoginService,
    private chartUtil: ChartUtil,
    private modalService: BsModalService,
    private util: CommonUtil
  ) {
    this.printSrc = '';
    this.loginService.sendLogin({ loginFlag: 0 });
    this.toggle = [false, false, false, false, false, false];
  }

  ngOnInit() {
    const cyChart = this.el.nativeElement.querySelector(`#cy`);
    const rightBar = this.el.nativeElement.querySelector(`.rightBar`);
    this.renderer2.setStyle(rightBar, 'width', `${cyChart.clientWidth}px`);
    this.rClientWidth = cyChart.clientWidth;
    Observable.fromEvent(window, 'resize')
      .subscribe((event) => {
        this.renderer2.setStyle(rightBar, 'width', `${cyChart.clientWidth}px`);
        this.rClientWidth = cyChart.clientWidth;
      });

    const companyId = +this.activatedRoute.snapshot.paramMap.get('id');
    const type = +this.activatedRoute.snapshot.paramMap.get('type');
    this.getNodeDataURL = `company/company_id?link_id=`;
    this.shipInit(companyId, type);
  }

  // 图谱初始化
  shipInit(companyId, type) {
    this.atlasMapApiService.sendAtlas(this.getChartURL(companyId, type))
      .subscribe(
        (data: any) => {
          this.chartData = data;
          this.cyInit();
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  };

  cyInit() {
    this.sel = this.chartData.BasicInfo.id;
    this.bottomBarDataSel.push(this.chartData.BasicInfo.id);
    this.cy = cytoscape({
      container: document.getElementById('cy'),
      wheelSensitivity: 0.1,
      zoom: 1.1,
      layout: {
        name: 'preset',
        fit:false
      },
      style: CyStyle
    });
    this.dataInit(this.chartData, { x: 800, y: 400 });
    if(this.ueSwitch){
      this.cy.$(`#${this.sel}`).addClass('select');
    }

    let self = this;
    this.cy.on('tap', 'node', (e) => {
        clearTimeout(this.banDbClick);
        this.banDbClick = setTimeout(() => {
          let node = e.target;
          if (node.data().label === 3) {
            this.noInfo = true;
            setTimeout(() => {
              this.noInfo = false;
            }, 2000);
            return;
          }
          // if (!this.sels[node.id()]) { 判断是否为当前节点是否加载本地缓存处理 wkm 20180517
          if (1==1){
            this.atlasMapApiService.sendAtlas(this.getChartURL(node.data().link_id, node.data().label))
              .subscribe(
                (data: any) => {
                  if (data.BasicInfo && data.EntityList.length > 0) { //当数据不为空的时候
                    this.clickAction(node);
                    let p = node.position();
                    let eles = this.dataInit(data, p);
                    this.cy.animate({   //将当前节点移至中央
                      center: {eles: node}
                    }).delay(500);
                    for (let i = 0; i < eles.nodes.length; i++) {
                      if (this.cy.$(`#${eles.nodes[i].data.id}`).hasClass('hidden')) {
                        // this.eleAction(this.cy.$('#'+eles.nodes[i].data.id), 'remove'); //当前节点为点击节点时 显示点击节点所有关系节点
                      }
                    }
                    if (this.ueSwitch) {  //交互模式切换
                      this.hideAll(this.sel, this.leftMark);
                    } else {
                      this.hideAll(data.BasicInfo.id, {});
                      let id = data.BasicInfo.id;
                      this.cy.edges().forEach((n) => {
                        if(n.data().source === id && n.hasClass('hiddening')){
                          this.leftBarDataSel[id][n.data().type][n.id()] = false;
                        } else if (n.data().target === id && n.hasClass('hiddening')){
                          this.leftBarDataSel[id]['t'+n.data().type][n.id()] = false;
                        }
                      });
                    }
                    this.sel = data.BasicInfo.id;
                  }else {
                    this.noInfo = true;
                    setTimeout(() => {
                      this.noInfo = false;
                    }, 2000);
                    this.modalRef = this.modalService.show(this.messageTemplate,  {class: 'modal-sm', ignoreBackdropClick: true});
                  }
                },
                (error: any[]) => console.log('Error: ' + error)
              );
          }
          else{
            this.clickAction(node);
            this.getCache(node);
          }
        },500);
    });

  }

  //初始化请求的数据
  dataInit(expJson, position) {
    let id = expJson.BasicInfo.id;
    let relations={
      1: [], t1: [], 2: [], t2: [], 3: [], 4: [], 5: [], t5: [], 6: [], 7: [], 8: [], 9: [], 10: [], 11: [], 12: [], t6: []
    };
    let tem = {};
    for(let i = 0; i < expJson.EntityList.length; i++) {
      tem[expJson.EntityList[i].id] = expJson.EntityList[i];
    }
    let mainNode = this.chartUtil.getNode1(tem[id], 'clicked', position);

    expJson.RelationList.forEach((n) => {
      if (n.source_id === id) {
        relations[n.relation_type].push({ edge: n, node: tem[n.target_id] });
      } else if (n.target_id === id) {
        if (!relations[`t${n.relation_type}`]) {
          relations[`t${n.relation_type}`] = [];
        }
        relations[`t${n.relation_type}`].push({ edge: n, node: tem[n.source_id], status: null });
      }
    });

    this.cy.$(`#${id}`).connectedEdges().forEach((n) => {
      if (n.data('source') === id) {
        let arr = relations[n.data('type')];
        if (arr.length === 0) {
          arr.push({ edge: n.data(), node: this.cy.$(`#${n.data('target')}`).data() });
        } else {
          let flag = true;
          for (let i = 0; i < arr.length; i++) {
            if (arr[i].edge.id === n.id()) {
              flag = false;
              break;
            }
          }
          if (flag) {
            arr.push({ edge: n.data(), node: this.cy.$(`#${n.data('target')}`).data() });
          }
        }
      } else if (n.data('target') === id) {
        let arr = relations[`t${n.data('type')}`];
        if (arr.length === 0) {
          arr.push({ edge: n.data(), node: this.cy.$(`#${n.data('source')}`).data() });
        } else {
          let flag = true;
          for (let i = 0; i < arr.length; i++) {
            if (arr[i].edge.id === n.id()) {
              flag = false;
              break;
            }
          }
          if (flag) {
            arr.push({ edge: n.data(), node: this.cy.$(`#${n.data('source')}`).data()});
          }
        }
      }
    });

    this.relations = relations;

    this.sels[id] = expJson.BasicInfo;

    let elements = this.chartUtil.draw(relations, position);

    elements.nodes.forEach((n) => {
      if (n.data.id === mainNode.data.id) {
        elements.nodes.splice(elements.nodes.indexOf(n), 1, mainNode)
      }
    });

    elements.nodes.push(mainNode);
    this.cy.add( elements );

    this.selEdges[this.cy.$(`#${this.sel}`).edgesWith(`#${id}`).id()] = true;

    this.allEdges = this.cy.edges();
    this.leftBarDataSel[id] = {
      1:{}, t1:{}, 2:{}, t2:{}, 3:{}, 4:{}, 5:{}, t5:{}, 6:{}, 7:{}, 8:{}, 9:{}, 10:{}, 11:{}, 12:{}, t6:{}, t11:{}, t4:{}, t3:{}
    };
    this.allEdges.forEach((n) => {
      if (n.data().source == id && !n.hasClass('hidden')) {
        this.leftBarDataSel[id][n.data().type][n.id()] = true;
      } else if (n.data().target == id && !n.hasClass('hidden')) {
        let type = 't' + Number(n.data().type);
        this.leftBarDataSel[id][type][n.id()] = true;
      }
    });
    return elements;
  }



  clickAction(node) {
    if (this.ueSwitch) {
      if (this.bottomBarDataSel.indexOf(node.id())!=-1) {
        this.bottomBarDataSel.splice(this.bottomBarDataSel.indexOf(node.id()), 1);
        this.bottomBarDataSel.unshift(node.id());
      } else {
        this.bottomBarDataSel.unshift(node.id());
      }
    }
    if (node.id() === this.sel) {
      this.cy.animate({
        center: { eles: node }
      }).delay(500);
      return;
    }
    let ids = [node.id() + '', this.sel + ''];
    if(this.ueSwitch){
      this.cy.$(`#${ids[1]}`).removeClass('select');
      node.classes('clicked select');
    }
  }

  hiddenEdge(ele) {
    let index = 0;
    ele.connectedEdges().forEach((j) => {
      if (!j.hasClass('hiddening')) {
        index++;
        return;
      }
    });
    if(!index && !this.ueSwitch){
      this.eleAction(ele, 'add');
    }else{
      if (!index && !this.sels[ele.id()]) {
        this.eleAction(ele, 'add');
      }
    }
  }
  showEdge(ele) {
    if (ele.hasClass('hiddening')) {
      this.eleAction(ele,'remove');
    }
  }

  // 类图形显示或隐藏
  elesAction(act, type, id, ids) {
    let isIds = (id1) => {
      if (!ids) {
        return true;
      }
      if (this.selEdges[id1]) {
        return false;
      }
      return !ids[id];
    };
    let action = {
      add: (n, t) => {
        let index = 0;
        n[t]().connectedEdges().forEach((j) => {
          if (!j.hasClass('hiddening')) {
            index++;
            return;
          }
        });
        return !index;
      },
      remove: (n,t) => {
        return n[t]().hasClass('hidden');
      }
    };
    this.cy.batch(() => {
      let aims = null, t = null, types;
      if (type.indexOf('t') === -1) {
        aims = 'source';
        t = 'target';
        types = type;
      } else {
        aims = 'target';
        t = 'source';
        types = type.substring(1, type.length);
      }
      this.cy.$(`#${id}`).connectedEdges().forEach((n) => {
        if (types === n.data('type') && n.data(aims) === id && isIds(n.id())) {
          this.eleAction(n, act);
          if (action[act](n, t)) {
            this.eleAction(n[t](), act);
          }
        }
      });
    });
  }
  // 图形显示或隐藏
  eleAction(ele, action) {
    if (action === 'add') {
      ele[action+'Class']('hiddening');
      if (this.ueSwitch) {
        setTimeout(() => {
          ele[`${action}Class`]('hidden');
        },550);
      } else {
        ele[action+'Class']('hidden');
      }
    } else {
      ele[action+'Class']('hiddening');
      ele[action+'Class']('hidden');
    }
  }

  hideClass(type, id, ids?) {
    this.elesAction('add', type, id, ids);
  }
  showCLass(type, id, ids?) {
    this.elesAction('remove', type, id, ids);
  }
  hideAll(id, ids) {
    let id1 = id;
    if (ids) {
      if (!this.ueSwitch) {
        id1 = this.sel;
        this.cy.$(`#${id1}`).connectedEdges().forEach((n) => {
          if(!n.hasClass('hidden')){
            this.selEdges[n.id()]=true;
          }
        });
      }
      for (let i in this.relations) {
        for (let j = 0; j < this.relations[i].length; j++) {
          this.cy.$(`#${id1}`).edgesWith(`#${this.relations[i][j].node.id}`).forEach((n) => {
            this.selEdges[n.id()]=true;
          });
        }
      }
      for(let i in this.relations){
        this.hideClass(i, id, ids);
      }
    } else {
      for(let i in this.relations){
        this.hideClass(i, id);
      }
    }
  }
  showAll(id, ids) {
    for (let i in this.relations) {
      this.showCLass(i, id, ids);
    }
  }

  setLeftMark(id) {
    if (!this.leftMark[id]) {
      this.leftMark[id] = this.leftBarDataSel[id];
    }
  }

  // 获取本地缓存
  getCache(node) {
    let relations={
      1:[], t1:[], 2:[], t2:[], 3:[], 4:[], 5:[], t5:[], 6:[], 7:[], 8:[], 9:[], 10:[], 11:[], 12:[], t6:[]
    };
    node.connectedEdges().forEach((n) => {
      if (n.data('source') === node.id()) {
        relations[n.data('type')].push({ edge: n.data(), node: n.target().data() });
      } else if (n.data('target') === node.id()) {
        if (!relations[`t${n.data('type')}`]) {
          relations[`t${n.data('type')}`] = [];
          relations[`t${n.data('type')}`].push({ edge: n.data(), node: n.source().data() });
        }
      }
    });
    if(this.ueSwitch) { //交互模式切换
      this.showAll(node.id(), this.leftMark);
    }
    this.relations = relations;
    if(this.ueSwitch) { //交互模式切换
      this.hideAll(this.sel, this.leftMark);
    }
    this.cy.animate({
      center: { eles: node }
    }).delay(500);
    this.sel = node.id();
  }

  leftBar($event) {
    if ($event['type'] === 1) {
      this.riskSel();
    }
    if ($event['type'] === 2) {
      this.clickToggle($event['e'], $event['i'], $event['index']);
    }
    if ($event['type'] === 3) {
      this.classFlag($event['i'], $event['index']);
    }
    if ($event['type'] === 4) {
      this.clickBox($event['id'], $event['i'], $event['flag']);
    }
  }

  // 风险信息过滤
  riskSel() {
    if (this.riskSelF) {
      this.reskSels = [];
      this.riskSelF = false;
      this.renderer2.setStyle(this.chart.nativeElement, 'background', 'rgb(255, 255, 255)');
      this.cy.batch(() => {
        this.cy.elements().removeClass('faded');
      });
    } else {
      let reskSels = [];
      this.riskSelF = true;
      this.renderer2.setStyle(this.chart.nativeElement, 'background', 'rgb(15, 15, 15)');
      this.cy.batch(() => {
        this.cy.elements().addClass('faded');
        this.cy.nodes().forEach((n) => {
          if (n.data('risk_label') && !n.hasClass('hidden')) {
            reskSels.push(n.data());
          }
        });
      });
      this.reskSels = reskSels;
    }
  }

  clickToggle(e, type, index) {
    if (e) {
      e.stopPropagation();
    }
    this.setLeftMark(this.sel);

    let flag = this.classFlag(type, index);
    if (!this.util.isEmpty(this.leftBarDataSel[this.sel][type])) {
      this.toggle[index] = !this.toggle[index];
    }

    if (flag) {
      this.hideClass(type, this.sel);
      for (let i in this.leftBarDataSel[this.sel][type]) {
        this.leftBarDataSel[this.sel][type][i] = false;
      }
    } else {
      this.showCLass(type,this.sel);
      for (let i in this.leftBarDataSel[this.sel][type]) {
        this.leftBarDataSel[this.sel][type][i] = true;
      }
    }
  }

  // 左侧类别控制器样式变更
  classFlag(type, index) {
    if (this.leftBarDataSel[this.sel]) {
      for (let i in this.leftBarDataSel[this.sel][type]) {
        if (this.leftBarDataSel[this.sel][type][i]) {
          this.toggle[index] = true;
          return true;
        }
      }
      this.toggle[index] = false;
      return false;
    }
  }

  // 左侧控制栏
  clickBox(id, type, flag) {
    this.setLeftMark(this.sel);
    let ele = this.cy.$(`#${id}`);
    if (flag) {
      this.cy.batch(() => {
        this.eleAction(ele,'remove');
        this.showEdge(ele.target());
        this.showEdge(ele.source());
      });
    } else {
      this.cy.batch(() => {
        this.eleAction(ele,'add');
        this.hiddenEdge(ele.target());
        this.hiddenEdge(ele.source());
      });
    }
  };

  printComplete($event) {
    if ($event === 1) {
      let tem=[];
      this.cy.elements().forEach((n) => {
        if(n.hasClass('hidden')){
          tem.push(this.cy.remove(n));
        }
      });
      this.printSrc = this.cy.png({bg: "#fff", full: true});
      tem.forEach((n) => {
        n.restore();
      });
      this.printComponent.print();
    }
    if ($event['type'] === 2) {
      this.riskSel();
    }
  }

  // 请求图谱接口地址
  getChartURL(companyId, type): string {
    return this.getNodeDataURL + companyId + '&label=' + type;
  }

  //关闭模态框
  modalRefOff(){
    this.modalRef.hide();
  }
}
