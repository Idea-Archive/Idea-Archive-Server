package Idea.Archive.IdeaArchive.domain.email.service;


import Idea.Archive.IdeaArchive.domain.email.entity.EmailAuth;
import Idea.Archive.IdeaArchive.domain.email.exception.FailedSendEmailException;
import Idea.Archive.IdeaArchive.domain.email.exception.ManyRequestEmailException;
import Idea.Archive.IdeaArchive.domain.email.presentation.dto.request.EmailAuthRequest;
import Idea.Archive.IdeaArchive.domain.email.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Async
@RequiredArgsConstructor
@Slf4j
public class EmailSendService {
    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender mailSender;


    @Async
    @Transactional(rollbackFor = Exception.class)
    public void execute(EmailAuthRequest emailAuthRequest) {

        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888)+1111);

        sendAuthEmail(emailAuthRequest.getEmail(),authKey);
    }

    private void sendAuthEmail(String email,String authKey) {

        String title = "IdeaArchive 인증번호";
        String content = "IdeaArchive 인증번호는 " + authKey + "입니다.";
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElse(EmailAuth.builder()
                        .authentication(false)
                        .randomValue(authKey)
                        .attemptCount(0)
                        .email(email)
                        .build());

        if(emailAuth.getAttemptCount() >= 100) {
            throw new ManyRequestEmailException("15분에 최대 100번까지 요청가능합니다.");
        }
        emailAuth.updateRandomValue(authKey);
        emailAuth.increaseAttemptCount();

        emailAuthRepository.save(emailAuth);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new FailedSendEmailException("메일 발송에 실패했습니다");
        }
    }
}
