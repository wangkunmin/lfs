import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-financial-chart',
  templateUrl: './financial-chart.component.html',
  styleUrls: ['./financial-report.component.css']
})
export class FinancialChartComponent implements OnInit {
  @Input() columnDefs: Array<any>;
  @Input() chartData: Object;

  @Output() financeChartType = new EventEmitter<any>();

  options: any;
  chartsFinancial:any = {
    'min-width':'568px',
  };

  constructor() { }

  ngOnInit() {
    let lData = [];
    let xData = [];
    let yData = [];

    let lReg = new RegExp("-", "g");
    let yReg = new RegExp(",", "g");

    for (let i = 0; i < this.columnDefs.length; i++) {
      if (i === 0) {
        lData.push(this.chartData[this.columnDefs[i]['field']].replace(lReg, ''));
      }
      if (i > 0) {
        xData.push(this.columnDefs[i]['headerName'].substr(0, 7));
        let data = this.chartData[this.columnDefs[i]['field']];
        data ? data = data.replace(yReg, '') : '';
        yData.push(data);
      }
      let len = this.columnDefs.length;
      let width = (len*75 + 70) + 'px';
      this.chartsFinancial.width = width;
    }
    this.options = {
      color: ['#3398DB'],
      tooltip: {
        trigger: 'axis',
      },
      grid: {
        left: '2%',
        right: '3%',
        bottom: '3%',
        containLabel: true
      },
      legend: {
        data: lData
      },
      xAxis: {
        type: 'category',
        data: xData
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        name: lData[0],
        type: 'bar',
        barWidth: '40',
        data: yData
      }]
    };
  }

  cancel() {
    this.financeChartType.emit({type: 0});
  }
}
