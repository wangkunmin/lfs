package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Created by hj on 2018/2/26.
 * 搜索返回的Dto
 */
public class SearchOutObj {


    List<?> list;

    //搜索输出
    private   List<CompanySearch> companySearcheslist;
    //总条数
    private Integer totalNum;

    private int count;

    public List<?> getList() {
        return list;
    }

    public void seList(List<?> list) {
        this.list = list;
    }

    public List<CompanySearch> getCompanySearcheslist() {
        return companySearcheslist;
    }

    public void setCompanySearcheslist(List<CompanySearch> companySearcheslist) {
        this.companySearcheslist = companySearcheslist;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
