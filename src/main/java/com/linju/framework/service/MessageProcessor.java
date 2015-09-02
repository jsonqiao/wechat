package com.linju.framework.service;

/**
 * 消息处理接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
public interface MessageProcessor {

    /**
     * 支持处理的消息类型判定
     *
     * @param message
     *
     * @return
     */
    public boolean support(Message message);

    /**
     * 处理消息
     *
     * @param message 要处理的消息
     */
    public String process(Message message);
}
