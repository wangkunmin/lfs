package com.cscs.listedfacesys.dto;

import java.util.List;

/**
 * Created by hj on 2018/3/1.
 */
public class CompanyShareholderOutData {
    private List<CompanyShareholderInfoData> companyShareholderList;

    public List<CompanyShareholderInfoData> getCompanyShareholderList() {
        return companyShareholderList;
    }

    public void setCompanyShareholderList(List<CompanyShareholderInfoData> companyShareholderList) {
        this.companyShareholderList = companyShareholderList;
    }
}
