package com.linju.framework.service.message;

import com.linju.framework.service.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 关注事件处理类
 *
 * @author jsonqiao
 *
 * @date 2015/6/25
 */
public class SubscribeEventProcessor extends TextMessageProcessor {

    private static final Log logger = LogFactory.getLog(SubscribeEventProcessor.class);

    @Override
    public boolean support(Message message) {
        return SubscribeEvent.class.equals(message.getClass());
    }

    @Override
    protected String buildReplyContent(Message message) {
        return "欢迎您关注谯杨的私人公众号，该公众号将免费为你推送JAVA,Python相关的技术文章";
    }
}
