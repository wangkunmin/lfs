package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.CompySuperviseData;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserSuperviseService {



    /**
     * 根据公司名称模糊查询
     * @param companyNm
     * @return
     */

    public List<Object> findCompySuperviseInfo(int page ,int pageSize ,String companyNm);
    public int findCompySuperviseInfoCount(String companyNm);




    /**
     * 操作监护人表
     */
    public void operationSupervise(CompySuperviseData inData) throws Exception ;


}
