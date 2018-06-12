package com.cscs.listedfacesys.services;

import java.util.List;


/**
 * 用户后台管理
 * create by wzy on 2018/03/20
 */
public interface UserManageService {

    /**
     * 查询用户账户条目数
     * @return
     */
    public int findUserMaintainCount(String userNm);

    /**
     * 根据用户名或账户ID查询账户信息
     * @return
     */
    public List<Object> findUserMaintain(String userNm, int page, int pageSize);

    /**
     * 修改用户角色权限
     */
    public void editUserRole(Long accountId, String accountPw, Long roleId, Long managerId, int flag);

    /**
     * 删除账户
     * @param accountId
     */
    public void deleteUser(Long accountId);

}
