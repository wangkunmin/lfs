package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/3/1.
 */
public class CompanyShareholderInfoData {

    private String sharehdname;//股东名字
    private String sharehdType;//股东类型
    private String sharehdRatio;//持股比例
    private String frozenTotalRatio;//未解质总额
    private String sharehdId;//股东ID
    private String sharehdNum;//持股数量

    public String getFrozenTotalRatio() {
        return frozenTotalRatio;
    }

    public void setFrozenTotalRatio(String frozenTotalRatio) {
        this.frozenTotalRatio = frozenTotalRatio;
    }

    public String getSharehdname() {
        return sharehdname;
    }

    public void setSharehdname(String sharehdname) {
        this.sharehdname = sharehdname;
    }

    public String getSharehdType() {
        return sharehdType;
    }

    public void setSharehdType(String sharehdType) {
        this.sharehdType = sharehdType;
    }

    public String getSharehdRatio() {
        return sharehdRatio;
    }

    public void setSharehdRatio(String sharehdRatio) {
        this.sharehdRatio = sharehdRatio;
    }

    public String getSharehdId() {
        return sharehdId;
    }

    public void setSharehdId(String sharehdId) {
        this.sharehdId = sharehdId;
    }

    public String getSharehdNum() {
        return sharehdNum;
    }

    public void setSharehdNum(String sharehdNum) {
        this.sharehdNum = sharehdNum;
    }
}
