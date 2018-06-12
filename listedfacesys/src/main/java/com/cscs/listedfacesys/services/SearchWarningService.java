package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.SearchCompletion;
import com.cscs.listedfacesys.dto.SearchOutObj;

import java.io.IOException;
import java.util.List;

/**
 * create by wuzy on 2018/03/14
 * 关键字查询
 */
public interface SearchWarningService {

    /**
     * 基础搜索
     */
    List<Object> basicSearch(String keyword, int page);

    int basicSearchCount(String keyword);

    //查找关系（公司）
    List<Object> findRelationShipByCompy(String keyword);

    //查找关系（个人）
    List<String> findRelationShipByName(String keyword);

    //title上查找公司功能
    List<SearchCompletion> findCompanySearchinfosForTitle(String keyword);
}
