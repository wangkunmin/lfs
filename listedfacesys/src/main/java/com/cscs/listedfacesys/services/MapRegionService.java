package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.NegativeInfoRecordInData;

import java.util.List;

/**
 * create by wzy on 2018/02/07
 * 地图类查询
 */
public interface MapRegionService {



    //获取深证市上市公司信息
    public List<Object> getRegionCompanyInfo();

    //获取用户当日所查看的所有负面事件信息
    public List<Object> getNegativeInfoByUserId(String date,String userId);

    //将用户查看过的该家公司的负面事件记录到记录表中
    public int insertNegativeInfoRecord(NegativeInfoRecordInData inData);

}
