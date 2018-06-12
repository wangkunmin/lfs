package com.cscs.listedfacesys.dto;

/**
 * Created by huangj on 2018/3/16.
 * 关系
 */
public class RelationShipInfoData {
    private String type;//类型
    private String companyId;
    private String companyNm;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String id) {
        this.companyId = id;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String name) {
        this.companyNm = name;
    }
}
