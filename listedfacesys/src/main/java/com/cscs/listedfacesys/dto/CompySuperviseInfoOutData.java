package com.cscs.listedfacesys.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by hj on 2018/3/23.
 */
public class CompySuperviseInfoOutData {

    private String companyId;

    private String companyNm;

    private String companySnm;

    private String securityCd;

    private List<Map<String,Object>> compySuperviseInfoList;

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

    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    public List<Map<String, Object>> getCompySuperviseInfoList() {
        return compySuperviseInfoList;
    }

    public void setCompySuperviseInfoList(List<Map<String, Object>> compySuperviseInfoList) {
        this.compySuperviseInfoList = compySuperviseInfoList;
    }
}
