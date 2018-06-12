package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.entity.Security;
import com.cscs.listedfacesys.services.CompanyInfoService;
import com.cscs.listedfacesys.services.SearchWarningService;
import com.cscs.util.Base64;
import com.cscs.util.GetCompany;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Create by hj on 2018/2/26
 * 公司基本信息
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/companyBase")
public class CompanyBaseController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    CompanyInfoService companyInfoServices;
    @Autowired
    SearchWarningService searchWarningService;

    //根据企业标识符查询唯一精确查询企业基本信息
    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public BaseOutData getCompanyBasicInfo(@PathVariable Long id) throws JsonProcessingException {
        BaseOutData out = new BaseOutData();
        CompanyOutData dtoCompanyInfo = new CompanyOutData();
        List<Object> companyBasicInfo = companyInfoServices.getCompanyBasicInfo(id);
        try{
            if (companyBasicInfo.size() > 0) {

                Object[] arrayCompanyBasicInfo = (Object[]) companyBasicInfo.get(0);
                dtoCompanyInfo.setCompanyNm(
                        arrayCompanyBasicInfo[0] != null ? arrayCompanyBasicInfo[0].toString() : "");
                dtoCompanyInfo.setFoundDt(
                        arrayCompanyBasicInfo[1] != null ? arrayCompanyBasicInfo[1].toString().substring(0, 10)
                                : "");
                dtoCompanyInfo.setRegCapital(
                        arrayCompanyBasicInfo[2] != null ? arrayCompanyBasicInfo[2].toString() : "");
                dtoCompanyInfo.setOrgFormNm(
                        arrayCompanyBasicInfo[3] != null ? arrayCompanyBasicInfo[3].toString() : "");
                dtoCompanyInfo.setLegRepresent(
                        arrayCompanyBasicInfo[4] != null ? arrayCompanyBasicInfo[4].toString() : "");
                dtoCompanyInfo.setEmployNum(
                        arrayCompanyBasicInfo[5] != null ? arrayCompanyBasicInfo[5].toString() : "");
                dtoCompanyInfo
                        .setRegAddr(arrayCompanyBasicInfo[6] != null ? arrayCompanyBasicInfo[6].toString() : "");
                dtoCompanyInfo.setOfficeAddr(
                        arrayCompanyBasicInfo[7] != null ? arrayCompanyBasicInfo[7].toString() : "");
                dtoCompanyInfo.setCompanyPh(
                        arrayCompanyBasicInfo[8] != null ? arrayCompanyBasicInfo[8].toString() : "");
                dtoCompanyInfo.setCompanyWeb(
                        arrayCompanyBasicInfo[9] != null ? arrayCompanyBasicInfo[9].toString() : "");
                dtoCompanyInfo.setBusinScope(
                        arrayCompanyBasicInfo[10] != null ? arrayCompanyBasicInfo[10].toString() : "");
                dtoCompanyInfo.setMainBusin(
                        arrayCompanyBasicInfo[11] != null ? arrayCompanyBasicInfo[11].toString() : "");
                dtoCompanyInfo.setCompanySnm(
                        arrayCompanyBasicInfo[12] != null ? arrayCompanyBasicInfo[12].toString() : "");
                dtoCompanyInfo.setSecurityCds(
                        arrayCompanyBasicInfo[13] != null ? arrayCompanyBasicInfo[13].toString() : "");
                //实际控制人
                dtoCompanyInfo.setSharehdName(companyInfoServices.getAffilparty(id));
                dtoCompanyInfo.setIndustry(searchIndustry(id));


                out.setCode("0");
                Map<String,Object> outMap = new HashMap<String,Object>();
                outMap.put("content",dtoCompanyInfo);
                out.setData(outMap);

            }else{
                out.setCode("1");
                out.setMessage("查询公司基础信息，获取数据为空！");
                logger.info("查询公司基础信息，获取数据为空！");
            }
            //是否为城投平台
//        boolean isPlatform = companyInfoServices.getPlatformCompanyIds().contains(id);
//        dtoCompanyInfo.setPlatform(isPlatform);
        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("查询公司基础信息，获取数据异常！异常信息："+e.getMessage());
            logger.error("查询公司基础信息，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }



    //根据企业标识符查询企业董事高管人员信息
    @RequestMapping(value = "/managelevel/{id}", method = RequestMethod.GET)
    public BaseOutData getCompyManagelevel(@PathVariable Long id)
            throws JsonProcessingException {
        BaseOutData out = new BaseOutData();
        List<CompyManagelevelOutData> outData = new ArrayList<CompyManagelevelOutData>();
        List<Object> compyManagelevel = companyInfoServices.findCompyManagelevel(id);
        try {
            if (compyManagelevel != null && compyManagelevel.size() > 0) {
                for (int i = 0; i < compyManagelevel.size(); i++) {

                    CompyManagelevelOutData compyManagelevelOutData = new CompyManagelevelOutData();
                    Object[] arrayCompyManagelevel = (Object[]) compyManagelevel.get(i);

                    compyManagelevelOutData
                            .setPstnNm(arrayCompyManagelevel[0] != null ? arrayCompyManagelevel[0].toString() : "");
                    compyManagelevelOutData
                            .setPersonNm(arrayCompyManagelevel[1] != null ? arrayCompyManagelevel[1].toString() : "");
                    compyManagelevelOutData
                            .setAge(arrayCompyManagelevel[2] != null ? arrayCompyManagelevel[2].toString() : "");
                    compyManagelevelOutData
                            .setSex(arrayCompyManagelevel[3] != null ? arrayCompyManagelevel[3].toString() : "");
                    compyManagelevelOutData.setCountryCd(
                            arrayCompyManagelevel[4] != null ? arrayCompyManagelevel[4].toString() : "");
                    compyManagelevelOutData.setHighestDegree(
                            arrayCompyManagelevel[5] != null ? arrayCompyManagelevel[5].toString() : "");
                    compyManagelevelOutData
                            .setResume(arrayCompyManagelevel[6] != null ? arrayCompyManagelevel[6].toString() : "");

                    outData.add(compyManagelevelOutData);
                }

                out.setCode("0");
                Map<String, Object> outMap = new HashMap<String, Object>();
                outMap.put("content", outData);
                out.setData(outMap);
            } else {
                out.setCode("1");
                out.setMessage("查询企业董事高管人员信息，获取数据为空！");
                logger.info("查询企业董事高管人员信息，获取数据为空！");
            }
        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("查询企业董事高管人员信息，获取数据异常！异常信息："+e.getMessage());
            logger.error("查询企业董事高管人员信息，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }
        return out;
    }

    //搜索框，根据关键字，查询公司基本信息
    @RequestMapping(value = "/companySearch/{keyword}", method = RequestMethod.GET)
    public BaseOutData getCompanySearchinfo(@PathVariable String keyword) throws Exception {
        BaseOutData out = new BaseOutData();
        SearchOutObj outObj = new SearchOutObj();
        List<SearchCompletion> infoCompyBasic = null;
        /*try {
            infoCompyBasic = searchWarningService.findCompanySearchinfosFromSolr(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            infoCompyBasic = searchWarningService.findCompanySearchinfos(keyword);
        }*/
        infoCompyBasic = searchWarningService.findCompanySearchinfosForTitle(keyword);
        if (infoCompyBasic.size() > 0) {
            outObj.seList(infoCompyBasic);
            out.setCode("0");
            Map<String, Object> outMap = new HashMap<String, Object>();
            outMap.put("searchOutObj", outObj);
            out.setData(outMap);
        }else {
            out.setCode("1");
            out.setMessage("获取数据为空！");
            logger.info("获取数据为空！");
        }

        return out;
    }

    //根据批量导入的关键字字符串，查询公司基本信息
    @RequestMapping(value = "/batchCompanySearch/{keyword}", method = RequestMethod.GET)
    public BaseOutData batchCompanySearch(@PathVariable String keyword) throws Exception {
        RelationShipOutData outData = new RelationShipOutData();
        BaseOutData out = new BaseOutData();
        if(keyword ==null || "".equals(keyword)){
            out.setCode("1");
            out.setMessage("获取数据为空！");
            logger.info("获取数据为空！");
            return out;
        }


        String[] keywords = keyword.split(",");
        List<String> keywordList = new ArrayList<String>();

        for (int i=0;i<keywords.length;i++){
            if(isDupliated(keywordList, keywords[i])){
                keywordList.add( keywords[i]);
            }

        }
        List<RelationShipInfoData> resList = new ArrayList<RelationShipInfoData>();
        for(String itemkey : keywordList){
            List<RelationShipInfoData> relationShipList = new LinkedList<>();
            List<Object> compyList = searchWarningService.findRelationShipByCompy(itemkey);
            for (int i = 0; i < compyList.size(); i++) {
                RelationShipInfoData infoData = new RelationShipInfoData();
                Object[] compyInfo = (Object[]) compyList.get(i);
                infoData.setType("企业名称");
                infoData.setCompanyId(compyInfo[0].toString());
                infoData.setCompanyNm(compyInfo[1].toString());
                relationShipList.add(infoData);
            }

            List<String> nameList = searchWarningService.findRelationShipByName(itemkey);
            for (String name : nameList) {
                RelationShipInfoData infoData = new RelationShipInfoData();
                infoData.setType("人名");
                infoData.setCompanyNm(name);
                relationShipList.add(infoData);
            }

            //中数公司查找
            if (relationShipList.size() == 0) {
                String[] companyList = GetCompany.getCompanyList(itemkey);
                if(companyList !=null && companyList.length>0){
                    for (int i = 0; i < 10 && i < companyList.length; i++) {
                        RelationShipInfoData info = new RelationShipInfoData();
                        info.setCompanyId(Base64.encode(companyList[i]));
                        info.setCompanyNm(companyList[i]);
                        info.setType("企业名称");
                        relationShipList.add(info);
                    }
                }

            }


            for (RelationShipInfoData relationShip :relationShipList) {
                if (relationShip.getCompanyNm().equals(itemkey)){
                    resList.add(relationShip);
                }
            }

        }

        outData.setList(resList);


        if (outData != null) {
            out.setCode("0");
            Map<String, Object> outMap = new HashMap<String, Object>();
            outMap.put("content", outData);
            out.setData(outMap);
        } else {
            out.setCode("1");
            out.setMessage("获取数据为空！");
            logger.info("获取数据为空！");
        }
        return out;
    }


    /**
     * 字符串相似比较
     * @param s
     * @return
     */
    private static boolean isDupliated(List<String> title, String s){
        for(String item : title){
            if (item.equals(s)) {
                return false;
            }
        }
        return true;
    }

    //搜索框，查找关系
    @RequestMapping(value = "/relationship/{keyword}", method = RequestMethod.GET)
    public BaseOutData getRelationship(@PathVariable String keyword) throws Exception {
        BaseOutData out = new BaseOutData();
        RelationShipOutData outData = new RelationShipOutData();
        List<RelationShipInfoData> relationShipList = new LinkedList<>();

        List<Object> compyList = searchWarningService.findRelationShipByCompy(keyword);
        for (int i = 0; i < compyList.size(); i++) {
            RelationShipInfoData infoData = new RelationShipInfoData();
            Object[] compyInfo = (Object[]) compyList.get(i);
            infoData.setType("企业名称");
            infoData.setCompanyId(compyInfo[0].toString());
            infoData.setCompanyNm(compyInfo[1].toString());
            relationShipList.add(infoData);
        }

        List<String> nameList = searchWarningService.findRelationShipByName(keyword);
        for (String name : nameList) {
            RelationShipInfoData infoData = new RelationShipInfoData();
            infoData.setType("人名");
            infoData.setCompanyNm(name);
            relationShipList.add(infoData);
        }

        //中数公司查找
        if (relationShipList.size() == 0) {
            String[] companyList = GetCompany.getCompanyList(keyword);
            if(companyList !=null){
                for (int i = 0; i < 10 && i < companyList.length; i++) {
                    RelationShipInfoData info = new RelationShipInfoData();
                    info.setCompanyId(Base64.encode(companyList[i]));
                    info.setCompanyNm(companyList[i]);
                    info.setType("企业名称");
                    relationShipList.add(info);
                }
            }else{
                out.setCode("1");
                out.setMessage("公司列表查询,获取数据为空！");
                logger.info("公司列表查询,获取数据为空！");
            }

        }
        outData.setList(relationShipList);
        if (outData != null) {
            out.setCode("0");
            Map<String, Object> outMap = new HashMap<String, Object>();
            outMap.put("content", outData);
            out.setData(outMap);
        } else {
            out.setCode("1");
            out.setMessage("获取数据为空！");
            logger.info("获取数据为空！");
        }
        return out;
    }

    // 获取公司所匹配的行业等级
    private String searchIndustry(Long id) {
        //List<Object> companyIndustry = companyInfoServices.findIndustryFlg(id);
        String arrayInd = "";
            // 申万
            List<Object> swIndustry = companyInfoServices.findSwIndustry(id, "1011");
            arrayInd = xsbSwIndustry(swIndustry, 2);
        return arrayInd;
    }

    private String xsbSwIndustry(List<Object> listIndustry, int ind) {
        String arrayInd = "";
        int size = 0;
        if (listIndustry.size() <= ind) {
            size = listIndustry.size();
            if (size > 0) {
                arrayInd = zilistIndustry(listIndustry, size);
            }
        } else {
            size = ind;
            arrayInd = zilistIndustry(listIndustry, size);
        }
        return arrayInd;
    }

    //获取行业等级所匹配的行业
    private String zilistIndustry(List<Object> listIndustry, int size) {
        String arrayInd = "";

        for (int i = 0; i < size; i++) {
            Object[] arrayQual = (Object[]) listIndustry.get(i);
            if (arrayInd == "") {
                if (arrayQual[1] != null) {
                    arrayInd = arrayQual[1].toString();
                }
            } else {
                if (arrayQual[1] != null) {
                    arrayInd = arrayInd + "-" + arrayQual[1].toString();
                }
            }
        }

        return arrayInd;
    }
}
