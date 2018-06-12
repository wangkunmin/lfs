package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.util.Jwt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wkm on 2018/2/27.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/token")
public class TokenController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    //重置TOKEN时间
    @RequestMapping(value = "/setToken", method = RequestMethod.POST)
    public BaseOutData setToken(@RequestBody String token){
        BaseOutData outData = new BaseOutData();
        Map<String,Object> resMap = new HashMap<String,Object>();
        Map<String,String> info = new HashMap<>();
        try {
            JSONObject item = new JSONObject(token);
            info.put("token", Jwt.setToken(item.getString("token")));
            outData.setCode("0");
            resMap.put("content", info);
            outData.setData(resMap);
            outData.setMessage("重置token成功");
            logger.info("重置token成功！");
        }catch(Exception e){
            outData.setCode("-1");
            outData.setMessage("重置token异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }
        return outData;
    }

    //检验TOKEN是否有效
    @RequestMapping(value = "/findToken", method = RequestMethod.POST)
    public BaseOutData findToken(@RequestBody String token){
        JSONObject item = new JSONObject(token);
        return Jwt.validToken(item.getString("token"));
    }
}