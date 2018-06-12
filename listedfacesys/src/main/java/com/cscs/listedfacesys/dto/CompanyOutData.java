package com.cscs.listedfacesys.dto;


import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.entity.BondViolation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hj on 2018/2/24.
 */
public class CompanyOutData extends BaseOutData {

    private String companyCd;
    private String companyNm;
    private String regCapital;
    private String foundDt;
    private String legRepresent;
    private String businScope;
    private String mainBusin;
    private String officeAddr;
    private double dbTotal;
    private HashMap qualFactor;
    private int updtDt;
    private List<CompyGuaranteeOutData> compyGuaranteeOutData;
    private List<CompyCreditOutData> compyCredit;
    private List<AnnounceAlarmInfoData> AnnounceAlarmList;
    //是否是城投平台
    private boolean isPlatform;

    private String companySnm;

    public List<AnnounceAlarmInfoData> getAnnounceAlarmList() {
        return AnnounceAlarmList;
    }

    private String securityCds;

    public void setAnnounceAlarmList(List<AnnounceAlarmInfoData> announceAlarmList) {
        AnnounceAlarmList = announceAlarmList;
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

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
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

    public String getBusinScope() {
        return businScope;
    }

    public void setBusinScope(String businScope) {
        this.businScope = businScope;
    }

    public String getMainBusin() {
        return mainBusin;
    }

    public void setMainBusin(String mainBusin) {
        this.mainBusin = mainBusin;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public double getDbTotal() {
        return dbTotal;
    }

    public void setDbTotal(double dbTotal) {
        this.dbTotal = dbTotal;
    }

    public HashMap getQualFactor() {
        return qualFactor;
    }

    public void setQualFactor(HashMap qualFactor) {
        this.qualFactor = qualFactor;
    }

    public int getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(int updtDt) {
        this.updtDt = updtDt;
    }

    public List<CompyGuaranteeOutData> getCompyGuaranteeOutData() {
        return compyGuaranteeOutData;
    }

    public void setCompyGuaranteeOutData(List<CompyGuaranteeOutData> compyGuaranteeOutData) {
        this.compyGuaranteeOutData = compyGuaranteeOutData;
    }

    public List<CompyCreditOutData> getCompyCredit() {
        return compyCredit;
    }

    public void setCompyCredit(List<CompyCreditOutData> compyCredit) {
        this.compyCredit = compyCredit;
    }

    public String getOrgFormNm() {
        return orgFormNm;
    }

    public void setOrgFormNm(String orgFormNm) {
        this.orgFormNm = orgFormNm;
    }

    private String orgFormNm;

    public String getEmployNum() {
        return employNum;
    }

    public void setEmployNum(String employNum) {
        this.employNum = employNum;
    }

    private String employNum;

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    private String regAddr;

    public String getCompanyPh() {
        return companyPh;
    }

    public void setCompanyPh(String companyPh) {
        this.companyPh = companyPh;
    }

    private String companyPh;

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    private String companyWeb;

    public String getSharehdName() {
        return sharehdName;
    }

    public void setSharehdName(String sharehdName) {
        this.sharehdName = sharehdName;
    }

    private String sharehdName;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    private String industry;

    private List<SecurityInfoData> securityList;

    public List<SecurityInfoData> getSecurityList() {
        return securityList;
    }

    public void setSecurityList(List<SecurityInfoData> securityList) {
        this.securityList = securityList;
    }

    public List<CompyFinanceOutData> getCompyFinanceOutData() {
        return compyFinanceOutData;
    }

    public void setCompyFinanceOutData(List<CompyFinanceOutData> compyFinanceOutData) {
        this.compyFinanceOutData = compyFinanceOutData;
    }

    private List<CompyFinanceOutData> compyFinanceOutData;

    public List<BondViolation> getBondViolation() {
        return bondViolation;
    }

    public void setBondViolation(List<BondViolation> bondViolation) {
        this.bondViolation = bondViolation;
    }

    private List<BondViolation> bondViolation;


    public boolean isPlatform() {
        return isPlatform;
    }

    public void setPlatform(boolean platform) {
        isPlatform = platform;
    }


    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    public String getSecurityCds() {
        return securityCds;
    }

    public void setSecurityCds(String securityCds) {
        this.securityCds = securityCds;
    }
}

