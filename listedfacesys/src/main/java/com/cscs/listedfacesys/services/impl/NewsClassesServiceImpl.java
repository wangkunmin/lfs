package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.busi.NewsClassBusiService;
import com.cscs.listedfacesys.dto.NegativeNewsInData;
import com.cscs.listedfacesys.dto.NewsWarningInData;
import com.cscs.listedfacesys.dto.NewsWarningOutData;
import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hj on 2018/02/01.
 * 新闻类实现类
 */
@Service
public class NewsClassesServiceImpl implements NewsClassesService {

    @PersistenceContext
    EntityManager em;

    //负面跟踪新闻
    public List<Object> getLastingBondViolationNews(int page, int pageSize,String startDate,String endDate) throws Exception {

        String where = "    AND (to_date(x.POST_DT,'yyyy-mm-dd') BETWEEN to_date('"+ startDate +"','yyyy-mm-dd') AND to_date('"+ endDate +"','yyyy-mm-dd'))\n";

        if((startDate==null || "".equals(startDate)) || (endDate==null || "".equals(endDate))){
            where="";
        }
        String sql = "SELECT \n" +
                "        x.ID,\n" +
                "        x.INFO_CD ,\n" +
                "        x.TITLE,\n" +
                "        x.POST_DT,\n" +
                "        x.PLAIN_TEXT,\n" +
                "        x.POST_URL,\n" +
                "        x.company_id,\n" +
                "        x.CNN_SCORE,\n" +
                "        x.IMPORTANCE,\n" +
                "        x.RELEVANCE,\n" +
                "        x.MEDIA_NM,\n" +
                "        x.POST_DT1, \n"+
                "        cb.COMPANY_SNM, \n"+
                "        cb.SECURITY_CDs SECURITY_CD \n"+
                "    FROM\n" +
                "      (SELECT d.NEWS_BASICINFO_SID\n" +
                "        || '-'\n" +
                "        || d.company_id ID,\n" +
                "        a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "        a.TITLE,\n" +
                "        TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                "        a.PLAIN_TEXT,\n" +
                "        a.POST_URL,\n" +
                "        d.company_id,\n" +
                "        d.SCORE CNN_SCORE,\n" +
                "        d.IMPORTANCE,\n" +
                "        d.RELEVANCE,\n" +
                "        a.MEDIA_NM,\n" +
                "       TO_CHAR(a.POST_DT,'YYYY-MM-DD hh24:mi:ss')POST_DT1 \n"+
                "      FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "      INNER JOIN XW_NEWS_COMPANY d\n" +
                "      ON a.NEWS_BASICINFO_SID  = d.NEWS_BASICINFO_SID\n" +
                "      WHERE ( NVL(d.RELEVANCE,0) > 0.05\n" +
                "      OR d.IMPORTANCE         != 0) and a.ISDEL = 0 \n" +
                "      )x\n" +
                "    inner join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = x.company_id \n" +

                "    WHERE x.CNN_SCORE <0\n" +
                "    AND x.RELEVANCE  >= 0.8\n" +
                where+
                "    ORDER BY x.POST_DT1 DESC\n" ;
        //使用自定义sql工具类，生成分页sql语句
        String paginSql = SqlUtils.pagingMethod2(page,pageSize,sql);
        return em.createNativeQuery(paginSql).getResultList();
    }


