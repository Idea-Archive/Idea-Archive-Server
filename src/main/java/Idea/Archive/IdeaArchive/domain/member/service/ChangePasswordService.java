package Idea.Archive.IdeaArchive.domain.member.service;


import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchPasswordException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.ChangePasswordRequest;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void execute(ChangePasswordRequest changePasswordRequest) {
        Member member = memberUtil.currentMember();
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), member.getPassword())){
            throw new MisMatchPasswordException("현재 비밀번호가 일치하지 않습니다.");
        }
        member.updatePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

    }
}
