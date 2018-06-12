package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.CompanySearch;
import com.cscs.listedfacesys.dto.SearchCompletion;
import com.cscs.listedfacesys.dto.SearchOutObj;
import com.cscs.listedfacesys.entity.CompyBasicinfo;
import com.cscs.listedfacesys.services.SearchWarningService;
import com.cscs.util.Contants;
import com.cscs.util.SolrUtil;
import com.cscs.util.SqlUtils;
import com.cscs.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.*;

/**
 * create by wuzy on 2018/03/14
 * 关键字查询
 */
@Service
public class SearchWarningServiceImpl implements SearchWarningService {

    @PersistenceContext
    EntityManager em;

    private final static String COMPANYNMNOTNULL = "公司名称";
    private final static String LEGREPRESENTNOTNULL = "法定代表人";
    private final static String BLNUMBNOTNULL = "营业执照号码";
    private final static String SECURITYCDNULL = "上市代码";

    @Override
    public List<Object> basicSearch(String keyword, int page) {

        String sql = " SELECT cb.COMPANY_ID ,\n" +
        "                cb.COMPANY_SNM,\n" +
                "                '-1' TITLE,\n" +
                "                '-1' TYPE_NAME,\n" +
                "                '' NOTICE_DT,\n" +
                "                -1 infocd,\n" +
                "                -99999 importance,\n" +
                "                '-1' flg,\n" +
                "                cb.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO cb \n" +
                "   WHERE cb.COMPANY_NM LIKE '%" + keyword + "%'\n" +
                " OR INSTR(cb.SECURITY_CDS,'" + keyword + "') > 0 \n";

        sql = SqlUtils.pagingMethod2(page, Contants.PAGESIZE, sql);

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public int basicSearchCount(String keyword) {

        String sql = "SELECT COUNT(*) FROM ( \n" +
                " SELECT cb.COMPANY_ID \n" +
                " FROM LFS_COMPY_BASICINFO cb \n" +
                "   WHERE cb.COMPANY_NM LIKE '%" + keyword + "%'\n" +
                " OR INSTR(cb.SECURITY_CDS,'" + keyword + "') > 0 \n" +
                " )";

        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }



    private SearchCompletion newSearchCompletion(String strkeyword, String companyNm,
                                                 String companySnm, String legRepresent, String blnumb, String companyId, String companyCd, Set<String> oldName) {
        SearchCompletion comsearch = new SearchCompletion();
        if (!StringUtils.isEmpty(companyNm) && companyNm.contains(strkeyword)) {
            comsearch.setType(COMPANYNMNOTNULL);
        } else if (!StringUtils.isEmpty(companySnm) && companySnm.contains(strkeyword)) {
            comsearch.setType(COMPANYNMNOTNULL);
        } else if (!StringUtils.isEmpty(legRepresent) && legRepresent.contains(strkeyword)) {
            comsearch.setType(LEGREPRESENTNOTNULL);
        } else if (!StringUtils.isEmpty(blnumb) && blnumb.contains(strkeyword)) {
            comsearch.setType(BLNUMBNOTNULL);
        } else {
            comsearch.setType(SECURITYCDNULL);
        }
        comsearch.setCompanyNm(companyNm);
        comsearch.setCompanyId(companyId);
        comsearch.setCompanyCd(companyCd);
        comsearch.setOldname(oldName);
        return comsearch;
    }

    //查找关系（个人）
    @Override
    public List<String> findRelationShipByName(String keyword) {
        try {
            List<String> namelist = new ArrayList<String>();
            boolean check_for_db = false;
            LinkedHashMap<String, String> fqMap = new LinkedHashMap<String, String>();
            fqMap.put("tablename", "rp");
            String queryPhrase = SolrUtil.getQueryPhrase("id", keyword, fqMap, Contants.PAGESIZE/2, 0);
            String uri = Contants.RELATIONSHIP_URL + queryPhrase;
            HttpGet fuzzyGet = new HttpGet(uri);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(3000).setSocketTimeout(3000).build();
            fuzzyGet.setConfig(requestConfig);
            CloseableHttpResponse fuzzyResponse = httpClient.execute(fuzzyGet);
            String fuzzyList = EntityUtils.toString(fuzzyResponse.getEntity(), Contants.UTF_8);
            JSONObject result = new JSONObject(fuzzyList);

            if (result.has("response")) {
                JSONObject response = (JSONObject) result.get("response");
                if (response.has("docs")) {
                    JSONArray docs = response.getJSONArray("docs");
                    int length = docs.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject obj = (JSONObject) docs.get(i);
                        namelist.add(obj.has("id") ? obj.get("id").toString() : null);
                    }
                } else {
                    check_for_db = true;
                }
            } else {
                check_for_db = true;
            }

            if (check_for_db) {
                throw new IOException();
            }

            return namelist;
        } catch (IOException e) {
            System.out.println("solr timeout");
            String sql = "SELECT NAME FROM\n" +
                    "（SELECT DISTINCT PERSON_NM NAME FROM "+Contants.SCHEMA_NAME+"COMPY_MANAGELEVEL WHERE PERSON_NM LIKE '%" + keyword
                    + "%' AND ROWNUM <= 5\n" +
                    "UNION\n" +
                    "SELECT DISTINCT SHAREHDNAME NAME FROM "+Contants.SCHEMA_NAME+"COMPY_SHAREHOLDER WHERE SHAREHD_TYPEID = '2911' AND SHAREHDNAME LIKE '%"
                    + keyword + "%' AND ROWNUM <= 5\n" +
                    ")\n" +
                    "WHERE ROWNUM <= 5";
            return em.createNativeQuery(sql).getResultList();
        }
    }

