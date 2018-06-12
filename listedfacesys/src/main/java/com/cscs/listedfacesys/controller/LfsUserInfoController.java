package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.LfsUserInData;
import com.cscs.listedfacesys.dto.LfsUserInfoData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.entity.LfsUser;
import com.cscs.listedfacesys.services.LfsUserInfoService;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.util.ConstantWebUtils;
import com.cscs.util.Md5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by wth on 2018/1/27
 * 用户信息
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/ifcUser")
public class LfsUserInfoController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    LfsUserInfoService lfsUserInfoService;
    @Autowired
    UserAttentionService userAttentionService;

    /**
     * 用户登录
     * @param inData
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseOutData getLogin(@RequestBody LfsUserInData inData) {
        BaseOutData outData = new BaseOutData();
        LfsUserInfoData info = new LfsUserInfoData();
        Map<String,Object> resMap = new HashMap<String,Object>();
        try {
            String chkaccountnm = lfsUserInfoService.findByUserName(inData.getAccountNm());
            if ("".equals(chkaccountnm)){
                outData.setCode("1");
                outData.setMessage("用户名或密码输入错误,请重新输入！");
            }else{
                String pwd = Md5Util.MD5(inData.getAccountPw());
                List<Object> resList =  lfsUserInfoService.loginForGetUserInfo(inData.getAccountNm(),pwd);
                if(resList !=null && resList.size()>0){
                    Object[] item = (Object[]) resList.get(0);
                    String userId = item[0] !=null ? item[0].toString() :"";//获取userId

                    info.setUserId(item[0] !=null ? item[0].toString() :"");
                    info.setUserNm(item[1] !=null ? item[1].toString() :"");
                    info.setHeadUrl(item[2] !=null ? item[2].toString() :"");
                    info.setBirth(item[3] !=null ? item[3].toString() :"");
                    info.setUserGender(item[4] !=null ? item[4].toString() :"");
                    info.setIdCardNo(item[5] !=null ? item[5].toString() :"");
                    info.setCellphone(item[6] !=null ? item[6].toString() :"");
                    info.setPhone(item[7] !=null ? item[7].toString() :"");
                    info.setEmail(item[8] !=null ? item[8].toString() :"");
                    info.setAddress(item[9] !=null ? item[9].toString() :"");
                    info.setMajor(item[10] !=null ? item[10].toString() :"");
                    info.setEducational(item[11] !=null ? item[11].toString() :"");
                    info.setPoliticalStatus(item[12] !=null ? item[12].toString() :"");
                    info.setIntroduction(item[13] !=null ? item[13].toString() :"");
                    info.setUserType(item[14] !=null ? item[14].toString() :"");
                    info.setCompanyId(item[15] !=null ? item[15].toString() :"");
                    info.setCompanyNm(item[16] !=null ? item[16].toString() :"");
                    info.setPosition(item[17] !=null ? item[17].toString() :"");
                    info.setGoodType(item[18] !=null ? item[18].toString() :"");
                    info.setIndustrySid(item[19] !=null ? item[19].toString() :"");
                    info.setUpdtBy(item[20] !=null ? item[20].toString() :"");
                    info.setUpdtDt(item[21] !=null ? item[21].toString() :"");
                    info.setAccountId(item[22] != null? item[22].toString() : "");
                    info.setRoleId(item[23] != null? item[23].toString() : "");

                    info.setAccountNm(inData.getAccountNm());
                    //生成token
                    info.setToken(ConstantWebUtils.getToken(userId));

                    outData.setCode("0");
                    resMap.put("content",info);
                    outData.setData(resMap);
                    outData.setMessage("Login Successful!");
                    logger.info("用户登陆成功！");
                }else {
                    outData.setCode("1");
                    outData.setMessage("用户名或密码输入错误,请重新输入！");
                }
            }


        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage("登录获取用户信息异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }


        return outData;
    }

    /**
     * 注册新用户
     * @param inData
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseOutData register(@RequestBody LfsUserInData inData) {
        BaseOutData outData = new BaseOutData();
        if(inData.getAccountPw()!=null && !"".equals(inData.getAccountPw())){
            String regex = "^[a-zA-Z][a-zA-Z0-9_]{7,15}$";
            if(!inData.getAccountPw().matches(regex)){
                outData.setCode("1");
                outData.setMessage("用户名或密码输入错误,请重新输入！");
                return outData;
            }
        }else{
            outData.setCode("1");
            outData.setMessage("用户名或密码输入错误,请重新输入！");
            return outData;
        }
        String userName = lfsUserInfoService.findByUserName(inData.getAccountNm());

        if (userName .equals(inData.getAccountNm())) {
            outData.setCode("1");
            outData.setMessage("用户名已被占用");
            return outData;
        }

        try{
            String pwd = Md5Util.MD5(inData.getAccountPw());
            lfsUserInfoService.register(inData.getAccountNm(),pwd,inData.getAccountNm(), 0L);

            outData.setCode("0");
            outData.setMessage("用户注册成功！");
        } catch (Exception e) {
            outData.setCode("-1");
            outData.setMessage("用户注册失败！");
        }
        return  outData;
    }

    /**
     * 修改密码
     * @param inData
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public BaseOutData updatePassword(@RequestBody LfsUserInData inData) {
        BaseOutData outData = new BaseOutData();
        try {
            if (inData.getUserId() != null && inData.getAccountPw() != null && inData.getAccountNm() != null) {
                lfsUserInfoService.updatePassword(inData.getUserId(), inData.getAccountNm(),Md5Util.MD5(inData.getAccountPw()));
                outData.setCode("0");
                outData.setMessage(" Successful");
                logger.info("密码修改成功！");
            } else {
                outData.setCode("1");
                outData.setMessage(" fail");
                logger.info("缺少参数");
            }

        } catch (Exception e) {
            outData.setCode("-1");
            outData.setMessage(" failed");
            logger.info("密码修改失败！");
        }

        return outData;
    }


    /**
     * 更新用户详细信息
     * @param inData
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public BaseOutData updateAccount(@RequestBody LfsUserInData inData) {
        BaseOutData outData = new BaseOutData();
        try {
            lfsUserInfoService.updateAccount(inData.getUserId(), inData.getUserNm(), inData.getCellPhone(),
                    inData.getPhone(), inData.getAddress(), inData.getEmail(), inData.getCompanyId(),
                    inData.getCompanyNm(), inData.getPosition());

            outData.setCode("0");
            outData.setMessage("[更新成功]");
            logger.info("更新成功！");
        }catch (Exception e){
            outData.setCode("-1");
            outData.setMessage(" [更新失败]");
            logger.info("更新失败！");
        }
        return outData;
        }

    /**
     * 查询用户信息
      * @param userId
     * @return
     */
    @RequestMapping(value = "/findUserInfo/{userId}", method = RequestMethod.GET)
    public BaseOutData findUserInfo (@PathVariable Long userId){
        BaseOutData outData = new BaseOutData();
        Map<String,LfsUserInfoData> dataMap = new HashMap<String,LfsUserInfoData>();
        List<Object> resList =  lfsUserInfoService.findByUserId(userId);
        LfsUserInfoData info = new LfsUserInfoData();
        if(resList != null && resList.size() > 0) {
            Object[] item = (Object[]) resList.get(0);
            info.setUserId(item[0] != null ? item[0].toString() : "");
            info.setUserNm(item[1] != null ? item[1].toString() : "");
            info.setHeadUrl(item[2] != null ? item[2].toString() : "");
            info.setBirth(item[3] != null ? item[3].toString() : "");
            info.setUserGender(item[4] != null ? item[4].toString() : "");
            info.setIdCardNo(item[5] != null ? item[5].toString() : "");
            info.setCellphone(item[6] != null ? item[6].toString() : "");
            info.setPhone(item[7] != null ? item[7].toString() : "");
            info.setEmail(item[8] != null ? item[8].toString() : "");
            info.setAddress(item[9] != null ? item[9].toString() : "");
            info.setMajor(item[10] != null ? item[10].toString() : "");
            info.setEducational(item[11] != null ? item[11].toString() : "");
            info.setPoliticalStatus(item[12] != null ? item[12].toString() : "");
            info.setIntroduction(item[13] != null ? item[13].toString() : "");
            info.setUserType(item[14] != null ? item[14].toString() : "");
            info.setCompanyId(item[15] != null ? item[15].toString() : "");
            info.setCompanyNm(item[16] != null ? item[16].toString() : "");
            info.setPosition(item[17] != null ? item[17].toString() : "");
            info.setGoodType(item[18] != null ? item[18].toString() : "");
            info.setIndustrySid(item[19] != null ? item[19].toString() : "");
            info.setUpdtBy(item[20] != null ? item[20].toString() : "");
            info.setUpdtDt(item[21] != null ? item[21].toString() : "");
            info.setAccountNm(item[22] != null ? item[22].toString() : "");
            info.setRoleId(item[23] != null? item[23].toString() : "");
        }
        int count = userAttentionService.findFollowedCompyInfoCount(userId, null);
        dataMap.put("userInfo", info);
        outData.setData(dataMap);
        outData.setCode("0");
        outData.setCount(count);
        outData.setMessage("[查询成功]");
        logger.info("查询成功！");
        return outData;
    }

    /**
     * 查询用户昵称是否存在
     * @param userNm
     * @return
     */
    @RequestMapping(value = "/findUserName/{userNm}", method = RequestMethod.GET)
    public BaseOutData findUserName(@PathVariable String userNm) {
        BaseOutData outData = new BaseOutData();

        int sum = lfsUserInfoService.findUserName(userNm);

        if (sum != 0) {
            outData.setCode("1");
            outData.setMessage("改昵称已存在！");
        } else {
            outData.setCode("0");
            outData.setMessage("该昵称可用！");
        }
        return outData;
    }

    /**
     * 查询用户名是否存在
     * @param accountNm
     * @return
     */
    @RequestMapping(value = "/findAccountName/{accountNm}", method = RequestMethod.GET)
    public BaseOutData findAccountName(@PathVariable String accountNm) {
        BaseOutData outData = new BaseOutData();

        int sum = lfsUserInfoService.findAcountName(accountNm);

        if (sum > 0) {
            outData.setCode("1");
            outData.setMessage("该用户已存在！");
        } else {
            outData.setCode("0");
            outData.setMessage("该用户可用！");
        }
        return outData;
    }

}
