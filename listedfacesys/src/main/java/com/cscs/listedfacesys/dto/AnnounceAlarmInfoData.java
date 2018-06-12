package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Created by hj on 2018/2/24.
 */
public class AnnounceAlarmInfoData extends BaseOutData {
    //证券CD
    private String securityCd;
    //公司ID
    private String companyId;
    //公司名字
    private String companyNm;
    //公告日期
    private String noticeDt;
    //来源
    private String srcType;
    //分类
    private String type;
    //URL
    private String url;
    //标题
    private String noticeTitle;
    private String infoCd;
    private String importance;

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
}
