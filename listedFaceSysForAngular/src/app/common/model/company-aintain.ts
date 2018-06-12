export class RiskRateIn { //编辑风险评级信息入参
  companyId: string;
  companyNm: string;
  pkCompanyId?: string;  //风险表主键
  ratingSrc?: string;  //风险表主键
  rating: number;  //风险类型  1高 2次 3关注 4正常
  riskContent: string; //风险描述
  companySnm: string;
  securityCds: string;
}

export class CompanyRiskIn { //编辑风险类型信息入参
  companyId: string;
  companyRiskId?: string;  // 关联表主键
  companySnm: string;
  companyNm:string;
  riskType: number;  // 风险类型
  riskTypeSrc?: string;  // 风险类型
  // { key:1,value:'治理风险'},
  /*{  key:2,value:'信息披露'},
    { key:3,value:'财务会计'},
    { key:4,value:'其他'},*/
  riskTarget: string; //风险指标大 文本框 200字节
  risksTypes: string;  //风险类别
  riskDetail?: string ; // 风险详情      600
  risksDetail?:string
}


export class CompanyBaseInfo { //编辑公司基本信息入参
  companyId:string;  //公司Id
  companyCd:string;  //公司代码
  companyNm:string;  //公司名称
  companySnm:string; //公司简称
  regAddr:string;  // 注册地址
  officeAddr?:string;  //办公地址
  securityCds?:string;  //证券代码
  positionX:string;     //坐标点X轴
  positionY:string;   //坐标点Y轴
}
