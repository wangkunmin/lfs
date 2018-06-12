package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.CompanyBaseInfo;
import com.cscs.listedfacesys.dto.CompanyRiskInfoData;

import java.util.List;


/**
 * 风险评级后台管理
 * create by hj on 2018/04/27
 */
public interface RiskRateManageService {

    /**
     * 查询深圳所有上市公司风险评级信息
     * @return
     */
    public List<Object> findAllCompyRiskRateInfo(String companyId,String companyNm,int page,int pageSize) throws Exception;
    /**
     * 查询深圳所有上市公司风险评级信息条数
     * @return
     */
    public int findAllCompyRiskRateInfoCount(String companyNm) throws Exception;


    /**
     * 根据主键pkCompanyId,判断是修改还是新增
     * @return
     */
    public int UpdateOrAddForRiskRate(String companyId,String pkCompanyId,int rating,String riskContent) throws Exception;

    /**
     * 查询深圳所有上市公司风险类型信息
     * @return
     */
    public List<Object> findCompanyRiskInfo(String riskId,String companyNm,int page,int pageSize) throws Exception;

    /**
     * 查询深圳所有上市公司风险类型信息条数
     * @return
     */
    public int findCompanyRiskInfoCount(String companyNm) throws Exception;

    /**
     * 添加企业风险信息
     * @return
     */
    public int addCompanyRiskInfo(CompanyRiskInfoData data);

    /**
     * 修改企业风险信息
     * @return
     */
    public int updateCompanyRiskInfo(CompanyRiskInfoData data);

    /**
     * 删除企业风险信息
     * @return
     */
    public int delCompanyRiskInfo(String compyRiskId);




    /* wkm 20180507 */
    /**
     * 查询深圳所有上市公司风险类型信息条数
     * @return
     */
    public int findCompanyBaseInfoCount(String companyNm) throws Exception;
    /**
     * 查询深圳所有上市公司基本信息
     * @return
     */
    public List<Object> findCompanyBaseInfo(String companyId,String companyNm,int page,int pageSize) throws Exception;
    /**
     * 修改企业基本信息
     * @return
     */
    public int upDateCompanyBaseInfo(CompanyBaseInfo data);
}
