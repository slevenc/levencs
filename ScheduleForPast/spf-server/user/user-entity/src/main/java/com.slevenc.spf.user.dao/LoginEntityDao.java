package com.slevenc.spf.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午9:49
 */
public interface LoginEntityDao {

    public Long getUserIdByUsernameAndPassword(String username, String password);
}
