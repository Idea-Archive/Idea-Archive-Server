package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.KakaoInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoClientInfo", url = "https://kapi.kakao.com/v2/user/me")
@Component
public interface KakaoInfo {

    @GetMapping
    KakaoInfoResponse kakaoInfo(@RequestHeader String accessToken);
}
