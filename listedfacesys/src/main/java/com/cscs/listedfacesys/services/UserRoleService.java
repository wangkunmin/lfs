package com.cscs.listedfacesys.services;

import java.util.List;

/**
 * 用户角色管理
 * create by wzy on 2018/03/06
 */
public interface UserRoleService {

    /**
     * 查询角色信息
     * @return
     */
    public List<Object> getRole();

    /**
     * 更新角色名称
     * @param roleId
     */
    public void updateRole(String roleNm, Long roleId);

}
