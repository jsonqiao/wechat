package com.linju.framework.service;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON数据解析接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public interface JsonDataParser {

    public JSONObject parse(Object data);
}
