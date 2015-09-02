package com.linju.framework.service.impl;

import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.service.SignatureValidator;
import com.linju.framework.utils.DigestUtil;
import com.linju.framework.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 微信默认签名校验
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
@Service(value = "defaultSignatureValidator")
public class DefaultSignatureValidator implements SignatureValidator {

    private static final Log logger = LogFactory.getLog(DefaultSignatureValidator.class);

    @Override
    public boolean validate(Authentication authentication, WechatConfig wechatConfig) {
        if (StringUtils.isBlank(wechatConfig.getToken())) {
            if (logger.isDebugEnabled()) {
                logger.debug("微信接入校验参数[token]为空，不进行进一步校验");
            }
            return false;
        }
        if (StringUtils.isBlank(authentication.getTimestamp())) {
            if (logger.isDebugEnabled()) {
                logger.debug("微信接入校验参数[timestamp]为空，不进行进一步校验");
            }
            return false;
        }
        if (StringUtils.isBlank(authentication.getNonce())) {
            if (logger.isDebugEnabled()) {
                logger.debug("微信接入校验参数[nonce]为空，不进行进一步校验");
            }
            return false;
        }
        if (StringUtils.isBlank(authentication.getSignature())) {
            if (logger.isDebugEnabled()) {
                logger.debug("微信接入校验参数[signature]为空，不进行进一步校验");
            }
            return false;
        }
        // step 1:将token、timestamp、nonce三个参数进行字典排序
        String[] signatureParams = {wechatConfig.getToken(),
                authentication.getTimestamp(), authentication.getNonce()};
        Arrays.sort(signatureParams);
        // step 2:将排序后的三个参数拼接
        String toString = StringUtil.join(signatureParams, StringUtil.EMPTY_STRING);
        // step 3:将排序后的三个参数进行sha1加密
        String encodedSignature = DigestUtil.sha1Hex(toString);
        return authentication.getSignature().equals(encodedSignature);
    }
}
