// 代码生成时间: 2025-08-14 23:58:41
package com.example.memoryanalysis;

import io.micronaut.context.annotation.Requires;
import io.micronaut.management.health.indicator.HealthIndicator;
import io.micronaut.management.health.indicator.HealthResult;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import javax.inject.Singleton;

@Requires(env = "production")
@Singleton
public class MemoryUsageAnalysis implements HealthIndicator {
    // Memory MX Bean to get memory usage statistics
    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @Override
    public HealthResult check() {
        try {
            // Get the current memory usage
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

            // Create a health result with memory usage data
            return HealthResult.builder()
                    .withDetail("heapMemoryUsage", heapMemoryUsage.toString())
                    .withDetail("nonHeapMemoryUsage", nonHeapMemoryUsage.toString())
                    .up()
                    .build();
        } catch (Exception e) {
            // Handle any exceptions that may occur and mark the health check as down
            return HealthResult.builder()
                    .withException(e)
                    .down()
                    .build();
        }
    }
}
