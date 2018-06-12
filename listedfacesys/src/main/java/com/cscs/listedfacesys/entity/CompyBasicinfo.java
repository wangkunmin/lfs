package com.cscs.listedfacesys.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sh on 2016/10/17.
 */
@Entity
@Table(name = "COMPY_BASICINFO", schema = "CS_FACEBOOK_1", catalog = "")
public class CompyBasicinfo {
    private long companyId;
    private String companyCd;
    private String companyNm;
    private String companySnm;
    private String clensCompanyNm;
    private String fenNm;
    private String legRepresent;
    private String chairman;
    private String gmanager;
    private String bsecretary;
    private Long orgFormId;
    private Date foundDt;
    private String currency;
    private Long regCapital;
    private String country;
    private Long region;
    private Long city;
    private String regAddr;
    private String officeAddr;
    private String officePostCd;
    private String companyPh;
    private String companyFax;
    private String companyEm;
    private String companyWeb;
    private String businScope;
    private String mainBusin;
    private Long employNum;
    private String blnumb;
    private String ntrnum;
    private String ltrnum;
    private String orgnum;
    private String regDt;
    private String infoUrl;
    private String infoNews;
    private String accountingFirm;
    private String legalAdvisor;
    private Long companySt;
    private Long isDel;
    private Date srcUpdtDt;
    private String srcCompanyCd;
    private String srcCd;
    private Timestamp updtDt;