    //负面跟踪新闻总数
    public int getLastingBondViolationNewsCount(String startDate,String endDate) throws Exception {

        String where = "    AND (to_date(x.POST_DT,'yyyy-mm-dd') BETWEEN to_date('"+ startDate +"','yyyy-mm-dd') AND to_date('"+ endDate +"','yyyy-mm-dd'))\n";

        if((startDate==null || "".equals(startDate)) || (endDate==null || "".equals(endDate))){
            where="";
        }
        String sql = "SELECT count(*) \n" +
                "    FROM\n" +
                "      (SELECT d.NEWS_BASICINFO_SID\n" +
                "        || '-'\n" +
                "        || d.company_id ID,\n" +
                "        a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "        a.TITLE,\n" +
                "        TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                "        a.PLAIN_TEXT,\n" +
                "        a.POST_URL,\n" +
                "        d.company_id,\n" +
                "        d.SCORE CNN_SCORE,\n" +
                "        d.IMPORTANCE,\n" +
                "        d.RELEVANCE,\n" +
                "        a.MEDIA_NM,\n" +
                "       TO_CHAR(a.POST_DT,'YYYY-MM-DD hh24:mi:ss')POST_DT1 \n"+
                "      FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "      INNER JOIN XW_NEWS_COMPANY d\n" +
                "      ON a.NEWS_BASICINFO_SID  = d.NEWS_BASICINFO_SID\n" +
                "      WHERE ( NVL(d.RELEVANCE,0) > 0.05\n" +
                "      OR d.IMPORTANCE         != 0) and a.ISDEL = 0 \n" +
                "      )x\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = x.company_id \n" +
                "    WHERE x.CNN_SCORE <0\n" +
                "    AND x.RELEVANCE  >= 0.8\n" +
                where;
        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }


    /**
     * 将固定sql提取出公共的，以便保持和调用的方法的关联性
     * @param sqlWhere 条件
     * @param allWhere 全部公司条件（可不传，）
     * @param userAttentionSqlWhere  用户关注的拼接条件语句
     * @return
     */
    private String returnSqlStr(String sqlWhere,String allWhere ,String userAttentionSqlWhere) {
        String classify = " TO_CHAR(POST_DT,'YYYY-MM-DD')POST_DT";
        if(allWhere == null){
            allWhere = " " ;
        }
        if(userAttentionSqlWhere == null){
            userAttentionSqlWhere = "";
        }



        String mysql = "SELECT CN1,\n" +
                "  CN2,\n" +
                "  t.post_dt post_dt\n" +
                "FROM\n" +
                "  (SELECT COUNT(a.POST_DT) cn1,\n" +
                "    TO_CHAR(POST_DT,'YYYY-MM-DD') POST_DT\n" +
                "  FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "  INNER JOIN XW_NEWS_COMPANY d\n" +
                "  ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                allWhere +"\n"+
                userAttentionSqlWhere +"\n"+
                "  WHERE a.ISDEL                      = 0\n" +
                "  AND d.RELEVANCE                   >= 0.8\n" +
                sqlWhere + " \n"+
                "  GROUP BY TO_CHAR(POST_DT,'YYYY-MM-DD') \n" +
                "  ) t\n" +
                "LEFT JOIN\n" +
                "  (SELECT COUNT(a.POST_DT) cn2,\n" +
                "    TO_CHAR(POST_DT,'YYYY-MM-DD') POST_DT\n" +
                "  FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "  INNER JOIN XW_NEWS_COMPANY d\n" +
                "  ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                allWhere +"\n"+
                userAttentionSqlWhere +"\n"+
                "  WHERE a.ISDEL                      = 0\n" +
                "  AND d.SCORE                        < 0\n" +
                "  AND d.RELEVANCE                   >= 0.8\n" +
                sqlWhere + " \n"+
                "  GROUP BY TO_CHAR(POST_DT,'YYYY-MM-DD') \n" +
                "  ) tt ON t.post_dt=tt.post_dt\n" +
                "  order by  t.post_dt desc";
        return mysql;

    }



    //热点新闻，查询当前时间前七个月的数据
    public List<Object> findchart(TendencyChartInData inData) {
        //String sqlWhere = " AND POST_DT >= add_months(SYSDATE, -7) ";
        String sqlWhere = " AND (POST_DT BETWEEN to_date('"+ inData.getStartDate() +"','yyyy-mm-dd') AND to_date('"+ inData.getEndDate() +"','yyyy-mm-dd'))\n";
        String allWhere = "  INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "  ON cb.COMPANY_ID                   = d.company_id\n" ;
        String sql = returnSqlStr(sqlWhere,allWhere,"");
        return em.createNativeQuery(sql).getResultList();
    }



    //查询公司名称
    public List<Object> findCompanyNm(String compyId) {
        String sql = "SELECT COMPANY_ID,COMPANY_SNM FROM LFS_COMPY_BASICINFO WHERE \n" +
                " IS_DEL = 0  " +
                "AND COMPANY_ID IN ("+compyId+")";
        return em.createNativeQuery(sql).getResultList();
    }

