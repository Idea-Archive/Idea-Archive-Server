package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.WritePost;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritePostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    public void WritePost(WritePost writePost) {
        Member member = memberUtil.currentMember();
        savePost(writePost, member);
    }

    private void savePost(WritePost writePost, Member member) {
        postRepository.save(
                Post.builder()
                        .title(writePost.getTitle())
                        .content(writePost.getContent())
                        .category(writePost.getCategory())
                        .member(member)
                        .build()
        );
    }

}
