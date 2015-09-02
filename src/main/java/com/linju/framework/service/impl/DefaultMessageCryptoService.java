package com.linju.framework.service.impl;

import com.linju.framework.constant.BaseKey;
import com.linju.framework.core.crypto.AesException;
import com.linju.framework.core.crypto.WXBizMsgCrypt;
import com.linju.framework.core.crypto.WechatEncryptType;
import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.service.MessageCryptoService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jsonqiao
 *
 * @date 2015/6/26
 */
@Service
public class DefaultMessageCryptoService implements MessageCryptoService {

    private static final Log logger = LogFactory.getLog(DefaultMessageCryptoService.class);

    @Override
    public String encrypt(WechatConfig wechatConfig, Authentication authentication, HttpServletRequest request, String unencryptMsg) {
        try {
            WXBizMsgCrypt crypt = new WXBizMsgCrypt(wechatConfig.getToken(), wechatConfig.getEncodingAESKey(), wechatConfig.getAppId());
            final String messageSignature = request.getParameter(BaseKey.MESSAGE_SIGNATURE);
            if (StringUtils.isBlank(messageSignature)) {
                logger.error("微信消息签名参数[" + BaseKey.MESSAGE_SIGNATURE + "]为空，无法进行解密， 返回null");
                return null;
            }
            return crypt.encryptMsg(unencryptMsg, authentication.getTimestamp(), authentication.getNonce());
        } catch (AesException e) {
            logger.error("进行微信消息解密的过程中出现异常[AesException]，异常详细信息：" + e);
        }
        return null;
    }

    @Override
    public String decrypt(WechatConfig wechatConfig, Authentication authentication, HttpServletRequest request, String undecryptMsg) {
        try {
            WXBizMsgCrypt crypt = new WXBizMsgCrypt(wechatConfig.getToken(), wechatConfig.getEncodingAESKey(), wechatConfig.getAppId());
            final String messageSignature = request.getParameter(BaseKey.MESSAGE_SIGNATURE);
            if (StringUtils.isBlank(messageSignature)) {
                logger.error("微信消息签名参数[" + BaseKey.MESSAGE_SIGNATURE + "]为空，无法进行解密， 返回null");
                return null;
            }
            return crypt.decryptMsg(messageSignature, authentication.getTimestamp(), authentication.getNonce(), undecryptMsg);
        } catch (AesException e) {
            logger.error("进行微信消息解密的过程中出现异常[AesException]，异常详细信息：" + e);
        }
        return null;
    }

    @Override
    public boolean encryptEnable(HttpServletRequest request) {
        final String encryptType = request.getParameter(BaseKey.ENCRYPT_TYPE);
        if (StringUtils.isBlank(encryptType)) {
            return false;
        }
        return WechatEncryptType.isEncryptEnabled(encryptType);
    }
}
