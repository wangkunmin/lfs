package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.LfsUserInfoData;
import com.cscs.listedfacesys.dto.UserManagementInData;
import com.cscs.listedfacesys.dto.UserManagementOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.LfsUserInfoService;
import com.cscs.listedfacesys.services.UserManageService;
import com.cscs.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户管理,后台管理
 * create by wzy on 2018/03/06
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/userManagement")
public class UserManagementController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private LfsUserInfoService lfsUserInfoService;

    /**
     * 后台管理：账户维护
     * 查询所有账户列表
     * @return
     */
    @RequestMapping(value = "/userMaintain", method = RequestMethod.POST)
    public BaseOutData getUserMaintain(@RequestBody UserManagementInData inData) {
        BaseOutData outData = new BaseOutData();
        Map<String, List<UserManagementOutData >> data = new HashMap<>();

        int count = userManageService.findUserMaintainCount(inData.getAccountNm());
        logger.info("[账户条目数]" + count);

        List<Object> infoList = userManageService.findUserMaintain(inData.getAccountNm(),
                inData.getPage(), inData.getPageSize());
        if (infoList == null || infoList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("[查询失败]");
            logger.info("[未查询到账户信息列表]");
            return outData;
        }

        List<UserManagementOutData> outDataList = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            UserManagementOutData infoData = new UserManagementOutData();
            Object[] item = (Object[]) infoList.get(i);
            infoData.setUserId(item[0] == null ? null : Long.valueOf(item[0].toString()));
            infoData.setAccountNm(item[1] == null ? null : item[1].toString());
            infoData.setActivityType(item[2] == null ? null : Integer.valueOf(item[2].toString()));
            infoData.setRoleId(item[3] == null ? null : item[3].toString());
            infoData.setRegeditDt(item[4] == null ? null : item[4].toString());
            infoData.setRoleNm(item[5] == null ? null : item[5].toString());
            infoData.setUserNm(item[6] == null ? null : item[6].toString());
            infoData.setAccountId(item[7] == null ? null : Long.valueOf(item[7].toString()));
            outDataList.add(infoData);
        }

        data.put("UserManagementList", outDataList);
        outData.setCode("0");
        outData.setMessage("[查询成功]");
        outData.setCount(count);
        outData.setData(data);
        logger.info("[查询成功]" );
        return outData;
    }

    /**
     * 后台管理：账户维护
     * 添加或修改账户权限
     * @return
     */
    @Transactional
    @RequestMapping(value = "/editUserMaintain", method = RequestMethod.POST)
    public BaseOutData addOrSaveUserMaintain(@RequestBody UserManagementInData inData) {
        BaseOutData outData = new BaseOutData();
        try {

            Long managerId = inData.getUserId();
            Long accountId = 0L;
            Long userId = 0L;
            int flag = -1;

            /*管理员新增用户*/
            if (inData.getType() == 0) {
                String account = lfsUserInfoService.findByUserName(inData.getAccountNm());
                if (account != null && account != "") {
                    outData.setCode("1");
                    outData.setMessage("[新增失败]:该用户已注册");
                    return outData;
                }
                if (inData.getAccountPw() == null || inData.getAccountPw() == "") {
                    inData.setAccountPw("123456");
                }

                lfsUserInfoService.register(inData.getAccountNm(),inData.getAccountPw(),
                        inData.getAccountNm(), inData.getRoleId());
                flag = 0;
                outData.setCode("0");
                outData.setMessage("[操作成功]");
                logger.info("[操作成功]" );
            }
            /*管理员重置密码*/
            else if (inData.getType() == 1) {
                List<Object> objList = lfsUserInfoService.findUserAccountId(inData.getAccountNm());
                if (objList == null || objList == null) {
                    outData.setCode("1");
                    outData.setMessage("[查询失败]");
                    return outData;
                }
                Number nb = (Number) objList.get(0);
                accountId = nb.longValue();
                flag = 1;
                if (accountId != 0L || userId != 0L) {

                    userManageService.editUserRole(accountId, inData.getAccountPw(), inData.getRoleId(), managerId, flag);

                    outData.setCode("0");
                    outData.setMessage("[操作成功]");
                    logger.info("[操作成功]" );
                } else {
                    outData.setCode("1");
                    outData.setMessage("[操作失败]");
                }
            }


        } catch (Exception e) {
            outData.setCode("-1");
            outData.setMessage("[操作失败]：后台异常");
            logger.info("[操作失败]:后台异常" );
        }

        return outData;
    }

    /**
     * 后台管理：账户维护
     * 用户删除
     * @param accountId
     * @return
     */
    @Transactional
    @RequestMapping(value = "/deleteUser/{accountId}", method = RequestMethod.GET)
    public BaseOutData deleteUser(@PathVariable Long accountId) {
        BaseOutData outData = new BaseOutData();

        try {

            userManageService.deleteUser(accountId);

        } catch (Exception e) {
            outData.setCode("-1");
            outData.setMessage("[删除失败]:后台异常");
            logger.info("[删除失败]:后台异常");
        }
        outData.setCode("0");
        outData.setMessage("[删除成功]");
        logger.info("[删除成功]");
        return outData;
    }

}
