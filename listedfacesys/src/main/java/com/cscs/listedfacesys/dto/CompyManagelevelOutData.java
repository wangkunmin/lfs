package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Created by hj on 2018/2/26.
 */
public class CompyManagelevelOutData extends BaseOutData {

    private String pstnNm;
    private String personNm;
    private String age;

    public String getPstnNm() {
        return pstnNm;
    }

    public void setPstnNm(String pstnNm) {
        this.pstnNm = pstnNm;
    }

    public String getPersonNm() {
        return personNm;
    }

    public void setPersonNm(String personNm) {
        this.personNm = personNm;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountryCd() {
        return countryCd;
    }

    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    private String sex;
    private String countryCd;
    private String highestDegree;
    private String resume;

}