    @Id
    @Column(name = "COMPANY_ID", nullable = false, precision = 0)
    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "COMPANY_CD", nullable = false, length = 30)
    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    @Basic
    @Column(name = "COMPANY_NM", nullable = false, length = 300)
    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    @Basic
    @Column(name = "COMPANY_SNM", nullable = true, length = 100)
    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    @Basic
    @Column(name = "CLENS_COMPANY_NM", nullable = true, length = 300)
    public String getClensCompanyNm() {
        return clensCompanyNm;
    }

    public void setClensCompanyNm(String clensCompanyNm) {
        this.clensCompanyNm = clensCompanyNm;
    }

    @Basic
    @Column(name = "FEN_NM", nullable = true, length = 200)
    public String getFenNm() {
        return fenNm;
    }

    public void setFenNm(String fenNm) {
        this.fenNm = fenNm;
    }

    @Basic
    @Column(name = "LEG_REPRESENT", nullable = true, length = 80)
    public String getLegRepresent() {
        return legRepresent;
    }

    public void setLegRepresent(String legRepresent) {
        this.legRepresent = legRepresent;
    }

    @Basic
    @Column(name = "CHAIRMAN", nullable = true, length = 80)
    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    @Basic
    @Column(name = "GMANAGER", nullable = true, length = 80)
    public String getGmanager() {
        return gmanager;
    }

    public void setGmanager(String gmanager) {
        this.gmanager = gmanager;
    }

    @Basic
    @Column(name = "BSECRETARY", nullable = true, length = 80)
    public String getBsecretary() {
        return bsecretary;
    }

    public void setBsecretary(String bsecretary) {
        this.bsecretary = bsecretary;
    }

    @Basic
    @Column(name = "ORG_FORM_ID", nullable = true, precision = 0)
    public Long getOrgFormId() {
        return orgFormId;
    }

    public void setOrgFormId(Long orgFormId) {
        this.orgFormId = orgFormId;
    }

    @Basic
    @Column(name = "FOUND_DT", nullable = true)
    public Date getFoundDt() {
        return foundDt;
    }

    public void setFoundDt(Date foundDt) {
        this.foundDt = foundDt;
    }

    @Basic
    @Column(name = "CURRENCY", nullable = true, length = 20)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "REG_CAPITAL", nullable = true, precision = 4)
    public Long getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Long regCapital) {
        this.regCapital = regCapital;
    }

    @Basic
    @Column(name = "COUNTRY", nullable = true, length = 6)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "REGION", nullable = true, precision = 0)
    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    @Basic
    @Column(name = "CITY", nullable = true, precision = 0)
    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    @Basic
    @Column(name = "REG_ADDR", nullable = true, length = 300)
    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    @Basic
    @Column(name = "OFFICE_ADDR", nullable = true, length = 300)
    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    @Basic
    @Column(name = "OFFICE_POST_CD", nullable = true, length = 10)
    public String getOfficePostCd() {
        return officePostCd;
    }

    public void setOfficePostCd(String officePostCd) {
        this.officePostCd = officePostCd;
    }

    @Basic
    @Column(name = "COMPANY_PH", nullable = true, length = 30)
    public String getCompanyPh() {
        return companyPh;
    }

    public void setCompanyPh(String companyPh) {
        this.companyPh = companyPh;
    }

    @Basic
    @Column(name = "COMPANY_FAX", nullable = true, length = 30)
    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    @Basic
    @Column(name = "COMPANY_EM", nullable = true, length = 60)
    public String getCompanyEm() {
        return companyEm;
    }

    public void setCompanyEm(String companyEm) {
        this.companyEm = companyEm;
    }

    @Basic
    @Column(name = "COMPANY_WEB", nullable = true, length = 100)
    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    @Basic
    @Column(name = "BUSIN_SCOPE", nullable = true, length = 4000)
    public String getBusinScope() {
        return businScope;
    }

    public void setBusinScope(String businScope) {
        this.businScope = businScope;
    }

    @Basic
    @Column(name = "MAIN_BUSIN", nullable = true, length = 4000)
    public String getMainBusin() {
        return mainBusin;
    }

    public void setMainBusin(String mainBusin) {
        this.mainBusin = mainBusin;
    }

    @Basic
    @Column(name = "EMPLOY_NUM", nullable = true, precision = 0)
    public Long getEmployNum() {
        return employNum;
    }

    public void setEmployNum(Long employNum) {
        this.employNum = employNum;
    }

    @Basic
    @Column(name = "BLNUMB", nullable = true, length = 60)
    public String getBlnumb() {
        return blnumb;
    }

    public void setBlnumb(String blnumb) {
        this.blnumb = blnumb;
    }

    @Basic
    @Column(name = "NTRNUM", nullable = true, length = 60)
    public String getNtrnum() {
        return ntrnum;
    }

    public void setNtrnum(String ntrnum) {
        this.ntrnum = ntrnum;
    }

    @Basic
    @Column(name = "LTRNUM", nullable = true, length = 60)
    public String getLtrnum() {
        return ltrnum;
    }

    public void setLtrnum(String ltrnum) {
        this.ltrnum = ltrnum;
    }

    @Basic
    @Column(name = "ORGNUM", nullable = true, length = 30)
    public String getOrgnum() {
        return orgnum;
    }

    public void setOrgnum(String orgnum) {
        this.orgnum = orgnum;
    }

    @Basic
    @Column(name = "REG_DT", nullable = true, length = 50)
    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    @Basic
    @Column(name = "INFO_URL", nullable = true, length = 100)
    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    @Basic
    @Column(name = "INFO_NEWS", nullable = true, length = 100)
    public String getInfoNews() {
        return infoNews;
    }

    public void setInfoNews(String infoNews) {
        this.infoNews = infoNews;
    }

    @Basic
    @Column(name = "ACCOUNTING_FIRM", nullable = true, length = 300)
    public String getAccountingFirm() {
        return accountingFirm;
    }

    public void setAccountingFirm(String accountingFirm) {
        this.accountingFirm = accountingFirm;
    }

    @Basic
    @Column(name = "LEGAL_ADVISOR", nullable = true, length = 300)
    public String getLegalAdvisor() {
        return legalAdvisor;
    }

    public void setLegalAdvisor(String legalAdvisor) {
        this.legalAdvisor = legalAdvisor;
    }

    @Basic
    @Column(name = "COMPANY_ST", nullable = true, precision = 0)
    public Long getCompanySt() {
        return companySt;
    }

    public void setCompanySt(Long companySt) {
        this.companySt = companySt;
    }

    @Basic
    @Column(name = "IS_DEL", nullable = true, precision = 0)
    public Long getIsDel() {
        return isDel;
    }

    public void setIsDel(Long isDel) {
        this.isDel = isDel;
    }

    @Basic
    @Column(name = "SRC_UPDT_DT", nullable = true)
    public Date getSrcUpdtDt() {
        return srcUpdtDt;
    }

    public void setSrcUpdtDt(Date srcUpdtDt) {
        this.srcUpdtDt = srcUpdtDt;
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

        CompyBasicinfo that = (CompyBasicinfo) o;

        if (companyId != that.companyId) return false;
        if (companyCd != null ? !companyCd.equals(that.companyCd) : that.companyCd != null) return false;
        if (companyNm != null ? !companyNm.equals(that.companyNm) : that.companyNm != null) return false;
        if (companySnm != null ? !companySnm.equals(that.companySnm) : that.companySnm != null) return false;
        if (clensCompanyNm != null ? !clensCompanyNm.equals(that.clensCompanyNm) : that.clensCompanyNm != null)
            return false;
        if (fenNm != null ? !fenNm.equals(that.fenNm) : that.fenNm != null) return false;
        if (legRepresent != null ? !legRepresent.equals(that.legRepresent) : that.legRepresent != null) return false;
        if (chairman != null ? !chairman.equals(that.chairman) : that.chairman != null) return false;
        if (gmanager != null ? !gmanager.equals(that.gmanager) : that.gmanager != null) return false;
        if (bsecretary != null ? !bsecretary.equals(that.bsecretary) : that.bsecretary != null) return false;
        if (orgFormId != null ? !orgFormId.equals(that.orgFormId) : that.orgFormId != null) return false;
        if (foundDt != null ? !foundDt.equals(that.foundDt) : that.foundDt != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (regCapital != null ? !regCapital.equals(that.regCapital) : that.regCapital != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (regAddr != null ? !regAddr.equals(that.regAddr) : that.regAddr != null) return false;
        if (officeAddr != null ? !officeAddr.equals(that.officeAddr) : that.officeAddr != null) return false;
        if (officePostCd != null ? !officePostCd.equals(that.officePostCd) : that.officePostCd != null) return false;
        if (companyPh != null ? !companyPh.equals(that.companyPh) : that.companyPh != null) return false;
        if (companyFax != null ? !companyFax.equals(that.companyFax) : that.companyFax != null) return false;
        if (companyEm != null ? !companyEm.equals(that.companyEm) : that.companyEm != null) return false;
        if (companyWeb != null ? !companyWeb.equals(that.companyWeb) : that.companyWeb != null) return false;
        if (businScope != null ? !businScope.equals(that.businScope) : that.businScope != null) return false;
        if (mainBusin != null ? !mainBusin.equals(that.mainBusin) : that.mainBusin != null) return false;
        if (employNum != null ? !employNum.equals(that.employNum) : that.employNum != null) return false;
        if (blnumb != null ? !blnumb.equals(that.blnumb) : that.blnumb != null) return false;
        if (ntrnum != null ? !ntrnum.equals(that.ntrnum) : that.ntrnum != null) return false;
        if (ltrnum != null ? !ltrnum.equals(that.ltrnum) : that.ltrnum != null) return false;
        if (orgnum != null ? !orgnum.equals(that.orgnum) : that.orgnum != null) return false;
        if (regDt != null ? !regDt.equals(that.regDt) : that.regDt != null) return false;
        if (infoUrl != null ? !infoUrl.equals(that.infoUrl) : that.infoUrl != null) return false;
        if (infoNews != null ? !infoNews.equals(that.infoNews) : that.infoNews != null) return false;
        if (accountingFirm != null ? !accountingFirm.equals(that.accountingFirm) : that.accountingFirm != null)
            return false;
        if (legalAdvisor != null ? !legalAdvisor.equals(that.legalAdvisor) : that.legalAdvisor != null) return false;
        if (companySt != null ? !companySt.equals(that.companySt) : that.companySt != null) return false;
        if (isDel != null ? !isDel.equals(that.isDel) : that.isDel != null) return false;
        if (srcUpdtDt != null ? !srcUpdtDt.equals(that.srcUpdtDt) : that.srcUpdtDt != null) return false;
        if (srcCompanyCd != null ? !srcCompanyCd.equals(that.srcCompanyCd) : that.srcCompanyCd != null) return false;
        if (srcCd != null ? !srcCd.equals(that.srcCd) : that.srcCd != null) return false;
        return updtDt != null ? updtDt.equals(that.updtDt) : that.updtDt == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (companyCd != null ? companyCd.hashCode() : 0);
        result = 31 * result + (companyNm != null ? companyNm.hashCode() : 0);
        result = 31 * result + (companySnm != null ? companySnm.hashCode() : 0);
        result = 31 * result + (clensCompanyNm != null ? clensCompanyNm.hashCode() : 0);
        result = 31 * result + (fenNm != null ? fenNm.hashCode() : 0);
        result = 31 * result + (legRepresent != null ? legRepresent.hashCode() : 0);
        result = 31 * result + (chairman != null ? chairman.hashCode() : 0);
        result = 31 * result + (gmanager != null ? gmanager.hashCode() : 0);
        result = 31 * result + (bsecretary != null ? bsecretary.hashCode() : 0);
        result = 31 * result + (orgFormId != null ? orgFormId.hashCode() : 0);
        result = 31 * result + (foundDt != null ? foundDt.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (regCapital != null ? regCapital.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (regAddr != null ? regAddr.hashCode() : 0);
        result = 31 * result + (officeAddr != null ? officeAddr.hashCode() : 0);
        result = 31 * result + (officePostCd != null ? officePostCd.hashCode() : 0);
        result = 31 * result + (companyPh != null ? companyPh.hashCode() : 0);
        result = 31 * result + (companyFax != null ? companyFax.hashCode() : 0);
        result = 31 * result + (companyEm != null ? companyEm.hashCode() : 0);
        result = 31 * result + (companyWeb != null ? companyWeb.hashCode() : 0);
        result = 31 * result + (businScope != null ? businScope.hashCode() : 0);
        result = 31 * result + (mainBusin != null ? mainBusin.hashCode() : 0);
        result = 31 * result + (employNum != null ? employNum.hashCode() : 0);
        result = 31 * result + (blnumb != null ? blnumb.hashCode() : 0);
        result = 31 * result + (ntrnum != null ? ntrnum.hashCode() : 0);
        result = 31 * result + (ltrnum != null ? ltrnum.hashCode() : 0);
        result = 31 * result + (orgnum != null ? orgnum.hashCode() : 0);
        result = 31 * result + (regDt != null ? regDt.hashCode() : 0);
        result = 31 * result + (infoUrl != null ? infoUrl.hashCode() : 0);
        result = 31 * result + (infoNews != null ? infoNews.hashCode() : 0);
        result = 31 * result + (accountingFirm != null ? accountingFirm.hashCode() : 0);
        result = 31 * result + (legalAdvisor != null ? legalAdvisor.hashCode() : 0);
        result = 31 * result + (companySt != null ? companySt.hashCode() : 0);
        result = 31 * result + (isDel != null ? isDel.hashCode() : 0);
        result = 31 * result + (srcUpdtDt != null ? srcUpdtDt.hashCode() : 0);
        result = 31 * result + (srcCompanyCd != null ? srcCompanyCd.hashCode() : 0);
        result = 31 * result + (srcCd != null ? srcCd.hashCode() : 0);
        result = 31 * result + (updtDt != null ? updtDt.hashCode() : 0);
        return result;
    }
}
