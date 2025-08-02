// 代码生成时间: 2025-08-02 17:46:50
import io.micronaut.context.annotation.Requires;
    import io.micronaut.http.HttpRequest;
    import io.micronaut.http.annotation.Controller;
    import io.micronaut.http.annotation.Get;
    import io.micronaut.security.annotation.Secured;
    import io.micronaut.security.rules.SecurityRule;
    import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
    import io.micronaut.security.token.jwt.signature.JwksSignatureVerifier;
    import io.micronaut.security.token.jwt.signature.JwksSignatureVerifierFactory;
    import io.micronaut.security.token.jwt.signature.JwksSignatureVerifierFactoryConfiguration;
    import io.micronaut.security.token.jwt.signature.SignatureVerifier;
    import io.micronaut.security.token.jwt.signature.SignatureVerifierFactory;
    import io.micronaut.security.token.jwt.validation.JwtValidator;
    import io.micronaut.security.token.jwt.validation.JwtValidationContext;
    import jakarta.inject.Singleton;
    import java.security.KeyFactory;
    import java.security.PublicKey;
    import java.security.spec.X509EncodedKeySpec;
    import java.util.Base64;
    import java.util.Optional;

    /**
     * User authentication service using Micronaut framework.
     * Provides basic user identity verification and JWT handling.
     */
    @Controller("/auth")
    @Singleton
    @Requires(beans = JwksSignatureVerifierFactory.class)
    @Requires(property = SecurityConstants.DISABLED, value = "false", defaultValue = 