package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.entity.CompyBalancesheet;
import com.cscs.listedfacesys.entity.CompyCashflow;
import com.cscs.listedfacesys.entity.CompyIncomestate;

import java.util.List;

/**
 * Created by hj on 2018/2/27.
 */
public interface CompanyFinanceService {

    //获取最新的年报时间和最新的年报外的报告时间
    public List<Object> getRptDtChk(Long id);

    //获取近三年财务数据(资产负债表)
    CompyBalancesheet getBalancesheetNewest(Long id, String strRptDtNewest, String rptTimetypeCd);

    List<CompyBalancesheet> getBalancesheetNewest(Long id, Object[] strRptDtNewest,String rptTimetypeCd);

    //获取近三年财务数据(利润表)
    CompyIncomestate getIncomestateNewest(Long id, String strRptDtNewest, String rptTimetypeCd);

    List<CompyIncomestate> getIncomestateNewest(Long id, Object[] strRptDtNewest,String rptTimetypeCd);

    //获取近三年财务数据(现金流量表)
    CompyCashflow getCashflowNewest(Long id, String strRptDtNewest, String rptTimetypeCd);

    List<CompyCashflow> getCashflowNewest(Long id, Object[] strRptDtNewest,String rptTimetypeCd);

    //获取近三年年报时间
    List<Object> getRptDtNewest(Long id);


    //以下为财务报表详细页所用

    List<String> findColumnEnm(Long companyId, int subjectType, String tableName);
    List<Object> findColumnEnmForLabel(Long companyId, int subjectType, String tableName);
    String findRptDtRecently(Long companyId, String rptTimetypeCd, String tableName);
    Object findListDt(Long companyId);
    List<Object> findRptDt(Long companyId, String rptTimetypeCd, int endDate, String tableName);
    List<Object> findCompyReport(Long companyId, String strColumnEnm1, String strColumnEnm2, String rptTimetypeCd, String strRptDt, int subjectType, String tableName);


}
