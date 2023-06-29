package Idea.Archive.IdeaArchive.domain.member.presentation;

import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request.OauthWithdrawMemberRequest;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.ChangePasswordRequest;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.WithdrawMemberRequest;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.response.MyPageResponse;
import Idea.Archive.IdeaArchive.domain.member.service.*;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.ChangeNameRequest;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.MakeNewPasswordRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MyPageService myPageService;
    private final WithdrawService withdrawService;
    private final FindPasswordService findPasswordService;
    private final ChangeNameService changeNameService;
    private final OauthWithdrawService oauthWithdrawService;
    private final ViewMyPostService viewMyPostService;
    private final ViewMyHeartService viewMyHeartService;

    @GetMapping
    public ResponseEntity<MyPageResponse> viewMyPage() {
        MyPageResponse myPageResponse = myPageService.execute();
        return ResponseEntity.ok().body(myPageResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> withdraw(@RequestBody @Valid WithdrawMemberRequest withdrawMemberRequest) {
        withdrawService.execute(withdrawMemberRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/oauth")
    public ResponseEntity<Void> withdraw(@RequestBody @Valid OauthWithdrawMemberRequest oauthWithdrawMemberRequest) {
        oauthWithdrawService.execute(oauthWithdrawMemberRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/find-password")
    public ResponseEntity<Void> findPassword(@RequestBody @Valid MakeNewPasswordRequest makeNewPasswordRequest) {
        findPasswordService.execute(makeNewPasswordRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/name")
    public ResponseEntity<Void> changeName(@RequestBody @Valid ChangeNameRequest changeNameRequest) {
        changeNameService.execute(changeNameRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts")
    public ResponseEntity<List<ViewPostResponse>> viewMyPost() {
        List<ViewPostResponse> response = viewMyPostService.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hearts-posts")
    public ResponseEntity<List<ViewPostResponse>> viewMyHeart() {
        List<ViewPostResponse> response = viewMyHeartService.execute();
        return ResponseEntity.ok(response);
    }
}
