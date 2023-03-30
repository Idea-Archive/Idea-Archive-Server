package Idea.Archive.IdeaArchive.infrastructure.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoCodeRequest {

    private final String grant_type;
    private final String client_id;
    private final String redirect_url;
    private final String code;
    private final String clientSecret;

    @Builder
    public KakaoCodeRequest(String grantType, String clientId, String redirectUrl, String code, String clientSecret) {
        this.grant_type = "authorization_code";
        this.client_id = clientId;
        this.redirect_url = redirectUrl;
        this.code = code;
        this.clientSecret = clientSecret;
    }
}
