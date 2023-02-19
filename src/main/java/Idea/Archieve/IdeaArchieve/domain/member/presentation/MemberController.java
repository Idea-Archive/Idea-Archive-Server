package Idea.Archieve.IdeaArchieve.domain.member.presentation;


import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.ChangePasswordRequest;
import Idea.Archieve.IdeaArchieve.domain.member.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final ChangePasswordService changePasswordService;

    @PatchMapping
    public ResponseEntity<Void> editPassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest){
        changePasswordService.execute(changePasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
