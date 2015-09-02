package com.linju.framework.mapper;

import com.linju.framework.criteria.WechatConfigCriteria;
import com.linju.framework.model.WechatConfig;

import java.util.List;

/**
 * 微信公众号接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public interface WechatConfigMapper {

    public List<WechatConfig> findAll();

    public List<WechatConfig> findByCriteria(WechatConfigCriteria wechatConfigCriteria);
}
