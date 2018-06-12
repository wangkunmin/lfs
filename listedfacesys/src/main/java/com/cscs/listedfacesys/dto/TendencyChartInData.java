package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/2/2.
 * 热点新闻入参
 */
public class TendencyChartInData {

    private Long userId;
    //热点新闻发布日期
    private String pos_Dt;

    private String startDate;
    private String endDate;


    private String companyId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPos_Dt() {
        return pos_Dt;
    }

    public void setPos_Dt(String pos_Dt) {
        this.pos_Dt = pos_Dt;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
