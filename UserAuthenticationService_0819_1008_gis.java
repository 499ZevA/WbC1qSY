// 代码生成时间: 2025-08-19 10:08:53
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.JwtJsonRenderer;
# TODO: 优化性能
import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import io.micronaut.core.type.Argument;
import io.micronaut.context.BeanContext;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationFailed;
# 扩展功能模块
import io.reactivex.Flowable;
import java.util.Collections;
import javax.inject.Singleton;
import java.util.Optional;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.UserDetailsLoader;
# FIXME: 处理边界情况

/**
 * Service for handling user authentication.
 */
@Controller("/auth")
@Requires(beans = SecurityRule.class)
public class UserAuthenticationService {

    private final BeanContext beanContext;
    private final JwtTokenGenerator jwtTokenGenerator;

    /**
     * Inject the BeanContext to retrieve AuthenticationProviders.
     */
    public UserAuthenticationService(BeanContext beanContext, JwtTokenGenerator jwtTokenGenerator) {
        this.beanContext = beanContext;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Post("/login")
    public Flowable<AuthenticationResponse> login(@Body LoginRequest loginRequest) {
        // Retrieve the AuthenticationProvider bean
        Optional<AuthenticationProvider> authenticationProvider = beanContext.findBean(AuthenticationProvider.class);
        if (authenticationProvider.isPresent()) {
            // Load user details from the request
            UserDetails userDetails = loadUserDetails(loginRequest.getUsername(), loginRequest.getPassword());
            // Authenticate user
            return authenticationProvider.get().authenticate(
                Authentication.builder(userId, Argument.STRING).credentials(password, Argument.STRING).build()
            ).onErrorResumeNext(ex -> Flowable.error(new AuthenticationFailed("Unable to authenticate user")));
        } else {
            return Flowable.error(new IllegalStateException("No AuthenticationProvider available"));
        }
    }

    private UserDetails loadUserDetails(String username, String password) {
# 改进用户体验
        // Retrieve the UserDetailsLoader bean
# 增强安全性
        Optional<UserDetailsLoader> userDetailsLoader = beanContext.findBean(UserDetailsLoader.class);
        if (userDetailsLoader.isPresent()) {
            return userDetailsLoader.get().loadUserByUsername(username);
        } else {
            throw new IllegalStateException("No UserDetailsLoader available");
        }
# TODO: 优化性能
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post("/logout")
    public Flowable<?> logout(Authentication authentication) {
        // Invalidate the JWT token
        jwtTokenGenerator.invalidateToken(authentication);
        return Flowable.just(Collections.emptyMap());
    }
}

/**
 * Login request data class.
# 优化算法效率
 */
class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
# 优化算法效率