package com.slevenc.spf.user.entity;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-28
 * Time: 下午9:23
 */
public class User {

    @Id
    public String userId;

    public String nickname;

    public String userEmail;

    public Date createTime;

    public Date modifyTime;


}
