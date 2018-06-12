package com.cscs.listedfacesys.dto;

import java.util.List;

/**
 * Created by hj on 2018/4/11
 */
public class RegionMapOutData {
    //区名
    private String regionName;
    // 该区下的所有公司信息
    private List<CompanyInfoMapOutData> companyInfos;
    //高风险数
    private int highRiskCount;
    //次高风险数（中风险）
    private int mediumRiskCount;
    //关注数
    private int focusCount;
    //低风险数
    private int lowRiskCount;
    //预警公司数
    private int waringCompanyCount;
    //负面事件数
    private int negativeCount;
    //财务风险数
    private int riskCaiWuCount;
    //治理风险数
    private int riskZhiLiCount;
    //信息披露数
    private int riskXinXiCount;
    //其他数
    private int riskQiTaCount;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<CompanyInfoMapOutData> getCompanyInfos() {
        return companyInfos;
    }

    public void setCompanyInfos(List<CompanyInfoMapOutData> companyInfos) {
        this.companyInfos = companyInfos;
    }

    public int getHighRiskCount() {
        return highRiskCount;
    }

    public void setHighRiskCount(int highRiskCount) {
        this.highRiskCount = highRiskCount;
    }

    public int getMediumRiskCount() {
        return mediumRiskCount;
    }

    public void setMediumRiskCount(int mediumRiskCount) {
        this.mediumRiskCount = mediumRiskCount;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public int getLowRiskCount() {
        return lowRiskCount;
    }

    public void setLowRiskCount(int lowRiskCount) {
        this.lowRiskCount = lowRiskCount;
    }

    public int getWaringCompanyCount() {
        return waringCompanyCount;
    }

    public void setWaringCompanyCount(int waringCompanyCount) {
        this.waringCompanyCount = waringCompanyCount;
    }

    public int getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(int negativeCount) {
        this.negativeCount = negativeCount;
    }

    public int getRiskCaiWuCount() {
        return riskCaiWuCount;
    }

    public void setRiskCaiWuCount(int riskCaiWuCount) {
        this.riskCaiWuCount = riskCaiWuCount;
    }

    public int getRiskZhiLiCount() {
        return riskZhiLiCount;
    }

    public void setRiskZhiLiCount(int riskZhiLiCount) {
        this.riskZhiLiCount = riskZhiLiCount;
    }

    public int getRiskXinXiCount() {
        return riskXinXiCount;
    }

    public void setRiskXinXiCount(int riskXinXiCount) {
        this.riskXinXiCount = riskXinXiCount;
    }

    public int getRiskQiTaCount() {
        return riskQiTaCount;
    }

    public void setRiskQiTaCount(int riskQiTaCount) {
        this.riskQiTaCount = riskQiTaCount;
    }
}
