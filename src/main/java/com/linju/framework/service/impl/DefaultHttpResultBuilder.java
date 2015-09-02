package com.linju.framework.service.impl;

import com.linju.framework.model.ResultModel;
import com.linju.framework.service.HttpResultBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 默认的网络请求结果处理构建实现
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public class DefaultHttpResultBuilder implements HttpResultBuilder {

    private static final Log logger = LogFactory.getLog(DefaultHttpResultBuilder.class);

    public static final String DEFAULT_CHARSET = "UTF-8";

    private HttpEntity httpEntity;

    private String charset = DEFAULT_CHARSET;


    @Override
    public void createBuilder(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    @Override
    public ResultModel<String> build() {
        ResultModel<String> resultModel = new ResultModel<>();
        if (httpEntity == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("网络请求结果[HttpEntity]内容为空");
            }
            resultModel.setError("9001", "请求没有响应数据");
            return resultModel;
        }
        try {
            String data = EntityUtils.toString(httpEntity, charset);
            EntityUtils.consume(httpEntity);
            resultModel.setError("0", "封装网络请求响应成功");
            resultModel.setData(data);
            return resultModel;
        } catch (IOException e) {
            logger.error("封装网络请求相应时出现[IOException]异常，异常详细信息：" + e);
            resultModel.setError("-1", "封装网络请求相应时出现[IOException]异常");
            return resultModel;
        }
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
