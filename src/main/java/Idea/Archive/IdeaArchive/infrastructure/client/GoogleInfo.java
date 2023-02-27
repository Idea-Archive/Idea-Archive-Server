package Idea.Archive.IdeaArchive.infrastructure.client;

import Idea.Archive.IdeaArchive.infrastructure.dto.request.GoogleCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.dto.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleInfo {

    @PostMapping
    TokenResponse googleAuth(GoogleCodeRequest googleCodeRequest);
}
