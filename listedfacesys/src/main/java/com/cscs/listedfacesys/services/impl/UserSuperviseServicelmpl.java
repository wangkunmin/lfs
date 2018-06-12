package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.CompySuperviseData;
import com.cscs.listedfacesys.services.UserSuperviseService;
import com.cscs.util.SqlUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Service
public class UserSuperviseServicelmpl implements UserSuperviseService {
    @PersistenceContext
    EntityManager em;




    //根据公司名称模糊查询
    @Override
    public List<Object> findCompySuperviseInfo(int page , int pageSize ,String companyNm) {
        String sqlWhere = "";
        if(companyNm !=null && !"".equals(companyNm)){
            sqlWhere =  " WHERE cb.COMPANY_NM LIKE '%"+companyNm+"%'\n" ;
        }
        String sql = "SELECT tb.COMPANY_ID,\n" +
                "  tb.COMPANY_NM,\n" +
                "  tb.COMPANY_SNM,\n" +
                "  tb.SECURITY_CD,\n" +
                "  LISTAGG(tb.USER_ID\n" +
                "  ||'-'\n" +
                "  ||tb.USER_NM,',') WITHIN GROUP (\n" +
                "ORDER BY tb.COMPANY_ID,tb.COMPANY_NM)\n" +
                "FROM\n" +
                "  (\n" +
                "  SELECT CS.COMPY_SUPERVISE_ID,\n" +
                "  cb.COMPANY_ID,\n" +
                "  ub.USER_ID,\n" +
                "  cb.COMPANY_NM,\n" +
                "  cb.COMPANY_SNM,\n" +
                "  ub.USER_NM,\n" +
                "  cb.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO cb\n" +
                " LEFT JOIN COMPY_SUPERVISE CS\n" +
                " ON cb.COMPANY_ID = CS.COMPY_ID\n" +
                " LEFT JOIN USER_BASICINFO ub\n" +
                " ON CS.USER_ID = ub.USER_ID\n" +
                 sqlWhere +
                "  ) tb\n" +
                " GROUP BY tb.COMPANY_ID,\n" +
                "  tb.COMPANY_NM,\n" +
                "  tb.COMPANY_SNM,\n" +
                "  tb.SECURITY_CD";

        String sqlStr = SqlUtils.pagingMethod2(page,pageSize,sql);
        List<Object> list = em.createNativeQuery(sqlStr).getResultList();
        return list;
    }


    public int findCompySuperviseInfoCount(String companyNm) {
        String sqlWhere = "";
        if(companyNm !=null && !"".equals(companyNm)){
            sqlWhere =  " WHERE cb.COMPANY_NM LIKE '%"+companyNm+"%'\n" ;
        }
        String sql = "  SELECT count(*) \n" +
                " FROM (\n" +
                "SELECT tb.COMPANY_ID,\n" +
                "  tb.COMPANY_NM,\n" +
                "  tb.COMPANY_SNM,\n" +
                "  tb.SECURITY_CD\n" +
                "FROM\n" +
                "  (\n" +
                "  SELECT CS.COMPY_SUPERVISE_ID,\n" +
                "  cb.COMPANY_ID,\n" +
                "  ub.USER_ID,\n" +
                "  cb.COMPANY_NM,\n" +
                "  cb.COMPANY_SNM,\n" +
                "  ub.USER_NM,\n" +
                "  cb.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO cb\n" +
                " LEFT JOIN COMPY_SUPERVISE CS\n" +
                " ON cb.COMPANY_ID = CS.COMPY_ID\n" +
                " LEFT JOIN USER_BASICINFO ub\n" +
                " ON CS.USER_ID = ub.USER_ID\n" +
                sqlWhere +
                "  ) tb\n" +
                " )";

        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }



    @Transactional
    public void operationSupervise(CompySuperviseData inData) throws Exception {
        if(inData.getOperationType() != null && !"".equals(inData.getOperationType())){
            if(inData.getUserIds() == null || "".equals(inData.getUserIds()) || inData.getCompyId() ==null || "".equals(inData.getCompyId())){
                throw new Exception("用户ID或企业ID为空，操作失败！");
            }
            String sql = "DELETE FROM COMPY_SUPERVISE WHERE USER_ID = "+ inData.getUserIds() + " AND COMPY_ID = "+ inData.getCompyId();

            String sql2 = "INSERT INTO COMPY_SUPERVISE (COMPY_SUPERVISE_ID,COMPY_ID,USER_ID,UPDT_BY,UPDT_TS) VALUES\n" +
                    "(SEQ_COMPY_SUPERVISE.NEXTVAL,"+ inData.getCompyId() +","+ inData.getUserIds() +","+ inData.getUpdtBy() +",SYSTIMESTAMP) ";
            if("1".equals(inData.getOperationType())){
                em.createNativeQuery(sql).executeUpdate();
                em.createNativeQuery(sql2).executeUpdate();
            }else if("2".equals(inData.getOperationType())){
                em.createNativeQuery(sql).executeUpdate();
            }
        }else{
            throw new Exception("操作类型为空，操作失败！");
        }

    }


}
