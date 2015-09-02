package com.linju.framework.service;

import com.linju.framework.criteria.PagerCriteria;
import com.linju.framework.criteria.WechatConfigCriteria;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.pager.PageInfo;

import java.util.List;

/**
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public interface WechatConfigService {

    public List<WechatConfig> findAll();

    /**
     * 分页查询
     *
     * @param pagerCriteria
     *
     * @param criteria
     *
     * @return
     */
    public PageInfo<WechatConfig> findPageDataByCriteria(PagerCriteria pagerCriteria, WechatConfigCriteria criteria);
}