    //查找关系（公司）
    @Override
    public List<Object> findRelationShipByCompy(String keyword) {
        try {
            List<Object> companylist = new ArrayList<Object>();
            boolean check_for_db = false;
            String queryPhrase = SolrUtil.getQueryPhrase(keyword, Contants.PAGESIZE/2, 0);
            String uri = Contants.COMPANY_INDEX_URL + queryPhrase;
            HttpGet fuzzyGet = new HttpGet(uri);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(3000).setSocketTimeout(3000).build();
            fuzzyGet.setConfig(requestConfig);
            CloseableHttpResponse fuzzyResponse = httpClient.execute(fuzzyGet);
            String fuzzyList = EntityUtils.toString(fuzzyResponse.getEntity(), Contants.UTF_8);
            JSONObject result = new JSONObject(fuzzyList);

            if (result.has("response")) {
                JSONObject response = (JSONObject) result.get("response");
                if (response.has("docs")) {
                    JSONArray docs = response.getJSONArray("docs");
                    int length = docs.length();
                    for (int i = 0; i < length; i++) {
                        String[] rs = new String[2];
                        JSONObject obj = (JSONObject) docs.get(i);
                        rs[0] = obj.has("company_cd") ? obj.get("id").toString() : null;
                        rs[1] = obj.has("company_nm") ? obj.get("company_nm").toString() : null;
                        companylist.add(rs);
                    }
                } else {
                    check_for_db = true;
                }
            } else {
                check_for_db = true;
            }

            if (check_for_db) {
                throw new IOException();
            }

            return companylist;
        } catch (IOException e) {
            System.out.println("solr timeout");
            String sql =
                    "SELECT * FROM(SELECT COMPANY_ID,CLENS_COMPANY_NM FROM LFS_COMPY_BASICINFO WHERE is_del = 0 and \n"
                            +
                            "(COMPANY_NM LIKE '%" + keyword + "%'\n" +
                            "OR COMPANY_SNM LIKE '%" + keyword + "%'\n" +
                            "OR CLENS_COMPANY_NM LIKE '%" + keyword + "%'\n" +
                            "OR FEN_NM LIKE '%" + keyword + "%')" +
                            "ORDER BY NVL(REG_CAPITAL,-1) DESC,CLENS_COMPANY_NM)\n" +
                            "WHERE ROWNUM <= 5";
            return em.createNativeQuery(sql).getResultList();
        }
    }

    @Override
    public List<SearchCompletion> findCompanySearchinfosForTitle(String keyword) {
        //空格
        String space = "\t";
        String keywordSplit[] = keyword.split(space);
        List<SearchCompletion> companySearch = new ArrayList<SearchCompletion>();

        for (String strkeyword : keywordSplit) {
            String sql =
                    "select A.*, ROWNUM RN FROM (SELECT * FROM LFS_COMPY_BASICINFO c  WHERE (" +
                            " c.LEG_REPRESENT = '" + strkeyword + "' or c.COMPANY_SNM like '%" + strkeyword
                            + "%'or c.COMPANY_NM like '%" + strkeyword +
                            "%' or INSTR(c.SECURITY_CDs,'" + strkeyword
                            + "')>0) order by  reg_capital DESC) A WHERE ROWNUM <= 10";
            // System.out.println(sql);
            List<CompyBasicinfo> infoCompyBasic = em.createNativeQuery(sql, CompyBasicinfo.class)
                    .getResultList();
            for (CompyBasicinfo req : infoCompyBasic) {

                //公司名称，法定代表人，营业执照号码
                String companyNm = req.getCompanyNm();
                String companySnm = req.getCompanySnm();
                String legRepresent = req.getLegRepresent();
                String blnumb = req.getBlnumb();
                String companyId = String.valueOf(req.getCompanyId());
                String companyCd = req.getCompanyCd();
                SearchCompletion comsearch = newSearchCompletion(strkeyword, companyNm, companySnm,
                        legRepresent, blnumb, companyId, companyCd, null);
                companySearch.add(comsearch);
            }
        }
        return companySearch;
    }
}
