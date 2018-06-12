package com.cscs.listedfacesys.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by hj on 2018/4/11
 */
public class CompanyInfoMapOutData {
    //公司ID
    private String companyId;
    //名称
    private String companyNm;
    //公司简称
    private String companySnm;
    //上市代码
    private String securityCd;
    //注册地址
    private String regAddr;
    //公司坐标[x,y]
    private float[] postion;
    //区名
    private String regionName;
    //公司评级 1:高风险，2：次高风险，3：关注,4：低风险
    private String rating;
    //负面事件数
    private int negativeCount;
    //风险概览
    private String riskContent;
    //按事件分类的负面事件详细内容
    private List<Map<String,String>> typeMap;
    //风险类型
    private List<Integer> riskType;
    //重要性
    private double relevance;
    //风险标题
    private List<Map<String,String>> title;
    //更新时间
    private String updtDt;

    //财务风险数
    private int riskCaiWuCount;
    //治理风险数
    private int riskZhiLiCount;
    //信息披露数
    private int riskXinXiCount;
    //其他数
    private int riskQiTaCount;
    //事件类型1：公告，2：新闻
    private String type;
    //公告表IDs
    private String announceCds;
    //新闻表IDs
    private String newsCds;
    //是否闪烁
    private boolean isTwinkle;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public float[] getPostion() {
        return postion;
    }

    public void setPostion(float[] postion) {
        this.postion = postion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(int negativeCount) {
        this.negativeCount = negativeCount;
    }

    public String getRiskContent() {
        return riskContent;
    }

    public void setRiskContent(String riskContent) {
        this.riskContent = riskContent;
    }

    public List<Map<String, String>> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(List<Map<String, String>> typeMap) {
        this.typeMap = typeMap;
    }

    public List<Integer> getRiskType() {
        return riskType;
    }

    public void setRiskType(List<Integer> riskType) {
        this.riskType = riskType;
    }

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    public List<Map<String, String>> getTitle() {
        return title;
    }

    public void setTitle(List<Map<String, String>> title) {
        this.title = title;
    }

    public String getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnnounceCds() {
        return announceCds;
    }

    public void setAnnounceCds(String announceCds) {
        this.announceCds = announceCds;
    }

    public String getNewsCds() {
        return newsCds;
    }

    public void setNewsCds(String newsCds) {
        this.newsCds = newsCds;
    }

    public boolean isTwinkle() {
        return isTwinkle;
    }

    public void setTwinkle(boolean twinkle) {
        isTwinkle = twinkle;
    }
}

