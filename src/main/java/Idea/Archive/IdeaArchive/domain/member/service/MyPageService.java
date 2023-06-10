package Idea.Archive.IdeaArchive.domain.member.service;

import Idea.Archive.IdeaArchive.domain.heart.entity.Heart;
import Idea.Archive.IdeaArchive.domain.heart.repository.HeartRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.response.MyPageResponse;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
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
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public MyPageResponse execute() {
        Member currentMember = memberUtil.currentMember();
        Member member = memberRepository.findByEmail(currentMember.getEmail())
                .orElseThrow(() -> new MemberNotFoundException());
        return MyPageResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .profileImg(member.getProfileImageUrl())
                .build();
    }
}
