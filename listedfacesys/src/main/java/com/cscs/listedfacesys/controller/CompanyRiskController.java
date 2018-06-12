package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.busi.AnnounceBusiService;
import com.cscs.listedfacesys.busi.NewsClassBusiService;
import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.CompanyInfoService;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by wzy on 2018/2/1
 * 上市公司风险展台
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/companyRisk")
public class CompanyRiskController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NewsClassesService newsClassService;

    @Autowired
    WarningAnnounceService warningAnnounceService;

    @Autowired
    CompanyInfoService companyInfoServices;

    //新闻事件趋势图
    @RequestMapping(value = "/newsEvent", method = RequestMethod.POST)
    public BaseOutData newsEvent(@RequestBody TendencyChartInData inData) {
        int newsCount = 0;
        int negativeNewsCount = 0;
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        //获取当前日期
        Date dt = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = sdf.format(dt);

        Map<String,List<TendencyChartOutData>> map = new HashMap<String,List<TendencyChartOutData>>();
        try {
            itemList = newsClassService.findchartByCompanyId(inData);
            List<TendencyChartOutData>  outList = new ArrayList<TendencyChartOutData>();
            if(itemList!=null && itemList.size()>0){
                Object[] tmp = (Object[]) itemList.get(itemList.size()-1);
                String minDate = tmp[2] != null ? tmp[2].toString() : "";
                if("".equals(minDate)){
                    minDate = inData.getStartDate();
                }else {
                    if(Integer.parseInt(minDate.substring(0,4)) <
                            Integer.parseInt(inData.getStartDate().substring(0,4))){
                        minDate = inData.getStartDate();
                    }else{
                        minDate = minDate.substring(0,4)+"-01-01";
                    }
                }
                //根据当前日期，生成包含当前日期前7个月的年月
                String[] dateStr = NewsClassBusiService.getMonth7ByNowDate(minDate,inData.getEndDate());
                for (int j=0;j<dateStr.length;j++){
                    List<TendencyChartInfoData> list = new ArrayList<TendencyChartInfoData>();
                    TendencyChartOutData outData = new TendencyChartOutData();
                    newsCount = 0;
                    negativeNewsCount = 0;
                    for (int i = 0; i < itemList.size(); i++) {
                        Object[] item = (Object[]) itemList.get(i);
                        //按年月对数据进行分组
                        String postDt = item[2] != null ? item[2].toString() : "";
                        if(null != postDt && !"".equals(postDt)){
                            String year_Month = postDt.substring(0,7);
                            if(dateStr[j].equals(year_Month)){
                                TendencyChartInfoData info = new TendencyChartInfoData();
                                info.setNewCount(Integer.parseInt(item[0] != null ? item[0].toString() : "0"));
                                info.setNegativeNewsCount(Integer.parseInt(item[1] != null ? item[1].toString() : "0"));
                                info.setPostDt(item[2] != null ? item[2].toString() : "");
                                if(info.getNewCount()==0){
                                    info.setRatio("0");
                                }else {
                                    info.setRatio(String.format("%.2f", (double) info.getNegativeNewsCount() / info.getNewCount() * 100) + "%");
                                }

                                list.add(info);
                                newsCount += item[0] != null ? Integer.valueOf(item[0].toString()) : 0;
                                negativeNewsCount += item[1] != null ? Integer.valueOf(item[1].toString()) : 0;
                            }
                        }
                    }
                outData.setNegativeTotalCount(negativeNewsCount);
                outData.setTotalCount(newsCount);
                if(newsCount==0){
                    outData.setTotalRatio("0");
                }else {
                    outData.setTotalRatio(String.format("%.2f", (double) negativeNewsCount / newsCount * 100) + "%");
                }
                    outData.setCountDate(dateStr[j]);

                    //根据日期，生成该日期月份的所有日期的数据
                    List<TendencyChartInfoData> reslist =NewsClassBusiService.getDaysStr(nowDateStr,dateStr[j],list);

                    outData.setSingleNews(reslist);
                    outList.add(outData);
                }

                map.put("conent",outList);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("新闻事件趋势图，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("新闻事件趋势图，获取数据异常！异常信息："+e.getMessage());
            logger.error("新闻事件趋势图，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;

    }

    /**
     * 风险公告
     * @param companyId
     * @param type 类型：1.财务风险 2.治理风险 3.经营风险 4.市场风险 5.法律法规风险
     * @return
     */
    @RequestMapping(value = "/singleCompanyWR/{companyId}/{type}/{dateStart}/{dateEnd}", method = RequestMethod.GET)
    public BaseOutData getSingleCompanyWR(@PathVariable Long companyId, @PathVariable int type, @PathVariable String dateStart, @PathVariable String dateEnd) {
        BaseOutData outData = new BaseOutData();
        Map<String,List<WarningInfoData>> data = new TreeMap<>();

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStart = null;
        String dateEnd  = null;*/
        List<Object> singleList;
        singleList = warningAnnounceService.getThreeSideWarning(companyId, type, dateStart, dateEnd);
        if (singleList==null){
            singleList = new ArrayList<Object>();
        }
        List<Object> singleListfornews = warningAnnounceService.getThreeSideWarningfornews(companyId, type, dateStart, dateEnd);
        for (Object it : singleListfornews) {
            Object[] item = (Object[]) it;
            singleList.add(item);
        }
        if ( singleList ==null || singleList.size() == 0 ) {
            outData.setCode("1");
            outData.setMessage("[查询失败:未查询到数据]");
            logger.info("[查询失败:未查询到数据]" + singleList);
            return outData;
        }

        List<WarningInfoData> outList = AnnounceBusiService.getThreeSideWarningData(singleList);

        data.put("warningInfoList", outList);
        outData.setCode("0");
        outData.setMessage("The query is successful!");
        outData.setData(data);
        logger.info("[查询成功]" + outList);
        return outData;
    }

   /* //舆情事件(原词云列表，调用外部接口)
    @RequestMapping(value = "/newsConsensus", method = RequestMethod.POST)
    public BaseOutData getNewsCount(@RequestBody NewsWarningInData inData) {
        return newsClassService.findNewsFromSolr(inData);
    }*/

    //舆情事件
    @RequestMapping(value = "/newsConsensus", method = RequestMethod.POST)
    public BaseOutData getNewsCount(@RequestBody NewsWarningInData inData) {
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        List<CompanyNewsOutData> reslist = new ArrayList<CompanyNewsOutData>();
        Map<String, List<CompanyNewsOutData>> map = new HashMap<String, List<CompanyNewsOutData>>();
        try {
            //查询新闻舆情风险总数
            int count =  newsClassService.getNewsRiskByCompanyNmCount(inData);
            itemList =  newsClassService.getNewsRiskByCompanyNm(inData);
            if(itemList !=null && itemList.size()>0){
                for (int i = 0; i <itemList.size() ; i++) {
                    Object[] item = (Object[]) itemList.get(i);
                    CompanyNewsOutData outData = new CompanyNewsOutData();
                    String title = item[2]!=null ? item[2].toString() : "";
                    outData.setTitle(title.replaceAll("\\\\", ""));
                    outData.setUrl(item[5]!=null ? item[5].toString() :"");
                    outData.setDate(item[3]!=null ? item[3].toString() :"");
                    outData.setCnn_score(item[7]!=null ? Integer.parseInt(item[7].toString()) : 0);
                    outData.setNewsSource(item[10]!=null ? item[10].toString() :"");
                    outData.setImportance(item[8]!=null ? item[8].toString() :"");
                    outData.setPlainText(item[4]!=null ? item[4].toString() :"");
                    outData.setRelevance(item[9]!=null ? item[9].toString() :"");
                    outData.setPostDt(item[11]!=null ? item[11].toString() :"");
                    outData.setNewsCode(item[1]!=null ? item[1].toString() :"");
                    outData.setCompanyId(item[6]!=null ? item[6].toString() :"");
                    outData.setCompanyNm(item[13]!=null ? item[13].toString() :"");
                    outData.setSecurityCd(item[14]!=null ? item[14].toString() :"");
                    String[] lable = new String[]{};
                    //将事件标签分割成String数组
                    if(item[12] !=null && !"".equals(item[12])){
                        String lableStr = item[12].toString();
                        lable =lableStr.split(",");
                    }else{
                        lable = null;
                    }
                    outData.setLable(lable);
                    reslist.add(outData);
                }
                map.put("content", reslist);
                out.setCode("0");
                out.setData(map);
                out.setCount(count);
            }else{
                out.setCode("1");
                out.setMessage("新闻舆情风险，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("新闻舆情风险，获取异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }


        return out;
    }

    /**
     * 公司公告
     * @return
     */
    @RequestMapping(value = "/companyAnnounce", method = RequestMethod.POST)
    public BaseOutData getCompanyAnnounce(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();
        Map<String,List<AnncounceEventInfoData>> data = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Object> outList = warningAnnounceService.getSingleCompanyAE(null, inData.getCompanyId(),
                inData.getDateStart(), inData.getDateEnd(), inData.getPage(),inData.getPageSize());

        if (outList.size() == 0 || outList ==null) {
            outData.setCode("1");
            outData.setMessage("Query Faile");
            return outData;
        }
        //对数据遍历
        List<AnncounceEventInfoData> infoList = new ArrayList<AnncounceEventInfoData>();
        for (int i = 0; i < outList.size(); i++) {
            AnncounceEventInfoData infoData = new AnncounceEventInfoData();
            Object[] info = (Object[]) outList.get(i);
            infoData.setSecurityCd(info[0] != null ? info[0].toString() : "");
            infoData.setCompanyId(info[1] != null ? info[1].toString() : "");
            infoData.setCompanySnm(info[2] != null ? info[2].toString() : "");
            infoData.setNoticeDt(info[3] != null ? sdf.format(info[3]) : "");
            infoData.setSrcType(info[4] != null ? info[4].toString() : "");
            infoData.setInfoCd(info[5] != null ? info[5].toString() : "");
            infoData.setNoticeTitle(info[6] != null ? info[6].toString() : "");
            infoData.setCompanyNm(info[7] != null ? info[7].toString() : "");
            String[] typeAll = info[8] != null ? info[8].toString().split(",") : null;
            infoData.setType(typeAll[0] != null ? StringUtils.substringBefore(typeAll[0] ,"-") : "");
            infoData.setTypeSecond(typeAll.length > 1 && typeAll[1] != null ? StringUtils.substringBefore(typeAll[1] ,"-") : "");
            infoData.setTypeThree(typeAll.length > 2 && typeAll[2] != null ? StringUtils.substringBefore(typeAll[2] ,"-") : "");
            infoList.add(infoData);
        }

        //获取公告总数
        List<Object> countList = warningAnnounceService.getSCAECount(null, inData.getCompanyId(),
                inData.getDateStart(), inData.getDateEnd());

        BigDecimal bd = (BigDecimal) countList.get(0);
        int count = bd.intValue();

        data.put("AnncounceEventDataList", infoList);
        outData.setCode("0");
        outData.setCount(count);
        outData.setMessage("The query is successful!");
        outData.setData(data);
        logger.info("[查询成功]" + outData);
        return outData;
    }

    /**
     * 公司公告详情
     * @return
     */
    @RequestMapping(value = "/announceDetail/{infoCd}", method = RequestMethod.GET)
    public BaseOutData getAllEventRisk(@PathVariable String infoCd) {
        BaseOutData outData = new BaseOutData();
        Map<String, String> data = new HashMap<>();

        String url = warningAnnounceService.getAnnoumceUrl(infoCd);
        if (url == null || url == "") {
            outData.setMessage("Query fail!");
            outData.setCode("1");
            return outData;
        }

        data.put("url", url);
        outData.setMessage("Query Success!");
        outData.setCode("0");
        outData.setData(data);

        return outData;
    }

    //股东明细
    @RequestMapping(value = "/shareholder/{id}", method = RequestMethod.GET)
    public BaseOutData getShareholderTop10(@PathVariable Long id)
            throws JsonProcessingException {
        BaseOutData out = new BaseOutData();
        CompanyShareholderOutData outData = new CompanyShareholderOutData();
        List<CompanyShareholderInfoData> companyShareholderList = new LinkedList<>();
        DecimalFormat df = new DecimalFormat(",###,##0");
        try {
            List<Object> compyShareholderList = companyInfoServices.findShareholderTop10(id);
            for (int i = 0; i < compyShareholderList.size(); i++) {
                CompanyShareholderInfoData shareholderInfo = new CompanyShareholderInfoData();
                Object[] arrayInfo = (Object[]) compyShareholderList.get(i);
                shareholderInfo.setSharehdname(arrayInfo[0] == null ? "" : arrayInfo[0].toString());
                shareholderInfo.setSharehdType(arrayInfo[1] == null ? "" : arrayInfo[1].toString());
                shareholderInfo.setSharehdRatio(arrayInfo[2] == null ? "" : arrayInfo[2].toString());
                shareholderInfo.setFrozenTotalRatio(arrayInfo[3] == null ? "" : arrayInfo[3].toString());
                shareholderInfo.setSharehdId(arrayInfo[5] == null ? "" : arrayInfo[5].toString());
                String sharedNum ="";
                if(arrayInfo[6] != null && !"".equals(arrayInfo[6])){
                    sharedNum = df.format(arrayInfo[6]);
                }
                shareholderInfo.setSharehdNum(sharedNum);
                companyShareholderList.add(shareholderInfo);
            }

            compyShareholderList = companyInfoServices.findCompyFrozemshare(id);
            for (int i = 0; i < compyShareholderList.size(); i++) {
                CompanyShareholderInfoData shareholderInfo = new CompanyShareholderInfoData();
                Object[] arrayInfo = (Object[]) compyShareholderList.get(i);
                shareholderInfo.setSharehdname(arrayInfo[0] == null ? "" : arrayInfo[0].toString());
                shareholderInfo.setSharehdType("");
                shareholderInfo.setSharehdRatio(arrayInfo[1] == null ? "" : arrayInfo[1].toString());
                shareholderInfo.setFrozenTotalRatio(arrayInfo[2] == null ? "" : arrayInfo[2].toString());
                shareholderInfo.setSharehdId(arrayInfo[3] == null ? "" : arrayInfo[3].toString());
                companyShareholderList.add(shareholderInfo);
            }
            outData.setCompanyShareholderList(companyShareholderList);

            if (outData != null && !"".equals(outData)) {
                out.setCode("0");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",outData);
                out.setData(map);
            }else {
                out.setCode("1");
                out.setMessage("股东明细，获取数据为空");
                logger.info("股东明细，获取数据为空");
            }

        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("股东明细，获取数据异常，异常信息："+e.getMessage());
            logger.info("股东明细，获取数据异常，异常信息："+e.getMessage());
        }
        return out;
    }
    /**
     * 获取风险事件公告count数
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/getRiskWarningCountByEvent/{companyId}/{dateStart}/{dateEnd}", method = RequestMethod.GET)
    public BaseOutData getRiskWarningCountByEvent(@PathVariable Long companyId, @PathVariable String dateStart, @PathVariable String dateEnd){
        BaseOutData outData = new BaseOutData();
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        countMap.put("caiWu",0);
        countMap.put("zhiLi",0);
        countMap.put("jingYin",0);
        countMap.put("shiChang",0);
        countMap.put("faLv",0);
        //获取新闻事件的count数
        List<Object> newsEvent =  warningAnnounceService.getCompyWarningCountfornews(companyId,dateStart,dateEnd );
        if(newsEvent!=null && newsEvent.size()>0 ){
            Object[] item = (Object[]) newsEvent.get(0);
            //累计新闻事件的财务风险数
            int num = countMap.get("caiWu")+ (item[0] !=null ? Integer.parseInt(item[0].toString()):0);
            countMap.put("caiWu",num);
            //累计新闻事件的治理风险数
            int num1 = countMap.get("zhiLi")+ (item[1] !=null ? Integer.parseInt(item[1].toString()):0);
            countMap.put("zhiLi",num1);
            //累计新闻事件的经营风险数
            int num2 = countMap.get("jingYin")+ (item[2] !=null ? Integer.parseInt(item[2].toString()):0);
            countMap.put("jingYin",num2);
            //累计新闻事件的市场风险数
            int num3 = countMap.get("shiChang")+ (item[3] !=null ? Integer.parseInt(item[3].toString()):0);
            countMap.put("shiChang",num3);
            //累计新闻事件的法律法规风险数
            int num4 = countMap.get("faLv")+ (item[4] !=null ? Integer.parseInt(item[4].toString()):0);
            countMap.put("faLv",num4);
        }
        //获取公告事件的count数
        List<Object> warningEvent =  warningAnnounceService.getCompyWarningCount(companyId,dateStart,dateEnd);
        //公告的统计数不为空，则累计各个事件的统计数
        if(warningEvent!=null && warningEvent.size()>0){
            Object[] item = (Object[]) warningEvent.get(0);
            //累计公告事件的财务风险数
            int num = countMap.get("caiWu")+  (item[0] !=null ? Integer.parseInt(item[0].toString()):0);
            countMap.put("caiWu",num);
            //累计公告事件的治理风险数
            int num1 = countMap.get("zhiLi")+ (item[1] !=null ? Integer.parseInt(item[1].toString()):0);
            countMap.put("zhiLi",num1);
            //累计公告事件的经营风险数
            int num2 = countMap.get("jingYin")+ (item[2] !=null ? Integer.parseInt(item[2].toString()):0);
            countMap.put("jingYin",num2);
            //累计公告事件的市场风险数
            int num3 = countMap.get("shiChang")+ (item[3] !=null ? Integer.parseInt(item[3].toString()):0);
            countMap.put("shiChang",num3);
            //累计公告事件的法律法规风险数
            int num4 = countMap.get("faLv")+ (item[4] !=null ? Integer.parseInt(item[4].toString()):0);
            countMap.put("faLv",num4);
        }
        if(countMap !=null && countMap.size()>0){
            outData.setCode("0");
            outData.setData(countMap);
        }else{
            outData.setCode("1");
            outData.setMessage("风险事件公告count数，获取数据为空");
            logger.info("风险事件公告count数，获取数据为空");
        }
        return outData;
    }

}
