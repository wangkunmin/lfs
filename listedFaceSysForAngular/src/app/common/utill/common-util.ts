import { NotShowColumn, RptTimeTypeCd } from '../constant/finance.const';

export class CommonUtil {

  private followCompanyPageFlag = true;

  setFollowCompanyPageFlag(t) {
    this.followCompanyPageFlag = t;
  }

  getFollowCompanyPageFlag() {
    return this.followCompanyPageFlag;
  }

  constructor() {
  }

  /*必须以4-20位字母、数字或“_”，字母开头*/
  rexSrc(src: string, minLen, maxLen, isHead?) {
    if (src.length > maxLen || src.length < minLen) {  //长度判断
      return false;
    }
    let flag = false;
    let rex1 = /[a-zA-Z]/;
    if (isHead == true) {
      let rex2 = /^[0-9]/;
      if (rex2.test(src)) {
        flag = true
      }
    }
    let headSrc = src.substring(1, 0);
    if (rex1.test(headSrc)) {
      flag = true
    }
    if (flag) {
      let nameRex = /^\w+$/;
      if (nameRex.test(src)) {
        return true;
      }
    } else {
      return false;
    }
  }

  /*必须以4-20位字母或数字*/
  rexSrc2(src: string) {
    let rex = /^[a-zA-Z0-9]{4,20}$/;
    if (!rex.test(src)) {
      return false;
    }
    return true;
  }


  //获取随机数方法min 最小 max最大值
  GetRandomNum(Min, Max) {
    let Range = Max - Min;
    let Rand = Math.random();
    return (Min + Math.round(Rand * Range));
  }

