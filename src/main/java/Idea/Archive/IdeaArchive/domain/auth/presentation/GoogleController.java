package Idea.Archive.IdeaArchive.domain.auth.presentation;

import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/google")
@RestController
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleAuthService googleAuthService;

    @GetMapping("/receive-code")
    public ResponseEntity<MemberLoginResponse> googleAuthLogin(@RequestParam("code") String code) {
        MemberLoginResponse memberLoginResponse = googleAuthService.execute(code);
        return ResponseEntity.ok().body(memberLoginResponse);
    }
}
