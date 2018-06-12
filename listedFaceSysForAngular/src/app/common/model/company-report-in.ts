export class CompanyReportIn {
  companyId: number;
  subjectType: number;        // 科目类型  1：资产负债表 2: 利润表 3: 现金流量表
  rptTimetypeCd: string;      // 报表时间类型  1：年报 2：中报 3：一季报 4：三季报
  rptDt: string;              // 报表日期  0：最新 1：3年 2: 5年 3: 10年 4：上市以来
}
