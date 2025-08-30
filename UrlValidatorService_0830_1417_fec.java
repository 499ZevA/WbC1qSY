// 代码生成时间: 2025-08-30 14:17:29
package com.example.urlvalidator;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.core.annotation.Nullable;
import java.net.URI;
import javax.inject.Singleton;

@Singleton
public class UrlValidatorService {

    @Client("/")
    private HttpClient httpClient;

    private static final String INVALID_URL = "Invalid URL";

    /**
     * Validates a given URL by attempting to make a HEAD request.
     *
     * @param url The URL to validate.
     * @return True if the URL is valid, false otherwise.
     */
    public boolean isValidUrl(String url) {
        try {
            HttpRequest<?> request = HttpRequest.HEAD(URI.create(url));
            return httpClient.toBlocking().exchange(request, String.class).code() == 200;
        } catch (Exception e) {
            // Log the exception or handle it as per your logging policy
            return false;
        }
    }
}
