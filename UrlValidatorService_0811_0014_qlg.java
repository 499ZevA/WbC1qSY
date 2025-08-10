// 代码生成时间: 2025-08-11 00:14:50
package com.example.validator;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.http.client.exceptions.HttpException;
import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Optional;

@Singleton
public class UrlValidatorService {

    private final HttpClient httpClient;

    public UrlValidatorService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**<ol>
     * Validates the URL's format and accessibility.
     * 
     * @param urlString The URL to be validated.
     * @return A boolean indicating whether the URL is valid and accessible.
     */
    public boolean isValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            return Optional.ofNullable(httpClient.toBlocking().exchange(HttpRequest.GET(urlString), String.class))
                    .map(response -> response.getStatus() == HttpStatus.OK)
                    .orElse(false);
        } catch (MalformedURLException e) {
            // Handle invalid URL format.
            return false;
        } catch (UnknownHostException e) {
            // Handle unknown host.
            return false;
        } catch (HttpClientException | HttpException e) {
            // Handle HTTP client related exceptions.
            return false;
        } catch (Exception e) {
            // Handle other possible exceptions.
            return false;
        }
    }
}
