package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.entity.CompyBalancesheet;
import com.cscs.listedfacesys.entity.CompyCashflow;
import com.cscs.listedfacesys.entity.CompyIncomestate;
import com.cscs.listedfacesys.services.CompanyFinanceService;
import com.cscs.util.Contants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by hj on 2018/2/27
 * 公司财务报表信息
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/financeInfo")
public class FinanceInfoController {

    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    CompanyFinanceService companyFinanceService;

    //根据企业标识符查询企业近三年及一期主要财务数据
    @RequestMapping(value = "/finance/{id}", method = RequestMethod.GET)
    public BaseOutData getFinanceDataNewest(@PathVariable Long id) throws JsonProcessingException {
        BaseOutData out = new BaseOutData();
        CompanyOutData outData = new CompanyOutData();
        List<CompyFinanceOutData> listCompyFinanceOutData = new ArrayList<CompyFinanceOutData>();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int intStartDate = Integer.parseInt(df1.format(calendar.getTime()));
        calendar.clear();
        calendar.set(Calendar.YEAR, intStartDate);
        String strStartDate = df2.format(calendar.getTime());

        try {
            //判断是否需要显示最新的季报
            List<Object> rptDtChk = companyFinanceService.getRptDtChk(id);
            Long rptDtYear = 0L;
            Long rptDtQuarter = 0L;
            String rptTimetypeCd = "";
            for (int i = 0; i < rptDtChk.size(); i++) {
                Object[] chk = (Object[]) rptDtChk.get(i);
                Long rptDt = chk[0] != null ? Long.parseLong(chk[0].toString()) : 0;
                rptTimetypeCd = chk[1] != null ? chk[1].toString() : "";
                if ("1".equals(rptTimetypeCd)) {
                    rptDtYear = rptDt;
                } else {
                    rptDtQuarter = rptDt;
                }
            }

            if (rptDtQuarter > rptDtYear) {
                String strRptDtNewestQuarter = rptDtQuarter.toString();
                CompyFinanceOutData tmpCompyFinanceOutData = new CompyFinanceOutData();
                tmpCompyFinanceOutData.setRptDtBak(Long.valueOf(strRptDtNewestQuarter));

                CompyBalancesheet tmpCompyBalancesheet = companyFinanceService
                        .getBalancesheetNewest(id, strRptDtNewestQuarter, rptTimetypeCd);
                if (tmpCompyBalancesheet != null) {
                    BeanUtils.copyProperties(tmpCompyBalancesheet, tmpCompyFinanceOutData);
                }

                CompyIncomestate tmpCompyIncomestate = companyFinanceService
                        .getIncomestateNewest(id, strRptDtNewestQuarter, rptTimetypeCd);
                if (tmpCompyIncomestate != null) {
                    BeanUtils.copyProperties(tmpCompyIncomestate, tmpCompyFinanceOutData);
                }

                CompyCashflow tmpCompyCashflow = companyFinanceService
                        .getCashflowNewest(id, strRptDtNewestQuarter, rptTimetypeCd);
                if (tmpCompyCashflow != null) {
                    BeanUtils.copyProperties(tmpCompyCashflow, tmpCompyFinanceOutData);
                }

                listCompyFinanceOutData.add(tmpCompyFinanceOutData);
                //}
            }
            List<Object> rptDtNewest = companyFinanceService.getRptDtNewest(id);
            Object[] arrayRptDtNewest = rptDtNewest.toArray();
            List<CompyBalancesheet> compyBalancesheetList = companyFinanceService.getBalancesheetNewest(id, arrayRptDtNewest, "1");
            List<CompyIncomestate> compyIncomestateList = companyFinanceService.getIncomestateNewest(id, arrayRptDtNewest, "1");
            List<CompyCashflow> compyCashflowList = companyFinanceService.getCashflowNewest(id, arrayRptDtNewest, "1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String tmpdt = "";
            String maxdt = "";
            int jindex = 0;
            CompyBalancesheet compyBalancesheet = null;
            CompyIncomestate compyIncomestate = null;
            CompyCashflow compyCashflow = null;

            for (int i = 0; i < arrayRptDtNewest.length; i++) {
                CompyFinanceOutData compyFinanceOutData = new CompyFinanceOutData();
                String sbRptDtNewest = arrayRptDtNewest[i].toString();
                compyFinanceOutData.setRptDtBak(Long.valueOf(sbRptDtNewest));

                for (int j = 0; j < compyBalancesheetList.size(); j++) {
                    compyBalancesheet = compyBalancesheetList.get(j);
                    if (compyBalancesheet.getRptDt().toString().equals(sbRptDtNewest)) {
                        tmpdt = sdf.format(compyBalancesheet.getLatestNoticeDt());
                        if ((maxdt != "" && tmpdt.compareTo(maxdt) > 0) || maxdt.equals("")) {
                            maxdt = tmpdt;
                            jindex = j;
                        }
                    }
                }
                compyBalancesheet = compyBalancesheetList.get(jindex);
                if (compyBalancesheet != null) {
                    BeanUtils.copyProperties(compyBalancesheet, compyFinanceOutData);
                }
                jindex = 0;
                maxdt = "";

                for (int j = 0; j < compyIncomestateList.size(); j++) {
                    compyIncomestate = compyIncomestateList.get(j);
                    if (compyIncomestate.getRptDt().toString().equals(sbRptDtNewest)) {
                        tmpdt = sdf.format(compyIncomestate.getLatestNoticeDt());
                        if ((maxdt != "" && tmpdt.compareTo(maxdt) > 0) || maxdt.equals("")) {
                            maxdt = tmpdt;
                            jindex = j;
                        }
                    }
                }
                compyIncomestate = compyIncomestateList.get(jindex);
                if (compyIncomestate != null) {
                    BeanUtils.copyProperties(compyIncomestate, compyFinanceOutData);
                }
                jindex = 0;
                maxdt = "";

                for (int j = 0; j < compyCashflowList.size(); j++) {
                    compyCashflow = compyCashflowList.get(j);
                    if (compyCashflow.getRptDt().toString().equals(sbRptDtNewest)) {
                        tmpdt = sdf.format(compyCashflow.getLatestNoticeDt());
                        if ((maxdt != "" && tmpdt.compareTo(maxdt) > 0) || maxdt.equals("")) {
                            maxdt = tmpdt;
                            jindex = j;
                        }
                    }
                }
                compyCashflow = compyCashflowList.get(jindex);
                if (compyCashflow != null) {
                    BeanUtils.copyProperties(compyCashflow, compyFinanceOutData);
                }
                jindex = 0;
                maxdt = "";

                listCompyFinanceOutData.add(compyFinanceOutData);
            }

            outData.setCompyFinanceOutData(listCompyFinanceOutData);

            if (listCompyFinanceOutData != null && listCompyFinanceOutData.size()>0) {
                out.setCode("0");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",listCompyFinanceOutData);
                out.setData(map);
            }else {
                out.setCode("1");
                out.setMessage("近三年财务报表信息，获取数据为空");
                logger.info("近三年财务报表信息，获取数据为空");
            }

        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("近三年财务报表信息，获取数据异常，异常信息："+e.getMessage());
            logger.info("近三年财务报表信息，获取数据异常，异常信息："+e.getMessage());
        }

        return out;
    }

    //财务详细报表
    @RequestMapping(value = "/cashflow", method = RequestMethod.POST)
    public BaseOutData getCompyCashflow(@RequestBody CompanyReportIn inData) throws JsonProcessingException {
        BaseOutData out = new BaseOutData();
        long companyId = inData.getCompanyId();
        int subjectType = inData.getSubjectType();
        String rptTimetypeCd = inData.getRptTimetypeCd();
        String rptDt = inData.getRptDt();

        String tableName = "";
        if (subjectType == 1) {
            tableName = Contants.SCHEMA_NAME+"COMPY_BALANCESHEET";
        } else if (subjectType == 2) {
            tableName = Contants.SCHEMA_NAME+"COMPY_INCOMESTATE";
        } else if (subjectType == 3) {
            tableName = Contants.SCHEMA_NAME+"COMPY_CASHFLOW";
        }

        CompanyReport dtoCompanyReport = new CompanyReport();
        try {
            List<String> columnEnm = companyFinanceService.findColumnEnm(companyId, subjectType, tableName);

            StringBuilder sbColumnEnm1 = new StringBuilder("");
            StringBuilder sbColumnEnm2 = new StringBuilder("");
            for (int i = 0; i < columnEnm.size(); i++) {
                if (!"RPT_DT_LABEL".equals(columnEnm.get(i))) {
                    sbColumnEnm1.append(columnEnm.get(i));
                }
                sbColumnEnm2.append(columnEnm.get(i)).append(" AS '").append(columnEnm.get(i)).append("'");
                if (i != columnEnm.size() - 1) {
                    if (!"RPT_DT_LABEL".equals(columnEnm.get(i))) {
                        sbColumnEnm1.append(", ");
                    }
                    sbColumnEnm2.append(", ");
                }
            }

            String strColumnEnm1 = sbColumnEnm1.toString();
            String strColumnEnm2 = sbColumnEnm2.toString();

            StringBuilder sbRptDt = new StringBuilder("");

            if ("最新".equals(rptDt.trim())) {
                String[] rptTimetypeCds = rptTimetypeCd.split(",");
                for (int i = 0; i < rptTimetypeCds.length; i++) {
                    sbRptDt.append(companyFinanceService.findRptDtRecently(companyId, rptTimetypeCds[i], tableName));
                    if (i != rptTimetypeCds.length - 1) {
                        sbRptDt.append(", ");
                    }
                }
            } else {
                int endDate = 0;
                if ("上市以来".equals(rptDt.trim())) {
                    DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    endDate = Integer.valueOf(sdf.format(companyFinanceService.findListDt(companyId)));
                } else {
                    int recentYearTmp = 0;
                    int recentYear = 0;
                    try {
                        recentYearTmp = Integer.parseInt(rptDt);
                        recentYear = -recentYearTmp;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.YEAR, recentYear);
                        endDate = Integer.parseInt(sdf.format(calendar.getTime()));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                List<Object> rptDts = companyFinanceService.findRptDt(companyId, rptTimetypeCd, endDate, tableName);

                for (int i = 0; i < rptDts.size(); i++) {
                    sbRptDt.append(rptDts.get(i).toString());
                    if (i != rptDts.size() - 1) {
                        sbRptDt.append(", ");
                    }
                }
            }

            String strRptDt = sbRptDt.toString();
            strRptDt = strRptDt.replace(", null", "");
            strRptDt = strRptDt.replace("null, ", "");
            List<Object> compyReports = new ArrayList<Object>();

            if (strRptDt == null || "".equals(strRptDt.trim()) || "null".equals(strRptDt)) {
                ;
            } else {
                compyReports = companyFinanceService.findCompyReport(companyId, strColumnEnm1, strColumnEnm2, rptTimetypeCd, strRptDt, subjectType, tableName);
                if (compyReports.size() != 0) {
                    List<Object> strColumnEnmLabel = companyFinanceService.findColumnEnmForLabel(companyId, subjectType, tableName);
                    for (int i = 0; i < strColumnEnmLabel.size(); i++) {
                        compyReports.add(strColumnEnmLabel.get(i));
                    }
                }
            }


            dtoCompanyReport.setCompyReport(compyReports);

            if (compyReports != null && compyReports.size()>0) {
                out.setCode("0");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",compyReports);
                out.setData(map);
            }else {
                out.setCode("1");
                out.setMessage("财务详细报表，获取数据为空");
                logger.info("财务详细报表，获取数据为空");
            }

        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("财务详细报表，获取数据异常，异常信息："+e.getMessage());
            logger.info("财务详细报表，获取数据异常，异常信息："+e.getMessage());
        }
        return out;
    }
}
