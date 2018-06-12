package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.entity.LfsUser;
import com.cscs.listedfacesys.services.LfsUserInfoService;
import com.cscs.repository.LfsUserInfoRepository;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class LfsUserInfoServiceImpl implements LfsUserInfoService {

    @PersistenceContext
    EntityManager em;

    /**
     * 注册新用户
     * @return
     */
    @Override
    @Transactional
    public void  register(String accountName,String password, String userNm, Long roleId){

        Long value1 = registerUserId();
        Long value2 = registerAccountId();

        if (roleId == 0L) {
            roleId = 2050L;
        }
        String sql3 = "INSERT INTO USER_BASICINFO \n" +
                "(USER_ID,USER_NM,UPDT_BY,UPDT_DT) \n" +
                "VALUES \n"+
                "(" + value1 + ", '" + userNm + "'," + value1 + ",SYSTIMESTAMP)";
        em.createNativeQuery(sql3).executeUpdate();


        String sql = "INSERT INTO ACCOUNT \n" +
                "(ACCOUNT_ID,ACCOUNT_PW,ACCOUNT_NM,ACTIVITY_TYPE,USER_ID,CREATE_BY,CREATE_DT) \n" +
                "VALUES \n"+
                "(" + value2 + ",\'"+ password +"\',\'"+ accountName +"\','0'," + value1 + "," + value1 + ",SYSTIMESTAMP)";
        em.createNativeQuery(sql).executeUpdate();

        String sqlRole = "INSERT INTO ACCOUNT_ROLE_XW \n" +
                "(ACCOUNT_ID,ROLE_ID,CREATE_BY,CREATE_DT,UPDT_BY,UPDT_DT) \n" +
                "VALUES \n"+
                "(" + value2 +", " + roleId + "," + value2 + ",SYSTIMESTAMP " + "," + value2 + ",SYSTIMESTAMP)";
        em.createNativeQuery(sqlRole).executeUpdate();
    }

    private Long registerUserId() {
        String sql2 = "SELECT SEQ_USER_BASICINFO.NEXTVAL FROM DUAL";
        Query query = em.createNativeQuery(sql2);
        Long value = Long.valueOf(query.getSingleResult().toString());

        return value != 0 ? value : 0l;
    }
    private Long registerAccountId() {
        String sqlAccountId = "SELECT SEQ_ACCOUNT.NEXTVAL FROM DUAL";
        Query query = em.createNativeQuery(sqlAccountId);
        Long value = Long.valueOf(query.getSingleResult().toString());
        return value != 0 ? value : 0l;
    }

    @Override
    public String findByUserName(String userName) {
        String sql = "SELECT ACCOUNT_NM  FROM ACCOUNT WHERE ACCOUNT_NM =\'" + userName + "\' AND ACTIVITY_TYPE = 0";

        List<String> query = em.createNativeQuery(sql).getResultList();
        return query.size() > 0 ? query.get(0) : "";
    }

    @Override
    public List<Object> findByUserId(Long userId) {
        String sql = "select \n" +
                "T.USER_ID, \n" +
                "T.USER_NM, \n" +
                "T.HEAD_URL, \n" +
                "T.BIRTH, \n" +
                "T.USER_GENDER, \n" +
                "T.ID_CARD_NO, \n" +
                "T.CELLPHONE, \n" +
                "T.PHONE, \n" +
                "T.EMAIL, \n" +
                "T.ADDRESS, \n" +
                "T.MAJOR, \n" +
                "T.EDUCATIONAL, \n" +
                "T.POLITICAL_STATUS, \n" +
                "T.INTRODUCTION, \n" +
                "T.USER_TYPE, \n" +
                "T.COMPANY_ID, \n" +
                "T.COMPANY_NM, \n" +
                "T.POSITION, \n" +
                "T.GOOD_TYPE, \n" +
                "T.INDUSTRY_SID, \n" +
                "T.UPDT_BY, \n" +
                "T.UPDT_DT, \n" +
                "A.ACCOUNT_NM, \n" +
                "B.ROLE_ID \n" +
                "from USER_BASICINFO T \n" +
                "INNER JOIN ACCOUNT A ON T.USER_ID = A.USER_ID \n" +
                "AND A.USER_ID= " + userId + " \n" +
                "INNER JOIN ACCOUNT_ROLE_XW B ON A.ACCOUNT_ID = B.ACCOUNT_ID";

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> findUserAccountId(String accountName) {
        String sql = "SELECT ACCOUNT_ID FROM ACCOUNT WHERE ACCOUNT_NM =\'" + accountName + "\'";

        return em.createNativeQuery(sql).getResultList();
    }

    /**
     * 根据用户名修改密码
     * @param userName
     * @param password
     */
    @Override
    @Transactional
    public void updatePassword(Long userId, String userName, String password) {
        String sql = "UPDATE ACCOUNT SET \n" +
                " ACCOUNT_PW= \'"+password+"\', UPDT_BY = " + userId + ", UPDT_DT = SYSTIMESTAMP \n" +
                " WHERE USER_ID = " + userId + "\n" +
                " AND ACCOUNT_NM=\'"+userName+"\'";

        em.createNativeQuery(sql).executeUpdate();


    }

    /**
     * 根据ID更新用户详细信息
     * @param
     * @param
     * @return
     */
    @Override
    @Transactional
    public void updateAccount(Long userId, String userNm, String cellPhone, String phone,
                              String address, String mail, Long companyId, String companyNm, String position){
        String sql = "UPDATE USER_BASICINFO SET \n" +
                " USER_NM = \'"+userNm+"\',\n" +
                " CELLPHONE = '" + cellPhone + "',\n" +
                " EMAIL = '" + mail + "',\n" +
                " POSITION = '" + position + "',\n" +
                " PHONE = '" + phone + "',\n" +
                " COMPANY_NM = '" + companyNm + "',\n" +
                " ADDRESS = '" + address + "',\n" +
                " UPDT_DT = SYSTIMESTAMP \n" +
                " WHERE USER_ID = " + userId;
        em.createNativeQuery(sql).executeUpdate();
    }

    /**
     * 登录获取用户信息
     * @param userName
     * @param passWrod
     * @throws Exception
     */
    public List<Object> loginForGetUserInfo(String userName, String passWrod) throws Exception {
        String sql="select \n" +
                "A.USER_ID, \n" +
                "A.USER_NM, \n" +
                "A.HEAD_URL, \n" +
                "A.BIRTH, \n" +
                "A.USER_GENDER, \n" +
                "A.ID_CARD_NO, \n" +
                "A.CELLPHONE, \n" +
                "A.PHONE, \n" +
                "A.EMAIL, \n" +
                "A.ADDRESS, \n" +
                "A.MAJOR, \n" +
                "A.EDUCATIONAL, \n" +
                "A.POLITICAL_STATUS, \n" +
                "A.INTRODUCTION, \n" +
                "A.USER_TYPE, \n" +
                "A.COMPANY_ID, \n" +
                "A.COMPANY_NM, \n" +
                "A.POSITION, \n" +
                "A.GOOD_TYPE, \n" +
                "A.INDUSTRY_SID, \n" +
                "A.UPDT_BY, \n" +
                "A.UPDT_DT, \n" +
                "B.ACCOUNT_ID, \n" +
                "C.ROLE_ID \n" +
                "from USER_BASICINFO A  \n" +
                " INNER JOIN ACCOUNT B ON A.USER_ID = B.USER_ID \n" +
                " AND B.ACCOUNT_NM='"+userName+"' and  B.ACCOUNT_PW='"+passWrod+"' and B.ACTIVITY_TYPE='0'\n" +
                " INNER JOIN ACCOUNT_ROLE_XW C ON C.ACCOUNT_ID = B.ACCOUNT_ID";

        return em.createNativeQuery(sql).getResultList();
    }



    @Override
    public int findUserName(String userNm) {
        String sql = "select count(*) \n" +
                     "from USER_BASICINFO where USER_NM = '" + userNm +"'";
        List<Object> count = em.createNativeQuery(sql).getResultList();
        int sum = 0;
        if (count != null && count.size() != 0) {
            sum = Integer.valueOf(count.get(0).toString());
        }
        return sum;
    }

    @Override
    public int findAcountName(String acountNm) {
        String sql = "select count(*) \n" +
                "from ACCOUNT where ACCOUNT_NM = '" + acountNm +"' and ACTIVITY_TYPE = 0";
        List<Object> count = em.createNativeQuery(sql).getResultList();
        int sum = 0;
        if (count != null && count.size() > 0) {
            sum = Integer.valueOf(count.get(0).toString());
        }
        return sum;
    }


}
