package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.CompanyBaseInfo;
import com.cscs.listedfacesys.dto.CompanyRiskInfoData;
import com.cscs.listedfacesys.services.RiskRateManageService;
import com.cscs.listedfacesys.services.UserManageService;
import com.cscs.util.SqlUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * create by hj on 2018/04/27
 */
@Service
public class RiskRateManageServiceImpl implements RiskRateManageService {

    @PersistenceContext
    EntityManager em;

    //查询深圳所有上市公司风险评级信息
    public List<Object> findAllCompyRiskRateInfo(String companyId,String companyNm,int page,int pageSize) {
        String sqlWhere = " ";
        if(companyNm !=null && !"".equals(companyNm) && (!"-1".equals(companyNm))){
            sqlWhere = " and A.COMPANY_NM like '%"+companyNm+"%'";
        }
        String sqlwhereForId = "";
        if(companyId !=null && !"".equals(companyId) && !"-1".equals(companyId)){
            sqlwhereForId = " and A.COMPANY_ID ="+companyId;
        }

        String sql ="SELECT A.COMPANY_ID,\n" +
                "  A.COMPANY_NM,\n" +
                "  A.COMPANY_SNM,\n" +
                "  A.SECURITY_CDS,\n" +
                "  B.COMPANY_ID pkcompanyId,\n" +
                "  B.RATING,\n" +
                "  B.RISKCONTENT\n" +
                "FROM LFS_COMPY_BASICINFO A\n" +
                "LEFT JOIN LFS_COMPY_CREDITRATING B\n" +
                "ON A.COMPANY_CD = B.COMPANY_ID\n"+
                "where 1=1 "+
                 sqlWhere+sqlwhereForId+
                " order by B.UPDT_DT desc";
        if(page !=0 || pageSize!=0){
            sql = SqlUtils.pagingMethod2(page,pageSize,sql);
        }
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public int findAllCompyRiskRateInfoCount(String companyNm) throws Exception {
        String appendCompyNm ="";
        if(companyNm !=null && !"".equals(companyNm) && !"-1".equals(companyNm)){
            appendCompyNm =" AND a.COMPANY_NM LIKE '%"+companyNm+"%'\n";
        }
        String sql ="select count(*)" +
                " FROM LFS_COMPY_BASICINFO A\n" +
                "LEFT JOIN LFS_COMPY_CREDITRATING B\n" +
                "ON A.COMPANY_CD = B.COMPANY_ID\n"+
                "WHERE 1 = 1\n "  + appendCompyNm;
        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    //根据主键pkCompanyId,判断是修改还是新增
    @Transactional
    public int UpdateOrAddForRiskRate(String companyId, String pkCompanyId, int rating, String riskContent) {
        String sql ="";
        if(pkCompanyId !=null && !"".equals(pkCompanyId)){
            //不为空，执行修改操作
            sql="update LFS_COMPY_CREDITRATING set RATING ="+rating+",\n" +
                    "RISKCONTENT ='"+ riskContent +"' where COMPANY_ID = "+pkCompanyId;
        }else{
            //为空，执行新增操作
            sql="insert into LFS_COMPY_CREDITRATING VALUES \n" +
                    "("+companyId+","+rating+",SYSDATE,1,'"+riskContent+"')";
        }
        return em.createNativeQuery(sql).executeUpdate();
    }

    //查询深圳所有上市公司风险类型信息
    public List<Object> findCompanyRiskInfo(String riskId, String companyNm,int page,int pageSize) throws Exception {
        String appendRiskId ="";
        if(riskId !=null && !"".equals(riskId)&& !"-1".equals(riskId)){
            appendRiskId = " AND b.COMPANY_RISK_ID ="+riskId +"\n";
        }
        String appendCompyNm ="";
        if(companyNm !=null && !"".equals(companyNm) && !"-1".equals(companyNm)){
            appendCompyNm =" AND a.COMPANY_NM LIKE '%"+companyNm+"%'\n";
        }
        String sql = "SELECT a.COMPANY_ID,\n" +
                "  a.COMPANY_NM,\n" +
                "  a.COMPANY_SNM,\n" +
                "  a.SECURITY_CDS,\n" +
                "  b.COMPANY_RISK_ID,\n" +
                "  b.RISK_TYPE,\n" +
                "  b.RISK_DETAIL,\n" +
                "  b.RISK_TYPES, \n"+
                "  b.RISK_TARGET \n"+
                "FROM LFS_COMPY_BASICINFO a\n" +
                "LEFT JOIN LFS_COMPANY_RISK b\n" +
                "ON a.COMPANY_ID = b.COMPANY_ID\n" +
                "WHERE 1 = 1\n" +appendRiskId + appendCompyNm+
                " order by b.UPDT_DT desc";
        if(page!=0 || pageSize !=0){
            sql =SqlUtils.pagingMethod2(page,pageSize,sql);
        }

        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public int findCompanyRiskInfoCount(String companyNm) throws Exception {
        String appendCompyNm ="";
        if(companyNm !=null && !"".equals(companyNm) && !"-1".equals(companyNm+"")){
            appendCompyNm =" AND a.COMPANY_NM LIKE '%"+companyNm+"%'\n";
        }
        String sql = "select count(*)" +
                " FROM LFS_COMPY_BASICINFO a\n" +
                "LEFT JOIN LFS_COMPANY_RISK b\n" +
                "ON a.COMPANY_ID = b.COMPANY_ID\n "+
                "WHERE 1 = 1\n "  + appendCompyNm;
        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    //添加企业风险信息
    @Transactional
    public int addCompanyRiskInfo(CompanyRiskInfoData data) {
        String sql = "insert into  LFS_COMPANY_RISK values(SEQ_LFS_COMPANY_RISK.nextval," +
                data.getCompanyId() +",'"+data.getCompanySnm()+"',"+data.getRiskType()+",'"+
                data.getRiskTarget()+"','"+data.getRiskTypes()+"','"+data.getRisksDetail()+"',"+
                "1,SYSDATE)";
        return em.createNativeQuery(sql).executeUpdate();
    }

    //修改企业风险信息
    @Transactional
    public int updateCompanyRiskInfo(CompanyRiskInfoData data) {
        String sql ="update LFS_COMPANY_RISK set COMPANY_ID="+data.getCompanyId()+"\n"+
                ",COMPANY_SNM ='"+data.getCompanySnm()+"',RISK_TYPE ="+data.getRiskType()+"\n"+
                ",RISK_TARGET='"+data.getRiskTarget()+"',RISK_TYPES='"+data.getRiskTypes()+"\n"+
                "',RISK_DETAIL='"+data.getRisksDetail()+"',UPDT_DT=SYSDATE where \n"+
                "COMPANY_RISK_ID ="+data.getCompanyRiskId()
                ;
        return em.createNativeQuery(sql).executeUpdate();
    }

    //删除企业风险信息
    @Transactional
    public int delCompanyRiskInfo(String compyRiskId) {
        String sql ="delete from LFS_COMPANY_RISK where COMPANY_RISK_ID ="+compyRiskId;
        return em.createNativeQuery(sql).executeUpdate();
    }


    //查询企业信息记录条数
    @Override
    public int findCompanyBaseInfoCount(String companyNm) throws Exception {
        String appendCompyNm ="";
        if(companyNm !=null && !"".equals(companyNm) && !"-1".equals(companyNm+"")){
            appendCompyNm =" AND a.COMPANY_NM LIKE '%"+companyNm+"%'\n";
        }
        String sql = "select count(*)" +
                " FROM LFS_COMPY_BASICINFO a\n "+
                "WHERE 1 = 1\n "  + appendCompyNm;
        return Integer.valueOf(em.createNativeQuery(sql).getSingleResult().toString());
    }

    //查询企业信息记录
    @Override
    public List<Object> findCompanyBaseInfo(String companyId, String companyNm, int page, int pageSize) throws Exception {
        String appendRiskId ="";
        if(companyId !=null && !"".equals(companyId)&& !"-1".equals(companyId+"")){
            appendRiskId = " AND a.COMPANY_ID ="+companyId +"\n";
        }
        String appendCompyNm ="";
        if(companyNm !=null && !"".equals(companyNm) && !"-1".equals(companyNm+"")){
            appendCompyNm =" AND a.COMPANY_NM LIKE '%"+companyNm+"%'\n";
        }
        String sql = "SELECT " +
                "a.COMPANY_ID," +
                "a.COMPANY_CD," +
                "a.COMPANY_NM," +
                "a.COMPANY_SNM," +
                "a.REG_ADDR," +
                "a.UPDT_DT," +
                "a.SECURITY_CDS,"+
                "a.POSITIONX," +
                "a.POSITIONY " +
                    "FROM LFS_COMPY_BASICINFO a "+
                    "WHERE 1 = 1\n" +appendRiskId + appendCompyNm+
                " order by a.UPDT_DT desc";
        if(page!=0 || pageSize !=0){
            sql =SqlUtils.pagingMethod2(page,pageSize,sql);
        }
        return em.createNativeQuery(sql).getResultList();
    }

    //更新企业基本信息
    @Transactional
    public int upDateCompanyBaseInfo(CompanyBaseInfo data) {
        String sql ="update LFS_COMPY_BASICINFO set REG_ADDR ='"+data.getRegAddr()+"',POSITIONX ="+data.getPositionX()+"\n"+
                ",POSITIONY='"+data.getPositionY()+"',UPDT_DT=SYSDATE where \n"+
                "COMPANY_ID ="+data.getCompanyId();
        return em.createNativeQuery(sql).executeUpdate();
    }
}
