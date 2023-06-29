package Idea.Archive.IdeaArchive.domain.auth.presentation;

import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/google")
@RestController
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleAuthService googleAuthService;

    @GetMapping("/login")
    public ResponseEntity<MemberLoginResponse> googleAuthLogin(@RequestParam("code") String code) {
        MemberLoginResponse response = googleAuthService.execute(code);
        return ResponseEntity.ok().body(response);
    }
}
