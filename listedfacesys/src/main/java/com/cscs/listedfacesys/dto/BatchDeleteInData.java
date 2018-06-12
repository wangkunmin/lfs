package com.cscs.listedfacesys.dto;

public class BatchDeleteInData {

    private long userId;
    private String companyIds;

    public long getUserId() {
        return userId;
    }

    public String getCompanyIds() {
        return companyIds;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }
}
