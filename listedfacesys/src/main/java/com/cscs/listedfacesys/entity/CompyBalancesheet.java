package com.cscs.listedfacesys.entity;



import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by hj on 2018/2/27.
 */
@Entity
@Table(name = "COMPY_BALANCESHEET", schema = "CS_FACEBOOK_1", catalog = "")
public class CompyBalancesheet {
    private Long compyBalancesheetSid;
    private Date fstNoticeDt;
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
    private Long monetaryFund;
    private Long tradefAsset;
    private Long billRec;
    private Long accountRec;
    private Long otherRec;
    private Long advancePay;
    private Long dividendRec;
    private Long interestRec;
    private Long inventory;
    private Long nonlAssetOneyear;
    private Long deferExpense;
    private Long otherLasset;
    private Long lassetOther;
    private Long lassetBalance;
    private Long sumLasset;
    private Long saleableFasset;
    private Long heldMaturityInv;
    private Long estateInvest;
    private Long lteQuityInv;
    private Long ltrec;
    private Long fixedAsset;
    private Long constructionMaterial;
    private Long constructionProgress;
    private Long liquidateFixedAsset;
    private Long productBiologyAsset;
    private Long oilgasAsset;
    private Long intangibleAsset;
    private Long developExp;
    private Long goodWill;
    private Long ltdeferAsset;
    private Long deferIncometaxAsset;
    private Long otherNonlAsset;
    private Long nonlassetOther;
    private Long nonlassetBalance;
    private Long sumNonlAsset;
    private Long cashAndDepositcbank;
    private Long fiDeposit;
    private Long preciousMetal;
    private Long lendFund;
    private Long deriveFasset;
    private Long buySellbackFasset;
    private Long loanAdvances;
    private Long agencyAssets;
    private Long premiumRec;
    private Long subrogationRec;
    private Long riRec;
    private Long undueRireserveRec;
    private Long claimRireserveRec;
    private Long lifeRireserveRec;
    private Long lthealthRireserveRec;
    private Long gdepositPay;
    private Long insuredPledgeLoan;
    private Long capitalgDepositPay;
    private Long independentAsset;
    private Long clientFund;
    private Long settlementProvision;
    private Long clientProvision;
    private Long seatFee;
    private Long otherAsset;
    private Long assetOther;
    private Long assetBalance;
    private BigDecimal sumAsset;
    private Long stBorrow;
    private Long tradeFliab;
    private Long billPay;
    private Long accountPay;
    private Long advanceReceive;
    private Long salaryPay;
    private Long taxPay;
    private Long interestPay;
    private Long dividendPay;
    private Long otherPay;
    private Long accrueExpense;
    private Long anticipateLiab;
    private Long deferIncome;
    private Long nonlLiabOneyear;
    private Long otherLliab;
    private Long lliabOther;
    private Long lliabBalance;
    private BigDecimal sumLliab;
    private Long ltBorrow;
    private Long bondPay;
    private Long ltAccountPay;
    private Long specialPay;
    private Long deferIncometaxLiab;
    private Long otherNonlLiab;
    private Long nonlLiabOther;
    private Long nonlLiabBalance;
    private Long sumNonlLiab;
    private Long borrowFromCbank;
    private Long borrowFund;
    private Long deriveFinancedebt;
    private Long sellBuybackFasset;
    private Long acceptDeposit;
    private Long agencyLiab;
    private Long otherLiab;
    private Long premiumAdvance;
    private Long commPay;
    private Long riPay;
    private Long gdepositRec;
    private Long insuredDepositInv;
    private Long undueReserve;
    private Long claimReserve;
    private Long lifeReserve;
    private Long ltHealthReserve;
    private Long independentLiab;
    private Long pledgeBorrow;
    private Long agentTradeSecurity;
    private Long agentUwSecurity;
    private Long liabOther;
    private Long liabBalance;
    private BigDecimal sumLiab;
    private Long shareCapital;
    private Long capitalReserve;
    private Long surplusReserve;
    private Long retainedEarning;
    private Long inventoryShare;
    private Long generalRiskPrepare;
    private Long diffConversionFc;
    private Long minorityEquity;
    private Long shEquityOther;
    private Long shEquityBalance;
    private Long sumParentEquity;
    private Long sumShEquity;
    private Long liabshEquityOther;
    private Long liabshEquityBalance;
    private Long sumLiabshEquity;
    private Long tdEposit;
    private Long stBondRec;
    private Long claimPay;
    private Long policyDiviPay;
    private Long unconfirmInvLoss;
    private Long ricontactReserveRec;
    private Long deposit;
    private Long contactReserve;
    private Long investRec;
    private Long speciaLreserve;
    private Long subsidyRec;
    private Long marginoutFund;
    private Long exportRebateRec;
    private Long deferIncomeOneyear;
    private Long ltSalaryPay;
    private Long fvalueFasset;
    private Long defineFvalueFasset;
    private Long internalRec;
    private Long clheldSaleAss;
    private Long fvalueFliab;
    private Long defineFvalueFliab;
    private Long internalPay;
    private Long clheldSaleLiab;
    private Long anticipateLliab;
    private Long otherEquity;
    private Long otherCincome;
    private Long planCashDivi;
    private Long parentEquityOther;
    private Long parentEquityBalance;
    private Long preferredStock;
    private Long preferStocBond;
    private Long consBioloAsset;
    private Long stockNumEnd;
    private Long netMasSet;
    private Long outwardRemittance;
    private Long cdandbillRec;
    private Long hedgeReserve;
    private Long suggestAssignDivi;
    private Long marginoutSecurity;
    private Long cagentTradeSecurity;
    private Long tradeRiskPrepare;
    private Long creditorPlaninv;
    private String remark;
    private String chkStatus;
    private Long isdel;
    private String srcCompanyCd;
    private String srcid;
    private String srcCd;
    private Timestamp updtDt;

    @Id
    @Column(name = "COMPY_BALANCESHEET_SID", nullable = false, precision = 0)
    public Long getCompyBalancesheetSid() {
        return compyBalancesheetSid;
    }

    public void setCompyBalancesheetSid(Long compyBalancesheetSid) {
        this.compyBalancesheetSid = compyBalancesheetSid;
    }

    @Basic
    @Column(name = "FST_NOTICE_DT", nullable = true)
    public Date getFstNoticeDt() {
        return fstNoticeDt;
    }

