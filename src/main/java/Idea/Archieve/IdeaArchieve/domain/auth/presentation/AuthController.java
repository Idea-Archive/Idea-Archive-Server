package Idea.Archieve.IdeaArchieve.domain.auth.presentation;

import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberLoginRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberSignUpRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archieve.IdeaArchieve.domain.auth.service.MemberLoginService;
import Idea.Archieve.IdeaArchieve.domain.auth.service.MemberSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberSignUpService memberSignUpService;
    private final MemberLoginService memberLoginService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
        memberSignUpService.execute(memberSignUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public MemberLoginResponse login(@RequestBody @Valid MemberLoginRequest memberLoginRequest){
        return memberLoginService.execute(memberLoginRequest);
    }
}
