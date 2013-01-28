package com.slevenc.spf.user.dao;

import com.slevenc.spf.user.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午9:49
 */
public interface UserDao {

    public String getUserIdByUsernameAndPassword(String username, String password);

    public User getUserByUserId(String userId);

    public User getUserByUserName(String username);

    public String getPasswordByUserId(String userId);

    public void changeUserPassword(String userId,String Password);

    public void insertUser(User user);

    public void insertLoginEntity(String userId,String username,String password);
}
