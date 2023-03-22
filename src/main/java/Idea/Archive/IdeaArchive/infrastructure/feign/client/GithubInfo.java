package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(name = "GithubInfoClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
@Component
public interface GithubInfo {
}
