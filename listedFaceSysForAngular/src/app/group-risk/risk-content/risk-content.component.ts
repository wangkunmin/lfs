import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-risk-content',
  templateUrl: './risk-content.component.html',
  styleUrls: ['../group-risk.component.css']
})
export class RiskContentComponent {
  @Input() flag: any;

  constructor() {

  }
}
