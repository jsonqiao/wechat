package com.linju.framework.service;

/**
 * 微信消息接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/16
 */
public interface Message {

    /**
     * 消息是否有效
     *
     * @return 消息有效为true, 消息无效为false
     */
    public boolean isValid();

    /**
     * 是否启用加密
     *
     * @return
     */
    public boolean enableEncryption();

    /**
     * 获取微信消息属性
     *
     * @param attributeName 属性名称
     *
     * @return 属性值
     */
    public Object getAttribute(String attributeName);

    /**
     * 是否包含某一属性
     *
     * @param attributeName 属性名称
     *
     * @return 包含返回true，不包含返回false
     */
    public boolean containAttribute(String attributeName);

}
