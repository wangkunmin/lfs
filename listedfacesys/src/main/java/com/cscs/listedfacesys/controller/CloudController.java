package com.cscs.listedfacesys.controller;

import com.cscs.util.Contants;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by hj on 2018/02/24
 * 词云
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/wordCloud")
public class CloudController {

    @Autowired
    private StringRedisTemplate rt;

    protected final Log logger = LogFactory.getLog(this.getClass());



    //词云
    @RequestMapping(value = "/FindNewKeyWords/FindKeyWordsByCompanyId/{id}")
    public String sendCloud(@PathVariable Long id) throws IOException {
        // System.out.println("-----------------------------------");
        String rs = rt.opsForValue().get("newscloud_" + id);
        // System.out.println(rs);
        // System.out.println("-----------------------------------");
        return rs;
        // return HttpUtil.getResponse(getUrl(request));
    }



}
