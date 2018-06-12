package com.cscs.util;

//import com.cscs.portal.dto.CompanyTypeOutData;
//import com.cscs.portal.dto.IndustrysOutData;
//import com.cscs.portal.dto.SearchOutObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangjy on 2016/8/17.
 */
@Configuration
public class Contants {

    @Autowired
    private Environment _environment;

    public static final int PAGESIZE = 10;
//    public static SearchOutObj Region_ReturnVal = null;
//    public static IndustrysOutData Industry_ReturnVal = null;
//    public static IndustrysOutData IndustrySw_ReturnVal = null;
    public static Map<Object, Map<Object, Object>> Industrys_Level = new HashMap<Object, Map<Object, Object>>();
    public static Map<Object, Map<Object, Object>> IndustrysSw_Level = new HashMap<Object, Map<Object, Object>>();

    //所有公司平均成立时间
    public static String compyFoundDt = null;
    //所有公司平均注册资本
    public static String compyRegCapital = null;
    //公司类型总数
//    public static CompanyTypeOutData companyTypeOutData = null;


    //oracle2redis crontab
    public static String o2r_cron = null;


    // 发送验证码
    public static String SERVER_SENDCODE_URL;
    // 校验验证码
    public static String SERVER_VERIFY_CODE;
    // 验证码配置信息
    public static String APP_KEY;
    public static String APP_SECRET;
    public static String NONCE;
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    public static final String UTF_8 = "utf-8";
    public static String MOULD_ID;

    //邮件配置信息
    public static String PROTOCOL = "smtp";
    public static String SSL_PROTOCOL = "smtps";
    public static String MAIL_HOST;
    public static String MAIL_USER;
    public static String MAIL_PSW;
    public static String MAIL_ADMIN_LIST;
    public static String MAIL_SSL_PORT;
    //中数API
    public static String TOKEN_URL;
    public static String FUZZY_URL;
    public static String INFO_URL;
    public static String LAWSUIT_URL;
    public static final String CLIENT_ID = "clientapp";
    public static final String GRANT_TYPE = "password";
    public static final String USER_NAME = "portal";
    public static final String PASSWORD = "123";

    //全文搜索服务器

    public static String FULL_TEXT_URL;
    public static String FULL_TEXT_URL_DB;
    public static String FULL_TEXT_INDEX_DIR = "/newreport";
    public static String COMPANY_INDEX_URL;
    public static String RELATIONSHIP_URL;
    public static String NEWS_SERVER_URL;
    public static String WARNING_URL;
    public static String HBASE_URL;

    //XML
    public static Map<Object, Object> TABLE_MAPPING = null;

    //文件URL
    public static String FILE_URL;

    public static String INTELLIGENT_URL;
    //B版行业智能
    public static String INDUSTRY_URL;
    //关系图后端
    public static String CHART_URL;
    public static String CHART_CUSTOM_URL;
    //评分_关联风险
    public static String DAASCOMPANYSEARCH_URL;

    //UserTrace
    public static String USER_TRACE_PERIOD = "100";
    public static String USER_TRACE_THRESHOLD = "100";
//    public static Set<String> USER_TRACE_URL_SET = new HashSet<String>();
//    public static String USER_TRACE_URLS = "";

    //权限管理
//    public static String[] LIMITED_URL = {};
//    public static int MAX_ACCESS_NUMBER = 10;

    public static String REDIS_HOST;
    public static int REDIS_PORT;
    public static String REDIS_PASSWORD;

    public static int NEWS_IN_DAYS = 30;
    //时间段是否开启开关
    public static boolean TIMEFLAG;
    //时间段是否使用判断
    public static boolean TIME_IS_FLAG;
    public static String TIME_START;
    public static String TIME_END;
    public static boolean TIME_IS_WORK;
    public static boolean MAIL_SEND;
//    public static String UNDERTAKER_URL;
//    public static String DISRUPTINFO_URL;
//    public static String WENSHUTHEME_URL;
//    public static String COURT_URL;
//
//    public static String UNDERTAKER_PERSON_URL;
//    public static String DISRUPTINFO_PERSON_URL;

