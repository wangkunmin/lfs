package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Created by hj on 2018/2/24.
 */
public class CompyCreditOutData extends BaseOutData {

    private String noticeDt;
    private String endDt;
    private String creditAmt;
    private String creditUsed;
    private String creditUnused;

    public String getCreditobjname() {
        return creditobjname;
    }

    public void setCreditobjname(String creditobjname) {
        this.creditobjname = creditobjname;
    }

    private String creditobjname;

    public String getNoticeDt() {
        return noticeDt;
    }

    public void setNoticeDt(String noticeDt) {
        this.noticeDt = noticeDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getCreditUsed() {
        return creditUsed;
    }

    public void setCreditUsed(String creditUsed) {
        this.creditUsed = creditUsed;
    }

    public String getCreditUnused() {
        return creditUnused;
    }

    public void setCreditUnused(String creditUnused) {
        this.creditUnused = creditUnused;
    }



}

