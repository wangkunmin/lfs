package com.cscs.listedfacesys.entity;

import javax.persistence.*;

/**
 * Created by hj on 2018/2/24.
 */
@Entity
@Table(name = "BOND_VIOLATION", schema = "CS_FACEBOOK", catalog = "")
public class BondViolation {
    private long id;
    private String securityCd;
    private String securitySnm;
    private String eventDt;
    private String eventRemark;
    private String issuer;
    private String guarantor;
    private String bondRatingRecent;
    private String publishRatingRecent;
    private String publishRatingIssue;
    private String ratingHistory;
    private Long bondIssuevol;
    private Long couponRate;
    private String orgForm;
    private String frstValueDt;
    private String lastValueDt;
    private String underwriter;
    private String isPublic;
    private String province;
    private String industryWind;
    private String bondType2IdWind;
    private String publicMarket;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SECURITY_CD", nullable = true, length = 20)
    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
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
    @Column(name = "EVENT_DT", nullable = true, length = 20)
    public String getEventDt() {
        return eventDt;
    }

    public void setEventDt(String eventDt) {
        this.eventDt = eventDt;
    }

    @Basic
    @Column(name = "EVENT_REMARK", nullable = true)
    public String getEventRemark() {
        return eventRemark;
    }

    public void setEventRemark(String eventRemark) {
        this.eventRemark = eventRemark;
    }

    @Basic
    @Column(name = "ISSUER", nullable = true, length = 60)
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Basic
    @Column(name = "GUARANTOR", nullable = true, length = 60)
    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    @Basic
    @Column(name = "BOND_RATING_RECENT", nullable = true, length = 30)
    public String getBondRatingRecent() {
        return bondRatingRecent;
    }

    public void setBondRatingRecent(String bondRatingRecent) {
        this.bondRatingRecent = bondRatingRecent;
    }

    @Basic
    @Column(name = "PUBLISH_RATING_RECENT", nullable = true, length = 30)
    public String getPublishRatingRecent() {
        return publishRatingRecent;
    }

    public void setPublishRatingRecent(String publishRatingRecent) {
        this.publishRatingRecent = publishRatingRecent;
    }

    @Basic
    @Column(name = "PUBLISH_RATING_ISSUE", nullable = true, length = 30)
    public String getPublishRatingIssue() {
        return publishRatingIssue;
    }

    public void setPublishRatingIssue(String publishRatingIssue) {
        this.publishRatingIssue = publishRatingIssue;
    }

    @Basic
    @Column(name = "RATING_HISTORY", nullable = true)
    public String getRatingHistory() {
        return ratingHistory;
    }

    public void setRatingHistory(String ratingHistory) {
        this.ratingHistory = ratingHistory;
    }

    @Basic
    @Column(name = "BOND_ISSUEVOL", nullable = true, precision = 4)
    public Long getBondIssuevol() {
        return bondIssuevol;
    }

    public void setBondIssuevol(Long bondIssuevol) {
        this.bondIssuevol = bondIssuevol;
    }

    @Basic
    @Column(name = "COUPON_RATE", nullable = true, precision = 4)
    public Long getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(Long couponRate) {
        this.couponRate = couponRate;
    }

    @Basic
    @Column(name = "ORG_FORM", nullable = true, length = 100)
    public String getOrgForm() {
        return orgForm;
    }

    public void setOrgForm(String orgForm) {
        this.orgForm = orgForm;
    }

    @Basic
    @Column(name = "FRST_VALUE_DT", nullable = true, length = 20)
    public String getFrstValueDt() {
        return frstValueDt;
    }

    public void setFrstValueDt(String frstValueDt) {
        this.frstValueDt = frstValueDt;
    }

    @Basic
    @Column(name = "LAST_VALUE_DT", nullable = true, length = 20)
    public String getLastValueDt() {
        return lastValueDt;
    }

    public void setLastValueDt(String lastValueDt) {
        this.lastValueDt = lastValueDt;
    }

    @Basic
    @Column(name = "UNDERWRITER", nullable = true, length = 60)
    public String getUnderwriter() {
        return underwriter;
    }

    public void setUnderwriter(String underwriter) {
        this.underwriter = underwriter;
    }

