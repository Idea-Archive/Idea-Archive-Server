package Idea.Archive.IdeaArchive.infrastructure.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GithubCodeRequest {

    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    @Builder
    public GithubCodeRequest(String code, String clientId, String clientSecret, String redirectUri) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
