package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/2/24.
 */
public class SecurityInfoData {

    private String securityCd;//证券代码
    private String securityNm;//证券全称
    private String securitySnm;//证券简称

    public String getSecurityCd() {
        return securityCd;
    }

    public void setSecurityCd(String securityCd) {
        this.securityCd = securityCd;
    }

    public String getSecurityNm() {
        return securityNm;
    }

    public void setSecurityNm(String securityNm) {
        this.securityNm = securityNm;
    }

    public String getSecuritySnm() {
        return securitySnm;
    }

    public void setSecuritySnm(String securitySnm) {
        this.securitySnm = securitySnm;
    }
}