    @Basic
    @Column(name = "IS_PUBLIC", nullable = true, length = 20)
    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    @Basic
    @Column(name = "PROVINCE", nullable = true, length = 20)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "INDUSTRY_WIND", nullable = true, length = 200)
    public String getIndustryWind() {
        return industryWind;
    }

    public void setIndustryWind(String industryWind) {
        this.industryWind = industryWind;
    }

    @Basic
    @Column(name = "BOND_TYPE2_ID_WIND", nullable = true, length = 100)
    public String getBondType2IdWind() {
        return bondType2IdWind;
    }

    public void setBondType2IdWind(String bondType2IdWind) {
        this.bondType2IdWind = bondType2IdWind;
    }

    @Basic
    @Column(name = "PUBLIC_MARKET", nullable = true, length = 100)
    public String getPublicMarket() {
        return publicMarket;
    }

    public void setPublicMarket(String publicMarket) {
        this.publicMarket = publicMarket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BondViolation that = (BondViolation) o;

        if (id != that.id) return false;
        if (securityCd != null ? !securityCd.equals(that.securityCd) : that.securityCd != null) return false;
        if (securitySnm != null ? !securitySnm.equals(that.securitySnm) : that.securitySnm != null) return false;
        if (eventDt != null ? !eventDt.equals(that.eventDt) : that.eventDt != null) return false;
        if (eventRemark != null ? !eventRemark.equals(that.eventRemark) : that.eventRemark != null) return false;
        if (issuer != null ? !issuer.equals(that.issuer) : that.issuer != null) return false;
        if (guarantor != null ? !guarantor.equals(that.guarantor) : that.guarantor != null) return false;
        if (bondRatingRecent != null ? !bondRatingRecent.equals(that.bondRatingRecent) : that.bondRatingRecent != null)
            return false;
        if (publishRatingRecent != null ? !publishRatingRecent.equals(that.publishRatingRecent) : that.publishRatingRecent != null)
            return false;
        if (publishRatingIssue != null ? !publishRatingIssue.equals(that.publishRatingIssue) : that.publishRatingIssue != null)
            return false;
        if (ratingHistory != null ? !ratingHistory.equals(that.ratingHistory) : that.ratingHistory != null)
            return false;
        if (bondIssuevol != null ? !bondIssuevol.equals(that.bondIssuevol) : that.bondIssuevol != null) return false;
        if (couponRate != null ? !couponRate.equals(that.couponRate) : that.couponRate != null) return false;
        if (orgForm != null ? !orgForm.equals(that.orgForm) : that.orgForm != null) return false;
        if (frstValueDt != null ? !frstValueDt.equals(that.frstValueDt) : that.frstValueDt != null) return false;
        if (lastValueDt != null ? !lastValueDt.equals(that.lastValueDt) : that.lastValueDt != null) return false;
        if (underwriter != null ? !underwriter.equals(that.underwriter) : that.underwriter != null) return false;
        if (isPublic != null ? !isPublic.equals(that.isPublic) : that.isPublic != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (industryWind != null ? !industryWind.equals(that.industryWind) : that.industryWind != null) return false;
        if (bondType2IdWind != null ? !bondType2IdWind.equals(that.bondType2IdWind) : that.bondType2IdWind != null)
            return false;
        return publicMarket != null ? publicMarket.equals(that.publicMarket) : that.publicMarket == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (securityCd != null ? securityCd.hashCode() : 0);
        result = 31 * result + (securitySnm != null ? securitySnm.hashCode() : 0);
        result = 31 * result + (eventDt != null ? eventDt.hashCode() : 0);
        result = 31 * result + (eventRemark != null ? eventRemark.hashCode() : 0);
        result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
        result = 31 * result + (guarantor != null ? guarantor.hashCode() : 0);
        result = 31 * result + (bondRatingRecent != null ? bondRatingRecent.hashCode() : 0);
        result = 31 * result + (publishRatingRecent != null ? publishRatingRecent.hashCode() : 0);
        result = 31 * result + (publishRatingIssue != null ? publishRatingIssue.hashCode() : 0);
        result = 31 * result + (ratingHistory != null ? ratingHistory.hashCode() : 0);
        result = 31 * result + (bondIssuevol != null ? bondIssuevol.hashCode() : 0);
        result = 31 * result + (couponRate != null ? couponRate.hashCode() : 0);
        result = 31 * result + (orgForm != null ? orgForm.hashCode() : 0);
        result = 31 * result + (frstValueDt != null ? frstValueDt.hashCode() : 0);
        result = 31 * result + (lastValueDt != null ? lastValueDt.hashCode() : 0);
        result = 31 * result + (underwriter != null ? underwriter.hashCode() : 0);
        result = 31 * result + (isPublic != null ? isPublic.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (industryWind != null ? industryWind.hashCode() : 0);
        result = 31 * result + (bondType2IdWind != null ? bondType2IdWind.hashCode() : 0);
        result = 31 * result + (publicMarket != null ? publicMarket.hashCode() : 0);
        return result;
    }
}
