package com.cscs.listedfacesys.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by hj on 2018/2/27.
 */
@Entity
@Table(name = "COMPY_CASHFLOW", schema = "CS_FACEBOOK_1", catalog = "")
public class CompyCashflow {
    private Long compyCashflowSid;
    private Date firstNoticeDt;
    private Date latestNoticeDt;
    private Long companyId;
    private Long rptDt;
    private Long startDt;
    private Long endDt;
    private Long rptTimetypeCd;
    private Long combineTypeCd;
    private Long rptSrctypeId;
    private Long dataAjustType;
    private Long dataType;
    private Long isPublicRpt;
    private Long companyType;
    private String currency;
    private Long salegoodsServiceRec;
    private Long taxReturnRec;
    private Long otherOperateRec;
    private Long niDeposit;
    private Long niborrowFromCbank;
    private Long niborrowFromFi;
    private Long premiumRec;
    private Long nidispTradeFasset;
    private Long nidispSaleableFasset;
    private Long niborrowFund;
    private Long nibuybackFund;
    private Long operateFlowinOther;
    private Long operateFlowinBalance;
    private Long sumOperateFlowin;
    private Long buygoodsServicePay;
    private Long employeePay;
    private Long taxPay;
    private Long otherOperatEpay;
    private Long niloanAdvances;
    private Long nidepositIncbankfi;
    private Long indemnityPay;
    private Long intandcommPay;
    private Long operateFlowoutOther;
    private Long operateFlowoutBalance;
    private Long sumOperateFlowout;
    private Long operateFlowOther;
    private Long operateFlowBalance;
    private BigDecimal netOperateCashflow;
    private Long disposalInvRec;
    private Long invIncomeRec;
    private Long dispFilassetRec;
    private Long dispSubsidiaryRec;
    private Long otherInvrec;
    private Long invFlowinOther;
    private Long invFlowinBalance;
    private Long sumInvFlowin;
    private Long buyFilassetPay;
    private Long invPay;
    private Long getSubsidiaryPay;
    private Long otherInvPay;
    private Long nipledgeLoan;
    private Long invFlowoutOther;
    private Long invFlowoutBalance;
    private Long sumInvFlowout;
    private Long invFlowOther;
    private Long invCashflowBalance;
    private BigDecimal netInvCashflow;
    private Long acceptInvRec;
    private Long loanRec;
    private Long otherFinaRec;
    private Long issueBondRec;
    private Long niinsuredDepositInv;
    private Long finaFlowinOther;
    private Long finaFlowinBalance;
    private Long sumFinaFlowin;
    private Long repayDebtPay;
    private Long diviProfitorintPay;
    private Long otherFinaPay;
    private Long finaFlowoutOther;
    private Long finaFlowoutBalance;
    private Long sumFinaFlowout;
    private Long finaFlowOther;
    private Long finaFlowBalance;
    private BigDecimal netFinaCashflow;
    private Long effectExchangeRate;
    private Long nicashEquiOther;
    private Long nicashEquiBalance;
    private BigDecimal nicashEqui;
    private Long cashEquiBeginning;
    private Long cashEquiEnding;
    private Long netProfit;
    private Long assetDevalue;
    private Long fixedAssetEtcdepr;
    private Long intangibleAssetAmor;
    private Long ltdeferExpAmor;
    private Long deferExpReduce;
    private Long drawingExpAdd;
    private Long dispFilassetLoss;
    private Long fixedAssetLoss;
    private Long fvalueLoss;
    private Long financeExp;
    private Long invLoss;
    private Long deferTaxassetReduce;
    private Long deferTaxliabAdd;
    private Long inventoryReduce;
    private Long operateRecReduce;
    private Long operatePayAdd;
    private Long inoperateFlowOther;
    private Long inoperateFlowBalance;
    private Long innetOperateCashflow;
    private Long debtToCapital;
    private Long cbOneyear;
    private Long finaleaseFixedAsset;
    private Long cashEnd;
    private Long cashBegin;
    private Long equiEnd;
    private Long equiBegin;
    private Long innicashEquiOther;
    private Long innicashEquiBalance;
    private Long innicashEqui;
    private Long other;
    private Long subsidiaryAccept;
    private Long subsidiaryPay;
    private Long diviPay;
    private Long intandcommRec;
    private Long netRirec;
    private Long nilendFund;
    private Long deferTax;
    private Long deferIncomeAmor;
    private Long exchangeLoss;
    private Long fixandestateDepr;
    private Long fixedAssetDepr;
    private Long tradefAssetReduce;
    private Long ndloanAdvances;
    private Long reducePledgetDeposit;
    private Long addPledgetDeposit;
    private Long buySubsidiaryPay;
    private Long cashEquiendingOther;
    private Long cashEquiendingBalance;
    private Long ndDepositincBankfi;
    private Long niborrowSellBuyback;
    private Long ndlendBuySellback;
    private Long netCd;
    private Long nitradeFliab;
    private Long ndtradeFasset;
    private Long dispMassetRec;
    private Long cancelLoanRec;
    private Long ndborrowFromCbank;
    private Long ndfidePosit;
    private Long ndissueCd;
    private Long nilendSellBuyback;
    private Long ndborrowSellBuyback;
    private Long nitradeFasset;
    private Long ndtradeFliab;
    private Long buyFinaleaseassetPay;
    private Long niaccountRec;
    private Long issueCd;
    private Long addshareCapitalRec;
    private Long issueShareRec;
    private Long bondIntpay;
    private Long niotherFinainstru;
    private Long agentTradeSecurityrec;
    private Long uwsecurityRec;
    private Long buysellbackFassetRec;
    private Long agentUwsecurityRec;
    private Long nidirectInv;
    private Long nitradeSettlement;
    private Long buysellbackFassetPay;
    private Long nddispTradeFasset;
    private Long ndotherFinaInstr;
    private Long ndborrowFund;
    private Long nddirectInv;
    private Long ndtradeSettlement;
    private Long ndbuybackFund;
    private Long agenttradeSecurityPay;
    private Long nddispSaleableFasset;
    private Long nisellBuyback;
    private Long ndbuySellback;
    private Long nettradeFassetRec;
    private Long netRipay;
    private Long ndlendFund;
    private Long nibuySellback;
    private Long ndsellBuyback;
    private Long ndinsuredDepositInv;
    private Long nettradeFassetPay;
    private Long niinsuredPledgeLoan;
    private Long dispSubsidiaryPay;
    private Long netsellBuybackFassetrec;
    private Long netsellBuybackFassetpay;
    private String remark;
    private String chkStatus;
    private Long isdel;
    private String srcCompanyCd;
    private String srcid;
    private String srcCd;
    private Timestamp updtDt;

    @Id
    @Column(name = "COMPY_CASHFLOW_SID", nullable = false, precision = 0)
    public Long getCompyCashflowSid() {
        return compyCashflowSid;
    }

    public void setCompyCashflowSid(Long compyCashflowSid) {
        this.compyCashflowSid = compyCashflowSid;
    }

    @Basic
    @Column(name = "FIRST_NOTICE_DT", nullable = true)
    public Date getFirstNoticeDt() {
        return firstNoticeDt;
    }

    public void setFirstNoticeDt(Date firstNoticeDt) {
        this.firstNoticeDt = firstNoticeDt;
    }

    @Basic
    @Column(name = "LATEST_NOTICE_DT", nullable = true)
    public Date getLatestNoticeDt() {
        return latestNoticeDt;
    }

    public void setLatestNoticeDt(Date latestNoticeDt) {
        this.latestNoticeDt = latestNoticeDt;
    }

    @Basic
    @Column(name = "COMPANY_ID", nullable = false, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "RPT_DT", nullable = false, precision = 0)
    public Long getRptDt() {
        return rptDt;
    }

    public void setRptDt(Long rptDt) {
        this.rptDt = rptDt;
    }

    @Basic
    @Column(name = "START_DT", nullable = true, precision = 0)
    public Long getStartDt() {
        return startDt;
    }

    public void setStartDt(Long startDt) {
        this.startDt = startDt;
    }

    @Basic
    @Column(name = "END_DT", nullable = true, precision = 0)
    public Long getEndDt() {
        return endDt;
    }

    public void setEndDt(Long endDt) {
        this.endDt = endDt;
    }

    @Basic
    @Column(name = "RPT_TIMETYPE_CD", nullable = true, precision = 0)
    public Long getRptTimetypeCd() {
        return rptTimetypeCd;
    }

    public void setRptTimetypeCd(Long rptTimetypeCd) {
        this.rptTimetypeCd = rptTimetypeCd;
    }

    @Basic
    @Column(name = "COMBINE_TYPE_CD", nullable = false, precision = 0)
    public Long getCombineTypeCd() {
        return combineTypeCd;
    }

    public void setCombineTypeCd(Long combineTypeCd) {
        this.combineTypeCd = combineTypeCd;
    }

    @Basic
    @Column(name = "RPT_SRCTYPE_ID", nullable = true, precision = 0)
    public Long getRptSrctypeId() {
        return rptSrctypeId;
    }

    public void setRptSrctypeId(Long rptSrctypeId) {
        this.rptSrctypeId = rptSrctypeId;
    }

    @Basic
    @Column(name = "DATA_AJUST_TYPE", nullable = true, precision = 0)
    public Long getDataAjustType() {
        return dataAjustType;
    }

    public void setDataAjustType(Long dataAjustType) {
        this.dataAjustType = dataAjustType;
    }

    @Basic
    @Column(name = "DATA_TYPE", nullable = false, precision = 0)
    public Long getDataType() {
        return dataType;
    }

    public void setDataType(Long dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "IS_PUBLIC_RPT", nullable = true, precision = 0)
    public Long getIsPublicRpt() {
        return isPublicRpt;
    }

    public void setIsPublicRpt(Long isPublicRpt) {
        this.isPublicRpt = isPublicRpt;
    }

