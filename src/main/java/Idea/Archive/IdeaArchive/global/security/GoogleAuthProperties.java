package Idea.Archive.IdeaArchive.global.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "auth.google")
public class GoogleAuthProperties {

    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;

    public GoogleAuthProperties(String baseUrl, String clientId, String clientSecret, String redirectUrl) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }

}