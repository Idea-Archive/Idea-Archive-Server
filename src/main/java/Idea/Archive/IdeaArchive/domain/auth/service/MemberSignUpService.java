package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.exception.ExistEmailException;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request.MemberSignUpRequest;
import Idea.Archive.IdeaArchive.domain.email.entity.EmailAuth;
import Idea.Archive.IdeaArchive.domain.email.exception.NotVerifyEmailException;
import Idea.Archive.IdeaArchive.domain.email.repository.EmailAuthRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.member.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;

    @Transactional(rollbackOn = Exception.class)
    public void execute(MemberSignUpRequest memberSignUpRequest) {

        if (memberRepository.existsByEmail(memberSignUpRequest.getEmail())) {
            throw new ExistEmailException();
        }

        EmailAuth emailAuth = emailAuthRepository.findById(memberSignUpRequest.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException());

        if (!emailAuth.getAuthentication()) {
            throw new NotVerifyEmailException();
        }

        Member member = Member
                .builder()
                .email(memberSignUpRequest.getEmail())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .name(memberSignUpRequest.getName())
                .role(Role.MEMBER)
                .profileImageUrl(null)
                .build();

        memberRepository.save(member);
    }
}
