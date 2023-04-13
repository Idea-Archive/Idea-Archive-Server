package Idea.Archive.IdeaArchive.infrastructure.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoCodeRequest {

    private final String grant_type;
    private final String client_id;
    private final String redirect_uri;
    private final String code;
    private final String client_secret;

    @Builder
    public KakaoCodeRequest(String clientId, String redirectUri, String code, String clientSecret) {
        this.grant_type = "authorization_code";
        this.client_id = clientId;
        this.redirect_uri = redirectUri;
        this.code = code;
        this.client_secret = clientSecret;
    }
}