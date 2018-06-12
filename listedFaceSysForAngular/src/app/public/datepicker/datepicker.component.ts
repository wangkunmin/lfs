import {Component, DoCheck, EventEmitter, OnInit, Output} from '@angular/core';
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrls: ['./datepicker.component.css']
})
export class DatepickerComponent implements OnInit,DoCheck {
  @Output() dateType = new EventEmitter<any>();
  @Output() datePickerObj = new EventEmitter<any>();

  startDate: any =  new Date( new Date().getTime()-30*24*60*60*1000);
  endDate: any = new Date();
  oldStartDate: any;
  oldEndDate: any;

  constructor(
    private util: CommonUtil
  ) { }
  ngOnInit() {
    this.datePickerObj.emit(this);
  }
  // 监听时间
  ngDoCheck() {
    if (this.startDate !== this.oldStartDate || this.endDate !== this.oldEndDate) {
        let start = this.startDate;
        let end = this.endDate;
        if (this.util.isSingleEmpty(this.startDate)) {
          start = new Date(1970, 1, 1);
        }
        if (this.util.isSingleEmpty(this.endDate)) {
          end = new Date();
        }
        this.dateType.emit({
          startDate: start,
          endDate: end
        });
        this.oldStartDate = this.startDate;
        this.oldEndDate = this.endDate;
      }
  }

  keyUpFun(ev,date,type){
    if(date == null){
      return
    }
    if((new Date(ev.target.value+'')+'') == 'Invalid date' || (new Date(ev.target.value+'')+'') == 'Invalid Date' ){
      if(date != '时间格式错误'){
        if(type==1){
          this.startDate = new Date(1011111999, 1, 1);;
          setTimeout(()=>{
            ev.target.value = '时间输入错误(YYYY/MM/DD)';
          },50);
        }
        if(type==2){
          this.endDate = new Date(1011111999, 1, 1);;
          setTimeout(()=>{
            ev.target.value = '时间输入错误(YYYY/MM/DD)';
          },50);

        }
      }
    }
  }
}
