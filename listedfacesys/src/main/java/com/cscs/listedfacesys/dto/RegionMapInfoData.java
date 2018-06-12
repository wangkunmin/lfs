package com.cscs.listedfacesys.dto;

/**
 * create by wzy on 2018/02/07
 */
public class RegionMapInfoData {

    private String regionName;      //区域名称
    private int mainboardCount;     //主板数量
    private int mediumCpCount;      //中小板数量
    private int businessCount;      //创业板数量
    private double mainratio;       //同比主板增长比率
    private double mediumratio;       //同比中小板增长比率
    private double busiratio;       //同比创业板比率
    private String date;        //日期（YYYYMM）

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getMainboardCount() {
        return mainboardCount;
    }

    public void setMainboardCount(int mainboardCount) {
        this.mainboardCount = mainboardCount;
    }

    public int getMediumCpCount() {
        return mediumCpCount;
    }

    public void setMediumCpCount(int mediumCpCount) {
        this.mediumCpCount = mediumCpCount;
    }

    public int getBusinessCount() {
        return businessCount;
    }

    public void setBusinessCount(int businessCount) {
        this.businessCount = businessCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMainratio() {
        return mainratio;
    }

    public void setMainratio(double mainratio) {
        this.mainratio = mainratio;
    }

    public double getMediumratio() {
        return mediumratio;
    }

    public void setMediumratio(double mediumratio) {
        this.mediumratio = mediumratio;
    }

    public double getBusiratio() {
        return busiratio;
    }

    public void setBusiratio(double busiratio) {
        this.busiratio = busiratio;
    }
}
