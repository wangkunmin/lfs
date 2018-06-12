package com.cscs.listedfacesys.dto;

import java.util.List;

public class BatchAddInData {
    private long userId;

    private List<UserFollowInData> list;

    public long getUserId() {
        return userId;
    }

    public List<UserFollowInData> getList() {
        return list;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setList(List<UserFollowInData> list) {
        this.list = list;
    }
}
