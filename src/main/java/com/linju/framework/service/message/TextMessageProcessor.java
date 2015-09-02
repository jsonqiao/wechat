package com.linju.framework.service.message;

import com.linju.framework.service.Message;
import com.linju.framework.service.MessageProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文本消息处理
 *
 * @author jsonqiao
 *
 * @date 2015/6/25
 */
public class TextMessageProcessor extends VelocityTemplateMessageProcessor implements MessageProcessor {

    private static final Log logger = LogFactory.getLog(TextMessageProcessor.class);

    @Override
    public boolean support(Message message) {
        return TextMessage.class.equals(message.getClass());
    }

    @Override
    public String process(Message message) {
        Map modal = new HashMap();
        modal.put("toUser", message.getAttribute("FromUserName"));
        modal.put("fromUser", message.getAttribute("ToUserName"));
        modal.put("createTime", message.getAttribute("CreateTime"));
        modal.put("content", buildReplyContent(message));
        try {
            // 处理乱码
            return new String(build(modal).getBytes("utf-8"), "ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            logger.error("处理文本消息回复的过程中出现异常[UnsupportedEncodingException]，异常详细信息：" + e);
            return "";
        }
    }

    protected String buildReplyContent(Message message) {
        String content = (String) message.getAttribute("Content");
        if ("刘滨艳".equals(content)) {
            return "*^__^* 嘻嘻，傻蛋，您好啊，我是坏蛋";
        }
        if ("傻蛋".equals(content)) {
            return "哈哈，我知道你是谁呢，但是我就不告诉你";
        }
        if ("坏蛋".equals(content)) {
            return "坏蛋是谯杨";
        }
        return "鹦鹉学舌[" + message.getAttribute("Content") + "]";
    }
}
