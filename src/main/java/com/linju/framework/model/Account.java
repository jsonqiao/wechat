package com.linju.framework.model;

import java.util.Date;

/**
 * @author jsonqiao
 *
 * @date 2015/6/18
 */
public class Account extends BaseDomain {

    private String username;

    private String password;

    // 头像
    private String photoUrl;

    // 职务
    private String duty;

    private Date lastLoginTime;

    private String lastLoginAddress;

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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