    @Basic
    @Column(name = "COMPANY_TYPE", nullable = false, precision = 0)
    public Long getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Long companyType) {
        this.companyType = companyType;
    }

    @Basic
    @Column(name = "CURRENCY", nullable = true, length = 6)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "SALEGOODS_SERVICE_REC", nullable = true, precision = 4)
    public Long getSalegoodsServiceRec() {
        return salegoodsServiceRec;
    }

    public void setSalegoodsServiceRec(Long salegoodsServiceRec) {
        this.salegoodsServiceRec = salegoodsServiceRec;
    }

    @Basic
    @Column(name = "TAX_RETURN_REC", nullable = true, precision = 4)
    public Long getTaxReturnRec() {
        return taxReturnRec;
    }

    public void setTaxReturnRec(Long taxReturnRec) {
        this.taxReturnRec = taxReturnRec;
    }

    @Basic
    @Column(name = "OTHER_OPERATE_REC", nullable = true, precision = 4)
    public Long getOtherOperateRec() {
        return otherOperateRec;
    }

    public void setOtherOperateRec(Long otherOperateRec) {
        this.otherOperateRec = otherOperateRec;
    }

    @Basic
    @Column(name = "NI_DEPOSIT", nullable = true, precision = 4)
    public Long getNiDeposit() {
        return niDeposit;
    }

    public void setNiDeposit(Long niDeposit) {
        this.niDeposit = niDeposit;
    }

    @Basic
    @Column(name = "NIBORROW_FROM_CBANK", nullable = true, precision = 4)
    public Long getNiborrowFromCbank() {
        return niborrowFromCbank;
    }

    public void setNiborrowFromCbank(Long niborrowFromCbank) {
        this.niborrowFromCbank = niborrowFromCbank;
    }

    @Basic
    @Column(name = "NIBORROW_FROM_FI", nullable = true, precision = 4)
    public Long getNiborrowFromFi() {
        return niborrowFromFi;
    }

    public void setNiborrowFromFi(Long niborrowFromFi) {
        this.niborrowFromFi = niborrowFromFi;
    }

    @Basic
    @Column(name = "PREMIUM_REC", nullable = true, precision = 4)
    public Long getPremiumRec() {
        return premiumRec;
    }

    public void setPremiumRec(Long premiumRec) {
        this.premiumRec = premiumRec;
    }

    @Basic
    @Column(name = "NIDISP_TRADE_FASSET", nullable = true, precision = 4)
    public Long getNidispTradeFasset() {
        return nidispTradeFasset;
    }

    public void setNidispTradeFasset(Long nidispTradeFasset) {
        this.nidispTradeFasset = nidispTradeFasset;
    }

    @Basic
    @Column(name = "NIDISP_SALEABLE_FASSET", nullable = true, precision = 4)
    public Long getNidispSaleableFasset() {
        return nidispSaleableFasset;
    }

    public void setNidispSaleableFasset(Long nidispSaleableFasset) {
        this.nidispSaleableFasset = nidispSaleableFasset;
    }

    @Basic
    @Column(name = "NIBORROW_FUND", nullable = true, precision = 4)
    public Long getNiborrowFund() {
        return niborrowFund;
    }

    public void setNiborrowFund(Long niborrowFund) {
        this.niborrowFund = niborrowFund;
    }

    @Basic
    @Column(name = "NIBUYBACK_FUND", nullable = true, precision = 4)
    public Long getNibuybackFund() {
        return nibuybackFund;
    }

    public void setNibuybackFund(Long nibuybackFund) {
        this.nibuybackFund = nibuybackFund;
    }

    @Basic
    @Column(name = "OPERATE_FLOWIN_OTHER", nullable = true, precision = 4)
    public Long getOperateFlowinOther() {
        return operateFlowinOther;
    }

    public void setOperateFlowinOther(Long operateFlowinOther) {
        this.operateFlowinOther = operateFlowinOther;
    }

    @Basic
    @Column(name = "OPERATE_FLOWIN_BALANCE", nullable = true, precision = 4)
    public Long getOperateFlowinBalance() {
        return operateFlowinBalance;
    }

    public void setOperateFlowinBalance(Long operateFlowinBalance) {
        this.operateFlowinBalance = operateFlowinBalance;
    }

    @Basic
    @Column(name = "SUM_OPERATE_FLOWIN", nullable = true, precision = 4)
    public Long getSumOperateFlowin() {
        return sumOperateFlowin;
    }

    public void setSumOperateFlowin(Long sumOperateFlowin) {
        this.sumOperateFlowin = sumOperateFlowin;
    }

    @Basic
    @Column(name = "BUYGOODS_SERVICE_PAY", nullable = true, precision = 4)
    public Long getBuygoodsServicePay() {
        return buygoodsServicePay;
    }

    public void setBuygoodsServicePay(Long buygoodsServicePay) {
        this.buygoodsServicePay = buygoodsServicePay;
    }

    @Basic
    @Column(name = "EMPLOYEE_PAY", nullable = true, precision = 4)
    public Long getEmployeePay() {
        return employeePay;
    }

    public void setEmployeePay(Long employeePay) {
        this.employeePay = employeePay;
    }

    @Basic
    @Column(name = "TAX_PAY", nullable = true, precision = 4)
    public Long getTaxPay() {
        return taxPay;
    }

    public void setTaxPay(Long taxPay) {
        this.taxPay = taxPay;
    }

    @Basic
    @Column(name = "OTHER_OPERAT_EPAY", nullable = true, precision = 4)
    public Long getOtherOperatEpay() {
        return otherOperatEpay;
    }

    public void setOtherOperatEpay(Long otherOperatEpay) {
        this.otherOperatEpay = otherOperatEpay;
    }

    @Basic
    @Column(name = "NILOAN_ADVANCES", nullable = true, precision = 4)
    public Long getNiloanAdvances() {
        return niloanAdvances;
    }

    public void setNiloanAdvances(Long niloanAdvances) {
        this.niloanAdvances = niloanAdvances;
    }

    @Basic
    @Column(name = "NIDEPOSIT_INCBANKFI", nullable = true, precision = 4)
    public Long getNidepositIncbankfi() {
        return nidepositIncbankfi;
    }

    public void setNidepositIncbankfi(Long nidepositIncbankfi) {
        this.nidepositIncbankfi = nidepositIncbankfi;
    }

    @Basic
    @Column(name = "INDEMNITY_PAY", nullable = true, precision = 4)
    public Long getIndemnityPay() {
        return indemnityPay;
    }

    public void setIndemnityPay(Long indemnityPay) {
        this.indemnityPay = indemnityPay;
    }

    @Basic
    @Column(name = "INTANDCOMM_PAY", nullable = true, precision = 4)
    public Long getIntandcommPay() {
        return intandcommPay;
    }

    public void setIntandcommPay(Long intandcommPay) {
        this.intandcommPay = intandcommPay;
    }

    @Basic
    @Column(name = "OPERATE_FLOWOUT_OTHER", nullable = true, precision = 4)
    public Long getOperateFlowoutOther() {
        return operateFlowoutOther;
    }

    public void setOperateFlowoutOther(Long operateFlowoutOther) {
        this.operateFlowoutOther = operateFlowoutOther;
    }

    @Basic
    @Column(name = "OPERATE_FLOWOUT_BALANCE", nullable = true, precision = 4)
    public Long getOperateFlowoutBalance() {
        return operateFlowoutBalance;
    }

    public void setOperateFlowoutBalance(Long operateFlowoutBalance) {
        this.operateFlowoutBalance = operateFlowoutBalance;
    }

    @Basic
    @Column(name = "SUM_OPERATE_FLOWOUT", nullable = true, precision = 4)
    public Long getSumOperateFlowout() {
        return sumOperateFlowout;
    }

    public void setSumOperateFlowout(Long sumOperateFlowout) {
        this.sumOperateFlowout = sumOperateFlowout;
    }

    @Basic
    @Column(name = "OPERATE_FLOW_OTHER", nullable = true, precision = 4)
    public Long getOperateFlowOther() {
        return operateFlowOther;
    }

    public void setOperateFlowOther(Long operateFlowOther) {
        this.operateFlowOther = operateFlowOther;
    }

    @Basic
    @Column(name = "OPERATE_FLOW_BALANCE", nullable = true, precision = 4)
    public Long getOperateFlowBalance() {
        return operateFlowBalance;
    }

    public void setOperateFlowBalance(Long operateFlowBalance) {
        this.operateFlowBalance = operateFlowBalance;
    }

    @Basic
    @Column(name = "NET_OPERATE_CASHFLOW", nullable = true, precision = 4)
    public BigDecimal getNetOperateCashflow() {
        return netOperateCashflow;
    }

    public void setNetOperateCashflow(BigDecimal netOperateCashflow) {
        this.netOperateCashflow = netOperateCashflow;
    }

    @Basic
    @Column(name = "DISPOSAL_INV_REC", nullable = true, precision = 4)
    public Long getDisposalInvRec() {
        return disposalInvRec;
    }

    public void setDisposalInvRec(Long disposalInvRec) {
        this.disposalInvRec = disposalInvRec;
    }

    @Basic
    @Column(name = "INV_INCOME_REC", nullable = true, precision = 4)
    public Long getInvIncomeRec() {
        return invIncomeRec;
    }

    public void setInvIncomeRec(Long invIncomeRec) {
        this.invIncomeRec = invIncomeRec;
    }

    @Basic
    @Column(name = "DISP_FILASSET_REC", nullable = true, precision = 4)
    public Long getDispFilassetRec() {
        return dispFilassetRec;
    }

    public void setDispFilassetRec(Long dispFilassetRec) {
        this.dispFilassetRec = dispFilassetRec;
    }

    @Basic
    @Column(name = "DISP_SUBSIDIARY_REC", nullable = true, precision = 4)
    public Long getDispSubsidiaryRec() {
        return dispSubsidiaryRec;
    }

    public void setDispSubsidiaryRec(Long dispSubsidiaryRec) {
        this.dispSubsidiaryRec = dispSubsidiaryRec;
    }

    @Basic
    @Column(name = "OTHER_INVREC", nullable = true, precision = 4)
    public Long getOtherInvrec() {
        return otherInvrec;
    }

    public void setOtherInvrec(Long otherInvrec) {
        this.otherInvrec = otherInvrec;
    }

    @Basic
    @Column(name = "INV_FLOWIN_OTHER", nullable = true, precision = 4)
    public Long getInvFlowinOther() {
        return invFlowinOther;
    }

    public void setInvFlowinOther(Long invFlowinOther) {
        this.invFlowinOther = invFlowinOther;
    }

    @Basic
    @Column(name = "INV_FLOWIN_BALANCE", nullable = true, precision = 4)
    public Long getInvFlowinBalance() {
        return invFlowinBalance;
    }

    public void setInvFlowinBalance(Long invFlowinBalance) {
        this.invFlowinBalance = invFlowinBalance;
    }

    @Basic
    @Column(name = "SUM_INV_FLOWIN", nullable = true, precision = 4)
    public Long getSumInvFlowin() {
        return sumInvFlowin;
    }

    public void setSumInvFlowin(Long sumInvFlowin) {
        this.sumInvFlowin = sumInvFlowin;
    }

    @Basic
    @Column(name = "BUY_FILASSET_PAY", nullable = true, precision = 4)
    public Long getBuyFilassetPay() {
        return buyFilassetPay;
    }

    public void setBuyFilassetPay(Long buyFilassetPay) {
        this.buyFilassetPay = buyFilassetPay;
    }

    @Basic
    @Column(name = "INV_PAY", nullable = true, precision = 4)
    public Long getInvPay() {
        return invPay;
    }

    public void setInvPay(Long invPay) {
        this.invPay = invPay;
    }

    @Basic
    @Column(name = "GET_SUBSIDIARY_PAY", nullable = true, precision = 4)
    public Long getGetSubsidiaryPay() {
        return getSubsidiaryPay;
    }

    public void setGetSubsidiaryPay(Long getSubsidiaryPay) {
        this.getSubsidiaryPay = getSubsidiaryPay;
    }

    @Basic
    @Column(name = "OTHER_INV_PAY", nullable = true, precision = 4)
    public Long getOtherInvPay() {
        return otherInvPay;
    }

    public void setOtherInvPay(Long otherInvPay) {
        this.otherInvPay = otherInvPay;
    }

    @Basic
    @Column(name = "NIPLEDGE_LOAN", nullable = true, precision = 4)
    public Long getNipledgeLoan() {
        return nipledgeLoan;
    }

    public void setNipledgeLoan(Long nipledgeLoan) {
        this.nipledgeLoan = nipledgeLoan;
    }

    @Basic
    @Column(name = "INV_FLOWOUT_OTHER", nullable = true, precision = 4)
    public Long getInvFlowoutOther() {
        return invFlowoutOther;
    }

    public void setInvFlowoutOther(Long invFlowoutOther) {
        this.invFlowoutOther = invFlowoutOther;
    }

    @Basic
    @Column(name = "INV_FLOWOUT_BALANCE", nullable = true, precision = 4)
    public Long getInvFlowoutBalance() {
        return invFlowoutBalance;
    }

    public void setInvFlowoutBalance(Long invFlowoutBalance) {
        this.invFlowoutBalance = invFlowoutBalance;
    }

    @Basic
    @Column(name = "SUM_INV_FLOWOUT", nullable = true, precision = 4)
    public Long getSumInvFlowout() {
        return sumInvFlowout;
    }

    public void setSumInvFlowout(Long sumInvFlowout) {
        this.sumInvFlowout = sumInvFlowout;
    }

    @Basic
    @Column(name = "INV_FLOW_OTHER", nullable = true, precision = 4)
    public Long getInvFlowOther() {
        return invFlowOther;
    }

    public void setInvFlowOther(Long invFlowOther) {
        this.invFlowOther = invFlowOther;
    }

    @Basic
    @Column(name = "INV_CASHFLOW_BALANCE", nullable = true, precision = 4)
    public Long getInvCashflowBalance() {
        return invCashflowBalance;
    }

    public void setInvCashflowBalance(Long invCashflowBalance) {
        this.invCashflowBalance = invCashflowBalance;
    }

    @Basic
    @Column(name = "NET_INV_CASHFLOW", nullable = true, precision = 4)
    public BigDecimal getNetInvCashflow() {
        return netInvCashflow;
    }

    public void setNetInvCashflow(BigDecimal netInvCashflow) {
        this.netInvCashflow = netInvCashflow;
    }

    @Basic
    @Column(name = "ACCEPT_INV_REC", nullable = true, precision = 4)
    public Long getAcceptInvRec() {
        return acceptInvRec;
    }

    public void setAcceptInvRec(Long acceptInvRec) {
        this.acceptInvRec = acceptInvRec;
    }

    @Basic
    @Column(name = "LOAN_REC", nullable = true, precision = 4)
    public Long getLoanRec() {
        return loanRec;
    }

    public void setLoanRec(Long loanRec) {
        this.loanRec = loanRec;
    }

    @Basic
    @Column(name = "OTHER_FINA_REC", nullable = true, precision = 4)
    public Long getOtherFinaRec() {
        return otherFinaRec;
    }

    public void setOtherFinaRec(Long otherFinaRec) {
        this.otherFinaRec = otherFinaRec;
    }

    @Basic
    @Column(name = "ISSUE_BOND_REC", nullable = true, precision = 4)
    public Long getIssueBondRec() {
        return issueBondRec;
    }

    public void setIssueBondRec(Long issueBondRec) {
        this.issueBondRec = issueBondRec;
    }

    @Basic
    @Column(name = "NIINSURED_DEPOSIT_INV", nullable = true, precision = 4)
    public Long getNiinsuredDepositInv() {
        return niinsuredDepositInv;
    }

    public void setNiinsuredDepositInv(Long niinsuredDepositInv) {
        this.niinsuredDepositInv = niinsuredDepositInv;
    }

    @Basic
    @Column(name = "FINA_FLOWIN_OTHER", nullable = true, precision = 4)
    public Long getFinaFlowinOther() {
        return finaFlowinOther;
    }

    public void setFinaFlowinOther(Long finaFlowinOther) {
        this.finaFlowinOther = finaFlowinOther;
    }

    @Basic
    @Column(name = "FINA_FLOWIN_BALANCE", nullable = true, precision = 4)
    public Long getFinaFlowinBalance() {
        return finaFlowinBalance;
    }

    public void setFinaFlowinBalance(Long finaFlowinBalance) {
        this.finaFlowinBalance = finaFlowinBalance;
    }

    @Basic
    @Column(name = "SUM_FINA_FLOWIN", nullable = true, precision = 4)
    public Long getSumFinaFlowin() {
        return sumFinaFlowin;
    }

    public void setSumFinaFlowin(Long sumFinaFlowin) {
        this.sumFinaFlowin = sumFinaFlowin;
    }

    @Basic
    @Column(name = "REPAY_DEBT_PAY", nullable = true, precision = 4)
    public Long getRepayDebtPay() {
        return repayDebtPay;
    }

    public void setRepayDebtPay(Long repayDebtPay) {
        this.repayDebtPay = repayDebtPay;
    }

    @Basic
    @Column(name = "DIVI_PROFITORINT_PAY", nullable = true, precision = 4)
    public Long getDiviProfitorintPay() {
        return diviProfitorintPay;
    }

    public void setDiviProfitorintPay(Long diviProfitorintPay) {
        this.diviProfitorintPay = diviProfitorintPay;
    }

    @Basic
    @Column(name = "OTHER_FINA_PAY", nullable = true, precision = 4)
    public Long getOtherFinaPay() {
        return otherFinaPay;
    }

    public void setOtherFinaPay(Long otherFinaPay) {
        this.otherFinaPay = otherFinaPay;
    }

    @Basic
    @Column(name = "FINA_FLOWOUT_OTHER", nullable = true, precision = 4)
    public Long getFinaFlowoutOther() {
        return finaFlowoutOther;
    }

    public void setFinaFlowoutOther(Long finaFlowoutOther) {
        this.finaFlowoutOther = finaFlowoutOther;
    }

    @Basic
    @Column(name = "FINA_FLOWOUT_BALANCE", nullable = true, precision = 4)
    public Long getFinaFlowoutBalance() {
        return finaFlowoutBalance;
    }

    public void setFinaFlowoutBalance(Long finaFlowoutBalance) {
        this.finaFlowoutBalance = finaFlowoutBalance;
    }

    @Basic
    @Column(name = "SUM_FINA_FLOWOUT", nullable = true, precision = 4)
    public Long getSumFinaFlowout() {
        return sumFinaFlowout;
    }

    public void setSumFinaFlowout(Long sumFinaFlowout) {
        this.sumFinaFlowout = sumFinaFlowout;
    }

    @Basic
    @Column(name = "FINA_FLOW_OTHER", nullable = true, precision = 4)
    public Long getFinaFlowOther() {
        return finaFlowOther;
    }

    public void setFinaFlowOther(Long finaFlowOther) {
        this.finaFlowOther = finaFlowOther;
    }

    @Basic
    @Column(name = "FINA_FLOW_BALANCE", nullable = true, precision = 4)
    public Long getFinaFlowBalance() {
        return finaFlowBalance;
    }

    public void setFinaFlowBalance(Long finaFlowBalance) {
        this.finaFlowBalance = finaFlowBalance;
    }

    @Basic
    @Column(name = "NET_FINA_CASHFLOW", nullable = true, precision = 4)
    public BigDecimal getNetFinaCashflow() {
        return netFinaCashflow;
    }

    public void setNetFinaCashflow(BigDecimal netFinaCashflow) {
        this.netFinaCashflow = netFinaCashflow;
    }

    @Basic
    @Column(name = "EFFECT_EXCHANGE_RATE", nullable = true, precision = 4)
    public Long getEffectExchangeRate() {
        return effectExchangeRate;
    }

    public void setEffectExchangeRate(Long effectExchangeRate) {
        this.effectExchangeRate = effectExchangeRate;
    }

    @Basic
    @Column(name = "NICASH_EQUI_OTHER", nullable = true, precision = 4)
    public Long getNicashEquiOther() {
        return nicashEquiOther;
    }

    public void setNicashEquiOther(Long nicashEquiOther) {
        this.nicashEquiOther = nicashEquiOther;
    }

    @Basic
    @Column(name = "NICASH_EQUI_BALANCE", nullable = true, precision = 4)
    public Long getNicashEquiBalance() {
        return nicashEquiBalance;
    }

    public void setNicashEquiBalance(Long nicashEquiBalance) {
        this.nicashEquiBalance = nicashEquiBalance;
    }

    @Basic
    @Column(name = "NICASH_EQUI", nullable = true, precision = 4)
    public BigDecimal getNicashEqui() {
        return nicashEqui;
    }

    public void setNicashEqui(BigDecimal nicashEqui) {
        this.nicashEqui = nicashEqui;
    }

    @Basic
    @Column(name = "CASH_EQUI_BEGINNING", nullable = true, precision = 4)
    public Long getCashEquiBeginning() {
        return cashEquiBeginning;
    }

    public void setCashEquiBeginning(Long cashEquiBeginning) {
        this.cashEquiBeginning = cashEquiBeginning;
    }

    @Basic
    @Column(name = "CASH_EQUI_ENDING", nullable = true, precision = 4)
    public Long getCashEquiEnding() {
        return cashEquiEnding;
    }

    public void setCashEquiEnding(Long cashEquiEnding) {
        this.cashEquiEnding = cashEquiEnding;
    }

    @Basic
    @Column(name = "NET_PROFIT", nullable = true, precision = 4)
    public Long getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Long netProfit) {
        this.netProfit = netProfit;
    }

    @Basic
    @Column(name = "ASSET_DEVALUE", nullable = true, precision = 4)
    public Long getAssetDevalue() {
        return assetDevalue;
    }

    public void setAssetDevalue(Long assetDevalue) {
        this.assetDevalue = assetDevalue;
    }

    @Basic
    @Column(name = "FIXED_ASSET_ETCDEPR", nullable = true, precision = 4)
    public Long getFixedAssetEtcdepr() {
        return fixedAssetEtcdepr;
    }

    public void setFixedAssetEtcdepr(Long fixedAssetEtcdepr) {
        this.fixedAssetEtcdepr = fixedAssetEtcdepr;
    }

    @Basic
    @Column(name = "INTANGIBLE_ASSET_AMOR", nullable = true, precision = 4)
    public Long getIntangibleAssetAmor() {
        return intangibleAssetAmor;
    }

    public void setIntangibleAssetAmor(Long intangibleAssetAmor) {
        this.intangibleAssetAmor = intangibleAssetAmor;
    }

    @Basic
    @Column(name = "LTDEFER_EXP_AMOR", nullable = true, precision = 4)
    public Long getLtdeferExpAmor() {
        return ltdeferExpAmor;
    }

    public void setLtdeferExpAmor(Long ltdeferExpAmor) {
        this.ltdeferExpAmor = ltdeferExpAmor;
    }

    @Basic
    @Column(name = "DEFER_EXP_REDUCE", nullable = true, precision = 4)
    public Long getDeferExpReduce() {
        return deferExpReduce;
    }

    public void setDeferExpReduce(Long deferExpReduce) {
        this.deferExpReduce = deferExpReduce;
    }

    @Basic
    @Column(name = "DRAWING_EXP_ADD", nullable = true, precision = 4)
    public Long getDrawingExpAdd() {
        return drawingExpAdd;
    }

    public void setDrawingExpAdd(Long drawingExpAdd) {
        this.drawingExpAdd = drawingExpAdd;
    }

    @Basic
    @Column(name = "DISP_FILASSET_LOSS", nullable = true, precision = 4)
    public Long getDispFilassetLoss() {
        return dispFilassetLoss;
    }

    public void setDispFilassetLoss(Long dispFilassetLoss) {
        this.dispFilassetLoss = dispFilassetLoss;
    }

    @Basic
    @Column(name = "FIXED_ASSET_LOSS", nullable = true, precision = 4)
    public Long getFixedAssetLoss() {
        return fixedAssetLoss;
    }

    public void setFixedAssetLoss(Long fixedAssetLoss) {
        this.fixedAssetLoss = fixedAssetLoss;
    }

    @Basic
    @Column(name = "FVALUE_LOSS", nullable = true, precision = 4)
    public Long getFvalueLoss() {
        return fvalueLoss;
    }

    public void setFvalueLoss(Long fvalueLoss) {
        this.fvalueLoss = fvalueLoss;
    }

    @Basic
    @Column(name = "FINANCE_EXP", nullable = true, precision = 4)
    public Long getFinanceExp() {
        return financeExp;
    }

    public void setFinanceExp(Long financeExp) {
        this.financeExp = financeExp;
    }

    @Basic
    @Column(name = "INV_LOSS", nullable = true, precision = 4)
    public Long getInvLoss() {
        return invLoss;
    }

    public void setInvLoss(Long invLoss) {
        this.invLoss = invLoss;
    }

    @Basic
    @Column(name = "DEFER_TAXASSET_REDUCE", nullable = true, precision = 4)
    public Long getDeferTaxassetReduce() {
        return deferTaxassetReduce;
    }

    public void setDeferTaxassetReduce(Long deferTaxassetReduce) {
        this.deferTaxassetReduce = deferTaxassetReduce;
    }

    @Basic
    @Column(name = "DEFER_TAXLIAB_ADD", nullable = true, precision = 4)
    public Long getDeferTaxliabAdd() {
        return deferTaxliabAdd;
    }

    public void setDeferTaxliabAdd(Long deferTaxliabAdd) {
        this.deferTaxliabAdd = deferTaxliabAdd;
    }

    @Basic
    @Column(name = "INVENTORY_REDUCE", nullable = true, precision = 4)
    public Long getInventoryReduce() {
        return inventoryReduce;
    }

    public void setInventoryReduce(Long inventoryReduce) {
        this.inventoryReduce = inventoryReduce;
    }

    @Basic
    @Column(name = "OPERATE_REC_REDUCE", nullable = true, precision = 4)
    public Long getOperateRecReduce() {
        return operateRecReduce;
    }

    public void setOperateRecReduce(Long operateRecReduce) {
        this.operateRecReduce = operateRecReduce;
    }

    @Basic
    @Column(name = "OPERATE_PAY_ADD", nullable = true, precision = 4)
    public Long getOperatePayAdd() {
        return operatePayAdd;
    }

    public void setOperatePayAdd(Long operatePayAdd) {
        this.operatePayAdd = operatePayAdd;
    }

    @Basic
    @Column(name = "INOPERATE_FLOW_OTHER", nullable = true, precision = 4)
    public Long getInoperateFlowOther() {
        return inoperateFlowOther;
    }

    public void setInoperateFlowOther(Long inoperateFlowOther) {
        this.inoperateFlowOther = inoperateFlowOther;
    }

    @Basic
    @Column(name = "INOPERATE_FLOW_BALANCE", nullable = true, precision = 4)
    public Long getInoperateFlowBalance() {
        return inoperateFlowBalance;
    }

    public void setInoperateFlowBalance(Long inoperateFlowBalance) {
        this.inoperateFlowBalance = inoperateFlowBalance;
    }

    @Basic
    @Column(name = "INNET_OPERATE_CASHFLOW", nullable = true, precision = 4)
    public Long getInnetOperateCashflow() {
        return innetOperateCashflow;
    }

    public void setInnetOperateCashflow(Long innetOperateCashflow) {
        this.innetOperateCashflow = innetOperateCashflow;
    }

    @Basic
    @Column(name = "DEBT_TO_CAPITAL", nullable = true, precision = 4)
    public Long getDebtToCapital() {
        return debtToCapital;
    }

    public void setDebtToCapital(Long debtToCapital) {
        this.debtToCapital = debtToCapital;
    }

    @Basic
    @Column(name = "CB_ONEYEAR", nullable = true, precision = 4)
    public Long getCbOneyear() {
        return cbOneyear;
    }

    public void setCbOneyear(Long cbOneyear) {
        this.cbOneyear = cbOneyear;
    }

    @Basic
    @Column(name = "FINALEASE_FIXED_ASSET", nullable = true, precision = 4)
    public Long getFinaleaseFixedAsset() {
        return finaleaseFixedAsset;
    }

    public void setFinaleaseFixedAsset(Long finaleaseFixedAsset) {
        this.finaleaseFixedAsset = finaleaseFixedAsset;
    }

    @Basic
    @Column(name = "CASH_END", nullable = true, precision = 4)
    public Long getCashEnd() {
        return cashEnd;
    }

    public void setCashEnd(Long cashEnd) {
        this.cashEnd = cashEnd;
    }

    @Basic
    @Column(name = "CASH_BEGIN", nullable = true, precision = 4)
    public Long getCashBegin() {
        return cashBegin;
    }

    public void setCashBegin(Long cashBegin) {
        this.cashBegin = cashBegin;
    }

    @Basic
    @Column(name = "EQUI_END", nullable = true, precision = 4)
    public Long getEquiEnd() {
        return equiEnd;
    }

    public void setEquiEnd(Long equiEnd) {
        this.equiEnd = equiEnd;
    }

    @Basic
    @Column(name = "EQUI_BEGIN", nullable = true, precision = 4)
    public Long getEquiBegin() {
        return equiBegin;
    }

    public void setEquiBegin(Long equiBegin) {
        this.equiBegin = equiBegin;
    }

    @Basic
    @Column(name = "INNICASH_EQUI_OTHER", nullable = true, precision = 4)
    public Long getInnicashEquiOther() {
        return innicashEquiOther;
    }

    public void setInnicashEquiOther(Long innicashEquiOther) {
        this.innicashEquiOther = innicashEquiOther;
    }

    @Basic
    @Column(name = "INNICASH_EQUI_BALANCE", nullable = true, precision = 4)
    public Long getInnicashEquiBalance() {
        return innicashEquiBalance;
    }

    public void setInnicashEquiBalance(Long innicashEquiBalance) {
        this.innicashEquiBalance = innicashEquiBalance;
    }

    @Basic
    @Column(name = "INNICASH_EQUI", nullable = true, precision = 4)
    public Long getInnicashEqui() {
        return innicashEqui;
    }

    public void setInnicashEqui(Long innicashEqui) {
        this.innicashEqui = innicashEqui;
    }

    @Basic
    @Column(name = "OTHER", nullable = true, precision = 4)
    public Long getOther() {
        return other;
    }

    public void setOther(Long other) {
        this.other = other;
    }

    @Basic
    @Column(name = "SUBSIDIARY_ACCEPT", nullable = true, precision = 4)
    public Long getSubsidiaryAccept() {
        return subsidiaryAccept;
    }

    public void setSubsidiaryAccept(Long subsidiaryAccept) {
        this.subsidiaryAccept = subsidiaryAccept;
    }

    @Basic
    @Column(name = "SUBSIDIARY_PAY", nullable = true, precision = 4)
    public Long getSubsidiaryPay() {
        return subsidiaryPay;
    }

    public void setSubsidiaryPay(Long subsidiaryPay) {
        this.subsidiaryPay = subsidiaryPay;
    }

    @Basic
    @Column(name = "DIVI_PAY", nullable = true, precision = 4)
    public Long getDiviPay() {
        return diviPay;
    }

    public void setDiviPay(Long diviPay) {
        this.diviPay = diviPay;
    }

    @Basic
    @Column(name = "INTANDCOMM_REC", nullable = true, precision = 4)
    public Long getIntandcommRec() {
        return intandcommRec;
    }

    public void setIntandcommRec(Long intandcommRec) {
        this.intandcommRec = intandcommRec;
    }

    @Basic
    @Column(name = "NET_RIREC", nullable = true, precision = 4)
    public Long getNetRirec() {
        return netRirec;
    }

    public void setNetRirec(Long netRirec) {
        this.netRirec = netRirec;
    }

    @Basic
    @Column(name = "NILEND_FUND", nullable = true, precision = 4)
    public Long getNilendFund() {
        return nilendFund;
    }

    public void setNilendFund(Long nilendFund) {
        this.nilendFund = nilendFund;
    }

    @Basic
    @Column(name = "DEFER_TAX", nullable = true, precision = 4)
    public Long getDeferTax() {
        return deferTax;
    }

    public void setDeferTax(Long deferTax) {
        this.deferTax = deferTax;
    }

    @Basic
    @Column(name = "DEFER_INCOME_AMOR", nullable = true, precision = 4)
    public Long getDeferIncomeAmor() {
        return deferIncomeAmor;
    }

    public void setDeferIncomeAmor(Long deferIncomeAmor) {
        this.deferIncomeAmor = deferIncomeAmor;
    }

    @Basic
    @Column(name = "EXCHANGE_LOSS", nullable = true, precision = 4)
    public Long getExchangeLoss() {
        return exchangeLoss;
    }

    public void setExchangeLoss(Long exchangeLoss) {
        this.exchangeLoss = exchangeLoss;
    }

    @Basic
    @Column(name = "FIXANDESTATE_DEPR", nullable = true, precision = 4)
    public Long getFixandestateDepr() {
        return fixandestateDepr;
    }

    public void setFixandestateDepr(Long fixandestateDepr) {
        this.fixandestateDepr = fixandestateDepr;
    }

    @Basic
    @Column(name = "FIXED_ASSET_DEPR", nullable = true, precision = 4)
    public Long getFixedAssetDepr() {
        return fixedAssetDepr;
    }

    public void setFixedAssetDepr(Long fixedAssetDepr) {
        this.fixedAssetDepr = fixedAssetDepr;
    }

    @Basic
    @Column(name = "TRADEF_ASSET_REDUCE", nullable = true, precision = 4)
    public Long getTradefAssetReduce() {
        return tradefAssetReduce;
    }

    public void setTradefAssetReduce(Long tradefAssetReduce) {
        this.tradefAssetReduce = tradefAssetReduce;
    }

    @Basic
    @Column(name = "NDLOAN_ADVANCES", nullable = true, precision = 4)
    public Long getNdloanAdvances() {
        return ndloanAdvances;
    }

    public void setNdloanAdvances(Long ndloanAdvances) {
        this.ndloanAdvances = ndloanAdvances;
    }

    @Basic
    @Column(name = "REDUCE_PLEDGET_DEPOSIT", nullable = true, precision = 4)
    public Long getReducePledgetDeposit() {
        return reducePledgetDeposit;
    }

    public void setReducePledgetDeposit(Long reducePledgetDeposit) {
        this.reducePledgetDeposit = reducePledgetDeposit;
    }

    @Basic
    @Column(name = "ADD_PLEDGET_DEPOSIT", nullable = true, precision = 4)
    public Long getAddPledgetDeposit() {
        return addPledgetDeposit;
    }

    public void setAddPledgetDeposit(Long addPledgetDeposit) {
        this.addPledgetDeposit = addPledgetDeposit;
    }

    @Basic
    @Column(name = "BUY_SUBSIDIARY_PAY", nullable = true, precision = 4)
    public Long getBuySubsidiaryPay() {
        return buySubsidiaryPay;
    }

    public void setBuySubsidiaryPay(Long buySubsidiaryPay) {
        this.buySubsidiaryPay = buySubsidiaryPay;
    }

    @Basic
    @Column(name = "CASH_EQUIENDING_OTHER", nullable = true, precision = 4)
    public Long getCashEquiendingOther() {
        return cashEquiendingOther;
    }

    public void setCashEquiendingOther(Long cashEquiendingOther) {
        this.cashEquiendingOther = cashEquiendingOther;
    }

    @Basic
    @Column(name = "CASH_EQUIENDING_BALANCE", nullable = true, precision = 4)
    public Long getCashEquiendingBalance() {
        return cashEquiendingBalance;
    }

    public void setCashEquiendingBalance(Long cashEquiendingBalance) {
        this.cashEquiendingBalance = cashEquiendingBalance;
    }

    @Basic
    @Column(name = "ND_DEPOSITINC_BANKFI", nullable = true, precision = 4)
    public Long getNdDepositincBankfi() {
        return ndDepositincBankfi;
    }

    public void setNdDepositincBankfi(Long ndDepositincBankfi) {
        this.ndDepositincBankfi = ndDepositincBankfi;
    }

    @Basic
    @Column(name = "NIBORROW_SELL_BUYBACK", nullable = true, precision = 4)
    public Long getNiborrowSellBuyback() {
        return niborrowSellBuyback;
    }

    public void setNiborrowSellBuyback(Long niborrowSellBuyback) {
        this.niborrowSellBuyback = niborrowSellBuyback;
    }

    @Basic
    @Column(name = "NDLEND_BUY_SELLBACK", nullable = true, precision = 4)
    public Long getNdlendBuySellback() {
        return ndlendBuySellback;
    }

    public void setNdlendBuySellback(Long ndlendBuySellback) {
        this.ndlendBuySellback = ndlendBuySellback;
    }

    @Basic
    @Column(name = "NET_CD", nullable = true, precision = 4)
    public Long getNetCd() {
        return netCd;
    }

    public void setNetCd(Long netCd) {
        this.netCd = netCd;
    }

    @Basic
    @Column(name = "NITRADE_FLIAB", nullable = true, precision = 4)
    public Long getNitradeFliab() {
        return nitradeFliab;
    }

    public void setNitradeFliab(Long nitradeFliab) {
        this.nitradeFliab = nitradeFliab;
    }

    @Basic
    @Column(name = "NDTRADE_FASSET", nullable = true, precision = 4)
    public Long getNdtradeFasset() {
        return ndtradeFasset;
    }

    public void setNdtradeFasset(Long ndtradeFasset) {
        this.ndtradeFasset = ndtradeFasset;
    }

    @Basic
    @Column(name = "DISP_MASSET_REC", nullable = true, precision = 4)
    public Long getDispMassetRec() {
        return dispMassetRec;
    }

    public void setDispMassetRec(Long dispMassetRec) {
        this.dispMassetRec = dispMassetRec;
    }

    @Basic
    @Column(name = "CANCEL_LOAN_REC", nullable = true, precision = 4)
    public Long getCancelLoanRec() {
        return cancelLoanRec;
    }

    public void setCancelLoanRec(Long cancelLoanRec) {
        this.cancelLoanRec = cancelLoanRec;
    }

    @Basic
    @Column(name = "NDBORROW_FROM_CBANK", nullable = true, precision = 4)
    public Long getNdborrowFromCbank() {
        return ndborrowFromCbank;
    }

    public void setNdborrowFromCbank(Long ndborrowFromCbank) {
        this.ndborrowFromCbank = ndborrowFromCbank;
    }

    @Basic
    @Column(name = "NDFIDE_POSIT", nullable = true, precision = 4)
    public Long getNdfidePosit() {
        return ndfidePosit;
    }

    public void setNdfidePosit(Long ndfidePosit) {
        this.ndfidePosit = ndfidePosit;
    }

    @Basic
    @Column(name = "NDISSUE_CD", nullable = true, precision = 4)
    public Long getNdissueCd() {
        return ndissueCd;
    }

    public void setNdissueCd(Long ndissueCd) {
        this.ndissueCd = ndissueCd;
    }

    @Basic
    @Column(name = "NILEND_SELL_BUYBACK", nullable = true, precision = 4)
    public Long getNilendSellBuyback() {
        return nilendSellBuyback;
    }

    public void setNilendSellBuyback(Long nilendSellBuyback) {
        this.nilendSellBuyback = nilendSellBuyback;
    }

    @Basic
    @Column(name = "NDBORROW_SELL_BUYBACK", nullable = true, precision = 4)
    public Long getNdborrowSellBuyback() {
        return ndborrowSellBuyback;
    }

    public void setNdborrowSellBuyback(Long ndborrowSellBuyback) {
        this.ndborrowSellBuyback = ndborrowSellBuyback;
    }

    @Basic
    @Column(name = "NITRADE_FASSET", nullable = true, precision = 4)
    public Long getNitradeFasset() {
        return nitradeFasset;
    }

    public void setNitradeFasset(Long nitradeFasset) {
        this.nitradeFasset = nitradeFasset;
    }

    @Basic
    @Column(name = "NDTRADE_FLIAB", nullable = true, precision = 4)
    public Long getNdtradeFliab() {
        return ndtradeFliab;
    }

    public void setNdtradeFliab(Long ndtradeFliab) {
        this.ndtradeFliab = ndtradeFliab;
    }

    @Basic
    @Column(name = "BUY_FINALEASEASSET_PAY", nullable = true, precision = 4)
    public Long getBuyFinaleaseassetPay() {
        return buyFinaleaseassetPay;
    }

    public void setBuyFinaleaseassetPay(Long buyFinaleaseassetPay) {
        this.buyFinaleaseassetPay = buyFinaleaseassetPay;
    }

    @Basic
    @Column(name = "NIACCOUNT_REC", nullable = true, precision = 4)
    public Long getNiaccountRec() {
        return niaccountRec;
    }

    public void setNiaccountRec(Long niaccountRec) {
        this.niaccountRec = niaccountRec;
    }

    @Basic
    @Column(name = "ISSUE_CD", nullable = true, precision = 4)
    public Long getIssueCd() {
        return issueCd;
    }

    public void setIssueCd(Long issueCd) {
        this.issueCd = issueCd;
    }

    @Basic
    @Column(name = "ADDSHARE_CAPITAL_REC", nullable = true, precision = 4)
    public Long getAddshareCapitalRec() {
        return addshareCapitalRec;
    }

    public void setAddshareCapitalRec(Long addshareCapitalRec) {
        this.addshareCapitalRec = addshareCapitalRec;
    }

    @Basic
    @Column(name = "ISSUE_SHARE_REC", nullable = true, precision = 4)
    public Long getIssueShareRec() {
        return issueShareRec;
    }

    public void setIssueShareRec(Long issueShareRec) {
        this.issueShareRec = issueShareRec;
    }

    @Basic
    @Column(name = "BOND_INTPAY", nullable = true, precision = 4)
    public Long getBondIntpay() {
        return bondIntpay;
    }

    public void setBondIntpay(Long bondIntpay) {
        this.bondIntpay = bondIntpay;
    }

    @Basic
    @Column(name = "NIOTHER_FINAINSTRU", nullable = true, precision = 4)
    public Long getNiotherFinainstru() {
        return niotherFinainstru;
    }

    public void setNiotherFinainstru(Long niotherFinainstru) {
        this.niotherFinainstru = niotherFinainstru;
    }

    @Basic
    @Column(name = "AGENT_TRADE_SECURITYREC", nullable = true, precision = 4)
    public Long getAgentTradeSecurityrec() {
        return agentTradeSecurityrec;
    }

    public void setAgentTradeSecurityrec(Long agentTradeSecurityrec) {
        this.agentTradeSecurityrec = agentTradeSecurityrec;
    }

    @Basic
    @Column(name = "UWSECURITY_REC", nullable = true, precision = 4)
    public Long getUwsecurityRec() {
        return uwsecurityRec;
    }

    public void setUwsecurityRec(Long uwsecurityRec) {
        this.uwsecurityRec = uwsecurityRec;
    }

    @Basic
    @Column(name = "BUYSELLBACK_FASSET_REC", nullable = true, precision = 4)
    public Long getBuysellbackFassetRec() {
        return buysellbackFassetRec;
    }

    public void setBuysellbackFassetRec(Long buysellbackFassetRec) {
        this.buysellbackFassetRec = buysellbackFassetRec;
    }

    @Basic
    @Column(name = "AGENT_UWSECURITY_REC", nullable = true, precision = 4)
    public Long getAgentUwsecurityRec() {
        return agentUwsecurityRec;
    }

    public void setAgentUwsecurityRec(Long agentUwsecurityRec) {
        this.agentUwsecurityRec = agentUwsecurityRec;
    }

    @Basic
    @Column(name = "NIDIRECT_INV", nullable = true, precision = 4)
    public Long getNidirectInv() {
        return nidirectInv;
    }

    public void setNidirectInv(Long nidirectInv) {
        this.nidirectInv = nidirectInv;
    }

    @Basic
    @Column(name = "NITRADE_SETTLEMENT", nullable = true, precision = 4)
    public Long getNitradeSettlement() {
        return nitradeSettlement;
    }

    public void setNitradeSettlement(Long nitradeSettlement) {
        this.nitradeSettlement = nitradeSettlement;
    }

    @Basic
    @Column(name = "BUYSELLBACK_FASSET_PAY", nullable = true, precision = 4)
    public Long getBuysellbackFassetPay() {
        return buysellbackFassetPay;
    }

    public void setBuysellbackFassetPay(Long buysellbackFassetPay) {
        this.buysellbackFassetPay = buysellbackFassetPay;
    }

    @Basic
    @Column(name = "NDDISP_TRADE_FASSET", nullable = true, precision = 4)
    public Long getNddispTradeFasset() {
        return nddispTradeFasset;
    }

    public void setNddispTradeFasset(Long nddispTradeFasset) {
        this.nddispTradeFasset = nddispTradeFasset;
    }

    @Basic
    @Column(name = "NDOTHER_FINA_INSTR", nullable = true, precision = 4)
    public Long getNdotherFinaInstr() {
        return ndotherFinaInstr;
    }

    public void setNdotherFinaInstr(Long ndotherFinaInstr) {
        this.ndotherFinaInstr = ndotherFinaInstr;
    }

    @Basic
    @Column(name = "NDBORROW_FUND", nullable = true, precision = 4)
    public Long getNdborrowFund() {
        return ndborrowFund;
    }

    public void setNdborrowFund(Long ndborrowFund) {
        this.ndborrowFund = ndborrowFund;
    }

    @Basic
    @Column(name = "NDDIRECT_INV", nullable = true, precision = 4)
    public Long getNddirectInv() {
        return nddirectInv;
    }

    public void setNddirectInv(Long nddirectInv) {
        this.nddirectInv = nddirectInv;
    }

    @Basic
    @Column(name = "NDTRADE_SETTLEMENT", nullable = true, precision = 4)
    public Long getNdtradeSettlement() {
        return ndtradeSettlement;
    }

    public void setNdtradeSettlement(Long ndtradeSettlement) {
        this.ndtradeSettlement = ndtradeSettlement;
    }

    @Basic
    @Column(name = "NDBUYBACK_FUND", nullable = true, precision = 4)
    public Long getNdbuybackFund() {
        return ndbuybackFund;
    }

    public void setNdbuybackFund(Long ndbuybackFund) {
        this.ndbuybackFund = ndbuybackFund;
    }

    @Basic
    @Column(name = "AGENTTRADE_SECURITY_PAY", nullable = true, precision = 4)
    public Long getAgenttradeSecurityPay() {
        return agenttradeSecurityPay;
    }

    public void setAgenttradeSecurityPay(Long agenttradeSecurityPay) {
        this.agenttradeSecurityPay = agenttradeSecurityPay;
    }

    @Basic
    @Column(name = "NDDISP_SALEABLE_FASSET", nullable = true, precision = 4)
    public Long getNddispSaleableFasset() {
        return nddispSaleableFasset;
    }

    public void setNddispSaleableFasset(Long nddispSaleableFasset) {
        this.nddispSaleableFasset = nddispSaleableFasset;
    }

    @Basic
    @Column(name = "NISELL_BUYBACK", nullable = true, precision = 4)
    public Long getNisellBuyback() {
        return nisellBuyback;
    }

    public void setNisellBuyback(Long nisellBuyback) {
        this.nisellBuyback = nisellBuyback;
    }

    @Basic
    @Column(name = "NDBUY_SELLBACK", nullable = true, precision = 4)
    public Long getNdbuySellback() {
        return ndbuySellback;
    }

    public void setNdbuySellback(Long ndbuySellback) {
        this.ndbuySellback = ndbuySellback;
    }

    @Basic
    @Column(name = "NETTRADE_FASSET_REC", nullable = true, precision = 4)
    public Long getNettradeFassetRec() {
        return nettradeFassetRec;
    }

    public void setNettradeFassetRec(Long nettradeFassetRec) {
        this.nettradeFassetRec = nettradeFassetRec;
    }

    @Basic
    @Column(name = "NET_RIPAY", nullable = true, precision = 4)
    public Long getNetRipay() {
        return netRipay;
    }

    public void setNetRipay(Long netRipay) {
        this.netRipay = netRipay;
    }

    @Basic
    @Column(name = "NDLEND_FUND", nullable = true, precision = 4)
    public Long getNdlendFund() {
        return ndlendFund;
    }

    public void setNdlendFund(Long ndlendFund) {
        this.ndlendFund = ndlendFund;
    }

    @Basic
    @Column(name = "NIBUY_SELLBACK", nullable = true, precision = 4)
    public Long getNibuySellback() {
        return nibuySellback;
    }

    public void setNibuySellback(Long nibuySellback) {
        this.nibuySellback = nibuySellback;
    }

    @Basic
    @Column(name = "NDSELL_BUYBACK", nullable = true, precision = 4)
    public Long getNdsellBuyback() {
        return ndsellBuyback;
    }

    public void setNdsellBuyback(Long ndsellBuyback) {
        this.ndsellBuyback = ndsellBuyback;
    }

    @Basic
    @Column(name = "NDINSURED_DEPOSIT_INV", nullable = true, precision = 4)
    public Long getNdinsuredDepositInv() {
        return ndinsuredDepositInv;
    }

    public void setNdinsuredDepositInv(Long ndinsuredDepositInv) {
        this.ndinsuredDepositInv = ndinsuredDepositInv;
    }

    @Basic
    @Column(name = "NETTRADE_FASSET_PAY", nullable = true, precision = 4)
    public Long getNettradeFassetPay() {
        return nettradeFassetPay;
    }

    public void setNettradeFassetPay(Long nettradeFassetPay) {
        this.nettradeFassetPay = nettradeFassetPay;
    }

    @Basic
    @Column(name = "NIINSURED_PLEDGE_LOAN", nullable = true, precision = 4)
    public Long getNiinsuredPledgeLoan() {
        return niinsuredPledgeLoan;
    }

    public void setNiinsuredPledgeLoan(Long niinsuredPledgeLoan) {
        this.niinsuredPledgeLoan = niinsuredPledgeLoan;
    }

    @Basic
    @Column(name = "DISP_SUBSIDIARY_PAY", nullable = true, precision = 4)
    public Long getDispSubsidiaryPay() {
        return dispSubsidiaryPay;
    }

    public void setDispSubsidiaryPay(Long dispSubsidiaryPay) {
        this.dispSubsidiaryPay = dispSubsidiaryPay;
    }

    @Basic
    @Column(name = "NETSELL_BUYBACK_FASSETREC", nullable = true, precision = 4)
    public Long getNetsellBuybackFassetrec() {
        return netsellBuybackFassetrec;
    }

    public void setNetsellBuybackFassetrec(Long netsellBuybackFassetrec) {
        this.netsellBuybackFassetrec = netsellBuybackFassetrec;
    }

    @Basic
    @Column(name = "NETSELL_BUYBACK_FASSETPAY", nullable = true, precision = 4)
    public Long getNetsellBuybackFassetpay() {
        return netsellBuybackFassetpay;
    }

    public void setNetsellBuybackFassetpay(Long netsellBuybackFassetpay) {
        this.netsellBuybackFassetpay = netsellBuybackFassetpay;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 1000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "CHK_STATUS", nullable = true, length = 10)
    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    @Basic
    @Column(name = "ISDEL", nullable = false, precision = 0)
    public Long getIsdel() {
        return isdel;
    }

    public void setIsdel(Long isdel) {
        this.isdel = isdel;
    }

    @Basic
    @Column(name = "SRC_COMPANY_CD", nullable = true, length = 60)
    public String getSrcCompanyCd() {
        return srcCompanyCd;
    }

    public void setSrcCompanyCd(String srcCompanyCd) {
        this.srcCompanyCd = srcCompanyCd;
    }

    @Basic
    @Column(name = "SRCID", nullable = true, length = 100)
    public String getSrcid() {
        return srcid;
    }

    public void setSrcid(String srcid) {
        this.srcid = srcid;
    }

    @Basic
    @Column(name = "SRC_CD", nullable = false, length = 10)
    public String getSrcCd() {
        return srcCd;
    }

    public void setSrcCd(String srcCd) {
        this.srcCd = srcCd;
    }

    @Basic
    @Column(name = "UPDT_DT", nullable = false)
    public Timestamp getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(Timestamp updtDt) {
        this.updtDt = updtDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompyCashflow that = (CompyCashflow) o;

        if (compyCashflowSid != null ? !compyCashflowSid.equals(that.compyCashflowSid) : that.compyCashflowSid != null)
            return false;
        if (firstNoticeDt != null ? !firstNoticeDt.equals(that.firstNoticeDt) : that.firstNoticeDt != null)
            return false;
        if (latestNoticeDt != null ? !latestNoticeDt.equals(that.latestNoticeDt) : that.latestNoticeDt != null)
            return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (rptDt != null ? !rptDt.equals(that.rptDt) : that.rptDt != null) return false;
        if (startDt != null ? !startDt.equals(that.startDt) : that.startDt != null) return false;
        if (endDt != null ? !endDt.equals(that.endDt) : that.endDt != null) return false;
        if (rptTimetypeCd != null ? !rptTimetypeCd.equals(that.rptTimetypeCd) : that.rptTimetypeCd != null)
            return false;
        if (combineTypeCd != null ? !combineTypeCd.equals(that.combineTypeCd) : that.combineTypeCd != null)
            return false;
        if (rptSrctypeId != null ? !rptSrctypeId.equals(that.rptSrctypeId) : that.rptSrctypeId != null) return false;
        if (dataAjustType != null ? !dataAjustType.equals(that.dataAjustType) : that.dataAjustType != null)
            return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (isPublicRpt != null ? !isPublicRpt.equals(that.isPublicRpt) : that.isPublicRpt != null) return false;
        if (companyType != null ? !companyType.equals(that.companyType) : that.companyType != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (salegoodsServiceRec != null ? !salegoodsServiceRec.equals(that.salegoodsServiceRec) : that.salegoodsServiceRec != null)
            return false;
        if (taxReturnRec != null ? !taxReturnRec.equals(that.taxReturnRec) : that.taxReturnRec != null) return false;
        if (otherOperateRec != null ? !otherOperateRec.equals(that.otherOperateRec) : that.otherOperateRec != null)
            return false;
        if (niDeposit != null ? !niDeposit.equals(that.niDeposit) : that.niDeposit != null) return false;
        if (niborrowFromCbank != null ? !niborrowFromCbank.equals(that.niborrowFromCbank) : that.niborrowFromCbank != null)
            return false;
        if (niborrowFromFi != null ? !niborrowFromFi.equals(that.niborrowFromFi) : that.niborrowFromFi != null)
            return false;
        if (premiumRec != null ? !premiumRec.equals(that.premiumRec) : that.premiumRec != null) return false;
        if (nidispTradeFasset != null ? !nidispTradeFasset.equals(that.nidispTradeFasset) : that.nidispTradeFasset != null)
            return false;
        if (nidispSaleableFasset != null ? !nidispSaleableFasset.equals(that.nidispSaleableFasset) : that.nidispSaleableFasset != null)
            return false;
        if (niborrowFund != null ? !niborrowFund.equals(that.niborrowFund) : that.niborrowFund != null) return false;
        if (nibuybackFund != null ? !nibuybackFund.equals(that.nibuybackFund) : that.nibuybackFund != null)
            return false;
        if (operateFlowinOther != null ? !operateFlowinOther.equals(that.operateFlowinOther) : that.operateFlowinOther != null)
            return false;
        if (operateFlowinBalance != null ? !operateFlowinBalance.equals(that.operateFlowinBalance) : that.operateFlowinBalance != null)
            return false;
        if (sumOperateFlowin != null ? !sumOperateFlowin.equals(that.sumOperateFlowin) : that.sumOperateFlowin != null)
            return false;
        if (buygoodsServicePay != null ? !buygoodsServicePay.equals(that.buygoodsServicePay) : that.buygoodsServicePay != null)
            return false;
        if (employeePay != null ? !employeePay.equals(that.employeePay) : that.employeePay != null) return false;
        if (taxPay != null ? !taxPay.equals(that.taxPay) : that.taxPay != null) return false;
        if (otherOperatEpay != null ? !otherOperatEpay.equals(that.otherOperatEpay) : that.otherOperatEpay != null)
            return false;
        if (niloanAdvances != null ? !niloanAdvances.equals(that.niloanAdvances) : that.niloanAdvances != null)
            return false;
        if (nidepositIncbankfi != null ? !nidepositIncbankfi.equals(that.nidepositIncbankfi) : that.nidepositIncbankfi != null)
            return false;
        if (indemnityPay != null ? !indemnityPay.equals(that.indemnityPay) : that.indemnityPay != null) return false;
        if (intandcommPay != null ? !intandcommPay.equals(that.intandcommPay) : that.intandcommPay != null)
            return false;
        if (operateFlowoutOther != null ? !operateFlowoutOther.equals(that.operateFlowoutOther) : that.operateFlowoutOther != null)
            return false;
        if (operateFlowoutBalance != null ? !operateFlowoutBalance.equals(that.operateFlowoutBalance) : that.operateFlowoutBalance != null)
            return false;
        if (sumOperateFlowout != null ? !sumOperateFlowout.equals(that.sumOperateFlowout) : that.sumOperateFlowout != null)
            return false;
        if (operateFlowOther != null ? !operateFlowOther.equals(that.operateFlowOther) : that.operateFlowOther != null)
            return false;
        if (operateFlowBalance != null ? !operateFlowBalance.equals(that.operateFlowBalance) : that.operateFlowBalance != null)
            return false;
        if (netOperateCashflow != null ? !netOperateCashflow.equals(that.netOperateCashflow) : that.netOperateCashflow != null)
            return false;
        if (disposalInvRec != null ? !disposalInvRec.equals(that.disposalInvRec) : that.disposalInvRec != null)
            return false;
        if (invIncomeRec != null ? !invIncomeRec.equals(that.invIncomeRec) : that.invIncomeRec != null) return false;
        if (dispFilassetRec != null ? !dispFilassetRec.equals(that.dispFilassetRec) : that.dispFilassetRec != null)
            return false;
        if (dispSubsidiaryRec != null ? !dispSubsidiaryRec.equals(that.dispSubsidiaryRec) : that.dispSubsidiaryRec != null)
            return false;
        if (otherInvrec != null ? !otherInvrec.equals(that.otherInvrec) : that.otherInvrec != null) return false;
        if (invFlowinOther != null ? !invFlowinOther.equals(that.invFlowinOther) : that.invFlowinOther != null)
            return false;
        if (invFlowinBalance != null ? !invFlowinBalance.equals(that.invFlowinBalance) : that.invFlowinBalance != null)
            return false;
        if (sumInvFlowin != null ? !sumInvFlowin.equals(that.sumInvFlowin) : that.sumInvFlowin != null) return false;
        if (buyFilassetPay != null ? !buyFilassetPay.equals(that.buyFilassetPay) : that.buyFilassetPay != null)
            return false;
        if (invPay != null ? !invPay.equals(that.invPay) : that.invPay != null) return false;
        if (getSubsidiaryPay != null ? !getSubsidiaryPay.equals(that.getSubsidiaryPay) : that.getSubsidiaryPay != null)
            return false;
        if (otherInvPay != null ? !otherInvPay.equals(that.otherInvPay) : that.otherInvPay != null) return false;
        if (nipledgeLoan != null ? !nipledgeLoan.equals(that.nipledgeLoan) : that.nipledgeLoan != null) return false;
        if (invFlowoutOther != null ? !invFlowoutOther.equals(that.invFlowoutOther) : that.invFlowoutOther != null)
            return false;
        if (invFlowoutBalance != null ? !invFlowoutBalance.equals(that.invFlowoutBalance) : that.invFlowoutBalance != null)
            return false;
        if (sumInvFlowout != null ? !sumInvFlowout.equals(that.sumInvFlowout) : that.sumInvFlowout != null)
            return false;
        if (invFlowOther != null ? !invFlowOther.equals(that.invFlowOther) : that.invFlowOther != null) return false;
        if (invCashflowBalance != null ? !invCashflowBalance.equals(that.invCashflowBalance) : that.invCashflowBalance != null)
            return false;
        if (netInvCashflow != null ? !netInvCashflow.equals(that.netInvCashflow) : that.netInvCashflow != null)
            return false;
        if (acceptInvRec != null ? !acceptInvRec.equals(that.acceptInvRec) : that.acceptInvRec != null) return false;
        if (loanRec != null ? !loanRec.equals(that.loanRec) : that.loanRec != null) return false;
        if (otherFinaRec != null ? !otherFinaRec.equals(that.otherFinaRec) : that.otherFinaRec != null) return false;
        if (issueBondRec != null ? !issueBondRec.equals(that.issueBondRec) : that.issueBondRec != null) return false;
        if (niinsuredDepositInv != null ? !niinsuredDepositInv.equals(that.niinsuredDepositInv) : that.niinsuredDepositInv != null)
            return false;
        if (finaFlowinOther != null ? !finaFlowinOther.equals(that.finaFlowinOther) : that.finaFlowinOther != null)
            return false;
        if (finaFlowinBalance != null ? !finaFlowinBalance.equals(that.finaFlowinBalance) : that.finaFlowinBalance != null)
            return false;
        if (sumFinaFlowin != null ? !sumFinaFlowin.equals(that.sumFinaFlowin) : that.sumFinaFlowin != null)
            return false;
        if (repayDebtPay != null ? !repayDebtPay.equals(that.repayDebtPay) : that.repayDebtPay != null) return false;
        if (diviProfitorintPay != null ? !diviProfitorintPay.equals(that.diviProfitorintPay) : that.diviProfitorintPay != null)
            return false;
        if (otherFinaPay != null ? !otherFinaPay.equals(that.otherFinaPay) : that.otherFinaPay != null) return false;
        if (finaFlowoutOther != null ? !finaFlowoutOther.equals(that.finaFlowoutOther) : that.finaFlowoutOther != null)
            return false;
        if (finaFlowoutBalance != null ? !finaFlowoutBalance.equals(that.finaFlowoutBalance) : that.finaFlowoutBalance != null)
            return false;
        if (sumFinaFlowout != null ? !sumFinaFlowout.equals(that.sumFinaFlowout) : that.sumFinaFlowout != null)
            return false;
        if (finaFlowOther != null ? !finaFlowOther.equals(that.finaFlowOther) : that.finaFlowOther != null)
            return false;
        if (finaFlowBalance != null ? !finaFlowBalance.equals(that.finaFlowBalance) : that.finaFlowBalance != null)
            return false;
        if (netFinaCashflow != null ? !netFinaCashflow.equals(that.netFinaCashflow) : that.netFinaCashflow != null)
            return false;
        if (effectExchangeRate != null ? !effectExchangeRate.equals(that.effectExchangeRate) : that.effectExchangeRate != null)
            return false;
        if (nicashEquiOther != null ? !nicashEquiOther.equals(that.nicashEquiOther) : that.nicashEquiOther != null)
            return false;
        if (nicashEquiBalance != null ? !nicashEquiBalance.equals(that.nicashEquiBalance) : that.nicashEquiBalance != null)
            return false;
        if (nicashEqui != null ? !nicashEqui.equals(that.nicashEqui) : that.nicashEqui != null) return false;
        if (cashEquiBeginning != null ? !cashEquiBeginning.equals(that.cashEquiBeginning) : that.cashEquiBeginning != null)
            return false;
        if (cashEquiEnding != null ? !cashEquiEnding.equals(that.cashEquiEnding) : that.cashEquiEnding != null)
            return false;
        if (netProfit != null ? !netProfit.equals(that.netProfit) : that.netProfit != null) return false;
        if (assetDevalue != null ? !assetDevalue.equals(that.assetDevalue) : that.assetDevalue != null) return false;
        if (fixedAssetEtcdepr != null ? !fixedAssetEtcdepr.equals(that.fixedAssetEtcdepr) : that.fixedAssetEtcdepr != null)
            return false;
        if (intangibleAssetAmor != null ? !intangibleAssetAmor.equals(that.intangibleAssetAmor) : that.intangibleAssetAmor != null)
            return false;
        if (ltdeferExpAmor != null ? !ltdeferExpAmor.equals(that.ltdeferExpAmor) : that.ltdeferExpAmor != null)
            return false;
        if (deferExpReduce != null ? !deferExpReduce.equals(that.deferExpReduce) : that.deferExpReduce != null)
            return false;
        if (drawingExpAdd != null ? !drawingExpAdd.equals(that.drawingExpAdd) : that.drawingExpAdd != null)
            return false;
        if (dispFilassetLoss != null ? !dispFilassetLoss.equals(that.dispFilassetLoss) : that.dispFilassetLoss != null)
            return false;
        if (fixedAssetLoss != null ? !fixedAssetLoss.equals(that.fixedAssetLoss) : that.fixedAssetLoss != null)
            return false;
        if (fvalueLoss != null ? !fvalueLoss.equals(that.fvalueLoss) : that.fvalueLoss != null) return false;
        if (financeExp != null ? !financeExp.equals(that.financeExp) : that.financeExp != null) return false;
        if (invLoss != null ? !invLoss.equals(that.invLoss) : that.invLoss != null) return false;
        if (deferTaxassetReduce != null ? !deferTaxassetReduce.equals(that.deferTaxassetReduce) : that.deferTaxassetReduce != null)
            return false;
        if (deferTaxliabAdd != null ? !deferTaxliabAdd.equals(that.deferTaxliabAdd) : that.deferTaxliabAdd != null)
            return false;
        if (inventoryReduce != null ? !inventoryReduce.equals(that.inventoryReduce) : that.inventoryReduce != null)
            return false;
        if (operateRecReduce != null ? !operateRecReduce.equals(that.operateRecReduce) : that.operateRecReduce != null)
            return false;
        if (operatePayAdd != null ? !operatePayAdd.equals(that.operatePayAdd) : that.operatePayAdd != null)
            return false;
        if (inoperateFlowOther != null ? !inoperateFlowOther.equals(that.inoperateFlowOther) : that.inoperateFlowOther != null)
            return false;
        if (inoperateFlowBalance != null ? !inoperateFlowBalance.equals(that.inoperateFlowBalance) : that.inoperateFlowBalance != null)
            return false;
        if (innetOperateCashflow != null ? !innetOperateCashflow.equals(that.innetOperateCashflow) : that.innetOperateCashflow != null)
            return false;
        if (debtToCapital != null ? !debtToCapital.equals(that.debtToCapital) : that.debtToCapital != null)
            return false;
        if (cbOneyear != null ? !cbOneyear.equals(that.cbOneyear) : that.cbOneyear != null) return false;
        if (finaleaseFixedAsset != null ? !finaleaseFixedAsset.equals(that.finaleaseFixedAsset) : that.finaleaseFixedAsset != null)
            return false;
        if (cashEnd != null ? !cashEnd.equals(that.cashEnd) : that.cashEnd != null) return false;
        if (cashBegin != null ? !cashBegin.equals(that.cashBegin) : that.cashBegin != null) return false;
        if (equiEnd != null ? !equiEnd.equals(that.equiEnd) : that.equiEnd != null) return false;
        if (equiBegin != null ? !equiBegin.equals(that.equiBegin) : that.equiBegin != null) return false;
        if (innicashEquiOther != null ? !innicashEquiOther.equals(that.innicashEquiOther) : that.innicashEquiOther != null)
            return false;
        if (innicashEquiBalance != null ? !innicashEquiBalance.equals(that.innicashEquiBalance) : that.innicashEquiBalance != null)
            return false;
        if (innicashEqui != null ? !innicashEqui.equals(that.innicashEqui) : that.innicashEqui != null) return false;
        if (other != null ? !other.equals(that.other) : that.other != null) return false;
        if (subsidiaryAccept != null ? !subsidiaryAccept.equals(that.subsidiaryAccept) : that.subsidiaryAccept != null)
            return false;
        if (subsidiaryPay != null ? !subsidiaryPay.equals(that.subsidiaryPay) : that.subsidiaryPay != null)
            return false;
        if (diviPay != null ? !diviPay.equals(that.diviPay) : that.diviPay != null) return false;
        if (intandcommRec != null ? !intandcommRec.equals(that.intandcommRec) : that.intandcommRec != null)
            return false;
        if (netRirec != null ? !netRirec.equals(that.netRirec) : that.netRirec != null) return false;
        if (nilendFund != null ? !nilendFund.equals(that.nilendFund) : that.nilendFund != null) return false;
        if (deferTax != null ? !deferTax.equals(that.deferTax) : that.deferTax != null) return false;
        if (deferIncomeAmor != null ? !deferIncomeAmor.equals(that.deferIncomeAmor) : that.deferIncomeAmor != null)
            return false;
        if (exchangeLoss != null ? !exchangeLoss.equals(that.exchangeLoss) : that.exchangeLoss != null) return false;
        if (fixandestateDepr != null ? !fixandestateDepr.equals(that.fixandestateDepr) : that.fixandestateDepr != null)
            return false;
        if (fixedAssetDepr != null ? !fixedAssetDepr.equals(that.fixedAssetDepr) : that.fixedAssetDepr != null)
            return false;
        if (tradefAssetReduce != null ? !tradefAssetReduce.equals(that.tradefAssetReduce) : that.tradefAssetReduce != null)
            return false;
        if (ndloanAdvances != null ? !ndloanAdvances.equals(that.ndloanAdvances) : that.ndloanAdvances != null)
            return false;
        if (reducePledgetDeposit != null ? !reducePledgetDeposit.equals(that.reducePledgetDeposit) : that.reducePledgetDeposit != null)
            return false;
        if (addPledgetDeposit != null ? !addPledgetDeposit.equals(that.addPledgetDeposit) : that.addPledgetDeposit != null)
            return false;
        if (buySubsidiaryPay != null ? !buySubsidiaryPay.equals(that.buySubsidiaryPay) : that.buySubsidiaryPay != null)
            return false;
        if (cashEquiendingOther != null ? !cashEquiendingOther.equals(that.cashEquiendingOther) : that.cashEquiendingOther != null)
            return false;
        if (cashEquiendingBalance != null ? !cashEquiendingBalance.equals(that.cashEquiendingBalance) : that.cashEquiendingBalance != null)
            return false;
        if (ndDepositincBankfi != null ? !ndDepositincBankfi.equals(that.ndDepositincBankfi) : that.ndDepositincBankfi != null)
            return false;
        if (niborrowSellBuyback != null ? !niborrowSellBuyback.equals(that.niborrowSellBuyback) : that.niborrowSellBuyback != null)
            return false;
        if (ndlendBuySellback != null ? !ndlendBuySellback.equals(that.ndlendBuySellback) : that.ndlendBuySellback != null)
            return false;
        if (netCd != null ? !netCd.equals(that.netCd) : that.netCd != null) return false;
        if (nitradeFliab != null ? !nitradeFliab.equals(that.nitradeFliab) : that.nitradeFliab != null) return false;
        if (ndtradeFasset != null ? !ndtradeFasset.equals(that.ndtradeFasset) : that.ndtradeFasset != null)
            return false;
        if (dispMassetRec != null ? !dispMassetRec.equals(that.dispMassetRec) : that.dispMassetRec != null)
            return false;
        if (cancelLoanRec != null ? !cancelLoanRec.equals(that.cancelLoanRec) : that.cancelLoanRec != null)
            return false;
        if (ndborrowFromCbank != null ? !ndborrowFromCbank.equals(that.ndborrowFromCbank) : that.ndborrowFromCbank != null)
            return false;
        if (ndfidePosit != null ? !ndfidePosit.equals(that.ndfidePosit) : that.ndfidePosit != null) return false;
        if (ndissueCd != null ? !ndissueCd.equals(that.ndissueCd) : that.ndissueCd != null) return false;
        if (nilendSellBuyback != null ? !nilendSellBuyback.equals(that.nilendSellBuyback) : that.nilendSellBuyback != null)
            return false;
        if (ndborrowSellBuyback != null ? !ndborrowSellBuyback.equals(that.ndborrowSellBuyback) : that.ndborrowSellBuyback != null)
            return false;
        if (nitradeFasset != null ? !nitradeFasset.equals(that.nitradeFasset) : that.nitradeFasset != null)
            return false;
        if (ndtradeFliab != null ? !ndtradeFliab.equals(that.ndtradeFliab) : that.ndtradeFliab != null) return false;
        if (buyFinaleaseassetPay != null ? !buyFinaleaseassetPay.equals(that.buyFinaleaseassetPay) : that.buyFinaleaseassetPay != null)
            return false;
        if (niaccountRec != null ? !niaccountRec.equals(that.niaccountRec) : that.niaccountRec != null) return false;
        if (issueCd != null ? !issueCd.equals(that.issueCd) : that.issueCd != null) return false;
        if (addshareCapitalRec != null ? !addshareCapitalRec.equals(that.addshareCapitalRec) : that.addshareCapitalRec != null)
            return false;
        if (issueShareRec != null ? !issueShareRec.equals(that.issueShareRec) : that.issueShareRec != null)
            return false;
        if (bondIntpay != null ? !bondIntpay.equals(that.bondIntpay) : that.bondIntpay != null) return false;
        if (niotherFinainstru != null ? !niotherFinainstru.equals(that.niotherFinainstru) : that.niotherFinainstru != null)
            return false;
        if (agentTradeSecurityrec != null ? !agentTradeSecurityrec.equals(that.agentTradeSecurityrec) : that.agentTradeSecurityrec != null)
            return false;
        if (uwsecurityRec != null ? !uwsecurityRec.equals(that.uwsecurityRec) : that.uwsecurityRec != null)
            return false;
        if (buysellbackFassetRec != null ? !buysellbackFassetRec.equals(that.buysellbackFassetRec) : that.buysellbackFassetRec != null)
            return false;
        if (agentUwsecurityRec != null ? !agentUwsecurityRec.equals(that.agentUwsecurityRec) : that.agentUwsecurityRec != null)
            return false;
        if (nidirectInv != null ? !nidirectInv.equals(that.nidirectInv) : that.nidirectInv != null) return false;
        if (nitradeSettlement != null ? !nitradeSettlement.equals(that.nitradeSettlement) : that.nitradeSettlement != null)
            return false;
        if (buysellbackFassetPay != null ? !buysellbackFassetPay.equals(that.buysellbackFassetPay) : that.buysellbackFassetPay != null)
            return false;
        if (nddispTradeFasset != null ? !nddispTradeFasset.equals(that.nddispTradeFasset) : that.nddispTradeFasset != null)
            return false;
        if (ndotherFinaInstr != null ? !ndotherFinaInstr.equals(that.ndotherFinaInstr) : that.ndotherFinaInstr != null)
            return false;
        if (ndborrowFund != null ? !ndborrowFund.equals(that.ndborrowFund) : that.ndborrowFund != null) return false;
        if (nddirectInv != null ? !nddirectInv.equals(that.nddirectInv) : that.nddirectInv != null) return false;
        if (ndtradeSettlement != null ? !ndtradeSettlement.equals(that.ndtradeSettlement) : that.ndtradeSettlement != null)
            return false;
        if (ndbuybackFund != null ? !ndbuybackFund.equals(that.ndbuybackFund) : that.ndbuybackFund != null)
            return false;
        if (agenttradeSecurityPay != null ? !agenttradeSecurityPay.equals(that.agenttradeSecurityPay) : that.agenttradeSecurityPay != null)
            return false;
        if (nddispSaleableFasset != null ? !nddispSaleableFasset.equals(that.nddispSaleableFasset) : that.nddispSaleableFasset != null)
            return false;
        if (nisellBuyback != null ? !nisellBuyback.equals(that.nisellBuyback) : that.nisellBuyback != null)
            return false;
        if (ndbuySellback != null ? !ndbuySellback.equals(that.ndbuySellback) : that.ndbuySellback != null)
            return false;
        if (nettradeFassetRec != null ? !nettradeFassetRec.equals(that.nettradeFassetRec) : that.nettradeFassetRec != null)
            return false;
        if (netRipay != null ? !netRipay.equals(that.netRipay) : that.netRipay != null) return false;
        if (ndlendFund != null ? !ndlendFund.equals(that.ndlendFund) : that.ndlendFund != null) return false;
        if (nibuySellback != null ? !nibuySellback.equals(that.nibuySellback) : that.nibuySellback != null)
            return false;
        if (ndsellBuyback != null ? !ndsellBuyback.equals(that.ndsellBuyback) : that.ndsellBuyback != null)
            return false;
        if (ndinsuredDepositInv != null ? !ndinsuredDepositInv.equals(that.ndinsuredDepositInv) : that.ndinsuredDepositInv != null)
            return false;
        if (nettradeFassetPay != null ? !nettradeFassetPay.equals(that.nettradeFassetPay) : that.nettradeFassetPay != null)
            return false;
        if (niinsuredPledgeLoan != null ? !niinsuredPledgeLoan.equals(that.niinsuredPledgeLoan) : that.niinsuredPledgeLoan != null)
            return false;
        if (dispSubsidiaryPay != null ? !dispSubsidiaryPay.equals(that.dispSubsidiaryPay) : that.dispSubsidiaryPay != null)
            return false;
        if (netsellBuybackFassetrec != null ? !netsellBuybackFassetrec.equals(that.netsellBuybackFassetrec) : that.netsellBuybackFassetrec != null)
            return false;
        if (netsellBuybackFassetpay != null ? !netsellBuybackFassetpay.equals(that.netsellBuybackFassetpay) : that.netsellBuybackFassetpay != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (chkStatus != null ? !chkStatus.equals(that.chkStatus) : that.chkStatus != null) return false;
        if (isdel != null ? !isdel.equals(that.isdel) : that.isdel != null) return false;
        if (srcCompanyCd != null ? !srcCompanyCd.equals(that.srcCompanyCd) : that.srcCompanyCd != null) return false;
        if (srcid != null ? !srcid.equals(that.srcid) : that.srcid != null) return false;
        if (srcCd != null ? !srcCd.equals(that.srcCd) : that.srcCd != null) return false;
        return updtDt != null ? updtDt.equals(that.updtDt) : that.updtDt == null;

    }

    @Override
    public int hashCode() {
        int result = compyCashflowSid != null ? compyCashflowSid.hashCode() : 0;
        result = 31 * result + (firstNoticeDt != null ? firstNoticeDt.hashCode() : 0);
        result = 31 * result + (latestNoticeDt != null ? latestNoticeDt.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (rptDt != null ? rptDt.hashCode() : 0);
        result = 31 * result + (startDt != null ? startDt.hashCode() : 0);
        result = 31 * result + (endDt != null ? endDt.hashCode() : 0);
        result = 31 * result + (rptTimetypeCd != null ? rptTimetypeCd.hashCode() : 0);
        result = 31 * result + (combineTypeCd != null ? combineTypeCd.hashCode() : 0);
        result = 31 * result + (rptSrctypeId != null ? rptSrctypeId.hashCode() : 0);
        result = 31 * result + (dataAjustType != null ? dataAjustType.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (isPublicRpt != null ? isPublicRpt.hashCode() : 0);
        result = 31 * result + (companyType != null ? companyType.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (salegoodsServiceRec != null ? salegoodsServiceRec.hashCode() : 0);
        result = 31 * result + (taxReturnRec != null ? taxReturnRec.hashCode() : 0);
        result = 31 * result + (otherOperateRec != null ? otherOperateRec.hashCode() : 0);
        result = 31 * result + (niDeposit != null ? niDeposit.hashCode() : 0);
        result = 31 * result + (niborrowFromCbank != null ? niborrowFromCbank.hashCode() : 0);
        result = 31 * result + (niborrowFromFi != null ? niborrowFromFi.hashCode() : 0);
        result = 31 * result + (premiumRec != null ? premiumRec.hashCode() : 0);
        result = 31 * result + (nidispTradeFasset != null ? nidispTradeFasset.hashCode() : 0);
        result = 31 * result + (nidispSaleableFasset != null ? nidispSaleableFasset.hashCode() : 0);
        result = 31 * result + (niborrowFund != null ? niborrowFund.hashCode() : 0);
        result = 31 * result + (nibuybackFund != null ? nibuybackFund.hashCode() : 0);
        result = 31 * result + (operateFlowinOther != null ? operateFlowinOther.hashCode() : 0);
        result = 31 * result + (operateFlowinBalance != null ? operateFlowinBalance.hashCode() : 0);
        result = 31 * result + (sumOperateFlowin != null ? sumOperateFlowin.hashCode() : 0);
        result = 31 * result + (buygoodsServicePay != null ? buygoodsServicePay.hashCode() : 0);
        result = 31 * result + (employeePay != null ? employeePay.hashCode() : 0);
        result = 31 * result + (taxPay != null ? taxPay.hashCode() : 0);
        result = 31 * result + (otherOperatEpay != null ? otherOperatEpay.hashCode() : 0);
        result = 31 * result + (niloanAdvances != null ? niloanAdvances.hashCode() : 0);
        result = 31 * result + (nidepositIncbankfi != null ? nidepositIncbankfi.hashCode() : 0);
        result = 31 * result + (indemnityPay != null ? indemnityPay.hashCode() : 0);
        result = 31 * result + (intandcommPay != null ? intandcommPay.hashCode() : 0);
        result = 31 * result + (operateFlowoutOther != null ? operateFlowoutOther.hashCode() : 0);
        result = 31 * result + (operateFlowoutBalance != null ? operateFlowoutBalance.hashCode() : 0);
        result = 31 * result + (sumOperateFlowout != null ? sumOperateFlowout.hashCode() : 0);
        result = 31 * result + (operateFlowOther != null ? operateFlowOther.hashCode() : 0);
        result = 31 * result + (operateFlowBalance != null ? operateFlowBalance.hashCode() : 0);
        result = 31 * result + (netOperateCashflow != null ? netOperateCashflow.hashCode() : 0);
        result = 31 * result + (disposalInvRec != null ? disposalInvRec.hashCode() : 0);
        result = 31 * result + (invIncomeRec != null ? invIncomeRec.hashCode() : 0);
        result = 31 * result + (dispFilassetRec != null ? dispFilassetRec.hashCode() : 0);
        result = 31 * result + (dispSubsidiaryRec != null ? dispSubsidiaryRec.hashCode() : 0);
        result = 31 * result + (otherInvrec != null ? otherInvrec.hashCode() : 0);
        result = 31 * result + (invFlowinOther != null ? invFlowinOther.hashCode() : 0);
        result = 31 * result + (invFlowinBalance != null ? invFlowinBalance.hashCode() : 0);
        result = 31 * result + (sumInvFlowin != null ? sumInvFlowin.hashCode() : 0);
        result = 31 * result + (buyFilassetPay != null ? buyFilassetPay.hashCode() : 0);
        result = 31 * result + (invPay != null ? invPay.hashCode() : 0);
        result = 31 * result + (getSubsidiaryPay != null ? getSubsidiaryPay.hashCode() : 0);
        result = 31 * result + (otherInvPay != null ? otherInvPay.hashCode() : 0);
        result = 31 * result + (nipledgeLoan != null ? nipledgeLoan.hashCode() : 0);
        result = 31 * result + (invFlowoutOther != null ? invFlowoutOther.hashCode() : 0);
        result = 31 * result + (invFlowoutBalance != null ? invFlowoutBalance.hashCode() : 0);
        result = 31 * result + (sumInvFlowout != null ? sumInvFlowout.hashCode() : 0);
        result = 31 * result + (invFlowOther != null ? invFlowOther.hashCode() : 0);
        result = 31 * result + (invCashflowBalance != null ? invCashflowBalance.hashCode() : 0);
        result = 31 * result + (netInvCashflow != null ? netInvCashflow.hashCode() : 0);
        result = 31 * result + (acceptInvRec != null ? acceptInvRec.hashCode() : 0);
        result = 31 * result + (loanRec != null ? loanRec.hashCode() : 0);
        result = 31 * result + (otherFinaRec != null ? otherFinaRec.hashCode() : 0);
        result = 31 * result + (issueBondRec != null ? issueBondRec.hashCode() : 0);
        result = 31 * result + (niinsuredDepositInv != null ? niinsuredDepositInv.hashCode() : 0);
        result = 31 * result + (finaFlowinOther != null ? finaFlowinOther.hashCode() : 0);
        result = 31 * result + (finaFlowinBalance != null ? finaFlowinBalance.hashCode() : 0);
        result = 31 * result + (sumFinaFlowin != null ? sumFinaFlowin.hashCode() : 0);
        result = 31 * result + (repayDebtPay != null ? repayDebtPay.hashCode() : 0);
        result = 31 * result + (diviProfitorintPay != null ? diviProfitorintPay.hashCode() : 0);
        result = 31 * result + (otherFinaPay != null ? otherFinaPay.hashCode() : 0);
        result = 31 * result + (finaFlowoutOther != null ? finaFlowoutOther.hashCode() : 0);
        result = 31 * result + (finaFlowoutBalance != null ? finaFlowoutBalance.hashCode() : 0);
        result = 31 * result + (sumFinaFlowout != null ? sumFinaFlowout.hashCode() : 0);
        result = 31 * result + (finaFlowOther != null ? finaFlowOther.hashCode() : 0);
        result = 31 * result + (finaFlowBalance != null ? finaFlowBalance.hashCode() : 0);
        result = 31 * result + (netFinaCashflow != null ? netFinaCashflow.hashCode() : 0);
        result = 31 * result + (effectExchangeRate != null ? effectExchangeRate.hashCode() : 0);
        result = 31 * result + (nicashEquiOther != null ? nicashEquiOther.hashCode() : 0);
        result = 31 * result + (nicashEquiBalance != null ? nicashEquiBalance.hashCode() : 0);
        result = 31 * result + (nicashEqui != null ? nicashEqui.hashCode() : 0);
        result = 31 * result + (cashEquiBeginning != null ? cashEquiBeginning.hashCode() : 0);
        result = 31 * result + (cashEquiEnding != null ? cashEquiEnding.hashCode() : 0);
        result = 31 * result + (netProfit != null ? netProfit.hashCode() : 0);
        result = 31 * result + (assetDevalue != null ? assetDevalue.hashCode() : 0);
        result = 31 * result + (fixedAssetEtcdepr != null ? fixedAssetEtcdepr.hashCode() : 0);
        result = 31 * result + (intangibleAssetAmor != null ? intangibleAssetAmor.hashCode() : 0);
        result = 31 * result + (ltdeferExpAmor != null ? ltdeferExpAmor.hashCode() : 0);
        result = 31 * result + (deferExpReduce != null ? deferExpReduce.hashCode() : 0);
        result = 31 * result + (drawingExpAdd != null ? drawingExpAdd.hashCode() : 0);
        result = 31 * result + (dispFilassetLoss != null ? dispFilassetLoss.hashCode() : 0);
        result = 31 * result + (fixedAssetLoss != null ? fixedAssetLoss.hashCode() : 0);
        result = 31 * result + (fvalueLoss != null ? fvalueLoss.hashCode() : 0);
        result = 31 * result + (financeExp != null ? financeExp.hashCode() : 0);
        result = 31 * result + (invLoss != null ? invLoss.hashCode() : 0);
        result = 31 * result + (deferTaxassetReduce != null ? deferTaxassetReduce.hashCode() : 0);
        result = 31 * result + (deferTaxliabAdd != null ? deferTaxliabAdd.hashCode() : 0);
        result = 31 * result + (inventoryReduce != null ? inventoryReduce.hashCode() : 0);
        result = 31 * result + (operateRecReduce != null ? operateRecReduce.hashCode() : 0);
        result = 31 * result + (operatePayAdd != null ? operatePayAdd.hashCode() : 0);
        result = 31 * result + (inoperateFlowOther != null ? inoperateFlowOther.hashCode() : 0);
        result = 31 * result + (inoperateFlowBalance != null ? inoperateFlowBalance.hashCode() : 0);
        result = 31 * result + (innetOperateCashflow != null ? innetOperateCashflow.hashCode() : 0);
        result = 31 * result + (debtToCapital != null ? debtToCapital.hashCode() : 0);
        result = 31 * result + (cbOneyear != null ? cbOneyear.hashCode() : 0);
        result = 31 * result + (finaleaseFixedAsset != null ? finaleaseFixedAsset.hashCode() : 0);
        result = 31 * result + (cashEnd != null ? cashEnd.hashCode() : 0);
        result = 31 * result + (cashBegin != null ? cashBegin.hashCode() : 0);
        result = 31 * result + (equiEnd != null ? equiEnd.hashCode() : 0);
        result = 31 * result + (equiBegin != null ? equiBegin.hashCode() : 0);
        result = 31 * result + (innicashEquiOther != null ? innicashEquiOther.hashCode() : 0);
        result = 31 * result + (innicashEquiBalance != null ? innicashEquiBalance.hashCode() : 0);
        result = 31 * result + (innicashEqui != null ? innicashEqui.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        result = 31 * result + (subsidiaryAccept != null ? subsidiaryAccept.hashCode() : 0);
        result = 31 * result + (subsidiaryPay != null ? subsidiaryPay.hashCode() : 0);
        result = 31 * result + (diviPay != null ? diviPay.hashCode() : 0);
        result = 31 * result + (intandcommRec != null ? intandcommRec.hashCode() : 0);
        result = 31 * result + (netRirec != null ? netRirec.hashCode() : 0);
        result = 31 * result + (nilendFund != null ? nilendFund.hashCode() : 0);
        result = 31 * result + (deferTax != null ? deferTax.hashCode() : 0);
        result = 31 * result + (deferIncomeAmor != null ? deferIncomeAmor.hashCode() : 0);
        result = 31 * result + (exchangeLoss != null ? exchangeLoss.hashCode() : 0);
        result = 31 * result + (fixandestateDepr != null ? fixandestateDepr.hashCode() : 0);
        result = 31 * result + (fixedAssetDepr != null ? fixedAssetDepr.hashCode() : 0);
        result = 31 * result + (tradefAssetReduce != null ? tradefAssetReduce.hashCode() : 0);
        result = 31 * result + (ndloanAdvances != null ? ndloanAdvances.hashCode() : 0);
        result = 31 * result + (reducePledgetDeposit != null ? reducePledgetDeposit.hashCode() : 0);
        result = 31 * result + (addPledgetDeposit != null ? addPledgetDeposit.hashCode() : 0);
        result = 31 * result + (buySubsidiaryPay != null ? buySubsidiaryPay.hashCode() : 0);
        result = 31 * result + (cashEquiendingOther != null ? cashEquiendingOther.hashCode() : 0);
        result = 31 * result + (cashEquiendingBalance != null ? cashEquiendingBalance.hashCode() : 0);
        result = 31 * result + (ndDepositincBankfi != null ? ndDepositincBankfi.hashCode() : 0);
        result = 31 * result + (niborrowSellBuyback != null ? niborrowSellBuyback.hashCode() : 0);
        result = 31 * result + (ndlendBuySellback != null ? ndlendBuySellback.hashCode() : 0);
        result = 31 * result + (netCd != null ? netCd.hashCode() : 0);
        result = 31 * result + (nitradeFliab != null ? nitradeFliab.hashCode() : 0);
        result = 31 * result + (ndtradeFasset != null ? ndtradeFasset.hashCode() : 0);
        result = 31 * result + (dispMassetRec != null ? dispMassetRec.hashCode() : 0);
        result = 31 * result + (cancelLoanRec != null ? cancelLoanRec.hashCode() : 0);
        result = 31 * result + (ndborrowFromCbank != null ? ndborrowFromCbank.hashCode() : 0);
        result = 31 * result + (ndfidePosit != null ? ndfidePosit.hashCode() : 0);
        result = 31 * result + (ndissueCd != null ? ndissueCd.hashCode() : 0);
        result = 31 * result + (nilendSellBuyback != null ? nilendSellBuyback.hashCode() : 0);
        result = 31 * result + (ndborrowSellBuyback != null ? ndborrowSellBuyback.hashCode() : 0);
        result = 31 * result + (nitradeFasset != null ? nitradeFasset.hashCode() : 0);
        result = 31 * result + (ndtradeFliab != null ? ndtradeFliab.hashCode() : 0);
        result = 31 * result + (buyFinaleaseassetPay != null ? buyFinaleaseassetPay.hashCode() : 0);
        result = 31 * result + (niaccountRec != null ? niaccountRec.hashCode() : 0);
        result = 31 * result + (issueCd != null ? issueCd.hashCode() : 0);
        result = 31 * result + (addshareCapitalRec != null ? addshareCapitalRec.hashCode() : 0);
        result = 31 * result + (issueShareRec != null ? issueShareRec.hashCode() : 0);
        result = 31 * result + (bondIntpay != null ? bondIntpay.hashCode() : 0);
        result = 31 * result + (niotherFinainstru != null ? niotherFinainstru.hashCode() : 0);
        result = 31 * result + (agentTradeSecurityrec != null ? agentTradeSecurityrec.hashCode() : 0);
        result = 31 * result + (uwsecurityRec != null ? uwsecurityRec.hashCode() : 0);
        result = 31 * result + (buysellbackFassetRec != null ? buysellbackFassetRec.hashCode() : 0);
        result = 31 * result + (agentUwsecurityRec != null ? agentUwsecurityRec.hashCode() : 0);
        result = 31 * result + (nidirectInv != null ? nidirectInv.hashCode() : 0);
        result = 31 * result + (nitradeSettlement != null ? nitradeSettlement.hashCode() : 0);
        result = 31 * result + (buysellbackFassetPay != null ? buysellbackFassetPay.hashCode() : 0);
        result = 31 * result + (nddispTradeFasset != null ? nddispTradeFasset.hashCode() : 0);
        result = 31 * result + (ndotherFinaInstr != null ? ndotherFinaInstr.hashCode() : 0);
        result = 31 * result + (ndborrowFund != null ? ndborrowFund.hashCode() : 0);
        result = 31 * result + (nddirectInv != null ? nddirectInv.hashCode() : 0);
        result = 31 * result + (ndtradeSettlement != null ? ndtradeSettlement.hashCode() : 0);
        result = 31 * result + (ndbuybackFund != null ? ndbuybackFund.hashCode() : 0);
        result = 31 * result + (agenttradeSecurityPay != null ? agenttradeSecurityPay.hashCode() : 0);
        result = 31 * result + (nddispSaleableFasset != null ? nddispSaleableFasset.hashCode() : 0);
        result = 31 * result + (nisellBuyback != null ? nisellBuyback.hashCode() : 0);
        result = 31 * result + (ndbuySellback != null ? ndbuySellback.hashCode() : 0);
        result = 31 * result + (nettradeFassetRec != null ? nettradeFassetRec.hashCode() : 0);
        result = 31 * result + (netRipay != null ? netRipay.hashCode() : 0);
        result = 31 * result + (ndlendFund != null ? ndlendFund.hashCode() : 0);
        result = 31 * result + (nibuySellback != null ? nibuySellback.hashCode() : 0);
        result = 31 * result + (ndsellBuyback != null ? ndsellBuyback.hashCode() : 0);
        result = 31 * result + (ndinsuredDepositInv != null ? ndinsuredDepositInv.hashCode() : 0);
        result = 31 * result + (nettradeFassetPay != null ? nettradeFassetPay.hashCode() : 0);
        result = 31 * result + (niinsuredPledgeLoan != null ? niinsuredPledgeLoan.hashCode() : 0);
        result = 31 * result + (dispSubsidiaryPay != null ? dispSubsidiaryPay.hashCode() : 0);
        result = 31 * result + (netsellBuybackFassetrec != null ? netsellBuybackFassetrec.hashCode() : 0);
        result = 31 * result + (netsellBuybackFassetpay != null ? netsellBuybackFassetpay.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (chkStatus != null ? chkStatus.hashCode() : 0);
        result = 31 * result + (isdel != null ? isdel.hashCode() : 0);
        result = 31 * result + (srcCompanyCd != null ? srcCompanyCd.hashCode() : 0);
        result = 31 * result + (srcid != null ? srcid.hashCode() : 0);
        result = 31 * result + (srcCd != null ? srcCd.hashCode() : 0);
        result = 31 * result + (updtDt != null ? updtDt.hashCode() : 0);
        return result;
    }
}
