package Idea.Archive.IdeaArchive.global.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "auth.kakao")
public class KakaoAuthProperties {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;

    public KakaoAuthProperties(String clientId, String clientSecret, String redirectUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }
}
