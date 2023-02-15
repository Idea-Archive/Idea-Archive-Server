package Idea.Archieve.IdeaArchieve.domain.auth.presentation;

import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberLoginRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberSignUpRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.response.NewTokenResponse;
import Idea.Archieve.IdeaArchieve.domain.auth.service.MemberLoginService;
import Idea.Archieve.IdeaArchieve.domain.auth.service.MemberSignUpService;
import Idea.Archieve.IdeaArchieve.domain.auth.service.TokenReissuanceService;
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
    private final TokenReissuanceService tokenReissuanceService;

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

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse reIssueToken = tokenReissuanceService.execute(token);
        return new ResponseEntity<>(reIssueToken, HttpStatus.OK);
    }
}
