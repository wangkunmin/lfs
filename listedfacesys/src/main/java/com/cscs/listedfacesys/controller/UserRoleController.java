package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.UserManagementOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.UserRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理,后台管理
 * create by wzy on 2018/03/20
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/userRole")
public class UserRoleController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 后台管理：角色维护
     * 查询角色
     * @return
     */
    @RequestMapping(value = "/findUserAccredit", method = RequestMethod.GET)
    public BaseOutData findUserAccredit() {
        BaseOutData outData = new BaseOutData();
        Map<String, List<UserManagementOutData>> dataMap = new HashMap<>();
        List<UserManagementOutData> roleList = new ArrayList<>();

        List<Object> objList = userRoleService.getRole();
        if (objList == null || objList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("[查询失败]");
        } else {
            for (Object o : objList) {
                Object[] item = (Object[]) o;
                UserManagementOutData data = new UserManagementOutData();

                data.setRoleId(item[0].toString());
                data.setRoleNm(item[1].toString());
                roleList.add(data);
            }
            dataMap.put("roleList", roleList);
            outData.setCode("0");
            outData.setMessage("[查询成功]");
            outData.setData(dataMap);
        }

        return outData;
    }

    /**
     * 后台管理：角色维护
     * 修改角色
     * @return
     */
    @RequestMapping(value = "/updateUserAccredit/{roleId}/{roleNm}", method = RequestMethod.GET)
    public BaseOutData updateUserAccredit(@PathVariable Long roleId, @PathVariable String roleNm) {
        BaseOutData outData = new BaseOutData();
        try {

            userRoleService.updateRole(roleNm, roleId);

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
