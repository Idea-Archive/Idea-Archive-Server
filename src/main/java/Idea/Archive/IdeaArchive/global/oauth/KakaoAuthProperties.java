package Idea.Archive.IdeaArchive.global.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "auth.kakao")
public class KakaoAuthProperties {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

//    public KakaoAuthProperties(String clientId, String clientSecret, String redirectUri) {
//        this.clientId = clientId;
//        this.clientSecret = clientSecret;
//        this.redirectUri = redirectUri;
//    }
}
