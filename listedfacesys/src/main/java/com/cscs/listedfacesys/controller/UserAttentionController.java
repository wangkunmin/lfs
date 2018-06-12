package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.CompanyInfoService;
import com.cscs.listedfacesys.services.LfsUserInfoService;
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
 * Create by hj on 2018/2/22
 * 用户关注
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/userAttention")
public class UserAttentionController {

    protected final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    UserAttentionService userAttentionService;

    @Autowired
    CompanyInfoService companyInfoService;

    //添加用户关注
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseOutData addAttention(@RequestBody UserAttentionInData inData) {
        BaseOutData outData = new BaseOutData();

        try {
            userAttentionService.addAttention(inData.getUserId(), inData.getCompanyId(),inData.getCompanyNm(),inData.getFocusType());
            outData.setCode("0");
            outData.setMessage("Attention Successful!");
            logger.info("用户关注成功！");
        } catch (Exception e) {
            outData.setCode("1");
            outData.setMessage("Attention failed!");
            logger.info("用户关注失败！");
        }

        return outData;
    }

    //取消用户关注
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseOutData deleteAttention(@RequestBody UserAttentionInData inData) {
        BaseOutData outData = new BaseOutData();

        try {
            userAttentionService.deleteAttention(inData.getUserId(), inData.getCompanyId());
            outData.setCode("0");
            outData.setMessage("Delete Attention Successful!");
            logger.info("用户取消关注成功！");
        } catch (Exception e) {
            outData.setCode("1");
            outData.setMessage("Attention failed!");
            logger.info("用户取消关注失败！");
        }

        return outData;
    }


