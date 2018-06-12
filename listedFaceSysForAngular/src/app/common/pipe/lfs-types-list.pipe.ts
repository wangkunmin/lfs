import { Pipe, PipeTransform } from '@angular/core';
import {Base} from "../constant/api-base.const";

@Pipe({
  name: 'lfsTypesPipe'
})
export class LfsTypesListPipe implements PipeTransform{
  constructor(
  ) {}

  transform(types):string{
    let ret  = '';
    ret = Base.types[+types];
    return ret;
  }

}
