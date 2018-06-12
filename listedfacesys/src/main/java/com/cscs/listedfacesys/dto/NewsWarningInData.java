package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/3/1.
 */
public class NewsWarningInData {
    //关键字
    private String keyword;
    //显示条数
    private int page;
    //页码
    private int pageSize;
    private Long compyId;
    //相关性
    private int relevance;
    //事件分类
    private String warningType;
    //时间分类(1:日/2:月/3:季)
    private int time;
    private Long userId;
    //新闻评价(正:1/负:2/中性:3)
    private int score;

    private String dateStart;
    private String dateEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public Long getCompyId() {
        return compyId;
    }

    public void setCompyId(Long compyId) {
        this.compyId = compyId;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
