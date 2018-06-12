package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.entity.LfsUser;

import java.util.List;

/**
 * Create by wth on 2018/1/27
 */
public interface LfsUserInfoService {

    /**
     * 用户注册
     * @param accountName
     * @param password
     * @param userNm
     */
    public void register(String accountName,String password,String userNm, Long roleId);

    /**
     * 查询账号是否存在
     */
    public String findByUserName(String userName);

    /**
     * 根据userid获取账号信息
     */
    public List<Object> findByUserId(Long userId);

    /**
     * 根据用户名查询账户ID
     * @param accountName
     * @return
     */
    public List<Object> findUserAccountId(String accountName);

    /**
     * 修改密码
     * @param userName
     * @param passWord
     * @return 0：成功；1：失败
     */
    public void updatePassword(Long userId, String userName, String passWord);

    /**
     *  更新用户详细信息
     */
    public void updateAccount(Long userId, String userNm, String cellPhone, String phone, String address, String mail, Long companyId, String companyNm, String position);

    /**
     * 登录获取用户信息
     * @param userName
     * @param passWord
     * @throws Exception
     */
    public List<Object> loginForGetUserInfo(String userName, String passWord) throws Exception;



    /**
     * 查询用户昵称是否存在
     * @param userNm
     * @return
     */
    public int findUserName(String userNm);

    /**
     * 查询用户名是否存在
     * @param acountNm
     * @return
     */
    public int findAcountName(String acountNm);
}
