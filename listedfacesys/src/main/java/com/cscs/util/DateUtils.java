package com.cscs.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by rugal on 2016/3/30.
 */
public class DateUtils {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.CHINA);
    public static final SimpleDateFormat ISO_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat ISO_DATE_FORMAT2 = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("dd");

    public static Date getNextDate() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static String getCurrentDate() {
        Date date = new Date();
        return ISO_DATE_FORMAT.format(date);
    }

    public static String getCurrentDate2() {
        Date date = new Date();
        return ISO_DATE_FORMAT2.format(date);
    }

    public static String getDateDiff(int day) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, day);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return ISO_DATE_FORMAT.format(instance.getTime());
    }
    public static String getDateDiff2(int day) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, day);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return ISO_DATE_FORMAT2.format(instance.getTime());
    }


    public static Date getLastDayOfYear(String str) {
        try {
            if (str.endsWith(".0"))
                str = str.substring(0, str.length() - 2);
            return DATE_FORMAT.parse("31/12/" + str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp getLastDayOfYearAsTimestamp(String str) {
        Date date = getLastDayOfYear(str);
        if (date != null) {
            try {
                Timestamp time = new Timestamp(date.getTime());
                return time;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static Date getDate(String str) {
        try {
            return DATE_FORMAT2.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp getDateInTimestamp(String str) {
        try {
            return new Timestamp(ISO_DATE_FORMAT.parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    //传入小时数24 获取

    public static String[] subDateByFormat(Date date) {
        SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf1 = new SimpleDateFormat("HH:mm");
        String dateString = tf.format(date);
        String timeString = tf1.format(date);
        String[] newArray = new String[2];
        newArray[0] = dateString;
        newArray[1] = timeString;
        return newArray;
    }

    /**
     * java.util.Date类型时间转java.sql.Date时间
     *
     * @param date java.util.Date类型时间
     * @return
     */
    public static java.sql.Date dateChangeSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * java.util.Date类型时间转java.sql.Time时间
     *
     * @param date java.util.Date类型时间
     * @return
     */
    public static Time dateChangeTime(Date date) {
        return new Time(date.getTime());
    }

    /**
     * java.util.Date类型时间转java.sql.Timestamp时间
     *
     * @param date java.util.Date类型时间
     * @return
     */
    public static Timestamp dateChangeTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取MM格式
     *
     * @return
     */
    public static String getMonth() {
        return sdfMonth.format(new Date());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    private static String getString(Calendar c) {
        StringBuffer result = new StringBuffer();
        result.append(c.get(Calendar.YEAR));
        result.append("-");
        result.append((c.get(Calendar.MONTH) + 1));
        result.append("-");
        result.append(c.get(Calendar.DAY_OF_MONTH));
        result.append(" ");
        result.append(c.get(Calendar.HOUR_OF_DAY));
        result.append(":");
        result.append(c.get(Calendar.MINUTE));
        result.append(":");
        result.append(c.get(Calendar.SECOND));
        return result.toString();
    }

    /**
     * UCT 时间格式转换
     */
    public static String changeTime(String ts) {
        String result = "";
        try {
            ts = ts.replace("Z", " UTC");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
            Date dt = sdf.parse(ts);
            TimeZone tz = sdf.getTimeZone();
            Calendar c = sdf.getCalendar();
            result = getString(c);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return result;
    }


    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 字符串转换为Date
     */
    public static Date stringToDate(String dstr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String转化为时间戳
     */
    public static Long stringToTimestamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取减去当前时间N小时的字符串
     */
    public static String getHourTime(int hour) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, hour);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(now.getTimeInMillis());
        return dateStr;
    }

    /**
     * UTC  自行替换
     */
    public static String getUtc(String str) {
        str = str.replace("T", " ");
        str = str.replace("Z", "");
        return str;
    }
}