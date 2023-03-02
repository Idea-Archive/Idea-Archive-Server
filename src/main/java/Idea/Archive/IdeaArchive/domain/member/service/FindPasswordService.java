package Idea.Archieve.IdeaArchieve.domain.member.service;


import Idea.Archieve.IdeaArchieve.domain.email.entity.EmailAuth;
import Idea.Archieve.IdeaArchieve.domain.email.exception.NotVerifyEmailException;
import Idea.Archieve.IdeaArchieve.domain.email.repository.EmailAuthRepository;
import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MisMatchPasswordException;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.MakeNewPasswordRequest;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FindPasswordService {

    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthRepository emailAuthRepository;


    @Transactional
    public void execute(MakeNewPasswordRequest makeNewPasswordRequest){
        Member member = memberUtil.currentMember();
        EmailAuth emailAuth = emailAuthRepository.findById(member.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException("인증되지 않은 이메일입니다."));
        if(emailAuth.getAuthentication()){
            if(!emailAuth.getEmail().equals(member.getEmail())){
                throw new NotVerifyEmailException("인증되지 않은 이메일 입니다.");
            }
            if (!makeNewPasswordRequest.getPassword().equals(makeNewPasswordRequest.getCheckPassword())){
                throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
            }
            member.updatePassword(passwordEncoder.encode(makeNewPasswordRequest.getCheckPassword()));
        }else{
            throw new NotVerifyEmailException("인증되지 않은 이메일 입니다.");
        }
    }
}
