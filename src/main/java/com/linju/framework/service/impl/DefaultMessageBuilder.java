package com.linju.framework.service.impl;

import com.linju.framework.constant.BaseKey;
import com.linju.framework.constant.MessageType;
import com.linju.framework.core.crypto.WechatEncryptType;
import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.service.Message;
import com.linju.framework.service.MessageBuilder;
import com.linju.framework.service.MessageCryptoService;
import com.linju.framework.service.message.SubscribeEvent;
import com.linju.framework.service.message.TextMessage;
import com.linju.framework.utils.MessageUtil;
import com.linju.framework.utils.XmlUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 默认的消息构建
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
@Service("defaultMessageBuilder")
public class DefaultMessageBuilder implements MessageBuilder {

    private static final Log logger = LogFactory.getLog(DefaultMessageBuilder.class);

    @Autowired
    private MessageCryptoService messageCryptoService;

    @Override
    public Message build(WechatConfig wechatConfig, Authentication authentication, HttpServletRequest request) {
        Message message = null;

        String httpMessage = MessageUtil.parseHttpRequestToMessage(request);
        if (StringUtils.isBlank(httpMessage)) {
            logger.error("从HttpServletRequest中解析微信消息结果为空");
            return message;
        }
        // 是否进行对消息进行加密判定
        if (encryptEnable(request)) {
            httpMessage = messageCryptoService.decrypt(wechatConfig, authentication, request, httpMessage);
        }
        Map<String, Object> result2Map = XmlUtil.parse(httpMessage);
        if (!result2Map.containsKey(MessageType.KEY)) {
            logger.error("解析的消息结果有误，无法识别该消息，消息内容[" + result2Map + "]");
            return message;
        }
        if (MessageType.textMessage(result2Map)) {  // 文本消息
            return TextMessage.build(result2Map);
        }
        if (MessageType.event(result2Map)) {     // 事件
            if (MessageType.subscribe(result2Map)) {  // 关注事件
                return SubscribeEvent.build(result2Map);
            }
        }
        return null;
    }

    /**
     * 判断微信公众号是否启用了消息加密
     *
     * @param request
     *
     * @return
     */
    protected boolean encryptEnable(HttpServletRequest request) {
        return messageCryptoService.encryptEnable(request);
    }
}
