// 代码生成时间: 2025-08-02 22:06:20
package com.example.demo.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.scheduling.TaskExecutors;
import io.reactivex.Single;
import java.net.URL;
import javax.inject.Singleton;

@Singleton // 单例模式，确保同一实例被多次使用
public class UrlValidatorService {

    private final HttpClient httpClient; // HTTP客户端，用于发送请求

    // 构造函数注入HttpClient
    public UrlValidatorService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    // 验证URL链接有效性的方法
    public Single<Boolean> validateUrl(String urlString) {
        return Single.fromCallable(() -> {
            try {
                URL url = new URL(urlString);
                // 尝试打开连接以验证URL是否有效
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD"); // 使用HEAD请求，不下载资源内容
                connection.connect();
                // 根据响应码判断URL是否有效
                int responseCode = connection.getResponseCode();
                return responseCode >= 200 && responseCode < 300;
            } catch (Exception e) {
                // 捕获异常，返回URL无效
                return false;
            }
        }).subscribeOn(TaskExecutors.IO); // 在IO线程上执行网络请求
    }

    // 错误处理示例
    public String handleErrors(HttpClientResponseException e) {
        return "Error: " + e.getMessage();
    }
}
