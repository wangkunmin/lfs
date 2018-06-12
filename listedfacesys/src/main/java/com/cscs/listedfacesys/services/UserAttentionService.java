package com.cscs.listedfacesys.services;


import com.cscs.listedfacesys.dto.UserFollowCompyOutData;
import com.cscs.listedfacesys.dto.UserFollowInData;

import java.util.List;
import java.util.Set;

/**
 * Create by wzy on 2018/2/1
 * 用户关注
 */
public interface UserAttentionService {

    /**
     * 查询用户关注公司ID列表
     * @param userId
     * @return
     */
    public Set<String> searchAllCompy(Long userId);

    /**
     * 添加用户关注
     * @param userId
     * @param companyId
     * @return
     */
    public void addAttention(Long userId, Long companyId, String companyNm, Long focusType);

    /**
     * 取消用户关注
     * @param userId
     * @param companyId
     * @return
     */
    public void deleteAttention(Long userId, Long companyId);

    /**
     * 查询该用户未关注的公司信息
     * @param userId
     * @param companyNmOrSecuritycd  传了公司名称，根据名称模糊查询，不传查询所有
     * @return
     */
    public List<Object> findNotFollowCompyInfo(int page,int pageSize,Long userId, String companyNmOrSecuritycd);

    /**
     * 查询该用户已关注的公司信息
     * @param userId
     * @param companyNm  传了公司名称，根据名称模糊查询，不传查询所有
     * @return
     */
    public List<Object> findFollowedCompyInfo(int page,int pageSize,Long userId, String companyNm);

    /**
     * 查询该用户未关注的公司信息总数
     * @param userId
     * @param companyNmOrSecuritycd  传了公司名称，根据名称模糊查询，不传查询所有
     * @return
     */
    public int findNotFollowCompyInfoCount(Long userId, String companyNmOrSecuritycd);

    /**
     * 查询该用户已关注的公司信息总数
     * @param userId
     * @param companyNm  传了公司名称，根据名称模糊查询，不传查询所有
     * @return
     */
    public int findFollowedCompyInfoCount(Long userId, String companyNm);


    /**
     * 批量添加用户关注
     * @param list
     * @return
     */
    public void batchAddAttention(long userId,List<UserFollowInData> list);

    /**
     * 批量取消用户关注
     * @param userId
     * @param companyIds 多个公司ID
     * @return
     */
    public void batchDeleteAttention(Long userId, String companyIds);

    /**
     * 查询用户已关注的公司列表
     * @param userId
     * @return
     */
    public List<Object> findAllFollowedCompyInfo(Long userId);

    /**
     * 查询用户是否已关注某公司
     * @param userId
     * @param companyId
     * @return
     */
    public boolean findFollowedCompyInfoById(Long userId,String companyId);
}
