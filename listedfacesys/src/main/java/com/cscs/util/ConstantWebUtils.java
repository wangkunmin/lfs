package com.cscs.util;

import sun.misc.BASE64Decoder;


import java.io.IOException;
import java.util.*;

/**
 * 公用工具类
 *
 * @author
 */
public class ConstantWebUtils {
    /**
     * 获取Token
     * @param userId
     * @return
     */
    public static String getToken(String userId) {
        Date date = new Date();
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("userId", userId);// 用户名
        payload.put("iat", date.getTime());// 生成时间
        Long tokenExt = Contants.TOKENEXT !=null ? Long.parseLong(Contants.TOKENEXT):180;
        payload.put("ext", date.getTime() + tokenExt * 1000 * 60 );// 过期时间2小时 2000 * 60 * 60
        String token = Jwt.createToken(payload);
        return token;
    }

    /**
     * Token 解析Token
     *
     * @param token
     * @return
     */
    public static String getEnCodeToken(String token) {
        String resToken = "";
        try {
            byte[] tt = new BASE64Decoder().decodeBuffer(token);
            resToken = new String(tt, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resToken;
    }


}
