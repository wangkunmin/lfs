package com.cscs.util;

import java.util.ArrayList;
import java.util.List;

/**
 * sql工具类
 */
public class SqlUtils {

    /**
     * 生成分页sql
     * @param page 当前页数
     * @param pageSize 一页显示的数量
     * @param sql  需分页的业务sql
     * @return
     */
    public static String  pagingMethod(int page,int pageSize,String sql){
        int pageNum = page*pageSize+1;
        int rows = (page-1)*pageSize;
        String pagingSql = "SELECT *\n" +
                "FROM\n" +
                "  (SELECT tt.*,\n" +
                "    ROWNUM RN\n" +
                "  FROM\n" +
                "    ( \n"
                +
                sql
                +
                "    ) tt\n" +
                "  WHERE ROWNUM < "+pageNum+"   \n" +
                "  )\n" +
                "WHERE RN > "+ rows+"\n";
        return pagingSql;
    }

    /**
     * 生成分页sql
     * @param page 当前页数
     * @param pageSize 一页显示的数量
     * @param sql  需分页的业务sql
     * @return
     */
    public static String  pagingMethod2(int page,int pageSize,String sql){
        int pageNum = page*pageSize;
        int rows = (page-1)*pageSize;
        String pagingSql = "SELECT * FROM \n" +
                "( \n" +
                "    SELECT A.*,ROWNUM RN \n" +
                "    FROM ( "+ sql +" ) A \n" +
                ") \n"+
                "WHERE RN > "+ rows + " AND  RN <= "+pageNum;
        return pagingSql;
    }

    /**
     * 生成分页sql
     * @param page 当前页数
     * @param pageSize 一页显示的数量
     * @param list  需分页的业务list
     * @return
     */
    public static List<Object>  pagingMethod3(int page, int pageSize, List<Object> list){

        if (page == 0 && pageSize == 0) {
            pageSize = 10;
            page = 1;
        }
        int pageNum = page*pageSize;
        int startrow = (page-1)*pageSize;
        List<Object> retList = new ArrayList<>();
        int chksize =0;

        if (pageNum > list.size()){
            chksize = list.size();
        }else{
            chksize =pageNum;
        }
        for (int i = startrow;i<chksize;i++ ) {

            Object it = list.get(i);
            retList.add(it);
        }

        return retList;
    }
}