    //获取新闻事件
    public List<Object> findNewsType(long compyId, String newsCode) {
        String sql = "SELECT SHEET_L1,WM_CONCAT(DISTINCT A.LABEL)LABEL\n" +
                "FROM WARNING_NEWS_RESULT A\n" +
                "INNER JOIN RULE B ON A.RULE_ID = B.RULE_ID AND B.SHEET_TYPE = 0 \n" +
                "INNER JOIN (SELECT SHEET_ID,SHEET_L1 FROM SHEET WHERE SHEET_TYPE = 0)C ON B.SHEET_ID = C.SHEET_ID \n" +
                "WHERE COMPANY_ID = ?1 AND NEWS_BASICINFO_SID = ?2\n" +
                "GROUP BY C.SHEET_L1";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, compyId);
        query.setParameter(2, newsCode);
        return query.getResultList();
    }

    //获取新闻事件信息
    public List<Object> findNewsContent(long compyId, String newsCode) {
        String sql = "SELECT D.LABEL,NVL(F.TITLE,C.TITLE),C.POST_URL,C.POST_DT,C.MEDIA_NM,B.RELEVANCE,B.SCORE,NVL(E.HIGHLIGHT_TEXT,C.CONTENT),B.IMPORTANCE\n" +
                "FROM LFS_COMPY_BASICINFO A\n" +
                "INNER JOIN XW_NEWS_COMPANY B ON  A.COMPANY_ID = B.COMPANY_ID AND B.ISDEL = 0 AND (B.RELEVANCE > 0.05 OR B.IMPORTANCE > 0)\n" +
                "INNER JOIN "+Contants.SCHEMA_NAME+"NEWS_BASICINFO C ON C.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID and C.ISDEL = 0 \n" +
                "LEFT JOIN(SELECT COMPANY_ID,WM_CONCAT(DISTINCT SHEET_L1)LABEL,NEWS_BASICINFO_SID \n" +
                "          FROM WARNING_NEWS_RESULT A \n" +
                "          INNER JOIN RULE B ON A.RULE_ID = B.RULE_ID AND B.SHEET_TYPE = 0\n" +
                "          INNER JOIN (SELECT SHEET_ID,SHEET_L1 FROM SHEET WHERE SHEET_TYPE = 0)C ON B.SHEET_ID = C.SHEET_ID\n" +
                "          WHERE A.COMPANY_ID = " + compyId + "\n" +
                "          GROUP BY COMPANY_ID,NEWS_BASICINFO_SID)D ON A.COMPANY_ID = D.COMPANY_ID AND B.NEWS_BASICINFO_SID = D.NEWS_BASICINFO_SID\n" +
                "LEFT JOIN WARNING_NEWS_HIGHLIGHT E ON A.COMPANY_ID = E.COMPANY_ID AND B.NEWS_BASICINFO_SID = E.NEWS_BASICINFO_SID\n" +
                "LEFT JOIN WARNING_NEWS_RESULT F ON A.COMPANY_ID = F.COMPANY_ID AND B.NEWS_BASICINFO_SID = F.NEWS_BASICINFO_SID\n" +
                "WHERE A.COMPANY_ID = " + compyId + " AND B.NEWS_BASICINFO_SID = ?1 AND A.IS_DEL = 0 ";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, newsCode);
        return query.getResultList();
    }


    //舆情风险趋势图，查询当前时间前七个月的数据
    public List<Object> findchartForUserAttention(TendencyChartInData inData) {
        //String sqlWhere = " AND POST_DT >= add_months(SYSDATE, -7) ";
        String sqlWhere = " AND (POST_DT BETWEEN to_date('"+ inData.getStartDate() +"','yyyy-mm-dd') AND to_date('"+ inData.getEndDate() +"','yyyy-mm-dd'))\n";
        String userAttentionSqlWhere = "INNER JOIN (select FOCUS_ID from USER_FOCUS where USER_ID = "+inData.getUserId()+") uD ON d.COMPANY_ID = uD.FOCUS_ID";
        String sql = returnSqlStr(sqlWhere,"",userAttentionSqlWhere);
        return em.createNativeQuery(sql).getResultList();
    }




    //新闻舆情风险
    public List<Object> getNewsRiskForUserAttention(int page, int pageSize,String startDate,String endDate,String userId) throws Exception {

        String where = "    AND TO_CHAR(a.POST_DT,'YYYY-MM-DD')    BETWEEN '"+startDate+"' AND  '"+endDate+"' \n";

        if((startDate==null || "".equals(startDate)) || (endDate==null || "".equals(endDate))){
            where="";
        }


        String sql ="\n" +
                "select " +
                "distinct " +
                "        x.ID,\n" +
                "        x.INFO_CD ,\n" +
                "        x.TITLE,\n" +
                "        x.POST_DT,\n" +
                "        '' PLAIN_TEXT,\n" +
                "        x.POST_URL,\n" +
                "        x.company_id,\n" +
                "        x.CNN_SCORE,\n" +
                "        x.IMPORTANCE,\n" +
                "        x.RELEVANCE,\n" +
                "        x.MEDIA_NM,\n" +
                "        x.POST_DT1, \n"+
                "        z.LABEL,\n" +
                "        x.COMPANY_SNM, \n"+
                "        x.SECURITY_CD SECURITY_CD \n"+
                " from (\n" +
                "SELECT\n" +
                "d.NEWS_BASICINFO_SID\n" +
                "   || '-'\n" +
                "   || d.company_id ID,\n" +
                "   a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "   a.TITLE,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                //"   a.PLAIN_TEXT,\n" +
                "   a.POST_URL,\n" +
                "   d.company_id,\n" +
                "   d.SCORE CNN_SCORE,\n" +
                "   d.IMPORTANCE,\n" +
                "   d.RELEVANCE,\n" +
                "   a.MEDIA_NM,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD hh24:mi:ss')POST_DT1,\n" +
                "        cb.COMPANY_SNM, \n"+
                "        cb.SECURITY_CDs SECURITY_CD \n"+
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "    INNER JOIN USER_FOCUS f ON cb.COMPANY_ID = f.FOCUS_ID\n" +
                "     AND f.USER_ID ="+ userId +"\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE (NVL(d.RELEVANCE, 0)         > 0.39\n" +
                "    OR d.IMPORTANCE                   != 0)\n" +
                "    AND a.ISDEL                        = 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                where+
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    ) x left join \n" +
                "   ( SELECT\n" +
                "d.NEWS_BASICINFO_SID\n" +
                "   || '-'\n" +
                "   || d.company_id ID,\n" +
                "  wm_concat(DISTINCT D1.SHEET_L1) LABEL\n" +
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "   INNER JOIN USER_FOCUS f ON cb.COMPANY_ID = f.FOCUS_ID\n" +
                "  AND f.USER_ID ="+ userId +"\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE (NVL(d.RELEVANCE, 0)         > 0.39\n" +
                "    OR d.IMPORTANCE                   != 0)\n" +
                "    AND a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                where+
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "      GROUP BY d.NEWS_BASICINFO_SID,\n" +
                "        d.COMPANY_ID\n" +
                ") z ON x.ID     = z.ID order by x.POST_DT1 desc\n";
        //使用自定义sql工具类，生成分页sql语句
        String paginSql = SqlUtils.pagingMethod2(page,pageSize,sql);
        return em.createNativeQuery(paginSql).getResultList();
    }


    //新闻舆情风险总数
    public int getNewsRiskForUserAttentionCount(String startDate,String endDate,String userId) throws Exception {

        String where = "    AND TO_CHAR(a.POST_DT,'YYYY-MM-DD')    BETWEEN '"+startDate+"' AND  '"+endDate+"' \n";

        if((startDate==null || "".equals(startDate)) || (endDate==null || "".equals(endDate))){
            where="";
        }
        String sql ="\n " +
                "select " +
                "count(*) " +
                " from (\n" +
                "SELECT\n" +
                " DISTINCT d.NEWS_BASICINFO_SID\n" +
                "   || '-'\n" +
                "   || d.company_id ID,\n" +
                "   a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "   a.TITLE,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                //"   a.PLAIN_TEXT,\n" +
                "   a.POST_URL,\n" +
                "   d.company_id,\n" +
                "   d.SCORE CNN_SCORE,\n" +
                "   d.IMPORTANCE,\n" +
                "   d.RELEVANCE,\n" +
                "   a.MEDIA_NM,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD hh24:mi:ss')POST_DT1,\n" +
                "        cb.COMPANY_SNM, \n"+
                "        cb.SECURITY_CDs SECURITY_CD \n"+
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "    INNER JOIN USER_FOCUS f ON cb.COMPANY_ID = f.FOCUS_ID\n" +
                "     AND f.USER_ID ="+ userId +"\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE (NVL(d.RELEVANCE, 0)         > 0.39\n" +
                "    OR d.IMPORTANCE                   != 0)\n" +
                "    AND a.ISDEL                        = 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                where+
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    ) x \n";

        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    //新闻事件趋势图,查询当前时间前七个月的数据
    public List<Object> findchartByCompanyId(TendencyChartInData inData) throws Exception {
        //String sqlWhere = " AND POST_DT >= add_months(SYSDATE, -7) AND A.COMPANY_ID = "+inData.getCompanyId();
        String sqlWhere = " AND (POST_DT BETWEEN to_date('"+ inData.getStartDate() +"','yyyy-mm-dd') AND to_date('"+ inData.getEndDate() +"','yyyy-mm-dd'))\n "+
               " AND d.COMPANY_ID = "+inData.getCompanyId() +"\n";
        String sql = returnSqlStr(sqlWhere,"","");
        return em.createNativeQuery(sql).getResultList();
    }




    //舆情事件
    public List<Object> getNewsRiskByCompanyNm(NewsWarningInData inData) throws Exception {
        String where = "    AND TO_CHAR(a.POST_DT,'YYYY-MM-DD')    BETWEEN '"+inData.getDateStart()+"' AND  '"+inData.getDateEnd()+"' \n";

        if((inData.getDateStart() ==null || "".equals(inData.getDateStart())) || (inData.getDateEnd()==null || "".equals(inData.getDateEnd()))){
            where="";
        }

        String sql ="\n" +
                "select " +
                "distinct " +
                "        x.ID,\n" +
                "        x.INFO_CD ,\n" +
                "        x.TITLE,\n" +
                "        x.POST_DT,\n" +
                "        '' PLAIN_TEXT,\n" +
                "        x.POST_URL,\n" +
                "        x.company_id,\n" +
                "        x.CNN_SCORE,\n" +
                "        x.IMPORTANCE,\n" +
                "        x.RELEVANCE,\n" +
                "        x.MEDIA_NM,\n" +
                "        x.POST_DT1, \n"+
                "        z.LABEL,\n" +
                "        x.COMPANY_SNM, \n"+
                "        x.SECURITY_CD SECURITY_CD \n"+
                " from (\n" +
                "SELECT\n" +
                "d.NEWS_BASICINFO_SID\n" +
                "   || '-'\n" +
                "   || d.company_id ID,\n" +
                "   a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "   a.TITLE,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                //"   a.PLAIN_TEXT,\n" +
                "   a.POST_URL,\n" +
                "   d.company_id,\n" +
                "   d.SCORE CNN_SCORE,\n" +
                "   d.IMPORTANCE,\n" +
                "   d.RELEVANCE,\n" +
                "   a.MEDIA_NM,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD hh24:mi:ss')POST_DT1,\n" +
                "        cb.COMPANY_SNM, \n"+
                "        cb.SECURITY_CDs SECURITY_CD \n"+
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    and d.company_id = "+inData.getCompyId() +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE (NVL(d.RELEVANCE, 0)         > 0.39\n" +
                "    OR d.IMPORTANCE                   != 0)\n" +
                "    AND a.ISDEL                        = 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                where+
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    ) x left join \n" +
                "   ( SELECT\n" +
                "d.NEWS_BASICINFO_SID\n" +
                "   || '-'\n" +
                "   || d.company_id ID,\n" +
                "  wm_concat(DISTINCT D1.SHEET_L1) LABEL\n" +
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE (NVL(d.RELEVANCE, 0)         > 0.39\n" +
                "    OR d.IMPORTANCE                   != 0)\n" +
                "    AND a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                where+
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "      GROUP BY d.NEWS_BASICINFO_SID,\n" +
                "        d.COMPANY_ID\n" +
                ") z ON x.ID     = z.ID order by x.POST_DT1 desc\n";
        //使用自定义sql工具类，生成分页sql语句
        String paginSql = SqlUtils.pagingMethod2(inData.getPage(),inData.getPageSize(),sql);
        return em.createNativeQuery(paginSql).getResultList();
    }


    //舆情事件总数
    public int getNewsRiskByCompanyNmCount(NewsWarningInData inData) throws Exception {
        String where = "    AND TO_CHAR(a.POST_DT,'YYYY-MM-DD')    BETWEEN '"+inData.getDateStart()+"' AND  '"+inData.getDateEnd()+"' \n";

        if((inData.getDateStart() ==null || "".equals(inData.getDateStart())) || (inData.getDateEnd()==null || "".equals(inData.getDateEnd()))){
            where="";
        }
        String sql ="\n " +
                "select " +
                "count(*) " +
                " from (\n" +
                "SELECT\n" +
                " DISTINCT d.NEWS_BASICINFO_SID\n" +
                "   || '-'\n" +
                "   || d.company_id ID,\n" +
                "   a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "   a.TITLE,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                //"   a.PLAIN_TEXT,\n" +
                "   a.POST_URL,\n" +
                "   d.company_id,\n" +
                "   d.SCORE CNN_SCORE,\n" +
                "   d.IMPORTANCE,\n" +
                "   d.RELEVANCE,\n" +
                "   a.MEDIA_NM,\n" +
                "   TO_CHAR(a.POST_DT,'YYYY-MM-DD hh24:mi:ss')POST_DT1,\n" +
                "        cb.COMPANY_SNM, \n"+
                "        cb.SECURITY_CDs SECURITY_CD \n"+
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    and d.company_id = "+inData.getCompyId() +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE (NVL(d.RELEVANCE, 0)         > 0.39\n" +
                "    OR d.IMPORTANCE                   != 0)\n" +
                "    AND a.ISDEL                        = 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                where+
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    ) x \n";

        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    //舆情事件,调用外部接口
    public BaseOutData findNewsFromSolr(NewsWarningInData inData) {
        //String wt = inData.getWarningType();
        Long compyId = inData.getCompyId();
        //int rele = inData.getRelevance();
        /*int tm = inData.getTime();*/
        int tm = 2;
        int page = inData.getPage();
        int pgsize = inData.getPageSize();
        String kw = inData.getKeyword();
        //int score = inData.getScore();
        String datePhrase = "";
        int startRow = (page-1) * pgsize;

        String q = "q=company_ids:" + "%22" + compyId + "%22";

        if (!StringUtils.isEmpty(kw))
            q += "%20AND%20text:" + "%22" + kw + "%22";

        /*if (!StringUtils.isEmpty(wt))
            q += "%20AND%20label:" + "%22" + wt + "%22";

        if (score == 1)
            q += "%20AND%20cnn_score:[*%20TO%20-1]";*/

        switch (tm) {
            case 1:
                datePhrase = SolrUtil.getDateQueryPhraseByDay(7);
                break;
            case 2:
                datePhrase = SolrUtil.getDateQueryPhraseByDay(30);
                break;
            case 3:
                datePhrase = SolrUtil.getDateQueryPhraseByDay(90);
                break;
            case 4:
                datePhrase = SolrUtil.getDateQueryPhraseByDay(365);
                break;
            default:
                datePhrase = SolrUtil.getDateQueryPhraseByDay(365);
                break;
        }

        /*switch (rele) {
            case 1:
                q += "&fq=relevance:[0.01%20TO%200.1]%20OR%20(relevance:%7b*%20TO%200.05%7d%20AND%20importance:[0%20TO%20*])";
                break;
            case 2:
                q += "&fq=relevance:[0.1001%20TO%200.3]";
                break;
            case 3:
                q += "&fq=relevance:[0.3001%20TO%200.8]";
                break;
            case 4:
                q += "&fq=relevance:[0.8001%20TO%200.9999]";
                break;
            case 5:
                q += "&fq=relevance:%7b0.9999%20TO%20*%7d";
                break;
            default:
                break;
        }*/
        q += datePhrase;
        if (pgsize == 0)
            pgsize = 20;
        q += "&rows=" + pgsize + "&start=" + startRow;
        BaseOutData out = new BaseOutData();
        String uri = Contants.NEWS_SERVER_URL + q;
        // 执行请求
        try {
            String fuzzyList = HttpUtil.getResponse(uri);
            JSONObject result = new JSONObject(fuzzyList);
            JSONObject response = (JSONObject) result.get("response");
            out.setCount(response.getInt("numFound"));
            JSONArray docs = response.getJSONArray("docs");
            int length = docs.length();
            List<NewsWarningOutData> list = new ArrayList<NewsWarningOutData>(length);
            for (int i = 0; i < length; i++) {
                JSONObject obj = (JSONObject) docs.get(i);
                NewsWarningOutData outData = new NewsWarningOutData();
                if (obj.has("label"))
                    outData.setWarningType(StringUtil.toString(obj.get("label")));
                outData.setPublishTime(StringUtil.toString(obj.get("last_modified")));
                outData.setNewsTitle(StringUtil.toString(obj.get("title")).replaceAll("\\\\", ""));
                outData.setImportance(StringUtil.toString(obj.get("importance")));
                outData.setScore(StringUtil.toString(obj.get("cnn_score")));
                if (obj.has("relevance"))
                    outData.setRelevance(StringUtil.toString(obj.get("relevance")));
                outData.setNewsCode(StringUtil.toString(obj.get("newscode")));
                if (obj.has("media_nm"))
                    outData.setNewsSource(StringUtil.toString(obj.get("media_nm")));
                if(obj.has("company_ids")){
                    outData.setCompanyId(StringUtil.toString(obj.get("company_ids")));
                }
                list.add(outData);
            }
            List<Object> companyInfoList = findAllCompanyInfoBySZ();//查询所有深圳的上市公司信息
            list = transition(list,companyInfoList);//将对应的公司简称放入NewsWarningOutData
            if(list !=null && list.size()>0){
                Map<String, List<NewsWarningOutData>> map = new HashMap<String, List<NewsWarningOutData>>();
                map.put("content", list);
                out.setCode("0");
                out.setData(map);
            }else{
                out.setCode("1");
                out.setMessage("舆情事件获取数据为空");
            }

        } catch (IOException e) {
            out.setCode("-1");
            out.setMessage("舆情事件获取数据异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }

    //查询所有深圳市上市公司及证券代码
    public List<Object> findAllCompanyInfoBySZ() {
        String sql = "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  A.SECURITY_CDs  SECURITY_CD \n"+
                " FROM LFS_COMPY_BASICINFO A \n" ;
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }


    private List<NewsWarningOutData> transition(List<NewsWarningOutData>  news, List<Object> companyInfo){
        if(news == null || news.size()<=0 || companyInfo == null || companyInfo.size()<=0){
            return null;
        }
        for(int i=0;i<news.size();i++){
            NewsWarningOutData  newsOutData =  news.get(i);
            if(newsOutData.getCompanyId() !=null && !"".equals(newsOutData.getCompanyId())){
                for(int j=0;j<companyInfo.size();j++){
                    Object[] arrayInfo = (Object[]) companyInfo.get(j);
                    if(arrayInfo[0] !=null){
                        String compyId = arrayInfo[0] == null ? "" : arrayInfo[0].toString();
                        String compySnm = arrayInfo[1] == null ? "" : arrayInfo[1].toString();
                        if(newsOutData.getCompanyId().equals(compyId)){
                            newsOutData.setCompanySnm(compySnm);
                        }
                    }
                    if(arrayInfo[2] !=null){
                        String compyId = arrayInfo[0] == null ? "" : arrayInfo[0].toString();
                        String securityCd = arrayInfo[2] == null ? "" : arrayInfo[2].toString();
                        if(newsOutData.getCompanyId().equals(compyId)){
                            newsOutData.setSecurityCd(securityCd);
                        }
                    }

                }
            }

        }
        return news;
    }
}