    //XML过滤字符
    public static String FILTER_XML_STR = "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]";

    //企业展台关系缩略图
    public static String FINDTHUMBNAIL;
    //私募图谱规则
    public static String FUNDSHOWRULES;
    //私募图谱（找关系方式）
    public static String FINDRELATION;
    //私募图谱
    public static String AFFILIATEDPARTY;
    //图谱人员
    public static String PERSON;
    //关系图
    public static String GETALLNODESANDRELS;
    //查看背景关系
    public static String FINDALLACTUALCONTROLLEROFCOMPANY;

    //将schema改为可配置形式
    public static String SCHEMA_NAME;

    //将token有效时间改为可配置形式
    public static String TOKENEXT;



    @PostConstruct
    public void initialization() {
        //sms server
        SERVER_SENDCODE_URL = _environment.getProperty("cscs.portal.sms.server.sendCode");
        SERVER_VERIFY_CODE = _environment.getProperty("cscs.portal.sms.server.verifyCode");
        APP_KEY = _environment.getProperty("cscs.portal.sms.server.code.appKey");
        APP_SECRET = _environment.getProperty("cscs.portal.sms.server.code.appSecret");
        NONCE = _environment.getProperty("cscs.portal.sms.server.code.nonce");
        MOULD_ID = _environment.getProperty("cscs.portal.sms.server.code.mouldId");

        //中数API
        TOKEN_URL = _environment.getProperty("cscs.portal.api.server.token");
        FUZZY_URL = _environment.getProperty("cscs.portal.api.server.enterprise.fuzzy");
        INFO_URL = _environment.getProperty("cscs.portal.api.server.enterprise.info");
        LAWSUIT_URL = _environment.getProperty("cscs.portal.api.server.enterprise.lawsuit");
        //solr server
        FULL_TEXT_URL = _environment.getProperty("cscs.portal.solr.research.query");
        FULL_TEXT_URL_DB = _environment.getProperty("cscs.portal.solr.research_db.query");
        NEWS_SERVER_URL = _environment.getProperty("cscs.portal.solr.news.query");
        COMPANY_INDEX_URL = _environment.getProperty("cscs.portal.solr.company.query");
        RELATIONSHIP_URL = _environment.getProperty("cscs.portal.solr.relation.query");
        //智能搜索server
        INTELLIGENT_URL = _environment.getProperty("cscs.portal.intelligent.server");
        //搜索行业server
        INDUSTRY_URL = _environment.getProperty("cscs.portal.industry.server");

        //图谱公司
        CHART_URL = _environment.getProperty("cscs.listedfacesys.chart.pfcompany");
        //企业展台关系缩略图
        FINDTHUMBNAIL = _environment.getProperty("cscs.listedfacesys.chart.findThumbnail");
        //私募图谱规则
        FUNDSHOWRULES = _environment.getProperty("cscs.listedfacesys.chart.fundShowRules");
        //私募图谱（找关系方式）
        FINDRELATION = _environment.getProperty("cscs.listedfacesys.chart.findRelation");
        //私募图谱
        AFFILIATEDPARTY = _environment.getProperty("cscs.listedfacesys.chart.affiliatedParty");
        //图谱人员
        PERSON = _environment.getProperty("cscs.listedfacesys.chart.person");
        //关系图
        GETALLNODESANDRELS = _environment.getProperty("cscs.listedfacesys.chart.getAllNodesAndRels");
        //查看背景关系
        FINDALLACTUALCONTROLLEROFCOMPANY = _environment.getProperty("cscs.listedfacesys.chart.findAllActualControllerOfCompany");


        CHART_CUSTOM_URL = _environment.getProperty("cscs.portal.chart.server.custom");
        //文件server
        FILE_URL = _environment.getProperty("filePath.url");
        //邮件服务区
        MAIL_HOST = _environment.getProperty("mail.server.host");
        MAIL_USER = _environment.getProperty("mail.server.user");
        MAIL_PSW = _environment.getProperty("mail.server.password");
        MAIL_ADMIN_LIST = _environment.getProperty("mail.server.adminlist");
        MAIL_SSL_PORT = _environment.getProperty("mail.server.sslport");

        USER_TRACE_PERIOD = _environment.getProperty("userTrace.period");
        USER_TRACE_THRESHOLD = _environment.getProperty("userTrace.threshold");
//        USER_TRACE_URLS = _environment.getProperty("userTrace.urllist");

        //预警server
        WARNING_URL = _environment.getProperty("cscs.portal.focus.warning");

        REDIS_HOST = _environment.getProperty("spring.redis.host");
        HBASE_URL = _environment.getProperty("cscs.portal.api.hbase.enterprise.info");
        String redisPort = _environment.getProperty("spring.redis.port");
        if (redisPort != null) {
            REDIS_PORT = Integer.valueOf(redisPort);
        }
        REDIS_PASSWORD = _environment.getProperty("spring.redis.password");

        SCHEMA_NAME = _environment.getProperty("cscs.facebook.config.schema");

        TOKENEXT = _environment.getProperty("cscs.facebook.config.tokenExt");
//        String limitedUrl = _environment.getProperty("cscs.portal.limited.url");
//        if (limitedUrl != null) {
//            LIMITED_URL = limitedUrl.split(",");
//        }
//        String limitedNumber = _environment.getProperty("cscs.portal.limited.number");
//        if (limitedNumber != null) {
//            MAX_ACCESS_NUMBER = Integer.valueOf(limitedNumber);
//        }

        //评分_关联风险
        DAASCOMPANYSEARCH_URL = _environment.getProperty("cscs.portal.DAASCompanySearch.server");
        //时间段是否开启开关
//        String check = _environment.getProperty("time.flag");
//            if("0".equals(check)){
//                TIMEFLAG = false;
//            }
//            if("1".equals(check)){
//                TIMEFLAG = true;
//            }
        //是否使用自定义 时间段
        String check_time_is_flag = _environment.getProperty("time.is.flag");
        if ("0".equals(check_time_is_flag)) {
            TIME_IS_FLAG = false;
        }
        if ("1".equals(check_time_is_flag)) {
            TIME_IS_FLAG = true;
        }
        TIME_START = _environment.getProperty("time.start");
        TIME_END = _environment.getProperty("time.end");

        //是否工作日
        String check_time_is_work = _environment.getProperty("time.is.work");
        if ("0".equals(check_time_is_work)) {
            TIME_IS_WORK = false;
        }
        if ("1".equals(check_time_is_work)) {
            TIME_IS_WORK = true;
        }
        //是否发送邮件
        String check_mail_send = _environment.getProperty("mail.send");
        if ("0".equals(check_mail_send)) {
            MAIL_SEND = false;
        }
        if ("1".equals(check_mail_send)) {
            MAIL_SEND = true;
        }

//        //诚成嘉和(机构)
//        UNDERTAKER_URL = _environment.getProperty("cscs.portal.biinfo.undertaker");
//        DISRUPTINFO_URL = _environment.getProperty("cscs.portal.biinfo.disruptinfo");
//        WENSHUTHEME_URL = _environment.getProperty("cscs.portal.biinfo.wenshutheme");
//        COURT_URL = _environment.getProperty("cscs.portal.biinfo.court");
//
//        //诚成嘉和(个人)
//        UNDERTAKER_PERSON_URL = _environment.getProperty("cscs.portal.biinfo.undertaker.person");
//        DISRUPTINFO_PERSON_URL = _environment.getProperty("cscs.portal.biinfo.disruptinfo.person");

        //私募数据导入redis crontab表达式
        o2r_cron = _environment.getProperty("cscs.PFschedule");


        String in_days = _environment.getProperty("cscs.portal.news.in.days");
        if (in_days != null) {
            NEWS_IN_DAYS = Integer.valueOf(in_days);
        }
    }
}

