package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/4/27.
 */
public class CompanyRiskInfoData {
    private String companyRiskId;
    private String companyId;
    private String companySnm;
    private int riskType;
    private String riskTarget;
    private String riskTypes;
    private String risksDetail;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    public int getRiskType() {
        return riskType;
    }

    public void setRiskType(int riskType) {
        this.riskType = riskType;
    }

    public String getRiskTarget() {
        return riskTarget;
    }

    public void setRiskTarget(String riskTarget) {
        this.riskTarget = riskTarget;
    }

    public String getRiskTypes() {
        return riskTypes;
    }

    public void setRiskTypes(String riskTypes) {
        this.riskTypes = riskTypes;
    }

    public String getRisksDetail() {
        return risksDetail;
    }

    public void setRisksDetail(String risksDetail) {
        this.risksDetail = risksDetail;
    }

    public String getCompanyRiskId() {
        return companyRiskId;
    }

    public void setCompanyRiskId(String companyRiskId) {
        this.companyRiskId = companyRiskId;
    }
}
