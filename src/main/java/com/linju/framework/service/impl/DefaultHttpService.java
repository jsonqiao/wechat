package com.linju.framework.service.impl;

import com.linju.framework.service.HttpResultBuilder;
import com.linju.framework.service.HttpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * 默认的网络处理实现
 *
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public class DefaultHttpService implements HttpService {

    public static final Log logger = LogFactory.getLog(DefaultHttpService.class);

    private HttpClientBuilder httpClientBuilder;

    private CloseableHttpClient httpClient;

    protected void init() {
        httpClient = httpClientBuilder.create().build();
    }

    protected void close() {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭CloseableHttpClient网络接口时出现[IOException]异常，异常详细信息：" + e);
            }
        }
    }

    @Override
    public void doGet(String url, HttpResultBuilder httpResultBuilder) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            httpResultBuilder.createBuilder(httpEntity);
        } catch (ClientProtocolException e) {
            logger.error("执行HTTP GET请求的过程中出现[ClientProtocolException]异常，异常详细信息：" + e);
        } catch (IOException e) {
            logger.error("执行HTTP GET请求的过程中出现[IOException]异常，异常详细信息：" + e);
        }
    }

    @Override
    public void doPost(String url, HttpResultBuilder httpResultBuilder, String postParams) {
        HttpPost httpPost = new HttpPost(url);
        HttpEntity postEntity = new StringEntity(postParams, "UTF-8");
        httpPost.setEntity(postEntity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            httpResultBuilder.createBuilder(responseEntity);
        } catch (ClientProtocolException e) {
            logger.error("执行HTTP POST请求的过程中出现[ClientProtocolException]异常，异常详细信息：" + e);
        } catch (IOException e) {
            logger.error("执行HTTP POST请求的过程中出现[IOException]异常，异常详细信息：" + e);
        }

    }

    public void setHttpClientBuilder(HttpClientBuilder httpClientBuilder) {
        this.httpClientBuilder = httpClientBuilder;
    }
}
