package com.linju.framework.model;

import java.util.Date;

/**
 * 公众号接口调用票据
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public class AccessToken {

    // 票据
    private String accessToken;

    // 超时时间（单位：秒）
    private long expireTime;

    // 生成时间(单位：秒)
    private long createTime;

    /**
     * 票据是否有效
     *
     * @return
     */
    public boolean valid() {
        return (new Date().getTime() / 1000) - createTime <= expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AccessToken(String accessToken, long expireTime, long createTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public long getCreateTime() {
        return createTime;
    }
}
