package com.linju.framework.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.linju.framework.constant.AccessTokenKey;
import com.linju.framework.exception.AccessTokenNotFoundException;
import com.linju.framework.model.AccessToken;
import com.linju.framework.model.ResultModel;
import com.linju.framework.model.WechatConfig;
import com.linju.framework.service.AccessTokenService;
import com.linju.framework.service.HttpResultBuilder;
import com.linju.framework.service.HttpService;
import com.linju.framework.service.WechatConfigService;
import com.linju.framework.utils.WechatAPIHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 微信AccessToken中控服务器的默认实现
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public class DefaultAccessTokenService implements AccessTokenService {

    private static final Log logger = LogFactory.getLog(DefaultAccessTokenService.class);

    /** 微信AccessToken缓存*/
    private Map<String, AccessToken> accessTokenCache = new ConcurrentHashMap<>();

    private List<WechatConfig> wechatCache = new ArrayList<>();

    // 请求模版url
    private String templateUrl;

    // 底层网络请求
    private HttpService httpService;

    // 网络结果初步处理
    private HttpResultBuilder httpResultBuilder;

    private ScheduledExecutorService executor;

    // 刷新时间间隔
    private int refreshPeriod = 5400;

    // 是否自动刷新
    private boolean autoRefresh = true;

    private WechatConfigService wechatConfigService;

    private String connector = "_";

    @Override
    public void refresh(WechatConfig wechatConfig) {
        String requestUrl = MessageFormat.format(templateUrl, wechatConfig.getAppId(), wechatConfig.getAppSecret());
        httpService.doGet(requestUrl, httpResultBuilder);
        ResultModel<String> resultModel = httpResultBuilder.build();
        AccessToken accessToken = buildAccessToken(resultModel);
        if (accessToken != null) {
            synchronized (this) {
                accessTokenCache.put(wechatConfig.getAppId() + connector + wechatConfig.getAppSecret(), accessToken);
            }
        }
    }

    @Override
    public AccessToken getAccessToken(WechatConfig wechatConfig) throws AccessTokenNotFoundException {
        /*
        if (!wechatCache.contains(wechatConfig)) {
            wechatCache.add(wechatConfig);
            refresh(wechatConfig);
        }
         */
        String key = wechatConfig.getAppId() + connector + wechatConfig.getAppSecret();
        if (!accessTokenCache.containsKey(key)) {
            throw new AccessTokenNotFoundException("不存在该公众号对应的AccessToken");
        }
        return accessTokenCache.get(key);
    }

    /**
     * 初始化方法
     */
    public void init() {
        Assert.notNull("获取微信公众号AccessToken的请求模版url不能为空!");
        initWechatCache();
        refreshInternal();  // 内部主动刷新
        if (autoRefresh) {
            if (executor == null) {
                executor = Executors.newSingleThreadScheduledExecutor();
            }
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        refreshInternal();
                    } catch (Exception e) {
                        logger.error("微信AccessToken中控服务器在刷新AccessToken的过程中出现异常， 异常详细信息：" + e);
                    }
                }
            }, refreshPeriod, refreshPeriod, TimeUnit.SECONDS);
        }
    }

    /**
     * 内部主动刷新缓存的accessToken
     */
    protected void refreshInternal() {
        for (WechatConfig wechatConfig : wechatCache) {
            refresh(wechatConfig);
        }
    }

    /**
     * 刷新公众号缓存
     */
    protected void initWechatCache() {
        List<WechatConfig> wechatConfigs = wechatConfigService.findAll();
        if (wechatConfigs.size() > 0) {
            wechatCache.clear();
            wechatCache.addAll(wechatConfigs);
        }
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    protected AccessToken buildAccessToken(ResultModel<String> resultModel) {
        JSONObject jsonObject = JSONObject.parseObject(resultModel.getData());
        if (WechatAPIHelper.containAccessToken(jsonObject)) {
            String accessToken = jsonObject.getString(AccessTokenKey.ACCESS_TOKEN);
            long expireTime = jsonObject.getLong(AccessTokenKey.EXPIRES_IN);
            long createTime = new Date().getTime() / 1000;
            return new AccessToken(accessToken, expireTime, createTime);
        }
        return null;
    }

    public void setRefreshPeriod(int refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public void setHttpResultBuilder(HttpResultBuilder httpResultBuilder) {
        this.httpResultBuilder = httpResultBuilder;
    }

    public void setWechatConfigService(WechatConfigService wechatConfigService) {
        this.wechatConfigService = wechatConfigService;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }
}
