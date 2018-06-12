package com.cscs.listedfacesys.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by hj on 2018/2/27.
 */
@Entity
@Table(name = "COMPY_INCOMESTATE", schema = "CS_FACEBOOK_1", catalog = "")
public class CompyIncomestate {
    private Long compyIncomestateSid;
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
    private BigDecimal operateReve;
    private BigDecimal operateExp;
    private Long operateTax;
    private Long saleExp;
    private Long manageExp;
    private Long financeExp;
    private Long assetDevalueLoss;
    private Long fvalueIncome;
    private Long investIncome;
    private Long intnReve;
    private Long intReve;
    private Long intExp;
    private Long commnReve;
    private Long commReve;
    private Long commExp;
    private Long exchangeIncome;
    private Long premiumEarned;
    private Long premiumIncome;
    private Long ripremium;
    private Long undueReserve;
    private Long premiumExp;
    private Long indemnityExp;
    private Long amortiseIndemnityExp;
    private Long dutyReserve;
    private Long amortiseDutyReserve;
    private Long rireve;
    private Long riexp;
    private Long surrenderPremium;
    private Long policyDiviExp;
    private Long amortiseRiexp;
    private Long agentTradeSecurity;
    private Long securityUw;
    private Long clientAssetManage;
    private Long operateProfitOther;
    private Long operateProfitBalance;
    private BigDecimal operateProfit;
    private Long nonoperateReve;
    private Long nonoperateExp;
    private Long nonlassetNetLoss;
    private Long sumProfitOther;
    private Long sumProfitBalance;
    private BigDecimal sumProfit;
    private Long incomeTax;
    private Long netProfitOther2;
    private Long netProfitBalance1;
    private Long netProfitBalance2;
    private BigDecimal netProfit;
    private Long parentNetProfit;
    private Long minorityIncome;
    private Long undistributeProfit;
    private Long basicEps;
    private Long dilutedEps;
    private Long investJointIncome;
    private Long totalOperateReve;
    private Long totalOperateExp;
    private Long otherReve;
    private Long otherExp;
    private Long unconfirmInvloss;
    private Long otherCincome;
    private Long sumCincome;
    private Long parentCincome;
    private Long minorityCincome;
    private Long netContactReserve;
    private Long rdexp;
    private Long operateManageExp;
    private Long insurReve;
    private Long nonlassetReve;
    private Long totalOperatereveOther;
    private Long netIndemnityExp;
    private Long totalOperateexpOther;
    private Long netProfitOther1;
    private Long cincomeBalance1;
    private Long cincomeBalance2;
    private Long otherNetIncome;
    private Long reveOther;
    private Long reveBalance;
    private Long operateExpOther;
    private Long operateExpBalance;
    private Long bankIntnreve;
    private Long bankIntreve;
    private Long ninsurCommnReve;
    private Long ninsurCommReve;
    private Long ninsurCommExp;
    private String remark;
    private String chkStatus;
    private Long isdel;
    private String srcCompanyCd;
    private String srcid;
    private String srcCd;
    private Timestamp updtDt;

    @Id
    @Column(name = "COMPY_INCOMESTATE_SID", nullable = false, precision = 0)
    public Long getCompyIncomestateSid() {
        return compyIncomestateSid;
    }

