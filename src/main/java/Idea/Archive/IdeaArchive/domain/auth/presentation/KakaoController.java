package Idea.Archive.IdeaArchive.domain.auth.presentation;

import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/kakao")
@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoAuthService kakaoAuthService;

    @GetMapping("/receiveCode")
    public ResponseEntity<MemberLoginResponse> KakaoAuthLogin(@RequestParam("code") String code) {
        MemberLoginResponse memberLoginResponse = kakaoAuthService.execute(code);
        return ResponseEntity.ok().body(memberLoginResponse);
    }
}
