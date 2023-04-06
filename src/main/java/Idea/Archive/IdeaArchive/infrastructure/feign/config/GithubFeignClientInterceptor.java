package Idea.Archive.IdeaArchive.infrastructure.feign.config;

import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class GithubFeignClientInterceptor implements RequestInterceptor {

    private final TokenProvider tokenProvider;

    public GithubFeignClientInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void apply(RequestTemplate template) {
        String accessToken = tokenProvider.generatedAccessToken()
        if (accessToken != null) {
            template.header("Authorization", "Bearer " + accessToken);
        }
    }
}