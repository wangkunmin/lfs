package com.cscs.util;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hj on 2018/3/1.
 */
public class SolrUtil {

    public static final Set<String> COLUMNS_NEED_SPLIT = getHashSet();

    public static final String DEFAULT_QUERY_COLUMN = "text";

    /**
     * 根据默认字段text进行搜索，如果有多个关键字，则根据空格分割。
     */

    public static String getQueryPhrase(String keyword, int rows, int startRow) {
        String qColumn = DEFAULT_QUERY_COLUMN;
        String qValue = null;
        LinkedHashMap<String, String> fqMap = new LinkedHashMap<String, String>();

        if (keyword == null || keyword.trim().isEmpty()) {
            qValue = "*";
        } else {
            keyword = keyword.trim();
            String[] columns = keyword.split("\\s");
            qValue = "\"" + columns[0] + "\"";
            for (int i = 1; i < columns.length; i++) {
                fqMap.put(DEFAULT_QUERY_COLUMN, "\"" + columns[i] + "\"");
            }
        }
        return getQueryPhrase(qColumn, qValue, fqMap, rows, startRow);
    }

    /**
     * @param qColumn
     * @param qValue
     * @param keyword
     * @return
     */
    public static String getQueryPhrase(String qColumn, String qValue, String keyword, int rows, int startRow) {
        LinkedHashMap<String, String> fqMap = new LinkedHashMap<String, String>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            keyword = keyword.trim();
            String[] columns = keyword.split("\\s");
            for (int i = 0; i < columns.length; i++) {
                fqMap.put(DEFAULT_QUERY_COLUMN, "\"" + columns[i] + "\"");
            }
        }
        return getQueryPhrase(qColumn, qValue, fqMap, rows, startRow);
    }

    public static String getDateQueryPhraseByDay(int day) {
        // System.out.println("-------------------------" + day + "--------------------------");
        String startDate = DateUtils.getDateDiff(-day);
        String endDate = DateUtils.getCurrentDate();
        String dateQuery = "&fq=last_modified:["+startDate+"T00:00:00.000Z%20TO%20"+endDate+"T23:59:59.000Z]";
        return dateQuery;
    }



    public static String getString(JSONObject obj, String key){
        if(obj.has(key)){
            Object o = obj.get(key);
            if(o != null) {
                return o.toString();
            }
        }
        return null;
    }

    /**
     * field query，查询某字段，根据多个关键字搜索，取交集。
     * @param fqColumn
     * @param fqValue
     * @return
     */
    public static String getSplitedQueryPhrase(String fqColumn, String fqValue){
        StringBuilder sb = new StringBuilder();
        String[] columns = fqValue.split("\\s");
        for(String c : columns){
            sb.append("&fq=" + fqColumn + ":" + encode(c));
        }
        return sb.toString();
    }

    /**
     * field query, 范围搜索
     * @param fqColumn
     * @param start
     * @param end
     * @return
     */
    public static String getRangeQueryPhrase(String fqColumn, String start, String end){
        return "&fq=" + fqColumn + ":[" + start + "%20TO%20" + end + "]";
    }

    /**
     * 根据条件输出如："q=company_ids:513847&fq=text:链家&fq=title:万科"的地址。
     */
    public static String getQueryPhrase(String qColumn, String qValue,
                                        LinkedHashMap<String, String> fqMap, int rows, int startRow) {
        StringBuilder sb = new StringBuilder();
        sb.append("q=" + qColumn + ":" + encode(qValue));
        if (fqMap != null) {
            for (Map.Entry<String, String> entry : fqMap.entrySet()) {
                String fqColumn = entry.getKey();
                String fqValue = entry.getValue();

                if(COLUMNS_NEED_SPLIT.contains(fqColumn)){
                    String[] columns = fqValue.split("\\s");
                    for(String c : columns){
                        sb.append("&fq=" + fqColumn + ":" + encode(c));
                    }
                } else {
                    sb.append("&fq=" + fqColumn + ":" + encode(fqValue));
                }
            }
        }

        sb.append("&rows=" + rows + "&start=" + startRow);
        return sb.toString();
    }

    private static String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //never happen
            return s;
        }
    }

    public static Set<String> getHashSet() {
        Set<String> set = new HashSet<String >();
        set.add("text");
        return set;
    }
}
