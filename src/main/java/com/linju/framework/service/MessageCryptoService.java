package com.linju.framework.service;

import com.linju.framework.constant.BaseKey;
import com.linju.framework.core.crypto.WechatEncryptType;
import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信消息加密以及解析服务接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/26
 */
public interface MessageCryptoService {

    /**
     * 对微信消息进行加密
     *
     * @param wechatConfig 公众号基础信息
     *
     * @param authentication 公众号认证信息
     *
     * @param request 请求
     *
     * @param unencryptMsg 欲进行加密的消息
     *
     * @return 加密后的消息, 加密过程出现异常时返回null，否则返回正常的加密结果
     */
    public String encrypt(WechatConfig wechatConfig, Authentication authentication, HttpServletRequest request, String unencryptMsg);

    /**
     * 对加密微信消息进行解密
     *
     * @param wechatConfig 公众号基础信息
     *
     * @param authentication 公众号认证信息
     *
     * @param request 请求
     *
     * @param undecryptMsg 欲进行解密的消息
     *
     * @return 解密结果，解密过程中出现异常时返回null，否则返回正常的解密结果
     */
    public String decrypt(WechatConfig wechatConfig, Authentication authentication, HttpServletRequest request, String undecryptMsg);

    /**
     * 判断微信公众号是否启用了消息加密
     *
     * @param request
     *
     * @return
     */
    public boolean encryptEnable(HttpServletRequest request);

}
