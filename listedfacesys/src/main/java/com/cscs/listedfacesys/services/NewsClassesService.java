package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.NegativeNewsInData;
import com.cscs.listedfacesys.dto.NewsWarningInData;
import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Created by hj on 2018/02/01.
 * 新闻类相关
 */
public interface NewsClassesService {


    /**
     * 负面时间跟踪
     * @param page 页数
     * @param pageSize 页量
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @throws Exception
     */
    public List<Object> getLastingBondViolationNews(int page, int pageSize,String startDate,String endDate) throws Exception;

    /**
     * 负面跟踪新闻总数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @throws Exception
     */
    public int getLastingBondViolationNewsCount(String startDate,String endDate) throws Exception;

    /**
     * 查询公司名称
     * @param compyId
     * @return
     */
    public List<Object> findCompanyNm(String compyId);
    /**
     * 热点新闻
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchart(TendencyChartInData inData) throws  Exception;



    /**
     * 负面新闻跟踪详情
     * @param compyId
     * @param newsCode
     * @return
     * @throws Exception
     */
    public List<Object> findNewsContent(long compyId, String newsCode) throws Exception;

    /**
     * 新闻事件
     * @param compyId  公司ID
     * @param newsCode 新闻ID
     * @return
     */
    public List<Object> findNewsType(long compyId, String newsCode);

    /**
     * 舆情风险趋势图
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchartForUserAttention(TendencyChartInData inData) throws  Exception;




    /**
     * 新闻舆情风险
     * @param page 页数
     * @param pageSize 页量
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    public List<Object> getNewsRiskForUserAttention(int page, int pageSize,String startDate,String endDate,String userId) throws Exception;

    /**
     * 新闻舆情风险总数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    public int getNewsRiskForUserAttentionCount(String startDate,String endDate,String userId) throws Exception;

    /**
     * 新闻事件趋势图
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchartByCompanyId(TendencyChartInData inData) throws  Exception;


    /**
     * 舆情事件
     * @param
     * @return
     * @throws Exception
     */
    public List<Object> getNewsRiskByCompanyNm(NewsWarningInData inData) throws Exception;

    /**
     * 舆情事件总数
     * @param
     * @return
     * @throws Exception
     */
    public int getNewsRiskByCompanyNmCount(NewsWarningInData inData) throws Exception;

    /**
     * 舆情事件
     * @return
     * @throws Exception
     */
    public BaseOutData findNewsFromSolr(NewsWarningInData inData);

}
