package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.UserRoleService;
import com.cscs.util.SqlUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * create by wzy on 2018/03/06
 */
@SuppressWarnings("JpaQlInspection")
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getRole() {
        String sql = "SELECT ROLE_ID, ROLE_NM FROM ROLE";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    @Transactional
    public void updateRole(String roleNm, Long roleId) {
        String sql = "UPDATE ACCOUNT SET \n" +
                " ROLE_NM = '" + roleNm + "' \n" +
                " WHERE ROLE_ID = " + roleId;
        em.createNativeQuery(sql).executeUpdate();
    }

}
