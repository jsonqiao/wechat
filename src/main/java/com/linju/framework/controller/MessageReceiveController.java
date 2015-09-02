package com.linju.framework.controller;

import com.linju.framework.model.Authentication;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.service.*;
import com.linju.framework.validator.AuthenticationValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信消息接收处理类
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
@Controller
public class MessageReceiveController {


    private static final Log logger = LogFactory.getLog(MessageReceiveController.class);

    @Autowired
    private AuthenticationValidator validator;

    @Autowired
    private SignatureValidator signatureValidator;

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private MessageProcessorResolver messageProcessorResolver;

    @Autowired
    private MessageCryptoService messageCryptoService;

    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 接收微信消息
     *
     * 首先微信会发送一个GET请求认证开发者，携带4个参数[signature, timestamp, echostr, nonce]
     *
     * 然后微信会发送消息到该方法，携带3个参数[signature, timestamp, nonce]
     *
     * @return
     */
    @RequestMapping(value = "/receive")
    @ResponseBody
    public String receive(HttpServletRequest request, @ModelAttribute("authentication") Authentication authentication, BindingResult bindingResult) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("接收到微信消息，开始处理该消息");
            }
            validator.validate(authentication, bindingResult);
            if (bindingResult.hasErrors()) {
                handleError(authentication, bindingResult);
            }
            if (!signatureValidator.validate(authentication, wechatConfig)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("微信签名校验失败, 欲校验数据[" + authentication + "]");
                }
                return null;
            }
            // 当echostr不为空时表明为开发者认证
            if (!StringUtils.isBlank(authentication.getEchostr())) {
                return authentication.getEchostr();
            }
            Message msg = messageBuilder.build(wechatConfig, authentication, request);
            if (!msg.isValid()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("微信接收消息无效, 消息数据[" + msg + "]");
                }
                return null;
            }
            MessageProcessor messageProcessor = messageProcessorResolver.resolve(msg);
            if (messageProcessor == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("未找到微信消息处理类, 消息数据[" + msg + "]");
                }
                return null;
            }
            String replyMessage = messageProcessor.process(msg);
            // 消息后处理
            if (messageCryptoService.encryptEnable(request)) {
                replyMessage = messageCryptoService.encrypt(wechatConfig, authentication, request, replyMessage);
            }
            return replyMessage;
        } catch (Exception e) {
            logger.error("处理微信转发请求的过程中出现异常，异常信息[" + e + "]");
            return null;
        }
    }


    /**
     * 错误处理
     *
     * @param authentication
     * @param bindingResult
     */
    protected void handleError(Authentication authentication, BindingResult bindingResult) {

    }
}
