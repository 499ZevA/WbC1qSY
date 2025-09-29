// 代码生成时间: 2025-09-29 20:18:16
package com.example.demo;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.MediaType;
import io.micronaut.management.health.indicator.HealthIndicator;
import jakarta.inject.Singleton;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Controller to check network connection status.
 */
@Requires(property = "endpoints.health.sensitive", value = "false")
@Controller("/status")
@Singleton
public class NetworkStatusChecker implements HealthIndicator {

    private static final String HEALTH_CHECK_ENDPOINT = "/network";
    private static final String HOST_TO_PING = "8.8.8.8"; // Google's public DNS

    /**
     * Endpoint to check network connection status.
     * @return The network connection status in JSON format.
     */
    @Get(value = HEALTH_CHECK_ENDPOINT, produces = MediaType.APPLICATION_JSON)
    public String checkNetworkStatus() {
        try {
            InetAddress.getByName(HOST_TO_PING);
            return "{"status":"UP"}";
        } catch (UnknownHostException e) {
            return "{"status":"DOWN"}";
        }
    }

    @Override
    public String getName() {
        return "network";
    }
}
