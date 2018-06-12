package com.cscs.listedfacesys.dto;

/**
 * 负面事件记录信息入参
 */
public class NegativeInfoRecordInData {
    private String companyId;
    private String userId;
    private String announceCds;
    private String newsCds;
    private String eventDate;


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnnounceCds() {
        return announceCds;
    }

    public void setAnnounceCds(String announceCds) {
        this.announceCds = announceCds;
    }

    public String getNewsCds() {
        return newsCds;
    }

    public void setNewsCds(String newsCds) {
        this.newsCds = newsCds;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
