package Idea.Archieve.IdeaArchieve.domain.member.service;


import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MisMatchPasswordException;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.ChangePasswordRequest;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;

    public void execute(ChangePasswordRequest changePasswordRequest){
        Member member = memberUtil.currentMember();
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), member.getPassword())){
            throw new MisMatchPasswordException("현재 비밀번호가 일치하지 않습니다.");
        }
        member.updatePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

    }
}
