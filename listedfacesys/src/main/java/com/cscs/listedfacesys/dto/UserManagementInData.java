package com.cscs.listedfacesys.dto;

/**
 * 用户后台管理类
 * create by WZY on 2018/03/06
 */
public class UserManagementInData {

    private Long userId;        //用户ID
    private String userNm;      //昵称
    private Long accountId;     //账号ID
    private String accountNm;   //账户名
    private String accountPw;   //用户密码
    private Long activityType;  //激活类型
    private Long roleId;        //权限ID
    private String roleNm;      //角色名称
    private Long privilegeId;
    private String privilegeIdString;
    private String authorizeCd; //授权码
    private String email;       //邮箱
    private String code;
    private String accountPwConfirm;
    private Long browser;
    private boolean regeditFlg;
    private String accountOldPw;
    private String companyNm;   //公司名称
    private String dateStart;   //开始时间（YYYYMMDD）
    private String dateEnd;     //结束时间（YYYYMMDD）
    private int page;           //页码
    private int pageSize;       //单页数据量
    private String cellPhone;       //手机
    private String phone;       //固定电话
    private String position;        //职务
    private String address;     //公司地址

    private int type;       //0为新增，1为更新

    public UserManagementInData() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getRoleNm() {
        return roleNm;
    }

    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getPrivilegeIdString() {
        return privilegeIdString;
    }

    public void setPrivilegeIdString(String privilegeIdString) {
        this.privilegeIdString = privilegeIdString;
    }

    public String getAuthorizeCd() {
        return authorizeCd;
    }

    public void setAuthorizeCd(String authorizeCd) {
        this.authorizeCd = authorizeCd;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAccountNm() {
        return accountNm;
    }

    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm;
    }

    public String getAccountPw() {
        return accountPw;
    }

    public void setAccountPw(String accountPw) {
        this.accountPw = accountPw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountPwConfirm() {
        return accountPwConfirm;
    }

    public void setAccountPwConfirm(String accountPwConfirm) {
        this.accountPwConfirm = accountPwConfirm;
    }

    public Long getBrowser() {
        return browser;
    }

    public void setBrowser(Long browser) {
        this.browser = browser;
    }

    public boolean isRegeditFlg() {
        return regeditFlg;
    }

    public void setRegeditFlg(boolean regeditFlg) {
        this.regeditFlg = regeditFlg;
    }

    public String getAccountOldPw() {
        return accountOldPw;
    }

    public void setAccountOldPw(String accountOldPw) {
        this.accountOldPw = accountOldPw;
    }

    public Long getActivityType() {
        return activityType;
    }

    public void setActivityType(Long activityType) {
        this.activityType = activityType;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