    public void setFstNoticeDt(Date fstNoticeDt) {
        this.fstNoticeDt = fstNoticeDt;
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
    @Column(name = "MONETARY_FUND", nullable = true, precision = 4)
    public Long getMonetaryFund() {
        return monetaryFund;
    }

    public void setMonetaryFund(Long monetaryFund) {
        this.monetaryFund = monetaryFund;
    }

    @Basic
    @Column(name = "TRADEF_ASSET", nullable = true, precision = 4)
    public Long getTradefAsset() {
        return tradefAsset;
    }

    public void setTradefAsset(Long tradefAsset) {
        this.tradefAsset = tradefAsset;
    }

    @Basic
    @Column(name = "BILL_REC", nullable = true, precision = 4)
    public Long getBillRec() {
        return billRec;
    }

    public void setBillRec(Long billRec) {
        this.billRec = billRec;
    }

    @Basic
    @Column(name = "ACCOUNT_REC", nullable = true, precision = 4)
    public Long getAccountRec() {
        return accountRec;
    }

    public void setAccountRec(Long accountRec) {
        this.accountRec = accountRec;
    }

    @Basic
    @Column(name = "OTHER_REC", nullable = true, precision = 4)
    public Long getOtherRec() {
        return otherRec;
    }

    public void setOtherRec(Long otherRec) {
        this.otherRec = otherRec;
    }

    @Basic
    @Column(name = "ADVANCE_PAY", nullable = true, precision = 4)
    public Long getAdvancePay() {
        return advancePay;
    }

    public void setAdvancePay(Long advancePay) {
        this.advancePay = advancePay;
    }

    @Basic
    @Column(name = "DIVIDEND_REC", nullable = true, precision = 4)
    public Long getDividendRec() {
        return dividendRec;
    }

    public void setDividendRec(Long dividendRec) {
        this.dividendRec = dividendRec;
    }

    @Basic
    @Column(name = "INTEREST_REC", nullable = true, precision = 4)
    public Long getInterestRec() {
        return interestRec;
    }

    public void setInterestRec(Long interestRec) {
        this.interestRec = interestRec;
    }

    @Basic
    @Column(name = "INVENTORY", nullable = true, precision = 4)
    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    @Basic
    @Column(name = "NONL_ASSET_ONEYEAR", nullable = true, precision = 4)
    public Long getNonlAssetOneyear() {
        return nonlAssetOneyear;
    }

    public void setNonlAssetOneyear(Long nonlAssetOneyear) {
        this.nonlAssetOneyear = nonlAssetOneyear;
    }

    @Basic
    @Column(name = "DEFER_EXPENSE", nullable = true, precision = 4)
    public Long getDeferExpense() {
        return deferExpense;
    }

    public void setDeferExpense(Long deferExpense) {
        this.deferExpense = deferExpense;
    }

    @Basic
    @Column(name = "OTHER_LASSET", nullable = true, precision = 4)
    public Long getOtherLasset() {
        return otherLasset;
    }

    public void setOtherLasset(Long otherLasset) {
        this.otherLasset = otherLasset;
    }

    @Basic
    @Column(name = "LASSET_OTHER", nullable = true, precision = 4)
    public Long getLassetOther() {
        return lassetOther;
    }

    public void setLassetOther(Long lassetOther) {
        this.lassetOther = lassetOther;
    }

    @Basic
    @Column(name = "LASSET_BALANCE", nullable = true, precision = 4)
    public Long getLassetBalance() {
        return lassetBalance;
    }

    public void setLassetBalance(Long lassetBalance) {
        this.lassetBalance = lassetBalance;
    }

    @Basic
    @Column(name = "SUM_LASSET", nullable = true, precision = 4)
    public Long getSumLasset() {
        return sumLasset;
    }

    public void setSumLasset(Long sumLasset) {
        this.sumLasset = sumLasset;
    }

    @Basic
    @Column(name = "SALEABLE_FASSET", nullable = true, precision = 4)
    public Long getSaleableFasset() {
        return saleableFasset;
    }

    public void setSaleableFasset(Long saleableFasset) {
        this.saleableFasset = saleableFasset;
    }

    @Basic
    @Column(name = "HELD_MATURITY_INV", nullable = true, precision = 4)
    public Long getHeldMaturityInv() {
        return heldMaturityInv;
    }

    public void setHeldMaturityInv(Long heldMaturityInv) {
        this.heldMaturityInv = heldMaturityInv;
    }

    @Basic
    @Column(name = "ESTATE_INVEST", nullable = true, precision = 4)
    public Long getEstateInvest() {
        return estateInvest;
    }

    public void setEstateInvest(Long estateInvest) {
        this.estateInvest = estateInvest;
    }

    @Basic
    @Column(name = "LTE_QUITY_INV", nullable = true, precision = 4)
    public Long getLteQuityInv() {
        return lteQuityInv;
    }

    public void setLteQuityInv(Long lteQuityInv) {
        this.lteQuityInv = lteQuityInv;
    }

    @Basic
    @Column(name = "LTREC", nullable = true, precision = 4)
    public Long getLtrec() {
        return ltrec;
    }

    public void setLtrec(Long ltrec) {
        this.ltrec = ltrec;
    }

    @Basic
    @Column(name = "FIXED_ASSET", nullable = true, precision = 4)
    public Long getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(Long fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    @Basic
    @Column(name = "CONSTRUCTION_MATERIAL", nullable = true, precision = 4)
    public Long getConstructionMaterial() {
        return constructionMaterial;
    }

    public void setConstructionMaterial(Long constructionMaterial) {
        this.constructionMaterial = constructionMaterial;
    }

    @Basic
    @Column(name = "CONSTRUCTION_PROGRESS", nullable = true, precision = 4)
    public Long getConstructionProgress() {
        return constructionProgress;
    }

    public void setConstructionProgress(Long constructionProgress) {
        this.constructionProgress = constructionProgress;
    }

    @Basic
    @Column(name = "LIQUIDATE_FIXED_ASSET", nullable = true, precision = 4)
    public Long getLiquidateFixedAsset() {
        return liquidateFixedAsset;
    }

    public void setLiquidateFixedAsset(Long liquidateFixedAsset) {
        this.liquidateFixedAsset = liquidateFixedAsset;
    }

    @Basic
    @Column(name = "PRODUCT_BIOLOGY_ASSET", nullable = true, precision = 4)
    public Long getProductBiologyAsset() {
        return productBiologyAsset;
    }

    public void setProductBiologyAsset(Long productBiologyAsset) {
        this.productBiologyAsset = productBiologyAsset;
    }

    @Basic
    @Column(name = "OILGAS_ASSET", nullable = true, precision = 4)
    public Long getOilgasAsset() {
        return oilgasAsset;
    }

    public void setOilgasAsset(Long oilgasAsset) {
        this.oilgasAsset = oilgasAsset;
    }

    @Basic
    @Column(name = "INTANGIBLE_ASSET", nullable = true, precision = 4)
    public Long getIntangibleAsset() {
        return intangibleAsset;
    }

    public void setIntangibleAsset(Long intangibleAsset) {
        this.intangibleAsset = intangibleAsset;
    }

    @Basic
    @Column(name = "DEVELOP_EXP", nullable = true, precision = 4)
    public Long getDevelopExp() {
        return developExp;
    }

    public void setDevelopExp(Long developExp) {
        this.developExp = developExp;
    }

    @Basic
    @Column(name = "GOOD_WILL", nullable = true, precision = 4)
    public Long getGoodWill() {
        return goodWill;
    }

    public void setGoodWill(Long goodWill) {
        this.goodWill = goodWill;
    }

    @Basic
    @Column(name = "LTDEFER_ASSET", nullable = true, precision = 4)
    public Long getLtdeferAsset() {
        return ltdeferAsset;
    }

    public void setLtdeferAsset(Long ltdeferAsset) {
        this.ltdeferAsset = ltdeferAsset;
    }

    @Basic
    @Column(name = "DEFER_INCOMETAX_ASSET", nullable = true, precision = 4)
    public Long getDeferIncometaxAsset() {
        return deferIncometaxAsset;
    }

    public void setDeferIncometaxAsset(Long deferIncometaxAsset) {
        this.deferIncometaxAsset = deferIncometaxAsset;
    }

    @Basic
    @Column(name = "OTHER_NONL_ASSET", nullable = true, precision = 4)
    public Long getOtherNonlAsset() {
        return otherNonlAsset;
    }

    public void setOtherNonlAsset(Long otherNonlAsset) {
        this.otherNonlAsset = otherNonlAsset;
    }

    @Basic
    @Column(name = "NONLASSET_OTHER", nullable = true, precision = 4)
    public Long getNonlassetOther() {
        return nonlassetOther;
    }

    public void setNonlassetOther(Long nonlassetOther) {
        this.nonlassetOther = nonlassetOther;
    }

    @Basic
    @Column(name = "NONLASSET_BALANCE", nullable = true, precision = 4)
    public Long getNonlassetBalance() {
        return nonlassetBalance;
    }

    public void setNonlassetBalance(Long nonlassetBalance) {
        this.nonlassetBalance = nonlassetBalance;
    }

    @Basic
    @Column(name = "SUM_NONL_ASSET", nullable = true, precision = 4)
    public Long getSumNonlAsset() {
        return sumNonlAsset;
    }

    public void setSumNonlAsset(Long sumNonlAsset) {
        this.sumNonlAsset = sumNonlAsset;
    }

    @Basic
    @Column(name = "CASH_AND_DEPOSITCBANK", nullable = true, precision = 4)
    public Long getCashAndDepositcbank() {
        return cashAndDepositcbank;
    }

    public void setCashAndDepositcbank(Long cashAndDepositcbank) {
        this.cashAndDepositcbank = cashAndDepositcbank;
    }

    @Basic
    @Column(name = "FI_DEPOSIT", nullable = true, precision = 4)
    public Long getFiDeposit() {
        return fiDeposit;
    }

    public void setFiDeposit(Long fiDeposit) {
        this.fiDeposit = fiDeposit;
    }

    @Basic
    @Column(name = "PRECIOUS_METAL", nullable = true, precision = 4)
    public Long getPreciousMetal() {
        return preciousMetal;
    }

    public void setPreciousMetal(Long preciousMetal) {
        this.preciousMetal = preciousMetal;
    }

    @Basic
    @Column(name = "LEND_FUND", nullable = true, precision = 4)
    public Long getLendFund() {
        return lendFund;
    }

    public void setLendFund(Long lendFund) {
        this.lendFund = lendFund;
    }

    @Basic
    @Column(name = "DERIVE_FASSET", nullable = true, precision = 4)
    public Long getDeriveFasset() {
        return deriveFasset;
    }

    public void setDeriveFasset(Long deriveFasset) {
        this.deriveFasset = deriveFasset;
    }

    @Basic
    @Column(name = "BUY_SELLBACK_FASSET", nullable = true, precision = 4)
    public Long getBuySellbackFasset() {
        return buySellbackFasset;
    }

    public void setBuySellbackFasset(Long buySellbackFasset) {
        this.buySellbackFasset = buySellbackFasset;
    }

    @Basic
    @Column(name = "LOAN_ADVANCES", nullable = true, precision = 4)
    public Long getLoanAdvances() {
        return loanAdvances;
    }

    public void setLoanAdvances(Long loanAdvances) {
        this.loanAdvances = loanAdvances;
    }

    @Basic
    @Column(name = "AGENCY_ASSETS", nullable = true, precision = 4)
    public Long getAgencyAssets() {
        return agencyAssets;
    }

    public void setAgencyAssets(Long agencyAssets) {
        this.agencyAssets = agencyAssets;
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
    @Column(name = "SUBROGATION_REC", nullable = true, precision = 4)
    public Long getSubrogationRec() {
        return subrogationRec;
    }

    public void setSubrogationRec(Long subrogationRec) {
        this.subrogationRec = subrogationRec;
    }

    @Basic
    @Column(name = "RI_REC", nullable = true, precision = 4)
    public Long getRiRec() {
        return riRec;
    }

    public void setRiRec(Long riRec) {
        this.riRec = riRec;
    }

    @Basic
    @Column(name = "UNDUE_RIRESERVE_REC", nullable = true, precision = 4)
    public Long getUndueRireserveRec() {
        return undueRireserveRec;
    }

    public void setUndueRireserveRec(Long undueRireserveRec) {
        this.undueRireserveRec = undueRireserveRec;
    }

    @Basic
    @Column(name = "CLAIM_RIRESERVE_REC", nullable = true, precision = 4)
    public Long getClaimRireserveRec() {
        return claimRireserveRec;
    }

    public void setClaimRireserveRec(Long claimRireserveRec) {
        this.claimRireserveRec = claimRireserveRec;
    }

    @Basic
    @Column(name = "LIFE_RIRESERVE_REC", nullable = true, precision = 4)
    public Long getLifeRireserveRec() {
        return lifeRireserveRec;
    }

    public void setLifeRireserveRec(Long lifeRireserveRec) {
        this.lifeRireserveRec = lifeRireserveRec;
    }

    @Basic
    @Column(name = "LTHEALTH_RIRESERVE_REC", nullable = true, precision = 4)
    public Long getLthealthRireserveRec() {
        return lthealthRireserveRec;
    }

    public void setLthealthRireserveRec(Long lthealthRireserveRec) {
        this.lthealthRireserveRec = lthealthRireserveRec;
    }

    @Basic
    @Column(name = "GDEPOSIT_PAY", nullable = true, precision = 4)
    public Long getGdepositPay() {
        return gdepositPay;
    }

    public void setGdepositPay(Long gdepositPay) {
        this.gdepositPay = gdepositPay;
    }

    @Basic
    @Column(name = "INSURED_PLEDGE_LOAN", nullable = true, precision = 4)
    public Long getInsuredPledgeLoan() {
        return insuredPledgeLoan;
    }

    public void setInsuredPledgeLoan(Long insuredPledgeLoan) {
        this.insuredPledgeLoan = insuredPledgeLoan;
    }

    @Basic
    @Column(name = "CAPITALG_DEPOSIT_PAY", nullable = true, precision = 4)
    public Long getCapitalgDepositPay() {
        return capitalgDepositPay;
    }

    public void setCapitalgDepositPay(Long capitalgDepositPay) {
        this.capitalgDepositPay = capitalgDepositPay;
    }

    @Basic
    @Column(name = "INDEPENDENT_ASSET", nullable = true, precision = 4)
    public Long getIndependentAsset() {
        return independentAsset;
    }

    public void setIndependentAsset(Long independentAsset) {
        this.independentAsset = independentAsset;
    }

    @Basic
    @Column(name = "CLIENT_FUND", nullable = true, precision = 4)
    public Long getClientFund() {
        return clientFund;
    }

    public void setClientFund(Long clientFund) {
        this.clientFund = clientFund;
    }

    @Basic
    @Column(name = "SETTLEMENT_PROVISION", nullable = true, precision = 4)
    public Long getSettlementProvision() {
        return settlementProvision;
    }

    public void setSettlementProvision(Long settlementProvision) {
        this.settlementProvision = settlementProvision;
    }

    @Basic
    @Column(name = "CLIENT_PROVISION", nullable = true, precision = 4)
    public Long getClientProvision() {
        return clientProvision;
    }

    public void setClientProvision(Long clientProvision) {
        this.clientProvision = clientProvision;
    }

    @Basic
    @Column(name = "SEAT_FEE", nullable = true, precision = 4)
    public Long getSeatFee() {
        return seatFee;
    }

    public void setSeatFee(Long seatFee) {
        this.seatFee = seatFee;
    }

    @Basic
    @Column(name = "OTHER_ASSET", nullable = true, precision = 4)
    public Long getOtherAsset() {
        return otherAsset;
    }

    public void setOtherAsset(Long otherAsset) {
        this.otherAsset = otherAsset;
    }

    @Basic
    @Column(name = "ASSET_OTHER", nullable = true, precision = 4)
    public Long getAssetOther() {
        return assetOther;
    }

    public void setAssetOther(Long assetOther) {
        this.assetOther = assetOther;
    }

    @Basic
    @Column(name = "ASSET_BALANCE", nullable = true, precision = 4)
    public Long getAssetBalance() {
        return assetBalance;
    }

    public void setAssetBalance(Long assetBalance) {
        this.assetBalance = assetBalance;
    }

    @Basic
    @Column(name = "SUM_ASSET", nullable = true, precision = 4)
    public BigDecimal getSumAsset() {
        return sumAsset;
    }

    public void setSumAsset(BigDecimal sumAsset) {
        this.sumAsset = sumAsset;
    }

    @Basic
    @Column(name = "ST_BORROW", nullable = true, precision = 4)
    public Long getStBorrow() {
        return stBorrow;
    }

    public void setStBorrow(Long stBorrow) {
        this.stBorrow = stBorrow;
    }

    @Basic
    @Column(name = "TRADE_FLIAB", nullable = true, precision = 4)
    public Long getTradeFliab() {
        return tradeFliab;
    }

    public void setTradeFliab(Long tradeFliab) {
        this.tradeFliab = tradeFliab;
    }

    @Basic
    @Column(name = "BILL_PAY", nullable = true, precision = 4)
    public Long getBillPay() {
        return billPay;
    }

    public void setBillPay(Long billPay) {
        this.billPay = billPay;
    }

    @Basic
    @Column(name = "ACCOUNT_PAY", nullable = true, precision = 4)
    public Long getAccountPay() {
        return accountPay;
    }

    public void setAccountPay(Long accountPay) {
        this.accountPay = accountPay;
    }

    @Basic
    @Column(name = "ADVANCE_RECEIVE", nullable = true, precision = 4)
    public Long getAdvanceReceive() {
        return advanceReceive;
    }

    public void setAdvanceReceive(Long advanceReceive) {
        this.advanceReceive = advanceReceive;
    }

    @Basic
    @Column(name = "SALARY_PAY", nullable = true, precision = 4)
    public Long getSalaryPay() {
        return salaryPay;
    }

    public void setSalaryPay(Long salaryPay) {
        this.salaryPay = salaryPay;
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
    @Column(name = "INTEREST_PAY", nullable = true, precision = 4)
    public Long getInterestPay() {
        return interestPay;
    }

    public void setInterestPay(Long interestPay) {
        this.interestPay = interestPay;
    }

    @Basic
    @Column(name = "DIVIDEND_PAY", nullable = true, precision = 4)
    public Long getDividendPay() {
        return dividendPay;
    }

    public void setDividendPay(Long dividendPay) {
        this.dividendPay = dividendPay;
    }

    @Basic
    @Column(name = "OTHER_PAY", nullable = true, precision = 4)
    public Long getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(Long otherPay) {
        this.otherPay = otherPay;
    }

    @Basic
    @Column(name = "ACCRUE_EXPENSE", nullable = true, precision = 4)
    public Long getAccrueExpense() {
        return accrueExpense;
    }

    public void setAccrueExpense(Long accrueExpense) {
        this.accrueExpense = accrueExpense;
    }

    @Basic
    @Column(name = "ANTICIPATE_LIAB", nullable = true, precision = 4)
    public Long getAnticipateLiab() {
        return anticipateLiab;
    }

    public void setAnticipateLiab(Long anticipateLiab) {
        this.anticipateLiab = anticipateLiab;
    }

    @Basic
    @Column(name = "DEFER_INCOME", nullable = true, precision = 4)
    public Long getDeferIncome() {
        return deferIncome;
    }

    public void setDeferIncome(Long deferIncome) {
        this.deferIncome = deferIncome;
    }

    @Basic
    @Column(name = "NONL_LIAB_ONEYEAR", nullable = true, precision = 4)
    public Long getNonlLiabOneyear() {
        return nonlLiabOneyear;
    }

    public void setNonlLiabOneyear(Long nonlLiabOneyear) {
        this.nonlLiabOneyear = nonlLiabOneyear;
    }

    @Basic
    @Column(name = "OTHER_LLIAB", nullable = true, precision = 4)
    public Long getOtherLliab() {
        return otherLliab;
    }

    public void setOtherLliab(Long otherLliab) {
        this.otherLliab = otherLliab;
    }

    @Basic
    @Column(name = "LLIAB_OTHER", nullable = true, precision = 4)
    public Long getLliabOther() {
        return lliabOther;
    }

    public void setLliabOther(Long lliabOther) {
        this.lliabOther = lliabOther;
    }

    @Basic
    @Column(name = "LLIAB_BALANCE", nullable = true, precision = 4)
    public Long getLliabBalance() {
        return lliabBalance;
    }

    public void setLliabBalance(Long lliabBalance) {
        this.lliabBalance = lliabBalance;
    }

    @Basic
    @Column(name = "SUM_LLIAB", nullable = true, precision = 4)
    public BigDecimal getSumLliab() {
        return sumLliab;
    }

    public void setSumLliab(BigDecimal sumLliab) {
        this.sumLliab = sumLliab;
    }

    @Basic
    @Column(name = "LT_BORROW", nullable = true, precision = 4)
    public Long getLtBorrow() {
        return ltBorrow;
    }

    public void setLtBorrow(Long ltBorrow) {
        this.ltBorrow = ltBorrow;
    }

    @Basic
    @Column(name = "BOND_PAY", nullable = true, precision = 4)
    public Long getBondPay() {
        return bondPay;
    }

    public void setBondPay(Long bondPay) {
        this.bondPay = bondPay;
    }

    @Basic
    @Column(name = "LT_ACCOUNT_PAY", nullable = true, precision = 4)
    public Long getLtAccountPay() {
        return ltAccountPay;
    }

    public void setLtAccountPay(Long ltAccountPay) {
        this.ltAccountPay = ltAccountPay;
    }

    @Basic
    @Column(name = "SPECIAL_PAY", nullable = true, precision = 4)
    public Long getSpecialPay() {
        return specialPay;
    }

    public void setSpecialPay(Long specialPay) {
        this.specialPay = specialPay;
    }

    @Basic
    @Column(name = "DEFER_INCOMETAX_LIAB", nullable = true, precision = 4)
    public Long getDeferIncometaxLiab() {
        return deferIncometaxLiab;
    }

    public void setDeferIncometaxLiab(Long deferIncometaxLiab) {
        this.deferIncometaxLiab = deferIncometaxLiab;
    }

    @Basic
    @Column(name = "OTHER_NONL_LIAB", nullable = true, precision = 4)
    public Long getOtherNonlLiab() {
        return otherNonlLiab;
    }

    public void setOtherNonlLiab(Long otherNonlLiab) {
        this.otherNonlLiab = otherNonlLiab;
    }

    @Basic
    @Column(name = "NONL_LIAB_OTHER", nullable = true, precision = 4)
    public Long getNonlLiabOther() {
        return nonlLiabOther;
    }

    public void setNonlLiabOther(Long nonlLiabOther) {
        this.nonlLiabOther = nonlLiabOther;
    }

    @Basic
    @Column(name = "NONL_LIAB_BALANCE", nullable = true, precision = 4)
    public Long getNonlLiabBalance() {
        return nonlLiabBalance;
    }

    public void setNonlLiabBalance(Long nonlLiabBalance) {
        this.nonlLiabBalance = nonlLiabBalance;
    }

    @Basic
    @Column(name = "SUM_NONL_LIAB", nullable = true, precision = 4)
    public Long getSumNonlLiab() {
        return sumNonlLiab;
    }

    public void setSumNonlLiab(Long sumNonlLiab) {
        this.sumNonlLiab = sumNonlLiab;
    }

    @Basic
    @Column(name = "BORROW_FROM_CBANK", nullable = true, precision = 4)
    public Long getBorrowFromCbank() {
        return borrowFromCbank;
    }

    public void setBorrowFromCbank(Long borrowFromCbank) {
        this.borrowFromCbank = borrowFromCbank;
    }

    @Basic
    @Column(name = "BORROW_FUND", nullable = true, precision = 4)
    public Long getBorrowFund() {
        return borrowFund;
    }

    public void setBorrowFund(Long borrowFund) {
        this.borrowFund = borrowFund;
    }

    @Basic
    @Column(name = "DERIVE_FINANCEDEBT", nullable = true, precision = 4)
    public Long getDeriveFinancedebt() {
        return deriveFinancedebt;
    }

    public void setDeriveFinancedebt(Long deriveFinancedebt) {
        this.deriveFinancedebt = deriveFinancedebt;
    }

    @Basic
    @Column(name = "SELL_BUYBACK_FASSET", nullable = true, precision = 4)
    public Long getSellBuybackFasset() {
        return sellBuybackFasset;
    }

    public void setSellBuybackFasset(Long sellBuybackFasset) {
        this.sellBuybackFasset = sellBuybackFasset;
    }

    @Basic
    @Column(name = "ACCEPT_DEPOSIT", nullable = true, precision = 4)
    public Long getAcceptDeposit() {
        return acceptDeposit;
    }

    public void setAcceptDeposit(Long acceptDeposit) {
        this.acceptDeposit = acceptDeposit;
    }

    @Basic
    @Column(name = "AGENCY_LIAB", nullable = true, precision = 4)
    public Long getAgencyLiab() {
        return agencyLiab;
    }

    public void setAgencyLiab(Long agencyLiab) {
        this.agencyLiab = agencyLiab;
    }

    @Basic
    @Column(name = "OTHER_LIAB", nullable = true, precision = 4)
    public Long getOtherLiab() {
        return otherLiab;
    }

    public void setOtherLiab(Long otherLiab) {
        this.otherLiab = otherLiab;
    }

    @Basic
    @Column(name = "PREMIUM_ADVANCE", nullable = true, precision = 4)
    public Long getPremiumAdvance() {
        return premiumAdvance;
    }

    public void setPremiumAdvance(Long premiumAdvance) {
        this.premiumAdvance = premiumAdvance;
    }

    @Basic
    @Column(name = "COMM_PAY", nullable = true, precision = 4)
    public Long getCommPay() {
        return commPay;
    }

    public void setCommPay(Long commPay) {
        this.commPay = commPay;
    }

    @Basic
    @Column(name = "RI_PAY", nullable = true, precision = 4)
    public Long getRiPay() {
        return riPay;
    }

    public void setRiPay(Long riPay) {
        this.riPay = riPay;
    }

    @Basic
    @Column(name = "GDEPOSIT_REC", nullable = true, precision = 4)
    public Long getGdepositRec() {
        return gdepositRec;
    }

    public void setGdepositRec(Long gdepositRec) {
        this.gdepositRec = gdepositRec;
    }

    @Basic
    @Column(name = "INSURED_DEPOSIT_INV", nullable = true, precision = 4)
    public Long getInsuredDepositInv() {
        return insuredDepositInv;
    }

    public void setInsuredDepositInv(Long insuredDepositInv) {
        this.insuredDepositInv = insuredDepositInv;
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
    @Column(name = "CLAIM_RESERVE", nullable = true, precision = 4)
    public Long getClaimReserve() {
        return claimReserve;
    }

    public void setClaimReserve(Long claimReserve) {
        this.claimReserve = claimReserve;
    }

    @Basic
    @Column(name = "LIFE_RESERVE", nullable = true, precision = 4)
    public Long getLifeReserve() {
        return lifeReserve;
    }

    public void setLifeReserve(Long lifeReserve) {
        this.lifeReserve = lifeReserve;
    }

    @Basic
    @Column(name = "LT_HEALTH_RESERVE", nullable = true, precision = 4)
    public Long getLtHealthReserve() {
        return ltHealthReserve;
    }

    public void setLtHealthReserve(Long ltHealthReserve) {
        this.ltHealthReserve = ltHealthReserve;
    }

    @Basic
    @Column(name = "INDEPENDENT_LIAB", nullable = true, precision = 4)
    public Long getIndependentLiab() {
        return independentLiab;
    }

    public void setIndependentLiab(Long independentLiab) {
        this.independentLiab = independentLiab;
    }

    @Basic
    @Column(name = "PLEDGE_BORROW", nullable = true, precision = 4)
    public Long getPledgeBorrow() {
        return pledgeBorrow;
    }

    public void setPledgeBorrow(Long pledgeBorrow) {
        this.pledgeBorrow = pledgeBorrow;
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
    @Column(name = "AGENT_UW_SECURITY", nullable = true, precision = 4)
    public Long getAgentUwSecurity() {
        return agentUwSecurity;
    }

    public void setAgentUwSecurity(Long agentUwSecurity) {
        this.agentUwSecurity = agentUwSecurity;
    }

    @Basic
    @Column(name = "LIAB_OTHER", nullable = true, precision = 4)
    public Long getLiabOther() {
        return liabOther;
    }

    public void setLiabOther(Long liabOther) {
        this.liabOther = liabOther;
    }

    @Basic
    @Column(name = "LIAB_BALANCE", nullable = true, precision = 4)
    public Long getLiabBalance() {
        return liabBalance;
    }

    public void setLiabBalance(Long liabBalance) {
        this.liabBalance = liabBalance;
    }

    @Basic
    @Column(name = "SUM_LIAB", nullable = true, precision = 4)
    public BigDecimal getSumLiab() {
        return sumLiab;
    }

    public void setSumLiab(BigDecimal sumLiab) {
        this.sumLiab = sumLiab;
    }

    @Basic
    @Column(name = "SHARE_CAPITAL", nullable = true, precision = 4)
    public Long getShareCapital() {
        return shareCapital;
    }

    public void setShareCapital(Long shareCapital) {
        this.shareCapital = shareCapital;
    }

    @Basic
    @Column(name = "CAPITAL_RESERVE", nullable = true, precision = 4)
    public Long getCapitalReserve() {
        return capitalReserve;
    }

    public void setCapitalReserve(Long capitalReserve) {
        this.capitalReserve = capitalReserve;
    }

    @Basic
    @Column(name = "SURPLUS_RESERVE", nullable = true, precision = 4)
    public Long getSurplusReserve() {
        return surplusReserve;
    }

    public void setSurplusReserve(Long surplusReserve) {
        this.surplusReserve = surplusReserve;
    }

    @Basic
    @Column(name = "RETAINED_EARNING", nullable = true, precision = 4)
    public Long getRetainedEarning() {
        return retainedEarning;
    }

    public void setRetainedEarning(Long retainedEarning) {
        this.retainedEarning = retainedEarning;
    }

    @Basic
    @Column(name = "INVENTORY_SHARE", nullable = true, precision = 4)
    public Long getInventoryShare() {
        return inventoryShare;
    }

    public void setInventoryShare(Long inventoryShare) {
        this.inventoryShare = inventoryShare;
    }

    @Basic
    @Column(name = "GENERAL_RISK_PREPARE", nullable = true, precision = 4)
    public Long getGeneralRiskPrepare() {
        return generalRiskPrepare;
    }

    public void setGeneralRiskPrepare(Long generalRiskPrepare) {
        this.generalRiskPrepare = generalRiskPrepare;
    }

    @Basic
    @Column(name = "DIFF_CONVERSION_FC", nullable = true, precision = 4)
    public Long getDiffConversionFc() {
        return diffConversionFc;
    }

    public void setDiffConversionFc(Long diffConversionFc) {
        this.diffConversionFc = diffConversionFc;
    }

    @Basic
    @Column(name = "MINORITY_EQUITY", nullable = true, precision = 4)
    public Long getMinorityEquity() {
        return minorityEquity;
    }

    public void setMinorityEquity(Long minorityEquity) {
        this.minorityEquity = minorityEquity;
    }

    @Basic
    @Column(name = "SH_EQUITY_OTHER", nullable = true, precision = 4)
    public Long getShEquityOther() {
        return shEquityOther;
    }

    public void setShEquityOther(Long shEquityOther) {
        this.shEquityOther = shEquityOther;
    }

    @Basic
    @Column(name = "SH_EQUITY_BALANCE", nullable = true, precision = 4)
    public Long getShEquityBalance() {
        return shEquityBalance;
    }

    public void setShEquityBalance(Long shEquityBalance) {
        this.shEquityBalance = shEquityBalance;
    }

    @Basic
    @Column(name = "SUM_PARENT_EQUITY", nullable = true, precision = 4)
    public Long getSumParentEquity() {
        return sumParentEquity;
    }

    public void setSumParentEquity(Long sumParentEquity) {
        this.sumParentEquity = sumParentEquity;
    }

    @Basic
    @Column(name = "SUM_SH_EQUITY", nullable = true, precision = 4)
    public Long getSumShEquity() {
        return sumShEquity;
    }

    public void setSumShEquity(Long sumShEquity) {
        this.sumShEquity = sumShEquity;
    }

    @Basic
    @Column(name = "LIABSH_EQUITY_OTHER", nullable = true, precision = 4)
    public Long getLiabshEquityOther() {
        return liabshEquityOther;
    }

    public void setLiabshEquityOther(Long liabshEquityOther) {
        this.liabshEquityOther = liabshEquityOther;
    }

    @Basic
    @Column(name = "LIABSH_EQUITY_BALANCE", nullable = true, precision = 4)
    public Long getLiabshEquityBalance() {
        return liabshEquityBalance;
    }

    public void setLiabshEquityBalance(Long liabshEquityBalance) {
        this.liabshEquityBalance = liabshEquityBalance;
    }

    @Basic
    @Column(name = "SUM_LIABSH_EQUITY", nullable = true, precision = 4)
    public Long getSumLiabshEquity() {
        return sumLiabshEquity;
    }

    public void setSumLiabshEquity(Long sumLiabshEquity) {
        this.sumLiabshEquity = sumLiabshEquity;
    }

    @Basic
    @Column(name = "TD_EPOSIT", nullable = true, precision = 4)
    public Long getTdEposit() {
        return tdEposit;
    }

    public void setTdEposit(Long tdEposit) {
        this.tdEposit = tdEposit;
    }

    @Basic
    @Column(name = "ST_BOND_REC", nullable = true, precision = 4)
    public Long getStBondRec() {
        return stBondRec;
    }

    public void setStBondRec(Long stBondRec) {
        this.stBondRec = stBondRec;
    }

    @Basic
    @Column(name = "CLAIM_PAY", nullable = true, precision = 4)
    public Long getClaimPay() {
        return claimPay;
    }

    public void setClaimPay(Long claimPay) {
        this.claimPay = claimPay;
    }

    @Basic
    @Column(name = "POLICY_DIVI_PAY", nullable = true, precision = 4)
    public Long getPolicyDiviPay() {
        return policyDiviPay;
    }

    public void setPolicyDiviPay(Long policyDiviPay) {
        this.policyDiviPay = policyDiviPay;
    }

    @Basic
    @Column(name = "UNCONFIRM_INV_LOSS", nullable = true, precision = 4)
    public Long getUnconfirmInvLoss() {
        return unconfirmInvLoss;
    }

    public void setUnconfirmInvLoss(Long unconfirmInvLoss) {
        this.unconfirmInvLoss = unconfirmInvLoss;
    }

    @Basic
    @Column(name = "RICONTACT_RESERVE_REC", nullable = true, precision = 4)
    public Long getRicontactReserveRec() {
        return ricontactReserveRec;
    }

    public void setRicontactReserveRec(Long ricontactReserveRec) {
        this.ricontactReserveRec = ricontactReserveRec;
    }

    @Basic
    @Column(name = "DEPOSIT", nullable = true, precision = 4)
    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "CONTACT_RESERVE", nullable = true, precision = 4)
    public Long getContactReserve() {
        return contactReserve;
    }

    public void setContactReserve(Long contactReserve) {
        this.contactReserve = contactReserve;
    }

    @Basic
    @Column(name = "INVEST_REC", nullable = true, precision = 4)
    public Long getInvestRec() {
        return investRec;
    }

    public void setInvestRec(Long investRec) {
        this.investRec = investRec;
    }

    @Basic
    @Column(name = "SPECIA_LRESERVE", nullable = true, precision = 4)
    public Long getSpeciaLreserve() {
        return speciaLreserve;
    }

    public void setSpeciaLreserve(Long speciaLreserve) {
        this.speciaLreserve = speciaLreserve;
    }

    @Basic
    @Column(name = "SUBSIDY_REC", nullable = true, precision = 4)
    public Long getSubsidyRec() {
        return subsidyRec;
    }

    public void setSubsidyRec(Long subsidyRec) {
        this.subsidyRec = subsidyRec;
    }

    @Basic
    @Column(name = "MARGINOUT_FUND", nullable = true, precision = 4)
    public Long getMarginoutFund() {
        return marginoutFund;
    }

    public void setMarginoutFund(Long marginoutFund) {
        this.marginoutFund = marginoutFund;
    }

    @Basic
    @Column(name = "EXPORT_REBATE_REC", nullable = true, precision = 4)
    public Long getExportRebateRec() {
        return exportRebateRec;
    }

    public void setExportRebateRec(Long exportRebateRec) {
        this.exportRebateRec = exportRebateRec;
    }

    @Basic
    @Column(name = "DEFER_INCOME_ONEYEAR", nullable = true, precision = 4)
    public Long getDeferIncomeOneyear() {
        return deferIncomeOneyear;
    }

    public void setDeferIncomeOneyear(Long deferIncomeOneyear) {
        this.deferIncomeOneyear = deferIncomeOneyear;
    }

    @Basic
    @Column(name = "LT_SALARY_PAY", nullable = true, precision = 4)
    public Long getLtSalaryPay() {
        return ltSalaryPay;
    }

    public void setLtSalaryPay(Long ltSalaryPay) {
        this.ltSalaryPay = ltSalaryPay;
    }

    @Basic
    @Column(name = "FVALUE_FASSET", nullable = true, precision = 4)
    public Long getFvalueFasset() {
        return fvalueFasset;
    }

    public void setFvalueFasset(Long fvalueFasset) {
        this.fvalueFasset = fvalueFasset;
    }

    @Basic
    @Column(name = "DEFINE_FVALUE_FASSET", nullable = true, precision = 4)
    public Long getDefineFvalueFasset() {
        return defineFvalueFasset;
    }

    public void setDefineFvalueFasset(Long defineFvalueFasset) {
        this.defineFvalueFasset = defineFvalueFasset;
    }

    @Basic
    @Column(name = "INTERNAL_REC", nullable = true, precision = 4)
    public Long getInternalRec() {
        return internalRec;
    }

    public void setInternalRec(Long internalRec) {
        this.internalRec = internalRec;
    }

    @Basic
    @Column(name = "CLHELD_SALE_ASS", nullable = true, precision = 4)
    public Long getClheldSaleAss() {
        return clheldSaleAss;
    }

    public void setClheldSaleAss(Long clheldSaleAss) {
        this.clheldSaleAss = clheldSaleAss;
    }

    @Basic
    @Column(name = "FVALUE_FLIAB", nullable = true, precision = 4)
    public Long getFvalueFliab() {
        return fvalueFliab;
    }

    public void setFvalueFliab(Long fvalueFliab) {
        this.fvalueFliab = fvalueFliab;
    }

    @Basic
    @Column(name = "DEFINE_FVALUE_FLIAB", nullable = true, precision = 4)
    public Long getDefineFvalueFliab() {
        return defineFvalueFliab;
    }

    public void setDefineFvalueFliab(Long defineFvalueFliab) {
        this.defineFvalueFliab = defineFvalueFliab;
    }

    @Basic
    @Column(name = "INTERNAL_PAY", nullable = true, precision = 4)
    public Long getInternalPay() {
        return internalPay;
    }

    public void setInternalPay(Long internalPay) {
        this.internalPay = internalPay;
    }

    @Basic
    @Column(name = "CLHELD_SALE_LIAB", nullable = true, precision = 4)
    public Long getClheldSaleLiab() {
        return clheldSaleLiab;
    }

    public void setClheldSaleLiab(Long clheldSaleLiab) {
        this.clheldSaleLiab = clheldSaleLiab;
    }

    @Basic
    @Column(name = "ANTICIPATE_LLIAB", nullable = true, precision = 4)
    public Long getAnticipateLliab() {
        return anticipateLliab;
    }

    public void setAnticipateLliab(Long anticipateLliab) {
        this.anticipateLliab = anticipateLliab;
    }

    @Basic
    @Column(name = "OTHER_EQUITY", nullable = true, precision = 4)
    public Long getOtherEquity() {
        return otherEquity;
    }

    public void setOtherEquity(Long otherEquity) {
        this.otherEquity = otherEquity;
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
    @Column(name = "PLAN_CASH_DIVI", nullable = true, precision = 4)
    public Long getPlanCashDivi() {
        return planCashDivi;
    }

    public void setPlanCashDivi(Long planCashDivi) {
        this.planCashDivi = planCashDivi;
    }

    @Basic
    @Column(name = "PARENT_EQUITY_OTHER", nullable = true, precision = 4)
    public Long getParentEquityOther() {
        return parentEquityOther;
    }

    public void setParentEquityOther(Long parentEquityOther) {
        this.parentEquityOther = parentEquityOther;
    }

    @Basic
    @Column(name = "PARENT_EQUITY_BALANCE", nullable = true, precision = 4)
    public Long getParentEquityBalance() {
        return parentEquityBalance;
    }

    public void setParentEquityBalance(Long parentEquityBalance) {
        this.parentEquityBalance = parentEquityBalance;
    }

    @Basic
    @Column(name = "PREFERRED_STOCK", nullable = true, precision = 4)
    public Long getPreferredStock() {
        return preferredStock;
    }

    public void setPreferredStock(Long preferredStock) {
        this.preferredStock = preferredStock;
    }

    @Basic
    @Column(name = "PREFER_STOC_BOND", nullable = true, precision = 4)
    public Long getPreferStocBond() {
        return preferStocBond;
    }

    public void setPreferStocBond(Long preferStocBond) {
        this.preferStocBond = preferStocBond;
    }

    @Basic
    @Column(name = "CONS_BIOLO_ASSET", nullable = true, precision = 4)
    public Long getConsBioloAsset() {
        return consBioloAsset;
    }

    public void setConsBioloAsset(Long consBioloAsset) {
        this.consBioloAsset = consBioloAsset;
    }

    @Basic
    @Column(name = "STOCK_NUM_END", nullable = true, precision = 4)
    public Long getStockNumEnd() {
        return stockNumEnd;
    }

    public void setStockNumEnd(Long stockNumEnd) {
        this.stockNumEnd = stockNumEnd;
    }

    @Basic
    @Column(name = "NET_MAS_SET", nullable = true, precision = 4)
    public Long getNetMasSet() {
        return netMasSet;
    }

    public void setNetMasSet(Long netMasSet) {
        this.netMasSet = netMasSet;
    }

    @Basic
    @Column(name = "OUTWARD_REMITTANCE", nullable = true, precision = 4)
    public Long getOutwardRemittance() {
        return outwardRemittance;
    }

    public void setOutwardRemittance(Long outwardRemittance) {
        this.outwardRemittance = outwardRemittance;
    }

    @Basic
    @Column(name = "CDANDBILL_REC", nullable = true, precision = 4)
    public Long getCdandbillRec() {
        return cdandbillRec;
    }

    public void setCdandbillRec(Long cdandbillRec) {
        this.cdandbillRec = cdandbillRec;
    }

    @Basic
    @Column(name = "HEDGE_RESERVE", nullable = true, precision = 4)
    public Long getHedgeReserve() {
        return hedgeReserve;
    }

    public void setHedgeReserve(Long hedgeReserve) {
        this.hedgeReserve = hedgeReserve;
    }

    @Basic
    @Column(name = "SUGGEST_ASSIGN_DIVI", nullable = true, precision = 4)
    public Long getSuggestAssignDivi() {
        return suggestAssignDivi;
    }

    public void setSuggestAssignDivi(Long suggestAssignDivi) {
        this.suggestAssignDivi = suggestAssignDivi;
    }

    @Basic
    @Column(name = "MARGINOUT_SECURITY", nullable = true, precision = 4)
    public Long getMarginoutSecurity() {
        return marginoutSecurity;
    }

    public void setMarginoutSecurity(Long marginoutSecurity) {
        this.marginoutSecurity = marginoutSecurity;
    }

    @Basic
    @Column(name = "CAGENT_TRADE_SECURITY", nullable = true, precision = 4)
    public Long getCagentTradeSecurity() {
        return cagentTradeSecurity;
    }

    public void setCagentTradeSecurity(Long cagentTradeSecurity) {
        this.cagentTradeSecurity = cagentTradeSecurity;
    }

    @Basic
    @Column(name = "TRADE_RISK_PREPARE", nullable = true, precision = 4)
    public Long getTradeRiskPrepare() {
        return tradeRiskPrepare;
    }

    public void setTradeRiskPrepare(Long tradeRiskPrepare) {
        this.tradeRiskPrepare = tradeRiskPrepare;
    }

    @Basic
    @Column(name = "CREDITOR_PLANINV", nullable = true, precision = 4)
    public Long getCreditorPlaninv() {
        return creditorPlaninv;
    }

    public void setCreditorPlaninv(Long creditorPlaninv) {
        this.creditorPlaninv = creditorPlaninv;
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

        CompyBalancesheet that = (CompyBalancesheet) o;

        if (compyBalancesheetSid != null ? !compyBalancesheetSid.equals(that.compyBalancesheetSid) : that.compyBalancesheetSid != null)
            return false;
        if (fstNoticeDt != null ? !fstNoticeDt.equals(that.fstNoticeDt) : that.fstNoticeDt != null) return false;
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
        if (monetaryFund != null ? !monetaryFund.equals(that.monetaryFund) : that.monetaryFund != null) return false;
        if (tradefAsset != null ? !tradefAsset.equals(that.tradefAsset) : that.tradefAsset != null) return false;
        if (billRec != null ? !billRec.equals(that.billRec) : that.billRec != null) return false;
        if (accountRec != null ? !accountRec.equals(that.accountRec) : that.accountRec != null) return false;
        if (otherRec != null ? !otherRec.equals(that.otherRec) : that.otherRec != null) return false;
        if (advancePay != null ? !advancePay.equals(that.advancePay) : that.advancePay != null) return false;
        if (dividendRec != null ? !dividendRec.equals(that.dividendRec) : that.dividendRec != null) return false;
        if (interestRec != null ? !interestRec.equals(that.interestRec) : that.interestRec != null) return false;
        if (inventory != null ? !inventory.equals(that.inventory) : that.inventory != null) return false;
        if (nonlAssetOneyear != null ? !nonlAssetOneyear.equals(that.nonlAssetOneyear) : that.nonlAssetOneyear != null)
            return false;
        if (deferExpense != null ? !deferExpense.equals(that.deferExpense) : that.deferExpense != null) return false;
        if (otherLasset != null ? !otherLasset.equals(that.otherLasset) : that.otherLasset != null) return false;
        if (lassetOther != null ? !lassetOther.equals(that.lassetOther) : that.lassetOther != null) return false;
        if (lassetBalance != null ? !lassetBalance.equals(that.lassetBalance) : that.lassetBalance != null)
            return false;
        if (sumLasset != null ? !sumLasset.equals(that.sumLasset) : that.sumLasset != null) return false;
        if (saleableFasset != null ? !saleableFasset.equals(that.saleableFasset) : that.saleableFasset != null)
            return false;
        if (heldMaturityInv != null ? !heldMaturityInv.equals(that.heldMaturityInv) : that.heldMaturityInv != null)
            return false;
        if (estateInvest != null ? !estateInvest.equals(that.estateInvest) : that.estateInvest != null) return false;
        if (lteQuityInv != null ? !lteQuityInv.equals(that.lteQuityInv) : that.lteQuityInv != null) return false;
        if (ltrec != null ? !ltrec.equals(that.ltrec) : that.ltrec != null) return false;
        if (fixedAsset != null ? !fixedAsset.equals(that.fixedAsset) : that.fixedAsset != null) return false;
        if (constructionMaterial != null ? !constructionMaterial.equals(that.constructionMaterial) : that.constructionMaterial != null)
            return false;
        if (constructionProgress != null ? !constructionProgress.equals(that.constructionProgress) : that.constructionProgress != null)
            return false;
        if (liquidateFixedAsset != null ? !liquidateFixedAsset.equals(that.liquidateFixedAsset) : that.liquidateFixedAsset != null)
            return false;
        if (productBiologyAsset != null ? !productBiologyAsset.equals(that.productBiologyAsset) : that.productBiologyAsset != null)
            return false;
        if (oilgasAsset != null ? !oilgasAsset.equals(that.oilgasAsset) : that.oilgasAsset != null) return false;
        if (intangibleAsset != null ? !intangibleAsset.equals(that.intangibleAsset) : that.intangibleAsset != null)
            return false;
        if (developExp != null ? !developExp.equals(that.developExp) : that.developExp != null) return false;
        if (goodWill != null ? !goodWill.equals(that.goodWill) : that.goodWill != null) return false;
        if (ltdeferAsset != null ? !ltdeferAsset.equals(that.ltdeferAsset) : that.ltdeferAsset != null) return false;
        if (deferIncometaxAsset != null ? !deferIncometaxAsset.equals(that.deferIncometaxAsset) : that.deferIncometaxAsset != null)
            return false;
        if (otherNonlAsset != null ? !otherNonlAsset.equals(that.otherNonlAsset) : that.otherNonlAsset != null)
            return false;
        if (nonlassetOther != null ? !nonlassetOther.equals(that.nonlassetOther) : that.nonlassetOther != null)
            return false;
        if (nonlassetBalance != null ? !nonlassetBalance.equals(that.nonlassetBalance) : that.nonlassetBalance != null)
            return false;
        if (sumNonlAsset != null ? !sumNonlAsset.equals(that.sumNonlAsset) : that.sumNonlAsset != null) return false;
        if (cashAndDepositcbank != null ? !cashAndDepositcbank.equals(that.cashAndDepositcbank) : that.cashAndDepositcbank != null)
            return false;
        if (fiDeposit != null ? !fiDeposit.equals(that.fiDeposit) : that.fiDeposit != null) return false;
        if (preciousMetal != null ? !preciousMetal.equals(that.preciousMetal) : that.preciousMetal != null)
            return false;
        if (lendFund != null ? !lendFund.equals(that.lendFund) : that.lendFund != null) return false;
        if (deriveFasset != null ? !deriveFasset.equals(that.deriveFasset) : that.deriveFasset != null) return false;
        if (buySellbackFasset != null ? !buySellbackFasset.equals(that.buySellbackFasset) : that.buySellbackFasset != null)
            return false;
        if (loanAdvances != null ? !loanAdvances.equals(that.loanAdvances) : that.loanAdvances != null) return false;
        if (agencyAssets != null ? !agencyAssets.equals(that.agencyAssets) : that.agencyAssets != null) return false;
        if (premiumRec != null ? !premiumRec.equals(that.premiumRec) : that.premiumRec != null) return false;
        if (subrogationRec != null ? !subrogationRec.equals(that.subrogationRec) : that.subrogationRec != null)
            return false;
        if (riRec != null ? !riRec.equals(that.riRec) : that.riRec != null) return false;
        if (undueRireserveRec != null ? !undueRireserveRec.equals(that.undueRireserveRec) : that.undueRireserveRec != null)
            return false;
        if (claimRireserveRec != null ? !claimRireserveRec.equals(that.claimRireserveRec) : that.claimRireserveRec != null)
            return false;
        if (lifeRireserveRec != null ? !lifeRireserveRec.equals(that.lifeRireserveRec) : that.lifeRireserveRec != null)
            return false;
        if (lthealthRireserveRec != null ? !lthealthRireserveRec.equals(that.lthealthRireserveRec) : that.lthealthRireserveRec != null)
            return false;
        if (gdepositPay != null ? !gdepositPay.equals(that.gdepositPay) : that.gdepositPay != null) return false;
        if (insuredPledgeLoan != null ? !insuredPledgeLoan.equals(that.insuredPledgeLoan) : that.insuredPledgeLoan != null)
            return false;
        if (capitalgDepositPay != null ? !capitalgDepositPay.equals(that.capitalgDepositPay) : that.capitalgDepositPay != null)
            return false;
        if (independentAsset != null ? !independentAsset.equals(that.independentAsset) : that.independentAsset != null)
            return false;
        if (clientFund != null ? !clientFund.equals(that.clientFund) : that.clientFund != null) return false;
        if (settlementProvision != null ? !settlementProvision.equals(that.settlementProvision) : that.settlementProvision != null)
            return false;
        if (clientProvision != null ? !clientProvision.equals(that.clientProvision) : that.clientProvision != null)
            return false;
        if (seatFee != null ? !seatFee.equals(that.seatFee) : that.seatFee != null) return false;
        if (otherAsset != null ? !otherAsset.equals(that.otherAsset) : that.otherAsset != null) return false;
        if (assetOther != null ? !assetOther.equals(that.assetOther) : that.assetOther != null) return false;
        if (assetBalance != null ? !assetBalance.equals(that.assetBalance) : that.assetBalance != null) return false;
        if (sumAsset != null ? !sumAsset.equals(that.sumAsset) : that.sumAsset != null) return false;
        if (stBorrow != null ? !stBorrow.equals(that.stBorrow) : that.stBorrow != null) return false;
        if (tradeFliab != null ? !tradeFliab.equals(that.tradeFliab) : that.tradeFliab != null) return false;
        if (billPay != null ? !billPay.equals(that.billPay) : that.billPay != null) return false;
        if (accountPay != null ? !accountPay.equals(that.accountPay) : that.accountPay != null) return false;
        if (advanceReceive != null ? !advanceReceive.equals(that.advanceReceive) : that.advanceReceive != null)
            return false;
        if (salaryPay != null ? !salaryPay.equals(that.salaryPay) : that.salaryPay != null) return false;
        if (taxPay != null ? !taxPay.equals(that.taxPay) : that.taxPay != null) return false;
        if (interestPay != null ? !interestPay.equals(that.interestPay) : that.interestPay != null) return false;
        if (dividendPay != null ? !dividendPay.equals(that.dividendPay) : that.dividendPay != null) return false;
        if (otherPay != null ? !otherPay.equals(that.otherPay) : that.otherPay != null) return false;
        if (accrueExpense != null ? !accrueExpense.equals(that.accrueExpense) : that.accrueExpense != null)
            return false;
        if (anticipateLiab != null ? !anticipateLiab.equals(that.anticipateLiab) : that.anticipateLiab != null)
            return false;
        if (deferIncome != null ? !deferIncome.equals(that.deferIncome) : that.deferIncome != null) return false;
        if (nonlLiabOneyear != null ? !nonlLiabOneyear.equals(that.nonlLiabOneyear) : that.nonlLiabOneyear != null)
            return false;
        if (otherLliab != null ? !otherLliab.equals(that.otherLliab) : that.otherLliab != null) return false;
        if (lliabOther != null ? !lliabOther.equals(that.lliabOther) : that.lliabOther != null) return false;
        if (lliabBalance != null ? !lliabBalance.equals(that.lliabBalance) : that.lliabBalance != null) return false;
        if (sumLliab != null ? !sumLliab.equals(that.sumLliab) : that.sumLliab != null) return false;
        if (ltBorrow != null ? !ltBorrow.equals(that.ltBorrow) : that.ltBorrow != null) return false;
        if (bondPay != null ? !bondPay.equals(that.bondPay) : that.bondPay != null) return false;
        if (ltAccountPay != null ? !ltAccountPay.equals(that.ltAccountPay) : that.ltAccountPay != null) return false;
        if (specialPay != null ? !specialPay.equals(that.specialPay) : that.specialPay != null) return false;
        if (deferIncometaxLiab != null ? !deferIncometaxLiab.equals(that.deferIncometaxLiab) : that.deferIncometaxLiab != null)
            return false;
        if (otherNonlLiab != null ? !otherNonlLiab.equals(that.otherNonlLiab) : that.otherNonlLiab != null)
            return false;
        if (nonlLiabOther != null ? !nonlLiabOther.equals(that.nonlLiabOther) : that.nonlLiabOther != null)
            return false;
        if (nonlLiabBalance != null ? !nonlLiabBalance.equals(that.nonlLiabBalance) : that.nonlLiabBalance != null)
            return false;
        if (sumNonlLiab != null ? !sumNonlLiab.equals(that.sumNonlLiab) : that.sumNonlLiab != null) return false;
        if (borrowFromCbank != null ? !borrowFromCbank.equals(that.borrowFromCbank) : that.borrowFromCbank != null)
            return false;
        if (borrowFund != null ? !borrowFund.equals(that.borrowFund) : that.borrowFund != null) return false;
        if (deriveFinancedebt != null ? !deriveFinancedebt.equals(that.deriveFinancedebt) : that.deriveFinancedebt != null)
            return false;
        if (sellBuybackFasset != null ? !sellBuybackFasset.equals(that.sellBuybackFasset) : that.sellBuybackFasset != null)
            return false;
        if (acceptDeposit != null ? !acceptDeposit.equals(that.acceptDeposit) : that.acceptDeposit != null)
            return false;
        if (agencyLiab != null ? !agencyLiab.equals(that.agencyLiab) : that.agencyLiab != null) return false;
        if (otherLiab != null ? !otherLiab.equals(that.otherLiab) : that.otherLiab != null) return false;
        if (premiumAdvance != null ? !premiumAdvance.equals(that.premiumAdvance) : that.premiumAdvance != null)
            return false;
        if (commPay != null ? !commPay.equals(that.commPay) : that.commPay != null) return false;
        if (riPay != null ? !riPay.equals(that.riPay) : that.riPay != null) return false;
        if (gdepositRec != null ? !gdepositRec.equals(that.gdepositRec) : that.gdepositRec != null) return false;
        if (insuredDepositInv != null ? !insuredDepositInv.equals(that.insuredDepositInv) : that.insuredDepositInv != null)
            return false;
        if (undueReserve != null ? !undueReserve.equals(that.undueReserve) : that.undueReserve != null) return false;
        if (claimReserve != null ? !claimReserve.equals(that.claimReserve) : that.claimReserve != null) return false;
        if (lifeReserve != null ? !lifeReserve.equals(that.lifeReserve) : that.lifeReserve != null) return false;
        if (ltHealthReserve != null ? !ltHealthReserve.equals(that.ltHealthReserve) : that.ltHealthReserve != null)
            return false;
        if (independentLiab != null ? !independentLiab.equals(that.independentLiab) : that.independentLiab != null)
            return false;
        if (pledgeBorrow != null ? !pledgeBorrow.equals(that.pledgeBorrow) : that.pledgeBorrow != null) return false;
        if (agentTradeSecurity != null ? !agentTradeSecurity.equals(that.agentTradeSecurity) : that.agentTradeSecurity != null)
            return false;
        if (agentUwSecurity != null ? !agentUwSecurity.equals(that.agentUwSecurity) : that.agentUwSecurity != null)
            return false;
        if (liabOther != null ? !liabOther.equals(that.liabOther) : that.liabOther != null) return false;
        if (liabBalance != null ? !liabBalance.equals(that.liabBalance) : that.liabBalance != null) return false;
        if (sumLiab != null ? !sumLiab.equals(that.sumLiab) : that.sumLiab != null) return false;
        if (shareCapital != null ? !shareCapital.equals(that.shareCapital) : that.shareCapital != null) return false;
        if (capitalReserve != null ? !capitalReserve.equals(that.capitalReserve) : that.capitalReserve != null)
            return false;
        if (surplusReserve != null ? !surplusReserve.equals(that.surplusReserve) : that.surplusReserve != null)
            return false;
        if (retainedEarning != null ? !retainedEarning.equals(that.retainedEarning) : that.retainedEarning != null)
            return false;
        if (inventoryShare != null ? !inventoryShare.equals(that.inventoryShare) : that.inventoryShare != null)
            return false;
        if (generalRiskPrepare != null ? !generalRiskPrepare.equals(that.generalRiskPrepare) : that.generalRiskPrepare != null)
            return false;
        if (diffConversionFc != null ? !diffConversionFc.equals(that.diffConversionFc) : that.diffConversionFc != null)
            return false;
        if (minorityEquity != null ? !minorityEquity.equals(that.minorityEquity) : that.minorityEquity != null)
            return false;
        if (shEquityOther != null ? !shEquityOther.equals(that.shEquityOther) : that.shEquityOther != null)
            return false;
        if (shEquityBalance != null ? !shEquityBalance.equals(that.shEquityBalance) : that.shEquityBalance != null)
            return false;
        if (sumParentEquity != null ? !sumParentEquity.equals(that.sumParentEquity) : that.sumParentEquity != null)
            return false;
        if (sumShEquity != null ? !sumShEquity.equals(that.sumShEquity) : that.sumShEquity != null) return false;
        if (liabshEquityOther != null ? !liabshEquityOther.equals(that.liabshEquityOther) : that.liabshEquityOther != null)
            return false;
        if (liabshEquityBalance != null ? !liabshEquityBalance.equals(that.liabshEquityBalance) : that.liabshEquityBalance != null)
            return false;
        if (sumLiabshEquity != null ? !sumLiabshEquity.equals(that.sumLiabshEquity) : that.sumLiabshEquity != null)
            return false;
        if (tdEposit != null ? !tdEposit.equals(that.tdEposit) : that.tdEposit != null) return false;
        if (stBondRec != null ? !stBondRec.equals(that.stBondRec) : that.stBondRec != null) return false;
        if (claimPay != null ? !claimPay.equals(that.claimPay) : that.claimPay != null) return false;
        if (policyDiviPay != null ? !policyDiviPay.equals(that.policyDiviPay) : that.policyDiviPay != null)
            return false;
        if (unconfirmInvLoss != null ? !unconfirmInvLoss.equals(that.unconfirmInvLoss) : that.unconfirmInvLoss != null)
            return false;
        if (ricontactReserveRec != null ? !ricontactReserveRec.equals(that.ricontactReserveRec) : that.ricontactReserveRec != null)
            return false;
        if (deposit != null ? !deposit.equals(that.deposit) : that.deposit != null) return false;
        if (contactReserve != null ? !contactReserve.equals(that.contactReserve) : that.contactReserve != null)
            return false;
        if (investRec != null ? !investRec.equals(that.investRec) : that.investRec != null) return false;
        if (speciaLreserve != null ? !speciaLreserve.equals(that.speciaLreserve) : that.speciaLreserve != null)
            return false;
        if (subsidyRec != null ? !subsidyRec.equals(that.subsidyRec) : that.subsidyRec != null) return false;
        if (marginoutFund != null ? !marginoutFund.equals(that.marginoutFund) : that.marginoutFund != null)
            return false;
        if (exportRebateRec != null ? !exportRebateRec.equals(that.exportRebateRec) : that.exportRebateRec != null)
            return false;
        if (deferIncomeOneyear != null ? !deferIncomeOneyear.equals(that.deferIncomeOneyear) : that.deferIncomeOneyear != null)
            return false;
        if (ltSalaryPay != null ? !ltSalaryPay.equals(that.ltSalaryPay) : that.ltSalaryPay != null) return false;
        if (fvalueFasset != null ? !fvalueFasset.equals(that.fvalueFasset) : that.fvalueFasset != null) return false;
        if (defineFvalueFasset != null ? !defineFvalueFasset.equals(that.defineFvalueFasset) : that.defineFvalueFasset != null)
            return false;
        if (internalRec != null ? !internalRec.equals(that.internalRec) : that.internalRec != null) return false;
        if (clheldSaleAss != null ? !clheldSaleAss.equals(that.clheldSaleAss) : that.clheldSaleAss != null)
            return false;
        if (fvalueFliab != null ? !fvalueFliab.equals(that.fvalueFliab) : that.fvalueFliab != null) return false;
        if (defineFvalueFliab != null ? !defineFvalueFliab.equals(that.defineFvalueFliab) : that.defineFvalueFliab != null)
            return false;
        if (internalPay != null ? !internalPay.equals(that.internalPay) : that.internalPay != null) return false;
        if (clheldSaleLiab != null ? !clheldSaleLiab.equals(that.clheldSaleLiab) : that.clheldSaleLiab != null)
            return false;
        if (anticipateLliab != null ? !anticipateLliab.equals(that.anticipateLliab) : that.anticipateLliab != null)
            return false;
        if (otherEquity != null ? !otherEquity.equals(that.otherEquity) : that.otherEquity != null) return false;
        if (otherCincome != null ? !otherCincome.equals(that.otherCincome) : that.otherCincome != null) return false;
        if (planCashDivi != null ? !planCashDivi.equals(that.planCashDivi) : that.planCashDivi != null) return false;
        if (parentEquityOther != null ? !parentEquityOther.equals(that.parentEquityOther) : that.parentEquityOther != null)
            return false;
        if (parentEquityBalance != null ? !parentEquityBalance.equals(that.parentEquityBalance) : that.parentEquityBalance != null)
            return false;
        if (preferredStock != null ? !preferredStock.equals(that.preferredStock) : that.preferredStock != null)
            return false;
        if (preferStocBond != null ? !preferStocBond.equals(that.preferStocBond) : that.preferStocBond != null)
            return false;
        if (consBioloAsset != null ? !consBioloAsset.equals(that.consBioloAsset) : that.consBioloAsset != null)
            return false;
        if (stockNumEnd != null ? !stockNumEnd.equals(that.stockNumEnd) : that.stockNumEnd != null) return false;
        if (netMasSet != null ? !netMasSet.equals(that.netMasSet) : that.netMasSet != null) return false;
        if (outwardRemittance != null ? !outwardRemittance.equals(that.outwardRemittance) : that.outwardRemittance != null)
            return false;
        if (cdandbillRec != null ? !cdandbillRec.equals(that.cdandbillRec) : that.cdandbillRec != null) return false;
        if (hedgeReserve != null ? !hedgeReserve.equals(that.hedgeReserve) : that.hedgeReserve != null) return false;
        if (suggestAssignDivi != null ? !suggestAssignDivi.equals(that.suggestAssignDivi) : that.suggestAssignDivi != null)
            return false;
        if (marginoutSecurity != null ? !marginoutSecurity.equals(that.marginoutSecurity) : that.marginoutSecurity != null)
            return false;
        if (cagentTradeSecurity != null ? !cagentTradeSecurity.equals(that.cagentTradeSecurity) : that.cagentTradeSecurity != null)
            return false;
        if (tradeRiskPrepare != null ? !tradeRiskPrepare.equals(that.tradeRiskPrepare) : that.tradeRiskPrepare != null)
            return false;
        if (creditorPlaninv != null ? !creditorPlaninv.equals(that.creditorPlaninv) : that.creditorPlaninv != null)
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
        int result = compyBalancesheetSid != null ? compyBalancesheetSid.hashCode() : 0;
        result = 31 * result + (fstNoticeDt != null ? fstNoticeDt.hashCode() : 0);
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
        result = 31 * result + (monetaryFund != null ? monetaryFund.hashCode() : 0);
        result = 31 * result + (tradefAsset != null ? tradefAsset.hashCode() : 0);
        result = 31 * result + (billRec != null ? billRec.hashCode() : 0);
        result = 31 * result + (accountRec != null ? accountRec.hashCode() : 0);
        result = 31 * result + (otherRec != null ? otherRec.hashCode() : 0);
        result = 31 * result + (advancePay != null ? advancePay.hashCode() : 0);
        result = 31 * result + (dividendRec != null ? dividendRec.hashCode() : 0);
        result = 31 * result + (interestRec != null ? interestRec.hashCode() : 0);
        result = 31 * result + (inventory != null ? inventory.hashCode() : 0);
        result = 31 * result + (nonlAssetOneyear != null ? nonlAssetOneyear.hashCode() : 0);
        result = 31 * result + (deferExpense != null ? deferExpense.hashCode() : 0);
        result = 31 * result + (otherLasset != null ? otherLasset.hashCode() : 0);
        result = 31 * result + (lassetOther != null ? lassetOther.hashCode() : 0);
        result = 31 * result + (lassetBalance != null ? lassetBalance.hashCode() : 0);
        result = 31 * result + (sumLasset != null ? sumLasset.hashCode() : 0);
        result = 31 * result + (saleableFasset != null ? saleableFasset.hashCode() : 0);
        result = 31 * result + (heldMaturityInv != null ? heldMaturityInv.hashCode() : 0);
        result = 31 * result + (estateInvest != null ? estateInvest.hashCode() : 0);
        result = 31 * result + (lteQuityInv != null ? lteQuityInv.hashCode() : 0);
        result = 31 * result + (ltrec != null ? ltrec.hashCode() : 0);
        result = 31 * result + (fixedAsset != null ? fixedAsset.hashCode() : 0);
        result = 31 * result + (constructionMaterial != null ? constructionMaterial.hashCode() : 0);
        result = 31 * result + (constructionProgress != null ? constructionProgress.hashCode() : 0);
        result = 31 * result + (liquidateFixedAsset != null ? liquidateFixedAsset.hashCode() : 0);
        result = 31 * result + (productBiologyAsset != null ? productBiologyAsset.hashCode() : 0);
        result = 31 * result + (oilgasAsset != null ? oilgasAsset.hashCode() : 0);
        result = 31 * result + (intangibleAsset != null ? intangibleAsset.hashCode() : 0);
        result = 31 * result + (developExp != null ? developExp.hashCode() : 0);
        result = 31 * result + (goodWill != null ? goodWill.hashCode() : 0);
        result = 31 * result + (ltdeferAsset != null ? ltdeferAsset.hashCode() : 0);
        result = 31 * result + (deferIncometaxAsset != null ? deferIncometaxAsset.hashCode() : 0);
        result = 31 * result + (otherNonlAsset != null ? otherNonlAsset.hashCode() : 0);
        result = 31 * result + (nonlassetOther != null ? nonlassetOther.hashCode() : 0);
        result = 31 * result + (nonlassetBalance != null ? nonlassetBalance.hashCode() : 0);
        result = 31 * result + (sumNonlAsset != null ? sumNonlAsset.hashCode() : 0);
        result = 31 * result + (cashAndDepositcbank != null ? cashAndDepositcbank.hashCode() : 0);
        result = 31 * result + (fiDeposit != null ? fiDeposit.hashCode() : 0);
        result = 31 * result + (preciousMetal != null ? preciousMetal.hashCode() : 0);
        result = 31 * result + (lendFund != null ? lendFund.hashCode() : 0);
        result = 31 * result + (deriveFasset != null ? deriveFasset.hashCode() : 0);
        result = 31 * result + (buySellbackFasset != null ? buySellbackFasset.hashCode() : 0);
        result = 31 * result + (loanAdvances != null ? loanAdvances.hashCode() : 0);
        result = 31 * result + (agencyAssets != null ? agencyAssets.hashCode() : 0);
        result = 31 * result + (premiumRec != null ? premiumRec.hashCode() : 0);
        result = 31 * result + (subrogationRec != null ? subrogationRec.hashCode() : 0);
        result = 31 * result + (riRec != null ? riRec.hashCode() : 0);
        result = 31 * result + (undueRireserveRec != null ? undueRireserveRec.hashCode() : 0);
        result = 31 * result + (claimRireserveRec != null ? claimRireserveRec.hashCode() : 0);
        result = 31 * result + (lifeRireserveRec != null ? lifeRireserveRec.hashCode() : 0);
        result = 31 * result + (lthealthRireserveRec != null ? lthealthRireserveRec.hashCode() : 0);
        result = 31 * result + (gdepositPay != null ? gdepositPay.hashCode() : 0);
        result = 31 * result + (insuredPledgeLoan != null ? insuredPledgeLoan.hashCode() : 0);
        result = 31 * result + (capitalgDepositPay != null ? capitalgDepositPay.hashCode() : 0);
        result = 31 * result + (independentAsset != null ? independentAsset.hashCode() : 0);
        result = 31 * result + (clientFund != null ? clientFund.hashCode() : 0);
        result = 31 * result + (settlementProvision != null ? settlementProvision.hashCode() : 0);
        result = 31 * result + (clientProvision != null ? clientProvision.hashCode() : 0);
        result = 31 * result + (seatFee != null ? seatFee.hashCode() : 0);
        result = 31 * result + (otherAsset != null ? otherAsset.hashCode() : 0);
        result = 31 * result + (assetOther != null ? assetOther.hashCode() : 0);
        result = 31 * result + (assetBalance != null ? assetBalance.hashCode() : 0);
        result = 31 * result + (sumAsset != null ? sumAsset.hashCode() : 0);
        result = 31 * result + (stBorrow != null ? stBorrow.hashCode() : 0);
        result = 31 * result + (tradeFliab != null ? tradeFliab.hashCode() : 0);
        result = 31 * result + (billPay != null ? billPay.hashCode() : 0);
        result = 31 * result + (accountPay != null ? accountPay.hashCode() : 0);
        result = 31 * result + (advanceReceive != null ? advanceReceive.hashCode() : 0);
        result = 31 * result + (salaryPay != null ? salaryPay.hashCode() : 0);
        result = 31 * result + (taxPay != null ? taxPay.hashCode() : 0);
        result = 31 * result + (interestPay != null ? interestPay.hashCode() : 0);
        result = 31 * result + (dividendPay != null ? dividendPay.hashCode() : 0);
        result = 31 * result + (otherPay != null ? otherPay.hashCode() : 0);
        result = 31 * result + (accrueExpense != null ? accrueExpense.hashCode() : 0);
        result = 31 * result + (anticipateLiab != null ? anticipateLiab.hashCode() : 0);
        result = 31 * result + (deferIncome != null ? deferIncome.hashCode() : 0);
        result = 31 * result + (nonlLiabOneyear != null ? nonlLiabOneyear.hashCode() : 0);
        result = 31 * result + (otherLliab != null ? otherLliab.hashCode() : 0);
        result = 31 * result + (lliabOther != null ? lliabOther.hashCode() : 0);
        result = 31 * result + (lliabBalance != null ? lliabBalance.hashCode() : 0);
        result = 31 * result + (sumLliab != null ? sumLliab.hashCode() : 0);
        result = 31 * result + (ltBorrow != null ? ltBorrow.hashCode() : 0);
        result = 31 * result + (bondPay != null ? bondPay.hashCode() : 0);
        result = 31 * result + (ltAccountPay != null ? ltAccountPay.hashCode() : 0);
        result = 31 * result + (specialPay != null ? specialPay.hashCode() : 0);
        result = 31 * result + (deferIncometaxLiab != null ? deferIncometaxLiab.hashCode() : 0);
        result = 31 * result + (otherNonlLiab != null ? otherNonlLiab.hashCode() : 0);
        result = 31 * result + (nonlLiabOther != null ? nonlLiabOther.hashCode() : 0);
        result = 31 * result + (nonlLiabBalance != null ? nonlLiabBalance.hashCode() : 0);
        result = 31 * result + (sumNonlLiab != null ? sumNonlLiab.hashCode() : 0);
        result = 31 * result + (borrowFromCbank != null ? borrowFromCbank.hashCode() : 0);
        result = 31 * result + (borrowFund != null ? borrowFund.hashCode() : 0);
        result = 31 * result + (deriveFinancedebt != null ? deriveFinancedebt.hashCode() : 0);
        result = 31 * result + (sellBuybackFasset != null ? sellBuybackFasset.hashCode() : 0);
        result = 31 * result + (acceptDeposit != null ? acceptDeposit.hashCode() : 0);
        result = 31 * result + (agencyLiab != null ? agencyLiab.hashCode() : 0);
        result = 31 * result + (otherLiab != null ? otherLiab.hashCode() : 0);
        result = 31 * result + (premiumAdvance != null ? premiumAdvance.hashCode() : 0);
        result = 31 * result + (commPay != null ? commPay.hashCode() : 0);
        result = 31 * result + (riPay != null ? riPay.hashCode() : 0);
        result = 31 * result + (gdepositRec != null ? gdepositRec.hashCode() : 0);
        result = 31 * result + (insuredDepositInv != null ? insuredDepositInv.hashCode() : 0);
        result = 31 * result + (undueReserve != null ? undueReserve.hashCode() : 0);
        result = 31 * result + (claimReserve != null ? claimReserve.hashCode() : 0);
        result = 31 * result + (lifeReserve != null ? lifeReserve.hashCode() : 0);
        result = 31 * result + (ltHealthReserve != null ? ltHealthReserve.hashCode() : 0);
        result = 31 * result + (independentLiab != null ? independentLiab.hashCode() : 0);
        result = 31 * result + (pledgeBorrow != null ? pledgeBorrow.hashCode() : 0);
        result = 31 * result + (agentTradeSecurity != null ? agentTradeSecurity.hashCode() : 0);
        result = 31 * result + (agentUwSecurity != null ? agentUwSecurity.hashCode() : 0);
        result = 31 * result + (liabOther != null ? liabOther.hashCode() : 0);
        result = 31 * result + (liabBalance != null ? liabBalance.hashCode() : 0);
        result = 31 * result + (sumLiab != null ? sumLiab.hashCode() : 0);
        result = 31 * result + (shareCapital != null ? shareCapital.hashCode() : 0);
        result = 31 * result + (capitalReserve != null ? capitalReserve.hashCode() : 0);
        result = 31 * result + (surplusReserve != null ? surplusReserve.hashCode() : 0);
        result = 31 * result + (retainedEarning != null ? retainedEarning.hashCode() : 0);
        result = 31 * result + (inventoryShare != null ? inventoryShare.hashCode() : 0);
        result = 31 * result + (generalRiskPrepare != null ? generalRiskPrepare.hashCode() : 0);
        result = 31 * result + (diffConversionFc != null ? diffConversionFc.hashCode() : 0);
        result = 31 * result + (minorityEquity != null ? minorityEquity.hashCode() : 0);
        result = 31 * result + (shEquityOther != null ? shEquityOther.hashCode() : 0);
        result = 31 * result + (shEquityBalance != null ? shEquityBalance.hashCode() : 0);
        result = 31 * result + (sumParentEquity != null ? sumParentEquity.hashCode() : 0);
        result = 31 * result + (sumShEquity != null ? sumShEquity.hashCode() : 0);
        result = 31 * result + (liabshEquityOther != null ? liabshEquityOther.hashCode() : 0);
        result = 31 * result + (liabshEquityBalance != null ? liabshEquityBalance.hashCode() : 0);
        result = 31 * result + (sumLiabshEquity != null ? sumLiabshEquity.hashCode() : 0);
        result = 31 * result + (tdEposit != null ? tdEposit.hashCode() : 0);
        result = 31 * result + (stBondRec != null ? stBondRec.hashCode() : 0);
        result = 31 * result + (claimPay != null ? claimPay.hashCode() : 0);
        result = 31 * result + (policyDiviPay != null ? policyDiviPay.hashCode() : 0);
        result = 31 * result + (unconfirmInvLoss != null ? unconfirmInvLoss.hashCode() : 0);
        result = 31 * result + (ricontactReserveRec != null ? ricontactReserveRec.hashCode() : 0);
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        result = 31 * result + (contactReserve != null ? contactReserve.hashCode() : 0);
        result = 31 * result + (investRec != null ? investRec.hashCode() : 0);
        result = 31 * result + (speciaLreserve != null ? speciaLreserve.hashCode() : 0);
        result = 31 * result + (subsidyRec != null ? subsidyRec.hashCode() : 0);
        result = 31 * result + (marginoutFund != null ? marginoutFund.hashCode() : 0);
        result = 31 * result + (exportRebateRec != null ? exportRebateRec.hashCode() : 0);
        result = 31 * result + (deferIncomeOneyear != null ? deferIncomeOneyear.hashCode() : 0);
        result = 31 * result + (ltSalaryPay != null ? ltSalaryPay.hashCode() : 0);
        result = 31 * result + (fvalueFasset != null ? fvalueFasset.hashCode() : 0);
        result = 31 * result + (defineFvalueFasset != null ? defineFvalueFasset.hashCode() : 0);
        result = 31 * result + (internalRec != null ? internalRec.hashCode() : 0);
        result = 31 * result + (clheldSaleAss != null ? clheldSaleAss.hashCode() : 0);
        result = 31 * result + (fvalueFliab != null ? fvalueFliab.hashCode() : 0);
        result = 31 * result + (defineFvalueFliab != null ? defineFvalueFliab.hashCode() : 0);
        result = 31 * result + (internalPay != null ? internalPay.hashCode() : 0);
        result = 31 * result + (clheldSaleLiab != null ? clheldSaleLiab.hashCode() : 0);
        result = 31 * result + (anticipateLliab != null ? anticipateLliab.hashCode() : 0);
        result = 31 * result + (otherEquity != null ? otherEquity.hashCode() : 0);
        result = 31 * result + (otherCincome != null ? otherCincome.hashCode() : 0);
        result = 31 * result + (planCashDivi != null ? planCashDivi.hashCode() : 0);
        result = 31 * result + (parentEquityOther != null ? parentEquityOther.hashCode() : 0);
        result = 31 * result + (parentEquityBalance != null ? parentEquityBalance.hashCode() : 0);
        result = 31 * result + (preferredStock != null ? preferredStock.hashCode() : 0);
        result = 31 * result + (preferStocBond != null ? preferStocBond.hashCode() : 0);
        result = 31 * result + (consBioloAsset != null ? consBioloAsset.hashCode() : 0);
        result = 31 * result + (stockNumEnd != null ? stockNumEnd.hashCode() : 0);
        result = 31 * result + (netMasSet != null ? netMasSet.hashCode() : 0);
        result = 31 * result + (outwardRemittance != null ? outwardRemittance.hashCode() : 0);
        result = 31 * result + (cdandbillRec != null ? cdandbillRec.hashCode() : 0);
        result = 31 * result + (hedgeReserve != null ? hedgeReserve.hashCode() : 0);
        result = 31 * result + (suggestAssignDivi != null ? suggestAssignDivi.hashCode() : 0);
        result = 31 * result + (marginoutSecurity != null ? marginoutSecurity.hashCode() : 0);
        result = 31 * result + (cagentTradeSecurity != null ? cagentTradeSecurity.hashCode() : 0);
        result = 31 * result + (tradeRiskPrepare != null ? tradeRiskPrepare.hashCode() : 0);
        result = 31 * result + (creditorPlaninv != null ? creditorPlaninv.hashCode() : 0);
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

