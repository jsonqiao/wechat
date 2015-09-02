package com.linju.framework.service;

import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;

/**
 * 微信签名校验
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
public interface SignatureValidator {

    /**
     * 进行微信签名校验
     *
     * @param authentication 校验数据
     *
     * @param wechatConfig 微信公众号基础数据
     *
     * @return 校验成功返回true，校验失败返回false
     */
    public boolean validate(Authentication authentication, WechatConfig wechatConfig);
}
