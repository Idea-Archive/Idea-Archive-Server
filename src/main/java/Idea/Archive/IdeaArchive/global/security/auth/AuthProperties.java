package Idea.Archive.IdeaArchive.global.security.auth;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("auth.google")
public class AuthProperties {

    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;

    public AuthProperties(String baseUrl, String clientId, String clientSecret, String redirectUrl) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }
}