package Idea.Archieve.IdeaArchieve.domain.auth.service;

import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberSignUpRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.RefreshTokenRepository;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void execute(MemberSignUpRequest memberSignUpRequest) {

    }
}
