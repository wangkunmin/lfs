package com.cscs.listedfacesys.busi;

import com.cscs.listedfacesys.dto.TendencyChartInfoData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by hj 2018/2/22
 * 新闻类公共service
 */
public class NewsClassBusiService {

    static Logger logger = Logger.getLogger("MyLogger");

    //根据时间区间，生成包含当前区间中的年月
    public static String[] getMonth7ByNowDate(String startDate ,String endDate){

        String[] dateStr = new String[]{};
        List list = new ArrayList();
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM").parse(startDate);
            Date date2 = new SimpleDateFormat("yyyy-MM").parse(endDate);
            Calendar dd = Calendar.getInstance();
            dd.setTime(date1);
            while (dd.getTime().before(date2)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String yearMonth = sdf.format(dd.getTime());
                list.add(yearMonth);
                dd.add(Calendar.MONTH,1);
            }
            list.add(endDate.substring(0,7));

            int size = list.size();
            dateStr = (String[])list.toArray(new String[size]);

        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("根据日期，获取改日期月份的所有日期,日期转换异常，异常信息："+e.getMessage());
        }
        return dateStr;
    }

    //根据时间区间，生成包含当前区间中的年
    public static String[] getYear7ByNowDate(String startDate ,String endDate){

        String[] dateStr = new String[]{};
        List list = new ArrayList();
        try {
            Date date1 = new SimpleDateFormat("yyyy").parse(startDate);
            Date date2 = new SimpleDateFormat("yyyy").parse(endDate);
            Calendar dd = Calendar.getInstance();
            dd.setTime(date1);
            while (dd.getTime().before(date2)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String year = sdf.format(dd.getTime());
                list.add(year);
                dd.add(Calendar.YEAR,1);
            }
            list.add(endDate.substring(0,4));

            int size = list.size();
            dateStr = (String[])list.toArray(new String[size]);

        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("根据日期，获取改日期月份的所有日期,日期转换异常，异常信息："+e.getMessage());
        }
        return dateStr;
    }

    /*根据传入日期获取到当月总计天数*/
    public static int calendarDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取某月的天数
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {

            logger.info("根据日期，获取改日期月份的所有日期,日期转换异常，异常信息："+e.getMessage());
        }
        int daysSize =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysSize;
    }

    //根据日期，生成该日期月份的所有日期的数据
    public static List<TendencyChartInfoData> getDaysStr(String nowDate, String date, List<TendencyChartInfoData> list) throws ParseException {
        date=date+"-01";
        List<TendencyChartInfoData> resList = new ArrayList<TendencyChartInfoData>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int daysSize = calendarDate(date);

        String[] daysStr = new String[daysSize];
        String yearMonth = date.substring(0,7);
        //生成该月份的所有日期
        for (int i =0 ; i < daysStr.length; i++) {
            if(i<9){
                daysStr[i] = yearMonth+"-0"+(i+1);
            }else {
                daysStr[i] = yearMonth+"-"+(i+1);
            }
        }

        for (int j=0;j<daysStr.length;j++){
            boolean flag = true;//标识list中没有当天日期的数据
            for (int k=0;k<list.size();k++){
                TendencyChartInfoData info = list.get(k);
                if(null!=info.getPostDt() && !"".equals(info.getPostDt())){
                    if(daysStr[j].equals(info.getPostDt())){
                        resList.add(info);
                        flag = false;
                        break;
                    }
                }
            }
            //当list中没有该日期的数据时，手动add空的对象进去
            if(flag){
                //小于等于当前日期的时候，给对象属性赋值默认为0，反之赋空值
                Date date1 = sdf.parse(daysStr[j]);
                Date date2 = sdf.parse(nowDate);
                if(date1.before(date2) || daysStr[j].equals(nowDate)){
                    TendencyChartInfoData infoData = new TendencyChartInfoData();
                    infoData.setNewCount(0);
                    infoData.setNegativeNewsCount(0);
                    infoData.setRatio("0");
                    infoData.setPostDt(daysStr[j]);
                    resList.add(infoData);
                }else{
                    TendencyChartInfoData infoData = new TendencyChartInfoData();
                    infoData.setNewCount(null);
                    infoData.setNegativeNewsCount(null);
                    infoData.setRatio("");
                    infoData.setPostDt(daysStr[j]);
                    resList.add(infoData);
                }

            }
        }

        return resList;
    }

    //拼接新闻趋势图时间条件
    public static String getsqlWhereForData(String date ){
        String sqlwhereDate = "";
        if(date !=null && !"".equals(date)){
            String postDate = date;
            if(postDate.length()==4){
                //拼接整年的区间
                String startDate=postDate+"-01-01";
                int daysSize = calendarDate(postDate+"-12-01");
                String endDate=postDate+"-12-" + daysSize ;
                sqlwhereDate = " WHERE to_date(A.POST_DT,'yyyy-MM-dd')  BETWEEN to_date('"+startDate+"','yyyy-MM-dd') and to_date('"+endDate+"','yyyy-MM-dd')";
            }else if(postDate.length()==6){
                //拼接整月的区间
                String year= postDate.substring(0,4);
                String month = postDate.substring(4);
                String startDate = year+"-"+month+"-01";
                int daysSize = calendarDate(startDate);
                String endDate = year+"-"+month+"-"  +daysSize;
                sqlwhereDate = " WHERE to_date(A.POST_DT,'yyyy-MM-dd')  BETWEEN to_date('"+startDate+"','yyyy-MM-dd') and to_date('"+endDate+"','yyyy-MM-dd')";
            }else{
                //拼接天的日期（加-）
                String year= postDate.substring(0,4);
                String month = postDate.substring(4,6);
                String day = postDate.substring(6);
                sqlwhereDate = " WHERE A.POST_DT= '"+year+"-"+month+"-"+day+"'";
            }
        }
        return sqlwhereDate;
    }
}
