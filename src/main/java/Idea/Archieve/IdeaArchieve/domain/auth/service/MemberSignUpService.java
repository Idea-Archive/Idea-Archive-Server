package Idea.Archieve.IdeaArchieve.domain.auth.service;

import Idea.Archieve.IdeaArchieve.domain.auth.exception.ExistEmailException;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberSignUpRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.RefreshTokenRepository;
import Idea.Archieve.IdeaArchieve.domain.email.entity.EmailAuth;
import Idea.Archieve.IdeaArchieve.domain.email.exception.NotVerifyEmailException;
import Idea.Archieve.IdeaArchieve.domain.email.repository.EmailAuthRepository;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import Idea.Archieve.IdeaArchieve.global.filter.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;

    public void execute(MemberSignUpRequest memberSignUpRequest) {

        if(memberRepository.existsByEmail(memberSignUpRequest.getEmail())){
            throw new ExistEmailException("이미 존재하는 이메일입니다.");
        }

        EmailAuth emailAuth = emailAuthRepository.findById(memberSignUpRequest.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException("인증되지 않은 이메일입니다."));

        if (!emailAuth.getAuthentication()) {
            throw new NotVerifyEmailException("인증되지 않은 이메일입니다.");
        }

        Member member = Member
                .builder()
                .email(memberSignUpRequest.getEmail())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .name(memberSignUpRequest.getName())
                .role(Role.from(memberSignUpRequest.getRole()))
                .build();

        memberRepository.save(member);
    }
}
