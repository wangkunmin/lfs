import { Component, OnInit } from '@angular/core';
declare var require: any;
import {ActivatedRoute} from "@angular/router";
import {AtlasMapApiService} from "../../common/api/atlas-map-api.service";
import {LoginService} from "../../common/service/login.service";

@Component({
  selector: 'app-shapes',
  templateUrl: './shapes.component.html',
  styleUrls: ['./shapes.component.css']
})
export class ShapesComponent implements OnInit {

  shipinfo: boolean;
  private str: string;
  private cy: any;
  names: any;
  cystyle: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private loginService: LoginService,
    private atlasMapApiService: AtlasMapApiService
  ) {
    this.loginService.sendLogin({ loginFlag: 0 });
    this.str = '?IdPara=';
    this.shipinfo = false;
  }

  ngOnInit() {
    this.initShip();
  }

  //通用找关系
  findShip(url, sys) {
    this.atlasMapApiService.searchShip(url + sys)
      .subscribe(
        (data: any) => {
          if (data.relationResult.length == 0) {
            this.shipinfo = true;
          } else {
            this.cystyle = {
              width:document.documentElement.clientWidth+'px',
              height:document.documentElement.clientHeight+'px'
            };

            setTimeout(() => {
              this.cy = this.relation(data, this.names);

              this.cy.on('select', (evt) => {
                this.highPath(evt.cyTarget);
              });
            }, 0)
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }

  //初始化找关系
  initShip() {
    let names: any;
    names = (this.activatedRoute.snapshot.paramMap.get('name') ).replace(/\-/,'）').replace(/\+/,'（');
    let key = (this.activatedRoute.snapshot.paramMap.get('key')).replace(/\-/,'）').replace(/\+/,'（');
    names = names.split(',');
    names.length = names.length-1;
    this.names = names;
    this.findShip(this.str, encodeURI(key));
  };


  relation(data, keys) {

    let cytoscape = require('cytoscape');

    let nodes = [], edges = [];
    for (let j = 0; j < data.entityResult.length; j++) {
      let node= {
        group: 'nodes',
        position: null,
        data: {
          id: data.entityResult[j].id,
          name: data.entityResult[j].name.slice(0,18).replace(/(.{10}(?!$))/g, "$1\n")
        },
        classes:'gudong'
      };
      nodes.push(this.highKey(node, keys, data.entityResult[j].name));
    }
    for (let j = 0; j < data.relationResult.length; j++) {
      edges.push({
        data: {
          source: data.relationResult[j].source,
          target: data.relationResult[j].target,
          classes: data.relationResult[j].direction,
          label: data.relationResult[j].relationType,
          lineColor:'#cecece'
        },
        classes: 'autorotate'
      });
    }
    let options = {
      container: document.getElementById('cy2'),
      wheelSensitivity: 0.1,
      zoom: 1.1,
      layout: {
        repulsion: 600,
        stiffness: 50,
        friction: 0.1,
        gravity: true,
        fps: 200,
        precision: 0.02
      },
      style: cytoscape.stylesheet()
        .selector('core')
        .style({
          'width': '160',
          'height':'50'
        })
        .selector('node')
        .style({
          'width': '160',
          'height':'50',
          'content': 'data(name)',
          'text-halign': 'center',
          'text-valign': 'center',
          'text-wrap': 'wrap',
          'shape': 'roundrectangle',
          'font-size':'14',
          'font-family':'Microsoft YaHei',
          'background-color': '#fff',
          'color': '#000'
        })
        .selector('edge')
        .style({
          'font-family':'Microsoft YaHei',
          'curve-style': 'bezier',
          'label': 'data(label)',
          'opacity': 1,
          'width': '2',
          'font-size':'12',
          'target-arrow-shape': 'triangle',
          'line-color': 'data(lineColor)',
          'color':'#666',
          'text-background-opacity': 1,
          'text-background-color': '#fff',
          'text-background-shape': 'roundrectangle',
          'source-arrow-color': 'data(lineColor)',
          'target-arrow-color': 'data(lineColor)'
        })

        .selector('.autorotate')
        .style({
          'edge-text-rotation': 'autorotate'
        })
        .selector('.faded')
        .style({
          'opacity': 0.25,
          'text-opacity': 0
        })
        .selector('.gudong')
        .style({
          'text-outline-color': '#E8F1F7',
          'background-color': '#E8F1F7',
          'color': '#333'
        })
        .selector('.search')
        .style({
          'text-outline-color': '#FFDB27',
          'background-color': '#FEEAE9',
          'color': '#333'
        })
        .selector(':selected')
        .style({
          'text-outline-color': '#ECF7EF',
          'background-color': '#ECF7EF',
          'color': '#333',
          'border-width': 4,
          'border-color': 'green'
        })
        .selector('.highlighted')
        .css({
          'background-color': '#61bffc',
          'line-color': '#61bffc',
          'target-arrow-color': '#61bffc',
          'transition-property': 'background-color, line-color, target-arrow-color',
          'transition-duration': '0.5s'
        }),
      elements: {
        nodes: nodes,
        edges: edges
      },
      ready: () => {}
    };
    return cytoscape(options);
  }

  highKey(node, keys, name) {
    for (let i = 0; i < keys.length; i++) {
      if (name == keys[i]) {
        node.classes += ' search';
      }
    }
    return node;
  }

  highPath(path) {
    let i = 0;
    let highlightNextEle = () => {
      if( i < path.length ){
        path[i].addClass('highlighted');
        i++;
        setTimeout(highlightNextEle, 1000);
      }
    };
    highlightNextEle();
  }

}
