package com.cscs.listedfacesys.dto;

public class UserAttentionInData {

    private Long userId;        //用户ID
    private Long companyId;     //公司ID
    private String companyNm;   //公司名称
    private Long focusType;     //关注类型：0 用户，1 公司， 2 行业
    private int page;
    private int pageSize;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public Long getFocusType() {
        return focusType;
    }

    public void setFocusType(Long focusType) {
        this.focusType = focusType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
