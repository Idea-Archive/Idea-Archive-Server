package Idea.Archive.IdeaArchive.domain.auth.presentation;

import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.service.GithubAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/github")
@RestController
@RequiredArgsConstructor
public class GithubController {

    private final GithubAuthService githubAuthService;

    @GetMapping("/login")
    public ResponseEntity<MemberLoginResponse> githubAuthLogin(@RequestParam("code") String code) {
        MemberLoginResponse response = githubAuthService.execute(code);
        return ResponseEntity.ok().body(response);
    }
}
