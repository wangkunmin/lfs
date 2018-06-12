package com.cscs.listedfacesys.busi;

import com.cscs.listedfacesys.dto.*;
import com.cscs.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by wzy 2018/2/6
 */
public class AnnounceBusiService {
    static Logger logger = Logger.getLogger("MyLogger");

    /**
     * 对公告信息列表进行处理及排序
     * @param contentList
     * @param focusIds
     * @return
     */
    public static List<WarningInfoData> getWarningInfoData(List<Object> contentList, Set<String> focusIds) {
        Map<String, WarningInfoData> outMap = new LinkedHashMap<String, WarningInfoData>();

        for (Object it : contentList) {
            Object[] item = (Object[]) it;
            String companyId = item[0].toString();  //公司id

            WarningInfoData info = outMap.get(companyId);

            /*若不存在key=当前公司id，就创建新的key*/
            if (info == null) {
                info = new WarningInfoData();
                info.setCompanyId(companyId);
                if (item[1] != null) {
                    info.setCompanyNm(item[1].toString());              //公司名称
                }
                info.setIsFocused(focusIds.contains(companyId));
                outMap.put(companyId, info);
            }


            /*若不存在key=当前大标题，就创建新的key*/
            Map<String, List<String>> typeMap = info.getTypeMap();
            if (typeMap == null) {
                typeMap = new TreeMap<String, List<String>>();
                info.setTypeMap(typeMap);
            }

            String warnType = item[3].toString();               //大标题
            List<String> warnTitles = typeMap.get(warnType);    //小标题
            /*若不存在key=当前小标题，就创建新的key*/
            if (warnTitles == null) {
                warnTitles = new ArrayList<String>();
                typeMap.put(warnType, warnTitles);
            }
            /*去除重复数据并对小标题计数*/
            if(isDupliated(warnTitles, item[2].toString())){
                warnTitles.add(item[2].toString());
                info.setWarnCount(info.getWarnCount() + 1);
            } else {
                logger.info("[重复数据]" + item[2].toString());
            }
            //公告时间
            if (item[4] != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String noticeDt = sdf.format(item[4]);
                info.setNoticeDt(noticeDt);
            }
            //公告CD
            if (item[5] != null) {
                String infoCd = item[5].toString();
                info.setInfoCd(infoCd);
            }
            //股票CD
            if (item[8] != null) {
                String securityCd = item[8].toString();
                info.setSecurityCd(securityCd);
            }

        }
        List<WarningInfoData> list = new ArrayList<>(outMap.values());          //排序
        Collections.sort(list, new Comparator<WarningInfoData>() {
            @Override
            public int compare(WarningInfoData o1, WarningInfoData o2) {
                if (o1 != null && o2 != null) {
                    /*对比中标数量排序，若标数相同，则根据公司id排序*/
                    int num1 = o2.getWarnCount() - o1.getWarnCount();
                    int num2 = Integer.valueOf(o2.getCompanyId()) - Integer.valueOf(o1.getCompanyId());
                    if (num1 == 0) {
                        return num2;
                    }
                    return num1;
                }
                return 0;
            }
        });

        return list;
    }

