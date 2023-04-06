package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubEmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GithubEmailInfoClient", url = "https://api.github.com/user/emails")
@Component
public interface GithubEmailInfo {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    GithubEmailResponse githubEmailInfo(@RequestHeader("Authorization") String accessToken);
}
