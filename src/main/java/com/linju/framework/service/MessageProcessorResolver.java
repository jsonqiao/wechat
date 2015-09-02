package com.linju.framework.service;

/**
 * 消息处理接口查找接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
public interface MessageProcessorResolver {

    /**
     * 映射消息处理接口
     *
     * @param message 消息
     *
     * @return 如果查找到返回相应的MessageResolver, 否则返回null
     */
    public MessageProcessor resolve(Message message);
}
