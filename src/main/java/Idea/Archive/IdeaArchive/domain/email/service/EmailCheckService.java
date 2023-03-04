package Idea.Archive.IdeaArchive.domain.email.service;


import Idea.Archive.IdeaArchive.domain.email.entity.EmailAuth;
import Idea.Archive.IdeaArchive.domain.email.exception.MisMatchAuthCodeException;
import Idea.Archive.IdeaArchive.domain.email.repository.EmailAuthRepository;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailCheckService {


    private final EmailAuthRepository emailAuthRepository;

    public void execute(String email, String authKey){
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(()->new MemberNotFoundException("유저를 찾을 수 없습니다."));
        checkAuth(emailAuth,authKey);
        emailAuth.updateAuthentication(true);
        emailAuthRepository.save(emailAuth);
    }

    private void checkAuth(EmailAuth emailAuth, String authKey){
        if(!Objects.equals(emailAuth.getRandomValue(),authKey)){
            throw new MisMatchAuthCodeException("인증번호가 일치하지 않습니다.");
        }
    }
}