    /**
     * 根据公司id增加监管人员姓名
     * @param list
     * @param nameList
     * @return
     */
    public static List<WarningInfoData> addSupervisor(List<WarningInfoData> list, List<Object> nameList) {
        List<WarningInfoData> warningInfoDataList = list;

        for (Object o: nameList) {
            Object[] item = (Object[]) o;
            String companyId = item[0].toString();
            String name = item[1].toString();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCompanyId().equals(companyId)) {
                    list.get(i).setSupervisor(name);
                }
            }
        }
        return list;
    }

    /**
     * 字符串相似比较
     * @param s
     * @return
     */
    private static boolean isDupliated(List<String> title, String s){
        for(String item : title){
            if (item.equals(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 7年数据遍历处理方法
     * @param volumeData
     * @param startDate
     * @return
     */
    public static List<WarningRiskOutData> convert(List<Object> volumeData, String startDate, String endDate, int range) {
        Map<String, WarningRiskOutData> dataMap = new TreeMap<String, WarningRiskOutData>();
        List<WarningRiskOutData> issuedVolumeList = new ArrayList<WarningRiskOutData>();

        for (Object o: volumeData) {
            Object[] objs = (Object[]) o;
            String dateMonth = StringUtil.toString(objs[0]);
            List<Number> nb = new ArrayList<>();
            for (int i = 0;i < 5;i++) {
                nb.add((Number) objs[i+1]);
            }
            //获取五种风险指标数
            int risk1 = nb.get(0).intValue();
            int risk2 = nb.get(1).intValue();
            int risk3 = nb.get(2).intValue();
            int risk4 = nb.get(3).intValue();
            int risk5 = nb.get(4).intValue();

            if (dateMonth != null && dateMonth.length() == 8) {
                String year = dateMonth.substring(0, 4);
                Integer month = Integer.parseInt(dateMonth.substring(4, 6));
                Integer day = Integer.parseInt(dateMonth.substring(6, 8));

                /*对年份进行操作，若无key=当前年，则创建key=当前年*/
                WarningRiskOutData riskData = dataMap.get(year);
                if (riskData == null) {
                    riskData = new WarningRiskOutData();
                    riskData.setDate(year);
                    dataMap.put(year, riskData);
                }

                /*对月份进行操作，若无key=当前月，则创建key=当前月*/
                List<WarningRiskInfoData> riskByYear = riskData.getWarningRiskInfoDataList();
                if (riskByYear == null) {
                    riskByYear = new ArrayList<WarningRiskInfoData>();
                    for (int i = 0; i < 12; i++) {
                        riskByYear.add(i,new WarningRiskInfoData());
                        riskByYear.get(i).setDataMonth(i + 1);
                    }
                }

                /*对日数据进行操作，若无key=当前天，则创建key=当前天*/
                List<WarningRiskInfo2Data> riskByMonth = riskByYear.get(month - 1).getDayList();
                if (riskByMonth == null) {
                    riskByMonth = new ArrayList<WarningRiskInfo2Data>();
                    int dayCount = NewsClassBusiService.calendarDate(year + "-" + month + "-" + day);
                    for (int j = 0; j < dayCount ; j++) {
                        riskByMonth.add(j, new WarningRiskInfo2Data());
                        riskByMonth.get(j).setDataMonth(j + 1);
                    }
                }

                /*统计当月五种指标数量*/
                riskByMonth.get(day - 1).setRisk1(risk1);
                riskByMonth.get(day - 1).setRisk2(risk2);
                riskByMonth.get(day - 1).setRisk3(risk3);
                riskByMonth.get(day - 1).setRisk4(risk4);
                riskByMonth.get(day - 1).setRisk5(risk5);

                riskByYear.get(month - 1).setDayList(riskByMonth);
                riskData.setWarningRiskInfoDataList(riskByYear);
            }
        }

        int yearSum = Integer.valueOf(endDate.substring(0, 4));
        Iterator<String> iter = dataMap.keySet().iterator();
        while (iter.hasNext()) {
            int key = Integer.valueOf(iter.next());
            if (key < yearSum) {
                yearSum = key;
            }
        }
        int num = Integer.valueOf(endDate.substring(0, 4)) - yearSum + 1;
        /*对数据进行遍历，填补无数据的空年份，月份，天数*/
        for (int i = 0; i < num; i++) {
            int yearByI = yearSum + i;
            WarningRiskOutData single = null;
            if (dataMap.get(String.valueOf(yearByI)) == null ){
                single = new WarningRiskOutData();
                single.setDate(String.valueOf(yearByI));
                single.setWarningRiskInfoDataList(addMonthElement(String.valueOf(yearByI)));
            } else {
                single = dataMap.get(String.valueOf(yearByI));
                single = sumDaydata(single, yearByI, endDate);
            }
            single = selectJudge(i, num, yearByI, single, startDate, endDate);
            issuedVolumeList.add(single);
        }

        return issuedVolumeList;
    }

    /*统计年份五种风险总数*/
    private static WarningRiskOutData sumDaydata(WarningRiskOutData data, int yearByI, String endDate) {
        WarningRiskOutData single = data;
        int yearCount1 = 0;
        int yearCount2 = 0;
        int yearCount3 = 0;
        int yearCount4 = 0;
        int yearCount5 = 0;
        for (int j = 0; j < 12 ; j++) {
            int riskMonthCount1 = 0;
            int riskMonthCount2 = 0;
            int riskMonthCount3 = 0;
            int riskMonthCount4 = 0;
            int riskMonthCount5 = 0;
            if (single.getWarningRiskInfoDataList().get(j).getDayList() == null) {
                String date = String.valueOf(yearByI) + "-" + String.valueOf(j + 1);
                single.getWarningRiskInfoDataList().get(j).setDayList(addDayElement(date));
            } else {
                WarningRiskInfoData monthData = single.getWarningRiskInfoDataList().get(j);
                int daySum = single.getWarningRiskInfoDataList().get(j).getDayList().size();
                if (yearByI == Integer.parseInt(endDate.substring(0, 4)) && j+1 == Integer.parseInt(endDate.substring(4, 6))) {
                    daySum = Integer.parseInt(endDate.substring(6, 8))-1;
                    int daySize = single.getWarningRiskInfoDataList().get(j).getDayList().size();
                    for (int k = daySum; k < daySize; k++) {
                        monthData.getDayList().get(k).setRisk1(null);
                        monthData.getDayList().get(k).setRisk2(null);
                        monthData.getDayList().get(k).setRisk3(null);
                        monthData.getDayList().get(k).setRisk4(null);
                        monthData.getDayList().get(k).setRisk5(null);
                    }
                }
                for (int k = 0; k < daySum; k++) {
                    riskMonthCount1 += monthData.getDayList().get(k).getRisk1();
                    riskMonthCount2 += monthData.getDayList().get(k).getRisk2();
                    riskMonthCount3 += monthData.getDayList().get(k).getRisk3();
                    riskMonthCount4 += monthData.getDayList().get(k).getRisk4();
                    riskMonthCount5 += monthData.getDayList().get(k).getRisk5();
                }
                single.getWarningRiskInfoDataList().get(j).setRisk1(riskMonthCount1);
                single.getWarningRiskInfoDataList().get(j).setRisk2(riskMonthCount2);
                single.getWarningRiskInfoDataList().get(j).setRisk3(riskMonthCount3);
                single.getWarningRiskInfoDataList().get(j).setRisk4(riskMonthCount4);
                single.getWarningRiskInfoDataList().get(j).setRisk5(riskMonthCount5);
            }
            yearCount1 += single.getWarningRiskInfoDataList().get(j).getRisk1();
            yearCount2 += single.getWarningRiskInfoDataList().get(j).getRisk2();
            yearCount3 += single.getWarningRiskInfoDataList().get(j).getRisk3();
            yearCount4 += single.getWarningRiskInfoDataList().get(j).getRisk4();
            yearCount5 += single.getWarningRiskInfoDataList().get(j).getRisk5();
        }
        single.setRisk1Count(yearCount1);
        single.setRisk2Count(yearCount2);
        single.setRisk3Count(yearCount3);
        single.setRisk4Count(yearCount4);
        single.setRisk5Count(yearCount5);
        return single;
    }

    //为每月空数据添加空字段
    private static List<WarningRiskInfoData> addMonthElement(String date) {
        List<WarningRiskInfoData> monthList = new ArrayList<>();
        for (int i = 0; i < 12 ; i++) {
            WarningRiskInfoData single = new WarningRiskInfoData();
            single.setDataMonth(i + 1);
            List<WarningRiskInfo2Data> dayList = addDayElement(date + "-" + (i + 1));
            single.setDayList(dayList);
            monthList.add(i, single);
        }
        return monthList;
    }

    //为每月每日空数据添加空字段
    private static List<WarningRiskInfo2Data> addDayElement(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNow = sdf.format(new Date());
        Calendar c = Calendar.getInstance();
        int yearNow = Integer.valueOf(dateNow.substring(0, 4));
        int monthNow = Integer.valueOf(dateNow.substring(4, 6));
        int dayNow = Integer.valueOf(dateNow.substring(6, 8));

        //获取当月总天数
        int dayCount = NewsClassBusiService.calendarDate(date + "-" + "01");

        int daySecond = 0;
        if (yearNow == Integer.valueOf(date.substring(0, 4)) && monthNow == Integer.valueOf(date.substring(5, date.length()))) {
            daySecond = dayCount - dayNow;
            dayCount = dayNow;
        }

        List<WarningRiskInfo2Data> dayList = new ArrayList<>();
        for (int i = 1; i <= dayCount ; i++) {
            WarningRiskInfo2Data dayData = new WarningRiskInfo2Data();

            dayData.setRisk1(0);
            dayData.setRisk2(0);
            dayData.setRisk3(0);
            dayData.setRisk4(0);
            dayData.setRisk5(0);
            dayData.setDataMonth(i);
            dayList.add(dayData);
        }
        if (yearNow == Integer.valueOf(date.substring(0, 4)) && monthNow == Integer.valueOf(date.substring(5, date.length()))) {

            for (int i = 0; i < daySecond; i++) {
                WarningRiskInfo2Data wri = new WarningRiskInfo2Data(null, null, null, null, null, dayNow + i + 1);
                dayList.add(wri);
            }

        }

        return dayList;
    }

    //根据传入需要查询的时间段消除不存在的月份
    private static WarningRiskOutData selectJudge(int i, int range, int yearByI, WarningRiskOutData judgeData, String startDate, String endDate) {
        WarningRiskOutData warningRiskOutData = new WarningRiskOutData();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String nowDate = df.format(new Date());

        warningRiskOutData = judgeData;
        int year = Integer.parseInt(nowDate.substring(0, 4));
        int month = Integer.parseInt(nowDate.substring(4, 6));
        int day = Integer.parseInt(nowDate.substring(6, 8));

        /*如果是一年内数据，从开始日到结束日消除多余日期*/
        if (Integer.parseInt(startDate.substring(0, 4)) == Integer.parseInt(endDate.substring(0, 4))) {
            year = Integer.parseInt(startDate.substring(0, 4));
            month = Integer.parseInt(startDate.substring(4, 6));
            day = Integer.parseInt(startDate.substring(6, 8));
            for (int j = 0; j < month - 1 ; j++) {
                warningRiskOutData.getWarningRiskInfoDataList().remove(0);
            }
            int year2 = Integer.parseInt(endDate.substring(0, 4));
            int month2 = Integer.parseInt(endDate.substring(4, 6));
            int day2 = Integer.parseInt(endDate.substring(6, 8));
            for (int j = 12 - month; j > month2 - month ; j--) {
                warningRiskOutData.getWarningRiskInfoDataList().remove(j);
            }
            return warningRiskOutData;
        }

        /*如果是开始日期，从开始日消除*/
        if (yearByI == Integer.parseInt(startDate.substring(0, 4))){
            year = Integer.parseInt(startDate.substring(0, 4));
            month = Integer.parseInt(startDate.substring(4, 6));
            day = Integer.parseInt(startDate.substring(6, 8));
            for (int j = 0; j < month - 1 ; j++) {
                warningRiskOutData.getWarningRiskInfoDataList().remove(0);
            }
            return warningRiskOutData;
        }
        /*如果是结束日期，从结束日消除*/
        if (yearByI == Integer.parseInt(endDate.substring(0, 4))) {
            year = Integer.parseInt(endDate.substring(0, 4));
            month = Integer.parseInt(endDate.substring(4, 6));
            day = Integer.parseInt(endDate.substring(6, 8));
            for (int j = 12; j > month ; j--) {
                warningRiskOutData.getWarningRiskInfoDataList().remove(j-1);
            }
            return warningRiskOutData;
        }
        if(i == (range - 1) && (yearByI + i) == year) {
            for (int j = 12; j > month ; j--) {
                warningRiskOutData.getWarningRiskInfoDataList().remove(j-1);
            }
            return warningRiskOutData;
        } else {
            return judgeData;
        }
    }

    //添加三种快捷浏览
    public static List<WarningInfoData> cutTypeName(List<WarningInfoData> outList) {
        Map<String, List<String>> contentMap = null;
        List<WarningInfoData> outData = new ArrayList<>();
        for (WarningInfoData wid:outList) {
            Map<String, List<String>> typeMap = wid.getTypeMap();
            contentMap = new TreeMap<>();
            Iterator<Map.Entry<String, List<String>>> itor = typeMap.entrySet().iterator();
            while (itor.hasNext()) {
                if (contentMap.size() >= 3) {
                    break;
                }
                Map.Entry<String, List<String>> nextMap = itor.next();
                String typeNm = StringUtils.substringBefore(nextMap.getKey(),"-");
                List<String> titleNm = nextMap.getValue();
                contentMap.put(typeNm, titleNm);
            }
            wid.setContent(contentMap);
            outData.add(wid);
        }

        return outData;
    }

    //3画面风险公告数据处理和排序
    public static List<WarningInfoData> getThreeSideWarningData(List<Object> singleList) {
        List<WarningInfoData> list = new ArrayList<>();

        for (Object it : singleList) {
            Object[] item = (Object[]) it;

            int flag = 0;

            WarningInfoData info = new WarningInfoData();
            if (item[2] != null) {
                info.setNoticeDt(item[2].toString().substring(0, 10));
            }

            //风险等级
            if (item[4] != null) {
                Number nb = (Number) item[4];
                int important = nb.intValue();
                info.setImportance(important);
            }

            Map<String, String> typeMap = new TreeMap<>();

            if (item[0] != null && item[1] != null) {
                if(list !=null && list.size()>0){
                    /*去除重复数据并对小标题计数*/
                    for (int i = 0; i < list.size(); i++) {
                        List<String> title = new ArrayList<>(list.get(i).getTypeMap().values());
                        if(isDupliated(title, item[0].toString())){
                            typeMap.put(item[1].toString(), item[0].toString());
                            info.setTypeSecond(item[1].toString());
                        } else {
                            if(info.getNoticeDt() !=null && list.get(i).getNoticeDt() !=null){
                                if(!info.getNoticeDt().equals(list.get(i).getNoticeDt())){
                                    typeMap.put(item[1].toString(), item[0].toString());
                                    info.setTypeSecond(item[1].toString());
                                    break;
                                }
                            }

                            String str = StringUtils.replace(String.valueOf(list.get(i).getTypeMap().keySet()),"[","");
                            str = StringUtils.replace(str,"]","");
                            if (list.get(i).getImportance() < info.getImportance()) {
                                typeMap.put(item[1].toString(), item[0].toString());
                                list.get(i).setTypeMap(typeMap);
                                list.get(i).setImportance(info.getImportance());
                            }
                            list.get(i).setTypeSecond(str + "," + item[1].toString());
                            flag = 1;
                            logger.info("[重复数据]" + item[0].toString());
                            break;
                        }

                    }
                    if (flag == 1) {
                        continue;
                    }
                }else{
                    typeMap.put(item[1].toString(), item[0].toString());
                    info.setTypeSecond(item[1].toString());
                }


            }

            //作为排序比较第二标识符
            if (item[3] != null) {
                String industryNm = item[3].toString();
                info.setIndustryNm(industryNm);
            } else {
                info.setIndustryNm("0");
            }
            info.setTypeMap(typeMap);
            list.add(info);
        }

        //排序
        Collections.sort(list, (o1, o2) -> {
            if (o1 != null && o2 != null) {
                int num1 = o2.getIndustryNm().compareTo(o1.getIndustryNm());
                int num2 = o2.getNoticeDt().compareTo(o1.getNoticeDt());
                if (num2 == 0) {
                    return num1;
                }
                return num2;
            }
            return 0;
        });

        return list;
    }

    public static List<WarningInfoData> getCompanyInfo(List<Object> info, List<WarningInfoData> companyList, Set<String> focusIds) {
        List<WarningInfoData> warningInfoList = new ArrayList<>();

        for (Object it : info) {
            Object[] item = (Object[]) it;
            WarningInfoData data = new WarningInfoData();

            if (item[0] != null) {
                data.setCompanyId(item[0].toString());
            }
            if (item[1] != null) {
                data.setCompanySnm(item[1].toString());
            }
            if (item[2] != null) {
                data.setCompanyNm(item[2].toString());
            }
            if (item[3] != null) {
                data.setSecurityCd(item[3].toString());
            }

            warningInfoList.add(data);

        }
        for (int i = 0;i < warningInfoList.size();i++) {
            int flag = 0;
            for (int j = 0; j < companyList.size(); j++) {
                if (companyList.get(j).getCompanyId().equals(warningInfoList.get(i).getCompanyId())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                WarningInfoData widata = new WarningInfoData();
                widata.setCompanyId(warningInfoList.get(i).getCompanyId());
                widata.setCompanySnm(warningInfoList.get(i).getCompanySnm());
                widata.setCompanyNm(warningInfoList.get(i).getCompanyNm());
                widata.setSecurityCd(warningInfoList.get(i).getSecurityCd());
                widata.setIsFocused(focusIds.contains(warningInfoList.get(i).getCompanyId()));
                companyList.add(widata);
            }
        }
        return companyList;
    }


    /**
     * 对公告信息列表进行处理及排序
     * @param contentList
     * @param focusIds
     * @return
     */
    public static List<WarningInfoData> getWarningInfoDataForMap(List<Object> contentList, Set<String> focusIds) {
        Map<String, WarningInfoData> outMap = new LinkedHashMap<String, WarningInfoData>();

        for (Object it : contentList) {
            Object[] item = (Object[]) it;
            String companyId = item[0].toString();  //公司id
            String type = item[7] !=null ? item[7].toString() : "";

            WarningInfoData info = outMap.get(companyId);

            /*若不存在key=当前公司id，就创建新的key*/
            if (info == null) {
                info = new WarningInfoData();
                info.setCompanyId(companyId);
                if (item[1] != null) {
                    info.setCompanyNm(item[1].toString());              //公司名称
                }
                info.setIsFocused(focusIds.contains(companyId));
                outMap.put(companyId, info);
            }


            /*若不存在key=当前大标题，就创建新的key*/
            List<Map<String,String>> typeMap = info.getTypeMap2();
            if (typeMap == null) {
                typeMap = new ArrayList<Map<String,String>>();
                Map<String,String> map = new HashMap<String,String>();
                map.put("infoCd",item[5]!=null ? item[5].toString():"");
                map.put("title",item[2] != null ? item[2].toString() :"");
                map.put("companyId",companyId);
                map.put("type",type);//获取事件类型1：公告，2：新闻
                map.put("eventId",item[9]!= null ? item[9].toString() :"");
                typeMap.add(map);
                info.setWarnCount(info.getWarnCount() + 1);
                info.setTypeMap2(typeMap);
            }else {
                Map<String,String> map = new HashMap<String,String>();
                map.put("infoCd",item[5]!=null ? item[5].toString():"");
                map.put("title",item[2] != null ? item[2].toString() :"");
                map.put("companyId",companyId);
                map.put("type",type);//获取事件类型1：公告，2：新闻
                map.put("eventId",item[9]!= null ? item[9].toString() :"");
                typeMap.add(map);
                info.setWarnCount(info.getWarnCount() + 1);
                info.setTypeMap2(typeMap);
            }

            //公告时间
            if (item[4] != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String noticeDt = sdf.format(item[4]);
                info.setNoticeDt(noticeDt);
            }
            //公告CD
            if (item[5] != null) {
                String infoCd = item[5].toString();
                info.setInfoCd(infoCd);
            }
            //股票CD
            if (item[8] != null) {
                String securityCd = item[8].toString();
                info.setSecurityCd(securityCd);
            }

        }
        List<WarningInfoData> list = new ArrayList<>(outMap.values());          //排序
        Collections.sort(list, new Comparator<WarningInfoData>() {
            @Override
            public int compare(WarningInfoData o1, WarningInfoData o2) {
                if (o1 != null && o2 != null) {
                    /*对比中标数量排序，若标数相同，则根据公司id排序*/
                    int num1 = o2.getWarnCount() - o1.getWarnCount();
                    int num2 = Integer.valueOf(o2.getCompanyId()) - Integer.valueOf(o1.getCompanyId());
                    if (num1 == 0) {
                        return num2;
                    }
                    return num1;
                }
                return 0;
            }
        });

        return list;
    }

}
