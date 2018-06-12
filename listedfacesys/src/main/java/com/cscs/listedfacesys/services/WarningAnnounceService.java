package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Create by wzy on 2018/2/1
 * 公告类相关
 */
public interface WarningAnnounceService {

    /**
     * 查询第一版面监测预警TOP10公司ID
     * @return
     */
    public List<Object> getWarningTop10(String dateStart, String dateEnd);


    /**
     * 查询第一版面监测预警TOP10公司相关公告详情
     * @param compyList
     * @return
     */
    public List<Object> getWarningTop10Content(String compyList, String dateStart, String dateEnd);


    /**
     * 查询第一版面最近七年相关公告条数
     * @return
     */
    public List<Object> getWarningYearCount(String startDate, String endDate);

    /**
     * 查询监管人员
     * @param compyList
     * @return
     */
    public List<Object> getSupervisor(String compyList);

    /**
     * 查询2画面监测预警TOP10公司ID
     * @return
     */
    public List<Object> getUserWarningTop10(Long userId, String dateStart, String dateEnd);

    /**
     * 查询2画面监测预警TOP10公告详情
     * @return
     */
    public List<Object> getUWTop10Content(Long userId, String compyList, String dateStart, String dateEnd);

    /**
     * 查询2画面数据量总数
     * @return
     */
    public List<Object> getUWTop10Count(Long userId, String compyList, String dateStart, String dateEnd);

    /**
     * 查询事件公告列表
     * @return
     */
    public List<Object> getAccounceEvent(Long userId, String dateStart, String dateEnd, int page, int pageSize,int eventType,int negativeType,int importanceType);

    /**
     * 查询事件公告数据量总数
     * @return
     */
    public List<Object> getAECount(Long userId, String dateStart, String dateEnd,int eventType,int negativeType,int importanceType);

    /**
     * 查询单个公司事件公告列表
     * @return
     */
    public List<Object> getSingleCompanyAE(Long userId, Long companyId, String dateStart, String dateEnd, int page, int pageSize);

    /**
     * 查询单个公司事件公告数据量总数
     * @return
     */
    public List<Object> getSCAECount(Long userId, Long companyId, String dateStart, String dateEnd);

    /**
     * 查询单个公司事件公告列表
     * @param infoCd
     * @return
     */
    public String getAnnoumceUrl(String infoCd);

    /**
     * 第三画面风险预警
     * @return
     */
    public List<Object> getThreeSideWarning(Long companyId, int type, String dateStart, String dateEnd);



    /**
     * 查询第一版面监测预警新闻公司ID
     * @return
     */
    public List<Object> getWarningTopfornews(String dateStart, String dateEnd);

    /**
     * 查询第一版面监测预警TOP10公司相关新闻详情
     * @param compyList
     * @return
     */
    public List<Object> getWarningTop10Contentfornews(String compyList, String dateStart, String dateEnd);

    /**
     * 查询第一版面最近七年相关新闻条数
     * @return
     */
    public List<Object> getWarningYearCountfornews(String startDate, String endDate);

    /**
     * 第三画面新闻风险预警
     * @return
     */
    public List<Object> getThreeSideWarningfornews(Long companyId, int type, String dateStart, String dateEnd);

    /**
     * 查询第二版面监测预警新闻公司ID
     * @return
     */
    public List<Object>  getUserWarningTop10fornews(Long userId, String dateStart, String dateEnd);

    /**
     * 查询第二版面监测预警TOP10公司相关新闻详情
     * @param compyList
     * @return
     */
    public List<Object> getUWTop10Contentfornews(Long userId, String compyList, String dateStart, String dateEnd);

    /**
     * 查询负面事件新闻的公司ID和新闻数
     * @return
     */
    public List<Object> getNegativeForNews(String dateStart, String dateEnd);
    /**
     * 查询负面事件新闻的详细内容
     * @return
     */
    public List<Object> getNewsNegativeContent(String dateStart, String dateEnd);
    /**
     * 查询负面事件公告的的详细内容
     * @return
     */
    public List<Object> getNegativeBulletinBoardContent(String dateStart, String dateEnd);

    /**
     * 查询负面事件公告的公司ID和公告数
     * @return
     */
    public List<Object> getNegativeForBulletinBoard(String dateStart, String dateEnd);

    /**
     * 展台预警新闻各种合计数
     * @return
     */
    public List<Object> getCompyWarningCountfornews(Long companyId,String dateStart, String dateEnd);

    /**
     * 展台预警公告各种合计数
     * @return
     */
    public List<Object> getCompyWarningCount(Long companyId,String dateStart, String dateEnd);

    public List<Object> getCompanyRisk(String dateStart,String dateEnd);

    public List<Object> getCompyRiskCount();

    public List<Object> getCompanyRiskById(String companyId);
}
