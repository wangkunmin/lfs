package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.CompySuperviseData;
import com.cscs.listedfacesys.dto.CompySuperviseInfoOutData;
import com.cscs.listedfacesys.dto.UserAttentionInData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.UserSuperviseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/userSupervise")
public class UserSuperviseController {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    UserSuperviseService userSuperviseService;


    //根据公司名称模糊查询
    @RequestMapping(value = "/findCompySuperviseInfo",method = RequestMethod.POST)
    public BaseOutData findCompySuperviseInfo(@RequestBody UserAttentionInData inData){
        BaseOutData out = new BaseOutData();
        List<CompySuperviseInfoOutData> lists = new ArrayList<CompySuperviseInfoOutData>();

        try {
            int count = userSuperviseService.findCompySuperviseInfoCount(inData.getCompanyNm());
            List<Object> list =  userSuperviseService.findCompySuperviseInfo(inData.getPage(),inData.getPageSize(),inData.getCompanyNm());
            if(list !=null && list.size()>0){
                for (Object items: list) {
                    Object[] item = (Object[])items;
                    CompySuperviseInfoOutData outData = new CompySuperviseInfoOutData();
                    outData.setCompanyId(item[0] !=null ? item[0].toString() :"");
                    outData.setCompanyNm(item[1] !=null ? item[1].toString() :"");
                    outData.setCompanySnm(item[2] !=null ? item[2].toString() :"");
                    outData.setSecurityCd(item[3] !=null ? item[3].toString() :"");
                    String userInfoStr = item[4] !=null ? item[4].toString() :"";
                    outData.setCompySuperviseInfoList(this.getCompySuperviseInfo(userInfoStr));
                    lists.add(outData);
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("content",lists);
                out.setCode("0");
                out.setCount(count);
                out.setData(map);
            }else {
                out.setCode("1");
                out.setMessage("根据公司名称模糊查询监管人信息为空");
                logger.info("根据公司名称模糊查询监管人信息为空");
            }
        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("根据公司名称模糊查询监管人信息异常：异常信息："+e.getMessage());
            logger.info("根据公司名称模糊查询监管人信息异常：异常信息："+e.getMessage());
            e.printStackTrace();
        }
        return out;
    }

    //将用户名和用户ID拼接的字符串转Map
    private List<Map<String,Object>> getCompySuperviseInfo(String userIdAndNameStrs){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        if(userIdAndNameStrs !=null && !"".equals(userIdAndNameStrs)){
            String[] userIdAndNameStr = userIdAndNameStrs.split(",");
            for(String strs: userIdAndNameStr){
                String [] str = strs.split("-");
                Map<String,Object> map = new HashMap<String,Object>();
                if(str.length ==1){
                    map.put("userId",str[0]);
                    map.put("userNm","");
                }else if (str.length ==2){
                    map.put("userId",str[0]);
                    map.put("userNm",str[1]);
                }
                list.add(map);
            }
        }

        return list;
    }



    //操作监护人表
    @RequestMapping(value = "/operationSupervise",method = RequestMethod.POST)
    public BaseOutData operationSupervise(@RequestBody CompySuperviseData inData) throws Exception {
        BaseOutData out = new BaseOutData();
        try {
            userSuperviseService.operationSupervise(inData);
            out.setCode("0");
            out.setMessage("操作成功！");
        }catch (Exception e){
            out.setCode("-1");
            out.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return  out;
    }

}
