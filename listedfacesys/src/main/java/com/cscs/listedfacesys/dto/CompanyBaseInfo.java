package com.cscs.listedfacesys.dto;


import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.sql.Timestamp;

/**
 * Created by wkm on 20180507.
 */
public class CompanyBaseInfo extends BaseOutData {

    private String companyId;  //公司Id
    private String companyCd;  //公司代码
    private String companyNm;  //公司名称
    private String companySnm; //公司简称
    private String regAddr;  // 注册地址
    private String officeAddr;  //办公地址
    private Timestamp updtDt;  //更新时间
    private String securityCds;  //证券代码
    private String positionX;     //坐标点X轴
    private String positionY;   //坐标点Y轴


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getCompanySnm() {
        return companySnm;
    }

    public void setCompanySnm(String companySnm) {
        this.companySnm = companySnm;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public Timestamp getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(Timestamp updtDt) {
        this.updtDt = updtDt;
    }

    public String getSecurityCds() {
        return securityCds;
    }

    public void setSecurityCds(String securityCds) {
        this.securityCds = securityCds;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }
}

