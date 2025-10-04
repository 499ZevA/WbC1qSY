// 代码生成时间: 2025-10-04 23:22:09
package com.example.demo.service;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.JwtToken;
import io.micronaut.security.handlers.LoginHandler;
import io.micronaut.security.handlers.SecurityHandler;
import io.micronaut.security.token.jwt.JwtConfiguration;
import io.micronaut.security.token.jwt.generator.JwtGenerator;
import io.micronaut.security.userdetails.UserDetails;
import io.micronaut.security.userdetails.UserDetailsService;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetailsNotFoundException;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.util.Optional;

@Requires(env = Environment.TEST)
@Singleton
public class AuthenticationService implements UserDetailsService {

    private final JwtGenerator jwtGenerator;
    private final JwtConfiguration jwtConfiguration;
    private final HttpClient httpClient;

    public AuthenticationService(JwtGenerator jwtGenerator, JwtConfiguration jwtConfiguration, @Client("user-service") HttpClient httpClient) {
        this.jwtGenerator = jwtGenerator;
        this.jwtConfiguration = jwtConfiguration;
        this.httpClient = httpClient;
    }

    @Override
    public Publisher<UserDetails> findByUsername(String username, String tenantId) {
        try {
            // Simulate fetching user details from a user service
            UserDetails userDetails = httpClient.toBlocking().retrieve(HttpRequest.GET("/users/" + username), UserDetails.class);
            return Publisher.just(userDetails);
        } catch (Exception e) {
            throw new UserDetailsNotFoundException("User not found", e);
        }
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    public Publisher<JwtToken> login(String username, String password) {
        // Validate user credentials
        if (!isValidUser(username, password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        // Generate JWT token
        JwtToken jwtToken = jwtGenerator.generateToken(username, Optional.empty(), Optional.empty());
        return Publisher.just(jwtToken);
    }

    private boolean isValidUser(String username, String password) {
        // Implement user validation logic
        // For demonstration purposes, assume all users are valid
        return true;
    }

    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> request, SecurityHandler securityHandler) {
        // Handle authentication logic
        // For demonstration purposes, return a successful response
        return Publisher.just(new AuthenticationResponse("success", HttpStatus.OK));
    }

    public Publisher<AuthenticationResponse> logout(HttpRequest<?> request, LoginHandler loginHandler) {
        // Handle logout logic
        // For demonstration purposes, return a successful response
        return Publisher.just(new AuthenticationResponse("success", HttpStatus.OK));
    }
}
