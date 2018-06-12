package com.cscs.listedfacesys.dto;

import java.util.List;

/**
 * Created by wzy 2018/2/6
 */
public class WarningRiskOutData {

    private List<WarningRiskInfoData> warningRiskInfoDataList; //每年的数据量
    private int risk1Count;     //治理风险总数量
    private int risk2Count;     //财务风险总数量
    private int risk3Count;     //经营风险总数量
    private int risk4Count;     //市场风险总数量
    private int risk5Count;     //法律法规风险总数量
    private String date;        //年日期（YYYY）

    public List<WarningRiskInfoData> getWarningRiskInfoDataList() {
        return warningRiskInfoDataList;
    }

    public void setWarningRiskInfoDataList(List<WarningRiskInfoData> warningRiskInfoDataList) {
        this.warningRiskInfoDataList = warningRiskInfoDataList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRisk1Count() {
        return risk1Count;
    }

    public void setRisk1Count(int risk1Count) {
        this.risk1Count = risk1Count;
    }

    public int getRisk2Count() {
        return risk2Count;
    }

    public void setRisk2Count(int risk2Count) {
        this.risk2Count = risk2Count;
    }

    public int getRisk3Count() {
        return risk3Count;
    }

    public void setRisk3Count(int risk3Count) {
        this.risk3Count = risk3Count;
    }

    public int getRisk4Count() {
        return risk4Count;
    }

    public void setRisk4Count(int risk4Count) {
        this.risk4Count = risk4Count;
    }

    public int getRisk5Count() {
        return risk5Count;
    }

    public void setRisk5Count(int risk5Count) {
        this.risk5Count = risk5Count;
    }
}
