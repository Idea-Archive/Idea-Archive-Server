package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.WritePostRequest;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WritePostService {
    private final PostRepository postRepository;
    private final MemberUtil memberUtil;
    @Transactional
    public void execute(WritePostRequest writePostRequest) {
        Member currentMember = memberUtil.currentMember();
        Post post = Post.builder()
                .title(writePostRequest.getTitle())
                .content(writePostRequest.getContent())
                .member(currentMember)
                .heartCount(0)
                .commentCount(0)
                .applicationCount(0)
                .build();
        postRepository.save(post);
    }
}
