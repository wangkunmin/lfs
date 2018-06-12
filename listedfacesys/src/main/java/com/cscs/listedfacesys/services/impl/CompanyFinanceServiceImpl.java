package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.entity.CompyBalancesheet;
import com.cscs.listedfacesys.entity.CompyCashflow;
import com.cscs.listedfacesys.entity.CompyIncomestate;
import com.cscs.listedfacesys.services.CompanyFinanceService;
import com.cscs.util.Contants;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by hj on 2018/2/27.
 */
@Service
public class CompanyFinanceServiceImpl implements CompanyFinanceService {

    @PersistenceContext
    EntityManager em;

    //获取最新的年报时间和最新的年报外的报告时间
    public List<Object> getRptDtChk(Long id) {
        String sql =
                "select t.RPT_DT, t.RPT_TIMETYPE_CD\n" +
                        "  from (SELECT RPT_DT,\n" +
                        "               RPT_TIMETYPE_CD,\n" +
                        "               row_number() over(partition by decode(RPT_TIMETYPE_CD, 1, 1, 2) order by rpt_dt desc) mm\n"
                        +
                        "          FROM "+ Contants.SCHEMA_NAME+"COMPY_BALANCESHEET\n" +
                        "         WHERE COMPANY_ID = " + id + "\n" +
                        "           AND ISDEL = 0\n" +
                        "           AND COMBINE_TYPE_CD = 1\n" +
                        "           AND DATA_AJUST_TYPE in ('2','3')) t\n" +
                        " where t.mm = 1\n" +
                        " order by t.RPT_TIMETYPE_CD";
        List<Object> query = em.createNativeQuery(sql).getResultList();

        return query;
    }

