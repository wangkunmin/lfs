package com.cscs.listedfacesys.dto;

/**
 * create by  hj 2018-02-23
 * 用户关注公司入参实体
 */
public class UserFollowInData {
    private long userId;

    private String companyId;

    private String companyNm;

    private int page;

    private int pageSize;

    public long getUserId() {
        return userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
