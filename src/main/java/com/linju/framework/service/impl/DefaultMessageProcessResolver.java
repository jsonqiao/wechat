package com.linju.framework.service.impl;

import com.linju.framework.service.Message;
import com.linju.framework.service.MessageProcessor;
import com.linju.framework.service.MessageProcessorResolver;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jsonqiao
 *
 * @date 2015/6/18
 */
public class DefaultMessageProcessResolver implements MessageProcessorResolver {

    List<MessageProcessor> messageProcessors;

    @Override
    public MessageProcessor resolve(Message message) {
        for (MessageProcessor messageProcessor : messageProcessors) {
            if (messageProcessor.support(message)) {
                return messageProcessor;
            }
        }
        return null;
    }

    public void setMessageProcessors(List<MessageProcessor> messageProcessors) {
        this.messageProcessors = messageProcessors;
    }
}
