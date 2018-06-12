package com.cscs.listedfacesys.busi;

import com.cscs.listedfacesys.dto.CompanyInfoMapOutData;
import com.cscs.listedfacesys.dto.RegionMapInfoData;
import com.cscs.listedfacesys.dto.RegionMapOutData;

import java.math.BigDecimal;
import java.util.*;

/**
 * create by on 2018/02/08
 * 地图类业务逻辑
 */
public class MapBusiService {

    /**
     * 全部地区数据遍历
     * @param objList
     * @return
     */
    public static List<RegionMapInfoData> getAllRegion(List<Object> objList) {
        List<RegionMapInfoData> regionDataList = new ArrayList<>();

        for (Object o: objList) {
            Object[] objs = (Object[]) o;
            String mapName = objs[0].toString();
            if (mapName.equals("其他")) {
                continue;
            }
            List<Number> nb = new ArrayList<>();
            for (int i = 1;i < 4;i++) {
                nb.add((Number) objs[i]);
            }
            int mainboardCount = nb.get(0).intValue();
            int mediumCpCount = nb.get(1).intValue();
            int businessCount = nb.get(2).intValue();

            RegionMapInfoData regionData = new RegionMapInfoData();
            regionData = new RegionMapInfoData();
            regionData.setMainboardCount(mainboardCount);
            regionData.setMediumCpCount(mediumCpCount);
            regionData.setBusinessCount(businessCount);
            regionData.setRegionName(mapName);
            regionData.setDate("00000000");

            regionDataList.add(regionData);

        }

        return regionDataList;
    }

