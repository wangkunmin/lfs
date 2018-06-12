package com.cscs.listedfacesys.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by sh on 2016/10/17.
 */
@Entity
public class Security {
    private long secinnerId;
    private String securityCd;
    private String securityNm;
    private String securitySnm;
    private String spell;
    private long securityTypeId;
    private long trdMarketId;
    private Long companyId;
    private long listSt;
    private Date listDt;
    private Date endDt;
    private long useSt;
    private String currency;
    private Long isdel;
    private String srcCompanyCd;
    private String srcSecinnerCd;
    private String srcid;
    private String srcCd;
    private Timestamp updtDt;

    @Id
    @Column(name = "SECINNER_ID", nullable = false, precision = 0)
    public long getSecinnerId() {
        return secinnerId;
    }

    public void setSecinnerId(long secinnerId) {
        this.secinnerId = secinnerId;
    }

    @Basic
    @Column(name = "SECURITY_CD", nullable = false, length = 30)
    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    @Basic
    @Column(name = "SECURITY_NM", nullable = false, length = 300)
    public String getSecurityNm() {
        return securityNm;
    }

    public void setSecurityNm(String securityNm) {
        this.securityNm = securityNm;
    }

    @Basic
    @Column(name = "SECURITY_SNM", nullable = true, length = 200)
    public String getSecuritySnm() {
        return securitySnm;
    }

    public void setSecuritySnm(String securitySnm) {
        this.securitySnm = securitySnm;
    }

    @Basic
    @Column(name = "SPELL", nullable = true, length = 80)
    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    @Basic
    @Column(name = "SECURITY_TYPE_ID", nullable = false, precision = 0)
    public long getSecurityTypeId() {
        return securityTypeId;
    }

    public void setSecurityTypeId(long securityTypeId) {
        this.securityTypeId = securityTypeId;
    }

    @Basic
    @Column(name = "TRD_MARKET_ID", nullable = false, precision = 0)
    public long getTrdMarketId() {
        return trdMarketId;
    }

    public void setTrdMarketId(long trdMarketId) {
        this.trdMarketId = trdMarketId;
    }

    @Basic
    @Column(name = "COMPANY_ID", nullable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "LIST_ST", nullable = false, precision = 0)
    public long getListSt() {
        return listSt;
    }

    public void setListSt(long listSt) {
        this.listSt = listSt;
    }

    @Basic
    @Column(name = "LIST_DT", nullable = true)
    public Date getListDt() {
        return listDt;
    }

    public void setListDt(Date listDt) {
        this.listDt = listDt;
    }

    @Basic
    @Column(name = "END_DT", nullable = true)
    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    @Basic
    @Column(name = "USE_ST", nullable = false, precision = 0)
    public long getUseSt() {
        return useSt;
    }

    public void setUseSt(long useSt) {
        this.useSt = useSt;
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
    @Column(name = "ISDEL", nullable = true, precision = 0)
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
    @Column(name = "SRC_SECINNER_CD", nullable = false, length = 30)
    public String getSrcSecinnerCd() {
        return srcSecinnerCd;
    }

    public void setSrcSecinnerCd(String srcSecinnerCd) {
        this.srcSecinnerCd = srcSecinnerCd;
    }

    @Basic
    @Column(name = "SRCID", nullable = false, length = 100)
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

        Security security = (Security) o;

        if (secinnerId != security.secinnerId) return false;
        if (securityTypeId != security.securityTypeId) return false;
        if (trdMarketId != security.trdMarketId) return false;
        if (listSt != security.listSt) return false;
        if (useSt != security.useSt) return false;
        if (securityCd != null ? !securityCd.equals(security.securityCd) : security.securityCd != null) return false;
        if (securityNm != null ? !securityNm.equals(security.securityNm) : security.securityNm != null) return false;
        if (securitySnm != null ? !securitySnm.equals(security.securitySnm) : security.securitySnm != null)
            return false;
        if (spell != null ? !spell.equals(security.spell) : security.spell != null) return false;
        if (companyId != null ? !companyId.equals(security.companyId) : security.companyId != null) return false;
        if (listDt != null ? !listDt.equals(security.listDt) : security.listDt != null) return false;
        if (endDt != null ? !endDt.equals(security.endDt) : security.endDt != null) return false;
        if (currency != null ? !currency.equals(security.currency) : security.currency != null) return false;
        if (isdel != null ? !isdel.equals(security.isdel) : security.isdel != null) return false;
        if (srcCompanyCd != null ? !srcCompanyCd.equals(security.srcCompanyCd) : security.srcCompanyCd != null)
            return false;
        if (srcSecinnerCd != null ? !srcSecinnerCd.equals(security.srcSecinnerCd) : security.srcSecinnerCd != null)
            return false;
        if (srcid != null ? !srcid.equals(security.srcid) : security.srcid != null) return false;
        if (srcCd != null ? !srcCd.equals(security.srcCd) : security.srcCd != null) return false;
        return updtDt != null ? updtDt.equals(security.updtDt) : security.updtDt == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (secinnerId ^ (secinnerId >>> 32));
        result = 31 * result + (securityCd != null ? securityCd.hashCode() : 0);
        result = 31 * result + (securityNm != null ? securityNm.hashCode() : 0);
        result = 31 * result + (securitySnm != null ? securitySnm.hashCode() : 0);
        result = 31 * result + (spell != null ? spell.hashCode() : 0);
        result = 31 * result + (int) (securityTypeId ^ (securityTypeId >>> 32));
        result = 31 * result + (int) (trdMarketId ^ (trdMarketId >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (int) (listSt ^ (listSt >>> 32));
        result = 31 * result + (listDt != null ? listDt.hashCode() : 0);
        result = 31 * result + (endDt != null ? endDt.hashCode() : 0);
        result = 31 * result + (int) (useSt ^ (useSt >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (isdel != null ? isdel.hashCode() : 0);
        result = 31 * result + (srcCompanyCd != null ? srcCompanyCd.hashCode() : 0);
        result = 31 * result + (srcSecinnerCd != null ? srcSecinnerCd.hashCode() : 0);
        result = 31 * result + (srcid != null ? srcid.hashCode() : 0);
        result = 31 * result + (srcCd != null ? srcCd.hashCode() : 0);
        result = 31 * result + (updtDt != null ? updtDt.hashCode() : 0);
        return result;
    }
}
