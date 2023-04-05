package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubNameResponse;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "GithubNameInfoClient", url = "https://api.github.com/user")
@Component
public interface GithubNameInfo {

    @GetMapping
    @Headers("Authorization: Bearer {access_token}")
    GithubNameResponse githubNameInfo(@Param("access_token") String accessToken);
}