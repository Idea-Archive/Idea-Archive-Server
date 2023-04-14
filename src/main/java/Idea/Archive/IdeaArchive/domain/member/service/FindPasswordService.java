package Idea.Archive.IdeaArchive.domain.member.service;



import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.MakeNewPasswordRequest;
import Idea.Archive.IdeaArchive.domain.email.entity.EmailAuth;
import Idea.Archive.IdeaArchive.domain.email.exception.NotVerifyEmailException;
import Idea.Archive.IdeaArchive.domain.email.repository.EmailAuthRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchPasswordException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.MakeNewPasswordRequest;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;


@Service
@RequiredArgsConstructor
public class FindPasswordService {

    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthRepository emailAuthRepository;


    @Transactional
    public void execute(@Valid MakeNewPasswordRequest makeNewPasswordRequest) {
        Member member = memberUtil.currentMember();
        EmailAuth emailAuth = emailAuthRepository.findById(member.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException("인증되지 않은 이메일입니다."));
        if (emailAuth.getAuthentication()) {
            if (!emailAuth.getEmail().equals(member.getEmail())) {
                throw new NotVerifyEmailException("인증되지 않은 이메일 입니다.");
            }
            if (!makeNewPasswordRequest.getPassword().equals(makeNewPasswordRequest.getCheckPassword())) {
                throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
            }
            member.updatePassword(passwordEncoder.encode(makeNewPasswordRequest.getCheckPassword()));
        } else {
            throw new NotVerifyEmailException("인증되지 않은 이메일 입니다.");
        }
    }
}
