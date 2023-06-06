package Idea.Archive.IdeaArchive.domain.email.service;

import Idea.Archive.IdeaArchive.domain.email.entity.EmailAuth;
import Idea.Archive.IdeaArchive.domain.email.exception.MisMatchAuthCodeException;
import Idea.Archive.IdeaArchive.domain.email.repository.EmailAuthRepository;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailCheckService {

    private final EmailAuthRepository emailAuthRepository;

    @Transactional(rollbackOn = Exception.class)
    public void execute(String email, String authKey) {
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(() -> new MemberNotFoundException());
        checkAuth(emailAuth, authKey);
        emailAuth.updateAuthentication(true);
        emailAuthRepository.save(emailAuth);
    }

    private void checkAuth(EmailAuth emailAuth, String authKey) {
        if (!Objects.equals(emailAuth.getRandomValue(), authKey)) {
            throw new MisMatchAuthCodeException();
        }
    }
}