    //查询该用户已关注的公司信息分页
    @RequestMapping(value = "/findFollowedCompyInfo", method = RequestMethod.POST)
    public BaseOutData findFollowedCompyInfo(@RequestBody UserFollowInData inData) {
        BaseOutData out = new BaseOutData();
        List<UserFollowCompyOutData> outList = new ArrayList<UserFollowCompyOutData>();
        try {
            int count = userAttentionService.findFollowedCompyInfoCount(inData.getUserId(), inData.getCompanyNm());
            List<Object> resList =  userAttentionService.findFollowedCompyInfo(inData.getPage(),inData.getPageSize(),inData.getUserId(), inData.getCompanyNm());
            if(resList !=null && resList.size()>0){
                for (int i = 0; i <resList.size() ; i++) {
                    Object[] item = (Object[]) resList.get(i);
                    UserFollowCompyOutData outData = new UserFollowCompyOutData();
                    outData.setCompanyId(item[0]!=null ? item[0].toString() :"");
                    outData.setCompanySnm(item[1]!=null ? item[1].toString() :"");
                    outData.setFollowTime(item[2]!=null ? item[2].toString() :"");
                    outData.setCompanyNm(item[3]!=null ? item[3].toString() :"");
                    outData.setSecurityCode(item[4]!=null ? item[4].toString() :"");
                    outData.setIsFocused(true);
                    outList.add(outData);
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",outList);
                out.setCount(count);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("查询该用户已关注的公司信息为空");
                logger.info("查询该用户已关注的公司信息为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("查询该用户已关注的公司信息异常："+e.getMessage());
            logger.error("查询该用户已关注的公司信息异常："+e.getMessage());
        }

        return out;
    }

    //查询该用户未关注的公司信息
    @RequestMapping(value = "/findNotFollowCompyInfo", method = RequestMethod.POST)
    public BaseOutData findNotFollowCompyInfo(@RequestBody UserFollowInData inData) {
        BaseOutData out = new BaseOutData();
        List<UserFollowCompyOutData> outList = new ArrayList<UserFollowCompyOutData>();
        try {
            int count = userAttentionService.findNotFollowCompyInfoCount(inData.getUserId(), inData.getCompanyNm());
            List<Object> resList =  userAttentionService.findNotFollowCompyInfo(inData.getPage(),inData.getPageSize(),inData.getUserId(), inData.getCompanyNm());
            if(resList !=null && resList.size()>0){
                for (int i = 0; i <resList.size() ; i++) {
                    Object[] item = (Object[]) resList.get(i);
                    UserFollowCompyOutData outData = new UserFollowCompyOutData();
                    outData.setCompanyId(item[0]!=null ? item[0].toString() :"");
                    outData.setCompanySnm(item[1]!=null ? item[1].toString() :"");
                    outData.setFollowTime(item[2]!=null ? item[2].toString() :"");
                    outData.setCompanyNm(item[3]!=null ? item[3].toString() :"");
                    outData.setSecurityCode(item[4]!=null ? item[4].toString() :"");
                    outData.setIsFocused(false);
                    outList.add(outData);
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",outList);
                out.setCount(count);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("查询该用户未关注的公司信息为空");
                logger.info("查询该用户未关注的公司信息为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("查询该用户未关注的公司信息异常："+e.getMessage());
            logger.error("查询该用户未关注的公司信息异常："+e.getMessage());
        }

        return out;
    }

    //批量添加用户关注
    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    public BaseOutData batchAddAttention(@RequestBody BatchAddInData inData) {
        BaseOutData outData = new BaseOutData();

        try {
            userAttentionService.batchAddAttention(inData.getUserId(), inData.getList());
            outData.setCode("0");
            outData.setMessage("Attention Successful!");
            logger.info("批量添加用户关注成功！");
        } catch (Exception e) {
            outData.setCode("1");
            outData.setMessage("Attention failed!");
            logger.info("批量添加用户关注失败！");
        }

        return outData;
    }

    //批量取消用户关注
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public BaseOutData batchDeleteAttention(@RequestBody BatchDeleteInData inData) {
        BaseOutData outData = new BaseOutData();

        try {
            userAttentionService.batchDeleteAttention(inData.getUserId(), inData.getCompanyIds());
            outData.setCode("0");
            outData.setMessage("Delete Attention Successful!");
            logger.info("批量取消用户关注成功！");
        } catch (Exception e) {
            outData.setCode("1");
            outData.setMessage("Attention failed!");
            logger.info("批量取消用户关注失败！");
        }

        return outData;
    }

    //查询用户已关注的公司列表
    @RequestMapping(value = "/findAllFollowedCompyInfo", method = RequestMethod.POST)
    public BaseOutData findAllFollowedCompyInfo(@RequestBody UserFollowInData inData) {
        BaseOutData out = new BaseOutData();
        List<UserFollowCompyOutData> outList = new ArrayList<UserFollowCompyOutData>();
        try {
            List<Object> resList =  userAttentionService.findAllFollowedCompyInfo(inData.getUserId());
            if(resList !=null && resList.size()>0){
                for (int i = 0; i <resList.size() ; i++) {
                    Object[] item = (Object[]) resList.get(i);
                    UserFollowCompyOutData outData = new UserFollowCompyOutData();
                    outData.setCompanyId(item[0]!=null ? item[0].toString() :"");
                    outData.setCompanySnm(item[1]!=null ? item[1].toString() :"");
                    outData.setFollowTime(item[2]!=null ? item[2].toString() :"");
                    outData.setCompanyNm(item[3]!=null ? item[3].toString() :"");
                    outData.setSecurityCode(item[4]!=null ? item[4].toString() :"");
                    outData.setIsFocused(true);
                    outList.add(outData);
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",outList);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("查询用户已关注的公司列表信息为空");
                logger.info("查询用户已关注的公司列表信息为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("查询用户已关注的公司列表信息异常："+e.getMessage());
            logger.error("查询用户已关注的公司列表信息异常："+e.getMessage());
        }

        return out;
    }

    //查询用户是否已关注某公司
    @RequestMapping(value = "/findFollowedCompyInfoById/{userId}/{companyId}", method = RequestMethod.GET)
    public BaseOutData findFollowedCompyInfoById(@PathVariable Long userId,@PathVariable String companyId) {
        BaseOutData out = new BaseOutData();
        try {
                boolean isFollowed =  userAttentionService.findFollowedCompyInfoById(userId,companyId);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",isFollowed);
                out.setData(map);
                out.setCode("0");

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("查询用户是否已关注某公司异常："+e.getMessage());
            logger.error("查询用户是否已关注某公司异常："+e.getMessage());
        }

        return out;
    }

    //查询公司信息
    @RequestMapping(value = "/queryCompanyInfo/{companyNmOrSecurityCd}", method = RequestMethod.GET)
    public BaseOutData queryCompanyInfo(@PathVariable String companyNmOrSecurityCd) {
        BaseOutData out = new BaseOutData();
        List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
        try {
            List<Object> list = companyInfoService.queryCompanyInfo(companyNmOrSecurityCd);
            if(list !=null && list.size()>0){
                for (Object items : list ) {
                    Object[] item = (Object[])items;
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("companyId",item[0]);
                    map.put("companyNm",item[1]);
                    map.put("securityCd",item[3]);
                    map.put("companySnm",item[2]);
                    resList.add(map);
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",resList);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("查询公司信息为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("查询公司信息异常："+e.getMessage());
            logger.error("查询公司信息异常："+e.getMessage());
            e.printStackTrace();
        }
        return out;
    }

    //查询用户未关注公司列表
    @RequestMapping(value = "/queryNotAttentCompyInfo/{userId}/{companyNmOrSecurityCd}", method = RequestMethod.GET)
    public BaseOutData queryNotAttentionCompanyInfo(@PathVariable String userId,@PathVariable String companyNmOrSecurityCd) {
        BaseOutData out = new BaseOutData();
        List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
        try {
            List<Object> list = companyInfoService.queryNotAttentionCompanyInfo(userId,companyNmOrSecurityCd);
            if(list !=null && list.size()>0){
                for (Object items : list ) {
                    Object[] item = (Object[])items;
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("companyId",item[0]);
                    map.put("companyNm",item[1]);
                    map.put("securityCd",item[3]);
                    map.put("companySnm",item[2]);
                    resList.add(map);
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",resList);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("查询公司信息为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("查询公司信息异常："+e.getMessage());
            logger.error("查询公司信息异常："+e.getMessage());
            e.printStackTrace();
        }
        return out;
    }
}