    public void setCompyIncomestateSid(Long compyIncomestateSid) {
        this.compyIncomestateSid = compyIncomestateSid;
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
    @Column(name = "OPERATE_REVE", nullable = true, precision = 4)
    public BigDecimal getOperateReve() {
        return operateReve;
    }

    public void setOperateReve(BigDecimal operateReve) {
        this.operateReve = operateReve;
    }

    @Basic
    @Column(name = "OPERATE_EXP", nullable = true, precision = 4)
    public BigDecimal getOperateExp() {
        return operateExp;
    }

    public void setOperateExp(BigDecimal operateExp) {
        this.operateExp = operateExp;
    }

    @Basic
    @Column(name = "OPERATE_TAX", nullable = true, precision = 4)
    public Long getOperateTax() {
        return operateTax;
    }

    public void setOperateTax(Long operateTax) {
        this.operateTax = operateTax;
    }

    @Basic
    @Column(name = "SALE_EXP", nullable = true, precision = 4)
    public Long getSaleExp() {
        return saleExp;
    }

    public void setSaleExp(Long saleExp) {
        this.saleExp = saleExp;
    }

    @Basic
    @Column(name = "MANAGE_EXP", nullable = true, precision = 4)
    public Long getManageExp() {
        return manageExp;
    }

    public void setManageExp(Long manageExp) {
        this.manageExp = manageExp;
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
    @Column(name = "ASSET_DEVALUE_LOSS", nullable = true, precision = 4)
    public Long getAssetDevalueLoss() {
        return assetDevalueLoss;
    }

    public void setAssetDevalueLoss(Long assetDevalueLoss) {
        this.assetDevalueLoss = assetDevalueLoss;
    }

    @Basic
    @Column(name = "FVALUE_INCOME", nullable = true, precision = 4)
    public Long getFvalueIncome() {
        return fvalueIncome;
    }

    public void setFvalueIncome(Long fvalueIncome) {
        this.fvalueIncome = fvalueIncome;
    }

    @Basic
    @Column(name = "INVEST_INCOME", nullable = true, precision = 4)
    public Long getInvestIncome() {
        return investIncome;
    }

    public void setInvestIncome(Long investIncome) {
        this.investIncome = investIncome;
    }

    @Basic
    @Column(name = "INTN_REVE", nullable = true, precision = 4)
    public Long getIntnReve() {
        return intnReve;
    }

    public void setIntnReve(Long intnReve) {
        this.intnReve = intnReve;
    }

    @Basic
    @Column(name = "INT_REVE", nullable = true, precision = 4)
    public Long getIntReve() {
        return intReve;
    }

    public void setIntReve(Long intReve) {
        this.intReve = intReve;
    }

    @Basic
    @Column(name = "INT_EXP", nullable = true, precision = 4)
    public Long getIntExp() {
        return intExp;
    }

    public void setIntExp(Long intExp) {
        this.intExp = intExp;
    }

    @Basic
    @Column(name = "COMMN_REVE", nullable = true, precision = 4)
    public Long getCommnReve() {
        return commnReve;
    }

    public void setCommnReve(Long commnReve) {
        this.commnReve = commnReve;
    }

    @Basic
    @Column(name = "COMM_REVE", nullable = true, precision = 4)
    public Long getCommReve() {
        return commReve;
    }

    public void setCommReve(Long commReve) {
        this.commReve = commReve;
    }

    @Basic
    @Column(name = "COMM_EXP", nullable = true, precision = 4)
    public Long getCommExp() {
        return commExp;
    }

    public void setCommExp(Long commExp) {
        this.commExp = commExp;
    }

    @Basic
    @Column(name = "EXCHANGE_INCOME", nullable = true, precision = 4)
    public Long getExchangeIncome() {
        return exchangeIncome;
    }

    public void setExchangeIncome(Long exchangeIncome) {
        this.exchangeIncome = exchangeIncome;
    }

    @Basic
    @Column(name = "PREMIUM_EARNED", nullable = true, precision = 4)
    public Long getPremiumEarned() {
        return premiumEarned;
    }

    public void setPremiumEarned(Long premiumEarned) {
        this.premiumEarned = premiumEarned;
    }

    @Basic
    @Column(name = "PREMIUM_INCOME", nullable = true, precision = 4)
    public Long getPremiumIncome() {
        return premiumIncome;
    }

    public void setPremiumIncome(Long premiumIncome) {
        this.premiumIncome = premiumIncome;
    }

    @Basic
    @Column(name = "RIPREMIUM", nullable = true, precision = 4)
    public Long getRipremium() {
        return ripremium;
    }

    public void setRipremium(Long ripremium) {
        this.ripremium = ripremium;
    }

    @Basic
    @Column(name = "UNDUE_RESERVE", nullable = true, precision = 4)
    public Long getUndueReserve() {
        return undueReserve;
    }

    public void setUndueReserve(Long undueReserve) {
        this.undueReserve = undueReserve;
    }

    @Basic
    @Column(name = "PREMIUM_EXP", nullable = true, precision = 4)
    public Long getPremiumExp() {
        return premiumExp;
    }

    public void setPremiumExp(Long premiumExp) {
        this.premiumExp = premiumExp;
    }

    @Basic
    @Column(name = "INDEMNITY_EXP", nullable = true, precision = 4)
    public Long getIndemnityExp() {
        return indemnityExp;
    }

    public void setIndemnityExp(Long indemnityExp) {
        this.indemnityExp = indemnityExp;
    }

    @Basic
    @Column(name = "AMORTISE_INDEMNITY_EXP", nullable = true, precision = 4)
    public Long getAmortiseIndemnityExp() {
        return amortiseIndemnityExp;
    }

    public void setAmortiseIndemnityExp(Long amortiseIndemnityExp) {
        this.amortiseIndemnityExp = amortiseIndemnityExp;
    }

    @Basic
    @Column(name = "DUTY_RESERVE", nullable = true, precision = 4)
    public Long getDutyReserve() {
        return dutyReserve;
    }

    public void setDutyReserve(Long dutyReserve) {
        this.dutyReserve = dutyReserve;
    }

    @Basic
    @Column(name = "AMORTISE_DUTY_RESERVE", nullable = true, precision = 4)
    public Long getAmortiseDutyReserve() {
        return amortiseDutyReserve;
    }

    public void setAmortiseDutyReserve(Long amortiseDutyReserve) {
        this.amortiseDutyReserve = amortiseDutyReserve;
    }

    @Basic
    @Column(name = "RIREVE", nullable = true, precision = 4)
    public Long getRireve() {
        return rireve;
    }

    public void setRireve(Long rireve) {
        this.rireve = rireve;
    }

    @Basic
    @Column(name = "RIEXP", nullable = true, precision = 4)
    public Long getRiexp() {
        return riexp;
    }

    public void setRiexp(Long riexp) {
        this.riexp = riexp;
    }

    @Basic
    @Column(name = "SURRENDER_PREMIUM", nullable = true, precision = 4)
    public Long getSurrenderPremium() {
        return surrenderPremium;
    }

    public void setSurrenderPremium(Long surrenderPremium) {
        this.surrenderPremium = surrenderPremium;
    }

    @Basic
    @Column(name = "POLICY_DIVI_EXP", nullable = true, precision = 4)
    public Long getPolicyDiviExp() {
        return policyDiviExp;
    }

    public void setPolicyDiviExp(Long policyDiviExp) {
        this.policyDiviExp = policyDiviExp;
    }

    @Basic
    @Column(name = "AMORTISE_RIEXP", nullable = true, precision = 4)
    public Long getAmortiseRiexp() {
        return amortiseRiexp;
    }

    public void setAmortiseRiexp(Long amortiseRiexp) {
        this.amortiseRiexp = amortiseRiexp;
    }

    @Basic
    @Column(name = "AGENT_TRADE_SECURITY", nullable = true, precision = 4)
    public Long getAgentTradeSecurity() {
        return agentTradeSecurity;
    }

    public void setAgentTradeSecurity(Long agentTradeSecurity) {
        this.agentTradeSecurity = agentTradeSecurity;
    }

    @Basic
    @Column(name = "SECURITY_UW", nullable = true, precision = 4)
    public Long getSecurityUw() {
        return securityUw;
    }

    public void setSecurityUw(Long securityUw) {
        this.securityUw = securityUw;
    }

    @Basic
    @Column(name = "CLIENT_ASSET_MANAGE", nullable = true, precision = 4)
    public Long getClientAssetManage() {
        return clientAssetManage;
    }

    public void setClientAssetManage(Long clientAssetManage) {
        this.clientAssetManage = clientAssetManage;
    }

    @Basic
    @Column(name = "OPERATE_PROFIT_OTHER", nullable = true, precision = 4)
    public Long getOperateProfitOther() {
        return operateProfitOther;
    }

    public void setOperateProfitOther(Long operateProfitOther) {
        this.operateProfitOther = operateProfitOther;
    }

    @Basic
    @Column(name = "OPERATE_PROFIT_BALANCE", nullable = true, precision = 4)
    public Long getOperateProfitBalance() {
        return operateProfitBalance;
    }

    public void setOperateProfitBalance(Long operateProfitBalance) {
        this.operateProfitBalance = operateProfitBalance;
    }

    @Basic
    @Column(name = "OPERATE_PROFIT", nullable = true, precision = 4)
    public BigDecimal getOperateProfit() {
        return operateProfit;
    }

    public void setOperateProfit(BigDecimal operateProfit) {
        this.operateProfit = operateProfit;
    }

    @Basic
    @Column(name = "NONOPERATE_REVE", nullable = true, precision = 4)
    public Long getNonoperateReve() {
        return nonoperateReve;
    }

    public void setNonoperateReve(Long nonoperateReve) {
        this.nonoperateReve = nonoperateReve;
    }

    @Basic
    @Column(name = "NONOPERATE_EXP", nullable = true, precision = 4)
    public Long getNonoperateExp() {
        return nonoperateExp;
    }

    public void setNonoperateExp(Long nonoperateExp) {
        this.nonoperateExp = nonoperateExp;
    }

    @Basic
    @Column(name = "NONLASSET_NET_LOSS", nullable = true, precision = 4)
    public Long getNonlassetNetLoss() {
        return nonlassetNetLoss;
    }

    public void setNonlassetNetLoss(Long nonlassetNetLoss) {
        this.nonlassetNetLoss = nonlassetNetLoss;
    }

    @Basic
    @Column(name = "SUM_PROFIT_OTHER", nullable = true, precision = 4)
    public Long getSumProfitOther() {
        return sumProfitOther;
    }

    public void setSumProfitOther(Long sumProfitOther) {
        this.sumProfitOther = sumProfitOther;
    }

    @Basic
    @Column(name = "SUM_PROFIT_BALANCE", nullable = true, precision = 4)
    public Long getSumProfitBalance() {
        return sumProfitBalance;
    }

    public void setSumProfitBalance(Long sumProfitBalance) {
        this.sumProfitBalance = sumProfitBalance;
    }

    @Basic
    @Column(name = "SUM_PROFIT", nullable = true, precision = 4)
    public BigDecimal getSumProfit() {
        return sumProfit;
    }

    public void setSumProfit(BigDecimal sumProfit) {
        this.sumProfit = sumProfit;
    }

    @Basic
    @Column(name = "INCOME_TAX", nullable = true, precision = 4)
    public Long getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Long incomeTax) {
        this.incomeTax = incomeTax;
    }

    @Basic
    @Column(name = "NET_PROFIT_OTHER2", nullable = true, precision = 4)
    public Long getNetProfitOther2() {
        return netProfitOther2;
    }

    public void setNetProfitOther2(Long netProfitOther2) {
        this.netProfitOther2 = netProfitOther2;
    }

    @Basic
    @Column(name = "NET_PROFIT_BALANCE1", nullable = true, precision = 4)
    public Long getNetProfitBalance1() {
        return netProfitBalance1;
    }

    public void setNetProfitBalance1(Long netProfitBalance1) {
        this.netProfitBalance1 = netProfitBalance1;
    }

    @Basic
    @Column(name = "NET_PROFIT_BALANCE2", nullable = true, precision = 4)
    public Long getNetProfitBalance2() {
        return netProfitBalance2;
    }

    public void setNetProfitBalance2(Long netProfitBalance2) {
        this.netProfitBalance2 = netProfitBalance2;
    }

    @Basic
    @Column(name = "NET_PROFIT", nullable = true, precision = 4)
    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    @Basic
    @Column(name = "PARENT_NET_PROFIT", nullable = true, precision = 4)
    public Long getParentNetProfit() {
        return parentNetProfit;
    }

    public void setParentNetProfit(Long parentNetProfit) {
        this.parentNetProfit = parentNetProfit;
    }

    @Basic
    @Column(name = "MINORITY_INCOME", nullable = true, precision = 4)
    public Long getMinorityIncome() {
        return minorityIncome;
    }

    public void setMinorityIncome(Long minorityIncome) {
        this.minorityIncome = minorityIncome;
    }

    @Basic
    @Column(name = "UNDISTRIBUTE_PROFIT", nullable = true, precision = 4)
    public Long getUndistributeProfit() {
        return undistributeProfit;
    }

    public void setUndistributeProfit(Long undistributeProfit) {
        this.undistributeProfit = undistributeProfit;
    }

    @Basic
    @Column(name = "BASIC_EPS", nullable = true, precision = 4)
    public Long getBasicEps() {
        return basicEps;
    }

    public void setBasicEps(Long basicEps) {
        this.basicEps = basicEps;
    }

    @Basic
    @Column(name = "DILUTED_EPS", nullable = true, precision = 4)
    public Long getDilutedEps() {
        return dilutedEps;
    }

    public void setDilutedEps(Long dilutedEps) {
        this.dilutedEps = dilutedEps;
    }

    @Basic
    @Column(name = "INVEST_JOINT_INCOME", nullable = true, precision = 4)
    public Long getInvestJointIncome() {
        return investJointIncome;
    }

    public void setInvestJointIncome(Long investJointIncome) {
        this.investJointIncome = investJointIncome;
    }

    @Basic
    @Column(name = "TOTAL_OPERATE_REVE", nullable = true, precision = 4)
    public Long getTotalOperateReve() {
        return totalOperateReve;
    }

    public void setTotalOperateReve(Long totalOperateReve) {
        this.totalOperateReve = totalOperateReve;
    }

    @Basic
    @Column(name = "TOTAL_OPERATE_EXP", nullable = true, precision = 4)
    public Long getTotalOperateExp() {
        return totalOperateExp;
    }

    public void setTotalOperateExp(Long totalOperateExp) {
        this.totalOperateExp = totalOperateExp;
    }

    @Basic
    @Column(name = "OTHER_REVE", nullable = true, precision = 4)
    public Long getOtherReve() {
        return otherReve;
    }

    public void setOtherReve(Long otherReve) {
        this.otherReve = otherReve;
    }

    @Basic
    @Column(name = "OTHER_EXP", nullable = true, precision = 4)
    public Long getOtherExp() {
        return otherExp;
    }

    public void setOtherExp(Long otherExp) {
        this.otherExp = otherExp;
    }

    @Basic
    @Column(name = "UNCONFIRM_INVLOSS", nullable = true, precision = 4)
    public Long getUnconfirmInvloss() {
        return unconfirmInvloss;
    }

    public void setUnconfirmInvloss(Long unconfirmInvloss) {
        this.unconfirmInvloss = unconfirmInvloss;
    }

    @Basic
    @Column(name = "OTHER_CINCOME", nullable = true, precision = 4)
    public Long getOtherCincome() {
        return otherCincome;
    }

    public void setOtherCincome(Long otherCincome) {
        this.otherCincome = otherCincome;
    }

    @Basic
    @Column(name = "SUM_CINCOME", nullable = true, precision = 4)
    public Long getSumCincome() {
        return sumCincome;
    }

    public void setSumCincome(Long sumCincome) {
        this.sumCincome = sumCincome;
    }

    @Basic
    @Column(name = "PARENT_CINCOME", nullable = true, precision = 4)
    public Long getParentCincome() {
        return parentCincome;
    }

    public void setParentCincome(Long parentCincome) {
        this.parentCincome = parentCincome;
    }

    @Basic
    @Column(name = "MINORITY_CINCOME", nullable = true, precision = 4)
    public Long getMinorityCincome() {
        return minorityCincome;
    }

    public void setMinorityCincome(Long minorityCincome) {
        this.minorityCincome = minorityCincome;
    }

    @Basic
    @Column(name = "NET_CONTACT_RESERVE", nullable = true, precision = 4)
    public Long getNetContactReserve() {
        return netContactReserve;
    }

    public void setNetContactReserve(Long netContactReserve) {
        this.netContactReserve = netContactReserve;
    }

    @Basic
    @Column(name = "RDEXP", nullable = true, precision = 4)
    public Long getRdexp() {
        return rdexp;
    }

    public void setRdexp(Long rdexp) {
        this.rdexp = rdexp;
    }

    @Basic
    @Column(name = "OPERATE_MANAGE_EXP", nullable = true, precision = 4)
    public Long getOperateManageExp() {
        return operateManageExp;
    }

    public void setOperateManageExp(Long operateManageExp) {
        this.operateManageExp = operateManageExp;
    }

    @Basic
    @Column(name = "INSUR_REVE", nullable = true, precision = 4)
    public Long getInsurReve() {
        return insurReve;
    }

    public void setInsurReve(Long insurReve) {
        this.insurReve = insurReve;
    }

    @Basic
    @Column(name = "NONLASSET_REVE", nullable = true, precision = 4)
    public Long getNonlassetReve() {
        return nonlassetReve;
    }

    public void setNonlassetReve(Long nonlassetReve) {
        this.nonlassetReve = nonlassetReve;
    }

    @Basic
    @Column(name = "TOTAL_OPERATEREVE_OTHER", nullable = true, precision = 4)
    public Long getTotalOperatereveOther() {
        return totalOperatereveOther;
    }

    public void setTotalOperatereveOther(Long totalOperatereveOther) {
        this.totalOperatereveOther = totalOperatereveOther;
    }

    @Basic
    @Column(name = "NET_INDEMNITY_EXP", nullable = true, precision = 4)
    public Long getNetIndemnityExp() {
        return netIndemnityExp;
    }

    public void setNetIndemnityExp(Long netIndemnityExp) {
        this.netIndemnityExp = netIndemnityExp;
    }

    @Basic
    @Column(name = "TOTAL_OPERATEEXP_OTHER", nullable = true, precision = 4)
    public Long getTotalOperateexpOther() {
        return totalOperateexpOther;
    }

    public void setTotalOperateexpOther(Long totalOperateexpOther) {
        this.totalOperateexpOther = totalOperateexpOther;
    }

    @Basic
    @Column(name = "NET_PROFIT_OTHER1", nullable = true, precision = 4)
    public Long getNetProfitOther1() {
        return netProfitOther1;
    }

    public void setNetProfitOther1(Long netProfitOther1) {
        this.netProfitOther1 = netProfitOther1;
    }

    @Basic
    @Column(name = "CINCOME_BALANCE1", nullable = true, precision = 4)
    public Long getCincomeBalance1() {
        return cincomeBalance1;
    }

    public void setCincomeBalance1(Long cincomeBalance1) {
        this.cincomeBalance1 = cincomeBalance1;
    }

    @Basic
    @Column(name = "CINCOME_BALANCE2", nullable = true, precision = 4)
    public Long getCincomeBalance2() {
        return cincomeBalance2;
    }

    public void setCincomeBalance2(Long cincomeBalance2) {
        this.cincomeBalance2 = cincomeBalance2;
    }

    @Basic
    @Column(name = "OTHER_NET_INCOME", nullable = true, precision = 4)
    public Long getOtherNetIncome() {
        return otherNetIncome;
    }

    public void setOtherNetIncome(Long otherNetIncome) {
        this.otherNetIncome = otherNetIncome;
    }

    @Basic
    @Column(name = "REVE_OTHER", nullable = true, precision = 4)
    public Long getReveOther() {
        return reveOther;
    }

    public void setReveOther(Long reveOther) {
        this.reveOther = reveOther;
    }

    @Basic
    @Column(name = "REVE_BALANCE", nullable = true, precision = 4)
    public Long getReveBalance() {
        return reveBalance;
    }

    public void setReveBalance(Long reveBalance) {
        this.reveBalance = reveBalance;
    }

    @Basic
    @Column(name = "OPERATE_EXP_OTHER", nullable = true, precision = 4)
    public Long getOperateExpOther() {
        return operateExpOther;
    }

    public void setOperateExpOther(Long operateExpOther) {
        this.operateExpOther = operateExpOther;
    }

    @Basic
    @Column(name = "OPERATE_EXP_BALANCE", nullable = true, precision = 4)
    public Long getOperateExpBalance() {
        return operateExpBalance;
    }

    public void setOperateExpBalance(Long operateExpBalance) {
        this.operateExpBalance = operateExpBalance;
    }

    @Basic
    @Column(name = "BANK_INTNREVE", nullable = true, precision = 4)
    public Long getBankIntnreve() {
        return bankIntnreve;
    }

    public void setBankIntnreve(Long bankIntnreve) {
        this.bankIntnreve = bankIntnreve;
    }

    @Basic
    @Column(name = "BANK_INTREVE", nullable = true, precision = 4)
    public Long getBankIntreve() {
        return bankIntreve;
    }

    public void setBankIntreve(Long bankIntreve) {
        this.bankIntreve = bankIntreve;
    }

    @Basic
    @Column(name = "NINSUR_COMMN_REVE", nullable = true, precision = 4)
    public Long getNinsurCommnReve() {
        return ninsurCommnReve;
    }

    public void setNinsurCommnReve(Long ninsurCommnReve) {
        this.ninsurCommnReve = ninsurCommnReve;
    }

    @Basic
    @Column(name = "NINSUR_COMM_REVE", nullable = true, precision = 4)
    public Long getNinsurCommReve() {
        return ninsurCommReve;
    }

    public void setNinsurCommReve(Long ninsurCommReve) {
        this.ninsurCommReve = ninsurCommReve;
    }

    @Basic
    @Column(name = "NINSUR_COMM_EXP", nullable = true, precision = 4)
    public Long getNinsurCommExp() {
        return ninsurCommExp;
    }

    public void setNinsurCommExp(Long ninsurCommExp) {
        this.ninsurCommExp = ninsurCommExp;
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

        CompyIncomestate that = (CompyIncomestate) o;

        if (compyIncomestateSid != null ? !compyIncomestateSid.equals(that.compyIncomestateSid) : that.compyIncomestateSid != null)
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
        if (operateReve != null ? !operateReve.equals(that.operateReve) : that.operateReve != null) return false;
        if (operateExp != null ? !operateExp.equals(that.operateExp) : that.operateExp != null) return false;
        if (operateTax != null ? !operateTax.equals(that.operateTax) : that.operateTax != null) return false;
        if (saleExp != null ? !saleExp.equals(that.saleExp) : that.saleExp != null) return false;
        if (manageExp != null ? !manageExp.equals(that.manageExp) : that.manageExp != null) return false;
        if (financeExp != null ? !financeExp.equals(that.financeExp) : that.financeExp != null) return false;
        if (assetDevalueLoss != null ? !assetDevalueLoss.equals(that.assetDevalueLoss) : that.assetDevalueLoss != null)
            return false;
        if (fvalueIncome != null ? !fvalueIncome.equals(that.fvalueIncome) : that.fvalueIncome != null) return false;
        if (investIncome != null ? !investIncome.equals(that.investIncome) : that.investIncome != null) return false;
        if (intnReve != null ? !intnReve.equals(that.intnReve) : that.intnReve != null) return false;
        if (intReve != null ? !intReve.equals(that.intReve) : that.intReve != null) return false;
        if (intExp != null ? !intExp.equals(that.intExp) : that.intExp != null) return false;
        if (commnReve != null ? !commnReve.equals(that.commnReve) : that.commnReve != null) return false;
        if (commReve != null ? !commReve.equals(that.commReve) : that.commReve != null) return false;
        if (commExp != null ? !commExp.equals(that.commExp) : that.commExp != null) return false;
        if (exchangeIncome != null ? !exchangeIncome.equals(that.exchangeIncome) : that.exchangeIncome != null)
            return false;
        if (premiumEarned != null ? !premiumEarned.equals(that.premiumEarned) : that.premiumEarned != null)
            return false;
        if (premiumIncome != null ? !premiumIncome.equals(that.premiumIncome) : that.premiumIncome != null)
            return false;
        if (ripremium != null ? !ripremium.equals(that.ripremium) : that.ripremium != null) return false;
        if (undueReserve != null ? !undueReserve.equals(that.undueReserve) : that.undueReserve != null) return false;
        if (premiumExp != null ? !premiumExp.equals(that.premiumExp) : that.premiumExp != null) return false;
        if (indemnityExp != null ? !indemnityExp.equals(that.indemnityExp) : that.indemnityExp != null) return false;
        if (amortiseIndemnityExp != null ? !amortiseIndemnityExp.equals(that.amortiseIndemnityExp) : that.amortiseIndemnityExp != null)
            return false;
        if (dutyReserve != null ? !dutyReserve.equals(that.dutyReserve) : that.dutyReserve != null) return false;
        if (amortiseDutyReserve != null ? !amortiseDutyReserve.equals(that.amortiseDutyReserve) : that.amortiseDutyReserve != null)
            return false;
        if (rireve != null ? !rireve.equals(that.rireve) : that.rireve != null) return false;
        if (riexp != null ? !riexp.equals(that.riexp) : that.riexp != null) return false;
        if (surrenderPremium != null ? !surrenderPremium.equals(that.surrenderPremium) : that.surrenderPremium != null)
            return false;
        if (policyDiviExp != null ? !policyDiviExp.equals(that.policyDiviExp) : that.policyDiviExp != null)
            return false;
        if (amortiseRiexp != null ? !amortiseRiexp.equals(that.amortiseRiexp) : that.amortiseRiexp != null)
            return false;
        if (agentTradeSecurity != null ? !agentTradeSecurity.equals(that.agentTradeSecurity) : that.agentTradeSecurity != null)
            return false;
        if (securityUw != null ? !securityUw.equals(that.securityUw) : that.securityUw != null) return false;
        if (clientAssetManage != null ? !clientAssetManage.equals(that.clientAssetManage) : that.clientAssetManage != null)
            return false;
        if (operateProfitOther != null ? !operateProfitOther.equals(that.operateProfitOther) : that.operateProfitOther != null)
            return false;
        if (operateProfitBalance != null ? !operateProfitBalance.equals(that.operateProfitBalance) : that.operateProfitBalance != null)
            return false;
        if (operateProfit != null ? !operateProfit.equals(that.operateProfit) : that.operateProfit != null)
            return false;
        if (nonoperateReve != null ? !nonoperateReve.equals(that.nonoperateReve) : that.nonoperateReve != null)
            return false;
        if (nonoperateExp != null ? !nonoperateExp.equals(that.nonoperateExp) : that.nonoperateExp != null)
            return false;
        if (nonlassetNetLoss != null ? !nonlassetNetLoss.equals(that.nonlassetNetLoss) : that.nonlassetNetLoss != null)
            return false;
        if (sumProfitOther != null ? !sumProfitOther.equals(that.sumProfitOther) : that.sumProfitOther != null)
            return false;
        if (sumProfitBalance != null ? !sumProfitBalance.equals(that.sumProfitBalance) : that.sumProfitBalance != null)
            return false;
        if (sumProfit != null ? !sumProfit.equals(that.sumProfit) : that.sumProfit != null) return false;
        if (incomeTax != null ? !incomeTax.equals(that.incomeTax) : that.incomeTax != null) return false;
        if (netProfitOther2 != null ? !netProfitOther2.equals(that.netProfitOther2) : that.netProfitOther2 != null)
            return false;
        if (netProfitBalance1 != null ? !netProfitBalance1.equals(that.netProfitBalance1) : that.netProfitBalance1 != null)
            return false;
        if (netProfitBalance2 != null ? !netProfitBalance2.equals(that.netProfitBalance2) : that.netProfitBalance2 != null)
            return false;
        if (netProfit != null ? !netProfit.equals(that.netProfit) : that.netProfit != null) return false;
        if (parentNetProfit != null ? !parentNetProfit.equals(that.parentNetProfit) : that.parentNetProfit != null)
            return false;
        if (minorityIncome != null ? !minorityIncome.equals(that.minorityIncome) : that.minorityIncome != null)
            return false;
        if (undistributeProfit != null ? !undistributeProfit.equals(that.undistributeProfit) : that.undistributeProfit != null)
            return false;
        if (basicEps != null ? !basicEps.equals(that.basicEps) : that.basicEps != null) return false;
        if (dilutedEps != null ? !dilutedEps.equals(that.dilutedEps) : that.dilutedEps != null) return false;
        if (investJointIncome != null ? !investJointIncome.equals(that.investJointIncome) : that.investJointIncome != null)
            return false;
        if (totalOperateReve != null ? !totalOperateReve.equals(that.totalOperateReve) : that.totalOperateReve != null)
            return false;
        if (totalOperateExp != null ? !totalOperateExp.equals(that.totalOperateExp) : that.totalOperateExp != null)
            return false;
        if (otherReve != null ? !otherReve.equals(that.otherReve) : that.otherReve != null) return false;
        if (otherExp != null ? !otherExp.equals(that.otherExp) : that.otherExp != null) return false;
        if (unconfirmInvloss != null ? !unconfirmInvloss.equals(that.unconfirmInvloss) : that.unconfirmInvloss != null)
            return false;
        if (otherCincome != null ? !otherCincome.equals(that.otherCincome) : that.otherCincome != null) return false;
        if (sumCincome != null ? !sumCincome.equals(that.sumCincome) : that.sumCincome != null) return false;
        if (parentCincome != null ? !parentCincome.equals(that.parentCincome) : that.parentCincome != null)
            return false;
        if (minorityCincome != null ? !minorityCincome.equals(that.minorityCincome) : that.minorityCincome != null)
            return false;
        if (netContactReserve != null ? !netContactReserve.equals(that.netContactReserve) : that.netContactReserve != null)
            return false;
        if (rdexp != null ? !rdexp.equals(that.rdexp) : that.rdexp != null) return false;
        if (operateManageExp != null ? !operateManageExp.equals(that.operateManageExp) : that.operateManageExp != null)
            return false;
        if (insurReve != null ? !insurReve.equals(that.insurReve) : that.insurReve != null) return false;
        if (nonlassetReve != null ? !nonlassetReve.equals(that.nonlassetReve) : that.nonlassetReve != null)
            return false;
        if (totalOperatereveOther != null ? !totalOperatereveOther.equals(that.totalOperatereveOther) : that.totalOperatereveOther != null)
            return false;
        if (netIndemnityExp != null ? !netIndemnityExp.equals(that.netIndemnityExp) : that.netIndemnityExp != null)
            return false;
        if (totalOperateexpOther != null ? !totalOperateexpOther.equals(that.totalOperateexpOther) : that.totalOperateexpOther != null)
            return false;
        if (netProfitOther1 != null ? !netProfitOther1.equals(that.netProfitOther1) : that.netProfitOther1 != null)
            return false;
        if (cincomeBalance1 != null ? !cincomeBalance1.equals(that.cincomeBalance1) : that.cincomeBalance1 != null)
            return false;
        if (cincomeBalance2 != null ? !cincomeBalance2.equals(that.cincomeBalance2) : that.cincomeBalance2 != null)
            return false;
        if (otherNetIncome != null ? !otherNetIncome.equals(that.otherNetIncome) : that.otherNetIncome != null)
            return false;
        if (reveOther != null ? !reveOther.equals(that.reveOther) : that.reveOther != null) return false;
        if (reveBalance != null ? !reveBalance.equals(that.reveBalance) : that.reveBalance != null) return false;
        if (operateExpOther != null ? !operateExpOther.equals(that.operateExpOther) : that.operateExpOther != null)
            return false;
        if (operateExpBalance != null ? !operateExpBalance.equals(that.operateExpBalance) : that.operateExpBalance != null)
            return false;
        if (bankIntnreve != null ? !bankIntnreve.equals(that.bankIntnreve) : that.bankIntnreve != null) return false;
        if (bankIntreve != null ? !bankIntreve.equals(that.bankIntreve) : that.bankIntreve != null) return false;
        if (ninsurCommnReve != null ? !ninsurCommnReve.equals(that.ninsurCommnReve) : that.ninsurCommnReve != null)
            return false;
        if (ninsurCommReve != null ? !ninsurCommReve.equals(that.ninsurCommReve) : that.ninsurCommReve != null)
            return false;
        if (ninsurCommExp != null ? !ninsurCommExp.equals(that.ninsurCommExp) : that.ninsurCommExp != null)
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
        int result = compyIncomestateSid != null ? compyIncomestateSid.hashCode() : 0;
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
        result = 31 * result + (operateReve != null ? operateReve.hashCode() : 0);
        result = 31 * result + (operateExp != null ? operateExp.hashCode() : 0);
        result = 31 * result + (operateTax != null ? operateTax.hashCode() : 0);
        result = 31 * result + (saleExp != null ? saleExp.hashCode() : 0);
        result = 31 * result + (manageExp != null ? manageExp.hashCode() : 0);
        result = 31 * result + (financeExp != null ? financeExp.hashCode() : 0);
        result = 31 * result + (assetDevalueLoss != null ? assetDevalueLoss.hashCode() : 0);
        result = 31 * result + (fvalueIncome != null ? fvalueIncome.hashCode() : 0);
        result = 31 * result + (investIncome != null ? investIncome.hashCode() : 0);
        result = 31 * result + (intnReve != null ? intnReve.hashCode() : 0);
        result = 31 * result + (intReve != null ? intReve.hashCode() : 0);
        result = 31 * result + (intExp != null ? intExp.hashCode() : 0);
        result = 31 * result + (commnReve != null ? commnReve.hashCode() : 0);
        result = 31 * result + (commReve != null ? commReve.hashCode() : 0);
        result = 31 * result + (commExp != null ? commExp.hashCode() : 0);
        result = 31 * result + (exchangeIncome != null ? exchangeIncome.hashCode() : 0);
        result = 31 * result + (premiumEarned != null ? premiumEarned.hashCode() : 0);
        result = 31 * result + (premiumIncome != null ? premiumIncome.hashCode() : 0);
        result = 31 * result + (ripremium != null ? ripremium.hashCode() : 0);
        result = 31 * result + (undueReserve != null ? undueReserve.hashCode() : 0);
        result = 31 * result + (premiumExp != null ? premiumExp.hashCode() : 0);
        result = 31 * result + (indemnityExp != null ? indemnityExp.hashCode() : 0);
        result = 31 * result + (amortiseIndemnityExp != null ? amortiseIndemnityExp.hashCode() : 0);
        result = 31 * result + (dutyReserve != null ? dutyReserve.hashCode() : 0);
        result = 31 * result + (amortiseDutyReserve != null ? amortiseDutyReserve.hashCode() : 0);
        result = 31 * result + (rireve != null ? rireve.hashCode() : 0);
        result = 31 * result + (riexp != null ? riexp.hashCode() : 0);
        result = 31 * result + (surrenderPremium != null ? surrenderPremium.hashCode() : 0);
        result = 31 * result + (policyDiviExp != null ? policyDiviExp.hashCode() : 0);
        result = 31 * result + (amortiseRiexp != null ? amortiseRiexp.hashCode() : 0);
        result = 31 * result + (agentTradeSecurity != null ? agentTradeSecurity.hashCode() : 0);
        result = 31 * result + (securityUw != null ? securityUw.hashCode() : 0);
        result = 31 * result + (clientAssetManage != null ? clientAssetManage.hashCode() : 0);
        result = 31 * result + (operateProfitOther != null ? operateProfitOther.hashCode() : 0);
        result = 31 * result + (operateProfitBalance != null ? operateProfitBalance.hashCode() : 0);
        result = 31 * result + (operateProfit != null ? operateProfit.hashCode() : 0);
        result = 31 * result + (nonoperateReve != null ? nonoperateReve.hashCode() : 0);
        result = 31 * result + (nonoperateExp != null ? nonoperateExp.hashCode() : 0);
        result = 31 * result + (nonlassetNetLoss != null ? nonlassetNetLoss.hashCode() : 0);
        result = 31 * result + (sumProfitOther != null ? sumProfitOther.hashCode() : 0);
        result = 31 * result + (sumProfitBalance != null ? sumProfitBalance.hashCode() : 0);
        result = 31 * result + (sumProfit != null ? sumProfit.hashCode() : 0);
        result = 31 * result + (incomeTax != null ? incomeTax.hashCode() : 0);
        result = 31 * result + (netProfitOther2 != null ? netProfitOther2.hashCode() : 0);
        result = 31 * result + (netProfitBalance1 != null ? netProfitBalance1.hashCode() : 0);
        result = 31 * result + (netProfitBalance2 != null ? netProfitBalance2.hashCode() : 0);
        result = 31 * result + (netProfit != null ? netProfit.hashCode() : 0);
        result = 31 * result + (parentNetProfit != null ? parentNetProfit.hashCode() : 0);
        result = 31 * result + (minorityIncome != null ? minorityIncome.hashCode() : 0);
        result = 31 * result + (undistributeProfit != null ? undistributeProfit.hashCode() : 0);
        result = 31 * result + (basicEps != null ? basicEps.hashCode() : 0);
        result = 31 * result + (dilutedEps != null ? dilutedEps.hashCode() : 0);
        result = 31 * result + (investJointIncome != null ? investJointIncome.hashCode() : 0);
        result = 31 * result + (totalOperateReve != null ? totalOperateReve.hashCode() : 0);
        result = 31 * result + (totalOperateExp != null ? totalOperateExp.hashCode() : 0);
        result = 31 * result + (otherReve != null ? otherReve.hashCode() : 0);
        result = 31 * result + (otherExp != null ? otherExp.hashCode() : 0);
        result = 31 * result + (unconfirmInvloss != null ? unconfirmInvloss.hashCode() : 0);
        result = 31 * result + (otherCincome != null ? otherCincome.hashCode() : 0);
        result = 31 * result + (sumCincome != null ? sumCincome.hashCode() : 0);
        result = 31 * result + (parentCincome != null ? parentCincome.hashCode() : 0);
        result = 31 * result + (minorityCincome != null ? minorityCincome.hashCode() : 0);
        result = 31 * result + (netContactReserve != null ? netContactReserve.hashCode() : 0);
        result = 31 * result + (rdexp != null ? rdexp.hashCode() : 0);
        result = 31 * result + (operateManageExp != null ? operateManageExp.hashCode() : 0);
        result = 31 * result + (insurReve != null ? insurReve.hashCode() : 0);
        result = 31 * result + (nonlassetReve != null ? nonlassetReve.hashCode() : 0);
        result = 31 * result + (totalOperatereveOther != null ? totalOperatereveOther.hashCode() : 0);
        result = 31 * result + (netIndemnityExp != null ? netIndemnityExp.hashCode() : 0);
        result = 31 * result + (totalOperateexpOther != null ? totalOperateexpOther.hashCode() : 0);
        result = 31 * result + (netProfitOther1 != null ? netProfitOther1.hashCode() : 0);
        result = 31 * result + (cincomeBalance1 != null ? cincomeBalance1.hashCode() : 0);
        result = 31 * result + (cincomeBalance2 != null ? cincomeBalance2.hashCode() : 0);
        result = 31 * result + (otherNetIncome != null ? otherNetIncome.hashCode() : 0);
        result = 31 * result + (reveOther != null ? reveOther.hashCode() : 0);
        result = 31 * result + (reveBalance != null ? reveBalance.hashCode() : 0);
        result = 31 * result + (operateExpOther != null ? operateExpOther.hashCode() : 0);
        result = 31 * result + (operateExpBalance != null ? operateExpBalance.hashCode() : 0);
        result = 31 * result + (bankIntnreve != null ? bankIntnreve.hashCode() : 0);
        result = 31 * result + (bankIntreve != null ? bankIntreve.hashCode() : 0);
        result = 31 * result + (ninsurCommnReve != null ? ninsurCommnReve.hashCode() : 0);
        result = 31 * result + (ninsurCommReve != null ? ninsurCommReve.hashCode() : 0);
        result = 31 * result + (ninsurCommExp != null ? ninsurCommExp.hashCode() : 0);
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
