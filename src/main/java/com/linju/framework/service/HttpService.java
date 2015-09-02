package com.linju.framework.service;

import java.util.Map;

/**
 * 网络服务接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public interface HttpService {

    public void doGet(String url, HttpResultBuilder httpResultBuilder);

    public void doPost(String url, HttpResultBuilder httpResultBuilder, String postParams);
}
