package com.linju.framework.utils;

import com.alibaba.fastjson.JSONObject;
import com.linju.framework.constant.AccessTokenKey;
import org.springframework.util.Assert;

/**
 * 微信接口工具类
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public abstract class WechatAPIHelper {

    public static final boolean success(JSONObject obj) {
        Assert.notNull(obj, "返回数据不能为空");
        if (!obj.containsKey("errorCode")) {
            return false;
        }
        String errorCode = obj.getString("errorCode");

        return "0".equals(errorCode);
    }

    public static boolean containAccessToken(JSONObject obj) {
        Assert.notNull(obj, "返回数据不能为空");
        return obj.containsKey(AccessTokenKey.ACCESS_TOKEN);
    }
}
