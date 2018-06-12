package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.entity.CompyBasicinfo;
import com.cscs.listedfacesys.dto.SearchCompletion;
import com.cscs.listedfacesys.entity.Security;
import com.cscs.listedfacesys.services.CompanyInfoService;
import com.cscs.util.Contants;
import com.cscs.util.StringUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hj on 2018/2/24.
 */
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {



    @PersistenceContext
    EntityManager em;

    private final static String COMPANYNMNOTNULL = "公司名称";
    private final static String LEGREPRESENTNOTNULL = "法定代表人";
    private final static String BLNUMBNOTNULL = "营业执照号码";
    private final static String SECURITYCDNULL = "上市代码";

    @Override
    public List<Object> getCompanyBasicInfo(Long id) {
        String sql =
                "SELECT A.COMPANY_NM, A.FOUND_DT, A.REG_CAPITAL, B.CONSTANT_NM AS ORG_FORM_NM, \n" +
                        "A.LEG_REPRESENT, A.EMPLOY_NUM, A.REG_ADDR, A.OFFICE_ADDR, A.COMPANY_PH, \n" +
                        "A.COMPANY_WEB,A.BUSIN_SCOPE,A.MAIN_BUSIN,A.COMPANY_SNM,A.security_cds \n"
                        +
                        "FROM LFS_COMPY_BASICINFO A \n" +
                        "LEFT JOIN LKP_CHARCODE B ON A.ORG_FORM_ID = B.CONSTANT_ID AND B.CONSTANT_TYPE = '2' AND B.ISDEL = 0 \n" +
                        "WHERE A.COMPANY_ID = " + id + " AND A.IS_DEL = 0";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }

    @Override
    public String getAffilparty(Long id) {
        String sql = "SELECT wm_concat(AFFIL_PARTY) FROM(\n" +
                "SELECT A.AFFIL_PARTY,max(RPT_DT) over (partition by COMPANY_ID) as last_rpt_dt ,RPT_DT\n" +
                "FROM "+ Contants.SCHEMA_NAME+"COMPY_AFFILPARTY A LEFT JOIN LKP_CHARCODE B\n" +
                "ON A.RELATION_TYPE_ID = B.CONSTANT_ID \n" +
                "WHERE A.COMPANY_ID = " + id + " AND B.CONSTANT_NM='实际控制人') WHERE RPT_DT =last_rpt_dt";
        List<String> query = em.createNativeQuery(sql).getResultList();
        return query.size() > 0 ? query.get(0) : "";
    }





    @Override
    public List<Object> findCompyManagelevel(Long id) {
        String sql = "SELECT c.CONSTANT_NM AS PSTN_NM, a.PERSON_NM, FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(a.BIRTH_YEAR, 'yyyy'))/12) AS AGE,\n" +
                " DECODE(a.SEX, 0, '男', 1, '女', 2, '未知') AS SEX, a.COUNTRY_CD, b.CONSTANT_NM AS HIGHEST_DEGREE, TO_CHAR(a.RESUME) \n" +
                "FROM "+Contants.SCHEMA_NAME+"COMPY_MANAGELEVEL a \n" +
                "LEFT JOIN LKP_CHARCODE b ON b.CONSTANT_ID = a.HIGHEST_DEGREE AND b.CONSTANT_TYPE = '17' AND b.ISDEL = 0 \n" +
                "LEFT JOIN LKP_CHARCODE c ON c.CONSTANT_ID = a.PSTN_CD AND c.CONSTANT_TYPE = '6' AND c.ISDEL = 0 \n" +
                "INNER JOIN (SELECT MAX(BOARD_SESSION) BOARD_SESSION FROM "+Contants.SCHEMA_NAME+"COMPY_MANAGELEVEL WHERE COMPANY_ID = " + id + "\n" +
                "AND ISDEL = 0 AND STATUS = 2) d ON a.BOARD_SESSION = d.BOARD_SESSION \n" +
                "AND a.COMPANY_ID = " + id + " AND a.ISDEL = 0 AND a.STATUS = 2 \n" +
                "ORDER BY instr('100101,110101,010302,010503,010401,010202,010101,100101,110110,110204,110101,110104,110102,110103,110104,110105',C.CONSTANT_CD) DESC";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }



    private SearchCompletion newSearchCompletion(String strkeyword, String companyNm,
                                                 String companySnm, String legRepresent, String blnumb, String companyId, String companyCd, Set<String> oldName) {
        SearchCompletion comsearch = new SearchCompletion();
        if (!StringUtil.isEmpty(companyNm) && companyNm.contains(strkeyword)) {
            comsearch.setType(COMPANYNMNOTNULL);
        } else if (!StringUtil.isEmpty(companySnm) && companySnm.contains(strkeyword)) {
            comsearch.setType(COMPANYNMNOTNULL);
        } else if (!StringUtil.isEmpty(legRepresent) && legRepresent.contains(strkeyword)) {
            comsearch.setType(LEGREPRESENTNOTNULL);
        } else if (!StringUtil.isEmpty(blnumb) && blnumb.contains(strkeyword)) {
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



    //企业股东前10取得
    public List<Object> findShareholderTop10(Long id) {
        String sql = "SELECT SHAREHDNAME,CONSTANT_NM,SHAREHD_RATIO,FROZEN_TOTAL_RATIO,SHAREHD_TYPEID,SHAREHD_ID,SHAREHD_NUM\n" +
                "FROM (SELECT MAX(TO_CHAR(END_DT)) OVER (PARTITION BY A.COMPANY_ID) AS LATEST_END_DT,\n" +
                "            TO_CHAR(A.END_DT)END_DT,A.RANK,A.SHAREHD_TYPEID,A.SHAREHD_RATIO,A.SHAREHDNAME,B.FROZEN_TOTAL_RATIO,A.SHAREHD_ID,A.SHAREHD_NUM\n" +
                "        FROM "+Contants.SCHEMA_NAME+"COMPY_SHAREHOLDER A\n" +
                "        LEFT JOIN (SELECT SHAREHD_ID,FROZEN_TOTAL_RATIO FROM (\n" +
                "                  SELECT ROW_NUMBER() OVER (PARTITION BY COMPANY_ID,SHAREHD_ID ORDER BY NOTICE_DT DESC)ROW_NUM,SHAREHD_ID,FROZEN_TOTAL_RATIO\n" +
                "                  FROM "+Contants.SCHEMA_NAME+"COMPY_FROZENSHARE WHERE COMPANY_ID = " + id + ")\n" +
                "                  WHERE ROW_NUM = 1 AND SHAREHD_ID IS NOT NULL)B\n" +
                "                  ON A.SHAREHD_ID = B.SHAREHD_ID\n" +
                "        WHERE  A.COMPANY_ID = " + id + " AND A.RANK IS NOT NULL)B\n" +
                "LEFT JOIN LKP_CHARCODE C ON  B.SHAREHD_TYPEID = C.CONSTANT_ID AND C.CONSTANT_TYPE = 13\n" +
                "WHERE LATEST_END_DT = END_DT AND RANK < 11 ORDER BY SHAREHD_RATIO DESC";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }

    //股权质押取得（不包含10大股东）
    @Override
    public List<Object> findCompyFrozemshare(Long id) {
        String sql =
                "SELECT SHAREHD_NM,TRUNC(FROZEN_TOTAL_RATIO/FROZEN_RATIO*100,4),FROZEN_TOTAL_RATIO,SHAREHD_ID FROM (\n" +
                        "SELECT ROW_NUMBER() OVER (PARTITION BY COMPANY_ID,SHAREHD_ID ORDER BY NOTICE_DT DESC)ROW_NUM,\n" +
                        "SHAREHD_NM,FROZEN_RATIO,FROZEN_TOTAL_RATIO,SHAREHD_ID\n" +
                        "FROM "+Contants.SCHEMA_NAME+"COMPY_FROZENSHARE WHERE COMPANY_ID = " + id + ") \n" +
                        "WHERE ROW_NUM = 1 AND SHAREHD_NM NOT IN (\n" +
                        "  SELECT SHAREHDNAME FROM(\n" +
                        "  SELECT MAX(TO_CHAR(END_DT)) OVER (PARTITION BY COMPANY_ID) AS LATEST_END_DT,TO_CHAR(END_DT)END_DT,SHAREHDNAME\n" +
                        "  FROM "+Contants.SCHEMA_NAME+"COMPY_SHAREHOLDER WHERE COMPANY_ID = " + id + "\n" +
                        "AND RANK IS NOT NULL)WHERE LATEST_END_DT = END_DT)";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }

    @Override
    public List<Object> companyBasicInfo(String companyId) {
        String sql = "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                "WHERE  A.COMPANY_ID in (" + companyId + ")";
        return em.createNativeQuery(sql).getResultList();
    }

    //查询公司信息
    public List<Object> queryCompanyInfo(String companyNmOrSecurityCd) {
        String sql ="SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_NM, \n" +
                "  A.COMPANY_SNM,\n" +
                "  A.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                " where A.COMPANY_NM like '%"+companyNmOrSecurityCd+"%' or A.SECURITY_CDs like '%" +companyNmOrSecurityCd +"%'";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> queryNotAttentionCompanyInfo(String userId,String companyNmOrSecurityCd) {
        String sql ="SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_NM, \n" +
                "  A.COMPANY_SNM,\n" +
                "  A.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                " left JOIN USER_FOCUS f ON A.COMPANY_ID = f.FOCUS_ID\n" +
                " AND f.USER_ID ="+ userId +"\n" +
                " where f.FOCUS_ID is null and (A.COMPANY_NM like '%"+companyNmOrSecurityCd+"%' or A.SECURITY_CDs like '%" +companyNmOrSecurityCd +"%')";
        return em.createNativeQuery(sql).getResultList();
    }

    // 获取主板企业：按照申万行业分类的二级行业;新三板企业：按证监会行业分类三级行业分类
    @Override
    public List<Object> findIndustryFlg(Long id) {
        String sql = "SELECT b.COMPANY_ID FROM lfs_compy_basicinfo b \n" +
                "INNER join COMPY_SECURITY_XW xw on b.company_id = xw.company_id \n" +
                "INNER join SECURITY a on xw.SECINNER_ID = a.SECINNER_ID \n" +
                "INNER JOIN (select constant_id as SECURITY_TYPE_ID, constant_nm as SECURITY_TYPE from LKP_CHARCODE where constant_type = 401 and isdel = 0) lkp2 \n" +
                "ON a.SECURITY_TYPE_ID = lkp2.SECURITY_TYPE_ID \n" +
                "WHERE lkp2.SECURITY_TYPE= '三板股' AND b.is_del = 0 and a.isdel = 0 and b.COMPANY_ST = 1 and b.country = 'CN' AND a.LIST_ST != 2 AND b.COMPANY_ID = " + id;
        return em.createNativeQuery(sql).getResultList();
    }

    // 获取申万一级行业和二级行业或者证监会行业
    @Override
    public List<Object> findSwIndustry(Long id, String cd) {
        String sql =
                "SELECT ind.ID,ind.NAME,ind.INDUSTRY_LEVEL,ind.PARENT_IND_SID FROM INDUSTRY ind INNER JOIN \n"
                        +
                        "(SELECT v.INDUSTRY_LEVEL1, v.INDUSTRY_LEVEL2, v.INDUSTRY_LEVEL3 \n" +
                        "  FROM VW_COMPY_INDUSTRY v \n" +
                        "  WHERE v.COMPANY_ID = " + id + " AND v.SYSTEM_CD = '" + cd
                        + "') m ON m.INDUSTRY_LEVEL1 = ind.ID or m.INDUSTRY_LEVEL2 = ind.ID or m.INDUSTRY_LEVEL3 = ind.ID" +
                        " order by ind.INDUSTRY_LEVEL";
        List<Object> query = em.createNativeQuery(sql).getResultList();
        return query;
    }

    // 判断是否属于深圳市上市公司
    @Override
    public List<Object> findExistCompy(Long companyId) {
        String sql = "SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_SNM,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.SECURITY_CDs SECURITY_CD\n" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                "WHERE  A.COMPANY_ID = " + companyId + " ";
        return em.createNativeQuery(sql).getResultList();
    }
}
