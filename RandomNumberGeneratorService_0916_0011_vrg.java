// 代码生成时间: 2025-09-16 00:11:02
package com.example.random;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.Introspected;
import javax.inject.Singleton;
import java.util.Random;

@Singleton
@Introspected
public class RandomNumberGeneratorService {

    // Define the range for the random number generation
    @Value('${random.min:0}')
    private int min;

    @Value('${random.max:100}')
    private int max;

    private final Random random = new Random();

    /**
     * Generates a random number within the defined range.
     *
     * @return int - Random number within the range [min, max].
     */
    public int generateRandomNumber() {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Sets the minimum value for the random number generation range.
     *
     * @param min - The new minimum value.
     */
    public void setMin(int min) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimum value cannot be negative.");
        }
        this.min = min;
    }

    /**
     * Sets the maximum value for the random number generation range.
     *
     * @param max - The new maximum value.
     */
    public void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("Maximum value cannot be negative.");
        }
        this.max = max;
    }

    // Getters and setters for min and max
    public int getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