  //时间格式化方法
  //  let retTime = this.mms.dateFormat(new Date(),'yyyy-MM-dd');  // '2017-01-11'
  dateFormat(inData?: Date, format?: string) {
    if (inData == undefined || (inData + '') == '' || (inData + '') == 'null') {
      return null;
    }
    let o = {
      'M+': inData.getMonth() + 1,                 //月份
      'd+': inData.getDate(),                    //日
      'h+': inData.getHours(),                   //小时
      'm+': inData.getMinutes(),                 //分
      's+': inData.getSeconds(),                 //秒
      'q+': Math.floor((inData.getMonth() + 3) / 3), //季度
      'S': inData.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(format)) {
      format = format.replace(RegExp.$1, (inData.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (let k in o) {
      if (new RegExp('(' + k + ')').test(format)) {
        format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
      }
    }
    return format;
  }

  //对象处理 data [Object,Object,...]  增加的属性值nodeAttribute {value:'  ',value12:'  '...}   rowIndex_my节点行次值,默认0开始
  dataHandle(data, nodeAttribute?: Object, rowIndex_my?) {
    if (data != null && data != undefined && data.length > 0) {
      if (rowIndex_my == undefined) {
        rowIndex_my = 0;
      }
      for (let row of data) {
        let rowIndexObj = {
          rowIndex_my: rowIndex_my++,
        }
        row = Object.assign(row, nodeAttribute, rowIndexObj);
        if (row.children) {
          if (row.children.length > 0) {
            let returnData = this.dataHandle(row.children, nodeAttribute, rowIndex_my);
            rowIndex_my = returnData[returnData.length - 1].rowIndex_my + 1;
          } else {
            return data;
          }
        }
      }
      return data;
    } else {
      return -1;
    }
  }

  // 通过字面量方式实现的函数each
  each(object, callback) {
    let type = (function () {
      switch (object.constructor) {
        case Object:
          return 'Object';
        // break;
        case Array:
          return 'Array';
        // break;
        case NodeList:
          return 'NodeList';
        // break;
        default:
          return 'null';
        // break;
      }
    })();
    // 为数组或类数组时, 返回: index, value
    if (type === 'Array' || type === 'NodeList') {
      // 由于存在类数组NodeList, 所以不能直接调用every方法
      [].every.call(object, function (v, i) {
        return callback.call(v, i, v) === false ? false : true;
      });
    }
    // 为对象格式时,返回:key, value
    else if (type === 'Object') {
      for (let i in object) {
        if (callback.call(object[i], i, object[i]) === false) {
          break;
        }
      }
    }
  }

  //金额格式化   12345格式化为12,345.00
  financeMoney(data: any, num: number): string {
    if (data) {
      num = num > 0 && num <= 20 ? num : 2;
      data = parseFloat((data + '').replace(/[^\d\.-]/g, '')).toFixed(num) + '';
      let lNum = data.split('.')[0].split('').reverse(),
        rNum = data.split('.')[1];
      let temp = '';
      for (let i = 0; i < lNum.length; i++) {
        temp += lNum[i] + ((i + 1) % 3 == 0 && (i + 1) != lNum.length ? ',' : '');
      }
      let ret = temp.split('').reverse().join('') + '.' + rNum;
      ret = ret.replace('-,', '-');  //将 -,123.40  转换为 -123.40
      return ret;
    }
  }

  //取消金额格式化
  removeMoney(data: string): number {
    return parseFloat(data.replace(/[^\d\.-]/g, ''));
  }

  // 判断访问者的浏览器是否是IE
  checkBrowserIsIE(): boolean {
    let result = false;
    let browser = navigator.appName;
    if (browser === 'Microsoft Internet Explorer') {
      result = true;
    }
    return result;
  }

  // 执行按键命令
  keyDown($event): boolean {
    let keyCode = 0,
      result = false;
    if (this.checkBrowserIsIE()) {
      keyCode = $event.keyCode;
    } else {
      keyCode = $event.which;
    }
    if (keyCode === 13) {
      result = true;
    }
    return result;
  }

  // map转化为对象（map所有键都是字符串，可以将其转化为对象）
  mapToObject(map) {
    let object = Object.create(null);
    map.forEach((value, key) => {
      object[key] = value;
    });
    return object;
  }

  // 对象转化为map
  objectToMap(object) {
    let map = new Map();
    for (let key of Object.keys(object)) {
      map.set(key, object[key]);
    }
    return map;
  }

  // 检查值是否为空
  isSingleEmpty(data): boolean {
    if (null === data || typeof data === 'undefined' || data.length === 0) {
      return true;
    }
    return false;
  }

  // 检查对象是否为空
  isEmpty(data): boolean {
    for (let name in data) {
      return false;
    }
    return true;
  }

  // 获取报表类型
  getRptTimeType(code: string): string {
    for (let i = 0; i < RptTimeTypeCd.length; i++) {
      if (RptTimeTypeCd[i].PARAMCODE === code) {
        return RptTimeTypeCd[i].PARAMCHNAME;
      }
    }
    return '';
  }

  // 获取报表日期
  getRptDtBak(date: string): string {
    if (date) {
      return `${date.substr(0, 4)}年${date.substr(4, 2)}月`;
    }
  }

  // 获取报表title
  getRptDtTitle(date: string): string {
    if (date) {
      return `${date.substr(0, 4)}年${date.substr(4, 2)}月${date.substr(6, 2)}日`;
    }
  }

  // 过滤掉false，null，0，''，undefined, NaN的值
  compact(array): Array<any> {
    return array.filter((data) => {
      return !(!data || data === '');
    });
  }

  // 如果list包含指定的value则返回true  _.contains([1,2,3], 3); => true
  container(array, key) {
    for (let i = 0; i < array.length; i++) {
      if (array[i] === key) {
        return true;
      }
    }
  }

  // 根据数组中对象的莫一个个属性值进行升序或者降序排列
  sortBy(attr, rev?) {
    if (rev === undefined) {
      rev = 1;
    } else {
      rev = (rev) ? 1 : -1;
    }
    return (a, b) => {
      a = a[attr];
      b = b[attr];
      if (a < b) {
        return rev * -1;
      }
      if (a > b) {
        return rev * 1;
      }
      return 0;
    }
  }

  // .arrayContainer([1,2,3], {'1': 'hello'}, true) => [2, 3]
  arrayContainer(array, object, flag) {
    let keys = Object.keys(object);
    let arr = [];
    for (let i = 0; i < keys.length; i++) {
      if (flag) {
        if (array[i] === keys[i]) {
          array.push(keys[i]);
        }
      } else {
        if (array[i] !== keys[i]) {
          array.push(keys[i]);
        }
      }
    }
  }

  /**
   * 数据转换
   * @param data  数据
   * @param str   对象中的属性值 例如（上市前/上市后）
   * @param type  规则对象
   */
  typeFilter(data, str, type) {
    for (let i = 0; i < data.length; i++) {
      if (data[i].subjectNm === str) {
        let keys = Object.keys(data[i]);
        for (let j = 0; j < keys.length; j++) {
          if (!this.container(NotShowColumn, keys[j])) {
            let typeKeys = Object.keys(type);
            if (this.container(typeKeys, data[i][keys[j]] + '')) {
              data[i][keys[j]] = type[data[i][keys[j]]];
            }
          }
        }
        break;
      }
    }
  };

  //时间处理201801  -->  2018-01      20180102  -->  2018-01-02    splitSrc : - 或者 / 分割符
  timeToTimeSrc(time, splitSrc?) {
    if (splitSrc == undefined) {
      splitSrc = '-';
    }
    let y = (time + '').substr(0, 2);
    let timeTemp = (time + '').substr(2);
    let timeList = timeTemp.split('');
    let len = timeList.length;
    if (len == 0) {
      return '';
    }
    let timeSrc = '';
    for (let i = 0; i < len; i++) {
      if ((i + 1) % 2 == 0) {
        if (i + 1 != len) {
          timeSrc = timeSrc + timeList[i] + splitSrc;
        } else {
          timeSrc = timeSrc + timeList[i];
        }
      } else {
        timeSrc = timeSrc + timeList[i];
      }
    }
    timeSrc = y + timeSrc;
    return timeSrc;
  }

  // json导出到excel
  JSONToExcelConvertor(JSONData, filename) {
    //先转化json
    let arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;

    let excel = '<table>';

    //设置数据
    for (let i = 0; i < arrData.length; i++) {
      let row = "<tr>";
      for (let j = 0; j < arrData[i].length; j++) {
        let value = arrData[i][j].value === "." ? "" : arrData[i][j].value;
        row += '<td>' + value + '</td>';
      }
      excel += row + "</tr>";
    }
    excel += "</table>";
    let excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
    excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
    excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
    excelFile += '; charset=UTF-8">';
    excelFile += "<head>";
    excelFile += "<!--[if gte mso 9]>";
    excelFile += "<xml>";
    excelFile += "<x:ExcelWorkbook>";
    excelFile += "<x:ExcelWorksheets>";
    excelFile += "<x:ExcelWorksheet>";
    excelFile += "<x:Name>";
    excelFile += filename;
    excelFile += "</x:Name>";
    excelFile += "<x:WorksheetOptions>";
    excelFile += "<x:DisplayGridlines/>";
    excelFile += "</x:WorksheetOptions>";
    excelFile += "</x:ExcelWorksheet>";
    excelFile += "</x:ExcelWorksheets>";
    excelFile += "</x:ExcelWorkbook>";
    excelFile += "</xml>";
    excelFile += "<![endif]-->";
    excelFile += "</head>";
    excelFile += "<body>";
    excelFile += excel;
    excelFile += "</body>";
    excelFile += "</html>";

    let uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);

    let userAgent = navigator.userAgent; //取得浏览器的userAgent字符串

    if (userAgent.indexOf("Edge") > -1) { // IE, Edge
      navigator.msSaveBlob(new Blob([excelFile]), filename + ".xls");
    } else { // Chrome
      let link = document.createElement("a");
      link.href = uri;

      link.style.visibility = 'hidden';

      link.download = filename + ".xls";

      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }
  }


  //判断时间精确到0 年 1月  2日 -1错误格式  time:2018  201801   20180101
  yMD(time: string) {
    let len = (time + '').trim().length;
    if (parseInt(time + '') + '' == 'NaN') {
      return -1
    }
    if (len == 4) {
      return 0;
    } else if (len == 6) {
      return 1;
    } else if (len == 8) {
      return 2;
    } else {
      return -1;
    }
  }

  //入参2018  201801  20180101  出参
  /*{
      inputTime:time,  //入参时间  2018
      startTimeSrc : '', //开始时间  20180101
      endTimeSrc : '', //结束时间  20181231
      startTimeDate :new Date(), //开始时间
      endTimeDate :new Date(), //结束时间
      timeType : 0,  //时间类型
    };*/
  getStartEndTime(time: string) {
    let timeObj: any = {};

    let startTimeSrc = '', //开始时间
      endTimeSrc = '', //结束时间
      startTimeDate: Date, //开始时间
      endTimeDate: Date; //结束时间
    let timeType = this.yMD(time);
    if (timeType == 0) { //获取年
      startTimeSrc = time + '0101';
      endTimeSrc = time + '1231';
      startTimeDate = new Date(this.timeToTimeSrc(startTimeSrc));
      endTimeDate = new Date(this.timeToTimeSrc(endTimeSrc));

      timeObj.inputTime = time;
      timeObj.timeType = timeType;
      timeObj.startTimeSrc = startTimeSrc;
      timeObj.endTimeSrc = endTimeSrc;
      timeObj.startTimeDate = startTimeDate;
      timeObj.endTimeDate = endTimeDate;
    } else if (timeType == 1) {
      startTimeSrc = time + '01';
      let y = '', m = '', d = '';
      y = time.substr(0, 4);
      m = time.substr(4);
      let t = new Date(+y, +m, 0);
      d = t.getDate() + '';
      endTimeSrc = time + (+d < 10 ? '0' + d : d);
      startTimeDate = new Date(this.timeToTimeSrc(startTimeSrc));
      endTimeDate = new Date(this.timeToTimeSrc(endTimeSrc));

      timeObj.inputTime = time;
      timeObj.timeType = timeType;
      timeObj.startTimeSrc = startTimeSrc;
      timeObj.endTimeSrc = endTimeSrc;
      timeObj.startTimeDate = startTimeDate;
      timeObj.endTimeDate = endTimeDate;
    } else if (timeType == 2) {
      timeObj.inputTime = time;
      timeObj.timeType = timeType;
    } else {
      return -1;
    }
    return timeObj;
  }

  //base64转码
  base64encode(str) {
    let chars = ['A', 'B', 'C', 'D', 'E', 'F',
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
      'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
      'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
      't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
      '6', '7', '8', '9', '_', '-', '.'];
    let bytes = this.str2UTF8(str);
    let buf = '';
    let len = bytes.length;
    let i = 0, inp;
    while (len >= 3) {
      inp = bytes[i++] + 256 & 0xff;
      inp <<= 8;
      inp += bytes[i++] + 256 & 0xff;
      inp <<= 8;
      inp += bytes[i++] + 256 & 0xff;
      len -= 3;
      buf += (chars[(inp >> 18) & 63]);
      buf += (chars[(inp >> 12) & 63]);
      buf += (chars[(inp >> 6) & 63]);
      buf += (chars[inp & 63]);
    }
    switch (len) {
      case 0: // end of input
        break;
      case 1: // 1 byte input, 2 bytes output, and 2 pads
        inp = bytes[i++] + 256 & 0xff;
        inp <<= 16;
        buf += (chars[(inp >> 18) & 63]);
        buf += (chars[(inp >> 12) & 63]);
        buf += (chars[64]);
        buf += (chars[64]);
        break;
      case 2: // 2 bytes input, 3 bytes output, and 1 pad
        inp = bytes[i++] + 256 & 0xff;
        inp <<= 8;
        inp += bytes[i] + 256 & 0xff;
        inp <<= 8;
        buf += (chars[(inp >> 18) & 63]);
        buf += (chars[(inp >> 12) & 63]);
        buf += (chars[(inp >> 6) & 63]);
        buf += (chars[64]);
        break;
    }
    return buf;
  }

  //utf8字符串转byte数组
  str2UTF8(str) {
    let bytes = [];
    let len, c;
    len = str.length;
    for (let i = 0; i < len; i++) {
      c = str.charCodeAt(i);
      if (c >= 0x010000 && c <= 0x10FFFF) {
        bytes.push(((c >> 18) & 0x07) | 0xF0);
        bytes.push(((c >> 12) & 0x3F) | 0x80);
        bytes.push(((c >> 6) & 0x3F) | 0x80);
        bytes.push((c & 0x3F) | 0x80);
      } else if (c >= 0x000800 && c <= 0x00FFFF) {
        bytes.push(((c >> 12) & 0x0F) | 0xE0);
        bytes.push(((c >> 6) & 0x3F) | 0x80);
        bytes.push((c & 0x3F) | 0x80);
      } else if (c >= 0x000080 && c <= 0x0007FF) {
        bytes.push(((c >> 6) & 0x1F) | 0xC0);
        bytes.push((c & 0x3F) | 0x80);
      } else {
        bytes.push(c & 0xFF);
      }
    }
    return bytes;
  }

  randomPassword(size) {
    let seed: Array<string> = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
      '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'];
    let seedLength = seed.length;
    let password = '';
    let copySeedLength: number;
    for (let i = 0; i < size; i++) {
      if (i === 0) {
        copySeedLength = seedLength - 10;
      } else {
        copySeedLength = seedLength;
      }
      let j = Math.floor(Math.random() * copySeedLength);
      password += seed[j];
    }
    return password;
  }

  isIE() {
    if ('ActiveXObject' in window || navigator.userAgent.indexOf("Edge") > -1) {
      return true;
    }
  }

  //获取字符串字节数
  getStringBytesLength(str) {
    let bytesLength;
    if (typeof str === 'string') {
      bytesLength = str.replace(/[^\u0000-\u00ff]/g, "aa").length
    }
    return bytesLength;
  }
  //是否为特殊字符，true表示为特殊字符
  isSpecialStr(str) {
    let rex = /^[a-zA-Z0-9\u4e00-\u9fa5_]+$/;
    if (!rex.test(str)) {
      return true;
    }
    return false;
  }
}
