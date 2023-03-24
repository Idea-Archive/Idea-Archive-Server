package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.GithubCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GithubAuthClient", url = "https://github.com/login/oauth/access_token")
@Component
public interface GithubAuth {

    @PostMapping(produces = "application/json")
    GithubTokenResponse githubAuth(GithubCodeRequest githubCodeRequest);

}
