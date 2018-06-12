package com.cscs.listedfacesys.dto;

/**
 * 负面新闻跟踪入参
 */
public class NegativeNewsInData {
    private int page;
    private int pageSize;
    private String dateStart;
    private String dateEnd;
    private String userId;
    private String companyNm;

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



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
