export class NegativeNewsIn {
  page: number;   //页码
  pageSize: number;   //单页大小
  dateStart?: string;
  dateEnd?: string;
  riskEventType?: number;//事件类型 0：全部风险，1：财务风险，2：治理风险，3：经营风险，4：市场风险，5：法律法规风险
  negativeType?: number;      //负面类型 0：否，1：是
  importanceType?: number;//重要类型 0：否，1：是
}
