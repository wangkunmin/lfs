package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/4/27.
 */
public class RiskRateInData {
    private String companyId;
    private String companyNm;
    private String companySnm;
    private String securityCds;
    private String pkCompanyId;
    private int rating;
    private String riskContent;

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

    public String getPkCompanyId() {
        return pkCompanyId;
    }

    public void setPkCompanyId(String pkCompanyId) {
        this.pkCompanyId = pkCompanyId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRiskContent() {
        return riskContent;
    }

    public void setRiskContent(String riskContent) {
        this.riskContent = riskContent;
    }
}
