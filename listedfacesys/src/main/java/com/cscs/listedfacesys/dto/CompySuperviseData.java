package com.cscs.listedfacesys.dto;

public class CompySuperviseData {
    private Long compyId;
    private String userIds;
    private Long updtBy;
    private String updtTs;   // 时间
    private String operationType; //操作类型 1：新增，2：删除


    public Long getCompyId() {
        return compyId;
    }

    public void setCompyId(Long compyId) {
        this.compyId = compyId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Long getUpdtBy() {
        return updtBy;
    }

    public void setUpdtBy(Long updtBy) {
        this.updtBy = updtBy;
    }

    public String getUpdtTs() {
        return updtTs;
    }

    public void setUpdtTs(String updtTs) {
        this.updtTs = updtTs;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
