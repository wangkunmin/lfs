import { Component, Input, AfterViewChecked } from '@angular/core';

import { CompanyRiskApiService } from '../../common/api/company-risk-api.service';
import { CommonUtil } from '../../common/utill/common-util';

declare let require: any;

@Component({
  selector: 'app-company-chart',
  templateUrl: './company-chart.component.html',
  styleUrls: ['./company-chart.component.css']
})
export class CompanyChartComponent implements AfterViewChecked {
  @Input() companyName: string;

  private checkedFlag: boolean;

  constructor(
    private companyRiskApiService: CompanyRiskApiService,
    private util: CommonUtil
  ) {
    this.checkedFlag = true;
  }



  ngAfterViewChecked() {
    if (this.checkedFlag && !this.util.isSingleEmpty(this.companyName)) {
      this.checkedFlag = false;
      this.getChartData();
    }
  }

  getChartData() {
    this.companyRiskApiService.getThumbnail(this.util.base64encode(this.companyName))
      .subscribe(
        (data: any) => {
          setTimeout(() => {
            if (data.status === 404) {
              return;
            }
            this.chartInit(data.NodeEdgesCount);
          }, 0);

        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }

  chartInit(data) {
    let ele = [], target = [], sourceKeys = [], styles = [], colors = [];
    colors = ['#e61c17', '#f98d24', '#3FAD59', '#1872B0'];
    styles = [
      {
        selector: 'node',
        style: {
          'content': 'data(name)',
          'color': '#333',
          'font-family': 'Microsoft YaHei',
          'font-size': 12,
          'font-weight': 400,
        }
      },
      {
        selector: 'node[label = "key"]',
        style: {
          'width': 24,
          'height': 24,
          'background-color': '#999'
        }
      },
      {
        selector: 'edge',
        css: {
          'width': 1
        }
      }
    ];

    let i = 0;
    Object.keys(data.edges).map((key) => {
      target = Object.keys(data.edges[key]);
      for (let nTarget of target) {
        let edge:any = {
          data: {
            source: key,
            target: nTarget,
            type: i
          }
        };
        sourceKeys.push(key);
        styles.push({
          selector: `edge[type = ${i}]`,
          style: {
            'line-color': colors[(i + 1) % colors.length]
          }
        });
        ele.push(edge);
        i++;
      }
    });

    let j = 0;
    Object.keys(data.nodes).map((key) => {
      let node: any = {
        data: {
          id: data.nodes[key].id,
          name: data.nodes[key].name
        }
      };
      if (sourceKeys.includes(key)) {
        node.data.label = 'key';
      } else {
        node.data.label = j;
        styles.push({
          selector: `node[label = ${j}]`,
          style: {
            'width': 16,
            'height': 16,
            'background-color': colors[(j + 1) % colors.length]
          }
        });
        j++;
      }
      ele.push(node);
    });

    let cytoscape = require('cytoscape');

    cytoscape({
      container: document.getElementById('cy'),
      elements: ele,
      style: styles,
      layout: { name: 'grid'},
      zoomingEnabled: false,
      panningEnabled: false
    });
  }
}
