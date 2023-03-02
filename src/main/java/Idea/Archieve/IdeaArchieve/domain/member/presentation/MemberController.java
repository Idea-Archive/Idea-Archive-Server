package Idea.Archieve.IdeaArchieve.domain.member.presentation;


import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.ChangeNameRequest;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.ChangePasswordRequest;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.MakeNewPasswordRequest;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.response.MyPageResponse;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.response.MyPostResponse;
import Idea.Archieve.IdeaArchieve.domain.member.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final ChangePasswordService changePasswordService;
    private final MyPageService myPageService;
    private final WithdrawService withdrawService;
    private final ViewMyPostService viewMyPostService;
    private final FindPasswordService findPasswordService;
    private final ChangeNameService changeNameService;

    @PatchMapping
    public ResponseEntity<Void> editPassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest){
        changePasswordService.execute(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<MyPageResponse> viewMyPage(){
        MyPageResponse myPageResponse = myPageService.execute();
        return ResponseEntity.ok().body(myPageResponse);
    }
    @DeleteMapping
    public ResponseEntity<Void> withdraw(@RequestParam String email,@RequestParam String password){
        withdrawService.execute(email,password);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/myPost")
    public ResponseEntity<List<MyPostResponse>> viewMyPost(){
        List<MyPostResponse> myPostResponses = viewMyPostService.execute();
        return ResponseEntity.ok().body(myPostResponses);
    }
    @PatchMapping("/findPassword")
    public ResponseEntity<Void> findPassword(@RequestBody @Valid MakeNewPasswordRequest makeNewPasswordRequest){
        findPasswordService.execute(makeNewPasswordRequest);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/name")
    public ResponseEntity<Void> changeName(@RequestBody @Valid ChangeNameRequest changeNameRequest){
        changeNameService.execute(changeNameRequest);
        return ResponseEntity.noContent().build();
    }
}
