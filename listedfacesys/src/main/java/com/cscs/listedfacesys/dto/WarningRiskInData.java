package com.cscs.listedfacesys.dto;

/**
 * create by wzy on 2018/02/07
 * 预警Top10入参类
 */
public class WarningRiskInData {

    private Long userId;        //用户ID
    private Long companyId;     //公司ID
    private String year;        //年份（YYYY），
    private String dateStart;       //用户关注模块格式风险（YYYYMM） 事件（YYYYMMDD）
    private String dateEnd;     //用户关注模块格式风险（YYYYMM） 事件（YYYYMMDD）
    private int page;       //页码
    private int pageSize;      //单页数据量
    private int timeType;      //1：近一天，2：近一周，3：近一月
    private int riskEventType;     //事件类型 0：全部风险，1：财务风险，2：治理风险，3：经营风险，4：市场风险，5：法律法规风险
    private int negativeType;      //负面类型 0：否，1：是
    private int importanceType;    //重要类型 0：否，1：是

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public int getRiskEventType() {
        return riskEventType;
    }

    public void setRiskEventType(int riskEventType) {
        this.riskEventType = riskEventType;
    }

    public int getNegativeType() {
        return negativeType;
    }

    public void setNegativeType(int negativeType) {
        this.negativeType = negativeType;
    }

    public int getImportanceType() {
        return importanceType;
    }

    public void setImportanceType(int importanceType) {
        this.importanceType = importanceType;
    }
}
