package com.linju.framework.service.impl;

import com.linju.framework.criteria.PagerCriteria;
import com.linju.framework.criteria.WechatConfigCriteria;
import com.linju.framework.mapper.WechatConfigMapper;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.pager.PageInfo;
import com.linju.framework.pager.PageUtil;
import com.linju.framework.service.WechatConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
@Service
public class WechatConfigServiceImpl implements WechatConfigService {

    @Autowired
    private WechatConfigMapper wechatConfigMapper;

    @Override
    public List<WechatConfig> findAll() {
        return wechatConfigMapper.findAll();
    }

    @Override
    public PageInfo<WechatConfig> findPageDataByCriteria(PagerCriteria pagerCriteria, WechatConfigCriteria criteria) {
        PageUtil.startPage(pagerCriteria.getPageNo(), pagerCriteria.getPageSize());
        List<WechatConfig> pageData = wechatConfigMapper.findByCriteria(criteria);
        return new PageInfo<WechatConfig>(pageData);
    }
}
