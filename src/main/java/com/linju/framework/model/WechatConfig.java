package com.linju.framework.model;

import com.linju.framework.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 微信公众号配置类
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public class WechatConfig extends BaseDomain {

    // 应用Id
    private String appId;

    // 应用密钥
    private String appSecret;

    // 应用服务器url
    private String url;

    // 应用令牌
    private String token;

    // 消息加密解密密钥
    private String encodingAESKey;

    // 消息加密方式
    private Integer encodingType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public Integer getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(Integer encodingType) {
        this.encodingType = encodingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WechatConfig that = (WechatConfig) o;

        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
        if (appSecret != null ? !appSecret.equals(that.appSecret) : that.appSecret != null) return false;
        /*
        if (encodingAESKey != null ? !encodingAESKey.equals(that.encodingAESKey) : that.encodingAESKey != null)
            return false;
        if (encodingType != null ? !encodingType.equals(that.encodingType) : that.encodingType != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        */
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (appSecret != null ? appSecret.hashCode() : 0);
        /*
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (encodingAESKey != null ? encodingAESKey.hashCode() : 0);
        result = 31 * result + (encodingType != null ? encodingType.hashCode() : 0);
        */
        return result;
    }
}
