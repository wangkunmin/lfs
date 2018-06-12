package com.cscs.listedfacesys.dto;


import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Created by hj on 2018/2/28.
 */
public class CompanyReport extends BaseOutData {

    public List<Object> getCompyReport() {
        return compyReport;
    }

    public void setCompyReport(List<Object> compyReport) {
        this.compyReport = compyReport;
    }

    List<Object> compyReport;


}
