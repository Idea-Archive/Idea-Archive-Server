package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.GoogleCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
@Component
public interface GoogleAuth {

    @PostMapping
    GoogleTokenResponse googleAuth(GoogleCodeRequest googleCodeRequest);

}
