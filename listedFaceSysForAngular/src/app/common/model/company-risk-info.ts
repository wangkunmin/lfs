export class CompanyRiskInfo {
  companyId:string;
  companyNm	:string;
  regCapital	:string;
  foundDt	:string;
  legRepresent	:string;
  businScope	:string;
  mainBusin	:string;
  officeAddr	:string;
  orgFormNm	:string;
  employNum	:string;
  regAddr	:string;
  companyPh	:string;
  companyWeb	:string;
  industry : string;
  sharehdName : string;
  companySnm	:string;
  securityList	:[
    {
      securityCd:string;
      securityNm:string;
      securitySnm:string;
    }
    ];
  securityCds:string;
}
