package com.cscs.listedfacesys.dto;

import java.util.List;

public class UserFollowCompyOutData {
    private String companyId;

    private String companyNm;//公司名称

    private String followTime;//关注日期，未关注的时候为空

    private boolean isFocused;//是否关注

    private String companySnm;//公司简称

    private String securityCode;//证券代码

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public String getFollowTime() {
        return followTime;
    }

    public boolean getIsFocused() {
        return isFocused;
    }

    public void setIsFocused(boolean focused) {
        isFocused = focused;
    }

    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
