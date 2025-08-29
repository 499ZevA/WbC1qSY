// 代码生成时间: 2025-08-30 06:16:09
package com.example.accesscontrol;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Singleton;

@Controller("/api")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AccessControlController {

    // This endpoint is accessible to anyone (anonymous access)
    @Get("/open")
    public String openAccess() {
        return "This is an open access endpoint.";
    }

    // This endpoint requires a user to be authenticated
    @Get("/secure")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public String secureAccess() {
        return "This is a secure endpoint accessible to authenticated users.";
    }

    // This endpoint requires a user to have a specific role
    @Get("/admin")
    @Secured("admin")
    public String adminAccess() {
        return "This is an admin endpoint accessible to users with the 'admin' role.";
    }

    // This method is used to handle access denied exceptions
    @Singleton
    public static class AccessDeniedExceptionHandler implements io.micronaut.security.authentication.AccessDeniedExceptionFilter {
        @Override
        public String filter(io.micronaut.security.authentication.AccessDeniedException exception) {
            return "Access Denied: You do not have permission to access this resource.";
        }
    }
}
