import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';

@Component({
  selector: 'app-message-company',
  templateUrl: './message-company.component.html',
  styleUrls: ['./message-company.component.css']
})
export class MessageCompanyComponent implements OnInit {

  @Input() messageObj:any;
  @Output() addType = new EventEmitter<any>();

  @ViewChild('messageKey') messageKey;

  constructor(

  ) {
  }

  ngOnInit() {
    let nativeElement:any = this.messageKey.nativeElement;
    nativeElement.innerHTML = this.messageObj.ele
  }

  closeModal(){
    this.addType.emit(-1);
  }
}
