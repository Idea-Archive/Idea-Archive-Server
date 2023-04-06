package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubNameResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GithubNameInfoClient", url = "https://api.github.com/user")
@Component
public interface GithubNameInfo {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    GithubNameResponse githubNameInfo(@RequestHeader("Authorization") String accessToken);
}