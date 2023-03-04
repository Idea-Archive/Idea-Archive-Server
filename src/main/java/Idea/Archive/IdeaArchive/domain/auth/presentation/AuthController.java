package Idea.Archive.IdeaArchive.domain.auth.presentation;

import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request.MemberLoginRequest;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request.MemberSignUpRequest;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.NewTokenResponse;
import Idea.Archive.IdeaArchive.domain.auth.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberSignUpService memberSignUpService;
    private final MemberLoginService memberLoginService;
    private final MemberLogoutService memberLogoutService;
    private final TokenReissuanceService tokenReissuanceService;
    private final GoogleAuthService googleAuthService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
        memberSignUpService.execute(memberSignUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody @Valid MemberLoginRequest memberLoginRequest){
        MemberLoginResponse memberLoginResponse = memberLoginService.execute(memberLoginRequest);
        return ResponseEntity.ok(memberLoginResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization")String accessToken){
        memberLogoutService.execute(accessToken);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse reIssueToken = tokenReissuanceService.execute(token);
        return ResponseEntity.ok(reIssueToken);
    }

    @GetMapping("/receiveCode")
    public ResponseEntity<MemberLoginResponse> GoogleAuthLogin(@RequestParam("code") String code) {
        MemberLoginResponse memberLoginResponse =  googleAuthService.execute(code);
        return ResponseEntity.ok().body(memberLoginResponse);
    }
}
