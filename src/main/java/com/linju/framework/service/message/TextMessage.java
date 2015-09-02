package com.linju.framework.service.message;

import java.util.Map;

/**
 * 文本消息
 *
 * @author jsonqiao
 *
 * @date 2015/6/23
 */
public class TextMessage extends AbstractMessage {

    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String msgType;

    private String content;

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean enableEncryption() {
        return false;
    }

    private TextMessage() {
    }

    public static final TextMessage build(Map<String, Object> messageMap) {
        TextMessage textMessage = new TextMessage();
        textMessage.attributeMap = messageMap;
        textMessage.toUserName = (String) messageMap.get("ToUserName");
        textMessage.fromUserName = (String) messageMap.get("FromUserName");
        textMessage.createTime = (String) messageMap.get("CreateTime");
        textMessage.msgType = (String) messageMap.get("MsgType");
        textMessage.content = (String) messageMap.get("Content");
        return textMessage;
    }
}
