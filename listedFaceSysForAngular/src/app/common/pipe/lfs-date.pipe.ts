import { Pipe, PipeTransform } from '@angular/core';
import { CommonUtil } from '../utill/common-util';

@Pipe({
  name: 'lfsDatePipe'
})
export class LfsDatePipe implements PipeTransform{
  constructor(
    private commonUtil: CommonUtil,
  ) {}

  // isSpecial为true 特殊处理
  transform(date: any,format:string,isSpecial?:boolean):string{
    if(isSpecial != true){
      if (date == undefined || date == null || date =='') {
        return '';
      }else{
        if( date instanceof Date){
          let res =  this.commonUtil.dateFormat(date,format);
          return res;
        }
        format = format.replace(/H/g,'h');
        if(typeof date === 'string'){
          date = date.replace(/-/g,'/');
          date = date.replace(/Z/g,' ');
          date = date.replace(/T/g,' ');
          date = date.replace('.0','');
          let res =  this.commonUtil.dateFormat(new Date(date),format);
          return res;
        }
      }
    }else{
      // format 分割符  -
      return this.commonUtil.timeToTimeSrc(date,format);
    }

  }

}
