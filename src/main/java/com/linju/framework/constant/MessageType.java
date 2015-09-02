package com.linju.framework.constant;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 消息类型
 *
 * @author jsonqiao
 *
 * @date 2015/6/25
 */
public class MessageType {

    public static final String KEY = "MsgType";

    // 文本消息
    public static final String TEXT = "text";

    // 事件
    public static final String EVENT = "event";

    // 订阅事件
    public static final String SUBSCRIBE_EVENT = "subscribe";

    public static final boolean textMessage(Map<String, Object> resultMap) {
        return judgeMessage(TEXT, resultMap);
    }

    public static final boolean event(Map<String, Object> resultMap) {
        return judgeMessage(EVENT, resultMap);
    }

    public static final boolean subscribe(Map<String, Object> resultMap) {
        if (resultMap.containsKey("Event")) {
            return SUBSCRIBE_EVENT.equals(resultMap.get("Event"));
        }
        return false;
    }

    public static final boolean judgeMessage(final String messageType, Map<String, Object> resultMap) {
        if (resultMap == null || resultMap.isEmpty()) {
            return false;
        }
        if (StringUtils.isBlank(messageType)) {
            return false;
        }
        if (!resultMap.containsKey(KEY)) {
            return false;
        }
        return messageType.equals(resultMap.get(KEY));
    }
}
