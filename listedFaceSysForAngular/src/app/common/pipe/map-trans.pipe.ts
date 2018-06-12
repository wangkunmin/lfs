import { Pipe, PipeTransform } from '@angular/core';
import { BaseMap } from '../model/base-map';
import { CommonUtil } from '../utill/common-util';

@Pipe({
  name: 'mapTrans'
})
export class MapTransPipe implements PipeTransform{

  transform(data: Object): Array<Map<string, any>> {
    if (data) {
      let array: Array<any> = new Array<Map<string, any>>();
      let baseMap: BaseMap;

      let map = new Map<string, any>();
      if (!(data instanceof Map)) {
        map = new CommonUtil().objectToMap(data);
      } else {
        map = data;
      }

      if (map !== null && map.size > 0) {
        map.forEach((value, key) => {
          baseMap = new BaseMap();

          baseMap.value = value;
          baseMap.key = key;
          array.push(baseMap);
        });

      }
      return array;
    }
  }

}
