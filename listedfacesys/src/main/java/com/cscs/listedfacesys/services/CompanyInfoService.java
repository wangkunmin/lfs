package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.SearchCompletion;

import java.util.List;
import java.util.Set;

/**
 * Created by hj on 2018/2/24.
 */
public interface CompanyInfoService {

    //公司基础信息(A版改版)
    List<Object> getCompanyBasicInfo(Long id);

    //实际控制人
    String getAffilparty(Long id);

    //企业董事高管人员
    List<Object> findCompyManagelevel(Long id);

    //企业股东前10取得
    List<Object> findShareholderTop10(Long id);

    //股权质押取得（不包含10大股东）
    List<Object> findCompyFrozemshare(Long id);

    List<Object> companyBasicInfo(String companyId);

    //查询公司信息
    List<Object> queryCompanyInfo(String companyNmOrSecurityCd);
    //查询用户未关注公司列表
    List<Object> queryNotAttentionCompanyInfo(String userId,String companyNmOrSecurityCd);

    //获取企业是主板还是新三板 按照申万行业分类的二级行业;新三板企业：按证监会行业分类三级行业分类
    List<Object> findIndustryFlg(Long id);

    // 获取申万一级行业和二级行业
    List<Object> findSwIndustry(Long id, String cd);

    // 判断是否属于深圳市上市公司
    List<Object> findExistCompy(Long companyId);
}

