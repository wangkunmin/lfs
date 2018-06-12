package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.CompanyInfoService;
import com.cscs.listedfacesys.services.RiskRateManageService;
import com.cscs.listedfacesys.services.UserAttentionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by hj on 2018/4/27
 * 风险评级管理
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/riskRateManage")
public class RiskRateManageController {

    protected final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    RiskRateManageService riskRateManageService;

    //查询深圳所有上市公司风险评级信息
    @RequestMapping(value = "/findAllRiskRateInfo/{page}/{pageSize}/{companyNm}", method = RequestMethod.GET)
    public BaseOutData findAllCompyRiskRateInfo(@PathVariable int page,@PathVariable int pageSize,@PathVariable String companyNm) {
        BaseOutData outData = new BaseOutData();
        List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try{
           int count = riskRateManageService.findAllCompyRiskRateInfoCount(companyNm);
           List<Object> resultList = riskRateManageService.findAllCompyRiskRateInfo(null,companyNm,page,pageSize);
           if(resultList !=null && resultList.size()>0){
               for (Object obj: resultList){
                   Object[] item = (Object[])obj;
                   Map<String,Object> map = new HashMap<String,Object>();
                   map.put("companyId",item[0] !=null ? item[0].toString() : "");
                   map.put("companyNm",item[1] !=null ? item[1].toString() : "");
                   map.put("companySnm",item[2] !=null ? item[2].toString() : "");
                   map.put("securityCds",item[3] !=null ? item[3].toString() : "");
                   map.put("pkCompanyId",item[4] !=null ? item[4].toString() : "");
                   map.put("rating",item[5] !=null ? Integer.parseInt(item[5].toString()) : 0);
                   map.put("riskContent",item[6] !=null ? item[6].toString() : "");
                   resList.add(map);
               }

               outData.setCode("0");
               outData.setCount(count);
               resMap.put("content",resList);
               outData.setData(resMap);
           } else{
               outData.setCode("1");
               outData.setMessage("查询深圳所有上市公司风险评级信息为空。");
               logger.info("查询深圳所有上市公司风险评级信息为空。");
           }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //查询单个公司风险评级信息
    @RequestMapping(value = "/findRiskRateInfo/{companyId}", method = RequestMethod.GET)
    public BaseOutData findCompyRiskRateInfo(@PathVariable String companyId) {
        BaseOutData outData = new BaseOutData();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try{
            List<Object> resultList = riskRateManageService.findAllCompyRiskRateInfo(companyId,null,0,0);
            if(resultList !=null && resultList.size()>0){
                Object obj = resultList.get(0);
                Object[] item = (Object[])obj;
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("companyId",item[0] !=null ? item[0].toString() : "");
                map.put("companyNm",item[1] !=null ? item[1].toString() : "");
                map.put("companySnm",item[2] !=null ? item[2].toString() : "");
                map.put("securityCds",item[3] !=null ? item[3].toString() : "");
                map.put("pkCompanyId",item[4] !=null ? item[4].toString() : "");
                map.put("rating",item[5] !=null ? Integer.parseInt(item[5].toString()) : 0);
                map.put("riskContent",item[6] !=null ? item[6].toString() : "");

                outData.setCode("0");
                resMap.put("content",map);
                outData.setData(resMap);
            } else{
                outData.setCode("1");
                outData.setMessage("查询单个公司风险评级信息为空。");
                logger.info("查询单个公司风险评级信息为空。");
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //编辑风险评级信息
    @RequestMapping(value = "/editRiskRateInfo", method = RequestMethod.POST)
    public BaseOutData editRiskRateInfo(@RequestBody RiskRateInData inData) {
        BaseOutData outData = new BaseOutData();
        try{
            int result = riskRateManageService.UpdateOrAddForRiskRate(inData.getCompanyId(),
                    inData.getPkCompanyId(),inData.getRating(),inData.getRiskContent());
            if(result>0){
                outData.setCode("0");
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",result);
                outData.setData(resMap);
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //查询深圳所有上市公司风险类型信息
    @RequestMapping(value = "/findAllRiskInfo/{page}/{pageSize}/{companyNm}", method = RequestMethod.GET)
    public BaseOutData findAllRiskInfo(@PathVariable int page,@PathVariable int pageSize,@PathVariable String companyNm) {
        BaseOutData outData = new BaseOutData();
        List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try{
            int count = riskRateManageService.findCompanyRiskInfoCount(companyNm);
            List<Object> resultList = riskRateManageService.findCompanyRiskInfo(null,companyNm,page,pageSize);
            if(resultList !=null && resultList.size()>0){
                for (Object obj: resultList){
                    Object[] item = (Object[])obj;
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("companyId",item[0] !=null ? item[0].toString() : "");
                    map.put("companyNm",item[1] !=null ? item[1].toString() : "");
                    map.put("companySnm",item[2] !=null ? item[2].toString() : "");
                    map.put("securityCds",item[3] !=null ? item[3].toString() : "");
                    map.put("companyRiskId",item[4] !=null ? item[4].toString() : "");
                    map.put("riskType",item[5] !=null ? Integer.parseInt(item[5].toString()) : 0);
                    map.put("risksDetail",item[6] !=null ? item[6].toString() : "");
                    map.put("risksTypes",item[7] !=null ? item[7].toString() : "");
                    map.put("riskTarget",item[8] !=null ? item[8].toString() : "");
                    resList.add(map);
                }

                outData.setCode("0");
                outData.setCount(count);
                resMap.put("content",resList);
                outData.setData(resMap);
            } else{
                outData.setCode("1");
                outData.setMessage("查询深圳所有上市公司风险类型信息为空。");
                logger.info("查询深圳所有上市公司风险类型信息为空。");
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //查询单个公司风险类型信息
    @RequestMapping(value = "/findRiskRateInfoById/{companyRiskId}", method = RequestMethod.GET)
    public BaseOutData findRiskRateInfoById(@PathVariable String companyRiskId) {
        BaseOutData outData = new BaseOutData();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try{
            List<Object> resultList = riskRateManageService.findCompanyRiskInfo(companyRiskId,null,0,0);
            if(resultList !=null && resultList.size()>0){
                Object obj = resultList.get(0);
                Object[] item = (Object[])obj;
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("companyId",item[0] !=null ? item[0].toString() : "");
                map.put("companyNm",item[1] !=null ? item[1].toString() : "");
                map.put("companySnm",item[2] !=null ? item[2].toString() : "");
                map.put("securityCds",item[3] !=null ? item[3].toString() : "");
                map.put("companyRiskId",item[4] !=null ? item[4].toString() : "");
                map.put("riskType",item[5] !=null ? Integer.parseInt(item[5].toString()) : 0);
                map.put("risksDetail",item[6] !=null ? item[6].toString() : "");
                map.put("risksTypes",item[7] !=null ? item[7].toString() : "");
                map.put("riskTarget",item[8] !=null ? item[8].toString() : "");
                outData.setCode("0");
                resMap.put("content",map);
                outData.setData(resMap);
            } else{
                outData.setCode("1");
                outData.setMessage("查询单个公司风险类型信息为空。");
                logger.info("查询单个公司风险类型信息为空。");
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //编辑风险类型信息
    @RequestMapping(value = "/editRiskInfo", method = RequestMethod.POST)
    public BaseOutData editRiskInfo(@RequestBody CompanyRiskInfoData inData) {
        BaseOutData outData = new BaseOutData();
        try{
            int result = riskRateManageService.updateCompanyRiskInfo(inData);
            if(result>0){
                outData.setCode("0");
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",result);
                outData.setData(resMap);
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //添加风险类型信息
    @RequestMapping(value = "/addRiskInfo", method = RequestMethod.POST)
    public BaseOutData addRiskInfo(@RequestBody CompanyRiskInfoData inData) {
        BaseOutData outData = new BaseOutData();
        try{
            if(inData.getRisksDetail()==null){
                inData.setRisksDetail("");
            }
            int result = riskRateManageService.addCompanyRiskInfo(inData);
            if(result>0){
                outData.setCode("0");
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",result);
                outData.setData(resMap);
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }


    //删除风险类型信息
    @RequestMapping(value = "/removeRiskInfo/{compyRiskId}", method = RequestMethod.GET)
    public BaseOutData removeRiskInfo(@PathVariable String compyRiskId) {
        BaseOutData outData = new BaseOutData();
        try{
            int result = riskRateManageService.delCompanyRiskInfo(compyRiskId);
            if(result>0){
                outData.setCode("0");
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",result);
                outData.setData(resMap);
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }




    /* wkm 21080507*/

    //查询深圳所有上市公司基本信息
    @RequestMapping(value = "/findAllCompanyBaseInfo/{page}/{pageSize}/{companyNm}", method = RequestMethod.GET)
    public BaseOutData findAllCompanyBaseInfo(@PathVariable int page,@PathVariable int pageSize,@PathVariable String companyNm) {
        BaseOutData outData = new BaseOutData();
        List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try{
            int count = riskRateManageService.findCompanyBaseInfoCount(companyNm);
            List<Object> resultList = riskRateManageService.findCompanyBaseInfo(null,companyNm,page,pageSize);
            if(resultList !=null && resultList.size()>0){
                for (Object obj: resultList){
                    Object[] item = (Object[])obj;
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("companyId",item[0] !=null ? item[0].toString() : "");
                    map.put("companyCd",item[1] !=null ? item[1].toString() : "");
                    map.put("companyNm",item[2] !=null ? item[2].toString() : "");
                    map.put("companySnm",item[3] !=null ? item[3].toString() : "");
                    map.put("regAddr",item[4] !=null ? item[4].toString() : "");
                    map.put("securityCds",item[6] !=null ? item[6].toString() : "");
                    resList.add(map);
                }
                outData.setCode("0");
                outData.setCount(count);
                resMap.put("content",resList);
                outData.setData(resMap);
            } else{
                outData.setCode("1");
                outData.setMessage("查询深圳所有上市公司基本信息为空。");
                logger.info("查询深圳所有上市公司基本信息为空。");
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

    //查询单个公司基本信息
    @RequestMapping(value = "/findCompanyBaseInfoById/{companyId}", method = RequestMethod.GET)
    public BaseOutData findCompanyBaseInfoById(@PathVariable String companyId) {
        BaseOutData outData = new BaseOutData();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try{
            List<Object> resultList = riskRateManageService.findCompanyBaseInfo(companyId,null,0,0);
            if(resultList !=null && resultList.size()>0){
                Object obj = resultList.get(0);
                Object[] item = (Object[])obj;
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("companyId",item[0] !=null ? item[0].toString() : "");
                map.put("companyCd",item[1] !=null ? item[1].toString() : "");
                map.put("companyNm",item[2] !=null ? item[2].toString() : "");
                map.put("companySnm",item[3] !=null ? item[3].toString() : "");
                map.put("regAddr",item[4] !=null ? item[4].toString() : "");
                map.put("securityCds",item[6] !=null ? item[6].toString() : "");
                map.put("positionX",item[7] !=null ? item[7].toString() : "");
                map.put("positionY",item[8] !=null ? item[8].toString() : "");
                outData.setCode("0");
                resMap.put("content",map);
                outData.setData(resMap);
            } else{
                outData.setCode("1");
                outData.setMessage("查询单个公司基本信息为空。");
                logger.info("查询单个公司基本信息为空。");
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return outData;
    }

    //编辑风险类型信息
    @RequestMapping(value = "/editCompanyBaseInfo", method = RequestMethod.POST)
    public BaseOutData editCompanyBaseInfo(@RequestBody CompanyBaseInfo inData) {
        BaseOutData outData = new BaseOutData();
        try{
            int result = riskRateManageService.upDateCompanyBaseInfo(inData);
            if(result>0){
                outData.setCode("0");
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",result);
                outData.setData(resMap);
            }
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(e.getMessage());
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return outData;
    }

}
