package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.math.BigDecimal;

/**
 * Created by hj on 2018/2/24.
 */
public class CompyFinanceOutData extends BaseOutData {

    public BigDecimal getSumAsset() {
        return sumAsset;
    }

    public void setSumAsset(BigDecimal sumAsset) {
        this.sumAsset = sumAsset;
    }

    public BigDecimal getSumLiab() {
        return sumLiab;
    }

    public void setSumLiab(BigDecimal sumLiab) {
        this.sumLiab = sumLiab;
    }

    public BigDecimal getSumLliab() {
        return sumLliab;
    }

    public void setSumLliab(BigDecimal sumLliab) {
        this.sumLliab = sumLliab;
    }

    public BigDecimal getOperateReve() {
        return operateReve;
    }

    public void setOperateReve(BigDecimal operateReve) {
        this.operateReve = operateReve;
    }

    public BigDecimal getOperateExp() {
        return operateExp;
    }

    public void setOperateExp(BigDecimal operateExp) {
        this.operateExp = operateExp;
    }

    public BigDecimal getOperateProfit() {
        return operateProfit;
    }

    public void setOperateProfit(BigDecimal operateProfit) {
        this.operateProfit = operateProfit;
    }

    public BigDecimal getSumProfit() {
        return sumProfit;
    }

    public void setSumProfit(BigDecimal sumProfit) {
        this.sumProfit = sumProfit;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getNetOperateCashflow() {
        return netOperateCashflow;
    }

    public void setNetOperateCashflow(BigDecimal netOperateCashflow) {
        this.netOperateCashflow = netOperateCashflow;
    }

    public BigDecimal getNetInvCashflow() {
        return netInvCashflow;
    }

    public void setNetInvCashflow(BigDecimal netInvCashflow) {
        this.netInvCashflow = netInvCashflow;
    }

    public BigDecimal getNetFinaCashflow() {
        return netFinaCashflow;
    }

    public void setNetFinaCashflow(BigDecimal netFinaCashflow) {
        this.netFinaCashflow = netFinaCashflow;
    }

    public BigDecimal getNicashEqui() {
        return nicashEqui;
    }

    public void setNicashEqui(BigDecimal nicashEqui) {
        this.nicashEqui = nicashEqui;
    }

    public Long getRptDtBak() {
        return rptDtBak;
    }

    public void setRptDtBak(Long rptDtBak) {
        this.rptDtBak = rptDtBak;
    }

    //报表日期
    private Long rptDtBak;

    //资产总计
    private BigDecimal sumAsset;

    //负债合计
    private BigDecimal sumLiab;

    //流动负债合计
    private BigDecimal sumLliab;

    //营业收入
    private BigDecimal operateReve;

    //营业成本
    private BigDecimal operateExp;

    //营业利润
    private BigDecimal operateProfit;

    //利润总额
    private BigDecimal sumProfit;

    //净利润
    private BigDecimal netProfit;

    //经营活动产生的现金流量净额
    private BigDecimal netOperateCashflow;

    //投资活动产生的现金流量净额
    private BigDecimal netInvCashflow;

    //筹资活动产生的现金流量净额
    private BigDecimal netFinaCashflow;

    //现金及现金等价物净增加额
    private BigDecimal nicashEqui;

    public Long getRptTimetypeCd() {
        return rptTimetypeCd;
    }

    public void setRptTimetypeCd(Long rptTimetypeCd) {
        this.rptTimetypeCd = rptTimetypeCd;
    }

    //报告期
    private Long rptTimetypeCd;
}
