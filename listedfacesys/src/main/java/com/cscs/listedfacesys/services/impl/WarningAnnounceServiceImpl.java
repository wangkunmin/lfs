package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.Contants;
import com.cscs.util.DateUtils;
import com.cscs.util.SqlUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class WarningAnnounceServiceImpl implements WarningAnnounceService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getWarningTop10(String dateStart, String dateEnd) {
        String time1 = "";
        String time2 = "";


        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = " AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        //查询舆情公告中所有的公司id
        String sql = "select sum(cn) cn, company_id\n" +
                "  from (" +
        //查询出预警中所有的目标风险类（财务、治理、经营、市场、法律法规）公司id
                "        SELECT w.company_id, count(distinct w.WARNING_TITLE) cn\n" +
                "          FROM VW_COMPY_WARNINGS w\n" +
                "         INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = w.company_id  \n"+
                "         WHERE w.WARNING_TITLE IS NOT NULL\n" +
                "           and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                time2 + "\n" +
                "         group by w.company_id\n";
        //查询出所有负面新闻的目标风险类（财务、治理、经营、市场、法律法规）公司id
                sql += ") t\n" +
                " group by t.company_id\n" ;


        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getWarningTop10Content(String compyList, String dateStart, String dateEnd) {
        String time1 = "";
        String time2 = "";
        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = " AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
         }

        //预警风险（财务、治理、经营、市场、法律法规）相关指标条目查询
               String sql = "SELECT DISTINCT w.company_id,\n" +
                "                cb.COMPANY_SNM,\n" +
                "                w.WARNING_TITLE TITLE,\n" +
                "                e.TYPE_NAME,\n" +
                "                w.NOTICE_DT,\n" +
                "                '' infocd,\n" +
                "                e.importance,\n" +
                "                '1' flg,\n" +
                "                cb.SECURITY_CDs SECURITY_CD\n" +
                "  FROM VW_COMPY_WARNINGS w\n" +
                " INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = w.company_id  \n"+
                "   AND cb.COMPANY_ID IN (" + compyList + ")\n"+
                " WHERE w.WARNING_TITLE IS NOT NULL\n" +
                "   and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                time2 + "\n";

        return em.createNativeQuery(sql).getResultList();
    }



    @Override
    public List<Object> getWarningYearCount(String dateStart, String dateEnd) {

        //五种风险指标条目查询
        String sql = "select to_char(NOTICE_DT,'YYYYMMDD'),\n" +
                "       sum(decode(type_key, 1, 1, 0)) sumfinance,\n" +
                "       sum(decode(type_key, 2, 1, 0)) sumgovern,\n" +
                "       sum(decode(type_key, 3, 1, 0)) summanage,\n" +
                "       sum(decode(type_key, 4, 1, 0)) summarket,\n" +
                "       sum(decode(type_key, 5, 1, 0)) sumlaw\n" +
                "  from ( \n" +
                "        SELECT case\n" +
                "                 when e.id in (10, 12, 101, 102, 103, 104, 105) then\n" +
                "                  1\n" +
                "                 when e.id in (106, 107) then\n" +
                "                  2\n" +
                "                 else\n" +
                "                  -1\n" +
                "               end type_key,\n" +
                "               w.notice_dt notice_dt,\n" +
                "               w.WARNING_TITLE\n" +
                "          FROM VW_COMPY_WARNINGS w\n" +
                "         INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "         INNER JOIN lfs_COMPY_BASICINFO cb on cb.COMPANY_ID = w.company_id\n" +
                "         WHERE w.WARNING_TITLE IS NOT NULL\n" +
                "           and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                "           AND to_char(w.NOTICE_DT,'YYYYMMDD') >= " + dateStart + " and to_char(w.NOTICE_DT,'YYYYMMDD') <= " + dateEnd + "\n" +
                ") t\n" +
                " where t.type_key <> -1\n" +
                " group by notice_dt";

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getSupervisor(String compyList) {
        String sql = "SELECT COMPY_ID, LISTAGG(A.USER_NM, ',') \n" +
                "WITHIN GROUP (ORDER BY COMPY_ID) FROM (\n" +
                "SELECT T.COMPY_ID, UB.USER_NM FROM COMPY_SUPERVISE T \n" +
                "LEFT JOIN  USER_BASICINFO UB ON T.USER_ID = UB.USER_ID \n" +
                "WHERE T.COMPY_ID IN ("+ compyList+")) A \n" +
                "GROUP BY COMPY_ID";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getUserWarningTop10(Long userId, String dateStart, String dateEnd) {

        String time1 = "";
        String time2 = "";


        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = " AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        //查询舆情公告中所有的公司id
        String sql = "select sum(cn) cn, company_id\n" +
                "  from (" +
        //查询出预警中所有的目标风险类（财务、治理、经营、市场、法律法规）公司id
                "        SELECT w.company_id, count(distinct w.WARNING_TITLE) cn\n" +
                "          FROM VW_COMPY_WARNINGS w\n" +
                "         INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "         INNER JOIN USER_FOCUS B ON w.COMPANY_ID = B.FOCUS_ID\n" +
                "                                and b.user_id = " + userId + "\n" +
                "         WHERE w.WARNING_TITLE IS NOT NULL\n" +
                "           and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                time2 + "\n" +
                "         group by w.company_id\n";
        //查询出所有负面新闻的目标风险类（财务、治理、经营、市场、法律法规）公司id
        sql += ") t\n" +
                " group by t.company_id\n" ;

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getUWTop10Content(Long userId, String compyList, String dateStart, String dateEnd) {
        String time1 = "";
        String time2 = "";
        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = "AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        //查询舆情公告中所有的指标

        //查询出预警中所有的目标风险类（财务、治理、经营、市场、法律法规）指标
        String sql ="SELECT DISTINCT w.COMPANY_ID,\n" +
                "                cb.COMPANY_SNM,\n" +
                "                w.WARNING_TITLE,\n" +
                "                e.TYPE_NAME,\n" +
                "                w.NOTICE_DT,\n" +
                "                '' infocd,\n" +
                "                e.importance,\n" +
                "                '1' flg，\n" +
                "                cb.SECURITY_CDs SECURITY_CD\n" +
                "  FROM VW_COMPY_WARNINGS w\n" +
                " INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                " INNER JOIN lfs_COMPY_BASICINFO cb on cb.COMPANY_ID = w.company_id\n" +
                " AND cb.COMPANY_ID IN (" + compyList + ")\n" +
                " WHERE w.WARNING_TITLE IS NOT NULL\n" +
                time2 + "\n" +
                "   and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getUWTop10Count(Long userId, String compyList, String dateStart, String dateEnd) {
        String time1 = "";
        String time2 = "";
        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = "AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        String sql = "SELECT COUNT(*) FROM (select sum(cn) cn, company_id \n" +
                "  from (SELECT xw.company_id, count(distinct info.NOTICE_TITLE) cn\n" +
                "          FROM "+Contants.SCHEMA_NAME+"ANNOUNCE_BASICINFO info\n" +
                "         INNER JOIN XW_ANNOUNCE_COMPANY xw ON info.INFO_CD = xw.INFO_CD\n" +
                "         INNER JOIN USER_FOCUS B ON xw.COMPANY_ID = B.FOCUS_ID\n" +
                "                                and b.user_id = " + userId + "\n" +
                "         INNER JOIN rule ON xw.RULE_ID = rule.RULE_ID\n" +
                "         INNER JOIN sheet ON rule.SHEET_ID = sheet.SHEET_ID\n" +
                "         WHERE sheet.SHEET_TYPE = 2\n" +
                "           AND info.ISDEL = '0'\n" +
                time1 + "\n" +
                "         group by xw.company_id\n" +
                "        union all\n" +
                "        SELECT w.company_id, count(distinct w.WARNING_TITLE) cn\n" +
                "          FROM VW_COMPY_WARNINGS w\n" +
                "         INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "         INNER JOIN USER_FOCUS B ON w.COMPANY_ID = B.FOCUS_ID\n" +
                "                                and b.user_id = " + userId + "\n" +
                "         WHERE w.WARNING_TITLE IS NOT NULL\n" +
                time2 + "\n" +
                "           and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                "         group by w.company_id)\n" +
                " group by company_id\n" +
                " )";
        return em.createNativeQuery(sql).getResultList();
    }

    //共同SQL
    private String getAESql(Long userId, Long companyId, String dateStart, String dateEnd,int eventType) {
        String sqlTime = "";
        String sqlCompanyId = "";
        String sqlUserId = "";
        if (dateStart != null && dateEnd != null) {
            sqlTime = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and "+ dateEnd;
        }
        if (companyId != null) {
            sqlCompanyId = " AND B.COMPANY_ID = " + companyId;
        }
        if (userId != null) {
            sqlUserId = " INNER JOIN USER_FOCUS f ON B.COMPANY_ID = f.FOCUS_ID AND f.USER_ID = "+ userId + "\n";
        }
        String reasonSql="";
        switch (eventType) {
            case 1:
                reasonSql = " and sheet.SHEET_L1 =  '财务风险' \n";

                break;
            case 2:
                reasonSql = "  and sheet.SHEET_L1 = '治理风险' \n";

                break;
            case 3:
                reasonSql = "  and sheet.SHEET_L1 = '经营风险' \n";
                break;
            case 4:
                reasonSql = "  and sheet.SHEET_L1 ='市场风险' \n";
                break;
            case 5:
                reasonSql = "  and sheet.SHEET_L1 ='法律法规风险' \n";
                break;
            default:
                reasonSql="";
        }



        String sql ="SELECT DISTINCT b.SECURITY_CDs SECURITY_CD,\n" +
                "                        B.COMPANY_ID COMPANY_ID,\n" +
                "                        B.COMPANY_SNM COMPANY_SNM,\n" +
                "                        info.NOTICE_DT NOTICE_DT,\n" +
                "                        info.SRC_TYPE SRC_TYPE,\n" +
                "                        sheet.SHEET_L1 SHEET,\n" +
                "                        info.INFO_CD INFO_CD,\n" +
                "                        info.NOTICE_TITLE NOTICE_TITLE,\n" +
                "                        B.COMPANY_NM COMPANY_NM\n" +
                "          FROM "+Contants.SCHEMA_NAME+"ANNOUNCE_BASICINFO info\n" +
                "         inner join (select xw.company_id, xw.INFO_CD, c.rule_id\n" +
                "                      from COMPY_ANNOUNCE_XW xw\n" +
                "                     inner join XW_ANNOUNCE_COMPANY C ON xw.INFO_CD =\n" +
                "                                                         C.INFO_CD\n" +
                "                                                     AND xw.COMPANY_ID =\n" +
                "                                                         C.COMPANY_ID\n" +
                "                                                     and xw.ISDEL = '0') xwc ON info.INFO_CD =\n" +
                "                                                                                xwc.INFO_CD\n" +
                "         INNER JOIN lfs_COMPY_BASICINFO B ON xwc.COMPANY_ID = B.COMPANY_ID\n" +
                sqlCompanyId + " \n"+
                sqlUserId +
                "         INNER JOIN rule ON xwC.RULE_ID = rule.RULE_ID\n" +
                "         INNER JOIN sheet ON rule.SHEET_ID = sheet.SHEET_ID\n" +
                reasonSql +
                "         WHERE info.ISDEL = '0'\n" +
                "           AND info.SRC_TYPE NOT IN\n" +
                "               ('美国证监会', '香港交易所', '发行人网站', '香港基金公告')"
                + sqlTime;

        return sql;
    }

    @Override
    public List<Object> getAccounceEvent(Long userId, String dateStart, String dateEnd, int page, int pageSize,int eventType,int negativeType,int importanceType) {

        //根据业务分组查询
        String sql = "select a.SECURITY_CD SECURITY_CD,\n" +
                "       a.COMPANY_ID COMPANY_ID,\n" +
                "       a.COMPANY_SNM COMPANY_SNM,\n" +
                "       a.NOTICE_DT NOTICE_DT,\n" +

                "       a.SRC_TYPE SRC_TYPE,\n" +
                "       LISTAGG(sheet, '、') WITHIN\n" +
                " GROUP(\n" +
                " ORDER BY a.NOTICE_TITLE) sheet, a.INFO_CD INFO_CD, a.NOTICE_TITLE NOTICE_TITLE, a.COMPANY_NM COMPANY_NM\n" +
                "  from ( \n" +
                getAESqlForUserFocus(userId, dateStart, dateEnd,eventType,negativeType,importanceType) +
                " ) A group by a.SECURITY_CD,\n" +
                "          a.COMPANY_ID,\n" +
                "          a.COMPANY_SNM,\n" +
                "          a.NOTICE_DT,\n" +
                "          a.SRC_TYPE,\n" +
                "          a.INFO_CD,\n" +
                "          a.NOTICE_TITLE,\n" +
                "          a.COMPANY_NM\n" +
                " order by a.NOTICE_DT desc";

        sql = SqlUtils.pagingMethod2(page, pageSize, sql);

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getAECount(Long userId, String dateStart, String dateEnd,int eventType,int negativeType,int importanceType) {
        String sqlStr = "select a.SECURITY_CD SECURITY_CD,\n" +
                "       a.COMPANY_ID COMPANY_ID,\n" +
                "       a.COMPANY_SNM COMPANY_SNM,\n" +
                "       a.NOTICE_DT NOTICE_DT,\n" +
                "       a.SRC_TYPE SRC_TYPE,\n" +
                "       LISTAGG(sheet, '、') WITHIN\n" +
                " GROUP(\n" +
                " ORDER BY a.NOTICE_TITLE) sheet, a.INFO_CD INFO_CD, a.NOTICE_TITLE NOTICE_TITLE, a.COMPANY_NM COMPANY_NM\n" +
                "  from ( \n" +
                getAESqlForUserFocus(userId, dateStart, dateEnd,eventType,negativeType,importanceType) +
                " ) A group by a.SECURITY_CD,\n" +
                "          a.COMPANY_ID,\n" +
                "          a.COMPANY_SNM,\n" +
                "          a.NOTICE_DT,\n" +
                "          a.SRC_TYPE,\n" +
                "          a.INFO_CD,\n" +
                "          a.NOTICE_TITLE,\n" +
                "          a.COMPANY_NM\n" +
                " order by a.NOTICE_DT desc";
        //根据业务分组查询
        String sql = "select count(*)\n" +
                "  from ( \n" +
                sqlStr +
                " ) A ";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getSingleCompanyAE(Long userId, Long companyId, String dateStart, String dateEnd, int page, int pageSize) {
        String sql = "select a.SECURITY_CD SECURITY_CD,\n" +
                "             a.COMPANY_ID COMPANY_ID,\n" +
                "             a.COMPANY_SNM COMPANY_SNM,\n" +
                "             a.NOTICE_DT NOTICE_DT,\n" +
                "             a.SRC_TYPE SRC_TYPE,\n" +
                "             a.INFO_CD INFO_CD,\n" +
                "             a.NOTICE_TITLE NOTICE_TITLE,\n" +
                "             a.COMPANY_NM COMPANY_NM,\n" +
                "             LISTAGG(sheet, ',') WITHIN\n" +
                "       GROUP(\n" +
                "       ORDER BY a.NOTICE_TITLE) sheet from ( \n" +
                getAESql(userId, companyId, dateStart, dateEnd,0) +
                ") A group by a.SECURITY_CD,\n" +
                "                  a.COMPANY_ID,\n" +
                "                  a.COMPANY_SNM,\n" +
                "                  a.NOTICE_DT,\n" +
                "                  a.SRC_TYPE,\n" +
                "                  a.INFO_CD,\n" +
                "                  a.NOTICE_TITLE,\n" +
                "                  a.COMPANY_NM\n" +
                " ORDER BY a.NOTICE_DT DESC";

        sql = SqlUtils.pagingMethod2(page, pageSize, sql);

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getSCAECount(Long userId, Long companyId, String dateStart, String dateEnd) {
        String sqlStr = "select a.SECURITY_CD SECURITY_CD,\n" +
                "             a.COMPANY_ID COMPANY_ID,\n" +
                "             a.COMPANY_SNM COMPANY_SNM,\n" +
                "             a.NOTICE_DT NOTICE_DT,\n" +
                "             a.SRC_TYPE SRC_TYPE,\n" +
                "             a.INFO_CD INFO_CD,\n" +
                "             a.NOTICE_TITLE NOTICE_TITLE,\n" +
                "             a.COMPANY_NM COMPANY_NM,\n" +
                "             LISTAGG(sheet, ',') WITHIN\n" +
                "       GROUP(\n" +
                "       ORDER BY a.NOTICE_TITLE) sheet from ( \n" +
                getAESql(userId, companyId, dateStart, dateEnd,0) +
                ") A group by a.SECURITY_CD,\n" +
                "                  a.COMPANY_ID,\n" +
                "                  a.COMPANY_SNM,\n" +
                "                  a.NOTICE_DT,\n" +
                "                  a.SRC_TYPE,\n" +
                "                  a.INFO_CD,\n" +
                "                  a.NOTICE_TITLE,\n" +
                "                  a.COMPANY_NM\n" +
                " ORDER BY a.NOTICE_DT DESC";
        String sql = "select count(*) from ( \n" +
                sqlStr +
                ") A ";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public String getAnnoumceUrl(String infoCd) {
        String sql = "SELECT '" + Contants.FILE_URL + "'|| REGEXP_REPLACE(REPLACE(ATTACH_NM,'\\','/'), '^/','') URL\n" +
                "FROM "+Contants.SCHEMA_NAME+"ANNOUNCE_BASICINFO A\n" +
                "WHERE A.INFO_CD = ?1";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1,infoCd);
        return query.getSingleResult().toString();
    }

    @Override
    public List<Object> getThreeSideWarning(Long companyId, int type, String dateStart, String dateEnd) {
        String time2 = "";
        if (dateEnd != null && dateEnd != "") {
            time2 = " AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        String reason2 = "";
        switch (type) {
            case 1:
                reason2 ="SELECT DISTINCT " +
                        "                w.WARNING_TITLE TITLE,\n" +
                        "                e.TYPE_NAME,\n" +
                        "                w.NOTICE_DT,\n" +
                        "                '' infocd,\n" +
                        "                e.importance,\n" +
                        "                '1' flg\n" +
                        "  FROM VW_COMPY_WARNINGS w\n" +
                        " INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                        " WHERE w.WARNING_TITLE IS NOT NULL\n" +
                        "   and e.id in (10, 12, 101, 102, 103, 104, 105)\n" +
                        "   AND w.COMPANY_ID IN (" + companyId + ")\n"+
                        time2 + "\n";
                break;
            case 2:
                reason2 = "SELECT DISTINCT " +
                        "                w.WARNING_TITLE TITLE,\n" +
                        "                e.TYPE_NAME,\n" +
                        "                w.NOTICE_DT,\n" +
                        "                '' infocd,\n" +
                        "                e.importance,\n" +
                        "                '1' flg\n" +
                        "  FROM VW_COMPY_WARNINGS w\n" +
                        " INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                        " WHERE w.WARNING_TITLE IS NOT NULL\n" +
                        "   and e.id in (106, 107)\n" +
                        "   AND w.COMPANY_ID IN (" + companyId + ")\n"+
                        time2 + "\n";
                break;
            case 3:
                return null;
            case 4:
                return null;
            case 5:
                return null;
        }
        //从五种风险指标（财务、治理、经营、市场、法律法规）条目查询
        String sql = reason2;
        return em.createNativeQuery(sql).getResultList();
    }

    /**
     * 查询监测预警新闻公司ID
     * @return
     */
    @Override
    public List<Object> getWarningTopfornews(String dateStart, String dateEnd){
        String time3="";
        if (dateEnd != null && dateEnd != "") {
            time3 = " AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }

        String sql ="        \n" +
        "        SELECT  count(distinct a.TITLE) cn,d.company_id \n" +
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
                "    WHERE  a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                time3 + "\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    and d1.SHEET_L1 in\n" +
                "        ('财务事件', '公司治理',\n" +
                "         '经营情况', '市场事件',\n" +
                "         '法律法规' ) \n" +
                "         group by d.company_id ";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getWarningTop10Contentfornews(String compyList, String dateStart, String dateEnd) {

        String time3 = "";
        if (dateEnd != null && dateEnd != "") {
            time3 = " AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }

        ////新闻条目相关指标（财务、治理、经营、市场、法律法规）查询

        String sql ="        \n" +
                "SELECT DISTINCT d.company_id,\n" +
                "                cb.COMPANY_SNM,\n" +
                "                a.TITLE TITLE,\n" +
                "                (d1.SHEET_L1 || '-' || d1.SHEET_L2) TYPE_NAME,\n" +
                "                to_date(to_char(a.POST_DT, 'yyyy-MM-dd'),'yyyy-MM-dd') notice_dt,\n" +
                "                to_char(a.NEWS_BASICINFO_SID) infocd,\n" +
                "                d.importance,\n" +
                "                '2' flg,\n" +
                "                cb.SECURITY_CDs SECURITY_CD\n" +
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "           AND D.COMPANY_ID IN (" + compyList + ")\n"+
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN lfs_COMPY_BASICINFO cb\n" +
                "    ON cb.COMPANY_ID                   = d.company_id\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE  a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                time3 + "\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    and d1.SHEET_L1 in\n" +
                "        ('财务事件', '公司治理',\n" +
                "         '经营情况', '市场事件',\n" +
                "         '法律法规' ) \n" ;
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getWarningYearCountfornews(String dateStart, String dateEnd) {


        String sql = "select to_char(NOTICE_DT,'YYYYMMDD'),\n" +
                "       sum(decode(type_key, 1, 1, 0)) sumfinance,\n" +
                "       sum(decode(type_key, 2, 1, 0)) sumgovern,\n" +
                "       sum(decode(type_key, 3, 1, 0)) summanage,\n" +
                "       sum(decode(type_key, 4, 1, 0)) summarket,\n" +
                "       sum(decode(type_key, 5, 1, 0)) sumlaw\n" +
                " from (\n" +
                "\n" +
                "        SELECT distinct case\n" +
                "                          when d1.SHEET_L1 = '财务事件' then\n" +
                "                           1\n" +
                "                          when d1.SHEET_L1 = '公司治理' then\n" +
                "                           2\n" +
                "                          when d1.SHEET_L1 = '经营情况' then\n" +
                "                           3\n" +
                "                          when d1.SHEET_L1 = '市场事件' then\n" +
                "                           4\n" +
                "                          when d1.SHEET_L1 = '法律法规' then\n" +
                "                           5\n" +
                "                          else\n" +
                "                           -1\n" +
                "                        end type_key,\n" +
                "                        a.post_dt notice_dt,\n" +
                "                        a.title WARNING_TITLE\n" +
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
                "    WHERE a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                "    AND to_char(a.post_dt,'YYYYMMDD') >= " + dateStart + " and to_char(a.post_dt,'YYYYMMDD') <=" + dateEnd + "\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    and d1.SHEET_L1 in\n" +
                "        ('财务事件', '公司治理',\n" +
                "         '经营情况', '市场事件',\n" +
                "         '法律法规' ) \n" +
                "    ) x  \n"+
                " where x.type_key <> -1\n" +
                " group by to_char(notice_dt,'YYYYMMDD')";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getThreeSideWarningfornews(Long companyId, int type, String dateStart, String dateEnd) {

        String time3 = "";
        if (dateEnd != null && dateEnd != "") {
            time3 = " AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }

        String reason3 = "";
        switch (type) {
            case 1:
                reason3 = "'财务事件'";
                break;
            case 2:
                reason3 = "'公司治理'";
                break;
            case 3:
                reason3 = "'经营情况'";
                break;
            case 4:
                reason3 = "'市场事件'";
                break;
            case 5:
                reason3 = "'法律法规'";
        }


        ////新闻条目相关指标（财务、治理、经营、市场、法律法规）查询
        String sql ="        \n" +
                "SELECT DISTINCT " +
                "                a.TITLE TITLE,\n" +
                "                (d1.SHEET_L1 || '-' || d1.SHEET_L2) TYPE_NAME,\n" +
                "                to_date(to_char(a.POST_DT, 'yyyy-MM-dd'),'yyyy-MM-dd') notice_dt,\n" +
                "                to_char(a.NEWS_BASICINFO_SID) infocd,\n" +
                "                -99999 importance,\n" +
                "                '2' flg\n" +
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "           AND D.COMPANY_ID =" + companyId + "\n"+
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE  a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                time3 + "\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    and d1.SHEET_L1 ="+reason3+" \n" ;

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getUserWarningTop10fornews(Long userId, String dateStart, String dateEnd) {
        String time3="";
        if (dateEnd != null && dateEnd != "") {
            time3 = " AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }

        String sql ="        \n" +
                "        SELECT  count(distinct a.TITLE) cn,d.company_id \n" +
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "         INNER JOIN USER_FOCUS UF ON d.COMPANY_ID = UF.FOCUS_ID\n" +
                "                                and UF.user_id = " + userId + "\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE  a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                time3 + "\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    and d1.SHEET_L1 in\n" +
                "        ('财务事件', '公司治理',\n" +
                "         '经营情况', '市场事件',\n" +
                "         '法律法规' ) \n" +
                "         group by d.company_id ";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getUWTop10Contentfornews(Long userId, String compyList, String dateStart, String dateEnd) {
        return getWarningTop10Contentfornews(compyList, dateStart, dateEnd);
    }

    @Override
    public List<Object> getNegativeForNews(String dateStart, String dateEnd) {
        String time3="";
        if (dateEnd != null && dateEnd != "") {
            time3 = " AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        String sql ="        \n" +
                "        SELECT  count(distinct x.TITLE) cn,x.company_id \n" +
                "          FROM (SELECT d.NEWS_BASICINFO_SID || '-' || d.company_id ID,\n" +
                "                       a.TITLE,\n" +
                "                       d.company_id\n" +
                "                  FROM NEWS_BASICINFO a\n" +
                "                 INNER JOIN XW_NEWS_COMPANY d ON a.NEWS_BASICINFO_SID =\n" +
                "                                                 d.NEWS_BASICINFO_SID\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = d.company_id  \n"+
                "                 WHERE (NVL(d.RELEVANCE, 0) > 0.39 OR d.IMPORTANCE != 0)\n" +
                "                   and a.ISDEL = 0\n" +
                "                   and d.SCORE < 0\n" +
                "                   AND d.RELEVANCE >= 0.8" +
                time3 + "\n" +
                " ) x\n" +
                "         inner JOIN (SELECT TO_CHAR(B.NEWS_BASICINFO_SID) || '-' ||\n" +
                "                            TO_CHAR(B.COMPANY_ID) ID\n" +
                "                       FROM WARNING_NEWS_RESULT A\n" +
                "                      INNER JOIN XW_NEWS_COMPANY B ON A.NEWS_BASICINFO_SID =\n" +
                "                                                      B.NEWS_BASICINFO_SID\n" +
                "                                                  AND A.COMPANY_ID =\n" +
                "                                                      B.COMPANY_ID\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = B.company_id  \n"+
                "                      INNER JOIN RULE C ON A.RULE_ID = C.RULE_ID\n" +
                "                                       AND C.SHEET_TYPE = 0\n" +
                "                      INNER JOIN SHEET D ON C.SHEET_ID = D.SHEET_ID\n" +
                "                                        AND D.SHEET_TYPE = 0\n" +
                "                                        and d.SHEET_L1 in\n" +
                "                                            ('财务事件', '公司治理',\n" +
                "                                             '经营情况', '市场事件',\n" +
                "                                             '法律法规')) z ON x.ID = z.ID\n" +
                "         group by x.company_id \n"+
                "   having COUNT(DISTINCT x.TITLE)    > 0";
        return em.createNativeQuery(sql).getResultList();
    }

    //查询负面事件新闻的详细内容
    public List<Object> getNewsNegativeContent(String dateStart, String dateEnd) {
        String time3 = "";
        if (dateEnd != null && dateEnd != "") {
            time3 = " AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }


        String sql ="        \n" +
                "SELECT DISTINCT d.company_id,\n" +
                "                cb.COMPANY_SNM,\n" +
                "                a.TITLE TITLE,\n" +
                "                '' TYPE_NAME,\n" +
                "                to_date(to_char(a.POST_DT, 'yyyy-MM-dd'),'yyyy-MM-dd') notice_dt,\n" +
                "                to_char(a.NEWS_BASICINFO_SID) infocd,\n" +
                "                d.importance,\n" +
                "                '2' flg,\n" +
                "                cb.SECURITY_CDs SECURITY_CD,\n" +
                "                a.NEWS_BASICINFO_SID \n"+
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
                "    WHERE  a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                time3 + "\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n";
        return em.createNativeQuery(sql).getResultList();
    }

    //查询负面事件公告的详细内容
    public List<Object> getNegativeBulletinBoardContent(String dateStart, String dateEnd) {
        String time1 = "";
        String time2 = "";
        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = "AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        //查询舆情公告中所有的指标
        String sql = "SELECT DISTINCT w.COMPANY_ID,\n" +
                "                cb.COMPANY_SNM,\n" +
                "                w.WARNING_TITLE,\n" +
                "                e.TYPE_NAME,\n" +
                "                w.NOTICE_DT,\n" +
                "                '' infocd,\n" +
                "                e.importance,\n" +
                "                '1' flg，\n" +
                "                cb.SECURITY_CDs SECURITY_CD,\n" +
                "                w.RECORD_SID \n"+
                "  FROM VW_COMPY_WARNINGS w\n" +
                " INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                " INNER JOIN lfs_COMPY_BASICINFO cb on cb.COMPANY_ID = w.company_id\n" +
                " WHERE w.WARNING_TITLE IS NOT NULL\n" +
                time2 + "\n" +
                "   and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getNegativeForBulletinBoard(String dateStart, String dateEnd) {
        String time1 = "";
        String time2 = "";


        if (dateEnd != null && dateEnd != "") {
            time1 = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
            time2 = " AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd;
        }
        //查询舆情公告中所有的公司id
        String sql = "select sum(cn) cn, company_id\n" +
                "  from (SELECT xw.company_id, count(distinct info.NOTICE_TITLE) cn\n" +
                "          FROM "+Contants.SCHEMA_NAME+"ANNOUNCE_BASICINFO info\n" +
                "         INNER JOIN XW_ANNOUNCE_COMPANY xw ON info.INFO_CD = xw.INFO_CD\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = xw.company_id  \n"+
                "         INNER JOIN rule ON xw.RULE_ID = rule.RULE_ID\n" +
                "         INNER JOIN sheet ON rule.SHEET_ID = sheet.SHEET_ID\n" +
                "                         and sheet.SHEET_L1 in\n" +
                "                             ('财务风险', '治理风险', '经营风险', '市场风险',\n" +
                "                              '法律法规风险')\n" +
                "         WHERE sheet.SHEET_TYPE = 2\n" +
                "           AND info.ISDEL = '0'\n" +
                time1 + "\n" +
                "         group by xw.company_id\n";
        //查询出预警中所有的目标风险类（财务、治理、经营、市场、法律法规）公司id
        sql += "        union all\n" +
                "        SELECT w.company_id, count(distinct w.WARNING_TITLE) cn\n" +
                "          FROM VW_COMPY_WARNINGS w\n" +
                "         INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "    INNER join  LFS_COMPY_BASICINFO cb  on  cb.COMPANY_ID = w.company_id  \n"+
                "         WHERE w.WARNING_TITLE IS NOT NULL\n" +
                "           and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                time2 + "\n" +
                "         group by w.company_id\n";
        //查询出所有负面新闻的目标风险类（财务、治理、经营、市场、法律法规）公司id
        sql += ") t\n" +
                " group by t.company_id\n" +
        "   having sum(cn)    > 0";


        return em.createNativeQuery(sql).getResultList();
    }


    @Override
    public List<Object> getCompyWarningCount(Long companyId, String dateStart, String dateEnd) {

        //五种风险指标条目查询
        String sql = "select \n" +
                "       sum(decode(type_key, 1, 1, 0)) sumfinance,\n" +
                "       sum(decode(type_key, 2, 1, 0)) sumgovern,\n" +
                "       sum(decode(type_key, 3, 1, 0)) summanage,\n" +
                "       sum(decode(type_key, 4, 1, 0)) summarket,\n" +
                "       sum(decode(type_key, 5, 1, 0)) sumlaw\n" +
                "  from (" +
                "        SELECT DISTINCT case\n" +
                "                 when e.id in (10, 12, 101, 102, 103, 104, 105) then\n" +
                "                  1\n" +
                "                 when e.id in (106, 107) then\n" +
                "                  2\n" +
                "                 else\n" +
                "                  -1\n" +
                "               end type_key,\n" +
                "               w.notice_dt notice_dt,\n" +
                "               w.WARNING_TITLE\n" +
                "          FROM VW_COMPY_WARNINGS w\n" +
                "         INNER JOIN COMPY_EVENT_TYPE e ON w.TYPE_ID = e.ID\n" +
                "         and w.company_id = "+ companyId +"\n" +
                "         WHERE w.WARNING_TITLE IS NOT NULL\n" +
                "           and e.id in (10, 12, 101, 102, 103, 104, 105, 106, 107)\n" +
                "           AND to_char(w.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd+"\n"+
                ") t\n" +
                " where t.type_key <> -1\n" +
                " ";

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getCompanyRisk(String dateStart, String dateEnd) {
        String sqlWhere = "";
        if(dateStart !=null && !"".equals(dateStart) && dateEnd !=null && !"".equals(dateEnd)){
            sqlWhere = " WHERE TO_CHAR(b.updt_dt,'YYYYMMDD') BETWEEN '"+dateStart+"' AND '"+dateEnd+"'";
        }
        String sql ="SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.COMPANY_SNM,\n" +
                "  A.REG_ADDR,\n" +
                "  A.POSITIONX,\n" +
                "  A.POSITIONY,\n" +
                "  A.SECURITY_CDS SECURITY_CD ,\n" +
                "  b.RISK_DETAIL,\n" +
                "  b.RISK_TYPE,\n" +
                "  TO_CHAR(b.UPDT_DT,'YYYY-MM-DD hh24:mi:ss'),\n" +
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
                "  END REGIONNAME \n" +
                "FROM LFS_COMPANY_RISK B\n" +
                "INNER JOIN LFS_COMPY_Basicinfo A\n" +
                "ON a.company_id = b.company_id\n" + sqlWhere;

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getCompyRiskCount() {
        String sql ="select t.company_id,\n" +
                "       sum(decode(t.risk_type, 1, 1,0)) caiwu,\n" +
                "       sum(decode(t.risk_type, 2, 1,0)) zhili,\n" +
                "       sum(decode(t.risk_type, 3, 1,0)) xinxi,\n" +
                "       sum(decode(t.risk_type, 4, 1,0)) qita\n" +
                "  from lfs_company_risk t\n" +
                " group by t.company_id";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getCompanyRiskById(String companyId) {
        String  sql = "select COMPANY_ID,RISK_TYPE,RISK_DETAIL from Lfs_company_risk " +
                "where COMPANY_ID = "+companyId;
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getCompyWarningCountfornews(Long companyId,String dateStart, String dateEnd) {

        //新闻五种风险指标条目查询
        String sql = "select \n" +
                "       sum(decode(type_key, 1, 1, 0)) sumfinance,\n" +
                "       sum(decode(type_key, 2, 1, 0)) sumgovern,\n" +
                "       sum(decode(type_key, 3, 1, 0)) summanage,\n" +
                "       sum(decode(type_key, 4, 1, 0)) summarket,\n" +
                "       sum(decode(type_key, 5, 1, 0)) sumlaw\n" +
                " from (\n" +
                "\n" +
                "        SELECT distinct case\n" +
                "                          when d1.SHEET_L1 = '财务事件' then\n" +
                "                           1\n" +
                "                          when d1.SHEET_L1 = '公司治理' then\n" +
                "                           2\n" +
                "                          when d1.SHEET_L1 = '经营情况' then\n" +
                "                           3\n" +
                "                          when d1.SHEET_L1 = '市场事件' then\n" +
                "                           4\n" +
                "                          when d1.SHEET_L1 = '法律法规' then\n" +
                "                           5\n" +
                "                          else\n" +
                "                           -1\n" +
                "                        end type_key,\n" +
                "                        a.post_dt notice_dt,\n" +
                "                        a.title WARNING_TITLE\n" +
                " FROM "+Contants.SCHEMA_NAME+"NEWS_BASICINFO a\n" +
                "    INNER JOIN XW_NEWS_COMPANY d\n" +
                "    ON a.NEWS_BASICINFO_SID = d.NEWS_BASICINFO_SID\n" +
                "                 and d.company_id="+ companyId +"\n" +
                "    INNEr join  WARNING_NEWS_RESULT W\n" +
                "    ON W.NEWS_BASICINFO_SID = a.NEWS_BASICINFO_SID\n" +
                "    INNER JOIN RULE C\n" +
                "    ON w.RULE_ID     = C.RULE_ID\n" +
                "    INNER JOIN SHEET D1\n" +
                "    ON C.SHEET_ID    = D1.SHEET_ID\n" +
                "    WHERE a.ISDEL                        = 0\n" +
                "    AND d.SCORE                        < 0\n" +
                "    AND d.RELEVANCE                   >= 0.8\n" +
                "    AND to_char(a.POST_DT,'YYYYMMDD') between " + dateStart + " and " + dateEnd+"\n" +
                "    AND C.SHEET_TYPE = 0\n" +
                "    AND D1.SHEET_TYPE = 0\n" +
                "    and d1.SHEET_L1 in\n" +
                "        ('财务事件', '公司治理',\n" +
                "         '经营情况', '市场事件',\n" +
                "         '法律法规' ) \n" +
                "    ) x  \n"+
                " where x.type_key <> -1\n" ;
        return em.createNativeQuery(sql).getResultList();
    }


    //共同SQL
    private String getAESqlForUserFocus(Long userId, String dateStart, String dateEnd,int eventType,int fuMian,int relevance) {
        String sqlTime = "";

        if (dateStart != null && !"".equals(dateStart) && dateEnd != null && !"".equals(dateEnd)) {
            sqlTime = " AND to_char(info.NOTICE_DT,'YYYYMMDD') between " + dateStart + " and "+ dateEnd;
        }
        String reasonSql="";
        switch (eventType) {
            case 1:
                reasonSql = " and sheet.SHEET_L1 =  '财务风险' \n";

                break;
            case 2:
                reasonSql = "  and sheet.SHEET_L1 = '治理风险' \n";

                break;
            case 3:
                reasonSql = "  and sheet.SHEET_L1 = '经营风险' \n";
                break;
            case 4:
                reasonSql = "  and sheet.SHEET_L1 ='市场风险' \n";
                break;
            case 5:
                reasonSql = "  and sheet.SHEET_L1 ='法律法规风险' \n";
                break;
            case 6:
                reasonSql = "  and sheet.SHEET_L1 not in ('财务风险','治理风险','经营风险','市场风险','法律法规风险') \n";
                break;
            default:
                reasonSql="";
        }
        String fuMianSql = "";
        if(fuMian != 0 &&relevance != 0){
            fuMianSql = " inner join LFS_ANNOUNCEMENT_RULE r on r.RULE_ID = rule.RULE_ID and r.SHEET_ID = sheet.SHEET_ID"+
                    " and r.PLUS_MINUS = '负面' and r.RELEVANCE = 1";
        }else{
            if(fuMian != 0){
                fuMianSql = " inner join LFS_ANNOUNCEMENT_RULE r on r.RULE_ID = rule.RULE_ID and r.SHEET_ID = sheet.SHEET_ID"+
                        " and r.PLUS_MINUS = '负面'";
            }

            if(relevance != 0){
                fuMianSql = " inner join LFS_ANNOUNCEMENT_RULE r on r.RULE_ID = rule.RULE_ID and r.SHEET_ID = sheet.SHEET_ID"+
                        " and r.RELEVANCE = 1 ";
            }
        }

        String sql ="SELECT DISTINCT b.SECURITY_CDs SECURITY_CD,\n" +
                "                        B.COMPANY_ID COMPANY_ID,\n" +
                "                        B.COMPANY_SNM COMPANY_SNM,\n" +
                "                        info.NOTICE_DT NOTICE_DT,\n" +
                "                        info.SRC_TYPE SRC_TYPE,\n" +
                "                        (sheet.SHEET_L1 || '-' || sheet.SHEET_L2) SHEET,\n" +
                "                        info.INFO_CD INFO_CD,\n" +
                "                        info.NOTICE_TITLE NOTICE_TITLE,\n" +
                "                        B.COMPANY_NM COMPANY_NM,\n" +
                "                        xwc.RULE_ID,\n" +
                "                        sheet.SHEET_ID\n" +
                "          FROM "+Contants.SCHEMA_NAME+"ANNOUNCE_BASICINFO info\n" +
                "         inner join (select xw.company_id, xw.INFO_CD, c.rule_id\n" +
                "                      from COMPY_ANNOUNCE_XW xw\n" +
                "                     inner join XW_ANNOUNCE_COMPANY C ON xw.INFO_CD =\n" +
                "                                                         C.INFO_CD\n" +
                "                                                     AND xw.COMPANY_ID =\n" +
                "                                                         C.COMPANY_ID\n" +
                "                                                     and xw.ISDEL = '0') xwc ON info.INFO_CD =\n" +
                "                                                                                xwc.INFO_CD\n" +
                "         INNER JOIN lfs_COMPY_BASICINFO B ON xwc.COMPANY_ID = B.COMPANY_ID\n" +
                "         INNER JOIN USER_FOCUS f ON B.COMPANY_ID = f.FOCUS_ID\n" +
                "                                AND f.USER_ID = "+ userId + "\n" +
                "         INNER JOIN rule ON xwC.RULE_ID = rule.RULE_ID\n" +
                "         INNER JOIN sheet ON rule.SHEET_ID = sheet.SHEET_ID\n" +
                fuMianSql +
                reasonSql +
                "         WHERE info.ISDEL = '0'\n" +
                "           AND info.SRC_TYPE NOT IN\n" +
                "               ('美国证监会', '香港交易所', '发行人网站', '香港基金公告')"
                + sqlTime;

        return sql;
    }

}