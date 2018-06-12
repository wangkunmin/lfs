package com.cscs.listedfacesys.dto;

import java.util.Set;

/**
 * Created by hj on 2018/2/26.
 * 搜索返回值
 */
public class CompanySearch {
    //公司ID
    private String companyId;
    //
    private String companyCd;
    //名称
    private String companyNm;
    //地址
    private String officeAddr;
    //注册资本
    private String regCapital;
    //业务范围
    private String businScope;
    //网址
    private String companyWeb;
    //公司状态
    private String companySt;
    //成立时间
    private String foundDt;
    //法定代表人
    private String legRepresent;
    //上市代码
    private String securityCd;

    //公司类型
    private String compayType;
    //财务报表
    private String financeData;
    //内部评级
    private String ratingHist;
    //警告
    private String warnings;

    //原因
    private String reason;

    //显示标志
    private String showFlg;

    private Set<String> oldname;


    public String getShowFlg() {
        return showFlg;
    }

    public void setShowFlg(String showFlg) {
        this.showFlg = showFlg;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getBusinScope() {
        return businScope;
    }

    public void setBusinScope(String businScope) {
        this.businScope = businScope;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getCompanySt() {
        return companySt;
    }

    public void setCompanySt(String companySt) {
        this.companySt = companySt;
    }

    public String getFoundDt() {
        return foundDt;
    }

    public void setFoundDt(String foundDt) {
        this.foundDt = foundDt;
    }

    public String getLegRepresent() {
        return legRepresent;
    }

    public void setLegRepresent(String legRepresent) {
        this.legRepresent = legRepresent;
    }

    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    public String getCompayType() {
        return compayType;
    }

    public void setCompayType(String compayType) {
        this.compayType = compayType;
    }

    public String getFinanceData() {
        return financeData;
    }

    public void setFinanceData(String financeData) {
        this.financeData = financeData;
    }

    public String getRatingHist() {
        return ratingHist;
    }

    public void setRatingHist(String ratingHist) {
        this.ratingHist = ratingHist;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Set<String> getOldname() {
        return oldname;
    }

    public void setOldname(Set<String> oldname) {
        this.oldname = oldname;
    }
}
