package Idea.Archive.IdeaArchive.domain.member.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.response.MyPageResponse;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public MyPageResponse execute() {
        Member currentMember = memberUtil.currentMember();
        Member member = memberRepository.findByEmail(currentMember.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다"));
        List<ViewPostResponse> heartList = ViewPostResponse.convertToHeartList(member.getHearts());
        if (heartList.isEmpty()) {
            throw new NotExistPostException("게시글이 존재하지 않습니다");
        }
        List<Post> posts = postRepository.findByMember(member);
        List<ViewPostResponse> myPostList = ViewPostResponse.convertToPostList(posts);
        if (myPostList.isEmpty()) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return MyPageResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .profileImg(member.getProfileImageUrl())
                .myPost(myPostList)
                .myHeartList(heartList)
                .build();
    }
}
