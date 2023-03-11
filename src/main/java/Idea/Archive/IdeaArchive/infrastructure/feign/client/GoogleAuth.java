package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.GoogleCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuth {

    @PostMapping
    TokenResponse googleAuth(GoogleCodeRequest googleCodeRequest);

}
