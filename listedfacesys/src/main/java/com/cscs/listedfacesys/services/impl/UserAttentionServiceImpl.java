package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.UserFollowCompyOutData;
import com.cscs.listedfacesys.dto.UserFollowInData;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.util.SqlUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class UserAttentionServiceImpl implements UserAttentionService {

    @PersistenceContext
    EntityManager em;

    @Override
    public Set<String> searchAllCompy(Long userId) {
        String sql = "SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + userId + "\n";
        List<Object> ids = em.createNativeQuery(sql).getResultList();
        Set<String> result = new HashSet<String>();
        for (Object o : ids) {
            result.add(String.valueOf(o));
        }
        return result;
    }

    @Transactional
    public void addAttention(Long userId, Long companyId, String companyNm, Long focusType) {
        String sql = "DELETE FROM USER_FOCUS WHERE USER_ID = "+ userId + " AND FOCUS_ID = "+ companyId;
        em.createNativeQuery(sql).executeUpdate();
        sql = "INSERT INTO USER_FOCUS \n" +
                "(USER_FOCUS_ID, USER_ID, FOCUS_ID, FOCUS_TYPE, FOCUS_NM, UPDT_BY,UPDT_DT) \n" +
                "VALUES \n" +
                "(SEQ_USER_FOCUS.NEXTVAL,"+ userId +","+ companyId +","+ focusType +",\'"+ companyNm +"\',"+ userId +",SYSTIMESTAMP)";
        em.createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public void deleteAttention(Long userId, Long companyId) {
        String sql = "DELETE FROM USER_FOCUS WHERE USER_ID = "+ userId + " AND FOCUS_ID = "+ companyId;
        em.createNativeQuery(sql).executeUpdate();
    }

    @Override
    public List<Object> findNotFollowCompyInfo(int page,int pageSize,Long userId, String companyNmOrSecuritycd) {
        String sqlWhere = "\n AND (A.COMPANY_NM LIKE '%"+companyNmOrSecuritycd+"%' or A.SECURITY_CDS like '%"+companyNmOrSecuritycd+"%')";
        if(companyNmOrSecuritycd == null || "".equals(companyNmOrSecuritycd)){
            sqlWhere ="";
        }
        String sql = "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  B.UPDT_DT,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.SECURITY_CDS SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                " LEFT JOIN USER_FOCUS B\n" +
                " ON A.COMPANY_ID   = B.FOCUS_ID\n" +
                " AND B.USER_ID     ="+ userId +"\n" +
                "WHERE B.FOCUS_ID IS NULL \n" + sqlWhere;

        if(page >0 && pageSize >0){
            sql = SqlUtils.pagingMethod(page,pageSize,sql);
        }
        List<Object> list = em.createNativeQuery(sql).getResultList();
        return list;
    }

    @Override
    public List<Object> findFollowedCompyInfo(int page,int pageSize,Long userId, String companyNm) {
        String sqlWhere = "\n AND A.COMPANY_NM LIKE '%"+companyNm+"%'";
        if(companyNm == null || "".equals(companyNm)){
            sqlWhere ="";
        }
        String sql = "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  TO_CHAR(B.UPDT_DT,'YYYY-MM-DD HH:mm'), \n" +
                "  A.COMPANY_NM,"+
                "  A.SECURITY_CDS SECURITY_CD "+
                "FROM LFS_COMPY_BASICINFO A\n" +
                "INNER JOIN USER_FOCUS B\n" +
                "ON A.COMPANY_ID = B.FOCUS_ID\n" +
                "WHERE B.USER_ID = "+ userId + sqlWhere;
        String paginSql = SqlUtils.pagingMethod(page,pageSize,sql);
        List<Object> list = em.createNativeQuery(paginSql).getResultList();
        return list;
    }

    @Override
    public int findNotFollowCompyInfoCount(Long userId, String companyNmOrSecuritycd) {
        String sqlWhere = "\n AND A.COMPANY_NM LIKE '%"+companyNmOrSecuritycd+"%' \n " +
                "or A.SECURITY_CDS LIKE '%"+companyNmOrSecuritycd+"%'";
        if(companyNmOrSecuritycd == null || "".equals(companyNmOrSecuritycd)){
            sqlWhere ="";
        }
        String sql = "SELECT COUNT(*) FROM ( \n" +
                "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  B.UPDT_DT,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.SECURITY_CDS SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                " LEFT JOIN USER_FOCUS B\n" +
                " ON A.COMPANY_ID   = B.FOCUS_ID\n" +
                " AND B.USER_ID     ="+ userId +"\n" +
                "WHERE B.FOCUS_ID IS NULL \n" + sqlWhere +
                ")";

        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    @Override
    public int findFollowedCompyInfoCount(Long userId, String companyNm) {
        String sqlWhere = "\n AND A.COMPANY_NM LIKE '%"+companyNm+"%'";
        if(companyNm == null || "".equals(companyNm)){
            sqlWhere ="";
        }
        String sql = "SELECT count(*) FROM ( \n" +
                "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  TO_CHAR(B.UPDT_DT,'YYYY-MM-DD HH:mm'), \n" +
                "  A.COMPANY_NM, \n"+
                "  A.SECURITY_CDS SECURITY_CD \n"+
                "FROM LFS_COMPY_BASICINFO A\n" +
                "INNER JOIN USER_FOCUS B\n" +
                "ON A.COMPANY_ID = B.FOCUS_ID\n" +
                "WHERE B.USER_ID = "+ userId + sqlWhere +
                ")";
        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    @Transactional
    public void batchAddAttention(long userId,List<UserFollowInData> list) {
        if(list !=null && list.size()>0){
            for (UserFollowInData inData: list) {
                String sql = "DELETE FROM USER_FOCUS WHERE USER_ID = "+ userId + " AND FOCUS_ID = "+ inData.getCompanyId();
                em.createNativeQuery(sql).executeUpdate();
                sql = "INSERT INTO USER_FOCUS \n" +
                        "(USER_FOCUS_ID, USER_ID, FOCUS_ID, FOCUS_TYPE, FOCUS_NM, UPDT_BY,UPDT_DT) \n" +
                        "VALUES \n" +
                        "(SEQ_USER_FOCUS.NEXTVAL,"+ userId +","+ inData.getCompanyId() +","+ 1 +",\'"+
                         inData.getCompanyNm() +"\',"+ userId +",SYSTIMESTAMP)";
                em.createNativeQuery(sql).executeUpdate();
            }
        }
    }

    @Transactional
    public void batchDeleteAttention(Long userId, String companyIds) {
        String[] strCompanyIds = {};
        if(companyIds != null && !"".equals(companyIds)){
            strCompanyIds =companyIds.split(",");
        }
        for(int i=0;i<strCompanyIds.length;i++){
            String sql = "DELETE FROM USER_FOCUS WHERE USER_ID = "+ userId + " AND FOCUS_ID = "+ strCompanyIds[i];
            em.createNativeQuery(sql).executeUpdate();
        }

    }

    @Override
    public List<Object> findAllFollowedCompyInfo(Long userId) {
        String sql = "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  B.UPDT_DT, \n" +
                "  A.COMPANY_NM,\n" +
                "  A.SECURITY_CDS SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                " INNER JOIN USER_FOCUS B\n" +
                " ON A.COMPANY_ID = B.FOCUS_ID\n" +
                "WHERE B.USER_ID = "+ userId;
        List<Object> list = em.createNativeQuery(sql).getResultList();
        return list;
    }

    //查询用户是否已关注某公司
    public boolean findFollowedCompyInfoById(Long userId, String companyId) {
        boolean flag = false;
        String sql = "SELECT COUNT(*)\n" +
                "FROM LFS_COMPY_BASICINFO A\n" +
                "INNER JOIN USER_FOCUS B\n" +
                "ON A.COMPANY_ID = B.FOCUS_ID\n" +
                "WHERE B.USER_ID = "+ userId + "\n"+
                " AND A.COMPANY_ID =" + companyId;
        Query query = em.createNativeQuery(sql);
        int count =Integer.valueOf(query.getSingleResult().toString());
        if(count>0){
            flag=true;
            return flag;
        }
        return flag;
    }


}
