package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.UserManageService;
import com.cscs.util.SqlUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * create by wzy on 2018/03/20
 */
@SuppressWarnings("JpaQlInspection")
@Service
public class UserManageServiceImpl implements UserManageService {

    @PersistenceContext
    EntityManager em;

    @Override
    public int findUserMaintainCount(String userNm) {
        String sql = "SELECT COUNT(1) FROM ACCOUNT A\n" +
                "WHERE 1=1 AND A.ACTIVITY_TYPE = 0 \n";
        if (!StringUtils.isEmpty(userNm)) {
            sql += "AND A.ACCOUNT_NM like '%" + userNm + "%'\n";
        }

        Query query = em.createNativeQuery(sql);

        return Integer.valueOf(query.getSingleResult().toString());
    }

    @Override
    public List<Object> findUserMaintain(String userNm, int page, int pageSize) {
        String sql = "SELECT A.USER_ID, A.ACCOUNT_NM, ACTIVITY_TYPE, \n" +
                "B.ROLE_ID, A.CREATE_DT, C.ROLE_NM, D.USER_NM, A.ACCOUNT_ID \n" +
                "FROM ACCOUNT A \n" +
                "LEFT JOIN ACCOUNT_ROLE_XW B ON A.ACCOUNT_ID = B.ACCOUNT_ID \n" +
                "LEFT JOIN ROLE C ON C.ROLE_ID = B.ROLE_ID \n" +
                "LEFT JOIN USER_BASICINFO D ON A.USER_ID = D.USER_ID \n" +
                "WHERE 1=1 AND A.ACTIVITY_TYPE = 0 \n";

        if (!StringUtils.isEmpty(userNm)) {
            sql += "AND A.ACCOUNT_NM like '%" + userNm + "%' \n";
        }

        sql += "ORDER BY A.UPDT_DT DESC ";

        if (pageSize != 0) {
            sql = SqlUtils.pagingMethod2(page, pageSize, sql);
        }

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    @Transactional
    public void editUserRole(Long accountId, String accountPw, Long roleId, Long managerId, int flag) {

        String sql = "";
        if (flag == 0) {
            sql = "DELETE FROM ACCOUNT_ROLE_XW WHERE ACCOUNT_ID = " + accountId;
            em.createNativeQuery(sql).executeUpdate();
            sql = "INSERT INTO ACCOUNT_ROLE_XW \n" +
                    "(ACCOUNT_ID,ROLE_ID,CREATE_BY,CREATE_DT,UPDT_BY,UPDT_DT) \n" +
                    "VALUES \n"+
                    "(" + accountId +"," + roleId + "," + managerId + ",SYSTIMESTAMP" + "," + managerId + ",SYSTIMESTAMP )";
        }
        if (flag == 1) {
            sql = "UPDATE ACCOUNT SET \n" +
                    " ACCOUNT_PW = '" + accountPw + "', \n" +
                    " UPDT_BY = " + managerId + ", \n" +
                    " UPDT_DT = SYSTIMESTAMP \n" +
                    " WHERE ACCOUNT_ID = " + accountId;
        }

        em.createNativeQuery(sql).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUser(Long accountId) {
        String sql = "UPDATE ACCOUNT SET \n" +
                " ACTIVITY_TYPE = 1" +
                " WHERE ACCOUNT_ID = " + accountId;
        em.createNativeQuery(sql).executeUpdate();
    }
}
