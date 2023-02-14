package Idea.Archieve.IdeaArchieve.domain.email.service;


import Idea.Archieve.IdeaArchieve.domain.email.entity.EmailAuth;
import Idea.Archieve.IdeaArchieve.domain.email.presentation.dto.response.VerifyCheckResponse;
import Idea.Archieve.IdeaArchieve.domain.email.repository.EmailAuthRepository;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailCheckService {


    private final EmailAuthRepository emailAuthRepository;
    private boolean check;

    public VerifyCheckResponse execute(String email, String authKey){
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(()->new MemberNotFoundException("유저를 찾을 수 없습니다."));
        checkAuth(emailAuth,authKey);
        VerifyCheckResponse verifyCheck = new VerifyCheckResponse(check);
        emailAuth.updateAuthentication(true);
        emailAuthRepository.save(emailAuth);
        return verifyCheck;
    }

    private void checkAuth(EmailAuth emailAuth, String authKey){
        if(Objects.equals(emailAuth.getRandomValue(),authKey)){
            log.info("인증성공");
            check = true;
        }else{
            log.info("인증실패");
            check = false;
        }
    }


}
