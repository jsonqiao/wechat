package com.linju.framework.service;

import com.linju.framework.model.*;
import org.apache.http.HttpEntity;

/**
 * 网络处理回调接口
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public interface HttpResultBuilder {

    public void createBuilder(HttpEntity httpEntity);

    public ResultModel<String> build();
}
