package com.linju.framework.service.message;

import com.linju.framework.service.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jsonqiao
 *
 * @date 2015/6/23
 */
public abstract class AbstractMessage implements Message {

    protected Map<String, Object> attributeMap = new HashMap<>();

    @Override
    public Object getAttribute(String attributeName) {
        return attributeMap.get(attributeName);
    }

    @Override
    public boolean containAttribute(String attributeName) {
        return attributeMap.containsKey(attributeName);
    }
}
