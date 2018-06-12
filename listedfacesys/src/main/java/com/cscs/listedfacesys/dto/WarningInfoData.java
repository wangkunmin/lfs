package com.cscs.listedfacesys.dto;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * Create by wzy 2018/02/01
 */
public class WarningInfoData implements Comparable<T>{

    private String companyId;       //公司ID
    private String companyNm;       //公司名称
    private String companySnm;       //公司简称
    private String noticeDt;        //日期
    private String type;            //类型
    private String securityCd;      //股票CD
    private String infoCd;          //公告CD
    private String industryNm;      //
    private int warnCount;          //命中指标数
    private int lowWarnCount;       //
    private int midWarnCount;       //
    private int highWarnCount;      //
    private int thisWarnCount;      //
    private String focusCompanyId;  //
    private String focusCompanyNm;  //
    private boolean isFocused;      //是否关注
    private String supervisor;      //监管人员
    private int importance;     //公告等级
    private Map content;        //
    private Map typeMap;        //下拉详情
    private String typeSecond;      //第二类别
    //无大标题，且每个小标题构造了info_cd
    private List<Map<String,String>> typeMap2;



    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getNoticeDt() {
        return noticeDt;
    }

    public void setNoticeDt(String noticeDt) {
        this.noticeDt = noticeDt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    public String getIndustryNm() {
        return industryNm;
    }

    public void setIndustryNm(String industryNm) {
        this.industryNm = industryNm;
    }

    public int getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(int warnCount) {
        this.warnCount = warnCount;
    }

    public int getLowWarnCount() {
        return lowWarnCount;
    }

    public void setLowWarnCount(int lowWarnCount) {
        this.lowWarnCount = lowWarnCount;
    }

    public int getMidWarnCount() {
        return midWarnCount;
    }

    public void setMidWarnCount(int midWarnCount) {
        this.midWarnCount = midWarnCount;
    }

    public int getHighWarnCount() {
        return highWarnCount;
    }

    public void setHighWarnCount(int highWarnCount) {
        this.highWarnCount = highWarnCount;
    }

    public String getFocusCompanyId() {
        return focusCompanyId;
    }

    public void setFocusCompanyId(String focusCompanyId) {
        this.focusCompanyId = focusCompanyId;
    }

    public String getFocusCompanyNm() {
        return focusCompanyNm;
    }

    public void setFocusCompanyNm(String focusCompanyNm) {
        this.focusCompanyNm = focusCompanyNm;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Map getContent() {
        return content;
    }

    public void setContent(Map content) {
        this.content = content;
    }

    public Map getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map typeMap) {
        this.typeMap = typeMap;
    }

    public boolean getIsFocused() {
        return isFocused;
    }

    public void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public String getInfoCd() {
        return infoCd;
    }

    public void setInfoCd(String infoCd) {
        this.infoCd = infoCd;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getThisWarnCount() {
        return thisWarnCount;
    }

    public void setThisWarnCount(int thisWarnCount) {
        this.thisWarnCount = thisWarnCount;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }

    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    public String getTypeSecond() {
        return typeSecond;
    }

    public void setTypeSecond(String typeSecond) {
        this.typeSecond = typeSecond;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public List<Map<String, String>> getTypeMap2() {
        return typeMap2;
    }

    public void setTypeMap2(List<Map<String, String>> typeMap2) {
        this.typeMap2 = typeMap2;
    }
}
