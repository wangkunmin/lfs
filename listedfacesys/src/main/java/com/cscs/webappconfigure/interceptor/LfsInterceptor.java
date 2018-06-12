package com.cscs.webappconfigure.interceptor;

import com.cscs.util.ConstantWebUtils;
import com.cscs.util.Jwt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * wkm
 */
public class LfsInterceptor implements HandlerInterceptor {
    final Log logger = LogFactory.getLog(this.getClass());
    private final static String SRC_TOKEN = "Token";

    @Override
    public synchronized boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("LfsInterceptor ********* 启动拦截器");
        String servletPath = httpServletRequest.getServletPath();
        logger.info("servletPath ********* : " + servletPath);
        if ("/ifcUser/login".equals(servletPath) || "/ifcUser/register".equals(servletPath)
                || ((servletPath+"").indexOf("ifcUser/findAccountName")>0)) {
            //登录时不拦截
            return true;
        } else {
            String token = "";
            token = httpServletRequest.getHeader("Token");
            String userId = httpServletRequest.getHeader("TokenUserid");
            int tokenState = Jwt.validIsToken(token);
            switch (tokenState) {
                case 1:
                    //如果校验通过重置token时间
                    String new_token = ConstantWebUtils.getToken(userId);
                    httpServletResponse.setHeader("new_token",new_token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers", "new_token");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    logger.info("token验证成功*************");
                    return true;
                default:
                    httpServletResponse.setStatus(999);
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    logger.info("成功拦截************* servletPath：" + servletPath + " token:"+token);
                    return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
