package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Created by hj on 2018/2/24.
 */
public class CompyGuaranteeOutData extends BaseOutData {

    private String noticeDt;
    private String guarEndDt;
    private String securityNm;
    private String guarCompanyId;
    private String guarCompanyNm;
    private String guarRelationNm;
    private String buarCompanyNm;
    private String buarCompanyId;
    private String buarRelationNm;
    private String guarAmt;

    public String getNoticeDt() {
        return noticeDt;
    }

    public void setNoticeDt(String noticeDt) {
        this.noticeDt = noticeDt;
    }

    public String getGuarEndDt() {
        return guarEndDt;
    }

    public void setGuarEndDt(String guarEndDt) {
        this.guarEndDt = guarEndDt;
    }

    public String getSecurityNm() {
        return securityNm;
    }

    public void setSecurityNm(String securityNm) {
        this.securityNm = securityNm;
    }

    public String getGuarCompanyNm() {
        return guarCompanyNm;
    }

    public void setGuarCompanyNm(String guarCompanyNm) {
        this.guarCompanyNm = guarCompanyNm;
    }

    public String getGuarRelationNm() {
        return guarRelationNm;
    }

    public void setGuarRelationNm(String guarRelationNm) {
        this.guarRelationNm = guarRelationNm;
    }

    public String getBuarCompanyNm() {
        return buarCompanyNm;
    }

    public void setBuarCompanyNm(String buarCompanyNm) {
        this.buarCompanyNm = buarCompanyNm;
    }

    public String getBuarRelationNm() {
        return buarRelationNm;
    }

    public void setBuarRelationNm(String buarRelationNm) {
        this.buarRelationNm = buarRelationNm;
    }

    public String getGuarAmt() {
        return guarAmt;
    }

    public void setGuarAmt(String guarAmt) {
        this.guarAmt = guarAmt;
    }

    public String getGuarCompanyId() {
        return guarCompanyId;
    }

    public void setGuarCompanyId(String guarCompanyId) {
        this.guarCompanyId = guarCompanyId;
    }

    public String getBuarCompanyId() {
        return buarCompanyId;
    }

    public void setBuarCompanyId(String buarCompanyId) {
        this.buarCompanyId = buarCompanyId;
    }
}
