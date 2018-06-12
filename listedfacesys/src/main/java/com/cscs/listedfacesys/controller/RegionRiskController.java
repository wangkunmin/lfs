package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.busi.AnnounceBusiService;
import com.cscs.listedfacesys.busi.MapBusiService;
import com.cscs.listedfacesys.busi.NewsClassBusiService;
import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.MapRegionService;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.DateUtils;
import com.cscs.util.SqlUtils;
import com.cscs.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.cscs.listedfacesys.busi.MapBusiService.getNegativeRiskCount;
import static com.cscs.listedfacesys.busi.MapBusiService.getRiskDimensionCount;

/**
 * Create by wzy on 2018/2/1
 * 区域风险总览
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/regionRisk")
public class RegionRiskController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NewsClassesService newsClassService;
    @Autowired
    WarningAnnounceService warningAnnounceService;
    @Autowired
    UserAttentionService userAttentionService;
    @Autowired
    MapRegionService mapRegionService;



    //上市公司预警趋势图
    @RequestMapping(value = "/warningChart/{dateStart}/{dateEnd}", method = RequestMethod.GET)
    public BaseOutData getWarningChart(@PathVariable String dateStart, @PathVariable String dateEnd) {
        BaseOutData outData = new BaseOutData();
        Map<String, List<WarningRiskOutData>> data = new HashMap<>();
        List<WarningRiskOutData> warningRiskList = new ArrayList<>();

        int range = Integer.valueOf(dateEnd.substring(0, 4)) - Integer.valueOf(dateStart.substring(0, 4)) + 1;

        dateEnd = String.valueOf(Integer.valueOf(dateEnd) + 1);
        //获取新闻五种风险按日期合计数据
        List<Object> sevYearDataListnews = warningAnnounceService.getWarningYearCountfornews(dateStart, dateEnd);
        Map<String, Object> topfornewsMap = new LinkedHashMap<String, Object>();
        for (Object it : sevYearDataListnews) {
            Object[] item = (Object[]) it;
            topfornewsMap.put(item[0].toString(),item);
        }
        List<Object> sevYearDataList;
        //获取公告五种风险按日期合计数据
        sevYearDataList = warningAnnounceService.getWarningYearCount(dateStart, dateEnd);
        if(sevYearDataList == null){
            sevYearDataList = new ArrayList<Object>();
        }
        //公司新闻数累计到公告数作为风险数
        for (Object it : sevYearDataList) {
            Object[] item = (Object[]) it;
            Object[] itemnews = (Object[])topfornewsMap.get(item[0].toString());
            if (itemnews !=null){
                item[1]=Integer.valueOf(item[1].toString())+ Integer.valueOf(itemnews[1].toString());
                item[2]=Integer.valueOf(item[2].toString())+ Integer.valueOf(itemnews[2].toString());
                item[3]=Integer.valueOf(item[3].toString())+ Integer.valueOf(itemnews[3].toString());
                item[4]=Integer.valueOf(item[4].toString())+ Integer.valueOf(itemnews[4].toString());
                item[5]=Integer.valueOf(item[5].toString())+ Integer.valueOf(itemnews[5].toString());
            }

        }

        for (String key :topfornewsMap.keySet()) {

            boolean having = false;
            for (Object it : sevYearDataList) {
                Object[] item = (Object[]) it;
                if(key.equals(item[0].toString())){
                    having = true;
                    break;
                }
            }
            if(!having){
                sevYearDataList.add(topfornewsMap.get(key));
            }
        }

        if (sevYearDataList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The query fails!");
            logger.info("[未查询到风险数据信息]");
            return outData;
        }
        //对年数据遍历排序
        warningRiskList = AnnounceBusiService.convert(sevYearDataList, dateStart, dateEnd, range);

        if (warningRiskList != null) {
            data.put("warningRiskOutDataList", warningRiskList);
            outData.setCode("0");
            outData.setMessage("The query is successful!");
            outData.setData(data);
            logger.info("[查询成功]"+warningRiskList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[公告数据处理异常]");
        }
        return outData;
    }

    //查询预警趋势TOP10公司信息
    @RequestMapping(value = "/warningTop", method = RequestMethod.POST)
    public BaseOutData getWarningTop10(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();

        List<WarningInfoData> warningInfoList = new ArrayList<>();

        String idList = "";

        //获取新闻公司ID列表
        List<Object> topfornews =warningAnnounceService.getWarningTopfornews(inData.getDateStart(), inData.getDateEnd());
        Map<String, Object[]> topfornewsMap = new LinkedHashMap<String, Object[]>();
        for (Object it : topfornews) {
            Object[] item = (Object[]) it;
            topfornewsMap.put(item[1].toString(),item);

        }
        List<Object> companyIdList;
        //获取公告公司ID列表
        companyIdList = warningAnnounceService.getWarningTop10(inData.getDateStart(), inData.getDateEnd());
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
            logger.info("[未查询到公司ID数据]");
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
        //根据id列表获取公告详情
        List<Object> contentList;
        contentList = warningAnnounceService.getWarningTop10Content(idList, inData.getDateStart(), inData.getDateEnd());
        if(contentList ==null){
            contentList = new ArrayList<Object>();
        }
        //根据id列表获取新闻详情
        List<Object> contentListnews = warningAnnounceService.getWarningTop10Contentfornews(idList, inData.getDateStart(), inData.getDateEnd());
        for (Object it : contentListnews) {
            Object[] item = (Object[]) it;
            contentList.add(item);
        }
        if (contentList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The Announce data is not queried!");
            logger.info("[未查询到公告数据]");
            return outData;
        }
        //如果预警top10则不需要取得预警总条数
        int count = companyIdList.size();

        List<Object> nameList = warningAnnounceService.getSupervisor(idList);
        if (nameList == null) {
            outData.setCode("1");
            outData.setMessage("The Count data is not queried!");
            logger.info("[未查询到监管人员数据]");
        }
        logger.info("监管人员：" + nameList);

        //查询是否关注
        Set<String> focusIds = userAttentionService.searchAllCompy(inData.getUserId());

        //对获取到的数据进行遍历
        warningInfoList = AnnounceBusiService.getWarningInfoData(contentList, focusIds);
        //添加监管人员姓名
        warningInfoList = AnnounceBusiService.addSupervisor(warningInfoList, nameList);

        if (warningInfoList != null){
            Map<String, List<WarningInfoData>> data = new HashMap<>();
            data.put("warningDataList",warningInfoList);
            outData.setData(data);
            outData.setCount(count);
            outData.setCode("0");
            outData.setMessage("The query is successful!");
            logger.info("[查询成功]"+warningInfoList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[公告数据处理异常]");
        }

        return outData;
    }

    //热点新闻趋势图(组合)
    @RequestMapping(value = "/newsChart", method = RequestMethod.POST)
    public BaseOutData getNewsChart(@RequestBody TendencyChartInData inData) {
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
            itemList = newsClassService.findchart(inData);

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
                        //按年月对数据进行分组
                        Object[] item = (Object[]) itemList.get(i);
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
                        outData.setTotalRatio(String.format("%.2f", (double) outData.getNegativeTotalCount() / outData.getTotalCount() * 100) + "%");
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
                out.setMessage("热点新闻，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("热点新闻，获取数据异常！异常信息："+e.getMessage());
            logger.error("热点新闻，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;

    }




    //负面新闻跟踪
    @RequestMapping(value = "/lastingBondViolation", method = RequestMethod.POST)
    public BaseOutData getViolation(@RequestBody NegativeNewsInData inData) {
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        List<CompanyNewsOutData> reslist = new ArrayList<CompanyNewsOutData>();
        Map<String, List<CompanyNewsOutData>> map = new HashMap<String, List<CompanyNewsOutData>>();
        try {
            //查询负面新闻总数
            int count =  newsClassService.getLastingBondViolationNewsCount(inData.getDateStart(),inData.getDateEnd());
            itemList =  newsClassService.getLastingBondViolationNews(inData.getPage(), inData.getPageSize(),inData.getDateStart(),inData.getDateEnd());
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
                   outData.setCompanyNm(item[12]!=null ? item[12].toString() :"");
                   outData.setSecurityCd(item[13]!=null ? item[13].toString() :"");
                   reslist.add(outData);
               }
                map.put("content", reslist);
                out.setCode("0");
                out.setData(map);
                out.setCount(count);
            }else{
                out.setCode("1");
                out.setMessage("负面新闻跟踪，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("负面新闻跟踪，获取异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }


        return out;
    }


    //负面新闻详情
    @RequestMapping(value = "/newsContent/{compyId}/{newsCode}", method = RequestMethod.GET)
    public BaseOutData getNewsContent(@PathVariable long compyId, @PathVariable String newsCode) {
        BaseOutData out = new BaseOutData();
        NewsWarningOutData outData = new NewsWarningOutData();
        List<Object> itemList = null;
        try {
            itemList = newsClassService.findNewsContent(compyId, newsCode);
            if (itemList.size() > 0) {
                Object[] item = (Object[]) itemList.get(0);
                outData.setWarningType(item[0] != null ? item[0].toString() : "");
                outData.setNewsTitle(item[1] != null ? item[1].toString() : "");
                outData.setPublishTime(item[3] != null ? item[3].toString() : "");
                outData.setNewsSource(item[4] != null ? item[4].toString() : "");
                outData.setRelevance(item[5] != null ? item[5].toString() : "");
                outData.setScore(item[6] != null ? item[6].toString() : "");
                outData.setContent(item[7] != null ? StringUtil.toString(item[7]) : "");
                outData.setImportance(item[8] != null ? item[8].toString() : "");

                Map sheetLabel = new HashMap();
                List<Object> labelList = newsClassService.findNewsType(compyId, newsCode);
                if(labelList!=null && labelList.size()>0){
                    for (int i = 0; i < labelList.size(); i++) {
                        Object[] label = (Object[]) labelList.get(i);
                        sheetLabel.put(label[0], label[1]);
                    }
                }else{
                    out.setCode("1");
                    out.setMessage("获取负面跟踪新闻事件信息，获取为空");
                }

                outData.setSheetLabel(sheetLabel);
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",outData);
                out.setCode("0");
                out.setData(resMap);
            }else{
                out.setCode("1");
                out.setMessage("负面跟踪新闻详细信息，获取为空");
            }
        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("负面跟踪新闻详细信息，获取异常,异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }


    //预警风险分布一览查询
    @RequestMapping(value = "/riskDistributeMap", method = RequestMethod.GET)
    public BaseOutData riskDistributeMap() {
        BaseOutData out  = new BaseOutData();
        //获取深圳所有区的信息
        List<RegionMapOutData> regionList = MapBusiService.getRegions();
        List<Object> result = warningAnnounceService.getCompyRiskCount();
        //获取两百多家深圳上市公司信息
        List<Object> resultList =  mapRegionService.getRegionCompanyInfo();
        if(resultList !=null && resultList.size()>0){
            for (Object item: resultList) {
                Object[] obj = (Object[]) item;
                //公司信息里的区名
                String compyRegionName = obj[8] !=null ? obj[8].toString() : "";
                String compyId = obj[0] !=null ? obj[0].toString() : "";
                //按区名进行分区
                for (RegionMapOutData region: regionList) {
                    if(region.getRegionName().equals(compyRegionName)){
                        for (Object items: result) {
                            Object[] obje = (Object[]) items;
                            String companyId = obje[0] !=null ? obje[0].toString() : "";
                            if(compyId.equals(companyId)){
                                CompanyInfoMapOutData companyInfo = new CompanyInfoMapOutData();
                                companyInfo.setCompanyId(obj[0] !=null ? obj[0].toString() : "");
                                companyInfo.setCompanyNm(obj[1] !=null ? obj[1].toString() : "");
                                companyInfo.setCompanySnm(obj[2] !=null ? obj[2].toString() : "");
                                companyInfo.setRegAddr(obj[3] !=null ? obj[3].toString() : "");
                                float[] posions = new float[2];
                                posions[0] =obj[4] !=null ? Float.parseFloat(obj[4].toString()) : 0f;
                                posions[1] =obj[5] !=null ? Float.parseFloat(obj[5].toString()) : 0f;
                                companyInfo.setPostion(posions);
                                companyInfo.setSecurityCd(obj[6] !=null ? obj[6].toString() : "");
                                companyInfo.setRating(obj[7] !=null ? obj[7].toString() : "");
                                companyInfo.setRegionName(compyRegionName);
                                companyInfo.setRiskContent(obj[9] !=null ? obj[9].toString() : "");
                                companyInfo.setRiskCaiWuCount(obje[1] !=null ? Integer.parseInt(obje[1].toString()) :0);
                                companyInfo.setRiskZhiLiCount(obje[2] !=null ? Integer.parseInt(obje[2].toString()) :0);
                                companyInfo.setRiskXinXiCount(obje[3] !=null ? Integer.parseInt(obje[3].toString()) :0);
                                companyInfo.setRiskQiTaCount(obje[4] !=null ? Integer.parseInt(obje[4].toString()) :0);
                                List<CompanyInfoMapOutData> conpanyInfos = region.getCompanyInfos();
                                if(conpanyInfos !=null && conpanyInfos.size()>0){
                                    conpanyInfos.add(companyInfo);
                                }else{
                                    List<CompanyInfoMapOutData> outList = new ArrayList<CompanyInfoMapOutData>();
                                    outList.add(companyInfo);
                                    region.setCompanyInfos(outList);
                                }
                                break;
                            }

                        }

                    }
                }

            }



            Map<String,Object> resMap = new HashMap<String,Object>();
            //获取深圳市所有上市公司的评级风险统计数
            Map<String,Object> shenZhen = MapBusiService.getRiskStatistics(regionList);
            resMap.put("content",shenZhen);
            out.setCode("0");
            out.setData(resMap);

        }else {
            out.setCode("1");
            out.setMessage("查询深圳所有区的上市公司信息，获取为空");
            logger.info("查询深圳所有区的上市公司信息，获取为空");
        }

        return out;
    }

    //负面事件分布一览查询
    @RequestMapping(value = "/negativeDistributeMap/{userId}/{eventDate}", method = RequestMethod.GET)
    public BaseOutData negativeDistributeMap(@PathVariable String userId,@PathVariable String eventDate) {
        BaseOutData out = new BaseOutData();

        List<WarningInfoData> warningInfoList = new ArrayList<>();

        List<Object> topfornews;
        //获取新闻公司ID列表
        topfornews =warningAnnounceService.getNewsNegativeContent(eventDate, eventDate);
        if(topfornews ==null){
            topfornews = new ArrayList<Object>();
        }
        //获取公告公司ID列表
        List<Object> companyIdList = warningAnnounceService.getNegativeBulletinBoardContent(eventDate,eventDate);
        for (Object it : companyIdList) {
            Object[] item = (Object[]) it;
            topfornews.add(item);
        }

        if (topfornews.size() == 0) {
            out.setCode("1");
            out.setMessage("The Announce data is not queried!");
            logger.info("[未查询到风险详情数据]");
            return out;
        }


        Set<String> setList = new HashSet<>();
        for (Object it : topfornews) {
            Object[] item = (Object[]) it;
            String companyId = item[0].toString();  //公司id
            setList.add(companyId);
        }

        /*对获取到的数据进行遍历，并添加三种快捷小标题*/
        warningInfoList = AnnounceBusiService.getWarningInfoDataForMap(topfornews, setList);

        //获取用户当日所查看的所有负面事件信息
       List<Object> NegativeInfoList = mapRegionService.getNegativeInfoByUserId(eventDate,userId);

        //获取深圳所有区的信息
        List<RegionMapOutData> regionList = MapBusiService.getRegions();
        //获取两百多家深圳上市公司信息
        List<Object> resultList =  mapRegionService.getRegionCompanyInfo();
        if(resultList !=null && resultList.size()>0){
            for (Object item: resultList) {
                Object[] obj = (Object[]) item;
                String companyId = obj[0] !=null ? obj[0].toString() : "";
                //公司信息里的区名
                String compyRegionName = obj[8] !=null ? obj[8].toString() : "";

                for (WarningInfoData info: warningInfoList) {
                    if(info.getCompanyId().equals(companyId)){
                        List<Map<String,String>> typeMap =  info.getTypeMap2();
                        //获取公司负面事件数
                        int negativeCount =  info.getWarnCount();
                        //按区名进行分区
                        for (RegionMapOutData region: regionList) {
                            if(region.getRegionName().equals(compyRegionName)){
                                CompanyInfoMapOutData companyInfo = new CompanyInfoMapOutData();
                                companyInfo.setCompanyId(companyId);
                                companyInfo.setCompanyNm(obj[1] !=null ? obj[1].toString() : "");
                                companyInfo.setCompanySnm(obj[2] !=null ? obj[2].toString() : "");
                                companyInfo.setRegAddr(obj[3] !=null ? obj[3].toString() : "");
                                float[] posions = new float[2];
                                posions[0] =obj[4] !=null ? Float.parseFloat(obj[4].toString()) : 0f;
                                posions[1] =obj[5] !=null ? Float.parseFloat(obj[5].toString()) : 0f;
                                companyInfo.setPostion(posions);
                                companyInfo.setSecurityCd(obj[6] !=null ? obj[6].toString() : "");
                                companyInfo.setRating(obj[7] !=null ? obj[7].toString() : "");
                                companyInfo.setRegionName(compyRegionName);
                                companyInfo.setNegativeCount(negativeCount);
                                companyInfo.setTypeMap(typeMap);
                                //将负面事件信息按新闻和公告分类，并将其主键Id拼接起来，用“，”隔开
                                StringBuffer announceCdBuffer= new StringBuffer();
                                StringBuffer newsCdBuffer =new StringBuffer();
                                for (Map<String,String> map :typeMap){
                                    String type = map.get("type")!=null ? map.get("type"):"";
                                    String eventId = map.get("eventId")!=null ? map.get("eventId"):"";
                                    if("1".equals(type)){
                                        announceCdBuffer.append(eventId+",");
                                    }else if("2".equals(type)){
                                        newsCdBuffer.append(eventId+",");
                                    }
                                }

                                    String announceCds = announceCdBuffer.toString();
                                if(announceCds !=null && !"".equals(announceCds)){
                                    announceCds = announceCds.substring(0,announceCds.length()-1);
                                    companyInfo.setAnnounceCds(announceCds);
                                }

                                   String newsCds = newsCdBuffer.toString();
                                if (newsCds !=null && !"".equals(newsCds)){
                                   newsCds = newsCds.substring(0,newsCds.length()-1);
                                   companyInfo.setNewsCds(newsCds);
                               }

                                //判断当前用户是否查看过该公司的负面事件信息
                                boolean isTwinkle = false;
                                //循环负面事件List
                                for (Map<String,String> map :typeMap){
                                    boolean isHaving = false;
                                    String type = map.get("type")!=null ? map.get("type"):"";
                                    String eventId = map.get("eventId")!=null ? map.get("eventId"):"";
                                    //循环记录是否查看过的负面事件信息集合
                                    for (Object items: NegativeInfoList) {
                                        Object[] obje = (Object[])items;
                                        String compyId = obje[0] !=null? obje[0].toString() :"";
                                        String eventCd = obje[2] !=null ? obje[2].toString(): "";
                                        String eventType = obje[4] !=null ? obje[4].toString(): "";
                                        //判断当前这家公司，是否被查看过
                                        if(companyId.equals(compyId)){
                                            //判断当前这个负面事件是否被查看过，未查看的，表示为新推送过来的数据，
                                            // 此公司坐标需闪烁
                                            if(eventId.equals(eventCd)&& eventType.equals(type)){
                                                isHaving = true;
                                                break;
                                            }
                                        }
                                    }
                                    if(!isHaving){
                                        isTwinkle = true;
                                        break;
                                    }
                                }
                                companyInfo.setTwinkle(isTwinkle);
                                List<CompanyInfoMapOutData> conpanyInfos = region.getCompanyInfos();
                                if(conpanyInfos !=null && conpanyInfos.size()>0){
                                    conpanyInfos.add(companyInfo);
                                }else{
                                    List<CompanyInfoMapOutData> outList = new ArrayList<CompanyInfoMapOutData>();
                                    outList.add(companyInfo);
                                    region.setCompanyInfos(outList);
                                }
                                break;

                            }
                        }
                        break;
                    }
                }


            }
            Map<String,Object> shenZhen = getNegativeRiskCount(regionList);
            Map<String,Object> resMap = new HashMap<String,Object>();
            resMap.put("content",shenZhen);
            out.setCode("0");
            out.setData(resMap);

        }else {
            out.setCode("1");
            out.setMessage("查询深圳所有区的上市公司信息，获取为空");
            logger.info("查询深圳所有区的上市公司信息，获取为空");
        }
        return out;
    }
    //记录查看负面事件信息
    @RequestMapping(value = "/negativeDistributeRecord", method = RequestMethod.POST)
    public BaseOutData negativeDistributeMap(@RequestBody NegativeInfoRecordInData inData) {
        BaseOutData outData = new BaseOutData();
        try{
         int status =  mapRegionService.insertNegativeInfoRecord(inData);
         if(status>0){
             outData.setCode("0");
         }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage("记录查看负面事件信息失败！");
            logger.info("记录查看负面事件信息失败！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //风险维度一览
    @RequestMapping(value = "/riskDimensionMap", method = RequestMethod.GET)
    public BaseOutData riskDimensionMap() {
        BaseOutData out = new BaseOutData();
        List<Object> compyRiskCount = warningAnnounceService.getCompyRiskCount();
        //获取两百多家深圳上市公司信息
        List<Object> riskList =  mapRegionService.getRegionCompanyInfo();
        if(riskList !=null && riskList.size()>0){

            //获取深圳所有区的信息
            List<RegionMapOutData> regionList = MapBusiService.getRegions();

            if(riskList !=null && riskList.size()>0){
                for (Object item: riskList) {
                    Object[] obj = (Object[]) item;
                    String companyId = obj[0] !=null ? obj[0].toString() : "";
                    //公司信息里的区名
                   String compyRegionName = obj[8] !=null ? obj[8].toString() : "";
                    //获取风险类型
                    //int riskType = obj[8] !=null ? Integer.parseInt(obj[8].toString()) : 0;
                    for (RegionMapOutData region: regionList) {
                        if(region.getRegionName().equals(compyRegionName)){
                            for (Object obj1: compyRiskCount) {
                                Object[] item1 = (Object[]) obj1;
                                String compyId = item1[0] !=null ? item1[0].toString() : "";
                                if(compyId.equals(companyId)){
                                    CompanyInfoMapOutData companyInfo = new CompanyInfoMapOutData();
                                    companyInfo.setCompanyId(companyId);
                                    companyInfo.setCompanyNm(obj[1] !=null ? obj[1].toString() : "");
                                    companyInfo.setCompanySnm(obj[2] !=null ? obj[2].toString() : "");
                                    companyInfo.setRegAddr(obj[3] !=null ? obj[3].toString() : "");
                                    float[] posions = new float[2];
                                    posions[0] =obj[4] !=null ? Float.parseFloat(obj[4].toString()) : 0f;
                                    posions[1] =obj[5] !=null ? Float.parseFloat(obj[5].toString()) : 0f;
                                    companyInfo.setPostion(posions);
                                    companyInfo.setSecurityCd(obj[6] !=null ? obj[6].toString() : "");
                                    companyInfo.setRiskCaiWuCount(item1[1] !=null ? Integer.parseInt(item1[1].toString()) :0);
                                    companyInfo.setRiskZhiLiCount(item1[2] !=null ? Integer.parseInt(item1[2].toString()) :0);
                                    companyInfo.setRiskXinXiCount(item1[3] !=null ? Integer.parseInt(item1[3].toString()) :0);
                                    companyInfo.setRiskQiTaCount(item1[4] !=null ? Integer.parseInt(item1[4].toString()) :0);
                                    List<CompanyInfoMapOutData> conpanyInfos = region.getCompanyInfos();
                                    if(conpanyInfos !=null && conpanyInfos.size()>0){
                                        conpanyInfos.add(companyInfo);
                                    }else{
                                        List<CompanyInfoMapOutData> outList = new ArrayList<CompanyInfoMapOutData>();
                                        outList.add(companyInfo);
                                        region.setCompanyInfos(outList);
                                    }
                                }
                            }

                        }
                    }

                }

                Map<String,Object> shenZhen = getRiskDimensionCount(regionList);
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",shenZhen);
                out.setCode("0");
                out.setData(resMap);

            }else {
                out.setCode("1");
                out.setMessage("查询深圳所有区的上市公司信息，获取为空");
                logger.info("查询深圳所有区的上市公司信息，获取为空");
            }
        }else{
            out.setCode("1");
            out.setMessage("查询企业风险信息，获取为空");
            logger.info("查询企业风险信息，获取为空");
        }

        return out;
    }

    //风险维度一览
    @RequestMapping(value = "/riskCompanyInfo/{companyId}", method = RequestMethod.GET)
    public BaseOutData riskCompanyInfo(@PathVariable String companyId) {
        BaseOutData out = new BaseOutData();
        List<Object> result = warningAnnounceService.getCompanyRiskById(companyId);
        CompanyInfoMapOutData info = new CompanyInfoMapOutData();
        List<Integer> riskTypes = new ArrayList<Integer>();
        List<Map<String,String>> riskContents = new ArrayList<Map<String,String>>();
        if(result !=null && result.size()>0){
            for (Object obj: result) {
                Object[] item = (Object[])obj;
                int riskType = item[1] !=null ? Integer.parseInt(item[1].toString()) : 0;
                riskTypes.add(riskType);
                String riskContent = item[2] !=null ? item[2].toString() : "";
                Map<String,String> map = new HashMap<String,String>();
                map.put("riskType",riskType+"");
                map.put("riskContent",riskContent);
                riskContents.add(map);
            }
            info.setRiskType(riskTypes);
            info.setTitle(riskContents);

            Map map = new HashMap();
            map.put("content",info);
            out.setData(map);
            out.setCode("0");
        }else{
            out.setCode("1");
            out.setMessage("查询企业风险信息，获取为空");
            logger.info("查询企业风险信息，获取为空");
        }

        return out;
    }

}
