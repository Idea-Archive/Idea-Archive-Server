package Idea.Archieve.IdeaArchieve.domain.email.presentation;


import Idea.Archieve.IdeaArchieve.domain.email.presentation.dto.request.EmailAuthRequest;
import Idea.Archieve.IdeaArchieve.domain.email.service.EmailCheckService;
import Idea.Archieve.IdeaArchieve.domain.email.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailAuthController {

    private final EmailSendService emailSendService;
    private final EmailCheckService emailCheckService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailAuthRequest emailAuthRequest){
        emailSendService.execute(emailAuthRequest);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> verifyEmail(@RequestParam @Email String email, @RequestParam String authKey){
         emailCheckService.execute(email,authKey);
         return ResponseEntity.ok().build();
    }
}
