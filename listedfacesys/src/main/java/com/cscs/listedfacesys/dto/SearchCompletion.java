package com.cscs.listedfacesys.dto;

import java.util.Set;

/**
 * Created by hj on 2018/2/26.
 */
public class SearchCompletion {

    //公司Id
    private String companyId;

    private String companyCd;
    //名称
    private String companyNm;
    //类型
    private String type;

    private Set<String> oldname;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getOldname() {
        return oldname;
    }

    public void setOldname(Set<String> oldname) {
        this.oldname = oldname;
    }
}
