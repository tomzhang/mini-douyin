package com.gjw.service;

import com.gjw.pojo.Users;

/**
 * Created by gjw19 on 2018/6/16.
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 保存用户（用户注册）
     * @param user
     */
    public void saveUser(Users user);

    /**
     * 用户登录，根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);

    /**
     * 用户修改信息
     * @param user
     */
    public void updateUserInfo(Users user);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);

}