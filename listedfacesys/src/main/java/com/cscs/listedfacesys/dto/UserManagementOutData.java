package com.cscs.listedfacesys.dto;

/**
 * 用户权限信息
 * create by WZY on 2018/03/06
 */
public class UserManagementOutData {

    private Long userId;        //用户Id
    private String userNm;      //用户昵称
    private Long accountId;     //账户ID
    private String accountNm;   //账户名
    private String regeditDt;   //注册时间
    private int activityType;  //激活类型
    private String roleId;      //权限ID
    private String roleNm;      //角色名称
    private String authorizeCd; //授权码编码
    private String authorizeNm; //授权码名称

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccountNm() {
        return accountNm;
    }

    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm;
    }

    public String getRegeditDt() {
        return regeditDt;
    }

    public void setRegeditDt(String regeditDt) {
        this.regeditDt = regeditDt;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthorizeCd() {
        return authorizeCd;
    }

    public void setAuthorizeCd(String authorizeCd) {
        this.authorizeCd = authorizeCd;
    }

    public String getAuthorizeNm() {
        return authorizeNm;
    }

    public void setAuthorizeNm(String authorizeNm) {
        this.authorizeNm = authorizeNm;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getRoleNm() {
        return roleNm;
    }

    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }
}
