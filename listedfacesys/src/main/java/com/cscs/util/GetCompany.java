package com.cscs.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by sh on 2016/12/15.
 */
public class GetCompany {

    //公司列表查询
    public static String[] getCompanyList(String keyword) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 查询
        HttpGet fuzzyGet = new HttpGet(Contants.FUZZY_URL + keyword);

        // 执行请求
        CloseableHttpResponse fuzzyResponse = httpClient.execute(fuzzyGet);

        String fuzzyList = EntityUtils.toString(fuzzyResponse.getEntity(), Contants.UTF_8);
        if ("".equals(fuzzyList)) {
            return null;
        }

        JSONObject companyList = new JSONObject(fuzzyList);
        if (companyList.isNull("ENTNAME")) {
            return null;
        }

        String companyListStr = companyList.get("ENTNAME").toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");

        if ("".equals(companyListStr)) {
            return null;
        }

        return companyListStr.split(",");
    }

    //公司详细查询
    public static String getCompanyDetail(String keyword) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(Contants.TOKEN_URL);
//        httpPost.addHeader("Authorization", "Basic Y2xpZW50YXBwOjEyMzQ1Ng==");

        // 设置请求的参数
//        List<NameValuePair> httpBody = new ArrayList<NameValuePair>();
//        httpBody.add(new BasicNameValuePair("client_id", Contants.CLIENT_ID));
//        httpBody.add(new BasicNameValuePair("grant_type", Contants.GRANT_TYPE));
//        httpBody.add(new BasicNameValuePair("username", Contants.USER_NAME));
//        httpBody.add(new BasicNameValuePair("password", Contants.PASSWORD));
//        httpPost.setEntity(new UrlEncodedFormEntity(httpBody, Contants.UTF_8));

        // token执行请求
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        String responseEntity = EntityUtils.toString(response.getEntity(), Contants.UTF_8);
//        JSONObject json = new JSONObject(responseEntity);

        // 查询
        HttpGet infoGet = new HttpGet(Contants.INFO_URL + keyword);
//        infoGet.addHeader("Authorization", json.getString("token_type") + " " + json.getString("access_token"));

        // 执行请求
        CloseableHttpResponse infoResponse = httpClient.execute(infoGet);

        String detail = EntityUtils.toString(infoResponse.getEntity(), Contants.UTF_8);
        return detail;
    }

    public static String getCompanyDetail_force(String keyword) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(Contants.TOKEN_URL);
//        httpPost.addHeader("Authorization", "Basic Y2xpZW50YXBwOjEyMzQ1Ng==");

        // 设置请求的参数
//        List<NameValuePair> httpBody = new ArrayList<NameValuePair>();
//        httpBody.add(new BasicNameValuePair("client_id", Contants.CLIENT_ID));
//        httpBody.add(new BasicNameValuePair("grant_type", Contants.GRANT_TYPE));
//        httpBody.add(new BasicNameValuePair("username", Contants.USER_NAME));
//        httpBody.add(new BasicNameValuePair("password", Contants.PASSWORD));
//        httpPost.setEntity(new UrlEncodedFormEntity(httpBody, Contants.UTF_8));

        // token执行请求
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        String responseEntity = EntityUtils.toString(response.getEntity(), Contants.UTF_8);
//        JSONObject json = new JSONObject(responseEntity);

        // 查询
        HttpGet infoGet = new HttpGet(Contants.INFO_URL + keyword + "?force_use_api=true");
//        infoGet.addHeader("Authorization", json.getString("token_type") + " " + json.getString("access_token"));

        // 执行请求
        CloseableHttpResponse infoResponse = httpClient.execute(infoGet);

        String detail = EntityUtils.toString(infoResponse.getEntity(), Contants.UTF_8);
        return detail;
    }
}
