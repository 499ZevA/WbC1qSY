// 代码生成时间: 2025-08-04 19:42:56
package com.example.hash;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

@Controller("/hash")
public class HashValueCalculator {

    private static final String HMAC_SHA_256_ALGORITHM = "HmacSHA256";
    private static final String SHA_256_ALGORITHM = "SHA-256";
    private static final String MD5_ALGORITHM = "MD5";

    // Calculate SHA-256 hash value
    @Get("/sha256/{input}")
    public HttpResponse<String> calculateSHA256Hash(@PathVariable String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
            byte[] hash = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HttpResponse.ok(bytesToHex(hash));
        } catch (Exception e) {
            return HttpResponse.serverError(e.getMessage());
        }
    }

    // Calculate MD5 hash value
    @Get("/md5/{input}")
    public HttpResponse<String> calculateMD5Hash(@PathVariable String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MD5_ALGORITHM);
            byte[] hash = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HttpResponse.ok(bytesToHex(hash));
        } catch (Exception e) {
            return HttpResponse.serverError(e.getMessage());
        }
    }

    // Calculate HMAC SHA-256 hash value
    @Get("/hmac/{input}/{key}")
    public HttpResponse<String> calculateHMACSHA256Hash(@PathVariable String input, @PathVariable String key) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA_256_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA_256_ALGORITHM);
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return HttpResponse.ok(bytesToHex(hash));
        } catch (Exception e) {
            return HttpResponse.serverError(e.getMessage());
        }
    }

    // Utility method to convert byte array to hex string
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
