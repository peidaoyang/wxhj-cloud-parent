package com.wxhj.cloud.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.io.IOException;

/**
 * @author daxiong
 * @date 2020/5/8 4:25 下午
 */
public class HttpClientUtil {

    private static final int SUCCESS_CODE = 200;
    private static final int TIMEOUT_TIME = 5000;

    /**
     * GET---无参
     *
     * @param url   请求的url
     * @param clazz 要转换的目标类
     * @return T
     * @throws AsyncRequestTimeoutException,IOException 超时异常
     * @throws RuntimeException 请求返回错误异常
     * @author daxiong
     * @date 2020/5/8 8:40 下午
     */
    public static <T> T doGetTestOne(String url, Class<T> clazz) throws AsyncRequestTimeoutException, IOException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(TIMEOUT_TIME)
                .setConnectTimeout(TIMEOUT_TIME)
                .setSocketTimeout(TIMEOUT_TIME).build();
        httpGet.setConfig(requestConfig);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == SUCCESS_CODE) {
                if (responseEntity != null) {
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                    return JSON.parseObject(EntityUtils.toString(responseEntity), clazz);
                }
            } else {
                throw new RuntimeException("请求异常：" + statusCode + "----" + EntityUtils.toString(responseEntity));
            }
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
