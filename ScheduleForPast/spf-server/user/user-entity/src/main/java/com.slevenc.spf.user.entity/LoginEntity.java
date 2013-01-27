package com.slevenc.spf.user.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午8:27
 */
@Entity
public class LoginEntity {
    public static int STATE_ENABLE = 1;
    public static int STATE_DISABLE = 0;

    @Id
    private String userid;
    private int state;
    private String username;
    private String password;
    private Date createTime;
    private Date modifyTime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
