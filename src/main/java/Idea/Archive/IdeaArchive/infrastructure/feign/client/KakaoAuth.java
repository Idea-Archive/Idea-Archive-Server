package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.KakaoCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "KakaoAuthClient", url = "https://kauth.kakao.com/oauth/token")
public interface KakaoAuth {

    @PostMapping
    KakaoTokenResponse kakaoAuth(KakaoCodeRequest kakaoCodeRequest);
}
