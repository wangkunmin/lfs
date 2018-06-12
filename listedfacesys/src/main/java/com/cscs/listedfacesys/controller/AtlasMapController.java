package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.busi.AnnounceBusiService;
import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.CompanyInfoService;
import com.cscs.listedfacesys.services.SearchWarningService;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.Contants;
import com.cscs.util.DateUtils;
import com.cscs.util.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Create by wzy on 2018/2/1
 * 关联图谱
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/atlasMap")
public class AtlasMapController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    CompanyInfoService companyInfoServices;
    @Autowired
    WarningAnnounceService warningAnnounceService;
    @Autowired
    UserAttentionService userAttentionService;
    @Autowired
    SearchWarningService searchWarningService;

    //企业展台关系缩略图
    @RequestMapping(value = "/findThumbnail/thumbnail/company_id/{.+}")
    public String sendMail(HttpServletRequest request) throws IOException {
        String parms = getRequestParams(request,"company_id/");
        return HttpUtil.getResponse(Contants.FINDTHUMBNAIL + parms);
    }

    private String getUrl(HttpServletRequest request) {
        String context = request.getContextPath();
        String uri = request.getRequestURI();
        uri = uri.substring(context.length());
        uri = uri.replace("//","/");
        return uri;
    }

    private String getRequestParams(HttpServletRequest request,String findStr){
        String url = getUrl(request);
        String params = url.substring(url.indexOf(findStr)+findStr.length());
        return params;
    }

    //图谱公司
    @RequestMapping(value = "/pfcompany/company/company_id{.+}")
    public String sendAtlas(HttpServletRequest request) throws IOException {
        return HttpUtil.getResponse(Contants.CHART_URL + "?" + request.getQueryString());
    }




    //关系图
    @RequestMapping(value = "/FindRelation/GetAllNodesAndRels")
    public String sendRelation(HttpServletRequest request) throws IOException {
        return HttpUtil.getResponse(Contants.GETALLNODESANDRELS + "?" + request.getQueryString());
    }

    /**关键字搜索*/
    @RequestMapping(value = "/warningSearch/{userId}/{keyword}/{page}", method = RequestMethod.GET)
    public BaseOutData warningSearch(@PathVariable Long userId, @PathVariable String keyword, @PathVariable int page) {
        BaseOutData outData = new BaseOutData();
        List<WarningInfoData> warningInfoList = new ArrayList<>();
        String companyIdList = "";

        String city = "440300";

        int count = searchWarningService.basicSearchCount(keyword);
        /*获取公司id列表*/
        List<Object> outObj = searchWarningService.basicSearch(keyword, page);

        if ((outObj.size() == 0 || outObj == null) || count == 0) {
            outData.setCode("1");
            outData.setMessage("The Announce data is not queried!");
            logger.info("[未查询到公司数据]");
            return outData;
        }

        for (Object it : outObj) {
            Object[] item = (Object[]) it;
            companyIdList += item[0].toString() + ",";
        }
        if(!"".equals(companyIdList)){
            companyIdList = companyIdList.substring(0, companyIdList.length() - 1);
        }
        String startDate = DateUtils.getDateDiff2(-365);
        String endDate = DateUtils.getCurrentDate2();
        //根据id列表获取公告详情
        List<Object> contentList = warningAnnounceService.getWarningTop10Content(companyIdList, startDate, endDate);

        //根据id列表获取新闻详情
        List<Object> contentListnews = warningAnnounceService.getWarningTop10Contentfornews(companyIdList, startDate, endDate);
        for (Object it : contentListnews) {
            Object[] item = (Object[]) it;
            contentList.add(item);
        }

        //没有公告和新闻的公司加上来
        for (Object it : outObj) {
            boolean hav =false;
            Object[] item = (Object[]) it;
            for (Object it1 : contentList) {
                Object[] item1 = (Object[]) it1;
                if (item[0].toString().equals(item1[0].toString())) {
                    hav = true;
                    break;
                }
            }
            if (!hav){
                contentList.add(item);
            }
        }

        //查询是否关注
        Set<String> focusIds = userAttentionService.searchAllCompy(userId);
        //对获取到的数据进行遍历
        warningInfoList = AnnounceBusiService.getWarningInfoData(contentList, focusIds);
        /*添加三种快捷小标题*/
        warningInfoList = AnnounceBusiService.cutTypeName(warningInfoList);

//        List<Object> companyInfoList = companyInfoServices.companyBasicInfo(companyIdList);
//        warningInfoList = AnnounceBusiService.getCompanyInfo(companyInfoList, warningInfoList, focusIds);

        if (warningInfoList != null){
            Map<String, List<WarningInfoData>> data = new HashMap<>();
            data.put("warningDataList",warningInfoList);
            outData.setData(data);
            outData.setCode("0");
            outData.setCount(count);
            outData.setMessage("[查询成功]:The query is successful!");
            logger.info("[查询成功]"+warningInfoList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[公告数据处理异常]");
        }
        return outData;
    }

    //判断是否属于深圳市上市公司
    @RequestMapping(value = "/iscompany/{id}", method = RequestMethod.GET)
    public BaseOutData iscompany(@PathVariable Long id) {
        BaseOutData out = new BaseOutData();
        CompanyOutData dtoCompanyInfo = new CompanyOutData();
        List<Object> chekcompany = companyInfoServices.findExistCompy(id);
        try{
            if (chekcompany.size() > 0) {
                out.setCode("0");
                out.setData(null);
            }else{
                out.setCode("1");
                out.setMessage("查询公司基础信息，获取数据为空！");

            }


        }catch (Exception e){
            out.setCode("-1");
            out.setMessage("查询公司基础信息，获取数据异常！异常信息："+e.getMessage());
            logger.error("查询公司基础信息，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }
}
