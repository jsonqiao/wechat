package com.linju.framework.service;

import com.linju.framework.exception.AccessTokenNotFoundException;
import com.linju.framework.model.AccessToken;
import com.linju.framework.model.WechatConfig;

/**
 * 微信AccessToken中控服务接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public interface AccessTokenService {

    /**
     * 刷新中控服务器中的公众号accessToken
     *
     * @param wechatConfig
     */
    public void refresh(WechatConfig wechatConfig);

    /**
     * 通过公众号的配置常量获取公众号的accessToken
     *
     * @param wechatConfig
     *
     * @return accessToken 公众号accessToken票据实体
     *
     * @throws AccessTokenNotFoundException
     */
    public AccessToken getAccessToken(WechatConfig wechatConfig) throws AccessTokenNotFoundException;
}
