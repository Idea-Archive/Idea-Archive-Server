package Idea.Archive.IdeaArchive.infrastructure.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GoogleCodeRequest {

    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String grantType;

    @Builder
    public GoogleCodeRequest(String code, String clientId, String clientSecret, String redirectUri) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.grantType = "authorization_code";
    }

}