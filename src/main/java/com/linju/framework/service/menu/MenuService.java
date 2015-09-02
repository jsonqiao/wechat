package com.linju.framework.service.menu;

import com.linju.framework.model.ResultModel;
import com.linju.framework.model.WechatConfig;

/**
 * 微信自定义菜单管理服务接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/26
 */
public interface MenuService {

    /**
     * 创建微信菜单
     *
     * @param wechatConfig 公众号基础信息
     *
     * @param menu 生成菜单的JSON字符串
     *
     * @return 接口调用的返回结果
     */
    public ResultModel<String> createMenu(WechatConfig wechatConfig, String menu);
}
