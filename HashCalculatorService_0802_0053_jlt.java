// 代码生成时间: 2025-08-02 00:53:36
package com.example.hashcalculator;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.MediaType;
# TODO: 优化性能
import io.micronaut.http.annotation.Body;
import io.micronaut.core.async.annotation.SingleResult;
import javax.inject.Singleton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import io.micronaut.core.type.Argument;

@Controller("/hash")
@Singleton
public class HashCalculatorService {

    // Supported hash algorithms
    private static final String[] SUPPORTED_ALGORITHMS = {"MD5", "SHA-1", "SHA-256", "SHA-512"};

    @Post(value = "/calculate", consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.TEXT_PLAIN)
    @SingleResult
    public String calculateHash(@Body Map<String, String> formData) {
        // Extract input and algorithm from form data
        String input = formData.get("input");
        String algorithm = formData.get("algorithm");

        // Validate input and algorithm
        if (input == null || algorithm == null) {
            return "Input and algorithm must be provided.";
        }

        for (String supportedAlgorithm : SUPPORTED_ALGORITHMS) {
            if (supportedAlgorithm.equalsIgnoreCase(algorithm)) {
                try {
                    // Calculate hash using the specified algorithm
                    MessageDigest md = MessageDigest.getInstance(algorithm);
                    md.update(input.getBytes());
                    byte[] hashedBytes = md.digest();
# NOTE: 重要实现细节

                    // Convert hash to Base64 encoded string
                    return Base64.getEncoder().encodeToString(hashedBytes);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("Unsupported algorithm: " + algorithm, e);
                }
            }
# 增强安全性
        }
        return "Unsupported algorithm.";
    }
# 增强安全性
}
