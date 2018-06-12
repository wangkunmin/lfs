package com.cscs.util;

import java.io.IOException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * Created by hj on 2018/3/1.
 */
public class HttpUtil {
    public static String getResponse(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet fuzzyGet = new HttpGet(url);
        int CONNECTION_TIMEOUT_MS = 6 * 1000; // Timeout in millis.
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT_MS)
                .setConnectTimeout(CONNECTION_TIMEOUT_MS)
                .setSocketTimeout(CONNECTION_TIMEOUT_MS)
                .build();
//    fuzzyGet.setConfig(requestConfig);
        CloseableHttpResponse fuzzyResponse = httpClient.execute(fuzzyGet);
        String fuzzyList = EntityUtils.toString(fuzzyResponse.getEntity(), Contants.UTF_8);
        if ("".equals(fuzzyList)) {
            return null;
        } else {
            return fuzzyList;
        }
    }
}
