package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.NegativeInfoRecordInData;
import com.cscs.listedfacesys.services.MapRegionService;
import com.cscs.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * create by wzy on 2018/02/07
 */
@Service
public class MapRegionServiceImpl implements MapRegionService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getRegionCompanyInfo() {
        String sql="SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.COMPANY_SNM,\n" +
                "  A.REG_ADDR,\n" +
                "  A.POSITIONX,\n" +
                "  A.POSITIONY,\n" +
                "  A.SECURITY_CDS SECURITY_CD,\n" +
                "  B.RATING,\n" +
                "  CASE\n" +
                "    WHEN INSTR(A.REG_ADDR,'光明新区') > 0 or INSTR(A.REG_ADDR,'光明区')>0 \n" +
                "    THEN '光明新区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'宝安区') > 0\n" +
                "    THEN '宝安区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'龙华新区')>0 or INSTR(A.REG_ADDR,'龙华区') > 0\n" +
                "    THEN '龙华新区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'南山区') > 0\n" +
                "    THEN '南山区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'福田区') > 0\n" +
                "    THEN '福田区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'罗湖区') > 0\n" +
                "    THEN '罗湖区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'龙岗区') > 0\n" +
                "    THEN '龙岗区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'坪山新区') > 0 or INSTR(A.REG_ADDR,'坪山区') > 0 \n" +
                "    THEN '坪山新区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'大鹏新区') > 0 or INSTR(A.REG_ADDR,'大鹏区') > 0 \n" +
                "    THEN '大鹏新区'\n" +
                "    WHEN INSTR(A.REG_ADDR,'盐田区') > 0\n" +
                "    THEN '盐田区'\n" +
                "    ELSE '其他'\n" +
                "  END REGIONNAME, \n" +
                "  B.RISKCONTENT \n"+
                "FROM LFS_COMPY_BASICINFO A\n" +
                "LEFT JOIN LFS_COMPY_CREDITRATING B\n" +
                "ON A.COMPANY_ID = B.COMPANY_ID"+
                " order by B.RATING desc";

        return em.createNativeQuery(sql).getResultList();
    }

    //获取用户当日所查看的所有负面事件信息
    @Override
    public List<Object> getNegativeInfoByUserId(String date, String userId) {
        String sql="SELECT COMPANY_ID,\n" +
                "  USER_ID,\n" +
                "  EVENT_ID,\n" +
                "  EVENT_DATE,\n" +
                "  EVENT_TYPE\n" +
                "FROM LFS_NEGATIVE_EVENTOVER n\n" +
                "  WHERE n.user_ID     =" +userId+
                " AND n.EVENT_DATE  = to_date('"+date+"','YYYY-MM-dd')";
        return em.createNativeQuery(sql).getResultList();
    }

    @Transactional
    public int insertNegativeInfoRecord(NegativeInfoRecordInData inData) {
        int executStatus=0;
        String announceCds = inData.getAnnounceCds();
        String newsCds = inData.getNewsCds();
        String delSql = "DELETE\n" +
                "FROM LFS_NEGATIVE_EVENTOVER t\n" +
                "WHERE t.COMPANY_ID                   =" +inData.getCompanyId()+"\n"+
                "AND t.USER_ID                        = " +inData.getUserId()+"\n"+
                "AND TO_CHAR(t.EVENT_DATE,'YYYYMMDD') ='"+inData.getEventDate()+"'";
       executStatus += em.createNativeQuery(delSql).executeUpdate();
        if(announceCds !=null && !"".equals(announceCds)){
            String[] announceCdArray =announceCds.split(",");
            for (int i=0;i<announceCdArray.length;i++){
                String sql = "insert into LFS_NEGATIVE_EVENTOVER \n" +
                        "values ("+inData.getCompanyId()+","+inData.getUserId()+",\n" +
                        announceCdArray[i]+",to_date("+ inData.getEventDate()+",'YYYY-MM-DD'),1,0,\n"+
                        "SYSDATE,0"+")";
                executStatus +=  em.createNativeQuery(sql).executeUpdate();
            }
        }
        if(newsCds !=null && !"".equals(newsCds)){
            String[] newsCdArray =newsCds.split(",");
            for (int i=0;i<newsCdArray.length;i++){
                String sql = "insert into LFS_NEGATIVE_EVENTOVER \n" +
                        "values ("+inData.getCompanyId()+","+inData.getUserId()+",\n" +
                        newsCdArray[i]+",to_date("+ inData.getEventDate()+",'YYYY-MM-DD'),2,0,\n"+
                        "SYSDATE,0"+")";
                executStatus += em.createNativeQuery(sql).executeUpdate();
            }
        }
        return executStatus;
    }
}