    //获取近三年财务数据(资产负债表)
    public CompyBalancesheet getBalancesheetNewest(Long id, String strRptDtNewest, String rptTimetypeCd) {
        String sql = "SELECT a.* FROM "+ Contants.SCHEMA_NAME+"COMPY_BALANCESHEET a WHERE a.COMPANY_ID = " + id
                + " AND a.ISDEL = 0 AND a.COMBINE_TYPE_CD = 1 AND a.DATA_AJUST_TYPE in('2','3')  AND a.RPT_TIMETYPE_CD =  " + rptTimetypeCd + "   AND a.RPT_DT = "
                + strRptDtNewest + " ORDER BY a.LATEST_NOTICE_DT DESC";
        List<CompyBalancesheet> query = em.createNativeQuery(sql, CompyBalancesheet.class)
                .getResultList();
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    //获取时间区间内(资产负债表)
    public List<CompyBalancesheet> getBalancesheetNewest(Long id, Object[] arrayRptDtNewest, String rptTimetypeCd) {
        String range = "(";
        for (int i = 0; i < arrayRptDtNewest.length; i++) {
            range += "\'" + arrayRptDtNewest[i].toString() + "\'";
            if (i != arrayRptDtNewest.length - 1) {
                range += ",";
            }
        }
        range += ")";

        String sql = "SELECT a.* FROM "+ Contants.SCHEMA_NAME+"COMPY_BALANCESHEET a WHERE a.COMPANY_ID = " + id
                + " AND a.ISDEL = 0 AND a.COMBINE_TYPE_CD = 1 AND a.DATA_AJUST_TYPE in('2','3')  AND a.RPT_TIMETYPE_CD =  " + rptTimetypeCd + "  AND a.RPT_DT IN "
                + range + " ORDER BY a.RPT_DT";
        List<CompyBalancesheet> query = em.createNativeQuery(sql, CompyBalancesheet.class).getResultList();
        return query;
    }

    //获取近三年财务数据(利润表)
    public CompyIncomestate getIncomestateNewest(Long id, String strRptDtNewest, String rptTimetypeCd) {
        String sql = "SELECT a.* FROM "+Contants.SCHEMA_NAME+"COMPY_INCOMESTATE a WHERE a.COMPANY_ID = " + id
                + " AND a.ISDEL = 0 AND a.COMBINE_TYPE_CD = 1 AND a.DATA_AJUST_TYPE in('2','3')   AND a.RPT_TIMETYPE_CD =  " + rptTimetypeCd + "   AND a.RPT_DT = "
                + strRptDtNewest + " ORDER BY a.LATEST_NOTICE_DT DESC";
        List<CompyIncomestate> query = em.createNativeQuery(sql, CompyIncomestate.class)
                .getResultList();
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    //获取时间区间内(利润表)
    public List<CompyIncomestate> getIncomestateNewest(Long id, Object[] arrayRptDtNewest, String rptTimetypeCd) {
        String range = "(";
        for (int i = 0; i < arrayRptDtNewest.length; i++) {
            range += "\'" + arrayRptDtNewest[i].toString() + "\'";
            if (i != arrayRptDtNewest.length - 1) {
                range += ",";
            }
        }
        range += ")";
        String sql = "SELECT a.* FROM "+Contants.SCHEMA_NAME+"COMPY_INCOMESTATE a WHERE a.COMPANY_ID = " + id
                + " AND a.ISDEL = 0 AND a.COMBINE_TYPE_CD = 1 AND a.DATA_AJUST_TYPE in('2','3')   AND a.RPT_TIMETYPE_CD =  " + rptTimetypeCd + "    AND a.RPT_DT in "
                + range + " ORDER BY a.RPT_DT";
        List<CompyIncomestate> query = em.createNativeQuery(sql, CompyIncomestate.class).getResultList();
        return query;
    }

    //获取近三年财务数据(现金流量表)
    public CompyCashflow getCashflowNewest(Long id, String strRptDtNewest, String rptTimetypeCd) {
        String sql = "SELECT a.* FROM "+Contants.SCHEMA_NAME+"COMPY_CASHFLOW a WHERE a.COMPANY_ID = " + id
                + " AND a.ISDEL = 0 AND a.COMBINE_TYPE_CD = 1 AND a.DATA_AJUST_TYPE in('2','3')   AND a.RPT_TIMETYPE_CD = " + rptTimetypeCd + "   AND a.RPT_DT = "
                + strRptDtNewest + " ORDER BY a.LATEST_NOTICE_DT DESC";
        List<CompyCashflow> query = em.createNativeQuery(sql, CompyCashflow.class).getResultList();
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    //获取时间区间内(现金流量表)
    public List<CompyCashflow> getCashflowNewest(Long id, Object[] arrayRptDtNewest, String rptTimetypeCd) {
        String range = "(";
        for (int i = 0; i < arrayRptDtNewest.length; i++) {
            range += "\'" + arrayRptDtNewest[i].toString() + "\'";
            if (i != arrayRptDtNewest.length - 1) {
                range += ",";
            }
        }
        range += ")";
        String sql = "SELECT a.* FROM "+Contants.SCHEMA_NAME+"COMPY_CASHFLOW a WHERE a.COMPANY_ID = " + id
                + " AND a.ISDEL = 0 AND a.COMBINE_TYPE_CD = 1 AND a.DATA_AJUST_TYPE in('2','3')   AND a.RPT_TIMETYPE_CD = " + rptTimetypeCd + "   AND a.RPT_DT in "
                + range + " ORDER BY a.RPT_DT";
        List<CompyCashflow> query = em.createNativeQuery(sql, CompyCashflow.class).getResultList();
        return query;
    }

    //获取近三年年报时间
    public List<Object> getRptDtNewest(Long id) {
        String sql = "SELECT a.* FROM (SELECT RPT_DT FROM "+ Contants.SCHEMA_NAME+"COMPY_BALANCESHEET WHERE COMPANY_ID = " + id
                + " AND ISDEL = 0 AND RPT_TIMETYPE_CD = 1 AND COMBINE_TYPE_CD = 1 AND DATA_AJUST_TYPE = 3 ORDER BY RPT_DT DESC ) a WHERE rownum <= 3";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }



    @Override
    public List<String> findColumnEnm(Long companyId, int subjectType, String tableName) {
        String sql = "SELECT DISTINCT L.SUBJECT_ENM FROM LKP_FINANSUBJECT_DISP L, (SELECT COMPANY_TYPE FROM " + tableName + " WHERE COMPANY_ID = " + companyId + " AND ISDEL = 0 AND rownum = 1) C  WHERE L.COMPANY_TYPE = C.COMPANY_TYPE AND L.ISDEL = 0 AND L.SUBJECT_TYPE = " + subjectType + " AND L.DISP_TYPE = 2 AND L.IS_LABEL <> 1 AND L.IS_FORMULAR = 0";
        List<String> query = em.createNativeQuery(sql).getResultList();
        return query;
    }

    @Override
    public List<Object> findColumnEnmForLabel(Long companyId, int subjectType, String tableName) {
        String sql = "SELECT L.LKP_FINANSUBJECT_DISP_SID, L.SUBJECT_CD, L.SUBJECT_NM, L.PARENT_SUBJECT_CD, L.SUBJECT_LEVEL, L.IS_LABEL, L.IS_BOLD, L.IS_FORMULAR FROM LKP_FINANSUBJECT_DISP L, (SELECT COMPANY_TYPE FROM " + tableName + " WHERE COMPANY_ID = " + companyId + " AND ISDEL = 0 AND rownum = 1) C WHERE L.COMPANY_TYPE = C.COMPANY_TYPE AND  L.ISDEL = 0 AND L.SUBJECT_TYPE = " + subjectType + " AND L.DISP_TYPE = 2 AND L.IS_LABEL = 1 ORDER BY L.SUBJECT_CD, L.LKP_FINANSUBJECT_DISP_SID";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }

    @Override
    public String findRptDtRecently(Long companyId, String rptTimetypeCd, String tableName) {
//        String sql = "SELECT RPT_DT FROM " + tableName + " WHERE COMPANY_ID = " + companyId + " AND ISDEL = 0 AND RPT_TIMETYPE_CD = " + rptTimetypeCd + " AND COMBINE_TYPE_CD = 1 AND DATA_AJUST_TYPE = 3 ORDER BY RPT_DT DESC";
        String sql ="SELECT RPT_DT FROM(SELECT RPT_DT,ROW_NUMBER() OVER (PARTITION BY RPT_DT ORDER BY LATEST_NOTICE_DT DESC)LAST\n" +
                "FROM "+ Contants.SCHEMA_NAME+"COMPY_BALANCESHEET WHERE COMPANY_ID = "+companyId+" AND ISDEL = 0 AND RPT_TIMETYPE_CD = ?1 AND COMBINE_TYPE_CD = 1 AND DATA_AJUST_TYPE IN('2','3'))\n" +
                "WHERE LAST = 1 ORDER BY RPT_DT DESC";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1,rptTimetypeCd);
        List<Object> queryList = query.getResultList();
        if (queryList.size() > 0) {
            return queryList.get(0).toString();
        } else {
            return null;
        }
    }

    @Override
    public Object findListDt(Long companyId) {
        String sql = "SELECT LIST_DT FROM SECURITY WHERE ISDEL = 0 AND COMPANY_ID = " + companyId + " AND LIST_ST = 0 ORDER BY LIST_DT";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        if (query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Object> findRptDt(Long companyId, String rptTimetypeCd, int endDate, String tableName) {
        StringBuilder sbSql = new StringBuilder("");
        sbSql.append("SELECT DISTINCT(RPT_DT) FROM ");
        sbSql.append(tableName);
        sbSql.append(" WHERE COMPANY_ID = ");
        sbSql.append(companyId);
        sbSql.append(" AND ISDEL = 0 AND RPT_DT >= ");
        sbSql.append(endDate);
        if (rptTimetypeCd == null || "".equals(rptTimetypeCd)) {
            ;
        } else {
            sbSql.append(" AND RPT_TIMETYPE_CD IN (");
            sbSql.append(rptTimetypeCd);
            sbSql.append(")");
        }
        sbSql.append(" ORDER BY RPT_DT DESC");

        List<Object> query = em.createNativeQuery(sbSql.toString()).getResultList();
        return query;
    }

    @Override
    public List<Object> findCompyReport(Long companyId, String strColumnEnm1, String strColumnEnm2, String rptTimetypeCd, String strRptDt, int subjectType, String tableName) {
        String sql = "SELECT * FROM " +
                "(SELECT a.RPT_DT, b.LKP_FINANSUBJECT_DISP_SID, b.SUBJECT_CD, b.SUBJECT_NM, b.PARENT_SUBJECT_CD, b.SUBJECT_LEVEL, b.IS_LABEL, b.IS_BOLD, b.IS_FORMULAR, a.PARAM_VALUE FROM " +
                "(SELECT RPT_DT AS RPT_DT, PARAM_NAME, PARAM_VALUE FROM " +
//                "(SELECT RPT_DT, RPT_DT RPT_DT_LABEL," + strColumnEnm1 + " FROM " + tableName +
//                " WHERE COMPANY_ID = " + companyId + " AND RPT_TIMETYPE_CD IN (" + rptTimetypeCd + ") AND COMBINE_TYPE_CD = 1 AND DATA_AJUST_TYPE = 3 AND ISDEL = 0) " +
                "(SELECT RPT_DT, RPT_DT RPT_DT_LABEL," + strColumnEnm1 + " FROM (SElECT RPT_DT," +strColumnEnm1+",ROW_NUMBER() OVER (PARTITION BY RPT_DT ORDER BY LATEST_NOTICE_DT DESC) LAST"+
                " FROM " + tableName + " WHERE COMPANY_ID = " + companyId + " AND RPT_TIMETYPE_CD IN (" + rptTimetypeCd + ") AND COMBINE_TYPE_CD = 1 AND DATA_AJUST_TYPE IN ('2','3') AND ISDEL = 0) WHERE LAST = 1)" +
                "UNPIVOT(PARAM_VALUE FOR PARAM_NAME IN (" + strColumnEnm2 + "))) a " +
                "JOIN " +
                "(SELECT b.* " +
                "FROM LKP_FINANSUBJECT_DISP b, " +
                "(SELECT COMPANY_TYPE FROM " + tableName + " WHERE COMPANY_ID = " + companyId + " AND ISDEL = 0 AND rownum = 1) F " +
                "WHERE b.COMPANY_TYPE = F.COMPANY_TYPE AND b.SUBJECT_TYPE = " + subjectType +" AND b.ISDEL = 0 AND b.DISP_TYPE = 2) b " +
                "ON b.SUBJECT_ENM = a.PARAM_NAME) " +
                "a PIVOT(MAX (a.PARAM_VALUE) FOR RPT_DT IN (" + strRptDt + ")) " +
                "ORDER BY SUBJECT_CD, LKP_FINANSUBJECT_DISP_SID";

        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }



}