    /**
     * 计算同比增长
     * @param lastList
     * @param nowList
     * @return
     */
    public static List<RegionMapInfoData> getYearGrowth(List<Object> lastList, List<Object> nowList){
        List<RegionMapInfoData> regionMapInfoDataList = new ArrayList<RegionMapInfoData>();
        RegionMapInfoData regionMapInfoData = new RegionMapInfoData();
        List<RegionMapInfoData> regionLastList = getAllRegion(lastList);
        List<RegionMapInfoData> regionNowList = getAllRegion(nowList);
        int lastMainCount = 0;
        int nowMainCount = 0;
        int lastMediumCount = 0;
        int nowMediumCount = 0;
        int lastBusiCount = 0;
        int nowBusiCount = 0;

        for (int i = 0; i < regionLastList.size(); i++) {
            //去年的数据
            lastMainCount += regionLastList.get(i).getMainboardCount();
            lastMediumCount += regionLastList.get(i).getMediumCpCount();
            lastBusiCount += regionLastList.get(i).getBusinessCount();
            //今年的数据
            nowMainCount += regionNowList.get(i).getMainboardCount();
            nowMediumCount += regionNowList.get(i).getMediumCpCount();
            nowBusiCount += regionNowList.get(i).getBusinessCount();

            RegionMapInfoData singleDate = new RegionMapInfoData();
            singleDate.setRegionName(regionLastList.get(i).getRegionName());
            int math = 0;
            //主板同比
            if (regionLastList.get(i).getMainboardCount() == 0) {
                if (regionNowList.get(i).getMainboardCount() != 0) {
                    singleDate.setMainratio(1);
                } else {
                    singleDate.setMainratio(0);
                }
            } else {
                math = regionNowList.get(i).getMainboardCount() - regionLastList.get(i).getMainboardCount();
                double mainRatio = new BigDecimal((float)math/regionLastList.get(i).getMainboardCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                singleDate.setMainratio(mainRatio);
            }
            //中小板同比
            if (regionLastList.get(i).getMediumCpCount() == 0) {
                if (regionNowList.get(i).getMediumCpCount() != 0) {
                    singleDate.setMediumratio(1);
                } else {
                    singleDate.setMediumratio(0);
                }
            } else {
                math = regionNowList.get(i).getMediumCpCount() - regionLastList.get(i).getMediumCpCount();
                double mediumRatio = (new BigDecimal((float)math/regionLastList.get(i).getMediumCpCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                singleDate.setMediumratio(mediumRatio);
            }
            //创业板同比
            if (regionLastList.get(i).getBusinessCount() == 0) {
                if (regionNowList.get(i).getBusinessCount() != 0) {
                    singleDate.setBusinessCount(1);
                } else {
                    singleDate.setBusinessCount(0);
                }
            } else {
                math = regionNowList.get(i).getBusinessCount() - regionLastList.get(i).getBusinessCount();
                double busiRatio = (new BigDecimal((float)math/regionLastList.get(i).getBusinessCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                singleDate.setBusiratio(busiRatio);
            }



            singleDate.setMainboardCount(regionNowList.get(i).getMainboardCount());
            singleDate.setMediumCpCount(regionNowList.get(i).getMediumCpCount());
            singleDate.setBusinessCount(regionNowList.get(i).getBusinessCount());
            regionMapInfoDataList.add(singleDate);
        }

        //主板同比
        int math = nowMainCount - lastMainCount;
        double mainRatio = new BigDecimal((float)math/lastMainCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //中小板同比
        math = nowMediumCount - lastMediumCount;
        double mediumRatio = (new BigDecimal((float)math/lastMediumCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        //创业板同比
        math = nowBusiCount - lastBusiCount;
        double busiRatio = (new BigDecimal((float)math/lastBusiCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        regionMapInfoData.setMainratio(mainRatio);
        regionMapInfoData.setMediumratio(mediumRatio);
        regionMapInfoData.setBusiratio(busiRatio);
        regionMapInfoData.setMainboardCount(nowMainCount);
        regionMapInfoData.setMediumCpCount(nowMediumCount);
        regionMapInfoData.setBusinessCount(nowBusiCount);
        regionMapInfoData.setRegionName("深圳市");
        regionMapInfoDataList.add(0,regionMapInfoData);


        return regionMapInfoDataList;
    }

    //生成深圳市的所有区
    public static List<RegionMapOutData> getRegions(){
        List<RegionMapOutData> list = new ArrayList<RegionMapOutData>();
        RegionMapOutData outData1 = new RegionMapOutData();
        outData1.setRegionName("光明新区");
        list.add(outData1);
        RegionMapOutData outData2 = new RegionMapOutData();
        outData2.setRegionName("宝安区");
        list.add(outData2);
        RegionMapOutData outData3 = new RegionMapOutData();
        outData3.setRegionName("龙华新区");
        list.add(outData3);
        RegionMapOutData outData4 = new RegionMapOutData();
        outData4.setRegionName("南山区");
        list.add(outData4);
        RegionMapOutData outData5 = new RegionMapOutData();
        outData5.setRegionName("福田区");
        list.add(outData5);
        RegionMapOutData outData6 = new RegionMapOutData();
        outData6.setRegionName("罗湖区");
        list.add(outData6);
        RegionMapOutData outData7 = new RegionMapOutData();
        outData7.setRegionName("龙岗区");
        list.add(outData7);
        RegionMapOutData outData8 = new RegionMapOutData();
        outData8.setRegionName("坪山新区");
        list.add(outData8);
        RegionMapOutData outData9 = new RegionMapOutData();
        outData9.setRegionName("大鹏新区");
        list.add(outData9);
        RegionMapOutData outData10 = new RegionMapOutData();
        outData10.setRegionName("盐田区");
        list.add(outData10);
        RegionMapOutData outData11 = new RegionMapOutData();
        outData11.setRegionName("其他");
        list.add(outData11);
        return list;

    }

    //获取深圳市所有上市公司的评级风险统计数
    public static Map<String,Object>  getRiskStatistics(List<RegionMapOutData> regionList){
        Map<String,Object> shenZhen = new HashMap<String,Object>();
        shenZhen.put("regionList",regionList);
        int highRiskCount =0;
        int mediumRiskCount = 0;
        int focusCount = 0;
        int lowRiskCount = 0;
        //统计每个区的风险数
        for (RegionMapOutData region: regionList) {
            //获取当前区的所有公司集合
            List<CompanyInfoMapOutData> companyInfos = region.getCompanyInfos();
            if(companyInfos !=null && companyInfos.size()>0){
                int regionHighRiskCount =0;
                int regionMediumRiskCount = 0;
                int regionFocusCount = 0;
                int regionLowRiskCount = 0;
                for (CompanyInfoMapOutData companyInfo: companyInfos) {
                    //获取公司评级字段
                    String rating = companyInfo.getRating();
                    if(rating !=null && !"".equals(rating)){
                        if("1".equals(rating)){
                            //累计高风险数
                            regionHighRiskCount = regionHighRiskCount+1;
                        }else if("2".equals(rating)){
                            //累计次高风险数
                            regionMediumRiskCount = regionMediumRiskCount+1;
                        }else if("3".equals(rating)){
                            //累计关注数
                            regionFocusCount = regionFocusCount+1;
                        }else if("4".equals(rating)){
                            //累计低风险数
                            regionLowRiskCount = regionLowRiskCount+1;
                        }
                    }
                }
                region.setHighRiskCount(regionHighRiskCount);
                region.setMediumRiskCount(regionMediumRiskCount);
                region.setFocusCount(regionFocusCount);
                region.setLowRiskCount(regionLowRiskCount);
            }
            highRiskCount += region.getHighRiskCount();
            mediumRiskCount += region.getMediumRiskCount();
            focusCount += region.getFocusCount();
            lowRiskCount += region.getLowRiskCount();
        }
        shenZhen.put("highRiskCount",highRiskCount);
        shenZhen.put("mediumRiskCount",mediumRiskCount);
        shenZhen.put("focusCount",focusCount);
        shenZhen.put("lowRiskCount",lowRiskCount);
        shenZhen.put("regionName","深圳");

        return  shenZhen;
    }

    //获取深圳市所有上市公司的负面事件和预警公司统计数
    public static Map<String,Object>  getNegativeRiskCount(List<RegionMapOutData> regionList){
        Map<String,Object> shenZhen = new HashMap<String,Object>();
        shenZhen.put("regionList",regionList);
        int waringCompanyCount =0;
        int negativeCount =0;
        //统计每个区的风险数
        for (RegionMapOutData region: regionList) {
            //获取当前区的所有公司集合
            List<CompanyInfoMapOutData> companyInfos = region.getCompanyInfos();
            if(companyInfos !=null && companyInfos.size()>0){

                int regionNegativeCount =0;
                for (CompanyInfoMapOutData companyInfo: companyInfos) {
                    regionNegativeCount += companyInfo.getNegativeCount();
                }
                region.setNegativeCount(regionNegativeCount);
                region.setWaringCompanyCount(companyInfos.size());
            }
            waringCompanyCount += region.getWaringCompanyCount();
            negativeCount += region.getNegativeCount();
        }
        shenZhen.put("waringCompanyCount",waringCompanyCount);
        shenZhen.put("negativeCount",negativeCount);
        return  shenZhen;
    }

    //获取风险维度各风险分类的统计数
    public static Map<String,Object>  getRiskDimensionCount(List<RegionMapOutData> regionList){
        Map<String,Object> shenZhen = new HashMap<String,Object>();
        shenZhen.put("regionList",regionList);
        //财务风险数
        int riskCaiWuCount=0;
        //治理风险数
        int riskZhiLiCount=0;
        //信息披露数
        int riskXinXiCount=0;
        //其他风险数
        int riskQiTaCount=0;
        //统计每个区的风险数
        for (RegionMapOutData region: regionList) {
            List<CompanyInfoMapOutData> conpanyInfos = region.getCompanyInfos();
            int regionCaiWuCount =0;
            int regionZhiLiCount =0;
            int regionXinXiCount =0;
            int regionQiTaCount =0;
            if(conpanyInfos !=null && conpanyInfos.size()>0){
                for (CompanyInfoMapOutData info : conpanyInfos){
                            regionZhiLiCount += info.getRiskZhiLiCount();
                            regionXinXiCount += info.getRiskXinXiCount();
                            regionCaiWuCount += info.getRiskCaiWuCount();
                            regionQiTaCount += info.getRiskQiTaCount();

                }

                region.setRiskCaiWuCount(regionCaiWuCount);
                region.setRiskZhiLiCount(regionZhiLiCount);
                region.setRiskXinXiCount(regionXinXiCount);
                region.setRiskQiTaCount(regionQiTaCount);
                riskCaiWuCount += regionCaiWuCount;
                riskZhiLiCount += regionZhiLiCount;
                riskXinXiCount += regionXinXiCount;
                riskQiTaCount += regionQiTaCount;
            }

        }


        shenZhen.put("riskCaiWuCount",riskCaiWuCount);
        shenZhen.put("riskZhiLiCount",riskZhiLiCount);
        shenZhen.put("riskXinXiCount",riskXinXiCount);
        shenZhen.put("riskQiTaCount",riskQiTaCount);
        return  shenZhen;
    }
}
