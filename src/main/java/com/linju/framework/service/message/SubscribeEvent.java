package com.linju.framework.service.message;

import java.util.Map;

/**
 * 关注事件
 *
 * @author jsonqiao
 *
 * @date 2015/6/25
 */
public class SubscribeEvent extends AbstractMessage {

    public static final SubscribeEvent build(Map<String, Object> messageMap) {
        SubscribeEvent subscribeEvent = new SubscribeEvent();
        subscribeEvent.attributeMap = messageMap;
        return subscribeEvent;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean enableEncryption() {
        return false;
    }
}
