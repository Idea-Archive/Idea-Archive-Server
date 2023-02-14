package Idea.Archieve.IdeaArchieve.domain.email.presentation;


import Idea.Archieve.IdeaArchieve.domain.email.presentation.dto.request.EmailAuthRequest;
import Idea.Archieve.IdeaArchieve.domain.email.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailAuthController {

    private final EmailSendService emailSendService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailAuthRequest emailAuthRequest){
        emailSendService.execute(emailAuthRequest);
        return ResponseEntity.ok().build();
    }
}
