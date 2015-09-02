package com.linju.framework.service.menu;

import com.linju.framework.exception.AccessTokenNotFoundException;
import com.linju.framework.model.AccessToken;
import com.linju.framework.model.ResultModel;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.service.AccessTokenService;
import com.linju.framework.service.HttpResultBuilder;
import com.linju.framework.service.HttpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * 默认的菜单服务接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/26
 */
@Service
public class DefaultMenuService implements MenuService {

    private static final Log logger = LogFactory.getLog(DefaultMenuService.class);

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private HttpService httpService;

    @Autowired
    private HttpResultBuilder httpResultBuilder;

    @Value("#{commonProperties['menu.create.url']")
    private String templateUrl;

    @Override
    public ResultModel<String> createMenu(WechatConfig wechatConfig, String menu) {
        // 获取AccessToken
        try {
            AccessToken accessToken = accessTokenService.getAccessToken(wechatConfig);
            String url = MessageFormat.format(templateUrl, accessToken.getAccessToken());
        } catch (AccessTokenNotFoundException e) {
            logger.error("获取AccessToken过程中出现异常[AccessTokenNotFoundException]，异常详细信息：" + e);
            return null;
        }
        httpService.doPost(templateUrl, httpResultBuilder, menu);
        return httpResultBuilder.build();
    }
}
