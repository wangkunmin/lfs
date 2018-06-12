package com.cscs.listedfacesys.dto;

/**
 * create by wuzy on 2018/02/13
 */
public class AnncounceEventInfoData {

    private String securityCd;      //证券CD
    private String companyId;       //公司ID
    private String companyNm;       //公司名字
    private String companySnm;       //公司名字简称
    private String noticeDt;        //公告日期
    private String srcType;     //来源
    private String type;        //分类
    private String typeSecond;      //分类标题2
    private String typeThree;       //分类标题3
    private String url;     //内容路径
    private String noticeTitle;     //公告小标题
    private String infoCd;      //公告标题Cd
    private String importance;   //风险等级

    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getNoticeDt() {
        return noticeDt;
    }

    public void setNoticeDt(String noticeDt) {
        this.noticeDt = noticeDt;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getInfoCd() {
        return infoCd;
    }

    public void setInfoCd(String infoCd) {
        this.infoCd = infoCd;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
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

    public String getTypeThree() {
        return typeThree;
    }

    public void setTypeThree(String typeThree) {
        this.typeThree = typeThree;
    }
}
