package com.linju.framework.service;

import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建微信端传送的消息
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
public interface MessageBuilder {

    /**
     * 创建微信消息
     *
     * @param wechatConfig 微信公众号配置信息
     *
     * @param authentication 微信认证相关的数据信息
     *
     * @param request HTTP请求
     *
     * @return 返回对应的微信消息
     */
    public Message build(WechatConfig wechatConfig, Authentication authentication, HttpServletRequest request);
}
