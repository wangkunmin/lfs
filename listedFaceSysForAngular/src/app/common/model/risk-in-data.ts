export class RiskInData {
  userId:string;  //用户ID
  companyId: string;    //公司ID
  pageSize:number;       //单页数据量
  page:number;      //页码
  year?:string;        //年份（YYYY）
  dateStart?:string;       //用户关注模块格式风险（YYYYMM） 事件（YYYYMMDD）
  dateEnd?:string;     //用户关注模块格式风险（YYYYMM） 事件（YYYYMMDD）
}
