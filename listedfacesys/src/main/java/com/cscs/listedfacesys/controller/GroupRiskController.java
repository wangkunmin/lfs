package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.busi.AnnounceBusiService;
import com.cscs.listedfacesys.busi.NewsClassBusiService;
import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.DateUtils;
import com.cscs.util.SqlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by wzy on 2018/2/1
 * 分组风险总览
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/groupRisk")
public class GroupRiskController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NewsClassesService newsClassService;
    @Autowired
    WarningAnnounceService warningAnnounceService;
    @Autowired
    UserAttentionService userAttentionService;

    /**
     * 舆情新闻风险趋势图
     * @return
     */
    @RequestMapping(value = "/consensusChart", method = RequestMethod.POST)
    public BaseOutData getConsensusChart(@RequestBody TendencyChartInData inData) {
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
            itemList = newsClassService.findchartForUserAttention(inData);
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
                out.setMessage("舆情新闻风险趋势图，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("舆情新闻风险趋势图，获取数据异常！异常信息："+e.getMessage());
            logger.error("舆情新闻风险趋势图，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }



    /**
     * 风险TOP10
     * @return
     */
    @RequestMapping(value = "/userWarningTop", method = RequestMethod.POST)
    public BaseOutData getWarningTop10(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();

        List<WarningInfoData> warningInfoList = new ArrayList<>();

        if (inData.getYear() != null && inData.getYear() != "") {
            inData.setDateStart(inData.getYear());
            inData.setDateEnd(inData.getYear());
        }

        String idList = "";

        //获取新闻公司ID列表
        List<Object> topfornews =warningAnnounceService.getUserWarningTop10fornews(inData.getUserId(), inData.getDateStart(), inData.getDateEnd());
        Map<String, Object[]> topfornewsMap = new LinkedHashMap<String, Object[]>();
        for (Object it : topfornews) {
            Object[] item = (Object[]) it;
            topfornewsMap.put(item[1].toString(),item);

        }
        List<Object> companyIdList;
        //获取公司ID列表
        companyIdList = warningAnnounceService.getUserWarningTop10(inData.getUserId(), inData.getDateStart(), inData.getDateEnd());
        if(companyIdList == null){
            companyIdList = new ArrayList<Object>();
        }

        //公司新闻数累计到公告数作为风险数
        for (Object it : companyIdList) {

            Object[] item = (Object[]) it;
            Object[] itemnews = (Object[])topfornewsMap.get(item[1].toString());
            if (itemnews !=null){
                item[0]=Integer.valueOf(item[0].toString())+ Integer.valueOf(itemnews[0].toString());
            }

        }
        for(String key : topfornewsMap.keySet()){
            boolean having = false;
            for (Object it : companyIdList) {
                Object[] item = (Object[]) it;
                if(key.equals(item[1].toString())){
                    having = true;
                    break;
                }
            }
            if(!having){
                companyIdList.add(topfornewsMap.get(key));
            }
        }

        if (companyIdList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The ID data is not queried!");
            logger.info("[未查询到关注公司ID数据]");
            return outData;
        }


        //公司的风险数排序
        Collections.sort(companyIdList, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Object[] item1 = (Object[]) o1;
                Object[] item2 = (Object[]) o2;
                if (o1 != null && o2 != null) {
                    /*对比中标数量排序，若标数相同，则根据公司id排序*/
                    int num1 = Integer.valueOf(item2[0].toString()) - Integer.valueOf(item1[0].toString());

                    return num1;
                }
                return 0;
            }
        });
        //分页
        companyIdList = SqlUtils.pagingMethod3(inData.getPage(), inData.getPageSize(),companyIdList);
        for (Object it : companyIdList) {
            Object[] focusItem = (Object[]) it;
            idList += focusItem[1].toString() + ",";
        }

        idList = idList.substring(0, idList.length() - 1);

        List<Object> contentList;
        //根据id列表获取公告详情
        contentList = warningAnnounceService.getUWTop10Content(inData.getUserId(), idList, inData.getDateStart(), inData.getDateEnd());
        if(contentList == null){
            contentList  = new ArrayList<Object>();
        }
        //根据id列表获取新闻详情
        List<Object> contentListnews = warningAnnounceService.getUWTop10Contentfornews(inData.getUserId(), idList, inData.getDateStart(), inData.getDateEnd());

        for (Object it : contentListnews) {
            Object[] item = (Object[]) it;
            contentList.add(item);
        }

        if (contentList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The Announce data is not queried!");
            logger.info("[未查询到风险详情数据]");
            return outData;
        }
        //如果预警top10则不需要取得预警总条数
        int count =companyIdList.size() ;

        Set<String> setList = new HashSet<>();
        for (Object it : contentList) {
            Object[] item = (Object[]) it;
            String companyId = item[0].toString();  //公司id
            setList.add(companyId);
        }

        /*对获取到的数据进行遍历，并添加三种快捷小标题*/
        warningInfoList = AnnounceBusiService.getWarningInfoData(contentList, setList);
        warningInfoList = AnnounceBusiService.cutTypeName(warningInfoList);

        if (warningInfoList != null){
            Map<String, List<WarningInfoData>> data = new HashMap<>();
            data.put("warningDataList",warningInfoList);
            outData.setData(data);
            outData.setCode("0");
            outData.setCount(count);
            outData.setMessage("The query is successful!");
            logger.info("[查询成功]"+warningInfoList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[风险数据处理异常]");
        }
        return outData;
    }

    /**
     * 新闻舆情风险
     * @return
     */
    @RequestMapping(value = "/newsConsensus", method = RequestMethod.POST)
    public BaseOutData getNewsConsensus(@RequestBody NegativeNewsInData inData) {
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        List<CompanyNewsOutData> reslist = new ArrayList<CompanyNewsOutData>();
        Map<String, List<CompanyNewsOutData>> map = new HashMap<String, List<CompanyNewsOutData>>();
        try {
            //查询新闻舆情风险总数
            int count =  newsClassService.getNewsRiskForUserAttentionCount(inData.getDateStart(),inData.getDateEnd(),inData.getUserId());
            itemList =  newsClassService.getNewsRiskForUserAttention(inData.getPage(), inData.getPageSize(),inData.getDateStart(),inData.getDateEnd(),inData.getUserId());
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
     * 事件公告风险
     * @return
     */
    @RequestMapping(value = "/eventRisk", method = RequestMethod.POST)
    public BaseOutData getEventRisk(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();

        /*查询公告和新闻数据*/
        List<Object> outList = warningAnnounceService.getAccounceEvent(inData.getUserId(),
                inData.getDateStart(), inData.getDateEnd(),inData.getPage(),inData.getPageSize(),inData.getRiskEventType(),inData.getNegativeType(),inData.getImportanceType());

        if (outList.size() == 0 || outList ==null) {
            outData.setCode("1");
            outData.setMessage("[查询失败]未查询到数据");
            return outData;
        }

        List<AnncounceEventInfoData> infoList = new ArrayList<AnncounceEventInfoData>();
        for (int i = 0; i < outList.size(); i++) {
            AnncounceEventInfoData infoData = new AnncounceEventInfoData();
            Object[] info = (Object[]) outList.get(i);
            infoData.setSecurityCd(info[0] != null ? info[0].toString() : "");
            infoData.setCompanyId(info[1] != null ? info[1].toString() : "");
            infoData.setCompanySnm(info[2] != null ? info[2].toString() : "");
            infoData.setNoticeDt(info[3] != null ? info[3].toString() : "");
            infoData.setSrcType(info[4] != null ? info[4].toString() : "");
            infoData.setType(info[5] != null ? info[5].toString() : "");
            infoData.setInfoCd(info[6] != null ? info[6].toString() : "");
            infoData.setNoticeTitle(info[7] != null ? info[7].toString() : "");
            infoData.setCompanyNm(info[8] != null ? info[8].toString() : "");
            infoList.add(infoData);
        }

        /*获取事件总条数*/
        List<Object> countList = warningAnnounceService.getAECount(inData.getUserId(), inData.getDateStart(), inData.getDateEnd(),inData.getRiskEventType(),inData.getNegativeType(),inData.getImportanceType());

        BigDecimal bd = (BigDecimal) countList.get(0);
        int count = bd.intValue();

        Map<String, List<AnncounceEventInfoData>> data = new HashMap<>();
        data.put("AnncounceEventDataList", infoList);

        outData.setCount(count);
        outData.setCode("0");
        outData.setMessage("Quert Success!");
        outData.setData(data);
        return outData;
    }

    /**
     * 事件公告风险单个公司全部
     * @return
     */
    @RequestMapping(value = "/singleEventRisk", method = RequestMethod.POST)
    public BaseOutData getSingleEventRisk(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();

        String dateStart = inData.getDateStart();
        String dateEnd = inData.getDateEnd();

        /*获取当前公司的全部事件*/
        List<Object> outList = warningAnnounceService.getSingleCompanyAE(inData.getUserId(), inData.getCompanyId(), dateStart, dateEnd, inData.getPage(),inData.getPageSize());

        if (outList.size() == 0 || outList ==null) {
            outData.setCode("1");
            outData.setMessage("Query Faile");
            return outData;
        }

        List<AnncounceEventInfoData> infoList = new ArrayList<AnncounceEventInfoData>();
        for (int i = 0; i < outList.size(); i++) {
            AnncounceEventInfoData infoData = new AnncounceEventInfoData();
            Object[] info = (Object[]) outList.get(i);
            infoData.setSecurityCd(info[0] != null ? info[0].toString() : "");
            infoData.setCompanyId(info[1] != null ? info[1].toString() : "");
            infoData.setCompanySnm(info[2] != null ? info[2].toString() : "");
            infoData.setNoticeDt(info[3] != null ? info[3].toString() : "");
            infoData.setSrcType(info[4] != null ? info[4].toString() : "");
            infoData.setInfoCd(info[5] != null ? info[5].toString() : "");
            infoData.setNoticeTitle(info[6] != null ? info[6].toString() : "");
            infoData.setType(info[7] != null ? info[7].toString() : "");
            infoData.setCompanyNm(info[8] != null ? info[8].toString() : "");
            infoList.add(infoData);
        }

        /*获取当前公司的事件总条数*/
        List<Object> countList = warningAnnounceService.getSCAECount(inData.getUserId(), inData.getCompanyId(), dateStart, dateEnd);

        BigDecimal bd = (BigDecimal) countList.get(0);
        int count = bd.intValue();

        Map<String, List<AnncounceEventInfoData>> data = new HashMap<>();
        data.put("AnncounceEventDataList", infoList);

        outData.setCount(count);
        outData.setCode("0");
        outData.setMessage("Quert Success!");
        outData.setData(data);
        return outData;
    }

    /**
     * 事件公告风险详情
     * @return
     */
    @RequestMapping(value = "/eventRiskDetail/{infoCd}", method = RequestMethod.GET)
    public BaseOutData getAllEventRisk(@PathVariable String infoCd) {
        BaseOutData outData = new BaseOutData();
        Map<String, String> data = new HashMap<>();

        String url = warningAnnounceService.getAnnoumceUrl(infoCd);
        if (url == null || url == "") {
            outData.setCode("1");
            outData.setMessage("Query fail!");
            return outData;
        }

        data.put("url", url);
        outData.setMessage("Query Success!");
        outData.setCode("0");
        outData.setData(data);

        return outData;
    }

    //风险预警top快捷查询
    @RequestMapping(value = "/getShortCutWarningTop", method = RequestMethod.POST)
    public BaseOutData getShortCutWarningTop10(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();
        List<WarningInfoData> warningInfoList = new ArrayList<>();
        String startDate ="";
        switch (inData.getTimeType()){
            case 1: startDate = DateUtils.getDateDiff2(-1);
                    break;
            case 2: startDate = DateUtils.getDateDiff2(-7);
                    break;
            case 3: startDate = DateUtils.getDateDiff2(-30);
                    break;
        }
        String endDate = DateUtils.getCurrentDate2();
        String idList = "";

        //获取新闻公司ID列表
       List<Object> topfornews =warningAnnounceService.getUserWarningTop10fornews(inData.getUserId(), startDate, endDate);
        Map<String, Object[]> topfornewsMap = new LinkedHashMap<String, Object[]>();
        for (Object it : topfornews) {
            Object[] item = (Object[]) it;
            topfornewsMap.put(item[1].toString(),item);

        }

        List<Object> companyIdList;
        //获取公司ID列表
        companyIdList = warningAnnounceService.getUserWarningTop10(inData.getUserId(), startDate, endDate);
        if(companyIdList == null){
            companyIdList = new ArrayList<Object>();
        }

        //公司新闻数累计到公告数作为风险数
        for (Object it : companyIdList) {

            Object[] item = (Object[]) it;
            Object[] itemnews = (Object[])topfornewsMap.get(item[1].toString());
            if (itemnews !=null){
                item[0]=Integer.valueOf(item[0].toString())+ Integer.valueOf(itemnews[0].toString());
            }

        }
        for(String key : topfornewsMap.keySet()){
            boolean having = false;
            for (Object it : companyIdList) {
                Object[] item = (Object[]) it;
                if(key.equals(item[1].toString())){
                    having = true;
                    break;
                }
            }
            if(!having){
                companyIdList.add(topfornewsMap.get(key));
            }
        }

        if (companyIdList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The ID data is not queried!");
            logger.info("[未查询到关注公司ID数据]");
            return outData;
        }


        //公司的风险数排序
        Collections.sort(companyIdList, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Object[] item1 = (Object[]) o1;
                Object[] item2 = (Object[]) o2;
                if (o1 != null && o2 != null) {
                    /*对比中标数量排序，若标数相同，则根据公司id排序*/
                    int num1 = Integer.valueOf(item2[0].toString()) - Integer.valueOf(item1[0].toString());

                    return num1;
                }
                return 0;
            }
        });
        //分页
        companyIdList = SqlUtils.pagingMethod3(inData.getPage(), inData.getPageSize(),companyIdList);
        for (Object it : companyIdList) {
            Object[] focusItem = (Object[]) it;
            idList += focusItem[1].toString() + ",";
        }

        idList = idList.substring(0, idList.length() - 1);

        List<Object> contentList;
        //根据id列表获取公告详情
        contentList = warningAnnounceService.getUWTop10Content(inData.getUserId(), idList, startDate, endDate);
        if(contentList ==null){
            contentList = new ArrayList<Object>();
        }
        //根据id列表获取新闻详情
        List<Object> contentListnews = warningAnnounceService.getUWTop10Contentfornews(inData.getUserId(), idList, startDate, endDate);

        for (Object it : contentListnews) {
            Object[] item = (Object[]) it;
            contentList.add(item);
        }

        if (contentList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The Announce data is not queried!");
            logger.info("[未查询到风险详情数据]");
            return outData;
        }
        //如果预警top10则不需要取得预警总条数
        int count =companyIdList.size() ;

        Set<String> setList = new HashSet<>();
        for (Object it : contentList) {
            Object[] item = (Object[]) it;
            String companyId = item[0].toString();  //公司id
            setList.add(companyId);
        }

        /*对获取到的数据进行遍历，并添加三种快捷小标题*/
        warningInfoList = AnnounceBusiService.getWarningInfoData(contentList, setList);
        warningInfoList = AnnounceBusiService.cutTypeName(warningInfoList);

        if (warningInfoList != null){
            Map<String, List<WarningInfoData>> data = new HashMap<>();
            data.put("warningDataList",warningInfoList);
            outData.setData(data);
            outData.setCode("0");
            outData.setCount(count);
            outData.setMessage("The query is successful!");
            logger.info("[查询成功]"+warningInfoList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[风险数据处理异常]");
        }
        return outData;
    }
}
