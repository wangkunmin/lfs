package com.cscs.listedfacesys.dto;

/**
 * Created by wzy 2018/3/1
 */
public class WarningRiskInfo2Data {

    private Integer risk1;       //治理风险数量
    private Integer risk2;       //财务风险数量
    private Integer risk3;       //经营风险数量
    private Integer risk4;       //市场风险数量
    private Integer risk5;       //法律法规风险数量
    private int dataMonth;        //天日期

    public WarningRiskInfo2Data() {
        this.risk1 = 0;
        this.risk2 = 0;
        this.risk3 = 0;
        this.risk4 = 0;
        this.risk5 = 0;
    }

    public WarningRiskInfo2Data(Integer risk1, Integer risk2, Integer risk3, Integer risk4, Integer risk5, int dataMonth) {
        super();
        this.risk1 = risk1;
        this.risk2 = risk2;
        this.risk3 = risk3;
        this.risk4 = risk4;
        this.risk5 = risk5;
        this.dataMonth = dataMonth;
    }

    public int getDataMonth() {
        return dataMonth;
    }

    public void setDataMonth(int dataMonth) {
        this.dataMonth = dataMonth;
    }

    public Integer getRisk1() {
        return risk1;
    }

    public void setRisk1(Integer risk1) {
        this.risk1 = risk1;
    }

    public Integer getRisk2() {
        return risk2;
    }

    public void setRisk2(Integer risk2) {
        this.risk2 = risk2;
    }

    public Integer getRisk3() {
        return risk3;
    }

    public void setRisk3(Integer risk3) {
        this.risk3 = risk3;
    }

    public Integer getRisk4() {
        return risk4;
    }

    public void setRisk4(Integer risk4) {
        this.risk4 = risk4;
    }

    public Integer getRisk5() {
        return risk5;
    }

    public void setRisk5(Integer risk5) {
        this.risk5 = risk5;
    }
}
