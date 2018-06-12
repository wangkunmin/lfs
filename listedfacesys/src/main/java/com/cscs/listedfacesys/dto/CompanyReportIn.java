package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Created by hj on 2018/2/28.
 */
public class CompanyReportIn extends BaseOutData {

    //企业标识符
    private long companyId;

    //科目类型  1：资产负债表 2: 利润表 3: 现金流量表
    private int subjectType;

    //报表时间类型  1：年报 2：中报 3：一季报 4：三季报
    private String rptTimetypeCd;

    //报表日期  0：最新 1：3年 2: 5年 3: 10年 4：上市以来
    private String rptDt;


    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(int subjectType) {
        this.subjectType = subjectType;
    }

    public String getRptTimetypeCd() {
        return rptTimetypeCd;
    }

    public void setRptTimetypeCd(String rptTimetypeCd) {
        this.rptTimetypeCd = rptTimetypeCd;
    }

    public String getRptDt() {
        return rptDt;
    }

    public void setRptDt(String rptDt) {
        this.rptDt = rptDt;
    }

}
