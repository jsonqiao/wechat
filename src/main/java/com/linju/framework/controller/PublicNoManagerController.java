package com.linju.framework.controller;

import com.linju.framework.constant.ErrorCode;
import com.linju.framework.criteria.PagerCriteria;
import com.linju.framework.criteria.WechatConfigCriteria;
import com.linju.framework.exception.AccessTokenNotFoundException;
import com.linju.framework.model.AccessToken;
import com.linju.framework.model.ResultModel;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.pager.PageInfo;
import com.linju.framework.service.AccessTokenService;
import com.linju.framework.service.WechatConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公众号管理
 *
 * @author jsonqiao
 *
 * @date 2015/6/19
 */
@Controller
@RequestMapping(value = PublicNoManagerController.PREFIX)
public class PublicNoManagerController {

    private static final Log logger = LogFactory.getLog(PublicNoManagerController.class);

    public static final String PREFIX = "/public-no-manage";

    @Autowired
    private WechatConfigService wechatConfigService;

    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping(value = "/list")
    public String list() {
        return PREFIX + "/list";
    }

    /**
     * 获取系统中存储的所有公众号
     *
     * @param pagerCriteria 分页条件
     *
     * @param criteria 查询条件
     *
     * @return 查询结果，当出现系统错误时，返回数据为空并携带相关的错误码和错误说明信息
     */
    @RequestMapping(value = "/find")
    @ResponseBody
    public ResultModel<PageInfo<WechatConfig>> findPageData(PagerCriteria pagerCriteria, WechatConfigCriteria criteria) {
        ResultModel<PageInfo<WechatConfig>> resultModel = new ResultModel<>();
        try {
            PageInfo<WechatConfig> pageData = wechatConfigService.findPageDataByCriteria(pagerCriteria, criteria);
            resultModel.setError(ResultModel.SUCCESS_CODE, "查询成功");
            resultModel.setData(pageData);
        } catch (Exception e) {
            logger.error("处理获取公众号数据请求的过程中出现异常，异常详细：" + e);
            resultModel.setError(ErrorCode.NORMAL_ERROR, e.toString());
        }
        return resultModel;
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        return PREFIX + "/edit";
    }

    @RequestMapping(value = "/findAccessToken")
    @ResponseBody
    public AccessToken findAccessToken(@RequestParam("appId") final String appId,
                                       @RequestParam("appSecret") final String appSecret) throws AccessTokenNotFoundException {
        WechatConfig wechatConfig = new WechatConfig();
        wechatConfig.setAppId(appId);
        wechatConfig.setAppSecret(appSecret);
        return accessTokenService.getAccessToken(wechatConfig);
    }

}
